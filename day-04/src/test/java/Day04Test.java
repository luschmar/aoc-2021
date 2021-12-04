import org.junit.jupiter.params.ParameterizedTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.assertEquals;

class Day04Test {
	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="4512"),
			@AocInputMapping(input="input.txt", solution="33462")
	}
	)
	void part1(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());
		var numbers = inputList.get(0);
		
		var boards = readBoards(inputList);
		
		var win = false;
		var lastNumber = 0;
		Board winBoard = null;
		
		var numnbersArray = Arrays.stream(numbers.split(",")).mapToInt(Integer::parseInt).toArray();
		for(int i = 0; i < numnbersArray.length && !win; i++) {
			lastNumber = numnbersArray[i];
			for(var b : boards) {
				if(b.crossNumber(lastNumber)) {
					win = true;
					winBoard = b;
				}
			}
		}
		
		var res = winBoard.sum() * lastNumber;
		assertEquals(solution, Integer.toString(res));
	}
	
	private List<Board> readBoards(List<String> inputList) {
		List<String> copy = new ArrayList<String>(inputList);
		copy.remove(0);
		List<Board> boards = new ArrayList<Board>();
		
		for(int i = 0; i < copy.size(); i += 6) {
			List<String> boardList = copy.subList(i+1, i+6);
			boards.add(new Board(boardList));
		}
		
		
		return boards;
	}

	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="1924"),
			@AocInputMapping(input="input.txt", solution="30070")
	}
	)
	void part2(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());
		var numbers = inputList.get(0);
		
		var boards = readBoards(inputList);
		
		var lastNumber = 0;
		var lastWinNumber = 0;
		Board winBoard = null;
		
		var numnbersArray = Arrays.stream(numbers.split(",")).mapToInt(Integer::parseInt).toArray();
		for(int i = 0; i < numnbersArray.length; i++) {
			lastNumber = numnbersArray[i];
			List<Board> toRemove = new ArrayList<Board>();
			for(int j = 0; j < boards.size(); j++) {
				var b = boards.get(j);
				if(b.crossNumber(lastNumber)) {
					winBoard = boards.get(j);
					lastWinNumber = lastNumber;
					toRemove.add(boards.get(j));
				}
			}
			boards.removeAll(toRemove);
		}
		
		var res = winBoard.sum() * lastWinNumber;
		assertEquals(solution, Integer.toString(res));
	}
}