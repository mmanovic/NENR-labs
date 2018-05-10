package hr.fer.zemris.dz4.models;

import java.util.List;

public interface ISelection {
	public Chromosome select(List<Chromosome> population);
}
