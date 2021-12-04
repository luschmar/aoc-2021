import org.junit.jupiter.params.ParameterizedTest;

import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.assertEquals;

class Day05Test {
	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="fail"),
			@AocInputMapping(input="input.txt", solution="fail")
	}
	)
	void part1(Stream<String> input, String solution) {
		int res = 1;
		
		assertEquals(solution, Integer.toString(res));
	}

	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="fail"),
			@AocInputMapping(input="input.txt", solution="fail")
	}
	)
	void part2(Stream<String> input, String solution) {
		int res = 1;
		
		assertEquals(solution, Integer.toString(res));
	}
}