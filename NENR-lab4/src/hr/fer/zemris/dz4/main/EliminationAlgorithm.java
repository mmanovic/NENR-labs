package hr.fer.zemris.dz4.main;

import java.util.Collections;
import java.util.List;

import hr.fer.zemris.dz4.models.Chromosome;
import hr.fer.zemris.dz4.models.Crossover;
import hr.fer.zemris.dz4.models.ISelection;
import hr.fer.zemris.dz4.models.Mutation;

public class EliminationAlgorithm {
	private int MAX_ITERATION = 50000;
	private double EPSILON = 1E-6;
	private List<Chromosome> population;
	private Mutation mutation;
	private Crossover crossover;
	private ISelection selection;

	public EliminationAlgorithm(List<Chromosome> population, Mutation mutation, Crossover crossover,
			ISelection selection) {
		super();
		this.population = population;
		this.mutation = mutation;
		this.crossover = crossover;
		this.selection = selection;
	}

	public void run() {
		int iteration = 0;
		double error = Double.MAX_VALUE;
		while (error > EPSILON && iteration < MAX_ITERATION) {

			Chromosome x = selection.select(population);
			Chromosome y = selection.select(population);
			Chromosome child = mutation.mutate(crossover.cross(x, y));

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
			//System.out.println(indexOfWorst + " " + worstError);

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
	}
}
