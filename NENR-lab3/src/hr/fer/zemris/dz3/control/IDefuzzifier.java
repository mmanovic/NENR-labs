package hr.fer.zemris.dz3.control;

import hr.fer.zemris.fuzzy.IFuzzySet;

public interface IDefuzzifier {
	public double defuzzify(IFuzzySet set);
}
