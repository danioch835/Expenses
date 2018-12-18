package expenses.jobs;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import expenses.dao.PersonDao;


//@Service
public class MySimpleJob {
	
	@Autowired
	PersonDao personDao;
	
//	@Autowired
//	SimpleService simpleService;
	
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}
	
	public void execute() throws SQLException {
//		Person person = personDao.getPersons().get(0);
//		System.out.println("Zadanie JOB " + person.getName() + " .Service: " + simpleService.getName());
	}
	
}
