import acm.program.*;

public class Hangman extends ConsoleProgram {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HangmanCanvas canvas;
	
	private HangmanLexicon lexicon;
	
	private static String wordOnScreen = "";
	
	private String answer = "MERTARICAN";
	
	private int numberOfGuessesLeft = 8;
	
	public void init() {
        canvas = new HangmanCanvas();
        canvas.drawScaffold();
        add(canvas);
        lexicon = new HangmanLexicon();
        answer = lexicon.wordList.get(0);
        for (int i = 0; i < answer.length(); i++) {
        	wordOnScreen += "-";
        }
		canvas.displayWord(wordOnScreen);
	}
	
	public void run() {
		println("Welcome to Hangman!");
		println("The word now looks like this: " + wordOnScreen);
		println("You have " + numberOfGuessesLeft + " guesses left.");
		while (true) {
			String guess = readLine("Your guess: " );
			gameplayInteraction(guess);
			if (result() || numberOfGuessesLeft == 0) { break; }
		}
	}
	
	private void gameplayInteraction(String guess) {
		if (isAppropiate(guess)) {
			feedback(guess);
		} else {
			println("You guess is not valid!");
		}
	}

	private boolean isAppropiate(String ch) {
		if (ch.length() != 1) { return false; }
		char letter = ch.charAt(0);
		return Character.isLetter(letter);		
	}
		
	private void feedback(String ch) {
		String newWord = "";
		if (isCorrectGuess(ch.charAt(0))) {
			for (int i = 0; i < answer.length(); i++) {
				if ( ch.charAt(0) == answer.charAt(i) || Character.toUpperCase(ch.charAt(0)) == answer.charAt(i) ) {
					newWord += Character.toUpperCase(ch.charAt(0));
				} else {
					newWord += wordOnScreen.charAt(i);
				}
			}
			wordOnScreen = newWord;
			println("The guess is correct.");
			if (result()) { println("You guessed the word: " + answer); println("You win."); } else {
				println("The word now looks like this: " + wordOnScreen);
				println("You have " + numberOfGuessesLeft + " guesses left.");  
			}
			canvas.displayWord(wordOnScreen);
		} else {
			numberOfGuessesLeft -= 1;
			canvas.noteIncorrectGuess(ch.charAt(0));
			println("The guess is wrong.");
			if (numberOfGuessesLeft == 0 && !result()) { println("The word was: " + answer); println("You lose."); } else {
				println("The word now looks like this: " + wordOnScreen);
				println("You have " + numberOfGuessesLeft + " guesses left."); 
			}
		}
	}
	
	private boolean result() {
		if (wordOnScreen.equals(answer)) { return true; }
		return false;
	}
	
	private boolean isCorrectGuess(char ch) {
		for (int i = 0; i < answer.length(); i++) {
			if (ch == answer.charAt(i)) { return true; }
			if (Character.toUpperCase(ch) == answer.charAt(i)) { return true; }
		}
		return false;
	}
	
}
