/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import java.util.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
		
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		initializeScoresArray(); playGame();
	}

	private void initializeScoresArray( ) {
		scores = new int[nPlayers][17];
	}
	
	private void saveScore(int player, int category, int point) {
		scores[player-1][category-1] = point;
	}
	
	private void playGame() {
		/* You fill this in */
		if (turnCount == 13) { endOfTheGame(); } else {
		display.printMessage(playerNames[playerCount - 1] + "'s turn! Click \"Roll Dice\" button to roll the dice.");
		display.waitForPlayerToClickRoll(playerCount); rollDice();
		while (nthRoll < 3 && isRollContinue) {
			display.printMessage("Select the dice you wish to re-roll and click \"Roll Again\".");
			display.waitForPlayerToSelectDice();
			isRollContinue = false; nthRoll += 1;
			for (int index = 0; index < 5; index++) {
				if (display.isDieSelected(index)) {
					diceList[index] = rgen.nextInt(1, 6);
					display.displayDice(diceList);
					isRollContinue = true;
				}
			}
		}
		display.printMessage("Select a category for this roll.");
		int categoryIndex = display.waitForPlayerToSelectCategory(); 
		String playerNCategory = playerNames[playerCount - 1] + categoryIndex;
		while (chosenCategories.contains(playerNCategory)) {
			display.printMessage("You've already selected that category. Please try another one.");
			categoryIndex = display.waitForPlayerToSelectCategory();
			playerNCategory = playerNames[playerCount - 1] + categoryIndex;
		}
		if (!chosenCategories.contains(playerNCategory)) {
			chosenCategories.add(playerNCategory);
		}
		int gainedPoint = checkCategory(categoryIndex);
		saveScore(playerCount,categoryIndex,gainedPoint);
		display.updateScorecard(categoryIndex, playerCount, gainedPoint);
		nthRoll = 0 ; isRollContinue = true; nextPlayer();  playGame();
		}
	}
	
	private void endOfTheGame() {
		for (int player = 1; player < nPlayers+1; player++) {
			int upperScore = upperScoreNBonus(player)[0] ;
			int upperBonus = upperScoreNBonus(player)[1] ;
			int lowerScore = lowerScore(player);
			int totalScore = upperScore + upperBonus + lowerScore;
			display.updateScorecard(7, player, upperScore);
			display.updateScorecard(8, player, upperBonus);
			display.updateScorecard(16, player, lowerScore);
			display.updateScorecard(17, player, totalScore);
			scores[player - 1][16] = totalScore ;
			if (player == nPlayers) {
				display.printMessage("Congratulations, " + getWinner() + ", you're the winner with a total score of "+ highScore + "!" ); 
			}
		}
	}
	
	private int[] upperScoreNBonus(int player) {
		int upperScore = 0; int upperBonus = 0;
		for (int i = 0; i<7; i++) {
			upperScore += scores[player - 1][i];
		}
		if (upperScore >= 63) { upperBonus = 35; }
		return new int[] { upperScore,upperBonus };
	}
	
	private int lowerScore(int player) {
		int lowerScore = 0; 
		for (int i = 8; i < 16; i++) {
			lowerScore += scores[player - 1][i];
		}
		return lowerScore;
	}
	
	private void nextPlayer() {
		playerCount += 1;  if (playerCount == nPlayers + 1) { playerCount = 1; turnCount += 1; }
	}
	
	private String getWinner() {
		for (int player = 0; player < nPlayers; player++) {
			if (scores[player][16] > highScore) { highScore = scores[player][16]; personHoldsHighScore = player ; }
		}
		return playerNames[personHoldsHighScore] ;
	}
	
	private int checkCategory(int category) {
		if (category <= 6) { return ones(category); }
		if (category == 9) { return xOfAKind(3); }
		if (category == 10) { return xOfAKind(4); }
		if (category == 11) { return fullHouse(); }
		if (category == 12) { return xStraight(determineSmallestNumber(),4); }
		if (category == 13) { return xStraight(determineSmallestNumber(),5); }
		if (category == 14) { return yahtzee(); }
		if (category == 15) { return chance(); }
		return 0;
	}
	
	private int ones(int number) {
		int numberOfNumber = 0;
		for (int i = 0; i< diceList.length; i++) {
			if (diceList[i] == number) { numberOfNumber += 1; }
		}
		return number * numberOfNumber;
	}
	
	private int xOfAKind(int x) {
		for (int i = 0; i<5; i++) {
			int numb = diceList[i];
			int numberOfSameElement = 0;
			for (int j = 0; j<5;j++) {
				if (numb == diceList[j]) {
					numberOfSameElement += 1;
				}	
			}
			if (numberOfSameElement >= x) { 
				return sumOfAll();
			}
		}
		return 0;
	}
	
	private int fullHouse() {
		ArrayList<Integer> list = new ArrayList<Integer>(); int howMany = 0;
		for (int element: diceList) { if (!list.contains(element)) { list.add(element); } }
		for (int i = 0; i < 5; i++) { if (diceList[i] == diceList[0]) { howMany += 1; } }
		if (list.size() == 2 && (howMany == 3 || howMany == 2)) {
			return 25;
		}
		return 0;
	}
	
	private int determineSmallestNumber() {
		int smallestNumber = 10;
		for (int element: diceList) {
			if (element < smallestNumber) { smallestNumber = element; }
		}
		return smallestNumber;
	}
	
	private int xStraight(int smallestNumb,int type) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int element: diceList) { list.add(element);}
		for (int i = smallestNumb; i < smallestNumb + type; i++) {
			if (!list.contains(i)) { return 0; }
		}
		if (type == 4) { return 25; }
		return 30;
	}
	
	private int yahtzee() {
		int numb = diceList[0];
		for (int dice: diceList) { if (dice != numb) { return 0; } }
		return 50;
	}
	
	private int chance() { return sumOfAll(); }
		
	private int sumOfAll() {
		int sum = 0;
		for (int dice: diceList) { sum += dice; }
		return sum;
	}
	
	private void rollDice() {
		diceList = new int[5];
		for (int i = 0; i < 5; i++) {
			diceList[i] = rgen.nextInt(1, 5);
		}
		display.displayDice(diceList); 
	}
	
/* Private instance variables */
	private int[][] scores;
	private int highScore = 0;
	private int personHoldsHighScore = 0;
	private int nthRoll = 1;
	private int playerCount = 1;
	private int turnCount = 1;
	private int nPlayers;
	private boolean isRollContinue = true;
	private int[] diceList;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	private ArrayList<String> chosenCategories = new ArrayList<String>();
	
}
