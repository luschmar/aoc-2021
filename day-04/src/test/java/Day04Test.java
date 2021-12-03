import org.junit.jupiter.params.ParameterizedTest;

import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.assertEquals;

class Day04Test {
	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="fail"),
			@AocInputMapping(input="input.txt", solution="fail")
	}
	)
	void part1(Stream<String> input, String solution) {
		assertEquals(solution, "myres");
	}
	
	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="fail"),
			@AocInputMapping(input="input.txt", solution="fail")
	}
	)
	void part2(Stream<String> input, String solution) {
		assertEquals(solution, "myres");
	}
}