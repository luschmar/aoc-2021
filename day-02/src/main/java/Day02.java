import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Day02 {
	public static void main(String[] args) {
		part1();
		part2();

	}
	
	static void part1() {
		  var br = new BufferedReader(new InputStreamReader(Day02.class.getClassLoader().getResourceAsStream("input1.txt")));
		  String[] lines = br.lines().toArray(String[]::new);
		  
		  int hpos = 0;
		  int depth = 0;
		  for(int i = 0; i < lines.length; i++) {
			  if(lines[i].startsWith("forward ")) {
				  hpos += Integer.parseInt(lines[i].substring(8));
			  }
			  if(lines[i].startsWith("down ")) {
				  depth += Integer.parseInt(lines[i].substring(5));
			  }
			  if(lines[i].startsWith("up ")) {
				  depth -= Integer.parseInt(lines[i].substring(3));
			  }
		  }
		  
		  System.out.println(hpos*depth);
	}
	
	static void part2() {
		  var br = new BufferedReader(new InputStreamReader(Day02.class.getClassLoader().getResourceAsStream("input1.txt")));
		  String[] lines = br.lines().toArray(String[]::new);
		  
		  int hpos = 0;
		  int depth = 0;
		  int aim = 0;
		  for(int i = 0; i < lines.length; i++) {
			  if(lines[i].startsWith("forward ")) {
				  hpos += Integer.parseInt(lines[i].substring(8));
				  depth += aim * Integer.parseInt(lines[i].substring(8));
			  }
			  if(lines[i].startsWith("down ")) {
				  aim += Integer.parseInt(lines[i].substring(5));
			  }
			  if(lines[i].startsWith("up ")) {
				  aim -= Integer.parseInt(lines[i].substring(3));
			  }
		  }
		  
		  System.out.println(hpos*depth);
	}
}
