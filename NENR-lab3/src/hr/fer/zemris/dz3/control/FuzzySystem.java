package hr.fer.zemris.dz3.control;

import java.util.ArrayList;

import hr.fer.zemris.fuzzy.IFuzzySet;
import hr.fer.zemris.fuzzy.Operations;

public class FuzzySystem {
	private AbstractRuleBase base;
	private IDefuzzifier defuzzifier;

	public FuzzySystem(AbstractRuleBase base, IDefuzzifier defuzzifier) {
		super();
		this.base = base;
		this.defuzzifier = defuzzifier;
	}

	public AbstractRuleBase getBase() {
		return base;
	}

	public IDefuzzifier getDefuzzifier() {
		return defuzzifier;
	}

	public double deduce(int[] values) {

		ArrayList<IFuzzySet> consequences = new ArrayList<>();
		for (AbstractRule rule : base.rules)
			consequences.add(rule.fuzzySetAt(values));

		IFuzzySet union = consequences.get(0);
		for (int i = 1; i < consequences.size(); i++)
			union = Operations.binaryOperation(union, consequences.get(i), Operations.zadehOr());

		return defuzzifier.defuzzify(union);
	}

}
