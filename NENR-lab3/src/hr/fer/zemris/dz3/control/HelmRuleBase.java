package hr.fer.zemris.dz3.control;

import java.util.ArrayList;
import java.util.Arrays;

import hr.fer.zemris.fuzzy.IFuzzySet;

public class HelmRuleBase extends AbstractRuleBase {

	public HelmRuleBase(TypeOfMachine type) {
		super(type);
		createRules();
	}

	private void createRules() {

		IFuzzySet[] a1 = { null, null, FuzzySets.NEAR_SHORE, null, null, null };
		IFuzzySet b1 = FuzzySets.TURN_RIGHT;
		addTypeRule(new ArrayList<>(Arrays.asList(a1)), b1);

		IFuzzySet[] a2 = { null, null, null, FuzzySets.NEAR_SHORE, null, null };
		IFuzzySet b2 = FuzzySets.TURN_LEFT;
		addTypeRule(new ArrayList<>(Arrays.asList(a2)), b2);


		// IFuzzySet[] a4 = { FuzzySets.NEAR_SHORE, null, null, null, null, null
		// };
		// IFuzzySet b4 = FuzzySets.TURN_RIGHT;
		// addTypeRule(new ArrayList<>(Arrays.asList(a4)), b4);
		//
		// IFuzzySet[] a5 = { null, FuzzySets.NEAR_SHORE, null, null, null, null
		// };
		// IFuzzySet b5 = FuzzySets.TURN_LEFT;
		// addTypeRule(new ArrayList<>(Arrays.asList(a5)), b5);

	}

}
