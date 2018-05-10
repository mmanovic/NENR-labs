package hr.fer.zemris.fuzzy;

public class Operations {
	public static IBinaryFunction ZADEH_AND = (x, y) -> {
		return Math.min(x, y);
	};
	public static IBinaryFunction ZADEH_OR = (x, y) -> {
		return Math.max(x, y);
	};
	public static IUnaryFunction ZADEH_NOT = x -> {
		return 1 - x;
	};

	public static IFuzzySet unaryOperation(IFuzzySet set, IUnaryFunction operation) {
		MutableFuzzySet fuzzySet = new MutableFuzzySet(set.getDomain());
		for (DomainElement element : fuzzySet.getDomain()) {
			fuzzySet.set(element, operation.valueAt(set.getValueAt(element)));
		}
		return fuzzySet;
	}

	public static IFuzzySet binaryOperation(IFuzzySet set1, IFuzzySet set2, IBinaryFunction operation) {
		MutableFuzzySet fuzzySet = new MutableFuzzySet(set1.getDomain());
		for (DomainElement element : fuzzySet.getDomain()) {
			fuzzySet.set(element, operation.valueAt(set1.getValueAt(element), set2.getValueAt(element)));
		}
		return fuzzySet;
	}

	public static IUnaryFunction zadehNot() {
		return ZADEH_NOT;
	}

	public static IBinaryFunction zadehAnd() {
		return ZADEH_AND;
	}

	public static IBinaryFunction zadehOr() {
		return ZADEH_OR;
	}

	public static IBinaryFunction hamacherTNorm(double v) {
		return new HamacherTNorm(v);
	}

	public static IBinaryFunction hamacherSNorm(double v) {
		return new HamacherSNorm(v);
	}

	public static class HamacherTNorm implements IBinaryFunction {
		private double v;

		public HamacherTNorm(double v) {
			this.v = v;
		}

		@Override
		public double valueAt(double x, double y) {
			return x * y / (v + (1 - v) * (x + y - x * y));
		}
	}

	public static class HamacherSNorm implements IBinaryFunction {
		private double v;

		public HamacherSNorm(double v) {
			this.v = v;
		}

		@Override
		public double valueAt(double x, double y) {
			return (x + y - (2 - v) * x * y) / (1 - (1 - v) * x * y);
		}
	}
}
