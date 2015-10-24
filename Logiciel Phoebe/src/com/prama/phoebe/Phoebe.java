package com.prama.phoebe;

import java.util.Scanner;

public class Phoebe {

	public static void main(String[] args) {
		System.out.println("PHOEBE DEV BUILD");
		System.out.println("Gimme a Map ID");
		Scanner inputs = new Scanner(System.in);
		short input = inputs.nextShort();
		Map map = new Map(input);
		System.out.print("Next map : ");
		System.out.println(map.getNextMapID());
	}

}
