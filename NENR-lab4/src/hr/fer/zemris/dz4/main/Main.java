package hr.fer.zemris.dz4.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.dz4.models.Chromosome;
import hr.fer.zemris.dz4.models.Crossover;
import hr.fer.zemris.dz4.models.Function;
import hr.fer.zemris.dz4.models.ISelection;
import hr.fer.zemris.dz4.models.Mutation;
import hr.fer.zemris.dz4.models.TournamentSelection;

public class Main {
	public static Path file = Paths.get("./zad4-dataset1.txt");
	public static double ALPHA = 0.5;
	public static double SIGMA = 0.01;
	public static int POPULATION_SIZE = 10;

	public static void main(String[] args) {
		if (!Files.exists(file)) {
			System.out.println("File " + file.toString() + " doesn't exist.");
			return;
		}
		List<String> lines;
		try {
			lines = Files.readAllLines(file);
		} catch (IOException e) {
			System.out.println("Unable to open file.");
			return;
		}
		Function function = getFunction(lines);
		Mutation mutation = new Mutation(SIGMA);
		Crossover crossover = new Crossover(ALPHA);
		ISelection selection = new TournamentSelection(3);
		List<Chromosome> population = createPopulation(function, POPULATION_SIZE);
		// GenerationAlgorithm algorithm = new GenerationAlgorithm(population,
		// mutation, crossover, selection, true);
		EliminationAlgorithm algorithm = new EliminationAlgorithm(population, mutation, crossover, selection);
		algorithm.run();
	}

	private static List<Chromosome> createPopulation(Function function, int populationSize) {
		int CHROMOSOME_SIZE = 5;
		List<Chromosome> population = new ArrayList<>();
		for (int i = 0; i < populationSize; i++) {
			population.add(new Chromosome(function, CHROMOSOME_SIZE));
		}
		return population;
	}

	private static Function getFunction(List<String> lines) {
		double[][] values = new double[lines.size()][3];
		int i = 0;
		for (String line : lines) {
			line = line.trim();
			if (!line.isEmpty()) {
				String[] tmp = line.split("\\s+");
				values[i][0] = Double.parseDouble(tmp[0]);
				values[i][1] = Double.parseDouble(tmp[1]);
				values[i][2] = Double.parseDouble(tmp[2]);
				i++;
			}
		}
		return new Function(values);
	}

}
