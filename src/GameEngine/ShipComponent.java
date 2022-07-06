package GameEngine;

import java.util.ArrayList;
import java.util.List;

import ComponentClasses.Component;
import ComponentClasses.Augment;
import GameEngine.Game.Status;

public class ShipComponent {
	
	Component component;
	
	List<Augment> augments = new ArrayList<Augment>();
	
	public Status status = Status.INTACT;
	
	int repairProgress = 0;
	
	boolean powered = true;
	
	int miscCounter = 0; //TODO: oh buddy, this is probably a really horrible idea.  Figure out something more robust.
	
	
	/**
	 * Different from Component in the ComponentClasses; stores data about the actual ship's use of the component.
	 * @param component
	 */
	public ShipComponent (Component component) {
		this.component = component;
	}

}
