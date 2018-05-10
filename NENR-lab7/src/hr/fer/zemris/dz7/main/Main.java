package hr.fer.zemris.dz7.main;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.dz7.ga.ArithmeticalCrossover;
import hr.fer.zemris.dz7.ga.BLXCrossover;
import hr.fer.zemris.dz7.ga.Chromosome;
import hr.fer.zemris.dz7.ga.ICrossover;
import hr.fer.zemris.dz7.ga.IMutation;
import hr.fer.zemris.dz7.ga.ISelection;
import hr.fer.zemris.dz7.ga.Mutation1;
import hr.fer.zemris.dz7.ga.Mutation2;
import hr.fer.zemris.dz7.ga.TournamentSelection;
import hr.fer.zemris.dz7.ga.UniformCrossover;
import hr.fer.zemris.dz7.models.IDataset;
import hr.fer.zemris.dz7.models.NeuralNet;
import hr.fer.zemris.dz7.models.Util;

public class Main {
	public static int tournamentSize = 3;
	public static double alpha = 0.4;
	public static double mutationRate1 = 0.03;
	public static double sigma1 = 0.3;
	public static double mutationRate2 = 0.03;
	public static double sigma2 = 0.3;
	public static double populationSize = 10;
	public static double firstMutationProb = 0.7;

	public static void main(String[] args) {
		IDataset dataset = Util.loadDataset(Paths.get("dataset.txt"));
		NeuralNet net = new NeuralNet(new int[] { 2, 5,3 }, dataset);
		List<Chromosome> population = createPopulation(populationSize, net.getWeightsCount());
		IMutation mutation1 = new Mutation1(mutationRate1, sigma1);
		IMutation mutation2 = new Mutation2(mutationRate2, sigma2);
		ICrossover[] crossover = new ICrossover[] { new BLXCrossover(alpha), new ArithmeticalCrossover(),
				new UniformCrossover() };
		// ICrossover crossover = new ArithmeticalCrossover();
		ISelection selection = new TournamentSelection(tournamentSize);
		EliminationAlgorithm algorithm = new EliminationAlgorithm(population, mutation1, firstMutationProb, mutation2,
				crossover, selection, net);
		Chromosome best = algorithm.run();
		net.printStats(best.getValues());
		System.out.println("Centroidi");
		double[] values = best.getValues();
		for (int i = 0; i < 8; i++) {
			System.out.println(
					values[i * 4] + " " + values[i * 4 + 2] + " " + values[i * 4 + 1] + " " + values[i * 4 + 3]);
		}
		System.out.println("\nTezine");
		for (int i = 0; i < 8; i++) {
			System.out.println(values[i + 32] + " " + values[i + 9 + 32] + " " + values[i + 18 + 32]);
		}

	}

	private static List<Chromosome> createPopulation(double populationSize, int weightsCount) {
		List<Chromosome> population = new ArrayList<>();
		for (int i = 0; i < populationSize; i++) {
			population.add(new Chromosome(weightsCount));
		}
		return population;
	}

}
