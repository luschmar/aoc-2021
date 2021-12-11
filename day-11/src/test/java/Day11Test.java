import org.junit.jupiter.params.ParameterizedTest;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day11Test {
	@ParameterizedTest
	@AocFileSource(inputs = { @AocInputMapping(input = "test.txt", solution = "1656"),
			@AocInputMapping(input="input.txt", solution="1571")
	})
	void part1(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());

		var intStreamArray = inputList.stream().map(s -> s.chars()).toArray();
		var octopusArray = new int[intStreamArray.length][];
		for (int i = 0; i < intStreamArray.length; i++) {
			octopusArray[i] = ((IntStream) intStreamArray[i]).mapToObj(Character::toString).mapToInt(Integer::parseInt)
					.toArray();
		}
		var workArray = new int[10][10];
		
		int flash = 0;
		for (int step = 0; step < 100; step++) {
			for (int i = 0; i < octopusArray.length; i++) {
				for (int j = 0; j < octopusArray.length; j++) {
					octopusArray[i][j]++;
				}
			}
			
			for(int c = 0; c < 100; c++) {
				for (int i = 0; i < octopusArray.length; i++) {
					for (int j = 0; j < octopusArray.length; j++) {
						if (workArray[i][j] == 0 && octopusArray[i][j] > 9) {
							increaseArount(octopusArray, i, j);
							workArray[i][j] = 1;
						}
					}
				}
			}
			
			

			System.out.println("Step " + (step + 1));
			printArray(octopusArray);
			// count flash
			for (int i = 0; i < octopusArray.length; i++) {
				for (int j = 0; j < octopusArray.length; j++) {
					if (octopusArray[i][j] > 9) {
						flash++;
						octopusArray[i][j] = 0;
					}

					workArray[i][j] = 0;
				}
			}
		}
		assertEquals(solution, Long.toString(flash));
	}

	private void increaseArount(int[][] octopusArray, int i, int j) {
		if (i - 1 >= 0) {
			octopusArray[i - 1][j]++;
		}
		if (i - 1 >= 0 && j + 1 < octopusArray.length) {
			octopusArray[i - 1][j + 1]++;
		}
		if (i - 1 >= 0 && j - 1 >= 0) {
			octopusArray[i - 1][j - 1]++;
		}
		if (j - 1 >= 0) {
			octopusArray[i][j - 1]++;
		}
		if (i + 1 < octopusArray.length && j - 1 >= 0) {
			octopusArray[i + 1][j - 1]++;
		}
		if (i + 1 < octopusArray.length) {
			octopusArray[i + 1][j]++;
		}
		if (j + 1 < octopusArray.length) {
			octopusArray[i][j + 1]++;
		}
		if (i + 1 < octopusArray.length && j + 1 < octopusArray.length) {
			octopusArray[i + 1][j + 1]++;
		}
	}

	private void printArray(int[][] octopusArray) {
		for (int i = 0; i < octopusArray.length; i++) {
			for (int j = 0; j < octopusArray.length; j++) {
				if (octopusArray[i][j] > 9) {
					System.out.print("x");
				} else {
					System.out.print(octopusArray[i][j]);
				}
			}
			System.out.println();
		}
	}

	@ParameterizedTest
	@AocFileSource(inputs = { @AocInputMapping(input = "test.txt", solution = "195"),
			@AocInputMapping(input = "input.txt", solution = "387") 
			})
	void part2(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());

		var intStreamArray = inputList.stream().map(s -> s.chars()).toArray();
		var octopusArray = new int[intStreamArray.length][];
		for (int i = 0; i < intStreamArray.length; i++) {
			octopusArray[i] = ((IntStream) intStreamArray[i]).mapToObj(Character::toString).mapToInt(Integer::parseInt)
					.toArray();
		}
		var workArray = new int[10][10];
		
		int step = 0;
		for (int flash = 0; flash < 100; ) {
			flash = 0;
			for (int i = 0; i < octopusArray.length; i++) {
				for (int j = 0; j < octopusArray.length; j++) {
					octopusArray[i][j]++;
				}
			}
			
			for(int c = 0; c < 100; c++) {
				for (int i = 0; i < octopusArray.length; i++) {
					for (int j = 0; j < octopusArray.length; j++) {
						if (workArray[i][j] == 0 && octopusArray[i][j] > 9) {
							increaseArount(octopusArray, i, j);
							workArray[i][j] = 1;
						}
					}
				}
			}
			
			

			System.out.println("Step " + (step + 1));
			printArray(octopusArray);
			// count flash
			
			for (int i = 0; i < octopusArray.length; i++) {
				for (int j = 0; j < octopusArray.length; j++) {
					if (octopusArray[i][j] > 9) {
						flash++;
						octopusArray[i][j] = 0;
					}

					workArray[i][j] = 0;
				}
			}
			step++;
		}
		assertEquals(solution, Long.toString(step));
	}
}
