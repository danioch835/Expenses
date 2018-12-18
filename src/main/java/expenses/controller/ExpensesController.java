package expenses.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import expenses.dao.CityDao;
import expenses.dao.ExpenseCategoryDao;
import expenses.dao.ExpenseDao;
import expenses.dao.PersonDao;
import expenses.dao.ShopDao;
import expenses.dataimport.ExcelExpensesReader;
import expenses.dataimport.ExcelValidationResult;
import expenses.model.City;
import expenses.model.Expense;
import expenses.model.ExpenseCategory;
import expenses.model.ExpenseDetail;
import expenses.model.Person;
import expenses.model.Shop;
import expenses.utils.ExpensesUtils;

@Controller
public class ExpensesController {
	
	@Autowired
	private PersonDao personDao;
	@Autowired
	private CityDao cityDao;
	@Autowired
	private ExpenseCategoryDao expenseCategoryDao;
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private ExpenseDao expenseDao;
	
	@RequestMapping(value="/allExpensesDetails")
	public ModelAndView getAllExpensesDetails(Model uiModel) throws SQLException {
		
		ArrayList<ExpenseDetail> expenses = (ArrayList<ExpenseDetail>) expenseDao.getExpensesDetails();
		
		uiModel.addAttribute("expenses", expenses);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("expensesTable");
		return modelAndView;
	}
	
	@RequestMapping(value="/expenses")
	public ModelAndView getExpenses(Model uiModel) throws SQLException {
		
		ArrayList<Person> persons = (ArrayList<Person>) personDao.getPersons();
		ArrayList<ExpenseDetail> expenses = (ArrayList<ExpenseDetail>) expenseDao.getExpensesDetails();
		
		uiModel.addAttribute("persons", persons);
		uiModel.addAttribute("expenses", expenses);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("expenses");
		return modelAndView;
	}
	
	@RequestMapping(value="/addExpense")
	public ModelAndView addExpensePage(Model uiModel) throws SQLException {
		
		ArrayList<Person> persons = (ArrayList<Person>) personDao.getPersons();
		ArrayList<City> cities = (ArrayList<City>) cityDao.getCitiesList();
		ArrayList<Shop> shops = (ArrayList<Shop>) shopDao.getShops();
		ArrayList<ExpenseCategory> expenseCategories = (ArrayList<ExpenseCategory>) expenseCategoryDao.getExpenseCategories();
		
		ArrayList<ExpenseDetail> expensesDetail =  (ArrayList<ExpenseDetail>) expenseDao.getExpensesDetails(10);
		
		uiModel.addAttribute("persons", persons);
		uiModel.addAttribute("cities", cities);
		uiModel.addAttribute("shops", shops);
		uiModel.addAttribute("expenseCategories", expenseCategories);
		uiModel.addAttribute("expenses", expensesDetail);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("addExpense");
		return modelAndView;
	}
	
	@RequestMapping(value="/addExpense", method = RequestMethod.POST)
	public ModelAndView saveExpensePage( Model uiModel,
									     Double koszt,
									     String shopName,
								   	     String personName,
									     String categoryName,
									     String cityName,
									     Integer zwrot,
									     String personZwrotName,
									     String data) throws SQLException, ParseException {
		
		Shop shop = shopDao.getShopByName(shopName);
		Person person = personDao.getPersonByName(personName);
		Person zwrotPerson = personDao.getPersonByName(personZwrotName);
		ExpenseCategory expenseCategory = expenseCategoryDao.getExpenseCategoryByName(categoryName);
		City city = cityDao.getCityByName(cityName);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dataWydatku = sdf.parse(data);
		
		Expense expense = new Expense();
		expense.setCityId(city.getId());
		expense.setPersonId(person.getId());
		expense.setPersonZwrotId(zwrotPerson.getId());
		expense.setShopId(shop.getId());
		expense.setShoppingCategoryId(expenseCategory.getId());
		expense.setCost(koszt);
		expense.setZwrot(zwrot);
		expense.setData(dataWydatku);
		
		expenseDao.addExpense(expense);
		
		return addExpensePage(uiModel);
	}
	
	@RequestMapping(value="/loadDataFromExcel", method = {RequestMethod.GET})
	public ModelAndView loadDataFromExcel() {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("excelData");
		return modelAndView;
	}
	
	@RequestMapping(value="/loadDataFromExcel", method = {RequestMethod.POST})
	public ModelAndView loadDataFromExcel(Model uiModel,
										  @RequestParam(value="file", required=false) MultipartFile file) throws ParseException, IOException {
		
		List<ExpenseDetail> expenses = new ArrayList<ExpenseDetail>();
		if(file != null) {
			ExcelExpensesReader excel = new ExcelExpensesReader();
			expenses = excel.getExpenseDetailsFromExcelFile(file.getInputStream());
			
			byte[] bytes = file.getBytes();
			String fileName = file.getOriginalFilename();
			
			File dir = new File("excelFilesExpenses");
			if(!dir.exists()) {
				dir.mkdir();
			}
			File excelFile = new File("excelFilesExpenses/" + fileName);
			
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(excelFile));
			stream.write(bytes);
			stream.flush();
			stream.close();
			
			uiModel.addAttribute("uploadedFileName", fileName);
			uiModel.addAttribute("expenses", expenses);
			uiModel.addAttribute("correctExpensesLoaded", expenses.size());
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("excelData");
		return modelAndView;
	}
	
	@RequestMapping(value="/checkExpensesInExcelFile", method = {RequestMethod.POST})
	@ResponseBody
	public String checkExpensesInExcelFile(@RequestParam(value="file") MultipartFile file) throws ParseException, IOException {
		
		ExcelValidationResult result = new ExcelValidationResult();
		
		if(file != null) {
			
			ExcelExpensesReader excel = new ExcelExpensesReader();
			result = excel.validateExpensesInFile(file);
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		return mapper.writeValueAsString(result);
	}
	
	@RequestMapping(value="/saveExpensesFromExcelFile", method = {RequestMethod.POST})
	public ModelAndView saveExpensesFromExcelFile(String fileName) throws ParseException, IOException, SQLException {
		
		List<ExpenseDetail> expenses = new ArrayList<ExpenseDetail>();
		if(fileName != null) {
			
			File file = new File("excelFilesExpenses/" + fileName);
			
			if(file.exists()) {
				InputStream is = FileUtils.openInputStream(file);
				
				ExcelExpensesReader excel = new ExcelExpensesReader();
				expenses = excel.getExpenseDetailsFromExcelFile(is);
				
				for(ExpenseDetail expenseDetail : expenses) {
					Shop shop = shopDao.getShopByName(expenseDetail.getShopName());
					Person person = personDao.getPersonByName(expenseDetail.getPersonName());
					Person zwrotPerson = personDao.getPersonByName(expenseDetail.getZwrotPersonName());
					ExpenseCategory expenseCategory = expenseCategoryDao.getExpenseCategoryByName(expenseDetail.getCategoryName());
					City city = cityDao.getCityByName(expenseDetail.getCityName());
					
					//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					//Date dataWydatku = sdf.parse(expenseDetail.getData());
					
					Date dataWydatku = ExpensesUtils.getDateFromString(expenseDetail.getData());
					
					Expense expense = new Expense();
					expense.setCityId(city.getId());
					expense.setPersonId(person.getId());
					expense.setPersonZwrotId(zwrotPerson.getId());
					expense.setShopId(shop.getId());
					expense.setShoppingCategoryId(expenseCategory.getId());
					expense.setCost(expenseDetail.getKoszt());
					expense.setZwrot(expenseDetail.getZwrotProcent());
					expense.setData(dataWydatku);
					try {
						expenseDao.addExpense(expense);
					} catch(SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
			
		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:addExpense");
		return modelAndView;
	}
}
