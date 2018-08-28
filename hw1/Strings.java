package homework1;
import java.io.*;
import java.util.ArrayList;

public class Strings {
	public String uniqueLetters(String str) {
		String unique2 = "";
		boolean flag = false;
		char[] unique1 = str.toCharArray();
		for (int x = 0; x < unique1.length; x++) {
			flag = false;
			for (int y = 0; y < unique1.length; y++) {
				if (x == y) {
					continue;
				}
				if (unique1[x] == unique1[y]) {
					flag = true;
					break;
				}
			}
			if (flag == true) {
				continue;
			}

			unique2 += unique1[x];
		}
		return unique2;

	}

}
