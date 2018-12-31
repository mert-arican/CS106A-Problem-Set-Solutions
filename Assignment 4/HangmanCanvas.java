/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */
import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/** Resets the display so that only the scaffold appears */ 
	public void reset() {
      removeAll(); drawScaffold(); guessNumber = 0;
   }
/**
* Updates the word on the screen to correspond to the current
* state of the game. The argument string shows what letters have
* been guessed so far; unguessed letters are indicated by hyphens. */
   public void displayWord(String word) {
	  remove(answerLabel);
      answerLabel = new GLabel(word,20,500);
      add(answerLabel);
}
   
   private GLabel answerLabel = new GLabel("");
   
   private GLabel wrongGuessLabel = new GLabel("",20,540);

   private String wrongGuesses = "";
   
   private int guessNumber = 0;
/**
* Updates the display to correspond to an incorrect guess by the
* user. Calling this method causes the next body part to appear
* on the scaffold and adds the letter to the list of incorrect
* guesses that appears at the bottom of the window. */
public void noteIncorrectGuess(char letter) { /* You fill this in */
	if (guessNumber == 0) { drawHead(); }
	if (guessNumber == 1) { drawBody(); }
	if (guessNumber == 2) { drawArm("left"); }
	if (guessNumber == 3) { drawArm("right"); }
	if (guessNumber == 4) { drawLeg("left"); }
	if (guessNumber == 5) { drawLeg("right"); }
	if (guessNumber == 6) { drawFoot("left"); }
	if (guessNumber == 7) { drawFoot("right"); }
	guessNumber += 1; wrongGuesses += letter;
	remove(wrongGuessLabel); wrongGuessLabel.setLabel(wrongGuesses); add(wrongGuessLabel);
}

int x = 50; int y = 40;

public void drawScaffold() {
	GLine scaffold = new GLine(x,y,x,y + SCAFFOLD_HEIGHT);
	add(scaffold);
	GLine beam = new GLine(x, y,x + BEAM_LENGTH,y);
	add(beam);
	GLine rope = new GLine(x + BEAM_LENGTH,y,x + BEAM_LENGTH,y + 18);
	add(rope);
}

private void drawHead() {
	GOval head = new GOval(x + BEAM_LENGTH - HEAD_RADIUS,y + ROPE_LENGTH,HEAD_RADIUS * 2,HEAD_RADIUS * 2);
	add(head);
}

private void drawBody() {
	GLine body = new GLine(x + BEAM_LENGTH,y + ROPE_LENGTH + HEAD_RADIUS * 2,x + BEAM_LENGTH,y + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH);
	GLine hip = new GLine(x + BEAM_LENGTH - (HIP_WIDTH / 2),y + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH,x + BEAM_LENGTH + (HIP_WIDTH / 2),y + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH);
	add(body); add(hip);
}

private void drawArm(String direction) {
	if (direction.equals("left")) {
		GLine leftArm = new GLine(x + BEAM_LENGTH,y + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD,x + BEAM_LENGTH - UPPER_ARM_LENGTH,y + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD);
		GLine leftLowerArm = new GLine(x + BEAM_LENGTH - UPPER_ARM_LENGTH,y + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD ,x + BEAM_LENGTH - UPPER_ARM_LENGTH,y + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		add(leftArm); add(leftLowerArm);
	}
	if (direction.equals("right")) {
		GLine rightArm = new GLine(x + BEAM_LENGTH,y + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD,x + BEAM_LENGTH + UPPER_ARM_LENGTH,y + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD);
		GLine rightLowerArm = new GLine(x + BEAM_LENGTH + UPPER_ARM_LENGTH,y + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD,x + BEAM_LENGTH + UPPER_ARM_LENGTH,y + ROPE_LENGTH + HEAD_RADIUS * 2 + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
		add(rightArm); add(rightLowerArm);
	}
}

private void drawLeg(String direction) {
	if (direction.equals("left")) {
		GLine leftLeg = new GLine(x + BEAM_LENGTH - (HIP_WIDTH / 2),y + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH,x + BEAM_LENGTH - (HIP_WIDTH / 2),y + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH);
		add(leftLeg);
	}
	if (direction.equals("right")) {
		GLine rightLeg = new GLine(x + BEAM_LENGTH + (HIP_WIDTH / 2),y + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH,x + BEAM_LENGTH + (HIP_WIDTH / 2),y + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH);
		add(rightLeg); 
	}
}

private void drawFoot(String direction) {
	if (direction.equals("left")) {
		GLine leftLeg = new GLine(x + BEAM_LENGTH - (HIP_WIDTH / 2),y + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH,x + BEAM_LENGTH - (HIP_WIDTH / 2) - FOOT_LENGTH,y + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH);
		add(leftLeg);
	}
	if (direction.equals("right")) {
		GLine rightLeg = new GLine(x + BEAM_LENGTH + (HIP_WIDTH / 2),y + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH,x + BEAM_LENGTH + (HIP_WIDTH / 2) + FOOT_LENGTH,y + ROPE_LENGTH + HEAD_RADIUS * 2 + BODY_LENGTH + LEG_LENGTH);
		add(rightLeg); 
	}
}

/* Constants for the simple version of the picture (in pixels) */
private static final int SCAFFOLD_HEIGHT = 360;
private static final int BEAM_LENGTH = 144;
private static final int ROPE_LENGTH = 18;
private static final int HEAD_RADIUS = 36;
private static final int BODY_LENGTH = 144;
private static final int ARM_OFFSET_FROM_HEAD = 28;
private static final int UPPER_ARM_LENGTH = 72;
private static final int LOWER_ARM_LENGTH = 44;
private static final int HIP_WIDTH = 36;
private static final int LEG_LENGTH = 108;
private static final int FOOT_LENGTH = 28;

}

