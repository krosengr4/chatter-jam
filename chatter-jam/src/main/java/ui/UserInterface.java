package ui;

import utils.Utils;

public class UserInterface {

	public int displayHomeScreen() {
		System.out.println("""
							_________________MAIN MENU_________________
				
						---OPTIONS---
				1 - Go to posts screen
				2 - Create a New Post
				0 - Exit
				""");

		return Utils.getUserInputIntMinMax("Enter here:\n", 0, 2);
	}

	public int displayPostScreen() {
		System.out.println("""
					_________________POSTS SCREEN_________________
		
				---OPTIONS---
		1 - See all posts
		2 - See all posts from a user
		3 - See post by id (here you can leave a comment)
		0 - Go back
		""");

		return Utils.getUserInputIntMinMax("Enter here:\n", 0, 3);
	}

	public int displaySinglePost() {
		System.out.println("""
		_____________________________________________________________
				---OPTIONS---
		1 - See comments
		2 - Like
		3 - Dislike
		0 - Go back
		""");

		return Utils.getUserInputIntMinMax("Enter here:\n", 0, 3);
	}
}
