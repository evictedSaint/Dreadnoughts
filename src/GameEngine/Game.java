package GameEngine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import ComponentClasses.Component;

public class Game {
	
	//making this a singleton
	protected static Game single_instance = null;
	
	public static String version = "v0.1.0";
	
	public static GameMode gameMode = new GameMode();  //TODO: I think this might be redundant? Singleton
	
	public static GameBoard gameBoard = new GameBoard(); //TODO: I think this might be redundant? Singleton
	
    
    //Do not modify this enum, it will break the Damage/Repair sections elsewhere.
    public enum Status
    {
        INTACT,
        DAMAGED,
        REPAIRING,
        JURY_RIGGED,
        RIGGING,
        DESTROYED,
        OBLITERATED
    }
    
    
    
    
    //Singleton stuff ========================
    //needs to be a singleton to save, since we have a bunch of static shit in this class.
    public static Game getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new Game(); 
  
        return single_instance; 
    } 
    
    protected Object readResolve() 
    { 
        return single_instance; 
    } 
    //end singleton stuff=======================
    
    
    
    public Game() {
    	
    }

    
}
