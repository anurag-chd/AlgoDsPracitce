package Amazon;

import java.util.Arrays;
import java.util.List;

public class AmazonMoviesOnFlight {

	public static void main(String[] args){
		int[] movieDuration = {90, 85, 75, 60, 120, 150, 125};
		int d = 250;
		System.out.println(Arrays.toString(getMovies(d, movieDuration)));
	}

	public static int[] getMovies(int d, int[] movieDuration){
		int max = 0;
		int[] res = new int[2];
		Arrays.sort(movieDuration);
		int start = 0;
		int end = movieDuration.length-1;
		int duration = d -30;
		int maxDiff = Integer.MAX_VALUE;
		while(start < end){
			if(movieDuration[start] + movieDuration[end] == duration){
				res[0] = start;
				res[1] = end;
				return res;
			}
			else if(movieDuration[start] + movieDuration[end] > duration){
				end--;
			}
			else{
				if(res[0] == 0 && res[1] == 0){
					res[0] = movieDuration[start];
					res[1] = movieDuration[end];
					maxDiff = duration - movieDuration[start] - movieDuration[end];
				}else if(maxDiff > duration - movieDuration[start] - movieDuration[end]){
					res[0] = movieDuration[start];
					res[1] = movieDuration[end];
					maxDiff = duration - movieDuration[start] - movieDuration[end];
				}
				start++;

			}
		}

		return res;

	}
}
