import org.junit.jupiter.params.ParameterizedTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day03Test {
	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="true"),
			@AocInputMapping(input="input.txt", solution="true")
	}
	)
	void part1(Stream<String> input, String solution) {
		assertEquals(solution, "fail");
	}

	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="true"),
			@AocInputMapping(input="input.txt", solution="true")
		}
	)
	void part2(Stream<String> input, String solution) {
		assertEquals(solution, "fail");
	}
}