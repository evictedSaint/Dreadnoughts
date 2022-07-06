package UserInterface;

import java.util.HashMap;

public class BankDefenseValues {
	
	/**
	 * BankDefenseValues holds each Bank Defense Type and tracks the damage it does to certain Weapon Types.
	 * Higher values means it damages that projectile more (e.g. higher values = better defense)
	 * 
	 * 0 = Weapon Type ignores this Bank Defense.
	 * 
	 * Yeah this is kind of a lazy way to track this but it makes it a LOT easier to manage
	 */
	
	String name;
	
	HashMap<String, Integer> weaponTypesDamage = new HashMap<String, Integer>(); //how much damage this bank defense does to the incoming projectile (higher = better defense)
	
	public BankDefenseValues(String name) {
		this.name = name;		
	}
	
	

}
