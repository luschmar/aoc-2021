import org.junit.jupiter.params.ParameterizedTest;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day07Test {
	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="37"),
			@AocInputMapping(input="input.txt", solution="323647")
	}
	)
	void part1(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());
		var values = Arrays.asList(inputList.get(0).split(","));
		var sum = new int[values.size()];
		for(int i = 0; i < values.size(); i++) {
			
			for(int j = 0; j < sum.length; j++) {
				sum[j] += Math.abs(Integer.parseInt(values.get(i)) - j);
			}
		}
				
		long res = Arrays.stream(sum).min().getAsInt();
		assertEquals(solution, Long.toString(res));
	}

	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="168"),
			@AocInputMapping(input="input.txt", solution="87640209")
	}
	)
	void part2(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());
		var values = Arrays.asList(inputList.get(0).split(","));
		var sum = new int[values.size()];
		for(int i = 0; i < values.size(); i++) {
			int bla = Integer.parseInt(values.get(i));
			for(int j = 0; j < sum.length; j++) {
				sum[j] += IntStream.range(1, Math.abs(bla - j )+1).sum();
			}
		}
		System.out.println(Arrays.stream(sum).mapToObj(Integer::toString).collect(Collectors.joining(",")));
				
		long res = Arrays.stream(sum).min().getAsInt();
		assertEquals(solution, Long.toString(res));
	}
}
