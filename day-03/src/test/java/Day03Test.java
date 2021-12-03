import org.junit.jupiter.params.ParameterizedTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.assertEquals;

class Day03Test {
	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="198"),
			@AocInputMapping(input="input.txt", solution="2498354")
	}
	)
	void part1(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());
		var bitHistogram = calcHistogram(inputList);
		var gammaRes = "";
		var epsilonRes = "";
		for(int i = 0; i < bitHistogram.length; i++) {
			if(bitHistogram[i] < inputList.size()-bitHistogram[i]) {
				gammaRes += "1";
				epsilonRes += "0";
			}else {
				gammaRes += "0";
				epsilonRes += "1";
			}
		}

		var result = Integer.parseUnsignedInt(gammaRes, 2) * Integer.parseUnsignedInt(epsilonRes, 2);
		assertEquals(solution, Integer.toString(result));
	}

	int[] calcHistogram(List<String> input) {
		var length = input.get(0).length();
		var res = new int[length];
		for(var s : input) {
			for(int i = 0; i < length; i++) {
				if(s.substring(i, i+1).equals("1")) {
					res[i] = res[i] + 1;
				}
			}
		}
		return res;
	}

	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="230"),
			@AocInputMapping(input="input.txt", solution="3277956")
		}
	)
	void part2(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());
		var length = inputList.get(0).length();
		
		List<String> oxygenList = new ArrayList<String>(inputList);
		for(int i = 0; oxygenList.size() > 1; i = ++i % length) {
			var bitHistogram = calcHistogram(oxygenList);
			final String toKeepString;
			if(bitHistogram[i] >= oxygenList.size()-bitHistogram[i]) {
				toKeepString ="1";
			} else {
				toKeepString ="0";
			}
			final int index = i;
			oxygenList = oxygenList.stream().filter(s -> s.substring(index, index+1).equals(toKeepString)).collect(Collectors.toList());
		}
		
		List<String> co2List = new ArrayList<String>(inputList);
		for(int i = 0; co2List.size() > 1; i = ++i % length) {
			var bitHistogram = calcHistogram(co2List);
			final String toKeepString;
			if(bitHistogram[i] >= co2List.size()-bitHistogram[i]) {
				toKeepString ="0";
			} else {
				toKeepString ="1";
			}
			final int index = i;
			co2List = co2List.stream().filter(s -> s.substring(index, index+1).equals(toKeepString)).collect(Collectors.toList());
		}
	
		var result = Integer.parseUnsignedInt(oxygenList.get(0), 2) *  Integer.parseUnsignedInt(co2List.get(0), 2);
		assertEquals(solution, Integer.toString(result));
	}
}