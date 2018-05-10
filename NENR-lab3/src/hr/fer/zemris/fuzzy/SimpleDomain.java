package hr.fer.zemris.fuzzy;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleDomain extends Domain {
	private int first;
	private int last;

	public SimpleDomain(int first, int last) {
		super();
		this.first = first;
		this.last = last;
	}

	@Override
	public int getCardinality() {
		return last - first;
	}

	public IDomain getComponent(int index) {
		return this;
	}

	@Override
	public int getNumberOfComponents() {
		return 1;
	}

	@Override
	public Iterator<DomainElement> iterator() {
		return new Iterator<DomainElement>() {
			private int curr = first;

			@Override
			public boolean hasNext() {
				return curr < last;
			}

			@Override
			public DomainElement next() {
				if (hasNext()) {
					return DomainElement.of(curr++);
				} else {
					throw new NoSuchElementException();
				}
			}
		};
	}

	public int getFirst() {
		return first;
	}

	public int getLast() {
		return last;
	}

}
