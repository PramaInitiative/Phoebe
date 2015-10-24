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
	protected ArrayList<ArrayList<Short>> paths = new ArrayList<ArrayList<Short>>();
	
	public short getMinAmountOfSteps() {
		return this.minAmountOfsteps;
	}
	
	public ArrayList<ArrayList<Short>> getPaths() {
		return this.paths;
	}
	
	protected void findPaths(short targetMapID) {
		for(short curMapID = 0; curMapID < Map.matrixArray.length; curMapID++) {
			if(curMapID != targetMapID) {
				ArrayList<Short> path = new ArrayList<Short>();
				path.add(curMapID);
				byte length = 1;
				short mapID = curMapID;
				while(mapID != targetMapID && length < Pathfinder.maxAmountOfSteps) {
					mapID = Map.matrixArray[mapID];
					path.add(mapID);
					length++;
				}
				if(length < Pathfinder.maxAmountOfSteps) {
					this.paths.add(path);
				}
			}
		}
	}
	
}
