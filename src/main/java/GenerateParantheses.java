import java.util.ArrayList;
import java.util.List;

public class GenerateParantheses {

	public static void main(String args[]){
		System.out.println(generateParenthesis(3));

	}

	public static List<String> generateParenthesis(int n) {
		List<String> res = new ArrayList<>();
		if(n <= 0)
			return res;

		recurse(n,res, new StringBuilder(), 0, 0, 0);
		return res;
	}

	public static void recurse(int n, List<String> res, StringBuilder sbr, int pos, int openCounter, int closeCounter){
		if(pos == (2 * n)){
			res.add(sbr.toString());
			return;
		}
		if(openCounter > closeCounter){
			sbr.insert(pos,")");
			recurse(n, res, sbr, pos +1, openCounter, closeCounter +1);
			sbr.deleteCharAt(pos);
		}
		if(openCounter < n){
			sbr.insert(pos,"(");
			recurse(n, res,sbr, pos +1, openCounter +1, closeCounter);
			sbr.deleteCharAt(pos);
		}
	}
}
