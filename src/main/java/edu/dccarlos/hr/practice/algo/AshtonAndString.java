package edu.dccarlos.hr.practice.algo;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * This will work
 */
public class AshtonAndString {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		final int tests = in.nextInt();

		in.nextLine();

		Map<String, Integer> testMap = new LinkedHashMap<>();

		IntStream.range(0, tests).forEach(test -> {
			testMap.put(in.nextLine(), Integer.parseInt(in.nextLine()));
		});

		testMap.forEach((str, key) -> {
			System.out.println(getFromLexicographicalOrdered(str, key, AshtonAndString::getSuffixArray01, AshtonAndString::getLcpArray));
		});
	}

	public static Character getFromLexicographicalOrdered(final String str, int key, final Function<String, int[]> getSuffixArray,
			final BiFunction<String, int[], int[]> getLcpArray) {

		final int strLength = str.length();
		int[] suffixArray = getSuffixArray.apply(str);
		int[] lcpArray = getLcpArray.apply(str, suffixArray);

		for(int i = 0; i < strLength; i++) {
			long suffixLength = strLength - suffixArray[i];
			long charCount = suffixLength * (suffixLength + 1) / 2;
			long prefixLength = lcpArray[i];

			charCount -= prefixLength * (prefixLength + 1) / 2;

			boolean suffixFound = true;

			if(key > charCount) {
				key -= charCount;
				suffixFound = false;
			}

			if(suffixFound) {
				for(long j = prefixLength + 1; j <= suffixLength; j++) {
					if(key <= j) {
						return str.charAt((int) (suffixArray[i] + key - 1));
					}
					key -= j;
				}

				return null;
			}
		}

		return null;
	}

	public static final int[] getSuffixArray(final String strArray) {
		return IntStream.range(0, strArray.length()).boxed().sorted((x, y) -> {
			String subStr1 = strArray.substring(x);
			String subStr2 = strArray.substring(y);

			return subStr1.compareTo(subStr2);
		}).mapToInt(x -> x).toArray();
	}

	public static int[] getSuffixArray01(final String str) {
		int n = str.length();
		Integer[] order = new Integer[n];

		for(int i = 0; i < n; i++)
			order[i] = n - 1 - i;

		Arrays.sort(order, (a, b) -> Character.compare(str.charAt(a), str.charAt(b)));

		int[] sa = new int[n];
		int[] classes = new int[n];

		for(int i = 0; i < n; i++) {
			sa[i] = order[i];
			classes[i] = str.charAt(i);
		}

		for(int len = 1; len < n; len *= 2) {

			int[] c = classes.clone();

			for(int i = 0; i < n; i++) {
				classes[sa[i]] = i > 0 && c[sa[i - 1]] == c[sa[i]] && sa[i - 1] + len < n && c[sa[i - 1] + len / 2] == c[sa[i] + len / 2] ? classes[sa[i - 1]] : i;
			}

			int[] cnt = new int[n];

			for(int i = 0; i < n; i++)
				cnt[i] = i;

			int[] s = sa.clone();

			for(int i = 0; i < n; i++) {
				int s1 = s[i] - len;

				if(s1 >= 0)
					sa[cnt[classes[s1]]++] = s1;
			}
		}

		return sa;
	}

	public static int[] getLcpArray(String input, int[] sa) {
		final int length = input.length();
		final int[] rank = new int[length];

		for(int i = 0; i < length; i++)
			rank[sa[i]] = i;

		int h = 0;

		final int[] lcp = new int[length];

		for(int i = 0; i < length; i++) {
			int k = rank[i];
			if(k == 0) {
				lcp[k] = -1;
			} else {
				final int j = sa[k - 1];
				while(i + h < length && j + h < length && input.charAt(i + h) == input.charAt(j + h)) {
					h++;
				}

				lcp[k] = h;
			}
			if(h > 0)
				h--;
		}

		return lcp;
	}
}