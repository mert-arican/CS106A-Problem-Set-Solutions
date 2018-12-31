import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void run() {
		println("Enter values to compute Pythagorean Theorem");
		int firstInt = readInt("a: ");
		int secondInt = readInt("b: ");
		double hypotenus = Math.sqrt(firstInt * firstInt + secondInt * secondInt);
		println("c: " + hypotenus);
	}
	
}
