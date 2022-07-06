package ComponentClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import GameEngine.Game;

public class Component {
	
	
	public String componentName;
	
	//resource costs
	public int commons = 0;
	public int energetics = 0;
	public int xotics = 0;
	
	public List<String> owningFactions = new ArrayList<String>(); //which factions have access to this component
	
	//stats
	public int powerRequirement = 0;
	
	public boolean canBeDamaged = true; //means this component is a viable target when dealing damage. TODO: this isn't implemented in the code (low priority, probably)
	public boolean repairable = true;  //TODO: not used?
	
	public boolean augmentSlot = false; //TODO: not used, i think?
	
	//outdated implimentation,each component type is now a class instead of an enum. TODO: remove this dead code.
	/*public enum Type {
		GENERIC,
		POWERSUPPLY,
		PASSIVEDEFENSE,
		WEAPON,
		DEFENSE,
		JUMPDRIVE,
		AUGMENT
	}*/
	
	
	public Component(String componentName) {
		this.componentName = componentName;
	}

}

