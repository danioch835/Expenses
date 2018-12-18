package expenses.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import expenses.model.Expense;
import expenses.model.ExpenseDetail;

@Repository
public class ExpenseDao {
	
	private DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public boolean addExpense(Expense expense) throws SQLException {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date data = expense.getData();
		String dataFormated = (data != null) ? sdf.format(data) : "";
		int result = 0;
		
		try {
			connection = dataSource.getConnection();
			ps = connection.prepareStatement(SQL_ADD_EXPENSE);
			ps.setDouble(1, expense.getCost());
			ps.setLong(2, expense.getShopId());
			ps.setLong(3, expense.getPersonId());
			ps.setLong(4, expense.getShoppingCategoryId());
			ps.setLong(5, expense.getCityId());
			ps.setLong(6, expense.getZwrot());
			ps.setLong(7, expense.getPersonZwrotId());
			ps.setString(8, dataFormated);
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null) {
				ps.close();
			}
			if(connection != null) {
				connection.close();
			}
		}
		return result > 0;
	}
	
	public List<ExpenseDetail> getExpensesDetails() throws SQLException {
		return getExpensesDetailsWithLimit(0);
	}
	
	public List<ExpenseDetail> getExpensesDetails(int limit) throws SQLException {
		return getExpensesDetailsWithLimit(limit);
	}
	
	private List<ExpenseDetail> getExpensesDetailsWithLimit(int limit) throws SQLException {
		
		ArrayList<ExpenseDetail> expenses = new ArrayList<ExpenseDetail>();
		
		Connection connection = null;
		Statement st = null;
		
		try {
			connection = dataSource.getConnection();
			st = connection.createStatement();
			

			String sql = SQL_GET_EXPENSES_DETAILS;
			if(limit  > 0)
				sql += "LIMIT " + limit;
			ResultSet result = st.executeQuery(sql);
			
			while(result.next()) {
				
				ExpenseDetail expenseDetail = new ExpenseDetail();
				expenseDetail.setPersonName(result.getString("prsname"));
				expenseDetail.setCategoryName(result.getString("category"));
				expenseDetail.setCityName(result.getString("cityname"));
				expenseDetail.setData(result.getString("data"));
				expenseDetail.setKoszt(result.getDouble("koszt"));
				expenseDetail.setShopName(result.getString("shpname"));
				expenseDetail.setZwrot(result.getDouble("zwrot"));
				expenseDetail.setZwrotPersonName(result.getString("zwprsname"));
				expenseDetail.setZwrotProcent(result.getLong("zwrotprocent"));
				
				expenses.add(expenseDetail);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(st != null) {
				st.close();
			}
			if(connection != null) {
				connection.close();
			}
		}
		return expenses;
	}
	
	private static String SQL_ADD_EXPENSE =
			"INSERT INTO EXP(KOSZT,NSHP,NPRS,NSCT,NCIT, ZWROT, ZWPRS, DATA) " +
			" VALUES(?,?,?,?,?,?,?,to_timestamp(?, 'YY-MM-DD')::timestamp without time zone)";
	
	private static String SQL_GET_EXPENSES_DETAILS =
			"SELECT prsname, koszt, zwrotprocent, zwrot, zwprsname, shpname, category, cityname, to_char(data, 'YYYY-MM-DD') as data" +
			"  FROM VM_EXP_DETAILS "+ 
			" ORDER BY DATA DESC ";
}
