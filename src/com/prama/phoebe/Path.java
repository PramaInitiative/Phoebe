package com.prama.phoebe;

import java.util.ArrayList;
import java.util.Vector;

public class Path {

	public static byte maxAmountOfSteps = 20; // Option modifiable au gr� de l'utilisateur.
	
	
	public Path(short entryMapID, short destinationMapID) {
		path.add(new Map(entryMapID)); // On commence par le point d'entr�e.
		
		byte length = 1; // Plus rapide que de recalculer la longueur de 'path', non ?
		short mapID = entryMapID; // On doit pr�server curMapID (voir commit 4cc7693216240efe9c8f5307e41cc1544c6a1351)
		
		while (mapID != destinationMapID && length < Path.maxAmountOfSteps) { // Soit on a d�pass� le nombre de pas allou�s, soit on est arriv� � destination.
			mapID = Map.matrixArray[mapID]; // On passe � la map suivante.
			path.add(new Map(mapID)); // On a une �tape suppl�mentaire.
			length++; // Donc une de plus !
		}
		
		this.numberOfSteps = length;
		this.reachedDestination = this.reachedDestination || mapID == destinationMapID; // Si on est arriv� au bout, le chemin est arriv� au but.
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