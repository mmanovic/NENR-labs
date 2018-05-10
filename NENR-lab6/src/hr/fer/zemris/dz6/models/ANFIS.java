package hr.fer.zemris.dz6.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ANFIS {
	private int maxIteration;
	private double learningRate1;
	private double learningRate2;
	private List<InputData> dataset;
	private int nOfSamples;
	private List<Rule> rules;

	public ANFIS(int maxIteration, double learningRate1, double learningRate2, int nOfRules, List<InputData> dataset) {
		super();
		this.maxIteration = maxIteration;
		this.learningRate1 = learningRate1;
		this.learningRate2 = learningRate2;
		this.dataset = dataset;
		this.nOfSamples = dataset.size();
		this.rules = new ArrayList<>();
		for (int i = 0; i < nOfRules; i++) {
			rules.add(new Rule());
		}
	}

	public double calculateOutput(InputData input) {
		double output = 0;
		double piSum = 0;
		for (Rule rule : rules) {
			double alfa = rule.getAlfa(input.x);
			double beta = rule.getBeta(input.y);
			double pi = alfa * beta;
			piSum += pi;
			output += pi * rule.getF(input);
		}
		return output / piSum;
	}

	public void completeGradient() {
		for (int i = 0; i < maxIteration; i++) {
			for (InputData input : dataset) {
				double output = 0;
				double piSum = 0;
				for (Rule rule : rules) {
					double alfa = rule.getAlfa(input.x);
					double beta = rule.getBeta(input.y);
					double pi = alfa * beta;
					piSum += pi;
					output += pi * rule.getF(input);
				}
				for (Rule rule : rules) {
					double alfa = rule.getAlfa(input.x);
					double beta = rule.getBeta(input.y);
					double commonPart = (input.f - output / piSum) * (rule.getF(input) * piSum - output) * beta * alfa
							* (1 - alfa) / Math.pow(piSum, 2);
					rule.da += commonPart * rule.b;
					rule.db -= commonPart * (input.x - rule.a);
					commonPart = (input.f - output / piSum) * (rule.getF(input) * piSum - output) * alfa * beta
							* (1 - beta) / Math.pow(piSum, 2);
					rule.dc += commonPart * rule.d;
					rule.dd -= commonPart * (input.y - rule.c);

					commonPart = (input.f - output / piSum) * alfa * beta / piSum;
					rule.dp += commonPart * input.x;
					rule.dq += commonPart * input.y;
					rule.dr += commonPart;

				}
			}
			for (Rule rule : rules) {
				rule.updateParameters(learningRate1, learningRate2);
			}
			if (i % 100 == 0) {
				System.out.println("Iteration: " + i + " Error: " + getANFISError());
			}

			// if (i % 1 == 0) {
			// System.out.print(getANFISError()+",");
			// }
		}
		System.out.println("Final error: " + getANFISError());

	}

	public void stohastic() {
		for (int i = 0; i < maxIteration; i++) {
			if (i % nOfSamples == 0) {
				Collections.shuffle(dataset);
			}
			InputData input = dataset.get(i % nOfSamples);
			double output = 0;
			double piSum = 0;
			for (Rule rule : rules) {
				double alfa = rule.getAlfa(input.x);
				double beta = rule.getBeta(input.y);
				double pi = alfa * beta;
				piSum += pi;
				output += pi * rule.getF(input);
			}
			for (Rule rule : rules) {
				double alfa = rule.getAlfa(input.x);
				double beta = rule.getBeta(input.y);
				double commonPart = (input.f - output / piSum) * (rule.getF(input) * piSum - output) * beta * alfa
						* (1 - alfa) / Math.pow(piSum, 2);
				rule.da += commonPart * rule.b;
				rule.db -= commonPart * (input.x - rule.a);
				commonPart = (input.f - output / piSum) * (rule.getF(input) * piSum - output) * alfa * beta * (1 - beta)
						/ Math.pow(piSum, 2);
				rule.dc += commonPart * rule.d;
				rule.dd -= commonPart * (input.y - rule.c);

				commonPart = (input.f - output / piSum) * alfa * beta / piSum;
				rule.dp += commonPart * input.x;
				rule.dq += commonPart * input.y;
				rule.dr += commonPart;

				rule.updateParameters(learningRate1, learningRate2);
			}

			if (i % 100 == 0) {
				System.out.println("Iteration: " + i + " Error: " + getANFISError());
			}
			// if(i%(81*1)==0){
			// System.out.print(getANFISError()+",");
			// }
		}
		System.out.println("Final error: " + getANFISError());
	}

	public double getANFISError() {
		double error = 0;
		for (InputData input : dataset) {
			double output = 0;
			double piSum = 0;
			for (Rule rule : rules) {
				double alfa = rule.getAlfa(input.x);
				double beta = rule.getBeta(input.y);
				double pi = alfa * beta;
				piSum += pi;
				output += pi * rule.getF(input);
			}
			error += Math.pow((input.f - output / piSum), 2);
		}
		return error / nOfSamples;
	}

	public List<Rule> getRules() {
		return rules;
	}

}
