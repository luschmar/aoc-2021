import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;

/**
 * 
 * TODO: after failing with a solo right-down solution, i used Dijkesra from the internet
 * so tomorrow I will implement my own Dijkstra
 *
 */

public class Day15Test {
	@ParameterizedTest
	@AocFileSource(inputs = { @AocInputMapping(input = "test.txt", solution = "40"),
			// @AocInputMapping(input = "test1.txt", solution = "fail"),
			@AocInputMapping(input = "input.txt", solution = "540") })
	void part1(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());

		MutableValueGraph<String, Integer> graph = ValueGraphBuilder.undirected().build();

		for (int x = 0; x < inputList.size(); x++) {
			for (int y = 0; y < inputList.get(x).length(); y++) {
				Integer.parseInt(Character.toString(inputList.get(x).charAt(y)));
				if (x - 1 >= 0) {
					graph.putEdgeValue(x + ":" + y, (x - 1) + ":" + y,
							Integer.parseInt(Character.toString(inputList.get(x - 1).charAt(y))));
				}

				if (y - 1 >= 0) {
					graph.putEdgeValue(x + ":" + y, x + ":" + (y - 1),
							Integer.parseInt(Character.toString(inputList.get(x).charAt(y - 1))));
				}

				if (x + 1 < inputList.get(x).length()) {
					graph.putEdgeValue(x + ":" + y, (x + 1) + ":" + y,
							Integer.parseInt(Character.toString(inputList.get(x + 1).charAt(y))));
				}

				if (y + 1 < inputList.get(x).length()) {
					graph.putEdgeValue(x + ":" + y, x + ":" + (y + 1),
							Integer.parseInt(Character.toString(inputList.get(x).charAt(y + 1))));
				}
			}
		}

		List<String> shortestPath = DijkstraWithPriorityQueue.findShortestPath(graph, "0:0",
				(inputList.size() - 1) + ":" + (inputList.get(0).length() - 1));

		int total = Integer.parseInt(
				Character.toString(inputList.get(inputList.size() - 1).charAt(inputList.get(0).length() - 1)));
		;
		for (int i = 0; i < shortestPath.size() - 1; i++) {
			total += graph.edgeValue(shortestPath.get(i), shortestPath.get(i + 1)).get();
		}

		assertEquals(solution, Integer.toString(total));
	}

	@ParameterizedTest
	@AocFileSource(inputs = { @AocInputMapping(input = "test.txt", solution = "315"),
			@AocInputMapping(input = "input.txt", solution = "fail") })
	void part2(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());

		var bigMaze = new ArrayList<String>(inputList);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < inputList.size(); j++) {
				final int ii = i + 1;
				var newLine = inputList.get(j).chars().mapToObj(Character::toString).mapToInt(Integer::parseInt)
						.map(a -> (a + ii) > 9 ? (a + ii) - 9 : (a + ii)).mapToObj(Integer::toString)
						.collect(Collectors.joining());
				bigMaze.add(newLine);
			}
		}

		for (int i = 0; i < bigMaze.size(); i++) {
			String newLine = bigMaze.get(i);
			for (int j = 0; j < 4; j++) {
				final int ii = j + 1;
				var newSegment = bigMaze.get(i).chars().mapToObj(Character::toString).mapToInt(Integer::parseInt)
						.map(a -> (a + ii) > 9 ? (a + ii) - 9 : (a + ii)).mapToObj(Integer::toString)
						.collect(Collectors.joining());

				newLine = newLine + newSegment;
			}
			bigMaze.set(i, newLine);
		}

		MutableValueGraph<String, Integer> graph = ValueGraphBuilder.undirected().build();

		graph.putEdgeValue("start", "0:0", Integer.parseInt(Character.toString(bigMaze.get(0).charAt(0))));
		graph.putEdgeValue((bigMaze.size() - 1) + ":" + (bigMaze.get(0).length() - 1), "end", Integer
				.parseInt(Character.toString(bigMaze.get((bigMaze.size() - 1)).charAt((bigMaze.get(0).length() - 1)))));

		for (int x = 0; x < bigMaze.size(); x++) {
			for (int y = 0; y < bigMaze.get(x).length(); y++) {
				if (x - 1 >= 0) {
					graph.putEdgeValue(x + ":" + y, (x - 1) + ":" + y,
							Integer.parseInt(Character.toString(bigMaze.get(x - 1).charAt(y))));
				}

				if (y - 1 >= 0) {
					graph.putEdgeValue(x + ":" + y, x + ":" + (y - 1),
							Integer.parseInt(Character.toString(bigMaze.get(x).charAt(y - 1))));
				}

				if (x + 1 < bigMaze.get(x).length()) {
					graph.putEdgeValue(x + ":" + y, (x + 1) + ":" + y,
							Integer.parseInt(Character.toString(bigMaze.get(x + 1).charAt(y))));
				}

				if (y + 1 < bigMaze.get(x).length()) {
					graph.putEdgeValue(x + ":" + y, x + ":" + (y + 1),
							Integer.parseInt(Character.toString(bigMaze.get(x).charAt(y + 1))));
				}
			}
		}

		List<String> shortestPath = DijkstraWithPriorityQueue.findShortestPath(graph, "start", "end");

		System.out.println((bigMaze.size() - 1) + ":" + (bigMaze.get(0).length() - 1));

		int total = Integer
				.parseInt(Character.toString(bigMaze.get(bigMaze.size() - 1).charAt(bigMaze.get(0).length() - 1)));
		for (int i = 0; i < shortestPath.size() - 1; i++) {
			System.out.println(shortestPath.get(i) + " " + shortestPath.get(i + 1) + " -> "
					+ graph.edgeValue(shortestPath.get(i), shortestPath.get(i + 1)).get());

			total += graph.edgeValue(shortestPath.get(i), shortestPath.get(i + 1)).get();
		}

		assertEquals(solution, Long.toString(total));
	}
}
