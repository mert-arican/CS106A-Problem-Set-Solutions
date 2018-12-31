import java.awt.Color;
import acm.program.*;
import acm.graphics.*;

public class Target extends GraphicsProgram {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int centerX;
	private int centerY;
	private int outerRadius = 36;
	private int middleRadius = 72 * 65 / 200;
	private int innerRadius = 72 * 3 / 20;
	
	public void run() {
		centerX = getWidth() / 2;
		centerY = getHeight() / 2;
		getOuterCircle();
		getMiddleCircle();
		getInnerCircle();
	}

	private void getOuterCircle() {
		GOval outerCircle = new GOval(centerX - outerRadius, centerY - outerRadius, 2 * outerRadius, 2 * outerRadius);
		outerCircle.setFilled(true); outerCircle.setFillColor(Color.red); add(outerCircle);
	}
	
	private void getMiddleCircle() {
		GOval middleCircle = new GOval(centerX - middleRadius, centerY - middleRadius, 2 * middleRadius, 2 * middleRadius);
		middleCircle.setFilled(true); middleCircle.setFillColor(Color.white); add(middleCircle);
	}
	
	private void getInnerCircle() {
		GOval innerCircle = new GOval(centerX - innerRadius, centerY - innerRadius, 2 * innerRadius, 2 * innerRadius);
		innerCircle.setFilled(true); innerCircle.setFillColor(Color.red); add(innerCircle);
	}
	
	
}
