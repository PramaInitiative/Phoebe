package com.prama.phoebe;

import java.util.ArrayList;
import java.util.Scanner;

public class Phoebe {

	public static void main(String[] args) {
		System.out.println("PHOEBE DEV BUILD");
		System.out.println("Je vais te trouver un chemin");
		System.out.println("Donne-moi un ID de map");
		Scanner inputs = new Scanner(System.in);
		short input = inputs.nextShort();
		inputs.nextLine();
		ArrayList<ArrayList<Map>> paths = new Pathfinder(input).getPaths();
		for(byte i = 0; i < paths.size(); i++) {
			ArrayList<Map> path = paths.get(i);
			System.out.println("Chemin n°" + i);
			for(byte j = 0; j < path.size(); j++) {
				Map curMap = path.get(j);
				System.out.print("Map " + curMap.getMapID() + " (" + curMap.getNom() + " ; ");
			}
			System.out.println("");
			inputs.nextLine();
		}
		inputs.close();
	}

}
