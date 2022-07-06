package GameEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;

import ComponentClasses.Augment;
import ComponentClasses.Component;
import ComponentClasses.Defense;
import ComponentClasses.DefenseBank;
import ComponentClasses.DefenseRolling;
import ComponentClasses.DefenseStatic;
import ComponentClasses.PassiveDefense;
import ComponentClasses.PowerSupply;
import ComponentClasses.Weapon;
import GameEngine.Game.Status;

public class Ship {
	
	
	String shipName;
	String captain;
	String faction;

	Random random = new Random();
	
	boolean isAlive = true;
	
	List<Section> sections = new ArrayList<Section>();
	
	List<Projectile> incoming = new ArrayList<Projectile>(); //bullets with their name on it
	
	HashMap<String, Integer> bankDefenses = new HashMap<String, Integer>(); //this is the ships current defense amount for bank defenses (e.g. shields)
	
	String report = "";

	public Ship(String shipName, String captain, String faction) {
		this.shipName = shipName;
		this.captain = captain;
		this.faction = faction;
	}
	
	//TODO: shipcomponents need to destroy their augments when destroyed.
	//FIXME: Ship is doing too much here.  Needs to simply be a data class, rather than having all these methods. Gotta move this up a level into "Turn".
	
	public String displayShip() {
		//yeah, I know, this is super lazy. Sue me. TODO: move this up a level with most of the methods here.
		HashMap<Status, String> color = new HashMap<Status, String>();
		color.put(Status.DAMAGED, "[color=#bbb000]");
		color.put(Status.DESTROYED, "[color=#ff0000]");
		color.put(Status.INTACT, "[color=#000000]");
		color.put(Status.JURY_RIGGED, "[color=#bbb000]");
		color.put(Status.OBLITERATED, "[color=#ababab]");
		color.put(Status.REPAIRING, "[color=#bbb000]");
		color.put(Status.RIGGING, "[color=#ff0000]");
		
		//Alright, now to display the ships status
		String shipStatus = "[spoiler=" + faction + " " + shipName + " - " + captain + "]\n";
		
		for (String bankD : bankDefenses.keySet()) {
			shipStatus += bankD + ": " + bankDefenses.get(bankD) + "\n";
		}
		
		shipStatus += "Power Balance: " + calculatePower() + "\n";
		
		//TODO: jumping indicator goes here
		
		shipStatus += "\n";
		
		int counter = 0;
		for (Section s : sections) {
			counter++;
			
			shipStatus += counter + ": ";
			
			//FIXME: need INTERNAL Augments, too!
			if (s.externalAugments.size() > 0) {
				StringJoiner sj = new StringJoiner(", ");			
				for (ShipComponent sc : s.externalAugments) {
					sj.add(sc.component.componentName);
				}
				shipStatus += sj.toString() + " / ";
			}
			else {
				shipStatus += " - / ";
			}
			
			
			if (s.external == null) {
				shipStatus += " - "; 
			}
			else {
				shipStatus += color.get(s.external.status) + s.external.component.componentName; 
				if (s.external.status == Status.INTACT) {
					shipStatus +=  "[/color]";
				}
				else {
					shipStatus += " (" + s.external.status;
					if ((s.external.status == Status.REPAIRING) || (s.external.status == Status.RIGGING)) {

						shipStatus += ": " + s.external.repairProgress + "/" + GameMode.getInstance().factions.get(faction).getDamconTime();
					}
					shipStatus += ")[/color]";
				}
			}
			
			shipStatus += " / ";
			
			if (s.internal == null) {
				shipStatus += " - "; 
			}
			else {
				shipStatus += color.get(s.internal.status) + s.internal.component.componentName; 
				if (s.internal.status == Status.INTACT) {
					shipStatus +=  "[/color]";
				}
				else {
					shipStatus += " (" + s.internal.status;
					if ((s.internal.status == Status.REPAIRING) || (s.internal.status == Status.RIGGING)) {

						shipStatus += ": " + s.internal.repairProgress + "/" + GameMode.getInstance().factions.get(faction).getDamconTime();
					}
					shipStatus += ")[/color]";
				}
			}
			shipStatus += "\n";
		}
			
		
		shipStatus += "[/spoiler]\n\n";
		return shipStatus;		
	}
	
	public void incrimentDamcon() {
		for (Section s : sections) {
			if (s.internal != null && (s.internal.status == Status.REPAIRING || s.internal.status == Status.RIGGING)) {
				s.internal.repairProgress++;
			}
			if (s.external != null && (s.external.status == Status.REPAIRING || s.external.status == Status.RIGGING)) {
				s.external.repairProgress++;
			}
		}
	}
	
	public void checkDamcon() {
		for (Section s : sections) {
			if (s.internal != null && s.internal.repairProgress >= GameMode.getInstance().factions.get(faction).getDamconTime()) {
				s.internal.repairProgress = 0;
				if (s.internal.status == Status.REPAIRING) {
					s.internal.status = Status.INTACT;
				}
				if (s.internal.status == Status.RIGGING) {
					s.internal.status = Status.JURY_RIGGED;
				}
			}

			if (s.external != null && s.external.repairProgress >= GameMode.getInstance().factions.get(faction).getDamconTime()) {
				s.external.repairProgress = 0;
				if (s.external.status == Status.REPAIRING) {
					s.external.status = Status.INTACT;
				}
				if (s.external.status == Status.RIGGING) {
					s.external.status = Status.JURY_RIGGED;
				}
			}
		}		
	}
	
	//pulls out the projectiles from "incoming" which proc this turn. 
	//discards projectiles which miss.
	//calls "shipCounterMeasures" to handle defenses and distribute resulting damage.
	//TODO: clean up the dead code here
	public String projectilesHit() {
		report = "";
		
		List<Projectile> imminent = new ArrayList<Projectile>(); //this is a reduced list of projectiles we'll have to worry about now
		List<Projectile> valid = new ArrayList<Projectile>(); //this will be the rest of the projectiles.  Any projectiles which no longer have valid targetting data will also be discarded.
		
		// Actually, since the only time we should lose targetting data is if the ship jumps, we don't need this.  Ship will just clear its "incoming" with each jump. 
		// This will avoid any mismatch that occurs when nodes are cleaned up and map nodes no longer match.
//		List<MapNode> tempMN = MapManager.findAddress(this); 
//		MapNode shipLocation = tempMN.get(tempMN.size() - 1);//the ships location, so we know if the projectile has hit its target.
		
		//alright, begin filtering out the projectiles we care about
		for (Projectile p : incoming) {
						
			if (p.turnsUntilArrival <= 0) {//if it's hitting this turn, add it to imminent
				imminent.add(p);
			}
			else {
				valid.add(p); //it's not time to consider this projectile yet, so we'll add it back to the list
			}
		}
		incoming = valid; //this removes the projectiles that no longer have valid targetting data, and the ones we will be handling this turn.  i.e. we no longer need to track them.
		
		//report stuff...
		if (imminent.size() == 0) {
			report += "[u][b]No incoming projectiles.[/b][/u]\n";
		}
		else {
			report += "[u][b]" + imminent.size() + " incoming projectile(s).[/b][/u]\n";
		}
		
		
		//We need to check if the imminent projectiles HIT.  If they don't, then we don't need to spend countermeasures on them.
		int shipToHitMod = getShipReceiveHitModifier();
		List<Projectile> hitting = new ArrayList<Projectile>(); //this is our list of projectiles which hit
		for (Projectile p : imminent) {
			//need to match or exceed weapons hitChance
			int dieRoll = random.nextInt(6) + random.nextInt(6) + 2 + p.toHitMod; //2d6 roll (+2 is because 6 is the upper bound; roll is 0-5) + hit modifier.
						
			report += p.parent.componentName + ": (" + (dieRoll - p.toHitMod) + " + " + p.toHitMod + ") vs (" + p.toHitChance + " + " + shipToHitMod + ") | ";  
			
			if (dieRoll >= p.toHitChance + shipToHitMod) { //hits
				hitting.add(p);
				report += "(HIT)";
				//need to check for overkill (bonus damage for exceeding hitChance by a certain amount, can stack)...
				if (p.overKill > 0) {
					int spawnedProjectiles = (dieRoll - (p.toHitChance + shipToHitMod)) /  p.overKill;
					
					report += "(" + spawnedProjectiles + " OVERKILL)";
					
					//overKill: for every X the die roll is over the toHitChance, it spawns an extra hit (projectile)
					for (int i = 0; i < spawnedProjectiles; i++) {
						Projectile clonedProjectile = new Projectile(p.sourceShip, p.parent, p.health, p.turnsUntilArrival, p.toHitChance, p.toHitMod, p.overKill);  //there's probably room to make this more efficient...
						hitting.add(clonedProjectile);
					}
				}
				report += "\n";
			}
			else {
				report += "(MISS)\n";
			}
		}
		
		report += hitting.size() + " incoming hit(s).\n";
		
		shipCounterMeasures(hitting);
		
		return report;
	}	
	
	public void shipCounterMeasures(List<Projectile> hitting) {
		//this is a bit wonky, but I wanted to be clear how this worked.
		List<Projectile> activeProjectiles = new ArrayList<Projectile>();
		activeProjectiles.addAll(hitting);
		
		activeProjectiles = rollingDefenses(activeProjectiles);
		activeProjectiles = bankDefenses(activeProjectiles);
		HashMap<Section, List<Projectile>> incomingDamage = staticDefenses(activeProjectiles);
		
		dealDamage(incomingDamage);
	}

	public void dealDamage(HashMap<Section, List<Projectile>> incomingDamage) {
		/*
		 * dealDamage: damage external first, unless destroyed or obliterated - then damage internal.
		 * if internal is destroyed, obliterate external.
		 * if external is obliterated, obliterate internal.
		 * if both obliterated, pick random non-obliterated internal to hit (make a list of valid targets, hit, remove if obliterated)
		 * */
		List<Projectile> obliteratedRedirect = new ArrayList<Projectile>();
		
		report += "\n[b][u]Damage Taken:[/u][/b]\n";
		
		for (Section s : incomingDamage.keySet()) {
			projectileLoop:
			for (Projectile p : incomingDamage.get(s)) {
				
				//gotta catch any nulls...we can simplify it as "obliterated" since it acts the same way.
				Status internal;
				Status external;
				if (s.internal == null) {
					internal = Status.OBLITERATED;
				}
				else {
					internal = s.internal.status;
				}
				if (s.external == null) {
					external = Status.OBLITERATED;
				}
				else {
					external = s.external.status;  
				}
				
				//now this god-awful if tree loop...
				//goes to redirect
				if (isObliterated(internal) && isObliterated(external)) {
					obliteratedRedirect.add(p);
					continue projectileLoop;
				}
				
				//goes internal
				if (isObliterated(external) || (isIntact(internal) && isDestroyed(external))) {
					incrimentDamage(s.internal);
					report += s.internal.component.componentName + " is struck by " + p.parent.componentName + " and has become " + s.internal.status + "!\n";
					continue projectileLoop;
				}
				
				//goes external
				incrimentDamage(s.external);
				report += s.external.component.componentName + " is struck by " + p.parent.componentName + " and has become " + s.external.status + "!\n";
			}
		}
		
		//if a component was obliterated, it hits a random non-obliterated internal component (essentially firing into a hole in the ship).
		List<ShipComponent> targettableComponent = new ArrayList<ShipComponent>();
		for (Section s : sections) {
			if (s.internal != null && !isObliterated(s.internal.status)) {
				targettableComponent.add(s.internal);
			}
		}
		
		for (Projectile p : obliteratedRedirect) {
			//check if there's available internal components...
			if (targettableComponent.size() > 0) {
				ShipComponent sc = targettableComponent.get(random.nextInt(targettableComponent.size()));
				
				incrimentDamage(sc);
				report += sc.component.componentName + " is struck by " + p.parent.componentName + " via obliterated redirect and has become " + sc.status + "!\n";
				if (isObliterated(sc.status)) {
					targettableComponent.remove(sc);
				}
			}
			else {
				report += "All internals have been obliterated!\n";
				break; //we don't care about redirecting more damage at this point; the ship has no working internals left.  It's toast.
			}
		}
	}
	
	public void incrimentDamage(ShipComponent target) {
		//need to reset any damcon
		target.repairProgress = 0;
		
		switch (target.status) {
			case INTACT:
				target.status = Status.DAMAGED;
				break;
			case DAMAGED:
				target.status = Status.DESTROYED;
				break;
			case REPAIRING:
				target.status = Status.RIGGING;
				break;
			case JURY_RIGGED:
				target.status = Status.DESTROYED;
				break;
			case RIGGING:
				target.status = Status.OBLITERATED;
				break;
			case DESTROYED:
				target.status = Status.OBLITERATED;
				break;
			case OBLITERATED:
				break;
		}
	}
	
	public HashMap<Section, List<Projectile>> staticDefenses(List<Projectile> hitting) {
		/* build hashmap of sections with a list of projectiles for each.
		 * 
		 * iterate through the sections, iterate through projectiles.
		 * 
		 * does section have operational static defenses?
		 * get a list of static defenses on that section that are still operational.
		 * 
		 * hashmap each static defense with their weaponTypeDamage list
		 * sort the static defenses by whichever has the highest typeDamage for that projectile.
		 * 
		 * spend down the defense and projectile health until one hits zero.
		 * 
		 * if projectile hits zero, remove it from targettedSections, continue with projectile iteration.
		 * if defense hits zero, continue with defense iteration.
		 * 
		 * if defenses iterated through, next section
		 * 
		 */
		

		report += "\n[u][b]Static Defenses:[/b][/u]\n";
		
		//now, iterate through projectiles.  Get a random valid section.  If it has static defense, it operates.

		HashMap<Section, List<Projectile>> targettedSections = new HashMap<Section, List<Projectile>>();
		for (Section s : sections) {
			targettedSections.put(s, new ArrayList<Projectile>());
		}
		
		for (Projectile p : hitting) {
			Section s = sections.get(random.nextInt(sections.size()));
			targettedSections.get(s).add(p);
		}
		
		//Go through sections and deal with their projectiles
		for (Section s : targettedSections.keySet()) {
			
			//get static defenses
			List<ShipComponent> staticDefenses = getShipComponentsOfType(new DefenseStatic(null));
			
			//let each defense do its best.
			defenseLoop:
			for (ShipComponent sc : staticDefenses) { 
				if (!sc.powered) {
					continue defenseLoop;
				}
				
				List<String> sortedWeaponTypes = sortWeaponTypeByDamageHL(((DefenseStatic) sc.component).projectileTypeDamage.get(sc.status)); //prioritize by what weapon type it defeats best
				
				weaponTypeLoop:
				for (String wt : sortedWeaponTypes) {
					while (sc.miscCounter >= ((DefenseStatic) sc.component).capacity) { //while this defense is still capable
						
						if (((DefenseStatic) sc.component).projectileTypeDamage.get(sc.status).get(wt) <= 0) {//if defense won't block the projectile, no point in trying. Since it's sorted, the remaining wt's can't be blocked, either.
							break weaponTypeLoop;
						}
						
						//check if there's an incoming projectile this defense can block
						Projectile projectile = getProjectileOfType(targettedSections.get(s), wt);
						if (projectile == null) {
							continue weaponTypeLoop; //if not, check next-highest defense type
						}
						
						//Start spending down the defense and health TODO: could probably be improved.
						sc.miscCounter++;
						projectile.health -= ((DefenseStatic) sc.component).projectileTypeDamage.get(sc.status).get(wt);
						
						if (projectile.health <= 0) {
							targettedSections.get(s).remove(projectile);
							report += sc.component.componentName + " deflects an incoming " + projectile.parent.componentName + ".\n";
						}
					}
				}
			} //end of defenseLoop
		}
		
		return targettedSections;		
	}
	
	public List<Projectile> bankDefenses(List<Projectile> hitting) {
		/* Bank defenses: 
	public HashMap<Status, HashMap<WeaponType, Integer>> projectileTypeDamage = new HashMap<Status, HashMap<WeaponType, Integer>>(); 
		 *
		 * HashMap<banktype, list<component>>
		 * Iterate through bankDefenses
		 * get a list of weapon types sorted by damage HL, from Game.
		 * iterate through the weapontypes
		 * get that weapon type from the projectiles list
		 * decriment projectile health and bank supply until one hits 0
		 *  > if projectile hits 0, remove it from list and get next projectile 
		 *  > if bank supply hits 0, get next bank supply.
		 * 
		 * 
		 */
		
		report += "\n[u][b]Bank Defenses:[/b][/u]\n";
		
		bankLoop:
		for (String bank : bankDefenses.keySet()) {
			if (bankDefenses.get(bank) <= 0) { //if we don't have any more defense banked, check the next one
				continue bankLoop;
			}
			
			List<String> sortedWeaponTypes = sortWeaponTypeByDamageHL(GameMode.getInstance().bankDefenseProjectileTypeDamage.get(bank));
			
			weaponTypeLoop:
			for (String wt : sortedWeaponTypes) {
				//note: I had this as a do-while loop, but I think it was a left-over from an earlier implimentation.  I've switched it to a simple while loop.
				while (bankDefenses.get(bank) >= 0) { //keep shooting projectiles down while we have shield strength
					if (GameMode.getInstance().bankDefenseProjectileTypeDamage.get(bank).get(wt) <= 0 ) { //if shield won't block the projectile, no point in trying.  
						break weaponTypeLoop;
					}
					
					Projectile projectile = getProjectileOfType(hitting, wt);
					if (projectile == null) {
						continue weaponTypeLoop;
					}
					
					//TODO: this can probably be improved.
					bankDefenses.put(bank, bankDefenses.get(bank) - 1);
					projectile.health -= GameMode.getInstance().bankDefenseProjectileTypeDamage.get(bank).get(wt);
					
					if (projectile.health <= 0) {
						hitting.remove(projectile);
						report += bank + " deflects an incoming " + projectile.parent.componentName + ": " + bankDefenses.get(bank) + " strength left.\n";
					}
				} 
			}
		}

		report += hitting.size() + " remaining hit(s).\n";
		
		return hitting;
	}

	public List<Projectile> rollingDefenses(List<Projectile> hitting) {	
		/* So, how does defense work?
		 * 
		 * We iterate through rollingDefenses.  
		 * get the component's status, and whether it is powered.
		 * rank the damage output based on the status.
		 * attempt to get a projectile for the damage type; iterate through the damage output until we get a match.
		 * damage the projectile.
		 * if the defense is used up, continue to the next defense.  if not, get the next projectile.
		 * 
		 * projectileTypeDamage = HashMap<Status, HashMap<WeaponType, Integer>>(); //weapon type, damage done to that projectile. Defenses prioritized by max damage. 0 means it doesn't even try.
		 */

		report += "\n[u][b]Rolling Defenses:[/b][/u]\n";
		
		//rolling defenses
		List<ShipComponent> rollingDefenses = getShipComponentsOfType(new DefenseRolling(null)); //get all our defenses from the ships components.
		
		rollingDefensesLoop:
		for (ShipComponent sc : rollingDefenses) {
			if (hitting.size() == 0) {
				break rollingDefensesLoop; //no point in processing defenses if there's nothing incoming
			}
			
			if (!sc.powered ) {
				continue rollingDefensesLoop; //if the component is not powered, we skip it
			}
			
			DefenseRolling defenseComponent = (DefenseRolling) sc.component; //cast it as DefenseRolling so we can get the arguments for it (we validated it earlier, so this should be safe)
			List<String> sortedWeaponTypes = sortWeaponTypeByDamageHL(defenseComponent.projectileTypeDamage.get(sc.status)); //sort this defense by what it targets best.
			
			int repeatCounter = 0; //in case the defense can repeat, we keep track of how many times it fires.

			//Start this mess of a loop
			weaponTypeLoop:
			for (String wt : sortedWeaponTypes) {
				
				shutDownOnSuccessLoop:
				do {
					if (defenseComponent.projectileTypeDamage.get(sc.status).get(wt) <= 0) { //if it won't do any damage to this weapon type, no point in trying. 
						break weaponTypeLoop; //since the weapontypes were sorted, any other types would also be 0 or less.
					}
					
					//The projectile we'll be shooting at.  Since getProjectileOfType iterates through a list, we'll always deal with the most-damaged projectile first.
					Projectile possibleProjectile = getProjectileOfType(hitting, wt);
					if (possibleProjectile == null) { //we couldn't find any incoming projectiles of this type, so we go to the next weapon type.
						continue weaponTypeLoop;
					}
					
					//okay: now we have a defense, and a projectile it can fight.
					
					//need to roll and match/exceed the projectileTypeRoll for the defense.  Add stackingMalus * repeatCounter 
					//if it hits, it deals the projectileTypeDamage to that projectile
					//if projectile health is <= 0, projectile is removed from 'hitting'
					//if shutsDownOnSuccess, go to next defense
					//if not, increment counter, repeat loop.
					
					//augment boost:
					int augmentBonus = 0;
					for (Augment augment : sc.augments) {
						//don't need to check status, it's dependent on the sc.  
						augmentBonus += augment.rollingDefenseBoost;
					}
					
					int dieRoll = random.nextInt(6) + random.nextInt(6) + 2; //2d6 (+2 because nextInt(6) returns 0-5)
					
					if (dieRoll + augmentBonus + (defenseComponent.stackingMalus * repeatCounter) >= defenseComponent.projectileTypeRoll.get(sc.status).get(wt)) {
						//alright, great - we hit the incoming projectile.
						possibleProjectile.health -= defenseComponent.projectileTypeDamage.get(sc.status).get(wt); //slash the projectiles health.
						if (possibleProjectile.health <= 0) { //if projectile is killed...
							hitting.remove(possibleProjectile);  //i know i shouldn't modify an input directly, but...¯\_(ツ)_/¯
							report += "(HIT) " + defenseComponent.componentName + " (" + dieRoll + " + " + (augmentBonus + (defenseComponent.stackingMalus * repeatCounter)) 
									+ ") vs (" + (defenseComponent.projectileTypeRoll.get(sc.status).get(wt)) + ") - DESTROYS an incoming " + possibleProjectile.parent.componentName + ".\n";
						}
						else {
							report += "(HIT) " + defenseComponent.componentName + " (" + dieRoll + " + " + (augmentBonus + (defenseComponent.stackingMalus * repeatCounter)) 
									+ ") vs (" + (defenseComponent.projectileTypeRoll.get(sc.status).get(wt)) + ") - DAMAGES an incoming " + possibleProjectile.parent.componentName + ".\n";
						}
						repeatCounter++; //increase the counter
					} 
					else {
						report += "(MISS) " + defenseComponent.componentName + " (" + dieRoll + " + " + (augmentBonus + (defenseComponent.stackingMalus * repeatCounter)) 
								+ ") vs (" + (defenseComponent.projectileTypeRoll.get(sc.status).get(wt)) + ") - MISSES an incoming " + possibleProjectile.parent.componentName + ".\n";						
						//we missed the incoming projectile, we are done with this component
						break weaponTypeLoop;
					}
					
					if (hitting.size() == 0) {
						break rollingDefensesLoop; //no point in continuing if there's nothing incoming
					}

				}while (!defenseComponent.shutsDownOnSuccess);
			}
		}
		
		report += hitting.size() + " remaining hit(s).\n";
		
		return hitting;
	}
	
	public Projectile getProjectileOfType(List<Projectile> collectionOfProjectiles, String typeToGet) {
		for (Projectile p : collectionOfProjectiles) {
			if (((Weapon) p.parent).weaponType.equals(typeToGet)) {
				return p;
			}		
		}
		
		return null;
	}
	
	public int getShipReceiveHitModifier() {
		int modifier = 0; //higher is better for the ship
		
		//TODO: make this a hashmap of PassiveDefense, List<ShipComponent> - in case the passive defense stacks.  If it doesnt, get the best value from that sc by status.
		List<ShipComponent> tempList = getShipComponentsOfType(new PassiveDefense(null));
		HashMap<PassiveDefense, List<ShipComponent>> passiveDefenses = new HashMap<PassiveDefense, List<ShipComponent>>();
		for (ShipComponent sc : tempList) {
			if (passiveDefenses.containsKey(sc.component)) {
				passiveDefenses.get(sc.component).add(sc); 
			}
			else {
				List<ShipComponent> tempList2 = new ArrayList<ShipComponent>();
				tempList2.add(sc);
				passiveDefenses.put((PassiveDefense) sc.component, tempList2);
			}
		}
		
		//alright, now to flip through the hashmap and get the bonus
		for (PassiveDefense pd : passiveDefenses.keySet()) {
			if (pd.stackingModifierSame) { //if the passive defense stacks for the same module FIXME: need to have different vs same stack
				for (ShipComponent sc : passiveDefenses.get(pd)) {
					if (sc.powered) {
						modifier += ((PassiveDefense) sc.component).getPassiveDefense(sc.status);
					}
				}
			}
			else { //f the passive defense DOESNT stack, we get the best modifier available.
				int tempModifier = 0;
				for (ShipComponent sc : passiveDefenses.get(pd)) {
					if (sc.powered) {
						int statusModifier = ((PassiveDefense) sc.component).getPassiveDefense(sc.status);
						tempModifier = tempModifier >= statusModifier ? tempModifier : statusModifier; //take the better bonus
					}
				}
				modifier += tempModifier;
			}
		}
		
		return modifier;
	}
	
	public List<ShipComponent> getShipComponentsOfType(Component componentType) {
		List<ShipComponent> shipComponents = new ArrayList<ShipComponent>();
		
		for (Section section : sections) {
			shipComponents.addAll(section.getShipComponentsOfTypeFromSection(componentType));
		}
		
		return shipComponents;
	}
	
	public static List<String> sortWeaponTypeByDamageHL(HashMap<String, Integer> setToSort) {		
		List<String> sortedSet = new ArrayList<>(setToSort.keySet());
		Collections.sort(sortedSet, (a, b) -> setToSort.get(a) > setToSort.get(b) ? -1 : setToSort.get(a) == setToSort.get(b) ? 0 : 1);
		return sortedSet;
	}
	
	public void initializeBankDefenses() {
		List<ShipComponent> scList = getShipComponentsOfType(new DefenseBank(null));
		
		for (ShipComponent sc : scList) {
			DefenseBank db = (DefenseBank) sc.component;
			
			if (bankDefenses.containsKey(db.defenseResource)) {
				bankDefenses.put(db.defenseResource, bankDefenses.get(db.defenseResource) + db.maxValue.get(sc.status));
			}
			else {
				bankDefenses.put(db.defenseResource, db.maxValue.get(sc.status));
			}
		}
	}
	
	public HashMap<String, Integer> rechargeBankDefenses() {
		List<ShipComponent> scList = getShipComponentsOfType(new DefenseBank(null));
		
		HashMap<String, Integer> rechargeAmount = new HashMap<String, Integer>();
		HashMap<String, Integer> maxAmount = new HashMap<String, Integer>();
		
		for (ShipComponent sc : scList) {
			if (!sc.powered) {
				continue;
			}
			
			DefenseBank db = (DefenseBank) sc.component;
			
			if (rechargeAmount.containsKey(db.defenseResource)) {
				rechargeAmount.put(db.defenseResource, rechargeAmount.get(db.defenseResource) + db.perTurnRecharge.get(sc.status));
			}
			else {
				rechargeAmount.put(db.defenseResource, db.perTurnRecharge.get(sc.status));
			}
			
			if (maxAmount.containsKey(db.defenseResource)) {
				maxAmount.put(db.defenseResource, maxAmount.get(db.defenseResource) + db.maxValue.get(sc.status));				
			}
			else {
				maxAmount.put(db.defenseResource, db.maxValue.get(sc.status));
			}
		}
		
		for (String bank : bankDefenses.keySet()) {
			bankDefenses.put(bank, bankDefenses.get(bank) + rechargeAmount.get(bank));
			
			if (bankDefenses.get(bank) > maxAmount.get(bank)) {
				bankDefenses.put(bank, maxAmount.get(bank));
			}
		}
		
		return maxAmount;
	}

	public int calculatePower() {
		int powerBalance = 0;
		
		for (Section s : sections) {
			if (s.internal != null && s.internal.powered && isIntact(s.internal.status)) {
				powerBalance -= s.internal.component.powerRequirement;
			}
			if (s.external != null && s.external.powered && isIntact(s.external.status)) {
				powerBalance -= s.external.component.powerRequirement;
			}
			for (ShipComponent a : s.externalAugments) {
				powerBalance -= a.component.powerRequirement;
			}
		}
		
		for (ShipComponent sc : getShipComponentsOfType(new PowerSupply(null))) {
			powerBalance += ((PowerSupply) sc.component).powerSupply.get(sc.status);
		}
		
		return powerBalance;
	}
	
	public boolean isIntact(Status s) {
		//note that "intact" means not Destroyed, Obliterated, or being Rigged
		if (s == Status.INTACT || s == Status.DAMAGED || s == Status.REPAIRING || s == Status.JURY_RIGGED) {
			return true;
		}
		return false;		
	}
	
	public boolean isDestroyed(Status s) {
		//note that "destroyed" means not Intact, Damaged, Repairing, or Obliterated
		if (s == Status.DESTROYED || s == Status.RIGGING) {
			return true;
		}
		return false;		
	}
	
	public boolean isObliterated(Status s) {
		if (s == Status.OBLITERATED) {
			return true;
		}
		return false;
	}
	

	//TODO: need an "is alive" method to determine if enough damage has been received to kill the ship.
}
