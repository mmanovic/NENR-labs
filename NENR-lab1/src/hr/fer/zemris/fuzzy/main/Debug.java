package hr.fer.zemris.fuzzy.main;

import hr.fer.zemris.fuzzy.DomainElement;
import hr.fer.zemris.fuzzy.IDomain;
import hr.fer.zemris.fuzzy.IFuzzySet;

public class Debug {

	public static void print(IDomain domain, String headingText) {
		if (headingText != null) {
			System.out.println(headingText);
		}
		for (DomainElement e : domain) {
			System.out.println("Element domene: " + e);
		}
		System.out.println("Kardinalitet domene je: " + domain.getCardinality());
		System.out.println();
	}

	public static void print(IFuzzySet set, String headingText) {
		if (headingText != null) {
			System.out.println(headingText);
		}
		for (DomainElement e : set.getDomain()) {

			System.out.print("d(" + e.getComponentValue(0) + ")=");
			System.out.format("%.6f\n", set.getValueAt(e));
		}
		System.out.println();
	}

}
