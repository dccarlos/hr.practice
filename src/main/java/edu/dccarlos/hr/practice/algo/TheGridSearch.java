package edu.dccarlos.hr.practice.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TheGridSearch {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();

		Map<int[][], int[][]> tests = new LinkedHashMap<>();

		for(int a0 = 0; a0 < t; a0++) {

			int R = Integer.parseInt(in.next());
			int C = Integer.parseInt(in.next());

			String[] G = new String[R];
			int[][] gInt = new int[R][C];

			for(int G_i = 0; G_i < R; G_i++) {
				G[G_i] = in.next();
				gInt[G_i] = Arrays.stream(G[G_i].split("")).map(intStr -> Integer.parseInt(intStr)).mapToInt(intObj -> intObj.intValue()).toArray();
			}

			int r = Integer.parseInt(in.next());
			int c = Integer.parseInt(in.next());

			String[] P = new String[r];
			int[][] pInt = new int[r][c];

			for(int P_i = 0; P_i < r; P_i++) {
				P[P_i] = in.next();
				pInt[P_i] = Arrays.stream(P[P_i].split("")).map(intStr -> Integer.parseInt(intStr)).mapToInt(intObj -> intObj.intValue()).toArray();
			}

			tests.put(pInt, gInt);
		}

		tests.entrySet().forEach(entry -> {
			System.out.println(isContained(entry.getKey(), entry.getValue()) ? "YES" : "NO");
		});
	}

	public static final boolean isContained(int P[][], int G[][]) {
		for(int gRow = 0; gRow < G.length; gRow++) {

			if(P.length < (G.length - gRow + 1)) {
				final List<Integer> foundRowsAt = getSubArrayIndexes(P[0], G[gRow]);

				for(Integer foundRowAt : foundRowsAt) {
					boolean found = true;

					for(int gIndex = gRow + 1, pRow = 1; (gIndex < G.length && pRow < P.length && found); gIndex++, pRow++) {
						found = getSubArrayIndexes(P[pRow], G[gIndex]).contains(foundRowAt);
					}

					if(found)
						return true;
				}

			} else
				return false;
		}

		return false;
	}

	public static final List<Integer> getSubArrayIndexes(int[] subArray, int[] array) {
		if((array.length - subArray.length) < 0) {
			Collections.emptyList();
		}

		final int limit = array.length - subArray.length + 1;
		List<Integer> indexes = new ArrayList<>();

		for(int i = 0; i < limit; i++) {

			boolean found = true;

			for(int j = 0; j < subArray.length; j++) {
				if(subArray[j] != array[i + j]) {
					found = false;
				}
			}

			if(found)
				indexes.add(i);
		}

		return indexes;
	}
}