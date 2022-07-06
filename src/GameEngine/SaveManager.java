package GameEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class SaveManager {
	
	
	
	/* What all do we need to have the save manager handle?
	 * 
	 * 
	 * We need a repository of game files, which is the "overall" save.  This holds distances, repair time, etc, and points to the additional saves which make up this save.
	 * We need a repository of gamemodes, which contain component classes.
	 * We need a repository of ship objects, which contain their sections and shipcomponents and whatnot.
	 * 
	 */

	private static String saveString = System.getProperty("user.dir") + "\\src\\Saves"; //the save directory
	
	//Game Files 
	//FIXME: this method is obsolete!
	public static boolean saveGameFile(Game game, String gameName) {
		checkSaveDirectories();
		checkSavedGameDirectory(gameName);
		GsonBuilder builder = new GsonBuilder(); 
		Gson gson = builder.setPrettyPrinting().create(); 
		
		FileWriter writer;
		
		//TOOD: put the close and open outside the try so it's always closed if the try fails.
		try {
			//making a saveslot.  If one already exists, we make a new one with the append "_n+1" and start fresh.
			File turnSaveSlot = new File(saveString + "\\SavedGames\\" + gameName + "\\turn_" + GameBoard.turn + ".json");
			//this "if" ensures the save name is unique
			if (turnSaveSlot.exists() && !turnSaveSlot.isDirectory()) {
				boolean saveSlotFound = false;
				int version = 1;
				while (!saveSlotFound) {
					turnSaveSlot = new File(saveString + "\\SavedGames\\" + gameName + "\\turn_" + GameBoard.turn + "_" + version + ".json");
					if (turnSaveSlot.exists() && !turnSaveSlot.isDirectory()) {
						version++;
					}
					else {
						saveSlotFound = true;
					}
				}
			}
			//now to actually write the file.
			writer = new FileWriter(turnSaveSlot);
			//prepareGameForSave(game);
			writer.write(gson.toJson(game));   
			writer.close(); 
			//
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}   
			   
		return true;
	}
	
	
	
	public boolean loadGameFile(String gameName, String saveName) {
		//gameName is the subfolder under SavedGames, which is all of the same game.
		//saveName is the specific gson save in that subfolder - this lets you load "earlier" saves, in case of errors.
		GsonBuilder builder = new GsonBuilder(); 
		Gson gson = builder.create(); 
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("student.json"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}   
		  
		//Student student = gson.fromJson(bufferedReader, Student.class); 
		
		return true;
	}
	
	

	public static void clearVariablesForSave() {
		for (Ship s : GameBoard.ships) {
			s.report = "";
		}
	}
	
	/**
	 * called prior to saving anything
	 */
	public static void checkSaveDirectories() {
		//checking to make sure the save directory (and subfolders) exists...
		File saveDirectory = new File(saveString);
		if (!saveDirectory.exists() || !saveDirectory.isDirectory()) { 
			new File(saveString).mkdir();
		}
		File gameModeDir = new File(saveString + "\\GameModes");
		if (!gameModeDir.exists() || !gameModeDir.isDirectory()) {
			new File(saveString + "\\GameModes").mkdir();
		}
		File savedGamesDir = new File(saveString + "\\SavedGames");
		if (!savedGamesDir.exists() || !savedGamesDir.isDirectory()) {
			new File(saveString + "\\SavedGames").mkdir();
		}
	}
	
	/** 
	 * Called prior to saving a game
	 * @param gameName
	 */
	public static void checkSavedGameDirectory(String gameName) {
		//checking to see if the saved game directories we need exist...
		File gameNameDirectory = new File(saveString + "\\SavedGames\\" + gameName);
		if (!gameNameDirectory.exists() || !gameNameDirectory.isDirectory()) { //if the file directory for this gameName doesn't exist (and checking to make sure we're dealing with a directory)
			//expect to do this on the very first initial save
			new File(saveString + "\\SavedGames\\" + gameName).mkdir();
		} 
	}
	
	
	
	//GameModes
	public static boolean saveGameMode(String gameModeName) {
		GsonBuilder builder = new GsonBuilder(); 
		Gson gson = builder.setPrettyPrinting().create(); 
		
		FileWriter writer;
		
		//TODO: put the close and open outside the try so it's always closed if the try fails.
		try {
			//making a gameMode.  
			File gameModeSave = new File(saveString + "\\GameModes\\" + gameModeName + ".json");
			//now to actually write the file.
			writer = new FileWriter(gameModeSave);
			//prepareGameForSave(game);
			gson.toJson(GameMode.getInstance(), writer);   
			writer.close(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}   
			   
		return true;
	}
	
	public static boolean loadGameMode(String gameModeName) {
		GsonBuilder builder = new GsonBuilder(); 
		Gson gson = builder.setPrettyPrinting().create(); 
		try {

			System.out.println("Reading JSON from a file");
			System.out.println("----------------------------");

			BufferedReader br = new BufferedReader(new FileReader(saveString + "\\GameModes\\" + gameModeName + ".json"));

			// convert the json string back to object
			GameMode gm = gson.fromJson(br, GameMode.class);
			GameMode.getInstance().setSelf(gm);


		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}
	
	public static boolean doesGameModeExist(String gameModeName) {
		File gameModeSave = new File(saveString + "\\GameModes\\" + gameModeName + ".json");
		//this "if" ensures the save name is unique
		if (gameModeSave.exists() && !gameModeSave.isDirectory()) {
			return true;
		}
		return false;
	}
	
	//Ships
	public boolean saveShip(Ship ship) {
		
		return true;
	}
	
	public boolean loadShip(String shipName) {
		
		return true;
	}
}
