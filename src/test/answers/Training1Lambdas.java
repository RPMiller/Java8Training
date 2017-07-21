package answers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Created by ryanmiller on 4/19/17.
 */
public class Training1Lambdas {
	@Test
	public void concatStrings() {
		Collection<String> strings = Arrays.asList("Hello", null, " ", "World");

		StringBuilder loopBuilder = new StringBuilder();
		for (String str : strings) {
			if (Objects.nonNull(str)) {
				loopBuilder.append(str);
			}
		}

		StringBuilder lambdaResult = new StringBuilder();
		strings.stream().filter(Objects::nonNull).forEach(lambdaResult::append);

		assertEquals(loopBuilder.toString(), lambdaResult.toString());
	}

	@Test
	public void factorial() {
		final int MAX_FACT_SIZE = 5; // 1 * 2 * 3 * 4
		int forLoopResult = 1;

		// Factorial
		for (int i = 1; i < MAX_FACT_SIZE; i++) {
			forLoopResult *= i;
		}

		int lambdaResult = IntStream.range(1, MAX_FACT_SIZE)
			.reduce((previous, current) -> previous * current)
			.getAsInt();

		assertEquals(forLoopResult, lambdaResult);
	}

	@Test
	public void anyItemsInListContain() {
		Collection<String> toSearch = Arrays.asList("Find the number of times bacon show up in this bacon string".split(" "));
		final String TO_FIND = "bacon";

		int foundInForLoop = 0;
		for (String val : toSearch) {
			if (TO_FIND.equals(val)) {
				foundInForLoop++;
			}
		}

		int foundInLambda  = (int)toSearch.stream()
			.filter(TO_FIND::equals)
			.count();

		assertEquals(foundInForLoop, foundInLambda);
	}

	@Test
	public void printNumbersInOrder() {
		Integer[] numbers = new Integer[]{5, 2, 4, 1, 3};

		// Copy so the original won't be sorted when running through the lambda
		List<Integer> workingCopy = Arrays.asList(Arrays.copyOf(numbers, numbers.length));

		StringBuilder sortedString = new StringBuilder();
		Collections.sort(workingCopy);

		for (Integer integer : workingCopy) {
			sortedString.append(integer);
		}

		StringBuilder sortedFromLambda = new StringBuilder(sortedString.length());
		Stream.of(numbers).sorted().forEach(sortedFromLambda::append);

		assertEquals(sortedString.toString(), sortedFromLambda.toString());
	}

	@Test
	public void doubleEachNumber() {
		List<Integer> numbers = Arrays.asList(1,2,3,4,5);

		List<Integer> doubledByLoop = new ArrayList<>(numbers.size());

		for (Integer number : numbers) {
			doubledByLoop.add(number * 2);
		}

		// Lets assume we want this as a list
		List<Integer> doubleByLambda = numbers.stream().map(number -> number * 2).collect(Collectors.toList());
		assertEquals("The list was not correct", doubledByLoop, doubleByLambda);
	}
}
