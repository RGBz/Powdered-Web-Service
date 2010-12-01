package org.squeakytinkerings.util;

public class CamelCaseConverter {

	public static String toLowerCamelCase(String str) {
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}
	
	public static String toUpperCamelCase(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
}
