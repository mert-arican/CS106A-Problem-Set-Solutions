import acm.program.*;

public class Hailstone extends ConsoleProgram {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int numberOfSteps = 0;
	
	public void run() {
		int number = readInt("Enter a number: ");
		while (true) {
			numberOfSteps += 1;
			int numberBeforeChange = number;
			if ((number % 2) == 1) {
				number = (number * 3) + 1;
				println(numberBeforeChange + " is odd, so I make 3n + 1: " + number);
			} else {
				number = number / 2;
				println(numberBeforeChange + " is even so I take half: + number");
			}
			if (number == 1) {
				println("The process took " + numberOfSteps + " to reach 1."); break;
			}
		}
	}
}
