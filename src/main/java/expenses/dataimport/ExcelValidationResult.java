package expenses.dataimport;

public class ExcelValidationResult {
	private int correctExpenses;
	private int incorrectExpenses;
	
	public int getCorrectExpenses() {
		return correctExpenses;
	}
	public void setCorrectExpenses(int correctExpenses) {
		this.correctExpenses = correctExpenses;
	}
	public int getIncorrectExpenses() {
		return incorrectExpenses;
	}
	public void setIncorrectExpenses(int incorrectExpenses) {
		this.incorrectExpenses = incorrectExpenses;
	}
}
