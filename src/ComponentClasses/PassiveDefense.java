package ComponentClasses;

import java.util.HashMap;
import GameEngine.Game.Status;

public class PassiveDefense extends Component {

	public HashMap<Status, Integer> receiveHitModifier = new HashMap<Status, Integer>();
	public boolean stackingModifierSame = false;
	public boolean stackingModifierDifferent = false; //FIXME: not implemented
	
	public PassiveDefense(String componentName) {
		super(componentName);
		
		for (Status s : Status.values()) {
			receiveHitModifier.put(s, 0);
		}
	}
	
	public int getPassiveDefense(Status status) {
		return receiveHitModifier.get(status);
	}
}
