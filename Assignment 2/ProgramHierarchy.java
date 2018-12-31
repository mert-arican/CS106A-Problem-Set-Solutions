import acm.graphics.*;
import acm.program.*;

public class ProgramHierarchy extends GraphicsProgram {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static int width = 100;
	
	private static int height = 40;
	
	public void run() {
		int startY = (getHeight() - (2 * height + 40)) / 2;
		int startX = (getWidth() - (2 * width - 100)) / 2;
		GRect programRect = new GRect(startX,startY,width,height);
		GRect gProgramRect = new GRect(startX - 20 - width,startY + 40 + height,width,height);
		GRect cProgramRect = new GRect(startX ,startY + 40 + height,width,height);
		GRect dProgramRect = new GRect(startX + 20 + width ,startY + 40 + height ,width,height);
		add(programRect);
		add(gProgramRect);
		add(cProgramRect);
		add(dProgramRect);
		GLabel programLabel = new GLabel("Program");
		programLabel.setLocation(startX  + ((width - programLabel.getWidth()) / 2), startY+height - ((height - programLabel.getHeight())/2));
		add(programLabel);
		GLabel gProgramLabel = new GLabel("GraphicsProgram");
		gProgramLabel.setLocation(startX - 20 - width + ((width - gProgramLabel.getWidth()) / 2), startY+40 + height +height - ((height - gProgramLabel.getHeight())/2));
		add(gProgramLabel);
		GLabel cProgramLabel = new GLabel("ConsoleProgram");
		cProgramLabel.setLocation(startX + ((width - cProgramLabel.getWidth()) / 2), startY+40 + height + height - ((height - cProgramLabel.getHeight())/2));
		add(cProgramLabel);
		GLabel dProgramLabel = new GLabel("DialogProgram");
		dProgramLabel.setLocation(startX + 20 + width + ((width - dProgramLabel.getWidth()) / 2), startY+40 + height + height - ((height - dProgramLabel.getHeight())/2));
		add(dProgramLabel);
	}

	
}
