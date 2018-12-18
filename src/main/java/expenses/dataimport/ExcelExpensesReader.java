package expenses.dataimport;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.multipart.MultipartFile;

import expenses.model.ExpenseDetail;

public class ExcelExpensesReader {
	
	public List<ExpenseDetail> getExpenseDetailsFromExcelFile(InputStream fileInputStream) throws ParseException {
		HSSFWorkbook excelFile = null;
		HSSFSheet sheet = null;
		InputStream is;
		List<ExpenseDetail> expenses = new ArrayList<ExpenseDetail>();
		try {
			is = fileInputStream;
			excelFile = new HSSFWorkbook(is);
			sheet = excelFile.getSheetAt(0);
			
			List<ExpenseDetail> expensesDaniel = new ArrayList<ExpenseDetail>();
			List<ExpenseDetail> expensesDorota = new ArrayList<ExpenseDetail>();
			
			ExpenseDetail expDaniel;
			ExpenseDetail expDorota;
			
			for(int i=2; i < 26; i++) {
				Row row = sheet.getRow(i);
				expDaniel = getExpenseDetailFromRow(row, 0, "Daniel");
				expDorota = getExpenseDetailFromRow(row, 8, "Dorota");
				
				if(expDaniel.isCorrect()) {
					expensesDaniel.add(expDaniel);
				}
				if(expDorota.isCorrect()) {
					expensesDorota.add(expDorota);
				}
			}
			expenses.addAll(expensesDaniel);
			expenses.addAll(expensesDorota);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(excelFile != null) {
			try {
				excelFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return expenses;
	}
	
	public ExcelValidationResult validateExpensesInFile(MultipartFile file) throws ParseException {
		ExcelValidationResult result = new ExcelValidationResult();
		
		HSSFWorkbook excelFile = null;
		HSSFSheet sheet = null;
		InputStream is;
		int correctExpensesCount = 0;
		int incorrectExpensesCount = 0;
		try {
			is = file.getInputStream();
			excelFile = new HSSFWorkbook(is);
			
			sheet = excelFile.getSheetAt(0);
			
			ExpenseDetail expDaniel;
			ExpenseDetail expDorota;
			
			boolean checkDorotaExpense = true;
			boolean checkDanielExpense = true;
			int rowIndex = 2;
			while(true) {
				Row row = sheet.getRow(rowIndex);
				expDaniel = getExpenseDetailFromRow(row, 0, "Daniel");
				expDorota = getExpenseDetailFromRow(row, 8, "Dorota");
				
				if(expDaniel.isCorrect()) {
					correctExpensesCount++;
				} else {
					if(expDaniel.getData() != null) {
						incorrectExpensesCount++;
					} else {
						checkDanielExpense = false;
					}
				}
				if(expDorota.isCorrect()) {
					correctExpensesCount++;
				} else {
					if(expDorota.getData() != null) {
						incorrectExpensesCount++;
					} else {
						checkDorotaExpense = false;
					}
				}
				
				if(!checkDanielExpense && !checkDorotaExpense) {
					break;
				}
				
				rowIndex++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(excelFile != null) {
			try {
				excelFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		result.setCorrectExpenses(correctExpensesCount);
		result.setIncorrectExpenses(incorrectExpensesCount);
		
		return result;
	}
	
	private String getStringValueFromCell(Cell cell) {
		if(cell != null) {
			switch(cell.getCellTypeEnum()) {
			case NUMERIC:
				Double doubleValue = cell.getNumericCellValue();
				return String.valueOf(doubleValue);
			case STRING:
				return cell.getStringCellValue();
			default:
				return "";
			}
		}
		return "";
	}
	
	private Double getNumericValueFromCell(Cell cell) {
		if(cell != null) {
			switch(cell.getCellTypeEnum()) {
			case NUMERIC:
				return cell.getNumericCellValue();
			case STRING:
				return Double.valueOf(cell.getStringCellValue());
			default:
				return 0.0;
			}
		}
		return 0.0;
	}
	
	private ExpenseDetail getExpenseDetailFromRow(Row row, int startColumnIndex, String personName) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		ExpenseDetail expenseDetail = new ExpenseDetail();
		
		String data = getStringValueFromCell(row.getCell(startColumnIndex));
		String gdzie = getStringValueFromCell(row.getCell(startColumnIndex+1));
		String miasto = getStringValueFromCell(row.getCell(startColumnIndex+2));
		String kategoria = getStringValueFromCell(row.getCell(startColumnIndex+3));
		Double ile = getNumericValueFromCell(row.getCell(startColumnIndex+4));
		Double zwrotProcent = getNumericValueFromCell(row.getCell(startColumnIndex+5));
		
		if(!data.isEmpty()) {
			expenseDetail.setData(sdf.format(sdf.parse(data)));
			expenseDetail.setShopName(gdzie);
			expenseDetail.setCityName(miasto);
			expenseDetail.setCategoryName(kategoria);
			expenseDetail.setKoszt(ile);
			expenseDetail.setZwrotProcent(zwrotProcent.longValue());
			expenseDetail.setZwrot(expenseDetail.getKoszt()*expenseDetail.getZwrotProcent()/100);
			expenseDetail.setPersonName(("Daniel".equals(personName) ? "Daniel" : "Dorota"));
			expenseDetail.setZwrotPersonName(("Daniel".equals(personName) ? "Dorota" : "Daniel"));
		}
		return expenseDetail;
	}
}
