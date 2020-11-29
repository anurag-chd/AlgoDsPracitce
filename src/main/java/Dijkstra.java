import DataStructures.BinaryMinHeapWithMap;

import java.util.Arrays;

public class Dijkstra {

	public static void main(String[] args){
		int graph[][] = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
				{ 4, 0, 8, 0, 0, 0, 0, 11, 0 },
				{ 0, 8, 0, 7, 0, 4, 0, 0, 2 },
				{ 0, 0, 7, 0, 9, 14, 0, 0, 0 },
				{ 0, 0, 0, 9, 0, 10, 0, 0, 0 },
				{ 0, 0, 4, 14, 10, 0, 2, 0, 0 },
				{ 0, 0, 0, 0, 0, 2, 0, 1, 6 },
				{ 8, 11, 0, 0, 0, 0, 1, 0, 7 },
				{ 0, 0, 2, 0, 0, 0, 6, 7, 0 } };

		System.out.println(Arrays.toString(findShortestPaths(graph, 0)));
	}

	public static int[] findShortestPaths(int[][] graph, int startPos){
		int res[] = new int[graph.length];
		Arrays.fill(res, Integer.MAX_VALUE);
		res[startPos] = 0;

		BinaryMinHeapWithMap<Integer, Integer> minHeap = new BinaryMinHeapWithMap<>();
		for(int i = 0; i< graph[0].length; i++){
			if (i == startPos || graph[startPos][i] == 0) {
				continue;
			}
			minHeap.add(i, graph[startPos][i]);
			res[i] = graph[startPos][i];


		}

		while(!minHeap.isEmpty()){
			int pos = minHeap.extractMin();
			for(int i = 0; i< graph[0].length; i++) {
				if(graph[pos][i] != 0){
					if(res[i] > res[pos] + graph[pos][i]){
						res[i] = res[pos] + graph[pos][i];
						minHeap.add(i, res[i]);
					}
				}
			}
		}

		return res;
	}
}
