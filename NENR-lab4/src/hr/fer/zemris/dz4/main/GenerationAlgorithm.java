package hr.fer.zemris.dz4.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import hr.fer.zemris.dz4.models.Chromosome;
import hr.fer.zemris.dz4.models.Crossover;
import hr.fer.zemris.dz4.models.ISelection;
import hr.fer.zemris.dz4.models.Mutation;

public class GenerationAlgorithm {
	private int MAX_GENERATION = 1500;
	private int ELITISM = 5;
	private double EPSILON = 1E-6;
	private List<Chromosome> population;
	private Mutation mutation;
	private Crossover crossover;
	private ISelection selection;
	private int populationSize;
	private boolean useElitism;

	public GenerationAlgorithm(List<Chromosome> population, Mutation mutation, Crossover crossover,
			ISelection selection, boolean useElitism) {
		super();
		this.population = population;
		population.sort(Comparator.reverseOrder());
		this.mutation = mutation;
		this.crossover = crossover;
		this.selection = selection;
		this.useElitism = useElitism;
		populationSize = population.size();
	}

	public void run() {
		int generation = 0;
		double error = Double.MAX_VALUE;
		while (error > EPSILON && generation < MAX_GENERATION) {
			List<Chromosome> newPopulation = new ArrayList<>();
			if (useElitism) {
				for (int j = 0; j < ELITISM; j++) {
					newPopulation.add(population.get(j));
				}
			}
			while (newPopulation.size() < populationSize) {
				Chromosome x = selection.select(population);
				Chromosome y = selection.select(population);
				Chromosome child = mutation.mutate(crossover.cross(x, y));
				newPopulation.add(child);
			}
			newPopulation.sort(Comparator.reverseOrder());
			population = newPopulation;
			error = -population.get(0).getFitness();
			generation++;
			System.out.println(error + " Generation: " + generation);
		}

		System.out.println("Final solution: " + population.get(0));
		System.out.println("Error value: " + error);
	}

}
