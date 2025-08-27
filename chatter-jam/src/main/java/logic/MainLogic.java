package logic;

import config.DatabaseConfiguration;
import data.PostDao;
import data.mysql.MySqlPostDao;
import models.Post;
import ui.UserInterface;
import utils.Utils;

import java.time.LocalDate;

public class MainLogic {

	static UserInterface ui = new UserInterface();
	static PostDao postDao = new MySqlPostDao(DatabaseConfiguration.setDataSource());

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
		String title = Utils.getUserInput("Enter the title:\n");
		String content = Utils.getUserInput("Write your post:\n");
		String author = Utils.getUserInput("Enter your name:");
		LocalDate date = LocalDate.now();

		Post post = postDao.create(new Post(0, title, content, author, date));

		if(post != null) {
			post.printData();
		}
	}

}
