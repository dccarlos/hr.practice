package edu.dccarlos.hr.practice.algo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MatrixRotation {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String init = in.nextLine();

		String[] matrixAndRotations = init.trim().split(" ");

		if(matrixAndRotations.length == 3) {
			int m = Integer.parseInt(matrixAndRotations[0]);
			int n = Integer.parseInt(matrixAndRotations[1]);
			int r = Integer.parseInt(matrixAndRotations[2]);

			int[][] matrix = new int[m][n];

			List<String> nList = new ArrayList<>();

			for(int row = 0; row < m; row++) {
				nList.add(in.nextLine());
			}

			for(int row = 0; row < m; row++) {
				String[] rowStrArray = ((String) nList.get(row)).trim().split(" ");

				for(int column = 0; column < n; column++) {
					matrix[row][column] = Integer.parseInt(rowStrArray[column]);
				}
			}

			rotateLeft(matrix, r);
			printArray(matrix);
		}
	}
	
	/**
	 * Print the array (HR problem statement).
	 */
	public static final void printArray(final int[][] array) {
		for(int row = 0; row < array.length; row++) {
			for(int column = 0; column < array[row].length; column++) {
				System.out.print(array[row][column] + " ");
			}

			System.out.println();
		}

		System.out.println();
	}

	/**
	 * Rotates 'rotations' left the squares of a matrix.
	 */
	public static final void rotateLeft(final int[][] matrix, final int rotations) {
		final int rows = matrix.length;
		final int columns = matrix[0].length;
		final int numberOfsquares = getNumberOfSquares(rows, columns);

		// Matrix element holder.
		class Element {
			public final int rotationIndex;
			public final int rowIndex;
			public final int columnIndex;
			public final int value;

			public Element(final int index, final int rowIndex, final int columnIndex, final int squareLength, final int rotations, final int value) {
				this.rotationIndex = getLeftRotationIndex(squareLength, index, rotations); // This calculates the rotation position of 'index'.
				this.rowIndex = rowIndex;
				this.columnIndex = columnIndex;
				this.value = value;
			}
		}

		// For every processable square: create a list of elements and then assign them to a matrix with their rotation position
		for(int square = 0; square < numberOfsquares; square++) {
			final int innerRows = rows - square * 2;
			final int innerColumns = columns - square * 2;
			final int squareLength = 2 * innerColumns + 2 * innerRows - 4; // Square length

			final List<Element> elements = new ArrayList<>();

			// Matrix's upper array
			for(int upper = 0; upper < innerColumns; upper++) {
				final int row = square;
				final int column = square + upper;
				elements.add(new Element(elements.size(), row, column, squareLength, rotations, matrix[row][column]));
			}

			// Matrix's right array
			for(int right = 1; right < innerRows; right++) {
				final int row = square + right;
				final int column = square + innerColumns - 1;
				elements.add(new Element(elements.size(), row, column, squareLength, rotations, matrix[row][column]));
			}

			// Matrix's lower array
			for(int lower = innerColumns - 2; lower > -1; lower--) {
				final int row = square + innerRows - 1;
				final int column = square + lower;
				elements.add(new Element(elements.size(), square + (innerRows - 1), square + lower, squareLength, rotations, matrix[row][column]));
			}

			// Matrix's left array
			for(int left = innerRows - 2; left > 0; left--) {
				final int row = square + left;
				final int column = square;
				elements.add(new Element(elements.size(), row, column, squareLength, rotations, matrix[row][column]));
			}

			// Rotate and assign
			for(Element e : elements) {
				final Element rotation = elements.get(e.rotationIndex);
				matrix[rotation.rowIndex][rotation.columnIndex] = e.value;
			}
		}
	}

	/**
	 * Given a matrix with 'rows' rows and 'columns' columns this method will calculate the number of processable inner squares.
	 */
	public static final int getNumberOfSquares(int rows, int columns) {
		int innerRows = 0;
		int innerColumns = 0;

		while(rows > 1) {
			rows -= 2;
			innerRows++;
		}

		while(columns > 1) {
			columns -= 2;
			innerColumns++;
		}

		return Math.min(innerRows, innerColumns);
	}

	/**
	 * This is method is the key to the problem solution success. Given an initial position 'initial position' It obtains a transformed
	 * position if an array o length 'length' when it rotates 'rotation' times.
	 */
	public static final int getLeftRotationIndex(final int length, int initialPosition, int rotations) {
		rotations = (rotations % length);

		for(int m = rotations; m > 0; m--) {
			if(initialPosition > 0)
				initialPosition--;
			else
				initialPosition = length - 1;
		}

		return initialPosition;
	}
}