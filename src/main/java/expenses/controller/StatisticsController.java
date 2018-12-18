package expenses.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import expenses.builders.ExpenseStatisticRequest;
import expenses.builders.ExpenseStatisticRequestBuilder;
import expenses.dao.ExpenseCategoryDao;
import expenses.dao.PersonDao;
import expenses.dao.StatisticsDao;
import expenses.model.ExpenseCategory;
import expenses.model.Person;
import expenses.model.statistics.CategoryExpense;
import expenses.model.statistics.GetExpensesStatisticForm;
import expenses.model.statistics.MonthExpense;
import expenses.model.statistics.PersonCategoriesExpenses;
import expenses.model.statistics.PersonMonthsExpenses;
import expenses.model.statistics.StatisticService;

@Controller
@RequestMapping(value="/statistics")
public class StatisticsController {

	@Autowired
	private StatisticsDao statisticDao;
	
	@Autowired
	private PersonDao personDao;
	
	@Autowired
	private ExpenseCategoryDao expenseCategoryDao;
	
	@Autowired
	private StatisticService statisticService;
	
	@RequestMapping(value="")
	public ModelAndView getStatistics(GetExpensesStatisticForm form, BindingResult bindingResult, Model uiModel) throws IOException, SQLException {
		
		List<Person> persons = personDao.getPersons();
		List<ExpenseCategory> categories = expenseCategoryDao.getExpenseCategories();
		
		uiModel.addAttribute("persons", persons);
		uiModel.addAttribute("categories", categories);
		
		ExpenseStatisticRequestBuilder requestBuilder = new ExpenseStatisticRequestBuilder();
		
		if(form.getPersonName() != null && !form.getPersonName().isEmpty()) {
			requestBuilder.setPersonName(form.getPersonName());
		} else {
			requestBuilder.setPersonName(persons.get(0).getName());
		}
		if(form.getDateFrom() != null || form.getDateTo() != null) {
			requestBuilder.setDateFrom(form.getDateFrom())
						  .setDateTo(form.getDateTo());
		}
		if(form.getCategories() != null && form.getCategories().length > 0) {
			requestBuilder.setCategoriesNames(Arrays.asList(form.getCategories()));
			uiModel.addAttribute("selectedCategories", Arrays.asList(form.getCategories()));
		}
		
		ExpenseStatisticRequest request = requestBuilder.build();
		
		PersonCategoriesExpenses expenses = statisticService.getPersonCategoriesExpenses(request);
		
		uiModel.addAttribute("personCategoriesExpenses", expenses);
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
		for(CategoryExpense categoryExpense : expenses.getCategoriesExpenses()) {
			dataset.setValue(categoryExpense.getCategoryName(), categoryExpense.getExpense());
			dataset2.setValue(categoryExpense.getExpense(), request.getPersonName(), categoryExpense.getCategoryName());
		}
		
		JFreeChart chart = ChartFactory.createPieChart("myChart", dataset, true, true, true);
		BufferedImage image = chart.createBufferedImage(500, 500);
		byte[] imageBytes = ChartUtilities.encodeAsPNG(image);
		String imageBase64 = Base64.encodeBase64String(imageBytes);
		uiModel.addAttribute("chart", imageBase64);
		
		JFreeChart chart2 = ChartFactory.createBarChart("Expenses",
												   "Category",
												   "Expense",
												   dataset2,
												   PlotOrientation.VERTICAL,
												   true,
												   true,
												   true);
		
		image = chart2.createBufferedImage(500, 500);
		imageBytes = ChartUtilities.encodeAsPNG(image);
		imageBase64 = Base64.encodeBase64String(imageBytes);
		uiModel.addAttribute("chart2", imageBase64);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("expensesStatistics");
		return modelAndView;
	}
	
	@RequestMapping(value="/personCategoriesExpenses", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getPersonCategoriesExpenses(String personName, Model uiModel) throws IOException, SQLException {
		
		ArrayList<Person> persons = (ArrayList<Person>) personDao.getPersons();
		uiModel.addAttribute("persons", persons);
		if(personName == null) {
			personName = persons.get(0).getName();
		}
		uiModel.addAttribute("selectedName", personName);
		PersonCategoriesExpenses personCategoriesExpenses = statisticDao.getPersonCategoriesExpenses(personName);
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
		for(CategoryExpense categoryExpense : personCategoriesExpenses.getCategoriesExpenses()) {
			dataset.setValue(categoryExpense.getCategoryName(), categoryExpense.getExpense());
			dataset2.setValue(categoryExpense.getExpense(), personName, categoryExpense.getCategoryName());
		}
		
		JFreeChart chart = ChartFactory.createPieChart("myChart", dataset, true, true, true);
		BufferedImage image = chart.createBufferedImage(500, 500);
		byte[] imageBytes = ChartUtilities.encodeAsPNG(image);
		String imageBase64 = Base64.encodeBase64String(imageBytes);
		uiModel.addAttribute("chart", imageBase64);
		
		JFreeChart chart2 = ChartFactory.createBarChart("Expenses",
												   "Category",
												   "Expense",
												   dataset2,
												   PlotOrientation.VERTICAL,
												   true,
												   true,
												   true);
		
		image = chart2.createBufferedImage(500, 500);
		imageBytes = ChartUtilities.encodeAsPNG(image);
		imageBase64 = Base64.encodeBase64String(imageBytes);
		uiModel.addAttribute("chart2", imageBase64);
		
		uiModel.addAttribute("personName", personName);
		uiModel.addAttribute("personCategoriesExpenses", personCategoriesExpenses);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("personCategoriesExpenses");
		return modelAndView;
	}
	
	/*@RequestMapping(value="/personCategoriesMonthExpenses")
	public ModelAndView getPersonCategoriesExpenses(String personName,
													@RequestParam(name="categoryName", required = false) String categoryName,
													Model uiModel) throws SQLException, IOException {
		ArrayList<Person> persons = (ArrayList<Person>) personDao.getPersons();
		uiModel.addAttribute("persons", persons);
		ArrayList<ExpenseCategory> categories = (ArrayList<ExpenseCategory>) expenseCategoryDao.getExpenseCategories();
		uiModel.addAttribute("categories", categories);
		if(personName == null && persons != null && !persons.isEmpty()) {
			personName = persons.get(0).getName();
		}
		uiModel.addAttribute("selectedName", personName);
		
		if(categoryName == null && categories != null && !categories.isEmpty())
			categoryName = categories.get(0).getName();
		uiModel.addAttribute("selectedCategory", categoryName);
		PersonMonthsExpenses personMonthsExpenses = statisticDao.getPersonCategoriesMonthExpenses(personName, categoryName);
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
		for(MonthExpense monthExpense : personMonthsExpenses.getMonthsExpenses()) {
			dataset.setValue(monthExpense.getMonth() + " " + monthExpense.getYear(), monthExpense.getExpense());
			dataset2.setValue(monthExpense.getExpense(), personName, monthExpense.getMonth() + " " + monthExpense.getYear());
		}
		
		JFreeChart chart = ChartFactory.createPieChart("myChart", dataset, true, true, true);
		BufferedImage image = chart.createBufferedImage(500, 500);
		byte[] imageBytes = ChartUtilities.encodeAsPNG(image);
		String imageBase64 = Base64.encodeBase64String(imageBytes);
		uiModel.addAttribute("chart", imageBase64);
		
		JFreeChart chart2 = ChartFactory.createBarChart("Expenses",
												   "Category",
												   "Expense",
												   dataset2,
												   PlotOrientation.VERTICAL,
												   true,
												   true,
												   true);
		
		image = chart2.createBufferedImage(500, 500);
		imageBytes = ChartUtilities.encodeAsPNG(image);
		imageBase64 = Base64.encodeBase64String(imageBytes);
		uiModel.addAttribute("chart2", imageBase64);
		
		uiModel.addAttribute("categoryName", categoryName);
		uiModel.addAttribute("personMonthsExpenses", personMonthsExpenses);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("personCategoriesMonthExpenses");
		return modelAndView;
	}*/
	
	/*@RequestMapping(value="/personMonthCategoriesExpenses")
	public ModelAndView getPersonMonthCategoriesExpenses(String personName,
													@RequestParam(name="month", required = false) Integer month,
													@RequestParam(name="year", required = false) Integer year,
													Model uiModel) throws SQLException, IOException {
		ArrayList<Person> persons = (ArrayList<Person>) personDao.getPersons();
		uiModel.addAttribute("persons", persons);
		if(personName == null) {
			personName = persons.get(0).getName();
		}
		uiModel.addAttribute("selectedName", personName);
		
		if(month == null)
			month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		if(year == null)
			year = Calendar.getInstance().get(Calendar.YEAR);
		uiModel.addAttribute("month", month);
		uiModel.addAttribute("year", year);
		PersonCategoriesExpenses personCategoriesExpenses = statisticDao.getPersonMonthCategoriesExpenses(personName, month, year);
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
		for(CategoryExpense categoryExpense : personCategoriesExpenses.getCategoriesExpenses()) {
			dataset.setValue(categoryExpense.getCategoryName(), categoryExpense.getExpense());
			dataset2.setValue(categoryExpense.getExpense(), personName, categoryExpense.getCategoryName());
		}
		
		JFreeChart chart = ChartFactory.createPieChart("myChart", dataset, true, true, true);
		BufferedImage image = chart.createBufferedImage(500, 500);
		byte[] imageBytes = ChartUtilities.encodeAsPNG(image);
		String imageBase64 = Base64.encodeBase64String(imageBytes);
		uiModel.addAttribute("chart", imageBase64);
		
		JFreeChart chart2 = ChartFactory.createBarChart("Expenses",
												   "Category",
												   "Expense",
												   dataset2,
												   PlotOrientation.VERTICAL,
												   true,
												   true,
												   true);
		
		image = chart2.createBufferedImage(500, 500);
		imageBytes = ChartUtilities.encodeAsPNG(image);
		imageBase64 = Base64.encodeBase64String(imageBytes);
		uiModel.addAttribute("chart2", imageBase64);
		
		uiModel.addAttribute("personName", personName);
		uiModel.addAttribute("personCategoriesExpenses", personCategoriesExpenses);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("personMonthCategoriesExpenses");
		return modelAndView;
	}*/
	
	@RequestMapping(value="/personMonthsExpenses", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getPersonMonthsExpenses(String personName, Model uiModel) throws IOException, SQLException {
		
		ArrayList<Person> persons = (ArrayList<Person>) personDao.getPersons();
		uiModel.addAttribute("persons", persons);
		if(personName == null) {
			personName = persons.get(0).getName();
		}
		uiModel.addAttribute("selectedName", personName);
		PersonMonthsExpenses personMonthsExpenses = statisticDao.getPersonMonthsExpenses(personName);
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
		for(MonthExpense monthExpense : personMonthsExpenses.getMonthsExpenses()) {
			dataset.setValue(monthExpense.getMonth() + "-" + monthExpense.getYear(), monthExpense.getExpense());
			dataset2.setValue(monthExpense.getExpense(), personName, monthExpense.getMonth() + "-" + monthExpense.getYear());
		}
		
		JFreeChart chart = ChartFactory.createPieChart("myChart", dataset, true, true, true);
		BufferedImage image = chart.createBufferedImage(500, 500);
		byte[] imageBytes = ChartUtilities.encodeAsPNG(image);
		String imageBase64 = Base64.encodeBase64String(imageBytes);
		uiModel.addAttribute("chart", imageBase64);
		
		JFreeChart chart2 = ChartFactory.createBarChart("Expenses",
												   "Category",
												   "Expense",
												   dataset2,
												   PlotOrientation.VERTICAL,
												   true,
												   true,
												   true);
		
		image = chart2.createBufferedImage(500, 500);
		imageBytes = ChartUtilities.encodeAsPNG(image);
		imageBase64 = Base64.encodeBase64String(imageBytes);
		uiModel.addAttribute("chart2", imageBase64);
		
		uiModel.addAttribute("personName", personName);
		uiModel.addAttribute("personMonthsExpenses", personMonthsExpenses);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("personMonthsExpenses");
		return modelAndView;
	}
	
}
