package ComponentClasses;

import java.util.HashMap;

import GameEngine.Game;
import GameEngine.Game.Status;
import GameEngine.GameMode;

public class Weapon extends Component {
	
	
	

	//TODO: need to eventually work in some sort of per-turn fire rate

	public String weaponType;
	
	public int overkill = 0; //amount above required roll for additional damage, cumulative.  0 (or lower) is no overkill.
	
	public int projectileHealth = 2; //amount of "health" the shot has, to be stopped by the defenses.
	
	//NOTE: ships can only fire at a certain range if they have BOTH speed and hitchance for that range.
	public HashMap<Status, HashMap<Integer, Integer>> speed = new HashMap<Status, HashMap<Integer, Integer>>();  //range, turns to reach
	public HashMap<Status, HashMap<Integer, Integer>> hitChance = new HashMap<Status, HashMap<Integer, Integer>>();  //range, die roll required (this number or higher to hit)
		
	public HashMap<Status, Integer> projectilesFired = new HashMap<Status, Integer>(); //amount of projectiles this gun fires per salvo
	
	public Weapon(String componentName) {
		super(componentName);		

		HashMap<Integer, Integer> tempHashMap = new HashMap<Integer, Integer>();
		for (Integer i : GameMode.getInstance().distances.keySet()) {
			tempHashMap.put(i, null);
		}
		for (Status s : Status.values()) {
			HashMap<Integer, Integer> tempHashMap1 = new HashMap<Integer, Integer>();
			tempHashMap1.putAll(tempHashMap);
			HashMap<Integer, Integer> tempHashMap2 = new HashMap<Integer, Integer>();
			tempHashMap2.putAll(tempHashMap);
			speed.put(s, tempHashMap1);
			hitChance.put(s, tempHashMap2);
			
			projectilesFired.put(s, 1);
		}
	}
	
	/*
	 * 
weapons:
have a TYPE
have a SPEED (distance vs time to reach - higher is slower, base 0)
have a TO HIT chance (based on range)
have an EFFECTIVE RANGE
have a TARGET (ship at node) (in projectile class)
have a DAMAGE (usually 1)
have an OVERKILL (bonus damage based on roll)
	 */

}
