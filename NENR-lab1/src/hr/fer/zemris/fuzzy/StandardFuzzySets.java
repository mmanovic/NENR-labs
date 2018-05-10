package hr.fer.zemris.fuzzy;

public class StandardFuzzySets {
	public static IIntUnaryFunction lFunction(int a, int b) {
		return new IIntUnaryFunction() {

			@Override
			public double valueAt(int index) {
				if (index < a) {
					return 1;
				} else if (index >= b) {
					return 0;
				} else {
					return (double) (b - index) / (b - a);
				}
			}
		};
	}

	public static IIntUnaryFunction gammaFunction(int a, int b) {
		return new IIntUnaryFunction() {

			@Override
			public double valueAt(int index) {
				if (index < a) {
					return 1;
				} else if (index >= b) {
					return 0;
				} else {
					return (double) (index - a) / (b - a);
				}
			}
		};

	}

	public static IIntUnaryFunction lambdaFunction(int a, int b, int c) {
		return new IIntUnaryFunction() {

			@Override
			public double valueAt(int index) {
				if (a <= index && index < b)
					return (double) (index - a) / (b - a);
				if (b <= index && index < c)
					return (double) (c - index) / (c - b);
				return 0;
			}
		};

	}
}
