package hr.fer.zemris.dz7.main;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import hr.fer.zemris.dz7.ga.Chromosome;
import hr.fer.zemris.dz7.ga.ICrossover;
import hr.fer.zemris.dz7.ga.IMutation;
import hr.fer.zemris.dz7.ga.ISelection;
import hr.fer.zemris.dz7.models.NeuralNet;

public class EliminationAlgorithm {
	private int MAX_ITERATION = 200000;
	private double EPSILON = 1E-7;
	private List<Chromosome> population;
	private IMutation mutation1;
	private IMutation mutation2;
	private ICrossover[] crossover;
	private ISelection selection;
	private NeuralNet net;
	private Random random = new Random();
	private double firstMutationProb;

	public EliminationAlgorithm(List<Chromosome> population, IMutation mutation1, double firstMutationProb,
			IMutation mutation2, ICrossover[] crossover, ISelection selection, NeuralNet net) {
		super();
		this.net = net;
		this.population = population;
		this.mutation1 = mutation1;
		this.mutation2 = mutation2;
		this.crossover = crossover;
		this.selection = selection;
		this.firstMutationProb = firstMutationProb;
		for (Chromosome unit : population) {
			unit.setFitness(-net.calcError(unit.getValues()));
		}
	}

	public Chromosome run() {
		int iteration = 0;
		double error = Double.MAX_VALUE;
		while (error > EPSILON && iteration < MAX_ITERATION) {

			Chromosome x = selection.select(population);
			Chromosome y = selection.select(population);
			Chromosome child = crossover[random.nextInt(3)].cross(x, y);
			if (random.nextDouble() <= firstMutationProb) {
				mutation1.mutate(child);
			} else {
				mutation2.mutate(child);
			}
			child.setFitness(-net.calcError(child.getValues()));

			int indexOfWorst = -1;
			double worstError = 0;
			int currentIndex = 0;
			for (Chromosome chromosome : population) {
				if (indexOfWorst == -1 || -chromosome.getFitness() >= worstError) {
					worstError = -chromosome.getFitness();
					indexOfWorst = currentIndex;
				}
				currentIndex++;
			}
			// System.out.println(indexOfWorst + " " + worstError);

			if (-child.getFitness() <= worstError) {
				population.remove(indexOfWorst);
				population.add(child);
			}

			error = Math.min(error, -Collections.max(population).getFitness());
			iteration++;
			if (iteration % 100 == 0) {
				System.out.println(error + " Iteration: " + iteration);
			}
		}

		System.out.println("Final solution: " + Collections.max(population));
		System.out.println("Error value: " + error);
		return Collections.max(population);
	}
}
