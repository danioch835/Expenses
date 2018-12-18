package expenses.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import expenses.dao.PersonDao;
import expenses.model.Person;

@Controller
public class PersonController {

	@Autowired
	private PersonDao personDao;
	
	@RequestMapping(value="/persons")
	public ModelAndView getPersons(Model uiModel) throws SQLException {
		
		List<Person> persons = personDao.getPersons();
		
		uiModel.addAttribute("persons", persons);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("persons");
		return modelAndView;
	}
	
}
