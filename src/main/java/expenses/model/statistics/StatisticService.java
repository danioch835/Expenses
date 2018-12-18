package expenses.model.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import expenses.builders.ExpenseStatisticRequest;
import expenses.dao.StatisticsDao;

@Service
public class StatisticService {

	@Autowired
	StatisticsDao statisticDao;
	
	public PersonCategoriesExpenses getPersonCategoriesExpenses(ExpenseStatisticRequest request) {
		if(request.getCategoriesNames() != null && !request.getCategoriesNames().isEmpty()) {
			if(request.getFromDate() != null || request.getToDate() != null) {
				return statisticDao.getPersonSelectedCategoriesExpenses(request.getPersonName(),
																request.getCategoriesNames(),
																request.getFromDate(),
																request.getToDate());
			}return statisticDao.getPersonSelectedCategoriesExpenses(request.getPersonName(), 
																	request.getCategoriesNames());
		}
		if(request.getFromDate() != null || request.getToDate() != null) {
			return statisticDao.getPersonCategoriesExpenses(request.getPersonName(),
															request.getFromDate(),
															request.getToDate());
		}
		return statisticDao.getPersonCategoriesExpenses(request.getPersonName());
	}
	
	public PersonMonthsExpenses getPersonMonthsExpenses(ExpenseStatisticRequest request) {
		/*if(request.getCategoriesNames() != null && !request.getCategoriesNames().isEmpty()) {
			return statisticDao.getPersonSelectedCategoriesExpenses(request.getPersonName(), 
																	request.getCategoriesNames());
		}*/
		return statisticDao.getPersonMonthsExpenses(request.getPersonName());
	}
	
}
