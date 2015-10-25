package com.prama.phoebe;

import java.util.ArrayList; // Il faut des listes dont la taile n'est pas connue à l'avance.
import java.util.ListIterator; // Plus pratique pour parcourir. Peut-être pas ?

public class Pathfinder { // Trouve tous les chemins pour se rendre à une map donnée.

	public static byte maxAmountOfSteps = 20; // Option modifiable au gré de l'utilisateur.


	// On n'acceptera que l'ID d'une map pour le constructeur.
	// L'idée de proposer un constructeur avec un objet Map a été abandonnée.
	public Pathfinder(short targetMapID) {
		
		for(short curMapID = 0; curMapID < Map.matrixArray.length; curMapID++) { // Pour chaque map,
			if(curMapID != targetMapID) { // différente de la map de destination (évidemment) :
				
				
				// Calcul du chemin
				
				ArrayList<Map> path = new ArrayList<Map>(); // Le chemin qui sera poussé (ou non) sur la liste.
				path.add(new Map(curMapID)); // On commence par la map source.
				byte length = 1; // Plus rapide que de recalculer la longueur de path, non ?
				short mapID = curMapID; // On doit préserver curMapID (voir commit 4cc7693216240efe9c8f5307e41cc1544c6a1351)
				while(mapID != targetMapID && length < Pathfinder.maxAmountOfSteps) { // Soit on a dépassé le nombre de pas alloués, soit on est arrivé à destination.
					mapID = Map.matrixArray[mapID]; // On passe à la map suivante.
					path.add(new Map(mapID)); // On a une étape supplémentaire.
					length++; // Donc une de plus !
				}
				
				
				// Ajout du chemin
				
				// NB : avant ce commit, si le chemin comprenait exactement maxAmountOfSteps étapes, il était rejeté.
				// Ce n'est plus le cas.
				if(mapID == targetMapID) { // On est arrivé au bout !
					if(this.paths.size() == 0) { // Si la liste est vide, on ajoute de toute façon.
						this.paths.add(path); // Sinon le premier test (i = 0) fait crasher.
					} else { // On a au moins un élément.
						byte i = 0;
						ListIterator<ArrayList<Map>> iter = this.paths.listIterator();
						while(iter.hasNext() && iter.next().size() <= path.size()) { // Tant qu'il y a des chemins et que ceux-ci sont plus courts que l'actuel,
								i++; // On passe au suivant.
						}
						this.paths.add(i, path); // On ajoute le nouveau chemin.
					}
				}
			}
		}
		
		// Détermination de la longueur du plus petit chemin.
		this.minAmountOfsteps = (byte)this.paths.get(0).size();
		
	}


	protected byte minAmountOfsteps;
	protected ArrayList<ArrayList<Map>> paths = new ArrayList<ArrayList<Map>>();

	// *** GETTERS ***
	public short getMinAmountOfSteps() {
		return this.minAmountOfsteps;
	}

	public ArrayList<ArrayList<Map>> getPaths() {
		return this.paths;
	}

}
