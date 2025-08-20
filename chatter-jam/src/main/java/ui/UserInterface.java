package ui;

import utils.Utils;

public class UserInterface {

	public int displayHomeScreen() {
		System.out.println("""
							_________________MAIN MENU_________________
				
						---OPTIONS---
				1 - View All Stories
				2 - Create a New Story
				3 - Admin
				0 - Exit
				""");

		return Utils.getUserInputIntMinMax("Enter here:\n", 0, 5);
	}

}
