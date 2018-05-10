package hr.fer.zemris.dz6.main;

import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.dz6.models.ANFIS;
import hr.fer.zemris.dz6.models.InputData;

public class Main {

	public static void main(String[] args) {
		List<InputData> dataset = createDataset();
		//List<InputData> copy=createDataset();

		ANFIS anfis = new ANFIS(1000000, 0.01, 0.001, 3, dataset);
		
		anfis.stohastic();
		//anfis.completeGradient();
		
		
//		for (Rule rule : anfis.getRules()) {
//			System.out.println(rule);
//		}
//
//		System.out.print("Z1=np.array([");
//		for (InputData data : copy) {
//			System.out.print(data.f-anfis.calculateOutput(data) + ",");
//		}
//		System.out.print("]).reshape(9,9)");
	}

	private static List<InputData> createDataset() {
		List<InputData> dataset = new ArrayList<>();
		for (int i = -4; i <= 4; i++) {
			for (int j = -4; j <= 4; j++) {
				dataset.add(new InputData(j, i));
			}
		}
		return dataset;
	}

}
