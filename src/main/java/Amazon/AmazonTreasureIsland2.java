package Amazon;

import java.util.ArrayList;
import java.util.List;

public class AmazonTreasureIsland2 {

	public static void main(String[] args) {
		char[][] matrix = {{'S', 'O', 'O', 'S', 'S'}, {'D', 'O', 'D', 'O', 'D'}, {'O', 'O', 'O', 'O', 'X'}, {'X', 'D', 'D', 'O', 'O'}, {'X', 'D', 'D', 'D', 'O'}};

		System.out.println(shortestRoute(matrix));
	}
	static int minDist;
	static List<Point> resList;
	public static List<Point> shortestRoute(char[][] matrix){
		minDist = Integer.MAX_VALUE;
		resList = new ArrayList<>();
		//List<Amazon.Point> resList = new ArrayList<>();
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		for(int i = 0;i < matrix.length; i++){
			for(int j = 0; j < matrix[0].length; j++){
				if(matrix[i][j] == 'S'){
					ArrayList<Point> rs = new ArrayList<>();
					dfs(i,j, matrix, 0, visited, rs);


				}
			}
		}
		return resList;

	}

	public static void dfs(int row, int col, char[][] matrix, int dist, boolean[][] visited, ArrayList<Point> res){
		if(dist >= minDist){
			return;
		}
		if(matrix[row][col] == 'X'){
			resList = (ArrayList<Point>)res.clone();
			minDist = dist;
			return;
		}

		visited[row][col] = true;
		res.add(new Point(row, col));

		if(row > 0 && !visited[row-1][col] && matrix[row-1][col] != 'D'){
			dfs(row-1,col,matrix, dist +1 ,visited, res);

		}
		if(row < matrix.length-1 && !visited[row+1][col] && matrix[row+1][col] != 'D'){
			dfs(row+1, col, matrix, dist+1, visited, res);
		}

		if(col > 0 && !visited[row][col-1] && matrix[row][col-1] != 'D'){
			dfs(row,col-1,matrix, dist +1 ,visited, res);

		}
		if(col < matrix[0].length-1 && !visited[row][col+1] && matrix[row][col+1] != 'D'){
			dfs(row, col+1, matrix, dist+1, visited, res);
		}
		res.remove(res.size()-1);
		visited[row][col] = false;




	}

}
class Point{
	int x, y;
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString(){
		return "(" + this.x +", "+ this.y +")";
	}
}
