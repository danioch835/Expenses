package expenses.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import expenses.model.Person;

@Repository
public class PersonDao {
	private DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Person> getPersons() throws SQLException {
		ArrayList<Person> persons = new ArrayList<Person>();
		
		Statement statement = null;
		
		try {
			Connection connection = dataSource.getConnection();
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(SQL_GET_PERSONS);
			while(result.next()) {
				Person person = new Person();
				person.setId(result.getLong("nprs"));
				person.setName(result.getString("name"));
				persons.add(person);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(statement != null) {
				statement.close();
			}
		}
		return persons;
	}
	
	public Person getPersonByName(String name) throws SQLException {
		Person person = new Person();
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(SQL_GET_PERSON_BY_NAME);
			statement.setString(1, name);
			ResultSet result = statement.executeQuery();
			if(result.next()) {
				person.setId(result.getLong("nprs"));
				person.setName(result.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(statement != null) {
				statement.close();
			}
			if(connection != null) {
				connection.close();
			}
		}
		return person;
	}
	
	private String SQL_GET_PERSONS =
			"SELECT NPRS, NAME" + 
			"  FROM PRS";
	
	private String SQL_GET_PERSON_BY_NAME =
			"SELECT NPRS, NAME" + 
			"  FROM PRS" +
			" WHERE NAME ILIKE ?";
	
}
