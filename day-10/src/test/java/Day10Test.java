import org.junit.jupiter.params.ParameterizedTest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day10Test {
	@ParameterizedTest
	@AocFileSource(inputs = { @AocInputMapping(input = "test.txt", solution = "26397"),
			@AocInputMapping(input = "input.txt", solution = "369105") })
	void part1(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());

		long res = 0L;
		for (var l : inputList) {
			res += scoreFirstPart(l);
		}
		assertEquals(solution, Long.toString(res));
	}

	private long scoreFirstPart(String line) {
		long res = 0L;
		Deque<String> stack = new ArrayDeque<String>();
		for (var c : line.chars().mapToObj(Character::toString).toList()) {
			if (c.equals("(") || c.equals("{") || c.equals("<") || c.equals("[")) {
				stack.push(c);
			}
			if (c.equals(")") || c.equals("}") || c.equals(">") || c.equals("]")) {
				var expected = stack.pop();
				if (expected.equals("(")) {
					expected = ")";
				}
				if (expected.equals("{")) {
					expected = "}";
				}
				if (expected.equals("<")) {
					expected = ">";
				}
				if (expected.equals("[")) {
					expected = "]";
				}

				if (!c.equals(expected)) {
					System.out.println(line);
					System.out.println(c);
					if (c.equals(")")) {
						return 3;
					} else if (c.equals("]")) {
						return 57;
					} else if (c.equals("}")) {

						return 1197;
					} else if (c.equals(">")) {
						return 25137;
					}
				}
			}
		}

		return res;
	}

	private long completeSyntax(String line) {
		long res = 0L;
		Deque<String> stack = new ArrayDeque<String>();
		for (var c : line.chars().mapToObj(Character::toString).toList()) {
			if (c.equals("(") || c.equals("{") || c.equals("<") || c.equals("[")) {
				stack.push(c);
			}
			if (c.equals(")") || c.equals("}") || c.equals(">") || c.equals("]")) {
				var expected = stack.pop();
				if (expected.equals("(")) {
					expected = ")";
				}
				if (expected.equals("{")) {
					expected = "}";
				}
				if (expected.equals("<")) {
					expected = ">";
				}
				if (expected.equals("[")) {
					expected = "]";
				}

				if (!c.equals(expected)) {
					System.out.println(line);
					System.out.println(c);
					if (c.equals(")")) {
						return 0;
					} else if (c.equals("]")) {
						return 0;
					} else if (c.equals("}")) {

						return 0;
					} else if (c.equals(">")) {
						return 0;
					}
				}
			}
		}

		while (!stack.isEmpty()) {
			var missing = stack.pop();
			res *= 5;
			if (missing.equals("(")) {
				res += 1;
			} else if (missing.equals("[")) {
				res += 2;
			} else if (missing.equals("{")) {
				res += 3;

			} else if (missing.equals("<")) {
				res += 4;
			}
		}

		return res;
	}

	@ParameterizedTest
	@AocFileSource(inputs = { @AocInputMapping(input = "test.txt", solution = "288957"),
			@AocInputMapping(input = "input.txt", solution = "3999363569") })
	void part2(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());

		long res = 0L;
		List<Long> bla = new ArrayList<Long>();
		for (var l : inputList) {
			bla.add(completeSyntax(l));
		}
		bla = bla.stream().filter(l -> l > 0).sorted().toList();

		assertEquals(solution, Long.toString(bla.get((bla.size() - 1) / 2)));
	}
}
