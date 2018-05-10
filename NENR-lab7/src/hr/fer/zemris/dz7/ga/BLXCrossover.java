package hr.fer.zemris.dz7.ga;

import java.util.Random;

public class BLXCrossover implements ICrossover {
	private Random random = new Random();
	private double alpha;

	public BLXCrossover(double alpha) {
		super();
		this.alpha = alpha;
	}

	public Chromosome cross(Chromosome x, Chromosome y) {
		int size = x.getLength();
		double[] values = new double[size];
		double[] xValues = x.getValues();
		double[] yValues = y.getValues();
		for (int i = 0; i < size; i++) {
			double cmin = Double.min(xValues[i], yValues[i]);
			double cmax = Double.max(xValues[i], yValues[i]);
			double interval = alpha * (cmax - cmin);
			values[i] = random.nextDouble() * (cmax - cmin + 2 * interval) + cmin - interval;
		}
		return new Chromosome(values);
	}
}
