
import java.util.ArrayList;
import java.util.List;

class Solution {

	public static void main(String args[]){
		System.out.println(solveNQueens(4));
	}

	public static List<List<String>> solveNQueens(int n) {
		int row [] = new int [n];
		boolean col [] = new boolean [n];
		List<List<String>> res = new ArrayList<>();
		check(n, 0, res, new ArrayList<String>(), row,col);
		return res;
	}

	public static void check(int n , int row, List<List<String>> res, ArrayList<String> l, int rowF [], boolean colF[]){
		if(row == n){
			res.add((ArrayList<String>) l.clone() );
			return;
		}
		for(int col= 0; col < n; col++){
			if(!invalid(row,col, rowF, colF)){
				rowF[row] = col;
				colF[col] = true;
				l.add(getString(col, n));
				System.out.println(l);
				check(n, row+1, res, l,rowF, colF);
				l.remove(l.size() -1);
				colF[col] = false;
				rowF[row] = -1;
			}
		}

	}

	public static String getString(int col, int n){
		StringBuilder sbr = new StringBuilder();
		for(int i = 0; i < n; i++){
			if(col == i){
				sbr.append("Q");
			}
			else
				sbr.append(".");
		}
		return sbr.toString();
	}

	public static boolean invalid(int row, int col, int r[], boolean c[]){
		if(c[col])
			return true;
		int startR = row-1, colR = col-1;
		while(startR >= 0 && colR >= 0){
			if(r[startR] == colR)
				return true;
			startR--;
			colR--;
		}
		startR = row -1;
		colR = col +1;
		while(startR >=0 && colR < c.length){
			if(r[startR] == colR)
				return true;
			startR--;
			colR++;
		}

		return false;
	}
}
