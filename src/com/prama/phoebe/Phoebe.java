package com.prama.phoebe;

import java.util.ArrayList;

public final class Phoebe {
	
	static PhoebeWindow window;
	static ArrayList<Path> paths;
	
	static short selectedTown = 0;
	
	public static void main(String[] args) {
		window = new PhoebeWindow();
		
		for(int i = 0; i < 559; i++) {	// Initialisation : on affiche toutes les localisations possibles
			window.townLocationsVector.add(i);
		}
		
		window.drawWindow();
	} 

}