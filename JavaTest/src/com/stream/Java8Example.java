package com.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8Example {
	public static void main(String[] args) {
		findFirstPresent();
		// findFirst();
		// filterUsingCharacter();

	}

	public static void findFirstPresent() {
		Stream.of("a1", "a2", "a3").findFirst().ifPresent(System.out::println); // a1
	}

	public static void findFirst() {
		Arrays.asList("a1", "a2", "a3").stream().findFirst().ifPresent(System.out::println);
	}

	public static void filterUsingCharacter() {
		List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
		List<String> result = myList.stream().filter(obj -> obj.startsWith("c")).sorted().collect(Collectors.toList());
		System.out.println(result);
	}

}
