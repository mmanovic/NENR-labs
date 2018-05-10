package hr.fer.zemris.dz3.control;

import java.util.List;

import hr.fer.zemris.fuzzy.DomainElement;
import hr.fer.zemris.fuzzy.IFuzzySet;

public class MinRule extends AbstractRule {

	public MinRule(List<IFuzzySet> antecedents, IFuzzySet consenquent) {
		super(antecedents, consenquent);
		// TODO Auto-generated constructor stub
	}
	
	public IFuzzySet fuzzySetAt(int[] values) {
		double minValue = 1;
		int n = values.length;
		for (int i = 0; i < n; i++) {
			if (antecedents.get(i) != null) {
				minValue = Math.min(minValue, antecedents.get(i).getValueAt(DomainElement.of(values[i])));
			}
		}
		return consenquent.cut(minValue);
	}
	
}
