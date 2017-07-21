import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.DoubleConsumer;
import java.util.function.Function;
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

		StringBuilder lambdaResult = new StringBuilder(); // use a lambda to get the same result. Avoid Stream.collect for now we will cover that later

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

		int lambdaResult = 0; // Write this using a Lambda

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

		int foundInLambda = 0; // Write this using a Lambda

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
		List<Integer> doubleByLambda = null;
		assertEquals("The sizes of the lists were different", doubledByLoop, doubleByLambda);

		for (int i = 0; i < doubleByLambda.size(); i++) {
			assertEquals("Item in location " + i + " was incorrect", doubledByLoop.get(i), doubleByLambda.get(i));
		}
	}
}
