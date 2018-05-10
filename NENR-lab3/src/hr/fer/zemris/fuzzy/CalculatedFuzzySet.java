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

	@Override
	public IFuzzySet cut(double minValue) {
		return new IFuzzySet() {

			@Override
			public IFuzzySet scale(double scalar) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public double getValueAt(DomainElement element) {
				return Math.min(minValue, function.valueAt(domain.indexOfElement(element)));
			}

			@Override
			public IDomain getDomain() {
				return domain;
			}

			@Override
			public IFuzzySet cut(double minValue) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String toString() {
				StringBuilder string = new StringBuilder();
				string.append("[");
				for (DomainElement element : domain) {
					string.append(getValueAt(element) + " ");
				}
				string.append("]");
				return string.toString();
			}
		};
	}

	@Override
	public IFuzzySet scale(double scalar) {
		return new IFuzzySet() {

			@Override
			public IFuzzySet scale(double scalar) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public double getValueAt(DomainElement element) {
				return function.valueAt(domain.indexOfElement(element)) * scalar;
			}

			@Override
			public IDomain getDomain() {
				return domain;
			}

			@Override
			public IFuzzySet cut(double minValue) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String toString() {
				StringBuilder string = new StringBuilder();
				string.append("[");
				for (DomainElement element : domain) {
					string.append(getValueAt(element) + " ");
				}
				string.append("]");
				return string.toString();
			}
		};
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("[");
		for (DomainElement element : domain) {
			string.append(function.valueAt(domain.indexOfElement(element)) + " ");
		}
		string.append("]");
		return string.toString();
	}

}
