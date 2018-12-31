import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** Width and height of application window in pixels */ 
	public static final int APPLICATION_WIDTH = 404; 
	public static final int APPLICATION_HEIGHT = 604;
	
	/** Dimensions of game board (usually the same) */ 
	private static final int WIDTH = APPLICATION_WIDTH;
	
	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60; 
	private static final int PADDLE_HEIGHT = 10;
	
	/** Offset of the paddle up from the bottom */ 
	private static final int PADDLE_Y_OFFSET = 30;
	
	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;
	
	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;
	
	/** Separation between bricks */
	private static final int BRICK_SEP = 4;
	
	/** Width of a brick */
	private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;
	
	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;
	
	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;
	
	/** Offset of the top brick row from the top */ 
	private static final int BRICK_Y_OFFSET = 70;
	
	/** Number of turns */
	private static int NTURNS = 3;
	
	/** Refreshing time */
	private static final int PAUSE_TIME = 9;
	
	/** Helping variable for designing bricks.  */
	private int rowCount = 0;
	
	/** Velocities for both x and y direction. */
	private double vx; private double vy = 3;
	
	/** RandomGenerator */
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	private static GOval ball;
	
	private static GRect paddle;
	
	AudioClip bounceClip;
	
	public static void main(String[] args) {
		new Breakout().start(args);
	}
    
    public void run() {
    	bounceClip = MediaTools.loadAudioClip("bounce.au");
    	getBricks(); getPaddle(); getBall(); getRandomVX(); gamePlay();
    }
    
    /** Returns random VX at the beginning of the game. */
	private void getRandomVX() {
		vx = rgen.nextDouble(1.0, 3.0); 
		if (rgen.nextBoolean(0.5)) vx = -vx;
	}
	
	private void getBricks() {
		for (int i = 0; i < NBRICK_ROWS; i++) {
			int y = BRICK_Y_OFFSET + (i * (BRICK_HEIGHT + BRICK_SEP));
			for (int j = 0; j < NBRICKS_PER_ROW; j++) {
				int x = ((j + 1) * BRICK_SEP) + (j * (BRICK_WIDTH));
				brickMaker(x,y);
			}
			rowCount++;
		}
	}
	
	private void brickMaker(int x, int y) {
		GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
		brick.setFilled(true); brick.setFillColor(getColor());
		add(brick);
	}
	
	private Color getColor() {
		if (rowCount == 0 || rowCount == 1) { return Color.red ;}
    	if (rowCount == 2 || rowCount == 3) { return Color.ORANGE ; }
    	if (rowCount == 4 || rowCount == 5) { return Color.yellow ; }
    	if (rowCount == 6 || rowCount == 7) { return Color.green ; }
    	if (rowCount == 8 || rowCount == 9) { return Color.cyan ; }
    	return null ;
	}
	
	private void getBall() {
		ball = new GOval((APPLICATION_WIDTH / 2) - BALL_RADIUS,
				APPLICATION_HEIGHT / 2 - BALL_RADIUS,
				BALL_RADIUS,BALL_RADIUS);
		ball.setFilled(true); ball.setFillColor(Color.black);
		add(ball);
	}
	
	private void getPaddle() {
    	paddle = new GRect((APPLICATION_WIDTH - PADDLE_WIDTH) / 2,(APPLICATION_HEIGHT - PADDLE_Y_OFFSET),PADDLE_WIDTH,PADDLE_HEIGHT);
    	paddle.setFilled(true); paddle.setFillColor(Color.BLACK);
    	add(paddle); addMouseListeners();
    }
	
	/** Checks for collision at every PAUSE_TIME. Updates the coordinates of the ball and returns the colliding object if there is. */
	private void gamePlay() {
    	while (true) {
    	    GObject collider = getCollidingObject();
    		collisionWithWall(); 
			if ((ball.getHeight() + ball.getY()) > APPLICATION_HEIGHT) { NTURNS -= 1; if (NTURNS > 0) { nextTurn(); } break ; }
    		if (collider != null) {
	    		if (collider == paddle) { vy = -vy; }
	    		else { remove(collider); vy = -vy; }
    		}
    		ball.setLocation(ball.getX() + vx, ball.getY() + vy);
       		pause(PAUSE_TIME);
    	}
    }
	
	private GObject getCollidingObject() {
		GObject collider = null;
		GObject[] corners = {
				getElementAt(ball.getX(),ball.getY()), getElementAt(ball.getX() + (2 * BALL_RADIUS), ball.getY()), 
				getElementAt(ball.getX(), ball.getY() + (2 * BALL_RADIUS)), getElementAt(ball.getX() + (2 * BALL_RADIUS), ball.getY() + (2 * BALL_RADIUS))
		};
		for (int i = 0; (corners[i] != null); i++) {
			collider = getElementAt(corners[i].getX(),corners[i].getY());
			if (i == 3) { break; }
		}
		return collider;
	}
	
	/** Changes the velocities if ball collided with one of the walls. */
	private void collisionWithWall() {
    	if ((ball.getX() + ball.getWidth()) > APPLICATION_WIDTH) { vx = -vx; }
    	if (ball.getX() < 0) { vx = -vx; }
    	if (ball.getY() < 0) { vy = - vy; }    		
    }
	
	/** Updates paddle's location. */
	public void mouseMoved(MouseEvent e) {
		if (e.getX() + PADDLE_WIDTH > WIDTH) {
			paddle.setLocation(WIDTH - PADDLE_WIDTH, paddle.getY());
		} else {
	    	paddle.setLocation(e.getX(), paddle.getY());
		}
    }
	
	/** Starts the game again */
	private void nextTurn() {
    	getRandomVX();
    	paddle.setLocation((APPLICATION_WIDTH - PADDLE_WIDTH) / 2, (APPLICATION_HEIGHT - PADDLE_Y_OFFSET));
    	ball.setLocation(APPLICATION_WIDTH / 2 - BALL_RADIUS, APPLICATION_HEIGHT / 2);
    	gamePlay();
    }
	
}
