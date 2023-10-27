package com.d.tdd;

public class MyStringHelper {

    public String replaceAInFirst2Chars(String str) {
        if(str.length() < 2)
            return str.replaceAll("A","");
        String first2Chars = str.substring(0,2);
        String restOfTheString = str.substring(2);
        return first2Chars.replaceAll("A","") + restOfTheString;
    }

    public boolean areFirstTwoAndLastTwoCharsSame(String s) {
        int length = s.length();
        if(length < 2)
            return false;

        String first2Chars = s.substring(0, 2);
        String last2Chars = s.substring(length - 2, length);
        return first2Chars.equals(last2Chars);
    }
}
