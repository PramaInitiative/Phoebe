package com.prama.phoebe;

import java.util.ArrayList; // Il faut des listes dont la taile n'est pas connue � l'avance.
import java.util.ListIterator; // Plus pratique pour parcourir. Peut-�tre pas ?

public class Pathfinder { // Trouve tous les chemins pour se rendre � une map donn�e.
	
	public static short[] entryPoints = {4, 8, 11, 58, 95, 99, 257, 305, 338, 513, 553}; // Liste des points d'entr�e pour la TP.


	// On n'acceptera que l'ID d'une map pour le constructeur.
	// L'id�e de proposer un constructeur avec un objet Map a �t� abandonn�e.
	public Pathfinder(short targetMapID) {
		
		this.targetMapID = targetMapID;
		
		for(short curMapID : Pathfinder.entryPoints) { // Pour chaque point d'entr�e,
			// Calcul du chemin
			Path path = new Path(curMapID, targetMapID);

			// Ajout du chemin
			if (path.hasReachedDestination()) { // On est arriv� au bout !
				if (this.paths.size() == 0) { // Si la liste est vide, on ajoute de toute fa�on.
					this.paths.add(path); // Sinon le premier test (i = 0) fait crasher.

				} else { // On a au moins un �l�ment.
					byte i = 0;
					ListIterator<Path> iter = this.paths.listIterator();

					// iter.hasNext() doit �tre plac� avant iter.next() et les deux doivent �tre s�par�s par un && et pas un &, sinon iter.next() provoquera une java.util.NoSuchElementException !
					while (iter.hasNext() && iter.next().steps() <= path.steps()) { // Tant qu'il y a des chemins et que ceux-ci sont plus courts que l'actuel,
							i++; // On passe au suivant.
					}
					this.paths.add(i, path); // On ajoute le nouveau chemin.
				}
			}
		}
	}


	private short targetMapID;
	protected ArrayList<Path> paths = new ArrayList<Path>();

	// *** GETTERS ***
	public int getMinAmountOfSteps() {
		return this.paths.get(0).steps();
	}

	public ArrayList<Path> getPaths() {
		return this.paths;
	}
	
	public short getTargetMap() {
		return this.targetMapID;
	}

}
