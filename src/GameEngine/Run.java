package GameEngine;

import java.awt.EventQueue;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;

import ComponentClasses.DefenseBank;
import ComponentClasses.DefenseRolling;
import ComponentClasses.DefenseStatic;
import ComponentClasses.PowerSupply;
import ComponentClasses.Weapon;
import GameEngine.Game.Status;
import UserInterface.Gui;

public class Run 
{

	static Gui window;
	
	public static void main(String[] args) 
	{
		Game game = new Game();
		//launch the gui
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Gui();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
		
		
		
		System.out.println("Hello World!");
		
		
		
		//initialize Game, which does some stuff with distances for later reference
		
		/*
		Game game = new Game();


		Ship red = new Ship("Red", "Kashyyk", "RDS");
		Ship blu = new Ship("USNC Blu", "evictedSaint", "BLS");
		
		game.ships.add(red);
		game.ships.add(blu);
		
		MapNode engagement = new MapNode(0);

		engagement.shipsHere.add(red);
		engagement.shipsHere.add(blu);
		
		MapManager.trunk.children.add(engagement);

		//--------------
		Weapon laser = new Weapon(null);
		Weapon gun = new Weapon(null);
		Weapon missile = new Weapon(null);
		
		
		laser.componentName = "Super Laser";
		laser.powerRequirement = 2;
		laser.weaponType = WeaponType.KINETIC;
		laser.hitChance.get(Status.INTACT).put(0, 4);
		
		//--------------
		DefenseRolling pointDefense = new DefenseRolling(null);
		DefenseBank shield = new DefenseBank(null);
		DefenseStatic armor = new DefenseStatic(null);
		
		pointDefense.componentName = "Point Defense";
		
		pointDefense.projectileTypeRoll.get(Status.INTACT).put(WeaponType.MISSILE, 3);
		pointDefense.projectileTypeRoll.get(Status.INTACT).put(WeaponType.KINETIC, 3);

		pointDefense.projectileTypeDamage.get(Status.INTACT).put(WeaponType.MISSILE, 2);
		pointDefense.projectileTypeDamage.get(Status.INTACT).put(WeaponType.KINETIC, 1);
		
		pointDefense.stackingMalus = -2;
		
		pointDefense.shutsDownOnSuccess = false;
		
		pointDefense.powerRequirement = 2;
		
		//-------------
		PowerSupply powerSupply = new PowerSupply(null);
		powerSupply.componentName = "Power Generator";
		powerSupply.powerSupply.put(Status.INTACT, 2);
		
		Section s1 = new Section();
		s1.external = new ShipComponent(pointDefense);
		s1.internal = new ShipComponent(powerSupply);

		Section s2 = new Section();
		s2.internal = new ShipComponent(powerSupply);

		Section s3 = new Section();
		s3.external = new ShipComponent(pointDefense);

		Section s4 = new Section();
		s4.external = new ShipComponent(pointDefense);
		s4.internal = new ShipComponent(powerSupply);

		red.sections.add(s1);
		red.sections.add(s2);
		red.sections.add(s3);
		red.sections.add(s4);
		
		
		//making map nodes

		
		
		//public Projectile(Ship sender, Weapon parent, int health, int turnsUntilArrival, int toHitChance, int toHitMod, int overKill) {
		for (int i = 0; i < 10; i++) {
			red.incoming.add(new Projectile(laser, 2, 0, 7, 1, 2));
		}
		
		//red shooting at blu		
		Turn.hitsAndDefense();
		
		
		//red.incoming
		
		System.out.println(red.displayShip());
		
		SaveManager.saveGameFile(game, "test");
		System.out.println("saved");
		
		/*
		//making the ships
		Ship a = new Ship("A", "", "");
		Ship b = new Ship("B", "", "");
		Ship c = new Ship("C", "", "");
		Ship d = new Ship("D", "", "");
		Ship e = new Ship("E", "", "");
		Ship f = new Ship("F", "", "");
		Ship g = new Ship("G", "", "");
		Ship h = new Ship("H", "", "");
		Ship i = new Ship("I", "", "");
		Ship j = new Ship("J", "", "");
		

		//making the nodes
		MapNode na = new MapNode(5);
		MapNode nb = new MapNode(2);
		MapNode nc = new MapNode(1);
		MapNode nd = new MapNode(3);
		MapNode ne = new MapNode(4);
		MapNode nf = new MapNode(3);
		MapNode ng = new MapNode(2);

		
		//placing ships...
		na.shipsHere.add(a);
		na.shipsHere.add(b);
		
		nb.shipsHere.add(c);
		
		nc.shipsHere.add(d);
		nc.shipsHere.add(e);
		
		nd.shipsHere.add(f);
		nd.shipsHere.add(g);
		
		ne.shipsHere.add(h);
		
		ng.shipsHere.add(j);
		
		MapManager.trunk.shipsHere.add(i);
		
		//building the node tree...
		na.children.add(nb);
		na.children.add(nd);
		nb.children.add(nc);
		ne.children.add(nf);
		ne.children.add(ng);
		
		MapManager.trunk.children.add(na); //"trunk" is the highest-possible node, which is parent to all smaller nodes.  
		MapManager.trunk.children.add(ne);
		
		//outputting the map
		System.out.println("\n======\n\n");
		System.out.println("Cleaning...");
		MapManager.cleanMap();
		System.out.println("\n======\n\n");
		MapManager.displayMap();
		System.out.println("\n======\n\n");

		System.out.println("Moving...");
		MapManager.moveShip(e, f, 0, true);

		System.out.println("\n======\n\n");
		System.out.println("Cleaning...");
		MapManager.cleanMap();
		System.out.println("\n======\n\n");
		MapManager.displayMap();
		System.out.println("\n======\n\n");
		*/
	}
	
}
