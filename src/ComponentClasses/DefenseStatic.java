package ComponentClasses;

public class DefenseStatic extends Defense {

	public int capacity = 0; //how many times this defense can be used per turn.
	
	/** Like armor.
	 */
	public DefenseStatic(String componentName) {
		super(componentName);
	}
}
