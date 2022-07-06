package ComponentClasses;

import java.util.HashMap;
import GameEngine.Game.Status;

public class JumpDrive extends Component {

	public HashMap<Status, Integer> jumpTime = new HashMap<Status, Integer>();
	public HashMap<Status, Integer> jumpDistance = new HashMap<Status, Integer>();
	
	public JumpDrive(String componentName) {
		super(componentName);
		
		for (Status s : Status.values()) {
			jumpTime.put(s, 0);
			jumpDistance.put(s, 0);
		}
	}
}
