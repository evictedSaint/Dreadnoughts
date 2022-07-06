package GameEngine;

import ComponentClasses.Weapon;

public class Projectile {
	
	/*
	 * tracks shots sent, target location, their sender, their type, turn order they were sent.
	 * Projectiles are tracked by the ship they're intended for, so projectile doesn't need to track that themselves. 
	 */

	String sourceShip;
	
	MapNode location; //targetting location //FIXME: i think this is obsolete?

	Weapon parent;
	
	//these values change 
	int health;
	int turnsUntilArrival;
	
	//these values dont change - yes, these COULD be queried from the parent, but the parent's status might have changed between when the shot was 
	//sent and when it arrived.  Thus, these values are stored here.
	int toHitChance; 
	int toHitMod; //negative less likely, positive more likely
	int overKill; //this one is stored here in case overkill becomes status-dependent. /shrug
	
	public Projectile(String sourceShip, Weapon parent, int health, int turnsUntilArrival, int toHitChance, int toHitMod, int overKill) {
		
		this.sourceShip = sourceShip;
		this.parent = parent;
		
		this.health = health;
		this.turnsUntilArrival = turnsUntilArrival;
		this.toHitChance = toHitChance;
		this.toHitMod = toHitMod;
		this.overKill = overKill;
	}
}
