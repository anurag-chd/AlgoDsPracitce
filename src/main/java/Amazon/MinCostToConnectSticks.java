package Amazon;

import java.util.PriorityQueue;

public class MinCostToConnectSticks {
	public static void main(String[] args){
		int res = 0;
		int[] sticks = {1, 2, 5, 10, 35, 89};
		PriorityQueue<Integer> q = new PriorityQueue<>();
		for(int i : sticks)
			q.add(i);

		while(q.size() > 1){
			int temp1 = q.poll();
			int temp2 = q.poll();
			int s = temp1 + temp2;
			res += s;
			q.add(s);
		}

		System.out.println(res);
	}
}
