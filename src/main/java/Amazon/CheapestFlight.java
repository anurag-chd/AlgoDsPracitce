package Amazon;

import java.util.*;

public class CheapestFlight {

	public static void main(String[] args){
		int n = 11;
		int[][] flights = {{0,3,3},{3,4,3},{4,1,3},{0,5,1},{5,1,100},{0,6,2},{6,1,100},{0,7,1},{7,8,1},{8,9,1},{9,1,1},{1,10,1},{10,2,1},{1,2,100}};
		int src = 0;
		int dst = 2;
		int k = 4;
		CheapestFlight cp = new CheapestFlight();
		System.out.println(cp.findCheapestPrice(n,flights, src, dst, k));
	}

	public  int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
		Map<Integer, List<int[]>> map = new HashMap<>();
		PriorityQueue<EdgeDistSteps> q = new PriorityQueue<>((a, b) -> Integer.compare(a.dist,b.dist));
		for(int[] flight : flights){
			List<int[]> l = map.getOrDefault(flight[0], new ArrayList<>());
			l.add(new int[]{flight[1], flight[2]});
			map.put(flight[0],l);
		}
		if(!map.containsKey(src))
			return -1;
		int[] distArr = new int[n];
		Arrays.fill(distArr, Integer.MAX_VALUE);
		distArr[src] = 0;


		for(int[] f : map.get(src)){
			if(K == 0 && f[0] == dst){
				return f[1];
			}
			//distArr[f[0]] = f[1];
			q.add(new EdgeDistSteps(f[0],f[1], 0));
		}


		while(!q.isEmpty()){
			EdgeDistSteps eds = q.poll();
			// if(distArr[eds.edge] != Integer.MAX_VALUE){
			//     continue;
			//  }
			// if(eds.edge == dst){
			//      return eds.dist;
			// }
			//if(eds.dist > distArr[eds.edge]){
			//	continue;
			//}
			distArr[eds.edge] = Math.min(eds.dist, distArr[eds.edge]);
			if(eds.step < K && map.containsKey(eds.edge)){
				for(int[] f : map.get(eds.edge)){
					//if(distArr[f[0]] > f[1] + eds.dist){
						//distArr[f[0]] = f[1] + eds.dist;
						q.add(new EdgeDistSteps(f[0], f[1] + eds.dist, eds.step +1));

					//}
				}

			}
		}
		return distArr[dst] == Integer.MAX_VALUE ? -1:distArr[dst];
	}

	class EdgeDistSteps{
		int edge, dist, step;

		public EdgeDistSteps(int e, int d, int s){
			this.edge = e;
			this.dist = d;
			this.step = s;
		}
	}

}
