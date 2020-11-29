package Amazon;

public class AmazonMaximalMinimumValuePath2 {
	static  int max ;
	public static void main(String[] args){
		int[][] matrix = {{7, 5, 3},{2, 0, 9},{4, 5, 9}};
		System.out.println(maxPathScore(matrix));
	}
	public static int maxPathScore(int[][] matrix) {
		max = Integer.MIN_VALUE;
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		dfs(0,0, matrix, visited, matrix[0][0]);
		return max;


	}


	public static void dfs(int row, int col, int[][] matrix, boolean[][] visited, int minValue){
		if(row == matrix.length-1 && col == matrix[0].length-1){
			if(max < minValue){
				max = minValue;
			}
			return;
		}
		visited[row][col] = true;

		if(row > 0 && !visited[row-1][col]){
			int currMinValue = Math.min(minValue, matrix[row-1][col]);
			dfs(row-1, col, matrix, visited, currMinValue);
		}
		if(row < matrix.length-1 && !visited[row+1][col]){
			int currMinValue = Math.min(minValue, matrix[row+1][col]);
			dfs(row+1, col, matrix, visited, currMinValue);
		}
		if(col > 0 && !visited[row][col-1]){
			int currMinValue = Math.min(minValue, matrix[row][col-1]);
			dfs(row, col-1, matrix, visited, currMinValue);
		}
		if(col < matrix[0].length-1 && !visited[row][col+1]){
			int currMinValue = Math.min(minValue, matrix[row][col+1]);
			dfs(row, col+1, matrix, visited, currMinValue);
		}

		visited[row][col] = false;
	}


}
