package hr.fer.zemris.dz4.models;

public class Function {
	double[][] values;

	public Function(double[][] values) {
		super();
		this.values = values;
	}

	public double valueAt(Chromosome chromosome) {
		double[] vector = chromosome.getValues();
		double result = 0;

		double b0 = vector[0];
		double b1 = vector[1];
		double b2 = vector[2];
		double b3 = vector[3];
		double b4 = vector[4];
		int row = values.length;
		for (int i = 0; i < row; i++) {
			double difference = 0;
			double x = values[i][0];
			double y = values[i][1];
			double output = values[i][2];
			difference += Math.sin(b0 + b1 * x);
			difference += (b2 * Math.cos(x * (b3 + y))) / (1 + Math.exp(Math.pow(x - b4, 2)));
			difference -= output;

			result += difference * difference;
		}
		
		return result/row;
	}

	public int numberOfVariables() {
		return 5;
	}

}
