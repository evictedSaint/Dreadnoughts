package ComponentClasses;

import java.util.HashMap;

import GameEngine.Game;
import GameEngine.Game.Status;
import GameEngine.GameMode;

public class DefenseRolling extends Defense {

	public HashMap<Status, HashMap<String, Integer>> projectileTypeRoll = new HashMap<Status, HashMap<String, Integer>>(); //weapon type, roll required 
	
	public boolean shutsDownOnSuccess = true; //i.e. can this defense be used multiple times in a turn.	
	public int stackingMalus = 0; //penalty to each following defense attempt.  Positive=more effective, negative=less effective.
		
	/** Like Point Defense.
	 */
	public DefenseRolling(String componentName) {
		super(componentName);

		HashMap<String, Integer> tempHashMap = new HashMap<String, Integer>();
		for (String wt : GameMode.getInstance().weaponTypes) {
			tempHashMap.put(wt, 0);
		}
		for (Status s : Status.values()) {
			HashMap<String, Integer> tempHashMap1 = new HashMap<String, Integer>();
			tempHashMap1.putAll(tempHashMap);
			projectileTypeRoll.put(s, tempHashMap1);
		}
	}
}
