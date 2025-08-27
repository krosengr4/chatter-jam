package logic;

import ui.UserInterface;

public class PostLogic {

	static UserInterface ui = new UserInterface();

	public static void processPostScreen() {
		boolean ifContinue = true;

		while(ifContinue) {
			int userChoice = ui.displayPostScreen();

			switch(userChoice) {
				case 1 -> showAll();
				case 2 -> showAllFromUser();
				case 3 -> showOne();
				case 0 -> ifContinue = false;
			}
		}
	}

	private static void showAll() {
		System.out.println("All posts");
	}

	private static void showAllFromUser() {
		System.out.println("All from a certain user");
	}

	public static void showOne() {
		System.out.println("Show one post");
	}

}

