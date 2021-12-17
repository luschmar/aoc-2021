import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
				while(x1 >= pX  && y1 <= pY) {
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
			@AocInputMapping(input="input.txt", solution="fail")
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
				while(x1 >= pX  && y1 <= pY) {
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
				//System.out.println();
			}
		}
		
		System.out.println("----------------");
		for(var s : solutions) {
			System.out.println(s);
		}
	
		var count = solutions.size();

		assertEquals(solution, Long.toString(count));
	}
}
