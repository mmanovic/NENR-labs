package hr.fer.zemris.fuzzy;

public class Relations {
	public static double EPSILON = 1E-9;

	public static boolean isUtimesURelation(IFuzzySet relation) {
		if (relation.getDomain().getNumberOfComponents() != 2) {
			return false;
		}
		if (!relation.getDomain().getComponent(0).equals(relation.getDomain().getComponent(1))) {
			return false;
		}
		return true;
	}

	public static boolean isSymmetric(IFuzzySet relation) {
		if (!isUtimesURelation(relation)) {
			return false;
		}
		IDomain domain = relation.getDomain();
		for (DomainElement element : domain) {
			int[] values = new int[2];
			values[0] = element.getComponentValue(1);
			values[1] = element.getComponentValue(0);
			DomainElement symmetric = new DomainElement(values);
			if (Math.abs(relation.getValueAt(element) - relation.getValueAt(symmetric)) > EPSILON) {
				return false;
			}
		}
		return true;
	}

	public static boolean isReflexive(IFuzzySet relation) {
		if (!isUtimesURelation(relation)) {
			return false;
		}
		IDomain domain = relation.getDomain();
		for (DomainElement element : domain) {
			if (element.getComponentValue(0) == element.getComponentValue(1)) {
				if (relation.getValueAt(element) != 1) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isMaxMinTransitive(IFuzzySet relation) {
		if (!isUtimesURelation(relation)) {
			return false;
		}
		IDomain domain = relation.getDomain();
		for (DomainElement element1 : domain) {
			for (DomainElement element2 : domain) {
				if (element1.getComponentValue(1) != element2.getComponentValue(0)) {
					continue;
				}
				int[] values = new int[2];
				values[0] = element1.getComponentValue(0);
				values[1] = element2.getComponentValue(1);
				if (!(relation.getValueAt(new DomainElement(values)) >= Math.min(relation.getValueAt(element1),
						relation.getValueAt(element2)))) {
					return false;
				}
			}
		}
		return true;
	}

	public static IFuzzySet compositionOfBinaryRelations(IFuzzySet set1, IFuzzySet set2) {
		IDomain domain1 = set1.getDomain();
		IDomain domain2 = set2.getDomain();
		if (domain1.getNumberOfComponents() != 2 || domain2.getNumberOfComponents() != 2) {
			throw new IllegalArgumentException("Relations are not binary.");
		}
		SimpleDomain sDomain1 = (SimpleDomain) domain1.getComponent(0);
		SimpleDomain sDomain2 = (SimpleDomain) domain2.getComponent(0);
		SimpleDomain sDomain3 = (SimpleDomain) domain2.getComponent(1);
		MutableFuzzySet composition = new MutableFuzzySet(
				new CompositeDomain(new SimpleDomain[] { sDomain1, sDomain3 }));
		for (DomainElement element1 : sDomain1) {
			for (DomainElement element3 : sDomain3) {
				double maxValue = 0;
				for (DomainElement element2 : sDomain2) {
					maxValue = Math.max(maxValue, Math.min(
							set1.getValueAt(
									DomainElement.of(element1.getComponentValue(0), element2.getComponentValue(0))),
							set2.getValueAt(
									DomainElement.of(element2.getComponentValue(0), element3.getComponentValue(0)))));
				}
				composition.set(DomainElement.of(element1.getComponentValue(0), element3.getComponentValue(0)),
						maxValue);
			}
		}
		return composition;
	}

	public static boolean isFuzzyEquivalence(IFuzzySet relation) {
		return Relations.isReflexive(relation) && Relations.isMaxMinTransitive(relation)
				&& Relations.isSymmetric(relation);

	}
}
