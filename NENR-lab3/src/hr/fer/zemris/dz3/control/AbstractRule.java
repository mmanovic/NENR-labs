package hr.fer.zemris.dz3.control;

import java.util.List;

import hr.fer.zemris.fuzzy.IFuzzySet;

public abstract class AbstractRule {
	protected List<IFuzzySet> antecedents;
	protected IFuzzySet consenquent;

	public AbstractRule(List<IFuzzySet> antecedents, IFuzzySet consenquent) {
		super();
		this.antecedents = antecedents;
		this.consenquent = consenquent;
	}

	public abstract IFuzzySet fuzzySetAt(int[] values);

}
