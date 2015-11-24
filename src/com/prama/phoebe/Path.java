package com.prama.phoebe;

import java.util.ArrayList;
import java.util.Vector;

public class Path {

	public static byte maxAmountOfSteps = 20; // Option modifiable au gré de l'utilisateur.
	
	
	public Path(short entryMapID, short destinationMapID) {
		path.add(new Map(entryMapID)); // On commence par le point d'entrée.
		
		byte length = 1; // Plus rapide que de recalculer la longueur de 'path', non ?
		short mapID = entryMapID; // On doit préserver curMapID (voir commit 4cc7693216240efe9c8f5307e41cc1544c6a1351)
		
		while (mapID != destinationMapID && length < Path.maxAmountOfSteps) { // Soit on a dépassé le nombre de pas alloués, soit on est arrivé à destination.
			mapID = Map.matrixArray[mapID]; // On passe à la map suivante.
			path.add(new Map(mapID)); // On a une étape supplémentaire.
			length++; // Donc une de plus !
		}
		
		this.numberOfSteps = length;
		this.reachedDestination = this.reachedDestination || mapID == destinationMapID; // Si on est arrivé au bout, le chemin est arrivé au but.
	}
	
	protected boolean reachedDestination = false;
	private ArrayList<Map> path = new ArrayList<Map>();
	private int numberOfSteps;
	
	public int steps() {
		return this.numberOfSteps;
	}
	
	public Map getMap(int mapIndex) {
		return path.get(mapIndex);
	}
	
	public boolean hasReachedDestination() {
		return this.reachedDestination;
	}
	
	public Vector<Integer> toVector() {
		Vector<Integer> ret = new Vector<Integer>();
		
		for(byte i = 0; i < this.numberOfSteps; i++) {
			Map curMap = this.path.get(i);
			ret.add(Short.toUnsignedInt(curMap.getMapID()));
		}
		
		return ret;
	}
	
	public String toString() {
		String ret = "";
		for(byte i = 0; i < this.numberOfSteps; i++) {
			Map curMap = this.path.get(i);
			ret = ret.concat(" ; Map " + curMap.getMapID() + ", " + curMap.getNom());
		}
		return ret.substring(3);
	}
}