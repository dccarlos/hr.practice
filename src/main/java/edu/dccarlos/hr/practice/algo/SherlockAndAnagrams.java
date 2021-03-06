package edu.dccarlos.hr.practice.algo;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class SherlockAndAnagrams {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		final int tests = in.nextInt();
		final String testsArray[] = new String[tests];

		in.nextLine();

		IntStream.range(0, tests).forEach(test -> {
			testsArray[test] = in.nextLine();
		});

		IntStream.range(0, tests).forEach(test -> {
			System.out.println(countAnagrammaticPairs(testsArray[test], 1, SherlockAndAnagrams::areAnagrams));
		});
	}

	public static final int areAnagrams(final String str1, final String str2) {
		if(str1.length() != str2.length())
			return -1;
		else {
			Map<Character, Integer> counter = new HashMap<>();

			str1.toLowerCase().chars().forEach(charInt -> {
				counter.put((char) charInt, counter.containsKey((char) charInt) ? counter.get((char) charInt) + 1 : 1);
			});

			str2.toLowerCase().chars().forEach(charInt -> {
				counter.put((char) charInt, counter.containsKey((char) charInt) ? counter.get((char) charInt) - 1 : 1);
			});

			return (int) counter.values().stream().filter(value -> (value > 0)).count();
		}
	}

	public static final int countAnagrammaticPairs(final String str, final int initialLength, final Comparator<String> comparator) {
		int counter = 0;

		for(int index = (initialLength > 0 ? initialLength : 1); index < str.length(); index++) {
			for(int i = 0; i < str.length() - index + 1; i++) {
				// Get the substring
				String str1 = str.substring(i, index + i);

				for(int j = i + 1; j < str.length() - index + 1; j++) {
					// Compare with the remaining substrings (j = i + 1)
					String str2 = str.substring(j, index + j);

					counter += (comparator.compare(str1, str2) == 0 ? 1 : 0);
				}
			}
		}

		return counter;
	}
}