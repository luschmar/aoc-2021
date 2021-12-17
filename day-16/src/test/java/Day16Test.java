import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day16Test {
	
	@Test
	void literalValues() {
		
		
		assertEquals(2021, ((BITSLiteralValue)packageHexReader("D2FE28").get(0)).getValue());
	}
	
	@Test
	void lengthTypeId() {
		var p = packageHexReader("38006F45291200").get(0);
		
		
		assertEquals(20, ((BITSLiteralValue)((BITSOperation)p).subpackages.get(0)).getValue());
	}
	

	@ParameterizedTest
	@AocFileSource(inputs = { @AocInputMapping(input = "literal_value_1.txt", solution = "2021") })
	void literalValueTest(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());
		var hexList = inputList.stream().collect(Collectors.joining());
		var packageList = packageHexReader(hexList);
		var bitsLiteral = (BITSLiteralValue) packageList.get(0);
		assertEquals(solution, Long.toString(bitsLiteral.getValue()));
	}

	@ParameterizedTest
	@AocFileSource(inputs = { @AocInputMapping(input = "operation_package_1.txt", solution = "2021"),
	// @AocInputMapping(input = "operation_package_2.txt", solution = "2021")
	})
	void operatorPackage(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());
		System.out.println(inputList.get(0));
		var hexData = inputList.stream().collect(Collectors.joining());
		var packageList = packageHexReader(hexData);
		var bitsLiteral = (BITSLiteralValue) packageList.get(0);
		assertEquals(solution, Long.toString(bitsLiteral.getValue()));
	}
	
	static List<BITSPackage> packageBinReader(String binData){
		var packageList = new ArrayList<BITSPackage>();
		BITSPackage currentPackage = null;

		for (int i = 0; i < binData.length(); i += 4-(i % 4)) {
			if (currentPackage == null) {
				var versionAndType = binData.substring(i, i+=6);
				if (versionAndType.substring(3).startsWith("100")) {
					currentPackage = new BITSLiteralValue(versionAndType);
					packageList.add(currentPackage);
				} else {
					currentPackage = new BITSOperation(versionAndType);
					packageList.add(currentPackage);
				}

				while (currentPackage.nextToRead() > 0) {
					var buf = currentPackage.nextToRead();
					currentPackage.add(binData.substring(i, i+buf));
					i += buf;
				}
				currentPackage = null;
			}
		}
		return packageList;
	}

	static List<BITSPackage> packageHexReader(String hexData) {
		var binData = Arrays.stream(hexData.split("")).map(s -> String.format("%04d", Integer.parseInt(new BigInteger(s, 16).toString(2)))).collect(Collectors.joining());
		return packageBinReader(binData);
	}

	@ParameterizedTest
	@AocFileSource(inputs = {
			// @AocInputMapping(input = "test.txt", solution = "fail"),
			@AocInputMapping(input = "input.txt", solution = "fail") })
	void part1(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());
		var hexData = inputList.stream().collect(Collectors.joining());
		var packageList = packageHexReader(hexData);

		int sum = sumpackageVersion(packageList);

		assertEquals(solution, Long.toString(sum));
	}

	private int sumpackageVersion(List<BITSPackage> packageList) {
		int sum = 0;
		for (var p : packageList) {
			if (p instanceof BITSLiteralValue) {
				sum += p.version;
			}

			if (p instanceof BITSOperation) {
				sum += p.version;
				var op = (BITSOperation) p;
				if (op.subpackages != null) {
					sum += sumpackageVersion(op.subpackages);
				}
			}
		}
		return sum;
	}

	@ParameterizedTest
	@AocFileSource(inputs = { @AocInputMapping(input = "test.txt", solution = "fail"),
			@AocInputMapping(input = "input.txt", solution = "fail") })
	void part2(Stream<String> input, String solution) {
		var inputList = input.collect(Collectors.toList());

		var res = 10l;

		assertEquals(solution, Long.toString(res));
	}
}
