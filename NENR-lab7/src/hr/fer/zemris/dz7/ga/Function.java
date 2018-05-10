package hr.fer.zemris.dz7.ga;

import hr.fer.zemris.dz7.models.NeuralNet;

public class Function {
	private NeuralNet net;

	public Function(NeuralNet net) {
		super();
		this.net = net;
	}

	public double valueAt(Chromosome chromosome) {
		return net.calcError(chromosome.getValues());
	}

}
