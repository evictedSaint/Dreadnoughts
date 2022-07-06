package ComponentClasses;

public class Augment extends Component {
	//TODO: make Augments a thing.

	public boolean destroyedWithBase = true; //is this augment destroyed when the base component gets destroyed?
	
	public int bankMaxValue = 0; //amount a DefenseBank max value is increased by.  e.g. Shield Capacitors
	
	public int powerMultiplier = 1; //amount a power supply gets multiplied by.  e.g. Experimental Reactor
	
	public int rollingDefenseBoost = 0; //amount a rolling defense gets boosted by. e.g. Overclocker

	public Augment(String componentName) {
		super(componentName);
		augmentSlot = true;
		
		/*
Shield Capacitors: Increases the Max Shield Strength by one. Must be placed on a Shield Generator. Lost permanently when base Component is Destroyed.
Cost: 3 MCreds

Experimental Reactor: Uses cutting-edge, volatile reagents to double Reactor output. Must be placed on a Reactor. 1 in 4 chance of permanently destroying base Sector and damaging adjacent Sectors when damaged.
Cost: 5 MCreds

Overclocker: Enhanced targeting computers and precision motors increase the effectiveness of Point Defence by +2. Must be placed on a Point Defence.
Cost: 4 MCreds
		 */
	}
}
