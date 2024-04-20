package org.joseloc.javadevelopmentplayground.util;

public class UtilString {

    public static String coalesceTrim(String str) {
        return (str == null) ? "" : str.trim();
    }
}
