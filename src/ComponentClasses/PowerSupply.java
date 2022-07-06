package ComponentClasses;

import java.util.HashMap;

import GameEngine.Game.Status;

public class PowerSupply extends Component {


	public HashMap<Status, Integer> powerSupply = new HashMap<Status, Integer>();
	
	public PowerSupply(String componentName) {
		super(componentName);
		
		for (Status s : Status.values()) {
			powerSupply.put(s, 0);
		}
	}
	
	public int getPower(Status status) {
		return powerSupply.get(status);
	}
}
