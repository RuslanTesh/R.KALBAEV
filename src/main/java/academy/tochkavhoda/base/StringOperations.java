package academy.tochkavhoda.base;

import java.math.BigDecimal;
import java.math.BigInteger;

public class StringOperations {

    public static int getSummaryLength(String[] strings) {
        int sum = 0;
        for (String s : strings) {
            sum += s.length();
        }
        return sum;
    }

    public static String getFirstAndLastLetterString(String string) {
        if (string.length() == 0) {
            return "";
        }
        if (string.length() == 1) {
            return string + string;
        }
        return String.valueOf(string.charAt(0)) + string.charAt(string.length() - 1);
    }

    public static boolean isSameCharAtPosition(String string1, String string2, int index) {
        if (index < 0 || index >= string1.length() || index >= string2.length()) {
            return false;
        }
        return string1.charAt(index) == string2.charAt(index);
    }

    public static boolean isSameFirstCharPosition(String string1, String string2, char character) {
        int pos1 = string1.indexOf(character);
        int pos2 = string2.indexOf(character);
        return pos1 == pos2;
    }

    public static boolean isSameLastCharPosition(String string1, String string2, char character) {
        int pos1 = string1.lastIndexOf(character);
        int pos2 = string2.lastIndexOf(character);
        return pos1 == pos2;
    }

    public static boolean isSameFirstStringPosition(String string1, String string2, String str) {
        int pos1 = string1.indexOf(str);
        int pos2 = string2.indexOf(str);
        return pos1 == pos2;
    }

    public static boolean isSameLastStringPosition(String string1, String string2, String str) {
        int pos1 = string1.lastIndexOf(str);
        int pos2 = string2.lastIndexOf(str);
        return pos1 == pos2;
    }

    public static boolean isEqual(String string1, String string2) {
        return string1.equals(string2);
    }

    public static boolean isEqualIgnoreCase(String string1, String string2) {
        return string1.equalsIgnoreCase(string2);
    }

    public static boolean isLess(String string1, String string2) {
        return string1.compareTo(string2) < 0;
    }

    public static boolean isLessIgnoreCase(String string1, String string2) {
        return string1.compareToIgnoreCase(string2) < 0;
    }

    public static String concat(String string1, String string2) {
        return string1 + string2;
    }

    public static boolean isSamePrefix(String string1, String string2, String prefix) {
        return string1.startsWith(prefix) && string2.startsWith(prefix);
    }

    public static boolean isSameSuffix(String string1, String string2, String suffix) {
        return string1.endsWith(suffix) && string2.endsWith(suffix);
    }

    public static String getCommonPrefix(String string1, String string2) {
        int minLength = Math.min(string1.length(), string2.length());
        int commonLength = 0;
        for (int i = 0; i < minLength; i++) {
            if (string1.charAt(i) == string2.charAt(i)) {
                commonLength++;
            } else {
                break;
            }
        }
        return string1.substring(0, commonLength);
    }

    public static String reverse(String string) {
        return new StringBuilder(string).reverse().toString();
    }

    public static boolean isPalindrome(String string) {
        return string.equals(reverse(string));
    }

    public static boolean isPalindromeIgnoreCase(String string) {
        String lowerStr = string.toLowerCase();
        return lowerStr.equals(reverse(lowerStr));
    }

    public static String getLongestPalindromeIgnoreCase(String[] strings) {
        String longest = "";
        for (String s : strings) {
            if (isPalindromeIgnoreCase(s) && s.length() > longest.length()) {
                longest = s;
            }
        }
        return longest;
    }

    public static boolean hasSameSubstring(String string1, String string2, int index, int length) {
        if (index < 0 || length < 0 || index + length > string1.length() || index + length > string2.length()) {
            return false;
        }
        return string1.substring(index, index + length).equals(string2.substring(index, index + length));
    }

    public static boolean isEqualAfterReplaceCharacters(String string1, char replaceInStr1, char replaceByInStr1, String string2, char replaceInStr2, char replaceByInStr2) {
        String newStr1 = string1.replace(replaceInStr1, replaceByInStr1);
        String newStr2 = string2.replace(replaceInStr2, replaceByInStr2);
        return newStr1.equals(newStr2);
    }

    public static boolean isEqualAfterReplaceStrings(String string1, String replaceInStr1, String replaceByInStr1, String string2, String replaceInStr2, String replaceByInStr2) {
        String newStr1 = string1.replace(replaceInStr1, replaceByInStr1);
        String newStr2 = string2.replace(replaceInStr2, replaceByInStr2);
        return newStr1.equals(newStr2);
    }

    public static boolean isPalindromeAfterRemovingSpacesIgnoreCase(String string) {
        String noSpaces = string.replaceAll("\\s+", "");
        String lowerStr = noSpaces.toLowerCase();
        return lowerStr.equals(reverse(lowerStr));
    }

    public static boolean isEqualAfterTrimming(String string1, String string2) {
        return string1.trim().equals(string2.trim());
    }

    public static String makeCsvStringFromInts(int[] array) {
        if (array.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(array[i]);
        }
        return sb.toString();
    }

    public static String makeCsvStringFromDoubles(double[] array) {
        if (array.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(String.format("%.2f", array[i]));
        }
        return sb.toString();
    }

    public static StringBuilder makeCsvStringBuilderFromInts(int[] array) {
        if (array.length == 0) {
            return new StringBuilder();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(array[i]);
        }
        return sb;
    }

    public static StringBuilder makeCsvStringBuilderFromDoubles(double[] array) {
        if (array.length == 0) {
            return new StringBuilder();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(String.format("%.2f", array[i]));
        }
        return sb;
    }

    public static StringBuilder removeCharacters(String string, int[] positions) {
        StringBuilder sb = new StringBuilder(string);
        for (int i = positions.length - 1; i >= 0; i--) {
            sb.deleteCharAt(positions[i]);
        }
        return sb;
    }

    public static StringBuilder insertCharacters(String string, int[] positions, char[] characters) {
        StringBuilder sb = new StringBuilder(string);
        int shift = 0;
        for (int i = 0; i < positions.length; i++) {
            int insertPos = positions[i] + shift;
            sb.insert(insertPos, characters[i]);
            shift++;
        }
        return sb;
    }
}