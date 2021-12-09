import org.junit.jupiter.params.ParameterizedTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day09Test {
	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="15"),
			@AocInputMapping(input="input.txt", solution="528")
	}
	)
	void part1(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());
		
		var intStreamArray = inputList.stream().map(s -> s.chars()).toArray();
		var heightmap =  new int[intStreamArray.length][];
		for(int i = 0; i < intStreamArray.length; i++) {
			heightmap[i] = ((IntStream)intStreamArray[i]).mapToObj(Character::toString).mapToInt(Integer::parseInt).toArray();
		}
		int riskLevel = 0;
		for(int i = 0; i < heightmap.length; i++) {
			for(int j = 0; j < heightmap[i].length; j++) {
				boolean left = true, up = true, rigth = true, down = true;
				// check left
				if(i - 1 >= 0) {
					left = heightmap[i][j] < heightmap[i-1][j];
				}
				// check up
				if(j - 1 >= 0) {
					up = heightmap[i][j] < heightmap[i][j-1];
				}
				// check right
				if(j + 1 < heightmap[i].length) {
					rigth = heightmap[i][j] < heightmap[i][j+1];
				}
				// check down
				if(i + 1 < heightmap.length) {
					down = heightmap[i][j] < heightmap[i+1][j];
				}
				if(left && up && rigth && down) {
					riskLevel += heightmap[i][j]+1;
				}
			}
		}
		//int[][] heightmap = 
		
		long res = 2l;
		assertEquals(solution, Integer.toString(riskLevel));
	}

	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="1134"),
			@AocInputMapping(input="input.txt", solution="920448")
	}
	)
	void part2(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());
		
		var intStreamArray = inputList.stream().map(s -> s.chars()).toArray();
		var heightmap =  new int[intStreamArray.length][];
		for(int i = 0; i < intStreamArray.length; i++) {
			heightmap[i] = ((IntStream)intStreamArray[i]).mapToObj(Character::toString).mapToInt(Integer::parseInt).toArray();
		}
		int riskLevel = 0;
		List<Integer> basinList = new ArrayList<Integer>();
		
		for(int i = 0; i < heightmap.length; i++) {
			for(int j = 0; j < heightmap[i].length; j++) {
				boolean left = true, up = true, rigth = true, down = true;
				// check left
				if(i - 1 >= 0) {
					left = heightmap[i][j] < heightmap[i-1][j];
				}
				// check up
				if(j - 1 >= 0) {
					up = heightmap[i][j] < heightmap[i][j-1];
				}
				// check right
				if(j + 1 < heightmap[i].length) {
					rigth = heightmap[i][j] < heightmap[i][j+1];
				}
				// check down
				if(i + 1 < heightmap.length) {
					down = heightmap[i][j] < heightmap[i+1][j];
				}
				if(left && up && rigth && down) {
					basinList.add(mesureBasin(heightmap, i, j));
				}
			}
		}
		//int[][] heightmap = 
		
		var sorted = basinList.stream().mapToInt(Integer::intValue).sorted().toArray();
		long res = sorted[sorted.length-1]*sorted[sorted.length-2]*sorted[sorted.length-3];
		assertEquals(solution, Long.toString(res));
	}

	private int mesureBasin(int[][] heightmap, int x, int y) {
		var set = new HashSet<Coordinates>();
		set.add(new Coordinates(x, y));
		
		set.addAll(searchBackRecursive(heightmap, set, x, y));
		
		return set.size();
	}
	
	private Set<Coordinates> searchBackRecursive(int[][] heightmap, Set<Coordinates> basin, int x, int y) {
		// check left
		if(x - 1 >= 0 && heightmap[x-1][y] < 9) {
			var c = new Coordinates(x-1, y);
			if(!basin.contains(c)) {
				basin.add(c);
				searchBackRecursive(heightmap, basin, x-1, y);
			}
		}
		// check up
		if(y - 1 >= 0 && heightmap[x][y-1] < 9) {
			var c = new Coordinates(x, y-1);
			if(!basin.contains(c)) {
				basin.add(c);
				searchBackRecursive(heightmap, basin, x, y-1);
			}
		}
		// check right
		if(y + 1 < heightmap[x].length && heightmap[x][y+1] < 9) {
			var c = new Coordinates(x, y+1);
			if(!basin.contains(c)) {
				basin.add(c);
				searchBackRecursive(heightmap, basin, x, y+1);
			}
		}
		// check down
		if(x + 1 < heightmap.length && heightmap[x+1][y] < 9 ) {
			var c = new Coordinates(x+1, y);
			if(!basin.contains(c)) {
				basin.add(new Coordinates(x+1, y));
				searchBackRecursive(heightmap, basin, x+1, y);
			}
		}
		return basin;
	}
	
	class Coordinates {
		int x;
		int y;
		
		Coordinates(int x, int y) {
			this.x = x;
			this.y = y;
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
			Coordinates other = (Coordinates) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

		private Day09Test getEnclosingInstance() {
			return Day09Test.this;
		}
		
		
	}
}
