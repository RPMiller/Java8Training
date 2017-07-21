package answers;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Created by ryanmiller on 5/26/17.
 */
public class Training2Collectors {
	@Test
	public void streamToList() {
		final String[] strings = {"A", "B", "C"};
		final Stream<String> stringStream = Stream.of(strings);
		final List<String> stringList = stringStream.collect(Collectors.toList());

		assertEquals("The list does not have enough elements", strings.length, stringList.size());
		for (int i = 0; i < strings.length; i++) {
			assertEquals("index incorrect : " + i, strings[i], stringList.get(i));
		}
	}

	@Test
	public void groupBy_firstCharacter() {
		final String[] strings = {"Hello", "World", "Hola", "Howdy"};
		final Stream<String> stringStream = Stream.of(strings);
		final Map<Character, List<String>> collect = stringStream.collect(Collectors.groupingBy(x -> x.charAt(0)));

		List<String> hCharacters = collect.get('H');
		List<String> wCharacters = collect.get('W');

		assertEquals(Arrays.asList("Hello", "Hola", "Howdy"), hCharacters);
		assertEquals(Arrays.asList("World"), wCharacters);
	}

	@Test
	public void concatStrings_withSpaceBetweenWords() {
		final Stream<String> stream = Stream.of("Hello", "World");

		String lambdaResult = stream.collect(Collectors.joining(" "));

		assertEquals("Hello World", lambdaResult);
	}
}
