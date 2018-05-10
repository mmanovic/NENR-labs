package hr.fer.zemris.dz7.models;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Util {
	public static IDataset loadDataset(Path file) {
		List<InputData> data = new ArrayList<>();
		if (!Files.exists(file)) {
			System.out.println("File " + file.toString() + " doesn't exist.");
			System.exit(1);
		}
		List<String> lines = new ArrayList<>();
		try {
			lines = Files.readAllLines(file);
		} catch (IOException e) {
			System.out.println("Unable to open file.");
			System.exit(1);
		}

		for (String line : lines) {
			String[] tmp = line.split("\\s+");

			double[] input = new double[2];
			double[] output = new double[3];
			for (int i = 0; i < 2; i++) {
				input[i] = Double.parseDouble(tmp[i]);
			}
			for (int i = 2; i < 5; i++) {
				output[i - 2] = Double.parseDouble(tmp[i]);
			}
			data.add(new InputData(input, output));
		}

		return new Dataset(data);
	}
}
