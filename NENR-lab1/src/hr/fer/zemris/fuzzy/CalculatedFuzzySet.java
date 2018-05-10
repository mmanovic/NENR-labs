package hr.fer.zemris.fuzzy;

public class CalculatedFuzzySet implements IFuzzySet {
	private IIntUnaryFunction function;
	private IDomain domain;

	public CalculatedFuzzySet(IDomain domain, IIntUnaryFunction function) {
		super();
		this.function = function;
		this.domain = domain;
	}

	@Override
	public IDomain getDomain() {
		return domain;
	}

	@Override
	public double getValueAt(DomainElement element) {
		return function.valueAt(domain.indexOfElement(element));
	}

}
