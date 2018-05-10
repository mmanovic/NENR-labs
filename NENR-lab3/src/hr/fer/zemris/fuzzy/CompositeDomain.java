package hr.fer.zemris.fuzzy;

import java.util.Iterator;

public class CompositeDomain extends Domain {
	private SimpleDomain[] components;

	public CompositeDomain(SimpleDomain[] components) {
		super();
		this.components = components;
	}

	@Override
	public int getCardinality() {
		int cardinality = 1;
		for (SimpleDomain component : components) {
			cardinality *= component.getCardinality();
		}
		return cardinality;
	}

	@Override
	public IDomain getComponent(int index) {
		return components[index];
	}

	@Override
	public int getNumberOfComponents() {
		return components.length;
	}

	@Override
	public Iterator<DomainElement> iterator() {
		return new CompositeDomainIterator();
	}

	private class CompositeDomainIterator implements Iterator<DomainElement> {
		private int[] currs;
		private int index;
		private int cardinality;

		public CompositeDomainIterator() {
			int length = components.length;
			index = 0;
			currs = new int[length];
			for (int i = 0; i < length; i++) {
				currs[i] = components[i].getFirst();
			}
			this.cardinality = getCardinality();
		}

		@Override
		public boolean hasNext() {
			return index < cardinality;
		}

		@Override
		public DomainElement next() {
			int[] values = new int[components.length];
			int tmp = index;
			int i = 0;
			int mod = 0;
			while (i < components.length) {
				cardinality /= components[i].getCardinality();
				mod = tmp % cardinality;
				values[i] = tmp / cardinality + currs[i];
				i++;
				tmp = mod;
			}
			cardinality = getCardinality();
			index++;
			return new DomainElement(values);
		}
	}
}
