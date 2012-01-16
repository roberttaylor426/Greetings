package org.robobinding.presentationmodel;

import java.util.ArrayList;
import java.util.List;

enum Salutation {
	Mr, Mrs, Miss, Dr, Prof, Undecided;

	public static List<Salutation> forMales() {
		List<Salutation> salutations = new ArrayList<Salutation>();
		salutations.add(Mr);
		salutations.add(Dr);
		salutations.add(Prof);
		return salutations;
	}
	
	public static List<Salutation> forFemales() {
		List<Salutation> salutations = new ArrayList<Salutation>();
		salutations.add(Mrs);
		salutations.add(Miss);
		salutations.add(Dr);
		salutations.add(Prof);
		return salutations;
	}
}