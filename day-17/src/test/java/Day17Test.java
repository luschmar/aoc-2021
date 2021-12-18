import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;


public class Day17Test {
	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="45"),
			@AocInputMapping(input="input.txt", solution="5253")
	}
	)
	void part1(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());
		var line = inputList.get(0);
		
		var xStart = line.indexOf("x=");
		var xEnd = line.indexOf(", y=");
		var xRange = line.substring(xStart+2, xEnd);
		var x0 = Integer.parseInt(xRange.split("\\.\\.")[0]);
		var x1 = Integer.parseInt(xRange.split("\\.\\.")[1]);
		var yRange = line.substring(xEnd+4, line.length());
		var y0 = Integer.parseInt(yRange.split("\\.\\.")[0]);
		var y1 = Integer.parseInt(yRange.split("\\.\\.")[1]);
		
		System.out.println(line);
		var solutions = new ArrayList<PairWithMax>();
		for(var x = 0; x <= x1*x1; x++) {
			for(var y = (y1*y1)*-1; y < Math.abs(y1*y1); y++) {
				int vX = x;
				int vY = y;
				int pX = 0;
				int pY = 0;
				int maxY = 0;
				//System.out.print(x+":"+y +"   ");
				while(pX < x1  && pY > y0) {
					pX += vX--;
					pY += vY--;
					vX = vX <= 0 ? 0 : vX;
					maxY = Math.max(maxY, pY);
					//System.out.print(pX+":"+pY+"  ");
					if(x0 <= pX && pX <= x1 && y0 <= pY && pY <= y1) {
						//System.out.println("");
						//System.out.println(pX+" > "+x0+" && "+pX+" < "+x1+" && "+pY+" > "+y0+" && "+pY+" < "+y1);
						//System.out.println("yuhee");
						solutions.add(new PairWithMax(x, y, maxY));
					}
				}
				//System.out.println();
			}
		}
		
		System.out.println("----------------");
		for(var s : solutions) {
			System.out.println(s);
		}
	
		var max = solutions.stream().mapToInt(m -> m.maxY).max().getAsInt();

		assertEquals(solution, Long.toString(max));
	}
	
	record PairWithMax(int x, int y, int maxY) {
		
	}
	
	record Pair(int x, int y) {
		
	}

	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="112"),
			@AocInputMapping(input="input.txt", solution="1770")
	}
	)
	void part2(Stream<String> input, String solution) {		
		var inputList = input.collect(Collectors.toList());
		var line = inputList.get(0);
		
		var xStart = line.indexOf("x=");
		var xEnd = line.indexOf(", y=");
		var xRange = line.substring(xStart+2, xEnd);
		var x0 = Integer.parseInt(xRange.split("\\.\\.")[0]);
		var x1 = Integer.parseInt(xRange.split("\\.\\.")[1]);
		var yRange = line.substring(xEnd+4, line.length());
		var y0 = Integer.parseInt(yRange.split("\\.\\.")[0]);
		var y1 = Integer.parseInt(yRange.split("\\.\\.")[1]);
		
		System.out.println(line);
		var solutions = new HashSet<Pair>();
		for(var x = 0; x <= x1*x1; x++) {
			for(var y = (y1*y1)*-1; y < Math.abs(y1*y1); y++) {
				int vX = x;
				int vY = y;
				int pX = 0;
				int pY = 0;
				//System.out.print(x+":"+y +"   ");
				while(pX < x1  && pY > y0) {
					pX += vX--;
					pY += vY--;
					vX = vX <= 0 ? 0 : vX;
					//System.out.print(pX+":"+pY+"  ");
					if(x0 <= pX && pX <= x1 && y0 <= pY && pY <= y1) {
						//System.out.println("");
						//System.out.println(pX+" > "+x0+" && "+pX+" < "+x1+" && "+pY+" > "+y0+" && "+pY+" < "+y1);
						//System.out.println("yuhee");
						solutions.add(new Pair(x, y));
					}
				}
				//System.out.println(y);
			}
		}
		
		//System.out.println("----------------");
		for(var s : solutions) {
			System.out.println(s);
		}
		//assertList(solutions);
	
		var count = solutions.size();

		assertEquals(solution, Long.toString(count));
	}

	private void assertList(HashSet<Pair> solutions) {
		var a = """
				23,-10  25,-9   27,-5   29,-6   22,-6   21,-7   9,0     27,-7   24,-5
				25,-7   26,-6   25,-5   6,8     11,-2   20,-5   29,-10  6,3     28,-7
				8,0     30,-6   29,-8   20,-10  6,7     6,4     6,1     14,-4   21,-6
				26,-10  7,-1    7,7     8,-1    21,-9   6,2     20,-7   30,-10  14,-3
				20,-8   13,-2   7,3     28,-8   29,-9   15,-3   22,-5   26,-8   25,-8
				25,-6   15,-4   9,-2    15,-2   12,-2   28,-9   12,-3   24,-6   23,-7
				25,-10  7,8     11,-3   26,-7   7,1     23,-9   6,0     22,-10  27,-6
				8,1     22,-8   13,-4   7,6     28,-6   11,-4   12,-4   26,-9   7,4
				24,-10  23,-8   30,-8   7,0     9,-1    10,-1   26,-5   22,-9   6,5
				7,5     23,-6   28,-10  10,-2   11,-1   20,-9   14,-2   29,-7   13,-3
				23,-5   24,-8   27,-9   30,-7   28,-5   21,-10  7,9     6,6     21,-5
				27,-10  7,2     30,-9   21,-8   22,-7   24,-9   20,-6   6,9     29,-5
				8,-2    27,-8   30,-5   24,-7""";
		var b = Arrays.stream(a.split("\\s+")).map(p -> new Pair(Integer.parseInt(p.split(",")[0]), Integer.parseInt(p.split(",")[1]))).collect(Collectors.toSet());
		
		 assertThat(b, Matchers.containsInAnyOrder(solutions.toArray()));
	    System.out.println(a);
	}
}
