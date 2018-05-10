package hr.fer.zemris.dz5.neural;

import java.util.Random;

public class Neuron {
	private double y;
	private double delta;
	private double[] weights;
	private int layerIndex;
	private double[] deltaWeights;

	public Neuron(int weightsSize, int layerIndex) {
		super();
		this.layerIndex = layerIndex;
		weights = new double[weightsSize];
		deltaWeights = new double[weightsSize];
		Random random = new Random();
		for (int i = 0; i < weightsSize; i++) {
			weights[i] = random.nextDouble() * 2 - 1.0;
			deltaWeights[i] = 0;
		}
	}

	public void updateDeltaWeights(Layer previous) {
		int i = 0;
		for (Neuron neuron : previous) {
			deltaWeights[i++] += delta * neuron.y;
		}
	}

	public void updateWeights(double learningRate) {
		for (int i = 0; i < weights.length; i++) {
			weights[i] += learningRate * deltaWeights[i];
			deltaWeights[i] = 0;
		}
	}

	public void calculateDelta(Layer nextLayer) {
		delta = 0;
		for (Neuron neuron : nextLayer) {
			delta += y * (1 - y) * neuron.weights[layerIndex] * neuron.delta;
		}
	}

	public void setDelta(double delta) {
		this.delta = delta;
	}

	public double getDelta() {
		return this.delta;
	}

	public double sigmoid(double x) {
		return 1.0 / (1.0 + Math.exp(-x));
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void calculateY(Layer inputLayer) {
		double net = 0;
		int index = 0;
		for (Neuron neuron : inputLayer) {
			net += neuron.getY() * weights[index++];
		}
		y = sigmoid(net);
	}
}
