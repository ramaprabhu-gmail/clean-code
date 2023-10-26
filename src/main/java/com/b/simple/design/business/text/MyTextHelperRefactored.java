package com.b.simple.design.business.text;

public class MyTextHelperRefactored {

	public String swapLastTwoCharacters(String str) {
		if(str == null || str.length() < 2)
			return str;

		int length = str.length();
		char lastChar = str.charAt(length - 1);
		char secondLastChar = str.charAt(length - 2);

		return  str.substring(0, length-2)
				 + lastChar + secondLastChar;
	}

	public String truncateAInFirst2Positions(String str) {
		if(str.length() < 2)
			return str.replaceAll("A","");

		String first2Chars = str.substring(0,2);
		first2Chars = first2Chars.replaceAll("A","");

		return first2Chars + str.substring(2);

	}
}
