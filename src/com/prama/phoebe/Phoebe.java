package com.prama.phoebe;

import java.util.ArrayList;
import java.util.Scanner;

public final class Phoebe {
	
	private static Scanner inputs = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("PHOEBE DEV BUILD");
		System.out.println("Je vais te trouver un chemin");
		System.out.println("Donne-moi un ID de map");
		short input = Phoebe.inputs.nextShort();
		inputs.nextLine();
		Phoebe.printPaths(new Pathfinder(input));
		inputs.close();
		
		System.out.println("C'�tait Phoebe, rapport termin�. Salut !");
	}
	
	public static void printPaths(Pathfinder pathFinder) {
		ArrayList<Path> paths = pathFinder.getPaths();
		if(paths.size() == 0) {
			System.out.println("Aucun chemin n'a �t� trouv� pour un maximum de " + Path.maxAmountOfSteps + " �tapes.");
			System.out.println("Voulez-vous essayer avec un nouveau maximum ?");
			System.out.println("Entrez 0 pour NON, ou un autre nombre (maximum 127) pour r�-essayer.");
			byte nouveauMax = Phoebe.inputs.nextByte();
			if(nouveauMax > 0) {
				Path.maxAmountOfSteps = nouveauMax;
				Phoebe.printPaths(new Pathfinder(pathFinder.getTargetMap()));
			}
		} else {
			System.out.println("Des chemins ont �t� trouv�s.");
			System.out.println("Nombre minimal d'�tapes : " + pathFinder.getMinAmountOfSteps());
			for (byte i = 0; i < paths.size(); i++) {
				Path path = paths.get(i);
				System.out.println("Chemin n�" + i + ", " + path.steps() + " �tapes.");
				System.out.println(path.toString());
				System.out.println("");
			}
		}
		
	}

}
