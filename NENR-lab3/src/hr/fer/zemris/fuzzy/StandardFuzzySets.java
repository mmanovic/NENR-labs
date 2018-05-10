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
					return (double) (b - index) / (double)(b - a);
				}
			}
		};
	}

	public static IIntUnaryFunction gammaFunction(int a, int b) {
		return new IIntUnaryFunction() {

			@Override
			public double valueAt(int index) {
				if (index < a) {
					return 0;
				} else if (index >= b) {
					return 1;
				} else {
					return (double) (index - a) / (double)(b - a);
				}
			}
		};

	}

	public static IIntUnaryFunction lambdaFunction(int a, int b, int c) {
		return new IIntUnaryFunction() {

			@Override
			public double valueAt(int index) {
				if (a <= index && index < b)
					return (double) (index - a) / (double)(b - a);
				if (b <= index && index < c)
					return (double) (c - index) / (double)(c - b);
				return 0;
			}
		};

	}
}
