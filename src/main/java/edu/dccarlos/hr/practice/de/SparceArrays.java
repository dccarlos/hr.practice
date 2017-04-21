package edu.dccarlos.hr.practice.de;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class SparceArrays {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		
		List<String> nList = new ArrayList<>();
		List<String> qList = new ArrayList<>();
		
		IntStream.range(0, n).forEachOrdered(index -> {
			nList.add(in.next());
		});
		
		int q = in.nextInt();
		
		IntStream.range(0, q).forEachOrdered(index -> {
			qList.add(in.next());
		});
		
		// And the result is:
		qList.forEach(query -> {
			System.out.println(nList.stream().filter(nWord -> nWord.equals(query)).count());
		});
	}
}