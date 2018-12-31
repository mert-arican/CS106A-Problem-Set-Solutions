import acm.program.*;

public class FindRange extends ConsoleProgram {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int SMALLEST_NUMBER = 0;
	
	private int BIGGEST_NUMBER = 0;
	
	private int numberOfIntsTypedIn = 0;
	
	public void run() {
		println("This program finds the smallest and largest numbers.");
		while(true) {
			int userInt = getIntFromUser();
			numberOfIntsTypedIn += 1;
			if (userInt == 0) { result(); break; }
			if (numberOfIntsTypedIn > 1) { 
				SMALLEST_NUMBER = Integer.min(SMALLEST_NUMBER, userInt);
				BIGGEST_NUMBER = Integer.max(BIGGEST_NUMBER, userInt);
			} else {
				SMALLEST_NUMBER = userInt; BIGGEST_NUMBER = userInt;
			}
		}
	}
	
	private int getIntFromUser() {
		return readInt("?: ");
	}
	
	private void result() {
		if (numberOfIntsTypedIn == 1 && SMALLEST_NUMBER == 0) {
			println("Error!") ;
		} else {
			println("Smallest: " + SMALLEST_NUMBER);
			println("Biggest: " + BIGGEST_NUMBER);
		}
	}
	
}
