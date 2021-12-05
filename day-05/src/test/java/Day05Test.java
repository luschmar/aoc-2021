import org.junit.jupiter.params.ParameterizedTest;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.assertEquals;

class Day05Test {
	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="5"),
			@AocInputMapping(input="input.txt", solution="5698")
	}
	)
	void part1(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());
		 
		var vectorList = inputList.stream().filter(s -> !s.isEmpty()).map(Vector::new).collect(Collectors.toList());
		var maxX = vectorList.stream().mapToInt(v -> v.maxX()).max().getAsInt();
		var maxY = vectorList.stream().mapToInt(v -> v.maxY()).max().getAsInt();
		
		var oceanFloor = new OceanFloor(maxX, maxY);
		for(var v : vectorList) {
			if(v.isHorV()) {
				oceanFloor.drawVector(v);
			}
		}

		var res = oceanFloor.count();
		
		assertEquals(solution, Integer.toString(res));
	}
	
	class Vector {
		final Pattern p = Pattern.compile("(\\d+),(\\d+) -> (\\d+),(\\d+)");
		
		int x1;
		int y1;
		int x2;
		int y2;

		private String data;
		
		public Vector(String s) {
			data = s;
			 Matcher m = p.matcher(s);
			 m.matches();
			 x1 = Integer.parseInt(m.group(1));
			 y1 = Integer.parseInt(m.group(2));
			 x2 = Integer.parseInt(m.group(3));
			 y2 = Integer.parseInt(m.group(4)); 
		}
		
		boolean isVertical() {
			return x1 == x2;
		}
		
		boolean isHorizontal() {
			return y1 == y2;
		}
		
		boolean isHorV() {
			return isVertical() || isHorizontal();
		}
		
		boolean isDiagonal() {
			return !isHorV();
		}
		/**
	  	READ THE PUZZLE!!!
		 
		boolean isDiagonal() {
			if(isHorV()) {
				return false;
			}
			boolean diagonal = false;
			if(x1 < x2) {
				var jplus = y1+1;
				var jminus = y1-1;
				for(int i = x1+1; i <= x2; i++) {
					if(i == x2 && jplus == y2) {
						diagonal = true;
					}
					if(i == x2 && jminus == y2) {
						diagonal = true;
					}
					jplus++;
					jminus--;
				}
			}
			else {
				var jplus = y1+1;
				var jminus = y1-1;
				for(int i = x1-1; i >= x2; i--) {
					if(i == x2 && jplus == y2) {
						diagonal = true;
					}
					if(i == x2 && jminus == y2) {
						diagonal = true;
					}
					jplus++;
					jminus--;
				}
			}
			
			return diagonal;
		}**/
		
		int max() {
			return Arrays.asList(x1, y1, x2, y2).stream().mapToInt(v -> v).max().getAsInt();
		}
		
		int maxX() {
			return x1 > x2 ? x1 : x2;
		}
		
		int maxY() {
			return y1 > y2 ? y1 : y2;
		}
		
		int minX() {
			return x1 < x2 ? x1 : x2;
		}
		
		int minY() {
			return y1 < y2 ? y1 : y2;
		}
		
		@Override
		public String toString() {
			return data;
		}
		
		
	}
	
	class OceanFloor {
		private int[][] array;

		OceanFloor(int x, int y) {
			array = new int[x+1][y+1];
		}
		
		public void drawDiagonal(Day05Test.Vector v) {
			if(v.x1 < v.x2) {
				var yDir = v.y1 < v.y2 ? 1 : -1;
				var j = v.y1;
				for(int i = v.x1; i <= v.x2; i++) {
					array[i][j] = array[i][j] + 1;
					j += yDir;
				}
			}
			else {
				var yDir = v.y1 < v.y2 ? 1 : -1;
				var j = v.y1;
				for(int i = v.x1; i >= v.x2; i--) {
					array[i][j] = array[i][j] + 1;
					j += yDir;
				}
			}
		}

		void drawVector(Vector v) {
			for(int i = v.minX(); i <= v.maxX(); i++) {
				for(int j = v.minY(); j <= v.maxY(); j++) {
					array[i][j] = array[i][j] + 1;
				}
			}
		}
		
		int count() {
			int count = 0;
			for(int i = 0; i < array.length; i++) {
				for(int j = 0; j < array[i].length; j++) {
					if(array[i][j] > 1) {
						count++;
					}
				}
			}
			return count;
		}
		
		void draw() {
			for(int i = 0; i < array.length; i++) {
				for(int j = 0; j < array[i].length; j++) {
						System.out.print(array[i][j]);
				}
				System.out.println();
			}
		}
	}

	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="12"),
			@AocInputMapping(input="input.txt", solution="15463")
	}
	)
	void part2(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());
		 
		var vectorList = inputList.stream().filter(s -> !s.isEmpty()).map(Vector::new).collect(Collectors.toList());
		var max = vectorList.stream().mapToInt(v -> v.max()).max().getAsInt();
		
		var oceanFloor = new OceanFloor(max, max);
		for(var v : vectorList) {
			if(v.isHorV()) {
				oceanFloor.drawVector(v);
			}
			
			if(v.isDiagonal()) {
				oceanFloor.drawDiagonal(v);
			}
		}

		var res = oceanFloor.count();
		

		assertEquals(solution, Integer.toString(res));
	}

}