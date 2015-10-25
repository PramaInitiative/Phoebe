package com.prama.phoebe;

import java.util.ArrayList; // Il faut des listes dont la taile n'est pas connue à l'avance.
import java.util.ListIterator; // Plus pratique pour parcourir. Peut-être pas ?

public class Pathfinder { // Trouve tous les chemins pour se rendre à une map donnée.

	public static byte maxAmountOfSteps = 20; // Option modifiable au gré de l'utilisateur.
	
	public static short[] entryPoints = {4, 8, 58, 95, 99, 257, 305, 338, 513, 553}; // Liste des points d'entrée pour la TP.
	// NE PAS SUPPRIMER, C'EST IMPORTANT !!
	public static short[] afterEntryPoints = {122, 141, 123, 224, 223, 123, 95, 146, 78, 65}; // Liste des maps où on est téléporté après l'Entry Point.


	// On n'acceptera que l'ID d'une map pour le constructeur.
	// L'idée de proposer un constructeur avec un objet Map a été abandonnée.
	public Pathfinder(short targetMapID) {
		
		for(short curMapID : Pathfinder.entryPoints) { // Pour chaque point d'entrée,
			// Calcul du chemin
			
			ArrayList<Map> path = new ArrayList<Map>(); // Le chemin qui sera poussé (ou non) sur la liste.
			path.add(new Map(curMapID)); // On commence par le point d'entrée.
			byte length = 1; // Plus rapide que de recalculer la longueur de 'path', non ?
			short mapID = curMapID; // On doit préserver curMapID (voir commit 4cc7693216240efe9c8f5307e41cc1544c6a1351)
			while (mapID != targetMapID && length < Pathfinder.maxAmountOfSteps) { // Soit on a dépassé le nombre de pas alloués, soit on est arrivé à destination.
				mapID = Map.matrix(mapID); // On passe à la map suivante.
				path.add(new Map(mapID)); // On a une étape supplémentaire.
				length++; // Donc une de plus !
			}
			
			
			// Ajout du chemin
			
			// NB : avant ce commit, si le chemin comprenait exactement maxAmountOfSteps étapes, il était rejeté.
			// Ce n'est plus le cas.
			if (mapID == targetMapID) { // On est arrivé au bout !
				if (this.paths.size() == 0) { // Si la liste est vide, on ajoute de toute façon.
					this.paths.add(path); // Sinon le premier test (i = 0) fait crasher.
				} else { // On a au moins un élément.
					byte i = 0;
					ListIterator<ArrayList<Map>> iter = this.paths.listIterator();
					while (iter.hasNext() && iter.next().size() <= path.size()) { // Tant qu'il y a des chemins et que ceux-ci sont plus courts que l'actuel,
							i++; // On passe au suivant.
					}
					this.paths.add(i, path); // On ajoute le nouveau chemin.
				}
			}
		}
	}


	protected ArrayList<ArrayList<Map>> paths = new ArrayList<ArrayList<Map>>();

	// *** GETTERS ***
	public int getMinAmountOfSteps() {
		return this.paths.get(0).size();
	}

	public ArrayList<ArrayList<Map>> getPaths() {
		return this.paths;
	}

}
