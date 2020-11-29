package Amazon;

import java.util.Arrays;

public class AmazonCutOffRank {

	public static void main(String[] args){
		int c = 3, num =5 ;
		int[] scores = {2,2,2,4,5};
		System.out.println(cutOffRank(c, num, scores));
	}

	public static int cutOffRank(int cutOffRank, int num, int[] scores) {
		Arrays.sort(scores);
		//int [] rank = new int[scores.length];
		int start = scores.length-1;
		//rank[0] = 1;
		int r =0;
		int len = scores.length-1;
		while(start >= 0){
			//rank = start+1;
			//int r = start+1;
			while(start >= 0 && start < scores.length-1 && scores[start+1] == scores[start] ){
				//rank[start] = rank[start+1];
				start--;
			}
			if(start >= 0){
			//	rank[start] = len - start +1;
				r = len- start+1;

			}
			if(r > cutOffRank){
				return len - start ;
			}
			start--;

		}
		/*System.out.println(Arrays.toString(rank));
		int res = 0;
		for(int i = scores.length-1; i>= 0; i--){
			if(rank[i] > cutOffRank)
				break;
			res++;
		}*/

		return scores.length;
	}
}
