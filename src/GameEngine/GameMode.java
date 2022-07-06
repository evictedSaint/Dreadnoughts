package GameEngine;

import java.util.HashMap;
import java.util.HashSet;

import ComponentClasses.Component;

public class GameMode {


	//making this a singleton
	protected static GameMode single_instance = null;
	
	public HashSet<String> weaponTypes;

    public HashMap<String, HashMap<String, Integer>> bankDefenseProjectileTypeDamage = new HashMap<String, HashMap<String, Integer>>(); //bankname, weapontype, damage done (higher value = better defense)

    public HashMap<Integer, String> distances = new HashMap<Integer, String>(); //starting at 0, closest.
    
    public HashMap<String, Component> components = new HashMap<String, Component>();

	public HashMap<String, Faction> factions = new HashMap<String, Faction>();
	
	public GameMode() {
		
	}
	
    //Singleton stuff ========================
    //needs to be a singleton to save, since we have a bunch of static shit in this class.
    public static GameMode getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new GameMode(); 
  
        return single_instance; 
    } 
    
    public static void setSelf(GameMode gm) {
    	single_instance = gm;
    }
    
    protected Object readResolve() 
    { 
        return single_instance; 
    } 
    //end singleton stuff=======================
	
}
