package ru.mirea;

import java.util.function.Consumer;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) {
		Consumer<String> consumer = str ->
				IntStream.range(1, str.length())
						.mapToObj(n -> n % 3 == 0 ?
								Character.toUpperCase(str.charAt(n - 1)) :
								str.charAt(n - 1))
						.forEach(System.out::print);
		consumer.accept("abcdefgh");
	}
}
