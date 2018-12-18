package expenses.utils;

import expenses.model.Expense;

public class ExpenseValidator {
	public static boolean checkIfExpenseIsValid(Expense expense) {
		return expense.getData() != null;
	}
}
