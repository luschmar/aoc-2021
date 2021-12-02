import org.junit.jupiter.params.ParameterizedTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day02Test {
	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="150"),
			@AocInputMapping(input="input.txt", solution="1488669")
	}
	)
	void part1(Stream<String> input, String solution) {
		var lines = input.toArray(String[]::new);

		int hpos = 0;
		int depth = 0;
		for(int i = 0; i < lines.length; i++) {
			if(lines[i].startsWith("forward ")) {
				hpos += Integer.parseInt(lines[i].substring(8));
			}
			if(lines[i].startsWith("down ")) {
				depth += Integer.parseInt(lines[i].substring(5));
			}
			if(lines[i].startsWith("up ")) {
				depth -= Integer.parseInt(lines[i].substring(3));
			}
		}

		var result = hpos*depth;
		assertEquals(Integer.parseInt(solution), result);
	}

	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="900"),
			@AocInputMapping(input="input.txt", solution="1176514794")
		}
	)
	void part2(Stream<String> input, String solution) {
		var lines = input.toArray(String[]::new);

		int hpos = 0;
		int depth = 0;
		int aim = 0;
		for(int i = 0; i < lines.length; i++) {
			if(lines[i].startsWith("forward ")) {
				hpos += Integer.parseInt(lines[i].substring(8));
				depth += aim * Integer.parseInt(lines[i].substring(8));
			}
			if(lines[i].startsWith("down ")) {
				aim += Integer.parseInt(lines[i].substring(5));
			}
			if(lines[i].startsWith("up ")) {
				aim -= Integer.parseInt(lines[i].substring(3));
			}
		}

		var result = hpos*depth;
		assertEquals(Integer.parseInt(solution), result);
	}
}