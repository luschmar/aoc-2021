import org.junit.jupiter.params.ParameterizedTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day01Test {
	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="7"),
			@AocInputMapping(input="input.txt", solution="1451")
		}
	)
	void part1(Stream<String> input, String solution) {
		Integer[] lines = input.map(Integer::parseInt).toArray(Integer[]::new);

		int result = 0;
		for(int i = 1; i < lines.length; i++) {
			if(lines[i-1] < lines[i]) {
				result++;
			}
		}

		assertEquals(Integer.parseInt(solution), result);
	}

	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="5"),
			@AocInputMapping(input="input.txt", solution="1395")
	}
	)
	void part2(Stream<String> input, String solution) {
		Integer[] lines = input.map(Integer::parseInt).toArray(Integer[]::new);

		int result = 0;
		for(int i = 3; i < lines.length; i++) {
			var sum1 = lines[i-3]+lines[i-2]+lines[i-1];
			var sum2 = lines[i-2]+lines[i-1]+lines[i];
			if(sum1 < sum2) {
				result++;
			}
		}

		assertEquals(Integer.parseInt(solution), result);
	}
}