package hr.fer.zemris.dz5.gui;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Coordinate {
	private double x;
	private double y;

	public Coordinate(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Coordinate(Point point) {
		this.x = point.getX();
		this.y = point.getY();
	}

	public static double distance(Coordinate c1, Coordinate c2) {
		return Point.distance(c1.x, c1.y, c2.x, c2.y);
	}

	public static void scale(List<Coordinate> coordinates) {
		Coordinate Tc = new Coordinate(0, 0);
		for (Coordinate coordinate : coordinates) {
			Tc.x += coordinate.x;
			Tc.y += coordinate.y;
		}
		Tc.x /= coordinates.size();
		Tc.y /= coordinates.size();
		double maxX = 0;
		double maxY = 0;
		for (Coordinate coordinate : coordinates) {
			coordinate.x -= Tc.x;
			coordinate.y -= Tc.y;
			maxX = Double.max(maxX, Math.abs(coordinate.x));
			maxY = Double.max(maxY, Math.abs(coordinate.y));
		}
		double maxCoordinate = Double.max(maxX, maxY);
		for (Coordinate coordinate : coordinates) {
			coordinate.x /= maxCoordinate;
			coordinate.y /= maxCoordinate;
		}
	}

	public static List<Coordinate> getFeatures(List<Coordinate> coordinates, int numberOfFeatures) {
		List<Coordinate> features = new ArrayList<>();
		scale(coordinates);
		double D = 0;
		int size = coordinates.size();
		for (int i = 1; i < size; i++) {
			D += distance(coordinates.get(i), coordinates.get(i - 1));
		}

		double segmentSize = D / (numberOfFeatures - 1);
		features.add(coordinates.get(0));
		double nextDistance = segmentSize;
		double currentDistance = 0;
		for (int i = 1; i < size; i++) {
			currentDistance += distance(coordinates.get(i), coordinates.get(i - 1));
			if (currentDistance >= nextDistance) {
				features.add(coordinates.get(i));
				nextDistance += segmentSize;
			}
		}
		while (features.size() < numberOfFeatures) {
			features.add(coordinates.get(size - 1));
		}
		return features;
	}

	public static double[] getInputsFromFeatures(List<Coordinate> features) {
		double[] inputs = new double[features.size() * 2];
		int i = 0;
		for (Coordinate feature : features) {
			inputs[i++] = feature.x;
			inputs[i++] = feature.y;
		}
		return inputs;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public String toString() {
		return x + " " + y + " ";
	}

}
