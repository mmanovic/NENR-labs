package hr.fer.zemris.dz5.neural;

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

}
