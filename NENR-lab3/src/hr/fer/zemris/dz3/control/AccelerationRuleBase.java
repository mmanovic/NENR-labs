package hr.fer.zemris.dz3.control;

import java.util.ArrayList;
import java.util.Arrays;

import hr.fer.zemris.fuzzy.IFuzzySet;

public class AccelerationRuleBase extends AbstractRuleBase {
	public AccelerationRuleBase(TypeOfMachine type) {
		super(type);
		createRules();
	}

	private void createRules() {
		IFuzzySet[] a1 = { null, null, null, null, FuzzySets.SLOW, null };
		IFuzzySet b1 = FuzzySets.SPEED_UP;
		addTypeRule(new ArrayList<>(Arrays.asList(a1)), b1);

		IFuzzySet[] a2 = { null, null, null, null, FuzzySets.FAST, null };
		IFuzzySet b2 = FuzzySets.SLOW_DOWN;
		addTypeRule(new ArrayList<>(Arrays.asList(a2)), b2);

//		IFuzzySet[] a3 = { null, null, null, FuzzySets.NEAR_SHORE, null, null };
//		IFuzzySet b3 = FuzzySets.SLOW_DOWN;
//		addTypeRule(new ArrayList<>(Arrays.asList(a3)), b3);
//
//		IFuzzySet[] a4 = { null, null, FuzzySets.NEAR_SHORE, null, null, null };
//		IFuzzySet b4 = FuzzySets.SLOW_DOWN;
//		addTypeRule(new ArrayList<>(Arrays.asList(a4)), b4);

		IFuzzySet[] a5 = { FuzzySets.FAR_SHORE, FuzzySets.FAR_SHORE, FuzzySets.FAR_SHORE, FuzzySets.FAR_SHORE, null,
				null };
		IFuzzySet b5 = FuzzySets.SPEED_UP;
		addTypeRule(new ArrayList<>(Arrays.asList(a5)), b5);

	}

}
