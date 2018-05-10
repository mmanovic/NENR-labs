package hr.fer.zemris.dz7.ga;

import java.util.Random;

public class UniformCrossover implements ICrossover {
	private Random random = new Random();

	@Override
	public Chromosome cross(Chromosome x, Chromosome y) {
		int size = x.getLength();
		double[] values = new double[size];
		double[] xValues = x.getValues();
		double[] yValues = y.getValues();
		for (int i = 0; i < size; i++) {
			if (random.nextDouble() < 0.5) {
				values[i] = xValues[i];
			} else {
				values[i] = yValues[i];
			}
		}
		return new Chromosome(values);
	}
}
