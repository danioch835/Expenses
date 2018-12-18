package expenses.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExpensesUtils {
	public static String getDateAsString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	public static Date getDateFromString(String dateAsString) throws ParseException {
		String dateFormat = "";
		String datePattern1 = "[0-9]{2}-[0-9]{2}-[0-9]{4}";
		String datePattern2 = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
		if(dateAsString.matches(datePattern1)) {
			dateFormat = "dd-MM-yyyy";
		} else if(dateAsString.matches(datePattern2)) {
			dateFormat = "yyyy-MM-dd";
		}
		if(!dateFormat.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			return sdf.parse(dateAsString);
		}
		return null;
	}
}
