package hr.fer.zemris.dz5.neural;

import java.util.ArrayList;
import java.util.List;

public class NeuralNet {
	private int maxIteration;
	private double learningRate;
	private IDataset dataset;
	private int[] layersSize;
	private List<Layer> layers;
	private int nOfSamples;
	private int EVALUATION = 0;

	public NeuralNet(int maxIteration, double learningRate, IDataset dataset, int[] layersSize) {
		super();
		this.maxIteration = maxIteration;
		this.learningRate = learningRate;
		this.dataset = dataset;
		this.layersSize = layersSize;
		nOfSamples = dataset.numberOfSamples();
		createNeuralNet();
	}

	private void createNeuralNet() {
		layers = new ArrayList<>();
		layers.add(new Layer(layersSize[0], 0));
		for (int i = 1; i < layersSize.length; i++) {
			layers.add(new Layer(layersSize[i], layersSize[i - 1]));
		}
	}

	public int getIndexOfBest(double[] inputs) {
		forwardPass(inputs);
		int indexOfBest = -1;
		double maxProbability = 0;
		int i = 0;
		for (Neuron neuron : layers.get(layers.size() - 1)) {
			if (neuron.getY() > maxProbability) {
				maxProbability = neuron.getY();
				indexOfBest = i;
			}
			i++;
		}
		return indexOfBest;
	}

	public void learnNeuralNet(int batchSize) {
		for (int i = 0; i < maxIteration; i++) {
			if (i % 100 == 0) {
				System.out.println("Iteratiorn " + i + " Error: " + computeError());
			}
			doIteration(batchSize);
		}
		System.out.println("Final error: " + computeError());
	}

	private void doIteration(int batchSize) {
		int indexOfLastLayer = layers.size() - 1;
		for (int i = 0; i < batchSize; i++) {
			forwardPass(dataset.getInput(EVALUATION % nOfSamples));
			double[] expected = dataset.getOutput(EVALUATION++ % nOfSamples);
			int j = 0;
			for (Neuron neuron : layers.get(indexOfLastLayer)) {
				double y = neuron.getY();
				neuron.setDelta(y * (1 - y) * (expected[j++] - y));
			}

			for (j = indexOfLastLayer - 1; j >= 1; j--) {
				layers.get(j).updateDeltas(layers.get(j + 1));
			}
			for (j = indexOfLastLayer; j >= 1; j--) {
				layers.get(j).updateDeltaWeights(layers.get(j - 1));
			}
		}
		for (Layer l : layers)
			l.updateWeights(learningRate);
		;

	}

	private void forwardPass(double[] inputs) {
		setInputLayer(inputs);
		for (int i = 1; i < layersSize.length; i++) {
			layers.get(i).forwardPass(layers.get(i - 1));
		}
	}

	private double computeError() {
		double error = 0;
		for (InputData data : dataset) {
			forwardPass(data.getInput());
			double[] expected = data.getOutput();
			Layer lastLayer = layers.get(layers.size() - 1);
			int i = 0;
			for (Neuron neuron : lastLayer) {
				error += Math.pow(expected[i++] - neuron.getY(), 2);
			}
		}
		return error / nOfSamples;
	}

	private void setInputLayer(double[] inputs) {
		int i = 0;
		for (Neuron neuron : layers.get(0)) {
			neuron.setY(inputs[i++]);
		}
	}

}
