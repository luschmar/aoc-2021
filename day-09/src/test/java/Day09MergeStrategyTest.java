import org.junit.jupiter.params.ParameterizedTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day09MergeStrategyTest {
	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="1134"),
			@AocInputMapping(input="input.txt", solution="920448")
	}
	)
	void mergeStrategyForPart2(Stream<String> input, String solution) {
		var inputList = input.toList();
		
		var intStreamArray = inputList.stream().map(String::chars).toArray();
		var heightmap =  new int[intStreamArray.length][];
		for(int i = 0; i < intStreamArray.length; i++) {
			heightmap[i] = ((IntStream)intStreamArray[i]).mapToObj(Character::toString).mapToInt(Integer::parseInt).toArray();
		}
		List<Basin> basinList = new ArrayList<>();
		var currentBasin = new Basin();
		basinList.add(currentBasin);
		for(int i = 0; i < heightmap.length; i++) {
			for(int j = 0; j < heightmap[i].length; j++) {
				if(9 == heightmap[i][j]) {
					currentBasin = new Basin();
					basinList.add(currentBasin);
					continue;
				}
				currentBasin.addPoint(new PointRec(i, j), heightmap[i][j]);
				if(i - 1 >= 0) {
					var topPoint = new PointRec(i-1, j);
					var topBasinOpt = basinList.stream().filter(b -> b.points.contains(topPoint)).findAny();
					if(topBasinOpt.isPresent()) {
						var topBasin = topBasinOpt.get();
						if(currentBasin != topBasin) {
							currentBasin.merge(topBasin);
							basinList.remove(topBasin);
						}
					}
				}
			}
			currentBasin = new Basin();
			basinList.add(currentBasin);
		}

		var sorted = basinList.stream().mapToInt(b -> b.sum).sorted().toArray();
		long res = sorted[sorted.length-1]*sorted[sorted.length-2]*sorted[sorted.length-3];
		assertEquals(solution, Long.toString(res));
	}

	class Basin {
		Set<PointRec> points = new HashSet<>();
		int sum;
		int min = 9;

		void merge(Basin b) {
			points.addAll(b.points);
			sum += b.sum;
		}

		public void addPoint(PointRec pointRec, int value) {
			if(min > value) {
				min = value;
			}
			if(points.add(pointRec)) {
				sum++;
			}
		}

		@Override
		public String toString() {
			return "Basin{" +
			       "points=" + points +
			       ", sum=" + sum +
			       '}';
		}
	}

	record PointRec(int x, int y) {
	}
}
