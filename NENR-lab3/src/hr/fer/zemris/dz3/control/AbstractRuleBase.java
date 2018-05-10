package hr.fer.zemris.dz3.control;

import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.fuzzy.IFuzzySet;

public abstract class AbstractRuleBase {
	protected List<AbstractRule> rules;
	protected TypeOfMachine type;

	public AbstractRuleBase(TypeOfMachine type) {
		super();
		this.type = type;
		this.rules = new ArrayList<>();
	}

	protected void addTypeRule(List<IFuzzySet> antecedents, IFuzzySet consenquent) {
		if (type.equals(TypeOfMachine.MIN_MACHINE)) {
			rules.add(new MinRule(antecedents, consenquent));
		} else if (type.equals(TypeOfMachine.PRODUCT_MACHINE)) {
			rules.add(new ProductRule(antecedents, consenquent));
		}
	}

	protected void setRules(List<AbstractRule> rules) {
		this.rules = rules;
	}

	public void addRule(AbstractRule rule) {
		rules.add(rule);
	}

	public List<AbstractRule> getRules() {
		return rules;
	}

	public AbstractRule getRule(int index) {
		return rules.get(index);
	}
}
