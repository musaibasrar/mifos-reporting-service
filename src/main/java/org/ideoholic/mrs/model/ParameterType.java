package org.ideoholic.mrs.model;

import lombok.Getter;

@Getter
public enum ParameterType {

	STRING("string"), INTEGER("integer"), FLOAT("float"), DATE("date"), BOOLEAN("boolean");

	private final String value;

	private ParameterType(String value) {
		this.value = value;
	}

	public static ParameterType fromValue(String value) {
		for (ParameterType param : ParameterType.values()) {
			if (param.value.equals(value)) {
				return param;
			}
		}
		throw new IllegalArgumentException(value);
	}

}
