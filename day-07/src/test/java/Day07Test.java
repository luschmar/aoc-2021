import org.junit.jupiter.params.ParameterizedTest;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.arraycopy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day07Test {
	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="fail"),
			@AocInputMapping(input="input.txt", solution="fail")
	}
	)
	void part1(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());

		long res= 0;
		assertEquals(solution, Long.toString(res));
	}

	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="fail"),
			@AocInputMapping(input="input.txt", solution="fail")
	}
	)
	void part2(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());

		long res= 0;
		assertEquals(solution, Long.toString(res));
	}
}
