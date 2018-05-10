package hr.fer.zemris.dz3.control;

import java.util.List;

import hr.fer.zemris.fuzzy.DomainElement;
import hr.fer.zemris.fuzzy.IFuzzySet;

public class ProductRule extends AbstractRule {

	public ProductRule(List<IFuzzySet> antecedents, IFuzzySet consenquent) {
		super(antecedents, consenquent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IFuzzySet fuzzySetAt(int[] values) {
		double scalar = 1;
		int n = values.length;
		for (int i = 0; i < n; i++) {
			if (antecedents.get(i) != null) {
				scalar = scalar * antecedents.get(i).getValueAt(DomainElement.of(values[i]));
			}
		}
		return consenquent.scale(scalar);
	}

}
