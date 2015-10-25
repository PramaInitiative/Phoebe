package com.prama.phoebe;

import java.util.ArrayList; // Il faut des listes dont la taile n'est pas connue � l'avance.
import java.util.ListIterator; // Plus pratique pour parcourir. Peut-�tre pas ?

public class Pathfinder { // Trouve tous les chemins pour se rendre � une map donn�e.

	public static byte maxAmountOfSteps = 20; // Option modifiable au gr� de l'utilisateur.
	
	public static short[] entryPoints = {4, 8, 58, 95, 99, 257, 305, 338, 513, 553}; // Liste des points d'entr�e pour la TP.
	// NE PAS SUPPRIMER, C'EST IMPORTANT !!
	public static short[] afterEntryPoints = {122, 141, 123, 224, 223, 123, 95, 146, 78, 65}; // Liste des maps o� on est t�l�port� apr�s l'Entry Point.


	// On n'acceptera que l'ID d'une map pour le constructeur.
	// L'id�e de proposer un constructeur avec un objet Map a �t� abandonn�e.
	public Pathfinder(short targetMapID) {
		
		for(short curMapID : Pathfinder.entryPoints) { // Pour chaque point d'entr�e,
			// Calcul du chemin
			
			ArrayList<Map> path = new ArrayList<Map>(); // Le chemin qui sera pouss� (ou non) sur la liste.
			path.add(new Map(curMapID)); // On commence par le point d'entr�e.
			byte length = 1; // Plus rapide que de recalculer la longueur de 'path', non ?
			short mapID = curMapID; // On doit pr�server curMapID (voir commit 4cc7693216240efe9c8f5307e41cc1544c6a1351)
			while (mapID != targetMapID && length < Pathfinder.maxAmountOfSteps) { // Soit on a d�pass� le nombre de pas allou�s, soit on est arriv� � destination.
				mapID = Map.matrix(mapID); // On passe � la map suivante.
				path.add(new Map(mapID)); // On a une �tape suppl�mentaire.
				length++; // Donc une de plus !
			}
			
			
			// Ajout du chemin
			
			// NB : avant ce commit, si le chemin comprenait exactement maxAmountOfSteps �tapes, il �tait rejet�.
			// Ce n'est plus le cas.
			if (mapID == targetMapID) { // On est arriv� au bout !
				if (this.paths.size() == 0) { // Si la liste est vide, on ajoute de toute fa�on.
					this.paths.add(path); // Sinon le premier test (i = 0) fait crasher.
				} else { // On a au moins un �l�ment.
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
