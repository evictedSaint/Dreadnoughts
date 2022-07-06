package GameEngine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class MapNode {


	Integer distance;
	
	List<MapNode> children = new ArrayList<MapNode>();
	
	List<Ship> shipsHere = new ArrayList<Ship>();
	
	
	public MapNode(Integer distance) {
		this.distance = distance;
	}
}
