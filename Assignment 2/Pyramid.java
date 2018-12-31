import acm.program.*;
import acm.graphics.*;

public class Pyramid extends GraphicsProgram {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int BRICK_WIDTH = 30;
	private static final int BRICK_HEIGHT = 12;
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
		for (int i = BRICKS_IN_BASE; i > 0; i--) {
			int y = getHeight() - ((BRICKS_IN_BASE - i) * BRICK_HEIGHT) - BRICK_HEIGHT;
			int startingPoint = (getWidth() - (i * BRICK_WIDTH)) / 2;
			for (int j = i; j > 0; j--) {
				int x = (startingPoint + ((i - j)) * BRICK_WIDTH) ;
				GRect stone = new GRect(x,y,BRICK_WIDTH,BRICK_HEIGHT);
				add(stone);
			}
		}
	}
	
	
}