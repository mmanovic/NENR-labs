package hr.fer.zemris.dz5.main;

import java.nio.file.Paths;

import javax.swing.SwingUtilities;

import hr.fer.zemris.dz5.gui.ClassificationFrame;
import hr.fer.zemris.dz5.neural.IDataset;
import hr.fer.zemris.dz5.neural.NeuralNet;
import hr.fer.zemris.dz5.neural.Util;

public class Main {
	public static void main(String[] args) {
		String filePath = "./examples3.txt";
		IDataset dataset = Util.loadDataset(Paths.get(filePath), 50, 5);
		NeuralNet net = new NeuralNet(100000, 0.2, dataset, new int[] { 40, 5, 5, 5 });
		// net.learnNeuralNet(dataset.numberOfSamples());
		net.learnNeuralNet(5);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ClassificationFrame(net).setVisible(true);
			}
		});

		// IDataset irisdataset =
		// Util.loadDataset(Paths.get("./07-iris-formatirano.data"));
		// NeuralNet net2 = new NeuralNet(1000000, 0.1, irisdataset, new int[] {
		// 4, 5, 5, 3 });
		// net2.learnNeuralNet(1);

		// IDataset dataset = Util.loadDataset(Paths.get("./bezveze.txt"), 1,
		// 1);
		// NeuralNet net = new NeuralNet(100000, 0.1, dataset, new int[] { 1, 6,
		// 6, 1 });
		// net.learnNeuralNet(dataset.numberOfSamples());
	}
}
