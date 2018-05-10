package hr.fer.zemris.fuzzy;

public class MutableFuzzySet implements IFuzzySet {
	private IDomain domain;
	private double[] memberships;

	public MutableFuzzySet(IDomain domain) {
		super();
		this.domain = domain;
		this.memberships = new double[domain.getCardinality()];
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

}
