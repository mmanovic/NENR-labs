package hr.fer.zemris.dz7.ga;

import java.util.Random;

public class Chromosome implements Comparable<Chromosome> {
	public static double INIT_MIN = -1;
	public static double INIT_MAX = 1;
	private double[] values;
	private double fitness;

	public Chromosome(double[] values) {
		super();
		this.values = values;
	}

	public Chromosome(int n) {
		this.values = new double[n];
		Random random = new Random();
		for (int i = 0; i < n; i++) {
			values[i] = random.nextDouble() * (INIT_MAX - INIT_MIN) + INIT_MIN;
		}
	}

	public void setValue(int index, double value) {
		values[index] = value;
	}

	public void setValues(double[] values) {
		this.values = values;
	}

	public double getValue(int index) {
		return values[index];
	}

	public double[] getValues() {
		return values;
	}

	public double getFitness() {
		return fitness;
	}

	public int getLength() {
		return values.length;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	@Override
	public int compareTo(Chromosome o) {
		return Double.compare(fitness, o.fitness);
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("[ ");
		int n = getLength();
		for (int i = 0; i < n; i++) {
			string.append(values[i] + " ");
		}
		string.append("]");
		return string.toString();
	}

}
