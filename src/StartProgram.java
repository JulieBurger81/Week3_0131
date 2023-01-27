import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import controller.GamesHelper;
import model.Games;

/**
 * @author Julie Burger - jaburger
 * CIS175 - Spring 2023
 * Jan 21, 2023
 */

public class StartProgram {
	static Scanner in = new Scanner(System.in);
	static GamesHelper gameHelper = new GamesHelper();
	/**
	 * @param 
	 */
	public static Games searchForGame() {
		int idSelected = 0;
		boolean found = false; 
		
		while (!found) {
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by name");
			System.out.println("2 : Search by type");
			System.out.println("3 : Search by max number of players");
			int searchBy = in.nextInt();
			in.nextLine(); // what's this for again?
			
			List<Games> foundGames;
			if (searchBy == 1) {
				System.out.print("Enter the name of the game: ");
				String name = in.nextLine();
				foundGames = gameHelper.searchGamesByName(name);
			} else if (searchBy == 2) {
				System.out.print("Enter the type of game: ");
				String type = in.nextLine();
				foundGames = gameHelper.searchGamesByType(type);
			} else if (searchBy == 3) {
				System.out.print("Enter the max number of players in game: ");
				int numOfPlayers = in.nextInt();
				foundGames = gameHelper.searchGamesbyNumber(numOfPlayers);
			} else {
				System.out.println("Please select optinons 1, 2, or 3.");
				foundGames = null;
			}
			
			if (foundGames == null) {  // invalid option selected, so repeat prior section
				System.out.println(); 
			} else if (!foundGames.isEmpty()) {  // result(s) found, specify ID
				System.out.println("Found Results: ");
				for (Games game : foundGames) {
					System.out.println(game.getId() + ": " + game.returnGameDetails());
				}
				System.out.print("Enter the ID in which to select: ");
				idSelected = in.nextInt();
				found = true;
			} else {	//no result(s) found, filter needs to be redone.
				System.out.println(" ---- Sorry no games found. Please try again.");
			}
		}
		
		Games gameSelected = gameHelper.searchGameByID(idSelected);
		return gameSelected;
	}
		
	/**
	 * 
	 */
	private static void addGame() {
		System.out.print("Enter the game's name: ");
		String name = in.nextLine();
		System.out.print("Enter the type of game: ");
		String type = in.nextLine();
		System.out.print("Enter number of players: ");
		int numOfPlayers = in.nextInt();
		Games toAdd = new Games(name, type, numOfPlayers);
		gameHelper.insertGame(toAdd);
		System.out.println("Game has been added.");
		System.out.println();
	}
	/**
	 * 
	 */
	private static void editGame() {
		Games gameToEdit = searchForGame();	
		boolean edit = false; 
		
		System.out.println("Thanks for clarifying.  Enter the field to edit: ");
		while (!edit) {
			System.out.println("1 : Update Name");
			System.out.println("2 : Update Type");
			System.out.println("3 : Update Max Number of Players");
			int update = in.nextInt();
			in.nextLine();
				
			if (update == 1) {
				System.out.print("New Name: ");
				String newName = in.nextLine();
				gameToEdit.setName(newName);
				edit = true;
			} else if (update == 2) {
				System.out.print("New Type: ");
				String newType = in.nextLine();
				gameToEdit.setType(newType);
				edit = true;
			} else if (update == 3) {
				System.out.print("New Max Number of Players: ");
				int newNumOfPlayers = in.nextInt();
				gameToEdit.setNumOfPlayers(newNumOfPlayers);
				edit = true;
			} else {
				System.out.println("Please select optinons 1, 2, or 3.");
			}
		}
		
			gameHelper.updateItem(gameToEdit);
			System.out.println("Game has been updated.");
			System.out.println();

	}
	/**
	 * 
	 */
	private static void deleteGame() {
		Games gameToDelete = searchForGame();	
		
		gameHelper.deleteGame(gameToDelete);
		System.out.println("Game has been deleted.");
		System.out.println();
	}
	/**
	 * 
	 */
	private static void viewTheList() {
		List<Games> games = gameHelper.showAllGames();
		for(Games game : games) {
			System.out.println(game.returnGameDetails());
		}
	}
	/**
	 * @param 
	 */
	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- Welcome to the Games Menu ---");
		
		while (goAgain) {
			System.out.println(" *Select an item:");
			System.out.println(" *1 -- Add a game");
			System.out.println(" *2 -- Edit a game");
			System.out.println(" *3 -- Delete a game");
			System.out.println(" *4 -- View the list");
			System.out.println(" *5 -- Exit the awesome program");
			System.out.print(" Your selection: ");
			if(in.hasNextInt()) {
				int selection = in.nextInt();
				in.nextLine();
				
				if (selection == 1) {
					addGame();
				} else if (selection == 2) {
					editGame();
				} else if (selection == 3) {
					deleteGame();
				} else if (selection == 4) {
					viewTheList();
				} else {
					gameHelper.cleanUp();
					System.out.println("--- Thanks for playing. BYE!! ---");
					goAgain = false;
				}
			} else {
				gameHelper.cleanUp();
				System.out.println("--- Thanks for playing. BYE!! ---");
				goAgain = false;
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		runMenu();
	}	
	
}
