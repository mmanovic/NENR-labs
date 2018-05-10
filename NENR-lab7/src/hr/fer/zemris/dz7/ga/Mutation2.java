package hr.fer.zemris.dz7.ga;

import java.util.Random;

public class Mutation2 implements IMutation {
	private double sigma;
	private double mutationRate;
	private Random random = new Random();

	public Mutation2(double mutationRate, double sigma) {
		super();
		this.mutationRate = mutationRate;
		this.sigma = sigma;
	}

	public void mutate(Chromosome x) {
		double[] values = x.getValues();
		int n = values.length;
		boolean changed = false;
		for (int i = 0; i < n; i++) {
			if (random.nextDouble() < mutationRate) {
				values[i] = random.nextGaussian() * sigma;
				changed = true;
			}
		}
		if (!changed) {
			values[random.nextInt(n)] += random.nextGaussian() * sigma;
		}
		x.setValues(values);
	}

}
