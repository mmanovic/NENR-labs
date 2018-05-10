package hr.fer.zemris.dz3.control;

import hr.fer.zemris.fuzzy.DomainElement;
import hr.fer.zemris.fuzzy.IFuzzySet;

public class COADefuzzifier implements IDefuzzifier {

	@Override
	public double defuzzify(IFuzzySet set) {
		double x = 0;
		double y = 0;
		for (DomainElement element : set.getDomain()) {
			double valueOfElement = set.getValueAt(element);
			y += valueOfElement;
			x += element.getComponentValue(0) * valueOfElement;
		}
		if (Math.abs(y) < 1E-9) {
			return 0;
		} else {
			return x / y;
		}
	}

}
