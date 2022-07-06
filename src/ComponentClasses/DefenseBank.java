package ComponentClasses;

import java.util.HashMap;

import GameEngine.Game.Status;

public class DefenseBank extends Defense {

	public String defenseResource = ""; //e.g. "shields"
		
	public HashMap<Status, Integer>  maxValue = new HashMap<Status, Integer>();
	
	public HashMap<Status, Integer> perTurnRecharge = new HashMap<Status, Integer>();
	
	/** Like shields
	 */
	public DefenseBank(String componentName) {
		super(componentName);
		
		projectileTypeDamage = null; //projectileTypeDamage is tracked by Game for bank-type defenses
		
		for (Status s : Status.values()) {
			maxValue.put(s, 0);
			perTurnRecharge.put(s, 0);
		}
	}
}
