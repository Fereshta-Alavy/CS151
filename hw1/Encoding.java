package homework1;
import java.io.*;
import java.util.ArrayList;

public class Encoding {
	public static Set<String> morseCodes(int dot, int dash) {
		String str = "";
		for (int i = 0; i < dot; i++) {
			str += ".";
		}
		for (int i = 0; i < dash; i++) {
			str += "-";
		}
		Set<String> strSet = Collections.emptySet();
		strSet.add(permutation(str, dot, dash));

		return strSet;
	}

	private static String permutation(String str, int left, int right) {
		if (left == right)
			return (str);
		else {
			for (int i = left; i <= right; i++) {
				str = swap(str, left, i);
				permutation(str, left + 1, right);
				str = swap(str, left, i);
			}
		}
		return str;
	}

	public static String swap(String str, int left, int right) {
		char temp;
		char[] charArr = str.toCharArray();
		temp = charArr[left];
		charArr[left] = charArr[right];
		charArr[left] = temp;
		return String.valueOf(charArr);
	}

}
