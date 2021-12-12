import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;

public class Day12Test {
	@ParameterizedTest
	@AocFileSource(inputs = { @AocInputMapping(input = "test-1.txt", solution = "10"),
			@AocInputMapping(input = "test-2.txt", solution = "19"),
			@AocInputMapping(input = "test-3.txt", solution = "226"),
			@AocInputMapping(input = "input.txt", solution = "4167") })
	void part1(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());
		var connectionList = new ArrayList<Connection>();

		for (var s : inputList) {
			var path = s.split("-");
			var caveS = path[0];
			var caveE = path[1];
			if (!caveE.equals("start") && !caveS.equals("end")) {
				connectionList.add(new Connection(caveS, caveE));
			}
			if (!caveS.equals("start") && !caveE.equals("end")) {
				connectionList.add(new Connection(caveE, caveS));
			}
		}

		var paths = new HashSet<Path>();
		var startConnections = connectionList.stream().filter(a -> a.caceS.equals("start")).toList();
		var connectionsWithoutStart = new ArrayList<Connection>(connectionList);
		for (var s : startConnections) {
			connectionsWithoutStart.remove(s);
		}
		for (var c : startConnections) {
			var p = new Path(c);
			paths.add(p);
			searchNext(p, paths, connectionsWithoutStart);
		}

		var pathToEnd = paths.stream().filter(a -> "end".equals(a.endCave)).toList();

		assertEquals(solution, Long.toString(pathToEnd.size()));
	}

	private boolean ensurePathDontHaveMultipleTimesSameSmallCave(Path p) {
		var histogram = p.connections.stream().map(Connection::caceS).filter(c -> Character.isLowerCase(c.charAt(0)))
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		return !histogram.values().stream().anyMatch(f -> f > 1);
	}

	private void searchNext(Day12Test.Path currentPath, HashSet<Day12Test.Path> paths, List<Connection> connections) {
		var end = currentPath.endCave;
		var nextConn = connections.stream().filter(c -> c.caceS.equals(end)).toList();
		for (var n : nextConn) {
			var p = new Path(currentPath);
			p.add(n);
			if (!ensurePathDontHaveMultipleTimesSameSmallCave(p)) {
				continue;
			}
			paths.add(p);
			var connWithout = new ArrayList<Connection>(connections);
			connWithout.remove(n);
			System.out.println(p);
			searchNext(p, paths, connWithout);
		}
	}

	record Connection(String caceS, String caveE) {
	}

	class Path {
		String endCave;
		List<Connection> connections = new ArrayList<Day12Test.Connection>();

		public Path(Connection c) {
			connections.add(c);
			endCave = c.caveE;
		}

		public Path(Day12Test.Path path) {
			endCave = path.connections.get(path.connections.size() - 1).caveE;
			this.connections = new ArrayList<Day12Test.Connection>(path.connections);
		}

		boolean canAdd(Connection c) {
			// if destination cave is lower and already visited
			// if (connections.stream().map(a -> a.caveE).filter(a ->
			// Character.isLowerCase(a.charAt(0)))
			// .anyMatch(a -> c.caveE.equals(a))) {
			// return false;
			// }

			// if this path is already taken
			if (connections.stream().anyMatch(a -> c.equals(a))) {
				return false;
			}

			return true;
		}

		void add(Connection c) {
			if (!c.caceS.equals(endCave)) {
				System.out.println("error " + c.caceS + "!=" + endCave);
			}
			connections.add(c);
			endCave = c.caveE;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + ((this.toString() == null) ? 0 : this.toString().hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Path other = (Path) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (this.toString() == null) {
				if (other.toString() != null)
					return false;
			} else if (!this.toString().equals(other.toString()))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return this.connections.stream().map(Connection::caceS).collect(Collectors.joining(",")) + "," + endCave;
		}

		private Day12Test getEnclosingInstance() {
			return Day12Test.this;
		}
	}

	@ParameterizedTest
	@AocFileSource(inputs = { @AocInputMapping(input = "test-1.txt", solution = "36"),
			@AocInputMapping(input = "test-2.txt", solution = "103"),
			@AocInputMapping(input = "test-3.txt", solution = "3509"),
			@AocInputMapping(input = "input.txt", solution = "98441") })
	void part2(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());
		var connectionList = new ArrayList<Connection>();

		for (var s : inputList) {
			var path = s.split("-");
			var caveS = path[0];
			var caveE = path[1];
			if (!caveE.equals("start") && !caveS.equals("end")) {
				connectionList.add(new Connection(caveS, caveE));
			}
			if (!caveS.equals("start") && !caveE.equals("end")) {
				connectionList.add(new Connection(caveE, caveS));
			}
		}

		var paths = new HashSet<Path>();
		var startConnections = connectionList.stream().filter(a -> a.caceS.equals("start")).toList();
		var connectionsWithoutStart = new ArrayList<Connection>(connectionList);
		for (var s : startConnections) {
			connectionsWithoutStart.remove(s);
		}
		for (var c : startConnections) {
			var p = new Path(c);
			paths.add(p);
			searchNext2(p, paths, connectionsWithoutStart);
		}

		var pathToEnd = paths.stream().filter(a -> "end".equals(a.endCave)).toList();

		var stringWithoutDuplicates = pathToEnd.stream().map(Path::toString).distinct().toList();

		for (var p : stringWithoutDuplicates) {
			System.out.println(p);
		}

		assertEquals(solution, Long.toString(stringWithoutDuplicates.size()));
	}

	private void searchNext2(Day12Test.Path currentPath, HashSet<Day12Test.Path> paths, List<Connection> connections) {
		var end = currentPath.endCave;
		var nextConn = connections.stream().filter(c -> c.caceS.equals(end)).toList();
		for (var n : nextConn) {
			var p = new Path(currentPath);
			p.add(n);
			if (oneSmallCaveTwiceAndOtherSmallCavesMaxOnce(p)) {
				continue;
			}
			paths.add(p);
			var connWithout = new ArrayList<Connection>(connections);
			if (canRemove(currentPath)) {
				connWithout.remove(n);
			}
			// System.out.println(p);
			searchNext2(p, paths, connWithout);
		}
	}

	private boolean canRemove(Day12Test.Path currentPath) {
		if (currentPath.endCave.equals("end")) {
			return true;
		}

		var histogram = currentPath.connections.stream().map(Connection::caceS)
				.filter(c -> Character.isLowerCase(c.charAt(0)))
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		if (histogram.values().stream().anyMatch(f -> f > 1)) {
			return true;
		}

		return false;
	}

	private boolean oneSmallCaveTwiceAndOtherSmallCavesMaxOnce(Path p) {
		var histogram = p.connections.stream().map(Connection::caceS).filter(c -> Character.isLowerCase(c.charAt(0)))
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		if (histogram.values().stream().anyMatch(f -> f > 1)) {
			// remove one with 2 and check again
			var any = histogram.entrySet().stream().filter(a -> a.getValue() == 2).findAny();
			if (any.isEmpty()) {
				return true;
			}
			histogram.remove(any.get().getKey());

			var ret = histogram.values().stream().anyMatch(f -> f > 1);
			return ret;
		}
		return false;
	}
}
