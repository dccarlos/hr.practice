package edu.dccarlos.hr.practice.algo;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;

/**
 * This works but It doesn't pass the HR test. If you want to pass the HR test You must to design an special algorithm using the suffix
 * array/LCP algorithms.
 */
public class AshtonAndStringTry01 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		final int tests = in.nextInt();

		in.nextLine();

		Map<String, Integer> testMap = new LinkedHashMap<>();

		IntStream.range(0, tests).forEach(test -> {
			testMap.put(in.nextLine(), Integer.parseInt(in.nextLine()));
		});

		testMap.forEach((k, v) -> {
			System.out.println(getFromLexicographicalOrdered(k, v));
		});
	}

	public static final Character getFromLexicographicalOrdered(final String str, final int element) {
		Set<String> stringsSet = new TreeSet<>();
		stringsSet.add(str);

		for(int index = 1; index < str.length(); index++) {
			for(int i = 0; i < str.length() - index + 1; i++) {
				String str1 = str.substring(i, index + i);
				stringsSet.add(str1);
			}
		}

		int lengthCounter = 0;
		for(String string : stringsSet) {
			for(int i = 0; i < string.length(); i++) {
				lengthCounter++;

				if(lengthCounter == element) {
					return string.charAt(i);
				}
			}
		}

		return null;
	}
}