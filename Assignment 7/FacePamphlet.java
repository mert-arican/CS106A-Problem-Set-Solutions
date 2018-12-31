/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;

import acm.graphics.*;
import acm.util.*;
//1import acmx.export.java.util.ArrayList;

import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		// You fill this in
		database = new FacePamphletDatabase();
		getGUI(); addActionListeners(); getMouseListeners();
    }
    // delete friend if profile deleted.
	private void getGUI() {
		add(new JLabel("Name"),NORTH); nameTextField = new JTextField(TEXT_FIELD_SIZE);
		nameTextField.addActionListener(this); addButton = new JButton("Add");
		delete = new JButton("Delete"); lookup = new JButton("Lookup");
		statusTextField = new JTextField(TEXT_FIELD_SIZE); changeStatus = new JButton("Change Status");
		pictureTextField = new JTextField(TEXT_FIELD_SIZE); changePicture = new JButton("Change Picture");
		friendTextField = new JTextField(TEXT_FIELD_SIZE); addFriend = new JButton("Add Friend");
		add(nameTextField, NORTH); add(addButton,NORTH); add(delete,NORTH); add(lookup,NORTH);
		add(statusTextField, WEST); add(changeStatus,WEST); add((new JLabel(EMPTY_LABEL_TEXT)),WEST); add(pictureTextField,WEST); add(changePicture,WEST);
		add((new JLabel(EMPTY_LABEL_TEXT)),WEST); add(friendTextField, WEST); add(addFriend,WEST); add(addFriend,WEST); 
		canvas = new FacePamphletCanvas(); add(canvas);
	}
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		// You fill this in as well as add any additional methods
    	Object source = e.getSource();
    	if (source == addButton || source == nameTextField) {
    		println("Adddd");
    		addProfile(nameTextField.getText());
    	}
    	if (source == delete) {
    		deleteProfile(nameTextField.getText());
    	}
    	if (source == lookup) {
    		lookupProfile(nameTextField.getText());
    	}
    	if (source == changeStatus || source == statusTextField) {
    		changeStatus(statusTextField.getText());
    	}
    	if (source == changePicture || source == pictureTextField) {
    		changePicture(pictureTextField.getText());
    	}
    	if (source == addFriend || source == friendTextField) {
    		addFriend(friendTextField.getText());
    	}
	}
    
    private void addProfile(String name) {
    	if (!database.containsProfile(name)) {
        	profile = new FacePamphletProfile(name);
        	profile.setStatus("STATUSTATUSSTATUS");
        	canvas.displayProfile(profile);
        	database.addProfile(profile);
    	} else {
    		println("AlreadyExistingProfile");
    	}
    }
    
    private void deleteProfile(String name) {
    	if (database.containsProfile(name)) {
        	database.deleteProfile(name);
        	canvas.defaultCanvas();
        	profile = null;
    	} else {
    		println("NoProfileError");
    	}
    }
    
    private void lookupProfile(String name) {
    	if (database.containsProfile(name)) {
    		profile = database.getProfile(name);
    		canvas.displayProfile(profile);
    	} else {
    		println("NoProfileError");
    	}
    }
    
    private void changeStatus(String status) {
    	if (profile != null) {
    		profile.setStatus(status);
    		canvas.displayProfile(profile);
    	} else {
    		println("NoProfileSelectedError");
    	}
    }
    
    private void changePicture(String imageSource) {
    	if (profile != null) {
    		image = null;
            try {
                image = new GImage(imageSource);
            } catch (ErrorException ex) {
            	println("imageError");
            	return;
            }
        	profile.setImage(image);
        	canvas.displayProfile(profile);
    	}
    }
    
    private void addFriend(String name) {
    	if (profile != null) {
    		if (!profile.addFriend(name)) {
    			println("AlredyFriendError");
    		} else {
    			database.getProfile(name).addFriend(profile.getName());
    			canvas.displayProfile(profile);
    		}
    	}
    }
    
    
    JTextField nameTextField ;
    JButton addButton;
    JButton delete;
    JButton lookup;
    JTextField statusTextField;
    JButton changeStatus;
    JTextField pictureTextField;
    JButton changePicture;
    JTextField friendTextField;
    JButton addFriend;
    FacePamphletCanvas canvas;
    FacePamphletProfile profile;
    FacePamphletDatabase database;
    GImage image;
    
    
    
}
