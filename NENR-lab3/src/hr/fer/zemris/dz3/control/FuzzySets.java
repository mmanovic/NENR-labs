package hr.fer.zemris.dz3.control;

import hr.fer.zemris.fuzzy.CalculatedFuzzySet;
import hr.fer.zemris.fuzzy.IFuzzySet;
import hr.fer.zemris.fuzzy.StandardFuzzySets;

public class FuzzySets {
	public static IFuzzySet FAST = new CalculatedFuzzySet(Domains.VELOCITY, StandardFuzzySets.gammaFunction(60, 70));
	public static IFuzzySet SLOW = new CalculatedFuzzySet(Domains.VELOCITY, StandardFuzzySets.lFunction(25, 40));
	public static IFuzzySet SPEED_UP = new CalculatedFuzzySet(Domains.ACCELERATION,
			StandardFuzzySets.lambdaFunction(55, 60, 65));
	public static IFuzzySet SLOW_DOWN = new CalculatedFuzzySet(Domains.ACCELERATION,
			StandardFuzzySets.lFunction(40, 50));
	public static IFuzzySet TURN_LEFT = new CalculatedFuzzySet(Domains.ANGLE,
			StandardFuzzySets.gammaFunction(150, 180));
	public static IFuzzySet TURN_RIGHT = new CalculatedFuzzySet(Domains.ANGLE, StandardFuzzySets.lFunction(0, 30));
	public static IFuzzySet NEAR_SHORE = new CalculatedFuzzySet(Domains.DISTANCE, StandardFuzzySets.lFunction(35, 50));
	public static IFuzzySet FAR_SHORE = new CalculatedFuzzySet(Domains.DISTANCE,
			StandardFuzzySets.gammaFunction(60, 70));

}
