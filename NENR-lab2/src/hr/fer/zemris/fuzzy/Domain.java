package hr.fer.zemris.fuzzy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Domain implements IDomain {

	public static IDomain intRange(int first, int last) {
		return new SimpleDomain(first, last);
	}

	public static Domain combine(IDomain first, IDomain second) {
		List<SimpleDomain> components = new ArrayList<>();
		if (first instanceof SimpleDomain) {
			components.add((SimpleDomain) first);
		} else {
			CompositeDomain tmp = (CompositeDomain) first;
			int length = tmp.getNumberOfComponents();
			for (int i = 0; i < length; i++) {
				components.add((SimpleDomain) tmp.getComponent(i));
			}
		}

		if (second instanceof SimpleDomain) {
			components.add((SimpleDomain) second);
		} else {
			CompositeDomain tmp = (CompositeDomain) second;
			int length = tmp.getNumberOfComponents();
			for (int i = 0; i < length; i++) {
				components.add((SimpleDomain) tmp.getComponent(i));
			}
		}

		SimpleDomain[] newComponents = new SimpleDomain[components.size()];
		newComponents = components.toArray(newComponents);
		return new CompositeDomain(newComponents);
	}

	@Override
	public int indexOfElement(DomainElement element) {
		int index = 0;
		for (DomainElement tmp : this) {
			if (tmp.equals(element))
				return index;
			index++;
		}
		return -1;
	}

	@Override
	public DomainElement elementForIndex(int index) {
		for (DomainElement element : this) {
			if (index == 0) {
				return element;
			}
			index--;
		}
		return null;
	}

}
