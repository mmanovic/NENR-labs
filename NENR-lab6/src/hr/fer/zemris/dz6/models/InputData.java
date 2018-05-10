package hr.fer.zemris.dz6.models;

/**
 * @author Mato
 *
 */
public class InputData {
	public double x;
	public double y;
	public double f;

	public InputData(double x, double y) {
		super();
		this.x = x;
		this.y = y;
		this.f = (Math.pow(x - 1, 2) + Math.pow(y + 2, 2) - 5 * x * y + 3) * Math.pow(Math.cos(x / 5), 2);
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ") f=" + f;
	}

}
