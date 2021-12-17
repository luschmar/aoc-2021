import org.junit.jupiter.params.ParameterizedTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day17Test {
	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="9"),
			@AocInputMapping(input="input.txt", solution="fail")
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
		var solutions = new ArrayList<Pair>();
		for(var x = 0; x < x0; x++) {
			for(var y = 0; y < Math.abs(y0); y++) {
				int vX = x;
				int vY = y;
				int pX = 0;
				int pY = 0;
				System.out.print(x+":"+y +"   ");
				while(x1 > pX  && y1 < pY) {
					pX += vX--;
					pY += vY--;
					vX = vX <= 0 ? 0 : vX;
					System.out.print(pX+":"+pY+"  ");
					if(pX >= x0 && pX <= x1 && pY >= y0 && pY <= y1) {
						System.out.println("");
						System.out.println(pX+" > "+x0+" && "+pX+" < "+x1+" && "+pY+" > "+y0+" && "+pY+" < "+y1);
						System.out.println("yuhee");
						solutions.add(new Pair(x, y));
					}
				}
				System.out.println();
			}
		}
		
		System.out.println("----------------");
		for(var s : solutions) {
			System.out.println(s);
		}
	
		var max = solutions.stream().mapToInt(m -> m.y).max().getAsInt();

		assertEquals(solution, Long.toString(max));
	}
	
	record Pair(int x, int y) {
		
	}

	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="fail"),
			@AocInputMapping(input="input.txt", solution="fail")
	}
	)
	void part2(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());

		var res = 10l;

		assertEquals(solution, Long.toString(res));
	}
}
