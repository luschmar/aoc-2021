import org.junit.jupiter.params.ParameterizedTest;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.arraycopy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day06Test {
	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="5934"),
			@AocInputMapping(input="input.txt", solution="349549")
	}
	)
	void part1(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());
		var initialPopulation = Arrays.stream(inputList.get(0).split(",")).map(Integer::parseInt).mapToInt(Integer::intValue).toArray();
		var fastBreeder = new int[7];
		for(var gen : initialPopulation) {
			fastBreeder[gen+1] += 1;
		}
		var slowBreeder = new int[9];


		for(int i = 0; i <= 80; i++) {
			var newFb = fastBreeder[0];
			var newSb = slowBreeder[0];
			arraycopy(fastBreeder, 1, fastBreeder, 0, fastBreeder.length-1);
			arraycopy(slowBreeder, 1, slowBreeder, 0, slowBreeder.length-1);
			fastBreeder[6] = newFb + newSb;
			slowBreeder[8] = newFb + newSb;
			System.out.println("Day "+i);
			System.out.println("fastBreeder: " + Arrays.stream(fastBreeder).mapToObj(Integer::toString).collect(Collectors.joining(",")));
			System.out.println("slowBreeder: " + Arrays.stream(slowBreeder).mapToObj(Integer::toString).collect(Collectors.joining(",")));
		}

		var res = Arrays.stream(fastBreeder).sum() + Arrays.stream(slowBreeder).sum();
		assertEquals(solution, Long.toString(res));
	}

	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="26984457539"),
			@AocInputMapping(input="input.txt", solution="1589590444365")
	}
	)
	void part2(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());
		var initialPopulation = Arrays.stream(inputList.get(0).split(",")).map(Integer::parseInt).mapToInt(Integer::intValue).toArray();
		var fastBreeder = new long[7];
		for(var gen : initialPopulation) {
			fastBreeder[gen+1] += 1;
		}
		var slowBreeder = new long[9];

		for(int i = 0; i <= 256; i++) {
			var newFb = fastBreeder[0];
			var newSb = slowBreeder[0];
			arraycopy(fastBreeder, 1, fastBreeder, 0, fastBreeder.length-1);
			arraycopy(slowBreeder, 1, slowBreeder, 0, slowBreeder.length-1);
			fastBreeder[6] = newFb + newSb;
			slowBreeder[8] = newFb + newSb;
			System.out.println("Day "+i);
			System.out.println("fastBreeder: " + Arrays.stream(fastBreeder).mapToObj(Long::toString).collect(Collectors.joining(",")));
			System.out.println("slowBreeder: " + Arrays.stream(slowBreeder).mapToObj(Long::toString).collect(Collectors.joining(",")));
		}

		var res = Arrays.stream(fastBreeder).sum() + Arrays.stream(slowBreeder).sum();
		assertEquals(solution, Long.toString(res));
	}
}
