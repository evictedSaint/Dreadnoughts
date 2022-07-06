package ComponentClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import GameEngine.Game;
import GameEngine.Game.Status;
import GameEngine.GameMode;
import GameEngine.Projectile;




public class Defense extends Component {
	//defenses are a bit more complicated than I expected...
		
	public HashMap<Status, HashMap<String, Integer>> projectileTypeDamage = new HashMap<Status, HashMap<String, Integer>>(); //weapon type, damage done to that projectile. Defenses prioritized by max damage. 0 means it doesn't even try.
		
	public Defense(String componentName) {
		super(componentName);
		
		HashMap<String, Integer> tempHashMap = new HashMap<String, Integer>();
		for (String wt : GameMode.getInstance().weaponTypes) {
			tempHashMap.put(wt, 0);
		}
		for (Status s : Status.values()) {
			HashMap<String, Integer> tempHashMap2 = new HashMap<String, Integer>();
			tempHashMap2.putAll(tempHashMap);
			projectileTypeDamage.put(s, tempHashMap2);
		}

	}
	
	
}

//TODO: tons of deadcode here.

	/*
	HashMap<WeaponType, Integer> projectileTypeRoll = new HashMap<WeaponType, Integer>(); //weapon type, roll required 
	HashMap<WeaponType, Integer> projectileTypeDamage = new HashMap<WeaponType, Integer>(); //weapon type, damage done to that projectile. Defenses prioritized by max damage. 0 means it doesn't even try.
	
	boolean globalDefense = true; //does this defend the whole ship, or just that section?
	boolean sleepsAfterUse = true; //does this only defend once per turn?
	
	int repeatDefenseModifier = 0; //for rolling defenses, after successful blocks. Positive means defense gets better as time goes on, negative means worse.
	
	int bankSize = 0;
	int refresh = 0;
	
	boolean displayBank = false;
	
	public Defense(String componentName, Location location) {
		super(componentName, location);
		types.add(Type.DEFENSE);
	}
	*/

	
	/*
	 * 


PD rolls, destroying missiles in 1 hit, kinetics in 2.  Rolling defense, cumulative -2 penalty. 

Shields have an int, and a refresh rate.  Lasers reduce by 1, missiles by 2.  

Armor protects the section.  Refreshes.  Stops 2 kinetic hits or 1 laser.


fuck, okay, so...maybe...uh. hm.

> Defenses have:
type, roll required to stop
type, damage done to projectile
global
totalbank
refreshperturn
rollingdefense
	 */
	
