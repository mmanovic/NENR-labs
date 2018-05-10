package hr.fer.zemris.dz3.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.omg.Messaging.SyncScopeHelper;

import hr.fer.zemris.dz3.control.AccelerationRuleBase;
import hr.fer.zemris.dz3.control.COADefuzzifier;
import hr.fer.zemris.dz3.control.FuzzySystem;
import hr.fer.zemris.dz3.control.HelmRuleBase;
import hr.fer.zemris.dz3.control.TypeOfMachine;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		int L = 0, D = 0, LK = 0, DK = 0, V = 0, S = 0;
		String line = null;

		FuzzySystem accSystem = new FuzzySystem(new AccelerationRuleBase(TypeOfMachine.MIN_MACHINE),
				new COADefuzzifier());
		FuzzySystem helmSystem = new FuzzySystem(new HelmRuleBase(TypeOfMachine.MIN_MACHINE), new COADefuzzifier());
		while (true) {
			if ((line = input.readLine()) != null) {
				if (line.charAt(0) == 'K')
					break;
				Scanner s = new Scanner(line);
				L = s.nextInt();
				D = s.nextInt();
				LK = s.nextInt();
				DK = s.nextInt();
				V = s.nextInt();
				S = s.nextInt();

			}

			int[] values = new int[] { L, D, LK, DK, V, S };
			// System.err.println((int) accSystem.deduce(values) + " " + (int)
			// helmSystem.deduce(values));
			int acceleration = (int) accSystem.deduce(values);
			int helm = (int) helmSystem.deduce(values);
			System.err.println(L + " " + D + " " + LK + " " + DK + " " + V + " " + S);
			System.err.println(acceleration + " " + helm);
			System.out.println(acceleration + " " + helm);

			System.out.flush();
		}
	}

}
