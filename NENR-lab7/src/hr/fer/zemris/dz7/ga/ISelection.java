package hr.fer.zemris.dz7.ga;

import java.util.List;

public interface ISelection {
	public Chromosome select(List<Chromosome> population);
}
