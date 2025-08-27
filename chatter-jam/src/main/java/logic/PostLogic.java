package logic;

import config.DatabaseConfiguration;
import data.PostDao;
import data.mysql.MySqlPostDao;
import models.Post;
import ui.UserInterface;
import utils.Utils;

import java.util.List;

public class PostLogic {

	static UserInterface ui = new UserInterface();
	static PostDao postDao = new MySqlPostDao(DatabaseConfiguration.setDataSource());

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
		List<Post> postList = postDao.getAll();

		if(postList.isEmpty()) {
			System.out.println("There are no posts to be displayed at this time...");
		} else {
			for(Post post : postList) {
				post.printData();
			}
		}

		Utils.pauseApp();
	}

	private static void showAllFromUser() {
		System.out.println("All from a certain user");
	}

	private static void showOne() {
		System.out.println("Show one post");
	}

}

