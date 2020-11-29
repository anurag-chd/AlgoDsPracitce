package Google;

import java.util.HashSet;
import java.util.Set;

interface Robot {
	void clean();
	void turnLeft();
	void turnRight();
	boolean move();
}

public class RobotCleaner implements Robot{
	private int initialRow;
	private int initialCol;
	private int initialAngle;
	private int currRow;
	private int currCol;
	private int currAngle;
	private Set<String> visited = new HashSet<>();
	private int[][] grid;

	public RobotCleaner(int initialRow, int initialCol, int initialAngle, int [][] grid){
		this.initialRow = initialRow;
		this.currRow = initialRow;
		this.initialCol = initialCol;
		this.currCol = initialCol;
		this.initialAngle = initialAngle;
		this.currAngle = initialAngle;
		this.grid = grid;
	}



	@Override
	public boolean move() {

		if(currAngle == 0 && (currCol == grid[0].length-1 || grid[currRow][currCol+1] == 0 ))
			return false;
		else if(currAngle == 90 && (currRow == 0 || grid[currRow-1][currCol] == 0))
			return false;
		else if(currAngle == 180 && (currCol == 0 || grid[currRow][currCol-1] == 0))
			return false;
		else if(currAngle == 270 && (currRow == grid.length-1 || grid[currRow +1][currCol] == 0))
			return false;

		else if(currAngle == 0){
			currCol++;
			return true;
		}
		else if(currAngle == 90){
			currRow--;
			return true;
		}
		else if(currAngle == 180){
			currCol--;
			return true;
		}
		else{
			currRow++;
			return true;
		}


	}

	@Override
	public void clean() {
		System.out.println("Cleaning up row= " + currRow + " col= " +currCol);
		visited.add(currRow + "_" + currCol);

	}

	@Override
	public void turnLeft() {
		currAngle = (currAngle + 90) % 360;
	}

	@Override
	public void turnRight() {
		if(currAngle == 0){
			currAngle = 270;
		}
		else
			currAngle = currAngle - 90;

	}

	public void dfs(){
		if(isVisited()){
			moveBack();
			return;
		}
		clean();

		if(move()){
			dfs();
		}

		turnLeft();
		if(move()){
			dfs();
		}

		turnLeft();
		if(move()){
			dfs();
		}

		turnLeft();
		if(move()){
			dfs();
		}

		turnLeft();

		if(currAngle == initialAngle && currCol == initialCol && currRow == initialRow){
			System.out.println("Google.Robot was able to clean room");
			return;
		}

		System.out.println("Explored all directions for current cell (" + currRow + " , " + currCol + " )");
		moveBack();

	}

	public void moveBack(){
		turnRight();
		turnRight();
		move();
		turnRight();
		turnRight();
	}

	public boolean isVisited(){
		String pos = currRow + "_" + currCol;
		return visited.contains(pos);
	}


	public static void main(String args[]){
		int [][] grid = {
				{0, 1, 0, 1},
				{1,1,1,1},
				{1,0,0,1},
				{1,1,1,1}
		};
		RobotCleaner r = new RobotCleaner(1,1,90,grid);
		r.dfs();
		//boolean res = r.currAngle == r.initialAngle && r.currRow == r.initialRow && r.currCol == r.initialCol;


	}
}
