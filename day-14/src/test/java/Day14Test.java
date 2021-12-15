import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;

public class Day14Test {
	@ParameterizedTest
	@AocFileSource(inputs = { @AocInputMapping(input = "test.txt", solution = "1588"),
			@AocInputMapping(input = "input.txt", solution = "3831") })
	void part1(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());
		var polymere = inputList.get(0);
		var rules = inputList.stream().filter(s -> s.contains("->"))
				.collect(Collectors.toMap(k -> k.split(" -> ")[0], v -> extractForAppend(v)));

		for (int i = 0; i < 10; i++) {
			var nextPolymere = new StringBuilder();
			for (int j = 0; j < polymere.length() - 1; j++) {
				var polymerePair = polymere.substring(j, j + 2);
				nextPolymere.append(rules.get(polymerePair));
			}
			polymere = polymere.substring(0, 1) + nextPolymere.toString();
		}

		var histogram = polymere.codePoints().boxed()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		var max = histogram.entrySet().stream().max((e1, e2) -> Long.compare(e1.getValue(), e2.getValue())).get();
		var min = histogram.entrySet().stream().min((e1, e2) -> Long.compare(e1.getValue(), e2.getValue())).get();
		var res = Math.subtractExact(max.getValue(), min.getValue());

		assertEquals(solution, Long.toString(res));
	}

	private String extractForAppend(String v) {
		var middle = v.split(" -> ")[1];
		var last = (v.split(" -> ")[0]).substring(1, 2);
		return middle + last;
	}

	@ParameterizedTest
	@AocFileSource(inputs = { @AocInputMapping(input = "test.txt", solution = "2188189693529"),
			@AocInputMapping(input = "input.txt", solution = "5725739914282") })
	void part2(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());
		var polymere = new StringBuffer(inputList.get(0));
		var polymereMap = new HashMap<String, Long>();
		for (int i = 0; i < polymere.length() - 1; i++) {
			var key = polymere.substring(i, i + 2);
			polymereMap.put(key, polymereMap.getOrDefault(key, 0L) + 1);
		}

		var rules = inputList.stream().filter(s -> s.contains("->"))
				.collect(Collectors.toMap(k -> k.split(" -> ")[0], v -> v.split(" -> ")[1]));

		for (int i = 0; i < 40; i++) {
			var parents = polymereMap.entrySet().stream().filter(e -> e.getValue() != null && e.getValue() > 0)
					.map(e -> e.getKey()).toList();
			var firstNew = new HashMap<String, Long>();
			var secoundNew = new HashMap<String, Long>();

			for (var parent : parents) {
				var append = rules.get(parent);

				var first = parent.substring(0, 1) + append;
				var secound = append + parent.substring(1);

				firstNew.put(first, firstNew.getOrDefault(first, 0L) + polymereMap.get(parent));
				secoundNew.put(secound, secoundNew.getOrDefault(secound, 0L) + polymereMap.get(parent));
			}
			polymereMap = firstNew;
			for (var e : secoundNew.entrySet()) {
				polymereMap.put(e.getKey(), polymereMap.getOrDefault(e.getKey(), 0L) + e.getValue());
			}
		}

		var singleCharHistogram = new HashMap<String, Long>();
		for (var e : polymereMap.entrySet()) {
			var first = e.getKey().substring(0, 1);
			singleCharHistogram.put(first, singleCharHistogram.getOrDefault(first, 0L) + e.getValue());
		}
		// add last
		var lastChar = polymere.substring(polymere.length() - 1, polymere.length());
		singleCharHistogram.put(lastChar, singleCharHistogram.get(lastChar) + 1);

		var max = singleCharHistogram.entrySet().stream().max((e1, e2) -> Long.compare(e1.getValue(), e2.getValue()))
				.get();
		var min = singleCharHistogram.entrySet().stream().min((e1, e2) -> Long.compare(e1.getValue(), e2.getValue()))
				.get();
		
		System.out.println(max + " - " + min);
		System.out.println(singleCharHistogram.entrySet().stream().mapToLong(e -> e.getValue()).sum());
		var res = Math.subtractExact(max.getValue(), min.getValue());

		assertEquals(solution, Long.toString(res));
	}

}
