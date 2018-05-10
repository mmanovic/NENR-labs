package hr.fer.zemris.dz3.main;

import java.util.Scanner;

import hr.fer.zemris.dz3.control.AccelerationRuleBase;
import hr.fer.zemris.dz3.control.COADefuzzifier;
import hr.fer.zemris.dz3.control.Domains;
import hr.fer.zemris.dz3.control.FuzzySystem;
import hr.fer.zemris.dz3.control.HelmRuleBase;
import hr.fer.zemris.dz3.control.TypeOfMachine;
import hr.fer.zemris.fuzzy.CalculatedFuzzySet;
import hr.fer.zemris.fuzzy.Domain;
import hr.fer.zemris.fuzzy.DomainElement;
import hr.fer.zemris.fuzzy.IFuzzySet;
import hr.fer.zemris.fuzzy.StandardFuzzySets;

public class Demo1 {

	public static void main(String[] args) {
		FuzzySystem accSystem = new FuzzySystem(new AccelerationRuleBase(TypeOfMachine.MIN_MACHINE),
				new COADefuzzifier());
		FuzzySystem helmSystem = new FuzzySystem(new HelmRuleBase(TypeOfMachine.MIN_MACHINE), new COADefuzzifier());
		Scanner scanner = new Scanner(System.in);
		System.out.println("Unesi indeks pravila:");
		int index = scanner.nextInt();
		System.out.println("Unesite L, D, LK, DK, V, i S");
		int[] values = new int[6];
		for (int i = 0; i < 6; i++) {
			values[i] = scanner.nextInt();
		}
		// int[] values = new int[] { 50, 50, 450, 50, 30, 1 };
		IFuzzySet set = accSystem.getBase().getRule(index).fuzzySetAt(values);
		System.out.println(set.toString());
		System.out.println(new COADefuzzifier().defuzzify(set));

		System.out.println(helmSystem.deduce(values));
	}

}
