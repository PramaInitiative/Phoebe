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
		for(short curMapID : Map.matrixArray) {
			if(curMapID != targetMapID) {
				ArrayList<Short> path = new ArrayList<Short>();
				path.add(curMapID);
				byte length = 1;
				while(curMapID != targetMapID && length < Pathfinder.maxAmountOfSteps) {
					curMapID = Map.matrixArray[curMapID];
					path.add(curMapID);
					length++;
				}
				if(length < Pathfinder.maxAmountOfSteps) {
					this.paths.add(path);
				}
			}
		}
	}
	
}
