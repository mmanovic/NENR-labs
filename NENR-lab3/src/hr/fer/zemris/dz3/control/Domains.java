package hr.fer.zemris.dz3.control;

import hr.fer.zemris.fuzzy.IDomain;
import hr.fer.zemris.fuzzy.SimpleDomain;

public class Domains {

	public static IDomain VELOCITY = new SimpleDomain(0, 100);
	public static IDomain ACCELERATION = new SimpleDomain(-50, 50);
	public static IDomain ANGLE = new SimpleDomain(-91, 91);
	public static IDomain DISTANCE = new SimpleDomain(0, 1301);
}
