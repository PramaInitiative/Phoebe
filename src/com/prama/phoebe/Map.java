package com.prama.phoebe;

public class Map {
	
	// Liste des matrices (map o� m�ne la TP)
	public static short[] matrixArray = { 0,   0,   2,   0, 122, 123, 116, 117, 141, 142, 143, 148, 149, 150, 151, 152, 
										153, 154, 207, 132, 208, 208, 208, 125, 132, 208, 208, 208, 205, 140, 132, 125, 
										125,   0, 122, 112, 116, 117, 217, 218, 219, 123, 123, 243, 243,   0, 122, 113, 
										116, 117, 132, 208, 136, 208, 132, 208, 136, 208, 123, 155, 123, 132, 208, 208, 
										123,   0, 122, 220, 221, 116, 117, 138, 132, 138, 132, 208, 132, 135, 208, 208, 
										159, 170, 124, 124, 244, 123,   0, 122, 222, 223, 223, 224, 223, 224, 223, 224, 
										223, 223, 223, 223, 225, 116, 117, 209, 210, 207, 156, 157, 157, 158, 158, 157, 
										209, 210, 207, 125, 237, 165, 166, 167,   0, 206, 111, 116, 117, 181, 182, 123, 
										123, 125, 123, 123,   0, 115, 116, 117, 139, 172, 173, 174, 175, 176, 207, 177, 
										204, 125, 125, 123, 123, 158,   0, 116, 117, 122, 226, 227, 228, 203, 243, 244, 
										243, 243, 243, 125, 230,   0, 122, 114, 116, 117, 123, 123,   0, 116, 117, 144, 
										187, 183, 188, 184, 188, 185, 188, 186, 189, 190, 192, 191,   0, 116, 117, 122, 
										159, 157, 123, 123, 123,   5,   5,   6,   0, 234,   0,   7,   0,   8,   0,   9,
										 10,  11,  12,  13,  14,  15,  16,  17,  18,  19,  20,  21,  22,  23,   0,  24, 
										  0,  25,  26,  40,  35,  39,  31,  34,  31,  40,  42,  35,  35,  36,  34,  30,
										 41,  31,  35,   0,  43, 123,  95, 146,  78,  65};
	
	public static short matrix(short mapID) {
		if(mapID < 245) {
			return Map.matrixArray[mapID];
		}
		for(byte i = 0; i < Pathfinder.afterEntryPoints.length; i++) {
			if(Pathfinder.entryPoints[i] == mapID) {
				return Pathfinder.entryPoints[i];
			}
		}
		return 0; // Ne devrait jamais arriver !
	}
	
	// TODO Faire cette liste !!
	public static String[] noms = {};
	
	protected short mapID; // L'ID de la map
	protected short matrix; // L'ID de la map o� on sera TP
	protected String nom; // Le nom de la map
	
	public Map(short mapID) {
		this.mapID = mapID;
		this.matrix = Map.matrix(mapID);
	}
	
	// *** GETTERS ***
	public short getMapID() {
		return this.mapID;
	}
	
	public short getMatrix() {
		return this.matrix;
	}
	
	public String getNom() {
		//return this.nom;
		return "nomMap";
	}
}
