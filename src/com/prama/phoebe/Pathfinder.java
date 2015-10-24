package com.prama.phoebe;

import java.util.ArrayList;

public class Pathfinder {
	
	public static byte maxAmountOfSteps = 20;
	
	
	public Pathfinder(short destinationMapID) {
		this.destination = destinationMapID;
		this.findPaths(destinationMapID);
	}
	
	
	protected short destination;
	protected short minAmountOfsteps;
	protected ArrayList<ArrayList<Map>> paths = new ArrayList<ArrayList<Map>>();
	
	public short getMinAmountOfSteps() {
		return this.minAmountOfsteps;
	}
	
	public ArrayList<ArrayList<Map>> getPaths() {
		return this.paths;
	}
	
	protected void findPaths(short targetMapID) {
		for(short curMapID = 0; curMapID < Map.matrixArray.length; curMapID++) {
			if(curMapID != targetMapID) {
				ArrayList<Map> path = new ArrayList<Map>();
				path.add(new Map(curMapID));
				byte length = 1;
				short mapID = curMapID;
				while(mapID != targetMapID && length < Pathfinder.maxAmountOfSteps) {
					mapID = Map.matrixArray[mapID];
					path.add(new Map(mapID));
					length++;
				}
				if(length < Pathfinder.maxAmountOfSteps) {
					this.paths.add(path);
				}
			}
		}
	}
	
}
