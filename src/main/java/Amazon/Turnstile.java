package Amazon;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Turnstile {
	public static void main(String[] args){

		int[] times = {0,0,1,5};
		int[] dir = {0,1,1,0};
		int[] res = getTimes(times, dir);
		System.out.println(Arrays.toString(res));
	}

	public static int[] getTimes(int[] times, int[] dirs){
		int len = times.length;
		if(len < 2)
			return times;
		int[] res = new int[len];
		PriorityQueue<IndexTimePosition> q = new PriorityQueue<>(Comparator.comparingInt(a -> a.time));
		for(int i = 0; i < len ;i++){
			q.add(new IndexTimePosition(i,times[i], dirs[i]));
		}
		// time, dir
		int []prevPos = {-1,-1};
		while(!q.isEmpty()){
			IndexTimePosition itp = q.poll();
			if(!q.isEmpty() && prevPos[0] == itp.time -1 && itp.time == q.peek().time){
				IndexTimePosition sec =q.poll();
				IndexTimePosition out, in;
				if(itp.pos == 1){
					out = itp;
					in  = sec;
				}else{
					out = sec;
					in  = itp;
				}
				if(prevPos[1] == -1 || prevPos[1] == 1){
					in.time++;
					q.add(in);
					res[out.index] = out.time;
					prevPos[0] = out.time;
					prevPos[1] = out.pos;
				}
				else{
					out.time++;
					q.add(out);
					res[in.index] = in.time;
					prevPos[0] = in.time;
					prevPos[1] = in.pos;
				}
			}
			else{
				res[itp.index] = itp.time;
				prevPos[0] = itp.time;
				prevPos[1] = itp.pos;
			}

		}


		return res;

	}


}

class IndexTimePosition{
	int index, time, pos;
	public IndexTimePosition(int index, int time, int pos){
		this.index = index;
		this.time = time;
		this.pos = pos;
	}
}
