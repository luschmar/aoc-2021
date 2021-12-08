import org.junit.jupiter.params.ParameterizedTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day08Test {
	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="26"),
			@AocInputMapping(input="input.txt", solution="255")
	}
	)
	void part1(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());
		
		var count = 0;
		for(var s : inputList) {
			var output = Arrays.asList(s.split("\\|")[1].split("\\s"));
			for(var o : output) {
				if(o.length() == 2 || o.length() == 4 || o.length() == 3 || o.length() == 7) {
					count++;
				}
			}
		}

		assertEquals(solution, Long.toString(count));
	}

	@ParameterizedTest
	@AocFileSource(inputs = {
			@AocInputMapping(input="test.txt", solution="61229"),
			@AocInputMapping(input="input.txt", solution="982158")
	}
	)
	void part2(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());
		
		var count = 0;
		for(var s : inputList) {
			var output = Arrays.asList(s.split("\\|")[1].split("\\s")).stream().filter(b -> !b.isBlank()).collect(Collectors.toList());;
			var digit = "";
			var in = Arrays.stream(s.split("\\|")[0].split("\\s")).filter(b -> !b.isBlank()).collect(Collectors.toList());
			var map = analyseNumbers(in);
			
			for(var o : output) {
				var sorted = sortString(o);
				digit = digit + map.get(sorted);
			}
			count += Integer.parseInt(digit);
		}

		assertEquals(solution, Long.toString(count));
	}

	private String sortString(String o) {
		return o.chars().sorted().mapToObj(Character::toString).collect(Collectors.joining());
	}

	private Map<String, String> analyseNumbers(List<String> asList) {
		var one = asList.stream().filter(s -> s.length() == 2).findAny().get();
		var four = asList.stream().filter(s -> s.length() == 4).findAny().get();
		var seven = asList.stream().filter(s -> s.length() == 3).findAny().get();
		var eight = asList.stream().filter(s -> s.length() == 7).findAny().get();
		asList.remove(one);
		asList.remove(four);
		asList.remove(seven);
		asList.remove(eight);
		
		var fifeTwoThree = asList.stream().filter(s -> s.length() == 5).collect(Collectors.toList());
		asList.remove(fifeTwoThree);
		
		var zeroSixNine = asList.stream().filter(s -> s.length() == 6).collect(Collectors.toList());
		asList.remove(zeroSixNine);
		var six = zeroSixNine.stream().filter(t -> testSix(t, one)).findAny().get();
		zeroSixNine.remove(six);
		var zeroNine = zeroSixNine;
		var nine = zeroSixNine.stream().filter(t -> testNine(t, four)).findAny().get();
		zeroNine.remove(nine);
		var zero = zeroNine.get(0);

		var three = fifeTwoThree.stream().filter(t -> testIsThree(t, seven)).findAny().get();
		fifeTwoThree.remove(three);
		var fifeTwo = fifeTwoThree;
		var two = fifeTwo.stream().filter(t -> testIsTwo(t, three, four)).findAny().get();
		fifeTwo.remove(two);
		var fife = fifeTwo.get(0);
				
		
		return Map.of(sortString(one),"1", 
				sortString(four), "4",  
				  sortString(seven), "7",
				sortString(eight),"8",  
				sortString(three),"3", 
				sortString(six),"6", 
				 sortString(zero),"0",
				sortString(nine),"9", 
				sortString(fife),"5", 
				sortString(two),"2" );
	}

	private boolean testIsTwo(String test, String three, String four) {
		var a = test.replace(Character.toString(three.charAt(0)) , "")
				.replace(Character.toString(three.charAt(1)) , "")
				.replace(Character.toString(three.charAt(2)) , "")
				.replace(Character.toString(three.charAt(3)) , "")
				.replace(Character.toString(three.charAt(4)) , "")
				.replace(Character.toString(four.charAt(0)) , "")
				.replace(Character.toString(four.charAt(1)) , "")
				.replace(Character.toString(four.charAt(2)) , "")
				.replace(Character.toString(four.charAt(3)) , "");
		
		return a.length() > 0;
	}
	
	private boolean testNine(String test, String four) {
		return test.indexOf(four.charAt(0)) >= 0 && 
				test.indexOf(four.charAt(1)) >= 0 && 
				test.indexOf(four.charAt(2)) >= 0 && 
				test.indexOf(four.charAt(3)) >= 0;
	}
	
	private boolean testSix(String test, String one) {
		return test.indexOf(one.charAt(0)) < 0 ||
				test.indexOf(one.charAt(1)) < 0;
	}
	
	private boolean testIsThree(String test, String seven) {
		return test.indexOf(seven.charAt(0)) >= 0 && 
				test.indexOf(seven.charAt(1)) >= 0 && 
				test.indexOf(seven.charAt(2)) >= 0;
	}
}
