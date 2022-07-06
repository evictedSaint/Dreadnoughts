package GameEngine;

public class Faction {
	
	public String factionName;
	
	public int damconTime;
	
	public int deathPerc; //if the number of destroyed/obliterated INTERNAL components *100, divided by number of internal components is GREATER or EQUAL to this number, ship dies.
	
	//TODO: gotta fill this thing out
	public Faction(String factionName, int damconTime, int deathPerc) {
		this.factionName = factionName;
		this.damconTime = damconTime;
		this.deathPerc = deathPerc;
	}
	
	public int getDamconTime() {
		return damconTime;
	}

	public int getDeathPerc() {
		return deathPerc;
	}
}
