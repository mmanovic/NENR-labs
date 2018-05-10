package hr.fer.zemris.dz5.neural;

public class SigmoidFunction implements ITransferFunction {

	
	@Override
	public double valueAt(double x) {
		return 1.0 / (1.0 + Math.exp(-x));
	}

}
