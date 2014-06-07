package org.sigaim.siie.utils;

public class Utils {
	public static String toUppercaseNotation(String input) {
		StringBuilder fullPath=new StringBuilder(input);
		for(int i=0;i<fullPath.length();i++) {
			char c=fullPath.charAt(i);
			if(c=='_') {
				//Uppercase next character
				if(i+1<fullPath.length()) {
					fullPath.setCharAt(i+1,Character.toUpperCase(fullPath.charAt(i+1)));
					fullPath.deleteCharAt(i);
				}
			}
		}
		return fullPath.toString();
	}
	public static String toUnderscoreNotation(String input) {
		StringBuilder fullPath=new StringBuilder(input);
		for(int i=0;i<fullPath.length();i++) {
			char c=fullPath.charAt(i);
			if(Character.isUpperCase(c)) {
				fullPath.setCharAt(i, Character.toLowerCase(c));
				fullPath.insert(i, "_");
			}
		}
		return fullPath.toString();
	}
}
