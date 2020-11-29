package Amazon;

import java.util.PriorityQueue;

public class AmazonMinCostRequiredToTieRopes {
	public static void main(String[] args){
		int[] ropes = {2, 2, 3, 3};
		System.out.println(connectRopes(ropes));
	}

	public static int connectRopes(int[] ropes) {
		int sum = 0;
		PriorityQueue<Integer> q = new PriorityQueue<>();
		for(int i : ropes)
			q.add(i);
		while(!q.isEmpty()){
			int res = q.poll();
			if(!q.isEmpty()){
				res  += q.poll();
				q.add(res);
				sum += res;
			}

		}
		return sum;
	}
}
