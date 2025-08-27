package logic;

import ui.UserInterface;

public class MainLogic {

	static UserInterface ui = new UserInterface();

	public static void processHomeScreen() {
		boolean ifContinue = true;

		while(ifContinue) {
			int userChoice = ui.displayHomeScreen();

			switch(userChoice) {
				case 1 -> PostLogic.processPostScreen();
				case 2 -> createPost();
				case 0 -> ifContinue = false;
			}
		}
	}

	private static void createPost() {
		System.out.println("Create a post");
	}

}
