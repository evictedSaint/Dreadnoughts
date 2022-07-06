package GameEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ComponentClasses.Defense;
import ComponentClasses.DefenseRolling;

public class Turn {

	HashMap<String, HashMap<String, List<List<String>>>> commands = new HashMap<String, HashMap<String, List<List<String>>>>(); //ship, commandtype, commands, command components
	
	/*
	 * 
	 * I'd allow some degree of aiming at Sections, each weapon would have a Heavy version that shot 
	 * less but penetrated defences,and there be an additional base cost per Sector when designing a
	 *  ship. Finally I wanted to make it possible to hit Internals before Extendable.  Each of those 
	 *  should help counter the "bigger is better than more" problem
	 * 
	 * 
It is as follows:
1. Weapons fire 
2. DamCon, Jumps tick, jumps complete
3. Weapon hit rolls
4. Countermeasures (PD, Shields, then Armor, blocking preferred projectile first)
5. Weapon damage
6. Check DC, and Jump completion criteria
7. Reset all ShipComponent miscCounters
8. Weapon turns to hit ticks down 1
Thus, a just-completed jump or repair attempt could get disrupted by enemy fire
	 */
	
	public void runTurn() {
		//first thing we do is clear out commands
		commands = new HashMap<String, HashMap<String, List<List<String>>>>(); //ship, commandtype, commands, command components
		
		//TODO: get commandBlock from ui
		String commandBlock = "";
		
		//parse the commands
		commands = CommandParser.parseCommands(commandBlock);
		
		
	}
	
	
	
	public void weaponsFire() {
		
	}
	
	public void damageControlInitializeAndtick() {
		
	}
	
	public void jumpInitializeAndTick() {
		
	}
	
	public static void hitsAndDefense() {
		/* So, each ship keeps track of the projectiles fired at it.
		 * 
		 * Currently, defenses are measured in three tiers:
		 * 1) Die roll defenses
		 * 2) Banked defenses
		 * 3) Static defenses
		 * 
		 * We process countermeasures by ship, by tier, by defense preference. 
		 */
		
		String counterMeasuresReport = "[spoiler=Action Report]";
		
		for (Ship s : GameBoard.ships) {
			
			
			if (!s.isAlive) { //we don't process damage for dead ships
				continue;
			}
			
			counterMeasuresReport += "\n[b]" + s.faction + " " + s.shipName + " (" + s.captain + ")[/b]\n";
			
			// hits, defense, etc.
			counterMeasuresReport += s.projectilesHit() + "\n[hr]\n";
		}
		counterMeasuresReport += "[/spoiler]";
		
		System.out.println(counterMeasuresReport);
		
	}
	
	
	public void projectileTick() {
		
	}
	
	public void damageControlResolve() {
		
	}
	
	public void jumpResolve() {
		
	}
}
