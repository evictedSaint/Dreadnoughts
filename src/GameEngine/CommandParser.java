package GameEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CommandParser {

	public static List<String> parseCommand(String inputRaw) {
		List<String> output = new ArrayList<String>();
		
		//TODO: might not be strictly necessary; i think regex has a way to make case irrelevant.  eitherway, kinda lazy.
		String input = inputRaw.replaceAll("[^A-Za-z0-9\\s]", " ").toLowerCase(); 	//all lowercase, only alphanumeric characters, spaces
		input = input.replaceAll("\\s{2,}", " ").trim(); 							//this makes sure there are no double spaces

		if (regComp(" |", input)) { //if it's a whitespace or empty, return null
			return null;
		}
		
		//kinda lazy, could do more regex stuff, but for now I'll make the players do their commands precisely.
		//TODO: see if i can get fuzzy string working at some point.  Make this "elegant" or w/e.
		String[] splitInput = input.split(" ");
		if (splitInput.length <= 1) {
			return null;
		}
		
		switch (splitInput[0]) {
			case "order":  //order shipname
				output.add("order");
				output.add(String.join(" ", Arrays.copyOfRange(splitInput, 1, splitInput.length))); //in case the name is multi-word
				break;
			case "attack": //attack shipname
				output.add("attack");
				output.add(String.join(" ", Arrays.copyOfRange(splitInput, 1, splitInput.length))); //in case the name is multi-word
				
				break;
			case "power": //power on/off # interior/exterior
				output.add("power");
				output.add(splitInput[1]);
				output.add(splitInput[2]);
				output.add(splitInput[3]);
				break;
			case "damcon": //damcon on/off # interior/exterior
				output.add("damcon");
				output.add(splitInput[1]);
				output.add(splitInput[2]);
				output.add(splitInput[3]);
				break; 
			case "move":  //move towards/away shipname  //TODO: enable selective distance during move. 'move towards/away distance shipname'
				output.add("move");
				output.add(splitInput[1]);
				output.add(String.join(" ", Arrays.copyOfRange(splitInput, 2, splitInput.length))); //in case the name is multi-word
				break;
			default:
				output.add("Error");
				output.add("Command Failed To Parse: ");
				output.add("not a recognized command");
				output.add(input);
		}
		
		return output;
	}
	
	
	public static HashMap<String, HashMap<String, List<List<String>>>> parseCommands(String commandBlock) {
		/* Possible commands:
		 * 
		 * 1) attack - target.
		 * 2) turn on/off component via section.
		 * 3) turn on/off damcon via section.
		 * 4) move towards/away from target.
		 * 
		 * 0) Order - shipname
		 * 1) Attack - shipname
		 * 2) Power - on/off - section - component
		 * 3) Damcon - on/off - section - component
		 * 4) Move - towards/away - shipname  //TODO: try setting this up as "move - distance - towards/away - shipname"
		 *
		 * 
		 */
		
		HashMap<String, HashMap<String, List<List<String>>>> commands = new HashMap<String, HashMap<String, List<List<String>>>>(); //ship, commandtype, commands, command components
		List<List<String>> outputBlock = new ArrayList<List<String>>();
		List<String> output = new ArrayList<String>();
		
		BufferedReader bufReaderInput = new BufferedReader(new StringReader(commandBlock));
		Scanner commandCorrection = new Scanner(System.in);
		String newInput = "";
		String line = "";
		
		try {
			while((line = bufReaderInput.readLine()) != null) {
				output = parseCommand(line);
				if (output != null) {  //ignoring blank lines in order inputs. 
					while ((output.size() != 0) && (output.get(0).equals("Error"))) {
						System.out.println(output.get(1) + output.get(2) + "\n" + output.get(3));
						System.out.println("Please input correct command (q to quit): ");
						newInput = commandCorrection.nextLine();
						if (newInput.equals("q")) {output = new ArrayList<String>();}
						else {output = parseCommand(newInput);}
					}	
					
					outputBlock.add(output);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String currentFaction = "";
		
		for (List<String> command : outputBlock) {
			if (command.size() < 1) {
				continue;
			}
			if (command.get(0).equals("order")) {
				currentFaction = command.get(1);
				
				if (commands.containsKey(currentFaction)) {
					System.out.println("!!!WARNING!!! Faction '" + currentFaction + "' has already entered their orders for this turn - continuing will append these orders to what "
							+ "they have thusfar.  Select 'n' to overwrite with these new orders instead.  \nContinue? [y/n]");
					newInput = "";
					while (newInput.length() < 1) {
						newInput = commandCorrection.nextLine();
						if (newInput.equals("y")) {
							break;
						}
						else if (newInput.equals("n")) {
							commands.put(currentFaction, new HashMap<String, List<List<String>>>());
							break;
						}
						else {newInput = "";}
					}
				} else {					
					commands.put(currentFaction, new HashMap<String, List<List<String>>>());
				}
			} else {
				//commands = ship, commandtype, commands, command components (hash, hash, array, array, string)
				if (commands.containsKey(currentFaction)) { //make sure there's a ship in charge; if not, we'll skip for now TODO: make this more robust so you can't sabotage other peoples orders.
					if (!commands.get(currentFaction).containsKey(command.get(0))) { //if this command type hasn't been issued, we'll add it here.
						commands.get(currentFaction).put(command.get(0), new ArrayList<List<String>>());
					}
					List<String> tempList = new ArrayList<String>();
					for (int i = 1; i <= command.size(); i++) {
						tempList.add(command.get(i));
					}
					commands.get(currentFaction).get(command.get(0)).add(tempList);
				}
			}
		}

		commandCorrection.close();
		return commands;	
	}
	
	
	
	//Because pattern matches are referenced so frequently, this is just a way to keep that command short and clean.
	public static boolean regComp(String regex, String input) {
		if (Pattern.matches(regex, input)) {
			return true;
		}
		return false;
	}
}
