package expenses.dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import expenses.model.statistics.CategoryExpense;
import expenses.model.statistics.MonthExpense;
import expenses.model.statistics.PersonCategoriesExpenses;
import expenses.model.statistics.PersonMonthsExpenses;

@Repository
public class StatisticsDao {

	private DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public PersonCategoriesExpenses getPersonCategoriesExpenses(String personName) {
		PersonCategoriesExpenses pce = new PersonCategoriesExpenses();
		ArrayList<CategoryExpense> categoriesExpenses = new ArrayList<CategoryExpense>();
		pce.setCategoriesExpenses(categoriesExpenses);
		pce.setPersonName(personName);
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = dataSource.getConnection();
			ps = connection.prepareStatement(StatisticsSQL.GET_PERSON_CATEGORIES_EXPENSES_SQL);
			ps.setString(1, personName);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CategoryExpense ce = new CategoryExpense();
				ce.setCategoryName(rs.getString("category"));
				ce.setExpense(rs.getDouble("expense"));
				categoriesExpenses.add(ce);
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return pce;
	}
	
	public PersonCategoriesExpenses getPersonCategoriesExpenses(String personName, Date dateFrom, Date dateTo ) {
		PersonCategoriesExpenses pce = new PersonCategoriesExpenses();
		ArrayList<CategoryExpense> categoriesExpenses = new ArrayList<CategoryExpense>();
		pce.setCategoriesExpenses(categoriesExpenses);
		pce.setPersonName(personName);
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			String sql = "";
			connection = dataSource.getConnection();
			if(dateFrom != null && dateTo != null) {
				Calendar calendar = GregorianCalendar.getInstance();
				calendar.setTime(dateFrom);
				sql = StatisticsSQL.GET_PERSON_CATEGORIES_EXPENSES_DATE_FROM_TO_SQL;
				ps = connection.prepareStatement(sql);
				ps.setString(1, personName);
				ps.setInt(2, calendar.get(Calendar.MONTH) + 1);
				ps.setInt(3, calendar.get(Calendar.YEAR));
				calendar.setTime(dateTo);
				ps.setInt(4, calendar.get(Calendar.MONTH) + 1);
				ps.setInt(5, calendar.get(Calendar.YEAR));
			} else if(dateFrom != null && dateTo == null) {
				sql = StatisticsSQL.GET_PERSON_CATEGORIES_EXPENSES_DATE_FROM_SQL;
				Calendar calendar = GregorianCalendar.getInstance();
				calendar.setTime(dateFrom);
				ps = connection.prepareStatement(sql);
				ps.setString(1, personName);
				ps.setInt(2, calendar.get(Calendar.MONTH) + 1);
				ps.setInt(3, calendar.get(Calendar.YEAR));
			} else if(dateTo != null && dateFrom == null) {
				sql = StatisticsSQL.GET_PERSON_CATEGORIES_EXPENSES_DATE_TO_SQL;
				Calendar calendar = GregorianCalendar.getInstance();
				calendar.setTime(dateTo);
				ps = connection.prepareStatement(sql);
				ps.setString(1, personName);
				ps.setInt(2, calendar.get(Calendar.MONTH) + 1);
				ps.setInt(3, calendar.get(Calendar.YEAR));
			} else {
				sql = StatisticsSQL.GET_PERSON_CATEGORIES_EXPENSES_SQL;
				ps = connection.prepareStatement(sql);
				ps.setString(1, personName);
			}
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CategoryExpense ce = new CategoryExpense();
				ce.setCategoryName(rs.getString("category"));
				ce.setExpense(rs.getDouble("expense"));
				categoriesExpenses.add(ce);
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return pce;
	}
	
	public PersonMonthsExpenses getPersonCategoriesMonthExpenses(String personName, String categoryName) {
		PersonMonthsExpenses pce = new PersonMonthsExpenses();
		ArrayList<MonthExpense> monthExpenses = new ArrayList<MonthExpense>();
		pce.setMonthsExpenses(monthExpenses);
		pce.setPersonName(personName);
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = dataSource.getConnection();
			ps = connection.prepareStatement(StatisticsSQL.GET_PERSON_CATEGORIES_MONTH_EXPENSES_SQL);
			ps.setString(1, personName);
			ps.setString(2, categoryName);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				MonthExpense me = new MonthExpense();
				me.setExpense(rs.getDouble("expense"));
				me.setMonth(rs.getInt("month"));
				me.setYear(rs.getInt("year"));
				monthExpenses.add(me);
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return pce;
	}
			
	public PersonCategoriesExpenses getPersonMonthCategoriesExpenses(String personName, int month, int year) {
		PersonCategoriesExpenses pce = new PersonCategoriesExpenses();
		ArrayList<CategoryExpense> categoriesExpenses = new ArrayList<CategoryExpense>();
		pce.setCategoriesExpenses(categoriesExpenses);
		pce.setPersonName(personName);
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = dataSource.getConnection();
			ps = connection.prepareStatement(StatisticsSQL.GET_PERSON_MONTH_CATEGORIES_EXPENSES_SQL);
			ps.setString(1, personName);
			ps.setInt(2, month);
			ps.setInt(3, year);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CategoryExpense ce = new CategoryExpense();
				ce.setCategoryName(rs.getString("category"));
				ce.setExpense(rs.getDouble("expense"));
				categoriesExpenses.add(ce);
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return pce;
	}
	
	public PersonCategoriesExpenses getPersonCategoriesExpenses(String personName, int month, int year) {
		PersonCategoriesExpenses pce = new PersonCategoriesExpenses();
		ArrayList<CategoryExpense> categoriesExpenses = new ArrayList<CategoryExpense>();
		pce.setCategoriesExpenses(categoriesExpenses);
		pce.setPersonName(personName);
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = dataSource.getConnection();
			ps = connection.prepareStatement(StatisticsSQL.GET_PERSON_CATEGORIES_MONTH_EXPENSES_SQL);
			ps.setString(1, personName);
			ps.setInt(2, month);
			ps.setInt(3, year);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CategoryExpense ce = new CategoryExpense();
				ce.setCategoryName(rs.getString("category"));
				ce.setExpense(rs.getDouble("expense"));
				categoriesExpenses.add(ce);
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return pce;
	}
	
	public PersonCategoriesExpenses getPersonSelectedCategoriesExpenses(String personName, List<String> categories) {
		PersonCategoriesExpenses pce = new PersonCategoriesExpenses();
		ArrayList<CategoryExpense> categoriesExpenses = new ArrayList<CategoryExpense>();
		pce.setCategoriesExpenses(categoriesExpenses);
		pce.setPersonName(personName);
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = dataSource.getConnection();
			ps = connection.prepareStatement(StatisticsSQL.GET_PERSON_SELECTED_CATEGORIES_EXPENSES_SQL);
			ps.setString(1, personName);
			Array expenseCategory = connection.createArrayOf("VARCHAR", categories.toArray(new String[categories.size()]));
			ps.setArray(2, expenseCategory);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CategoryExpense ce = new CategoryExpense();
				ce.setCategoryName(rs.getString("category"));
				ce.setExpense(rs.getDouble("expense"));
				categoriesExpenses.add(ce);
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return pce;
	}
	
	public PersonCategoriesExpenses getPersonSelectedCategoriesExpenses(String personName, 
																		List<String> categories,
																		Date dateFrom,
																		Date dateTo) {
		PersonCategoriesExpenses pce = new PersonCategoriesExpenses();
		ArrayList<CategoryExpense> categoriesExpenses = new ArrayList<CategoryExpense>();
		pce.setCategoriesExpenses(categoriesExpenses);
		pce.setPersonName(personName);
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			String sql = "";
			connection = dataSource.getConnection();
			if(dateFrom != null && dateTo != null) {
				Calendar calendar = GregorianCalendar.getInstance();
				calendar.setTime(dateFrom);
				sql = StatisticsSQL.GET_PERSON_SELECTED_CATEGORIES_EXPENSES_DATE_FROM_TO_SQL;
				ps = connection.prepareStatement(sql);
				ps.setString(1, personName);
				Array expenseCategory = connection.createArrayOf("VARCHAR", categories.toArray(new String[categories.size()]));
				ps.setArray(2, expenseCategory);
				ps.setInt(3, calendar.get(Calendar.MONTH) + 1);
				ps.setInt(4, calendar.get(Calendar.YEAR));
				calendar.setTime(dateTo);
				ps.setInt(5, calendar.get(Calendar.MONTH) + 1);
				ps.setInt(6, calendar.get(Calendar.YEAR));
			} else if(dateFrom != null && dateTo == null) {
				sql = StatisticsSQL.GET_PERSON_SELECTED_CATEGORIES_EXPENSES_DATE_FROM_SQL;
				Calendar calendar = GregorianCalendar.getInstance();
				calendar.setTime(dateFrom);
				ps = connection.prepareStatement(sql);
				ps.setString(1, personName);
				Array expenseCategory = connection.createArrayOf("VARCHAR", categories.toArray(new String[categories.size()]));
				ps.setArray(2, expenseCategory);
				ps.setInt(3, calendar.get(Calendar.MONTH) + 1);
				ps.setInt(4, calendar.get(Calendar.YEAR));
			} else if(dateTo != null && dateFrom == null) {
				sql = StatisticsSQL.GET_PERSON_SELECTED_CATEGORIES_EXPENSES_DATE_TO_SQL;
				Calendar calendar = GregorianCalendar.getInstance();
				calendar.setTime(dateTo);
				ps = connection.prepareStatement(sql);
				ps.setString(1, personName);
				Array expenseCategory = connection.createArrayOf("VARCHAR", categories.toArray(new String[categories.size()]));
				ps.setArray(2, expenseCategory);
				ps.setInt(3, calendar.get(Calendar.MONTH) + 1);
				ps.setInt(4, calendar.get(Calendar.YEAR));
			} else {
				sql = StatisticsSQL.GET_PERSON_SELECTED_CATEGORIES_EXPENSES_SQL;
				ps = connection.prepareStatement(sql);
				ps.setString(1, personName);
				Array expenseCategory = connection.createArrayOf("VARCHAR", categories.toArray(new String[categories.size()]));
				ps.setArray(2, expenseCategory);
			}
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				CategoryExpense ce = new CategoryExpense();
				ce.setCategoryName(rs.getString("category"));
				ce.setExpense(rs.getDouble("expense"));
				categoriesExpenses.add(ce);
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return pce;
	}
	
	public PersonMonthsExpenses getPersonMonthsExpenses(String personName) {
		PersonMonthsExpenses pme = new PersonMonthsExpenses();
		List<MonthExpense> monthsExpenses = new ArrayList<MonthExpense>();
		pme.setMonthsExpenses(monthsExpenses);
		pme.setPersonName(personName);
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = dataSource.getConnection();
			ps = connection.prepareStatement(StatisticsSQL.GET_PERSON_MONTHS_EXPENSES_SQL);
			ps.setString(1, personName);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				MonthExpense me = new MonthExpense();
				me.setMonth(rs.getInt("month"));
				me.setYear(rs.getInt("year"));
				me.setExpense(rs.getDouble("expense"));
				monthsExpenses.add(me);
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return pme;
	}
	
	private static class StatisticsSQL {
		public static String GET_PERSON_CATEGORIES_EXPENSES_SQL = 
				  "SELECT PERSON, SUM(EXPENSE) EXPENSE, CATEGORY " +
				  "  FROM VM_PERSON_EXPENSES_BY_CATEGORY_AND_MONTHS " +
				  " WHERE PERSON ILIKE ? " +
				  " GROUP BY PERSON, CATEGORY ";
		
		public static String GET_PERSON_CATEGORIES_EXPENSES_DATE_FROM_SQL = 
				  "SELECT PERSON, SUM(EXPENSE) EXPENSE, CATEGORY " +
				  "  FROM VM_PERSON_EXPENSES_BY_CATEGORY_AND_MONTHS " +
				  " WHERE PERSON ILIKE ? " +
				  "   AND MONTH >= ? " +
				  "   AND YEAR >= ? " +
				  " GROUP BY PERSON, CATEGORY ";
		
		public static String GET_PERSON_CATEGORIES_EXPENSES_DATE_TO_SQL = 
				  "SELECT PERSON, SUM(EXPENSE) EXPENSE, CATEGORY " +
				  "  FROM VM_PERSON_EXPENSES_BY_CATEGORY_AND_MONTHS " +
				  " WHERE PERSON ILIKE ? " +
				  "   AND MONTH <= ? " +
				  "   AND YEAR <= ? " +
				  " GROUP BY PERSON, CATEGORY ";
		
		public static String GET_PERSON_CATEGORIES_EXPENSES_DATE_FROM_TO_SQL = 
				  "SELECT PERSON, SUM(EXPENSE) EXPENSE, CATEGORY " +
				  "  FROM VM_PERSON_EXPENSES_BY_CATEGORY_AND_MONTHS " +
				  " WHERE PERSON ILIKE ? " +
				  "   AND MONTH >= ? " +
				  "   AND YEAR >= ? " +
				  "   AND MONTH <= ? " +
				  "   AND YEAR <= ? " +
				  " GROUP BY PERSON, CATEGORY ";
		
		public static String GET_PERSON_MONTH_CATEGORIES_EXPENSES_SQL = 
				  "SELECT PERSON, EXPENSE, CATEGORY " +
				  "  FROM VM_PERSON_EXPENSES_BY_CATEGORY_AND_MONTHS " +
				  " WHERE PERSON ILIKE ? " +
				  "   AND MONTH= ? " +
				  "   AND YEAR= ? ";
		
		public static String GET_PERSON_CATEGORIES_MONTH_EXPENSES_SQL = 
				  "SELECT PERSON, EXPENSE, MONTH, YEAR  " +
				  "  FROM VM_PERSON_EXPENSES_BY_CATEGORY_AND_MONTHS " +
				  " WHERE PERSON ILIKE ? " +
				  "   AND CATEGORY ILIKE ?";
		
		public static String GET_PERSON_SELECTED_CATEGORIES_EXPENSES_SQL = 
				  "SELECT PERSON, SUM(EXPENSE) EXPENSE, CATEGORY " +
				  "  FROM VM_PERSON_EXPENSES_BY_CATEGORY_AND_MONTHS " +
				  " WHERE PERSON ILIKE ? " +
				  "   AND CATEGORY = ANY(?)" +
				  " GROUP BY PERSON, CATEGORY ";
		
		public static String GET_PERSON_SELECTED_CATEGORIES_EXPENSES_DATE_FROM_SQL = 
				  "SELECT PERSON, SUM(EXPENSE) EXPENSE, CATEGORY " +
				  "  FROM VM_PERSON_EXPENSES_BY_CATEGORY_AND_MONTHS " +
				  " WHERE PERSON ILIKE ? " +
				  "   AND CATEGORY = ANY(?)" +
				  "   AND MONTH >= ? " +
				  "   AND YEAR >= ? " +
				  " GROUP BY PERSON, CATEGORY ";
		
		public static String GET_PERSON_SELECTED_CATEGORIES_EXPENSES_DATE_TO_SQL = 
				  "SELECT PERSON, SUM(EXPENSE) EXPENSE, CATEGORY " +
				  "  FROM VM_PERSON_EXPENSES_BY_CATEGORY_AND_MONTHS " +
				  " WHERE PERSON ILIKE ? " +
				  "   AND CATEGORY = ANY(?)" +
				  "   AND MONTH <= ? " +
				  "   AND YEAR <= ? " +
				  " GROUP BY PERSON, CATEGORY ";
		
		public static String GET_PERSON_SELECTED_CATEGORIES_EXPENSES_DATE_FROM_TO_SQL = 
				  "SELECT PERSON, SUM(EXPENSE) EXPENSE, CATEGORY " +
				  "  FROM VM_PERSON_EXPENSES_BY_CATEGORY_AND_MONTHS " +
				  " WHERE PERSON ILIKE ? " +
				  "   AND CATEGORY = ANY(?)" +
				  "   AND MONTH >= ? " +
				  "   AND YEAR >= ? " +
				  "   AND MONTH <= ? " +
				  "   AND YEAR <= ? " +
				  " GROUP BY PERSON, CATEGORY ";
		
		public static String GET_PERSON_MONTHS_EXPENSES_SQL = 
				  "SELECT MONTH,YEAR,SUM(EXPENSE) AS EXPENSE " +
				  "  FROM VM_PERSON_EXPENSES_BY_CATEGORY_AND_MONTHS M " +
				  " WHERE PERSON ILIKE ? " +
				  " GROUP BY PERSON,MONTH,YEAR";
	}
	
	
	
}
