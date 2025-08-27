import logic.MainLogic;

public class Main {

	public static void main(String[] args) {
		System.out.println("""


										WELCOME TO CHATTER JAMS!!!
				____________________________________________________________________________
					Share your experiences, stories, and ideas and interact with others!
				____________________________________________________________________________
				
				""");

		MainLogic.processHomeScreen();
		System.out.println("Goodbye!");
	}

}
