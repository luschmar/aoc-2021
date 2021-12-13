import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;

public class Day13Test {
	@ParameterizedTest
	@AocFileSource(inputs = { @AocInputMapping(input = "test.txt", solution = "17"),
			@AocInputMapping(input = "input.txt", solution = "592") })
	void part1(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());
		var points = inputList.stream().filter(s -> s.indexOf(",") > 0).map(s -> s.split(",")).map(s -> {
			return new Point(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
		}).toList();

		var instructions = inputList.stream().filter(s -> s.indexOf(",") < 0 && !s.isBlank()).toList();
		var inst = instructions.get(0);
		if (inst.startsWith("fold along y=")) {
			var foldLine = Integer.parseInt(inst.replace("fold along y=", ""));
			points.stream().filter(p -> p.y > foldLine).forEach(x -> x.mirrorY(foldLine));
		}

		if (inst.startsWith("fold along x=")) {
			var foldLine = Integer.parseInt(inst.replace("fold along x=", ""));
			points.stream().filter(p -> p.x > foldLine).forEach(x -> x.mirrorX(foldLine));
		}
		

		var count = points.stream().distinct().toList().size();
		assertEquals(solution, Long.toString(count));
	}

	class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		void mirrorX(int fold) {
			var v = (fold * 2);
			x = v - x;
		}

		void mirrorY(int fold) {
			var v = (fold * 2);
			y = v - y;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + x;
			result = prime * result + y;
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
			Point other = (Point) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return x + "," + y;
		}

		private Day13Test getEnclosingInstance() {
			return Day13Test.this;
		}

	}

	@ParameterizedTest
	@AocFileSource(inputs = { @AocInputMapping(input = "test.txt", solution = """
			#####
			#...#
			#...#
			#...#
			#####
			"""),
	@AocInputMapping(input="input.txt", solution="""
			..##..##...##....##.####.####.#..#.#..#
			...#.#..#.#..#....#.#....#....#.#..#..#
			...#.#....#..#....#.###..###..##...#..#
			...#.#.##.####....#.#....#....#.#..#..#
			#..#.#..#.#..#.#..#.#....#....#.#..#..#
			.##...###.#..#..##..####.#....#..#..##.
			""")
	})
	void part2(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());
		var points = inputList.stream().filter(s -> s.indexOf(",") >= 0).map(s -> s.split(",")).map(s -> {
			return new Point(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
		}).toList();
		
		//printPoints(points);

		var instructions = inputList.stream().filter(s -> s.indexOf(",") < 0 && !s.isBlank()).toList();
		for (var inst : instructions) {
			if (inst.startsWith("fold along y=")) {
				var foldLine = Integer.parseInt(inst.replace("fold along y=", ""));
				points.stream().filter(p -> p.y > foldLine).forEach(p -> p.mirrorY(foldLine));
			}

			if (inst.startsWith("fold along x=")) {
				var foldLine = Integer.parseInt(inst.replace("fold along x=", ""));
				points.stream().filter(p -> p.x > foldLine).forEach(p -> p.mirrorX(foldLine));
			}
			//printPoints(points);
		}

		printPoints(points);

		var maxX = points.stream().mapToInt(p -> p.x).max().getAsInt()+1;
		var maxY = points.stream().mapToInt(p -> p.y).max().getAsInt()+1;
		String result = "";
		for (int j = 0; j < maxY; j++) {
			for (int i = 0; i < maxX; i++) {
				if (points.contains(new Point(i, j))) {
					result = result + "#";
				} else {
					result = result + ".";
				}
			}
			result = result + "\n";
		}

		assertEquals(solution, result);
	}

	private void printPoints(List<Day13Test.Point> points) {
		var maxX = points.stream().mapToInt(p -> p.x).max().getAsInt()+1;
		var maxY = points.stream().mapToInt(p -> p.y).max().getAsInt()+1;
		for (int j = 0; j < maxY; j++) {
			for (int i = 0; i < maxX; i++) {
				if (points.contains(new Point(i, j))) {
					System.out.print("#");
				} else {
					System.out.print(".");
				}
			}
			System.out.println();
		}		
		System.out.println();
	}
}
