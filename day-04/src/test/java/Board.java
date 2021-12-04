import java.util.Arrays;
import java.util.List;

public class Board {
	
	int[][] numbers = new int[5][5];
	boolean[][] crossed = new boolean[5][5];

	public Board(List<String> boardNumbers) {
		for(int i = 0; i < boardNumbers.size(); i++) {
			numbers[i] = Arrays.stream(boardNumbers.get(i)
					.split("\\s+"))
					.filter(item-> !item.isEmpty())
					.map(Integer::parseInt).mapToInt(x->x).toArray();  
		}
	}
	
	boolean crossNumber(int n) {
		for(int i = 0; i < numbers.length; i++) {
			for(int j = 0; j < numbers[i].length; j++) {
				if(numbers[i][j] == n) {
					crossed[i][j] = true;
				}
			}
		}
		// check 
		boolean hitHorizontal = false;
		boolean hitVertical = false;
		for(int i = 0; i < crossed.length && !hitHorizontal && !hitVertical; i++) {
			hitHorizontal = true;
			hitVertical = true;
			for(int j = 0; j < crossed[i].length; j++) {
				hitHorizontal = hitHorizontal && crossed[i][j];
				hitVertical = hitVertical && crossed[j][i];
			}
		}

		
		return hitHorizontal || hitVertical;
	}
	
	int sum() {
		int sum = 0;
		for(int i = 0; i < numbers.length; i++) {
			for(int j = 0; j < numbers[i].length; j++) {
				if(!crossed[i][j]) {
					sum += numbers[i][j];
				}
			}
		}
		
		return sum;
	}
}
