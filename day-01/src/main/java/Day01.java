import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Day01 {
	public static void main(String[] args) {
		part1();
		part2();

	}
	
	static void part1() {
		  var br = new BufferedReader(new InputStreamReader(Day01.class.getClassLoader().getResourceAsStream("input1.txt")));
		  Integer[] lines = br.lines().map(Integer::parseInt).toArray(Integer[]::new);
		  
		  int result = 0;
		  for(int i = 1; i < lines.length; i++) {
			  if(lines[i-1] < lines[i]) {
				  result++;
			  }
		  }
		  
		  System.out.println(result);
	}
	
	static void part2() {
		  var br = new BufferedReader(new InputStreamReader(Day01.class.getClassLoader().getResourceAsStream("input1.txt")));
		  Integer[] lines = br.lines().map(Integer::parseInt).toArray(Integer[]::new);
		  
		  int result = 0;
		  
		  for(int i = 3; i < lines.length; i++) {
			  var sum1 = lines[i-3]+lines[i-2]+lines[i-1];
			  var sum2 = lines[i-2]+lines[i-1]+lines[i];
			  if(sum1 < sum2) {
				  result++;
			  }
		  }
		  
		  System.out.println(result);
	}
}
