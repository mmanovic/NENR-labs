package hr.fer.zemris.dz4.models;

import java.util.Random;

public class Mutation {
	private double sigma;
	private Random random = new Random();

	public Mutation(double sigma) {
		super();
		this.sigma = sigma;
	}

	public Chromosome mutate(Chromosome x) {
		double[] values = x.getValues();
		int n = x.getLength();
		int i = random.nextInt(n);
		values[i] += random.nextGaussian() * sigma;

		return new Chromosome(x.getFunction(), values);
	}
}
