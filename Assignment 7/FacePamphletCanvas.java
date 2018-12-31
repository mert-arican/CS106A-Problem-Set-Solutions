/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;


//import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		// You fill this in
		defaultCanvas();
	}
	
	public void defaultCanvas() {
		removeAll();
		nameLabel = new GLabel("",LEFT_MARGIN,TOP_MARGIN); add(nameLabel);
		statusLabel = new GLabel("",LEFT_MARGIN,(3*TOP_MARGIN)+200); add(statusLabel);
		friendsLabel = new GLabel("", APPLICATION_WIDTH/2 ,((TOP_MARGIN * 2) + nameLabel.getHeight())); add(friendsLabel);
	}
	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		// You fill this in
	}
	
	private void printFriends(Iterator<String> iterator) {
		ArrayList<String> list = new ArrayList<String>();
		if (iterator != null) {
			int i = 1;
			while (iterator.hasNext()) {
				String deneme = iterator.next();
				add(new GLabel(deneme,friendsLabel.getX(),friendsLabel.getY()+(i * friendsLabel.getHeight())));
				i += 1; list.add(deneme);
			}
		}
	}
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		// You fill this in
		defaultCanvas();
		this.image = profile.getImage();
		nameLabel.setLabel(profile.getName()); statusLabel.setLabel(profile.getStatus()); friendsLabel.setLabel("Friends:"); printFriends(profile.getFriends());
		if (image != null) { image.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);  add(image,LEFT_MARGIN,((TOP_MARGIN * 2))); } else {
			add(new GRect(LEFT_MARGIN,((TOP_MARGIN * 2)),200,200)); add(new GLabel("No Image", LEFT_MARGIN + 70,(TOP_MARGIN*2)+100));
		}
	}
	
    GLabel nameLabel;
    GLabel statusLabel;
    GLabel friendsLabel;
    GImage image;
}
