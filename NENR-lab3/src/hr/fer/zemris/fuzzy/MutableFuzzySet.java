package hr.fer.zemris.fuzzy;

import java.util.Arrays;

public class MutableFuzzySet implements IFuzzySet {
	private IDomain domain;
	private double[] memberships;

	public MutableFuzzySet(IDomain domain) {
		super();
		this.domain = domain;
		this.memberships = new double[domain.getCardinality()];
	}

	public MutableFuzzySet(IDomain domain, double[] memberships) {
		super();
		this.domain = domain;
		this.memberships = memberships;
	}

	@Override
	public IDomain getDomain() {
		return domain;
	}

	@Override
	public double getValueAt(DomainElement element) {
		return memberships[domain.indexOfElement(element)];
	}

	public MutableFuzzySet set(DomainElement element, double value) {
		memberships[domain.indexOfElement(element)] = value;
		return this;
	}

	@Override
	public IFuzzySet cut(double minValue) {
		int n = memberships.length;
		double[] newValues = Arrays.copyOf(memberships, n);
		for (int i = 0; i < n; i++) {
			newValues[i] = Math.min(minValue, newValues[i]);
		}
		return new MutableFuzzySet(domain, newValues);
	}

	@Override
	public IFuzzySet scale(double scalar) {
		int n = memberships.length;
		double[] newValues = Arrays.copyOf(memberships, n);
		for (int i = 0; i < n; i++) {
			newValues[i] = scalar * newValues[i];
		}
		return new MutableFuzzySet(domain, newValues);
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("[");
		for (double member : memberships) {
			string.append(member + " ");
		}
		string.append("]");
		return string.toString();
	}

}
