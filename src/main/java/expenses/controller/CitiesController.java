package expenses.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import expenses.dao.CityDao;
import expenses.model.City;

@Controller
public class CitiesController {

	@Autowired
	private CityDao cityDao;
	
	@RequestMapping(value="/cities")
	public ModelAndView getCities(Model uiModel) throws SQLException {
		
		List<City> cities = cityDao.getCitiesList();
		
		uiModel.addAttribute("cities", cities);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("cities");
		return modelAndView;
	}
	
	@RequestMapping(value="/city/i-{id}")
	public ModelAndView getCity(Model uiModel,
							  @PathVariable(value="id") Long id) throws SQLException {
		
		List<City> cities = new ArrayList<City>();
		City city = cityDao.getCityById(id);
		cities.add(city);
		
		uiModel.addAttribute("cities", cities);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("cities");
		return modelAndView;
	}
	
	@RequestMapping(value="/city/n-{name}")
	public ModelAndView getCity(Model uiModel,
							    @PathVariable(value="name") String name) throws SQLException {
		
		List<City> cities = new ArrayList<City>();
		City city = cityDao.getCityByName(name);
		cities.add(city);
		
		uiModel.addAttribute("cities", cities);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("cities");
		return modelAndView;
	}
}
