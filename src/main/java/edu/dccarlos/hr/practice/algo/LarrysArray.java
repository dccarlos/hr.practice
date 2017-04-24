package edu.dccarlos.hr.practice.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LarrysArray {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int tests = in.nextInt();

		if(tests > 0) {
			List<int[]> testCases = new ArrayList<>();

			for(int testCase = 0; testCase < tests; testCase++) {
				// in.reset();
				int testArrayLenth = in.nextInt();
				int[] testCaseArray = new int[testArrayLenth];

				in.nextLine();
				String testCaseInput = in.nextLine();
				String[] testCaseStr = testCaseInput.split(" ");

				for(int arrElement = 0; arrElement < testArrayLenth; arrElement++) {
					testCaseArray[arrElement] = Integer.parseInt(testCaseStr[arrElement]);
				}

				testCases.add(testCaseArray);
			}

			for(int[] arrTestCase : testCases) {
				testLarrysArray(arrTestCase);
			}
		}
	}

	public static final void testLarrysArray(int[] array) {
		if(getInsertionSortSwaps(array) % 2 == 0) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

	public static final int getInsertionSortSwaps(int[] originalArray) {
		int[] array = Arrays.copyOf(originalArray, originalArray.length);
		int numberOfSwaps = 0;

		for(int j = 1; j < array.length; j++) {
			int key = array[j];
			int i = j - 1;

			while(i > -1 && array[i] > key) {
				numberOfSwaps++;
				array[i + 1] = array[i];
				i = i - 1;
			}

			array[i + 1] = key;
		}

		return numberOfSwaps;
	}
}