package hr.fer.zemris.fuzzy;

import java.util.Arrays;

public class DomainElement {
	private int[] values;

	public DomainElement(int[] values) {
		super();
		this.values = values;
	}

	public static DomainElement of(int... values) {
		return new DomainElement(values);
	}

	public int getNumberOfComponents() {
		return values.length;
	}

	public int getComponentValue(int index) {
		return values[index];
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(values);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DomainElement other = (DomainElement) obj;
		if (!Arrays.equals(values, other.values))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("(");
		for (int value : values) {
			string.append(value + ",");
		}
		return string.substring(0,string.length() - 1) + ")";
	}

}
