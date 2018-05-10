package hr.fer.zemris.dz7.models;

public class InputData {
	private double[] input;
	private double[] output;

	public InputData(double[] input, double[] output) {
		super();
		this.input = input;
		this.output = output;
	}

	public double[] getInput() {
		return input;
	}

	public double[] getOutput() {
		return output;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Input: [");
		for (double x : input) {
			sb.append(x + " ");
		}
		sb.append("] Output: [");
		for (double x : output) {
			sb.append(x + " ");
		}
		sb.append("]");
		return sb.toString();

	}

}
