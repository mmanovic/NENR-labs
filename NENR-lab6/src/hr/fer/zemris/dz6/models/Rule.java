package hr.fer.zemris.dz6.models;

import java.util.Random;

public class Rule {
	public int ruleIndex;
	public double a;
	public double b;
	public double c;
	public double d;
	public double p;
	public double q;
	public double r;

	public double da;
	public double db;
	public double dc;
	public double dd;
	public double dp;
	public double dq;
	public double dr;

	private Random random = new Random();

	public Rule() {
		a = 2 * random.nextDouble() - 1;
		b = 2 * random.nextDouble() - 1;
		c = 2 * random.nextDouble() - 1;
		d = 2 * random.nextDouble() - 1;
		p = 2 * random.nextDouble() - 1;
		q = 2 * random.nextDouble() - 1;
		r = 2 * random.nextDouble() - 1;

		a = 2 * a;
		b = 2 * b;
		c = 2 * c;
		d = 2 * d;
		p = 2 * p;
		q = 2 * q;
		r = 2 * r;

		da = 0;
		db = 0;
		dc = 0;
		dd = 0;
		dp = 0;
		dq = 0;
		dr = 0;
	}

	public double getAlfa(double x) {
		return 1.0 / (1.0 + Math.exp(b * (x - a)));
	}

	public double getBeta(double y) {
		return 1.0 / (1.0 + Math.exp(d * (y - c)));
	}

	public double getF(InputData input) {
		return p * input.x + q * input.y + r;
	}

	public void updateParameters(double learningRate1, double learningRate2) {
		a += learningRate1 * da;
		b += learningRate1 * db;
		c += learningRate1 * dc;
		d += learningRate1 * dd;

		p += learningRate2 * dp;
		q += learningRate2 * dq;
		r += learningRate2 * dr;

		da = 0;
		db = 0;
		dc = 0;
		dd = 0;
		dp = 0;
		dq = 0;
		dr = 0;
	}

	@Override
	public String toString() {
		return "[" + a + " " + b + " " + c + " " + d + " " + p + " " + q + " " + r + "]";
	}

}
