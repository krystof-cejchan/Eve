package main;

public class Prefix {
	private String value = ";"; // default prefix

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		if (value.contains(" ") == false && value.length() >= 1)
			this.value = value;
	}

}
