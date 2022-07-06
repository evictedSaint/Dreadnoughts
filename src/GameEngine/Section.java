package GameEngine;

import java.util.ArrayList;
import java.util.List;

import ComponentClasses.Component;
import GameEngine.Game.Status;

public class Section {
	
	ShipComponent internal = null;
	ShipComponent external = null;

	List<ShipComponent> internalAugments = new ArrayList<ShipComponent>();
	List<ShipComponent> externalAugments = new ArrayList<ShipComponent>();
	
	
	public Section() {
	}
	
	public List<ShipComponent> getShipComponentsOfTypeFromSection(Component componentType) {
		List<ShipComponent> shipComponents = new ArrayList<ShipComponent>();
		
		if (internal != null && internal.component.getClass() == componentType.getClass()) {
			shipComponents.add(internal);
		}
		if (external != null && external.component.getClass() == componentType.getClass()) {
			shipComponents.add(external);
		}
		for (ShipComponent sc : internalAugments) {
			if (sc.component.getClass() == componentType.getClass()) {
				shipComponents.add(sc);
			}
		}
		for (ShipComponent sc : externalAugments) {
			if (sc.component.getClass() == componentType.getClass()) {
				shipComponents.add(sc);
			}
		}
		
		return shipComponents;
	}
	
	public boolean canBeTargetted() {
		if ((internal != null) && (internal.status != Status.OBLITERATED)) {
			return true;
		}
		if ((external != null) && (external.status != Status.OBLITERATED)) {
			return true;
		}
		return false;
	}
}
