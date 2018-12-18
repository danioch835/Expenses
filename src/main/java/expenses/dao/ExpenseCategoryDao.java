package expenses.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import expenses.model.ExpenseCategory;

@Repository
public class ExpenseCategoryDao {
	private DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<ExpenseCategory> getExpenseCategories() throws SQLException {
		ArrayList<ExpenseCategory> categories = new ArrayList<ExpenseCategory>();
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = dataSource.getConnection();
			ps = connection.prepareStatement(SQL_GET_CATEGORIES);
			ResultSet result = ps.executeQuery();
			
			while(result.next()) {
				Long id = result.getLong("nsct");
				String name = result.getString("name");
				
				ExpenseCategory expenseCategory = new ExpenseCategory();
				expenseCategory.setId(id);
				expenseCategory.setName(name);
				categories.add(expenseCategory);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null) {
				ps.close();
			}
			if(connection != null) {
				connection.close();
			}
		}
		return categories;
	}
	
	public ExpenseCategory getExpenseCategoryByName(String name) throws SQLException {
		ExpenseCategory category = new ExpenseCategory();
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = dataSource.getConnection();
			ps = connection.prepareStatement(SQL_GET_CATEGORY_BY_NAME);
			ps.setString(1, name);
			ResultSet result = ps.executeQuery();
			
			if(result.next()) {
				category.setId(result.getLong("nsct"));
				category.setName(result.getString("name"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(ps != null) {
				ps.close();
			}
			if(connection != null) {
				connection.close();
			}
		}
		return category;
	}
	
	/*private static String SQL_GET_EXPENSES_CATEGORIES_STATISTICS = 
			"SELECT PERSON,EXPENSE,CATEGORY,MONTH,YEAR " +
			"  FROM vm_person_expenses_by_category_and_months ";*/
	
	private static String SQL_GET_CATEGORIES = 
			"SELECT NSCT, NAME" +
			"  FROM SCT ";
	
	private static String SQL_GET_CATEGORY_BY_NAME = 
			"SELECT NSCT, NAME" +
			"  FROM SCT " +
		    " WHERE NAME ILIKE ?";
}
