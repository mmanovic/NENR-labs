package hr.fer.zemris.dz5.neural;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Layer implements Iterable<Neuron> {
	private List<Neuron> neurons;

	public Layer(int layerSize, int previousLayerSize) {
		neurons = new ArrayList<>(layerSize);
		for (int i = 0; i < layerSize; i++) {
			neurons.add(new Neuron(previousLayerSize, i));
		}
	}

	public void forwardPass(Layer inputLayer) {
		for (Neuron n : this) {
			n.calculateY(inputLayer);
		}
	}

	public void updateWeights(double learningRate) {
		for (Neuron n : this) {
			n.updateWeights(learningRate);
		}
	}

	public void updateDeltaWeights(Layer previous) {
		for (Neuron n : this) {
			n.updateDeltaWeights(previous);
		}
	}

	@Override
	public Iterator<Neuron> iterator() {
		return neurons.iterator();
	}

	public void updateDeltas(Layer next) {
		for (Neuron n : this) {
			n.calculateDelta(next);
		}
	}
}
