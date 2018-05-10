package hr.fer.zemris.dz5.neural;

public interface IDataset extends Iterable<InputData> {
	public int numberOfSamples();

	public InputData getData(int index);

	public double[] getInput(int index);

	public double[] getOutput(int index);
}
