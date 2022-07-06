package GameEngine;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class MapManager {
	
	public static HashMap<Integer, String> distances;
	public static MapNode trunk = new MapNode(6);
	

	public static String map = "";
	

	/** Handles ship movement.
	 * 
	 * 
	 * 
	 * @param shipToMove
	 * @param target
	 * @param distance
	 * @param movingTowards
	 */
	public static void moveShip(Ship shipToMove, Ship target, int distance, boolean movingTowards) {
		/*
		 * Alright, let's think about this for a second.
		 * 
		 * Let's say we have the branch:
			[b]MEDIUM[/b]
			|   |- F
			|   >- G
			>- [b]SHORT[/b]
			   |- C
			   >- [b]CLOSE[/b]
			      |- D
			      >- E
		 * and we want to move E two steps towards G.
		 * 
		 * so, moving down a branch seems easy enough - how do we move UP the tree?
		 * 
		 * Let's perform a depth-first recursive search for our shipToMove, then store the branches to get there in a list.
		 * Let's do the same for the target, and store it in a separate list.
		 * 
		 * now we know the path to both ships.  We can compare the two lists to find the nearest common parent - our ship will 
		 * move UP the tree until we hit the parent, then DOWN the tree to the target. If the ship is already on the common parent, 
		 * then we already have the first part handled.
		 * 
		 * We need to ensure the distance moved is discrete steps - if the next node isn't +/- 1, then we need to introduce a node 
		 * between them.  We also can't move out of the max distance available in our map (e.g. can't go beyond "Extreme")
		 */
		
		//TODO: allied ships should make it easier to move - non interference, at least.  Maybe add this functionality later.
		//TODO: some code is repeated here...but it'd be a real pain in the ass to isolate it and make it into a function. Probably not worth it.
		//TODO: I'm not doing any checks on the old node the ship was at.  Should probably fix that; until then, I can just run cleanMap() between each move.
		int distanceLeftToMove = distance;
		
		List<MapNode> shipAddress = findAddress(shipToMove);
		List<MapNode> targetAddress = findAddress(target);
		
		MapNode commonParent = findCommonParent(shipToMove, target);
		
		//can't forget: we've got to remove the old reference to the ship we're moving. We can do this here, now that we've found all the addresses and parents.
		shipAddress.get(shipAddress.size()-1).shipsHere.remove(shipToMove);
		
		int distanceToParent = commonParent.distance - shipAddress.get(shipAddress.size()-1).distance;
		int distanceToTarget = commonParent.distance - targetAddress.get(targetAddress.size()-1).distance;
			
		//first, we move towards parent until we can't move any further or the distance has hit 0.
		if (distanceLeftToMove < distanceToParent) {
			//we need to create a node, or jump to a node along the way, then return.
			//so - is there an existing node?
			int nodeDistance = shipAddress.get(shipAddress.size()-1).distance + distanceLeftToMove;
			for (MapNode n : shipAddress) {
				if (n.distance == nodeDistance) {
					//perfect: we already have a node here, and we can just pop the ship into place.
					n.shipsHere.add(shipToMove);
					return;
				}
				else if (n.distance < nodeDistance) { //no node with that distance exists, and we've skipped past the target distance
					MapNode nodeToModify = shipAddress.get(shipAddress.indexOf(n)-1);
					
					//insert a new node in nodeToModify with nodeDistance which inherits n. Remove n from nodeToModify.
					MapNode newNode = new MapNode(nodeDistance);
					newNode.shipsHere.add(shipToMove);
					newNode.children.add(n);
					nodeToModify.children.remove(n);
					nodeToModify.children.add(newNode);
					return;
				}
			}
			//TODO: I should really look into a logger at some point.
			System.out.println("WARNING: failure to place ship towards parent. Something messed up. :(");
		}
		else if (distanceLeftToMove == distanceToParent) {
			//we drop them off here at the parent, then return. Easy.
			commonParent.shipsHere.add(shipToMove);
			return;
		}
		else { //if we can move further than distance to the parent:
			//chunk off the distance we have left, then progress to the next phase of the move. Nothing complicated here.
			distanceLeftToMove -= distanceToParent;
		}
		
		//---------------------------------------------------------------------------------------------------------------
		//Now, we have to handle whether the ship is moving TOWARDS or AWAY from the target.
		if (movingTowards) {
			//if we have move left over, we move towards the target - DOWN the tree.
			if (distanceLeftToMove < distanceToTarget) {
				//like above, we've gotta either find an appropriate node, or make one.
				int nodeDistance = commonParent.distance - distanceLeftToMove;
				for (MapNode n : targetAddress) {
					if (n.distance == nodeDistance) {
						//perfect: we already have a node here, and we can just pop the ship into place.
						n.shipsHere.add(shipToMove);
						return;
					}
					else if (n.distance < nodeDistance) { //no node with that distance exists, and we've skipped past the target distance
						MapNode nodeToModify = targetAddress.get(targetAddress.indexOf(n)-1);
						
						//insert a new node in nodeToModify with nodeDistance which inherits n. Remove n from nodeToModify.
						MapNode newNode = new MapNode(nodeDistance);
						newNode.shipsHere.add(shipToMove);
						newNode.children.add(n);
						nodeToModify.children.remove(n);
						nodeToModify.children.add(newNode);
						return;
					}
				}
				//TODO: I should really look into a logger at some point.
				System.out.println("WARNING: failure to place ship towards target. Something messed up. :(");
			}
			else if (distanceLeftToMove == distanceToTarget) {
				//super simple - just drop them off at the same node.
				targetAddress.get(targetAddress.size()-1).shipsHere.add(shipToMove);
				return;
			}
			else {
				//a bit trickier: they're now closing the distance, so both the ship AND the target change nodes.
				//gotta make sure they don't go negative on the distance, but this shouldn't be terribly hard.
				int newDistance = (targetAddress.get(targetAddress.size()-1).distance - distanceLeftToMove < 0 ? 0 : targetAddress.get(targetAddress.size()-1).distance - distanceLeftToMove);
				
				//alright, now just make a new node with the correct distance, slot it into place, stick both ships there, and get rid of the targets old location.
				MapNode newNode = new MapNode(newDistance);
				newNode.shipsHere.add(shipToMove);
				newNode.shipsHere.add(target);
				targetAddress.get(targetAddress.size()-1).shipsHere.remove(target);
				targetAddress.get(targetAddress.size()-1).children.add(newNode);
				return;
			}
		}
		
		else {
			//if we have move left over, we move away from the target - UP the tree.
			//gotta make sure they don't escape beyond the bounds of the alloted distances.  
			//TODO: maybe allow distances beyond max to "escape"?
			int nodeDistance = (commonParent.distance + distanceLeftToMove > Collections.max(distances.keySet()) ? Collections.max(distances.keySet()) : commonParent.distance + distanceLeftToMove);
			for (MapNode n : shipAddress) {
				if (n.distance == nodeDistance) {
					//perfect: we already have a node here, and we can just pop the ship into place.
					n.shipsHere.add(shipToMove);
					return;
				}
				else if (n.distance < nodeDistance) { //no node with that distance exists, and we've skipped past the target distance
					MapNode nodeToModify = targetAddress.get(targetAddress.indexOf(n)-1);
					
					//insert a new node in nodeToModify with nodeDistance which inherits n. Remove n from nodeToModify.
					MapNode newNode = new MapNode(nodeDistance);
					newNode.shipsHere.add(shipToMove);
					newNode.children.add(n);
					nodeToModify.children.remove(n);
					nodeToModify.children.add(newNode);
					return;
				}
			}
			//TODO: I should really look into a logger at some point.
			System.out.println("WARNING: failure to place ship away from target. Something messed up. :(");
		}
		
	}
	
	public static MapNode findCommonParent(Ship ship1, Ship ship2) {
		//TODO: this findAddress is repeated in the moveShip command.  Should probably streamline this at some point
		List<MapNode> shipAddress1 = findAddress(ship1);
		List<MapNode> shipAddress2 = findAddress(ship2);
		
		MapNode commonParent = trunk; //the trunk should at least be their common parent.
		
		//just get the smaller size so we have to loop fewer times
		int maxAddressSize = (shipAddress1.size() < shipAddress2.size() ? shipAddress1.size() : shipAddress2.size());
		for (int i = 0; i < maxAddressSize; i++) {
			//if they no longer have the same node at this point, then the most common parent was the previous node.
			if (!shipAddress1.get(i).equals(shipAddress2.get(i))) { 
				return commonParent;
			}
			//if we haven't failed, then the next most-common parent is here.
			else {
				commonParent = shipAddress1.get(i);
			}
		}
		
		//if we go the whole length, then the most common parent was whatever we added last.
		return commonParent;
	}
	
	public static List<MapNode> findAddress(Ship shipToFind) {
		List<MapNode> address = new ArrayList<MapNode>();
		return recursiveFindAddress(shipToFind, trunk, address);
	}
	
	public static List<MapNode> recursiveFindAddress(Ship shipToFind, MapNode currentNode, List<MapNode> address) {
		List<MapNode> currentAddress = new ArrayList<MapNode>();
		currentAddress.addAll(address);
		currentAddress.add(currentNode);
		
		if (currentNode.shipsHere.contains(shipToFind)) {
			return currentAddress;
		}
		
		List<MapNode> possibleMatch = new ArrayList<MapNode>();
		for (MapNode child : currentNode.children) {
			
			possibleMatch = recursiveFindAddress(shipToFind, child, currentAddress);
			if (possibleMatch != null) {
				return possibleMatch;
			}
		}
		
		return null;
	}
	
	
	/** Makes sure the entire map is fully, completely clean. 
	 * 
	 * Simply calls @recursionNodeValidation() with 'trunk' as the MapNode.
	 * 
	 * cleanMap() should be called before every displayMap(), so the displayed map doesn't contain any lonely or hanging nodes.  Ideally, we won't ever have to clean the map, 
	 * since we should be handling nodes with each action, but this is a good place to start if for no other reason than to have a good understanding
	 */
	public static void cleanMap() {
		recursionNodeValidation(trunk);
	}
	
	/** Recursively examine nodes for lonely children and ships.
	 * 
	 * Nodes must branch to be considered valid.  Any node which only has one child and no ships is "lonely" and simply taking up space.  We can safely remove this node.
	 * Similarly, any node which only has one ship and no children is also lonely.  We can pass the ship to the parent and safely remove that node.
	 * Any node which has neither a child nor a ship is a hanging node, and should be removed.
	 * 
	 * @param (MapNode parent) is the highest-tier node that gets examined.  Will examine all descendents from this node.  Using 'trunk' will clean the entire map, but this can
	 * be called with any MapNode to ensure that particular branch is nice and tidy.
	 */
	public static boolean recursionNodeValidation(MapNode parent) {
		boolean eatMe = false;
		
		//We examine children.
		HashSet<MapNode> childrenToEat = new HashSet<MapNode>();
		for (MapNode child : parent.children) {
			if (recursionNodeValidation(child)) { //if child needs to be consumed, we add it to our list
				childrenToEat.add(child);
			}
		}
		
		//note we don't need to re-examine the children of what we consume; they've already been validated (we enter recursion before doing any 
		//validation on the current node, so the node tree is checked from bottom-up)
		for (MapNode childToEat : childrenToEat) {
			parent.children.addAll(childToEat.children);
			parent.shipsHere.addAll(childToEat.shipsHere);
			parent.children.remove(childToEat);
		}
		
		//node must have at least either multiple children, or multiple ships, or one of each - otherwise, it will be consumed by its parent.
		//if validation fails, we alert the parent, who then consumes this node.
		if (parent.children.size() + parent.shipsHere.size() < 2) {
			eatMe = true;
			return eatMe;
		}
		
		return eatMe;
	}
	
	/** Displays the full node map.
	 * 
	 * Simply cleans out the @map variable, calls @recursionMapBuilder() with 'trunk' as the MapNode, and outputs the result to the console. 
	 * 
	 */
	public static void displayMap() {
		map = "";
		recursionMapBuilder(trunk, "", false);
		System.out.println(map);
	}
	
	/** Recursively examines each node for children and ships, then adds them to the @map variable to be displayed.
	 * 
	 * @param n
	 * @param depth
	 * @param last
	 */
	public static void recursionMapBuilder(MapNode n, String depth, boolean last) {
		//displaying distance
		if (n.equals(trunk)) { //if it's the first node, we don't need to pad it
			map += depth + "[b]" + distances.get(n.distance) + "[/b]\n";			
		}
		else if (last) { //last node in a chain doesn't continue a line, so padding needs to be a bit longer
			map += depth + ">- [b]" + distances.get(n.distance) + "[/b]\n";
		}
		else {
			map += depth + "- [b]" + distances.get(n.distance) + "[/b]\n";
		}
		
		String newDepth = "   |";
		
		//displaying ships
		List<Ship> tempShipList = n.shipsHere;
		for (Ship s : tempShipList) {
			if (s.equals(tempShipList.get(tempShipList.size()-1)) && (n.children.size() == 0)) { //if last ship and there's no child nodes, we'll break the line
				map += depth + "   >- " + s.shipName + "\n";
			}
			else {
				map += depth + newDepth + "- " + s.shipName + "\n";
			}
		}
		
		//displaying child nodes
		List<MapNode> sortedNodes = sortByDistanceHL(n.children);
		for (MapNode child : sortedNodes) {
			if (child.equals(sortedNodes.get(sortedNodes.size()-1))) { //if last child, we dont continue the line
				recursionMapBuilder(child, depth + "   ", true);
			}
			else {
				recursionMapBuilder(child, depth + newDepth, false);
			}
		}
	}
	
	/**
	 * @param setToSort
	 * @return sorted set, from high to low
	 */
	public static List<MapNode> sortByDistanceHL(List<MapNode> setToSort) {		
		List<MapNode> sortedMapNodes = new ArrayList<>(setToSort);
		Collections.sort(sortedMapNodes, (a, b) -> a.distance > b.distance ? -1 : a.distance == b.distance ? 0 : 1);
		return sortedMapNodes;
	}
}
