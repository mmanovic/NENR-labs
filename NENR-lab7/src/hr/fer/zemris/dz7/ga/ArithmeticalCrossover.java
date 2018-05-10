package hr.fer.zemris.dz7.ga;

public class ArithmeticalCrossover implements ICrossover {

	@Override
	public Chromosome cross(Chromosome x, Chromosome y) {
		int size = x.getLength();
		double[] values = new double[size];
		double[] xValues = x.getValues();
		double[] yValues = y.getValues();
		for (int i = 0; i < size; i++) {
			values[i] = (xValues[i] + yValues[i]) / 2;
		}
		return new Chromosome(values);
	}

}
