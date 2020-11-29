package Amazon;

import java.util.*;

public class AmazonVideoScenes {
	public static void main(String[] args){
		char[] inputList = {'a','b', 'a', 'b','c', 'b', 'a', 'c', 'a', 'd', 'e', 'f', 'e', 'g', 'd', 'e', 'h', 'i', 'j', 'h', 'k', 'l', 'i','j'};
		System.out.println(getShotsLength(inputList));
	}

	public static List<Integer> getShotsLength(char[] inputList){
		List<Integer> res = new ArrayList<>();
		if(inputList == null || inputList.length == 0)
			return  res;
		Map<Character,int[]> map = new HashMap<>();
		for(int i = 0; i < inputList.length ; i++){
			if(!map.containsKey(inputList[i])){
				map.put(inputList[i], new int[]{i,i});
			}
			else{
				map.get(inputList[i])[1] = i;
			}
		}

		PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a, b) -> Integer.compare(a[0], b[0]));
		for(char c : map.keySet()){
			queue.add(map.get(c));
		}
		while(!queue.isEmpty()){
			int[] temp = queue.poll();
			while(!queue.isEmpty() && temp[1] >= queue.peek()[0]){
				int[] t = queue.poll();
				if(temp[1] < t[1]){
					temp[1] = t[1];
				}
			}
			res.add(temp[1] -temp[0] +1);
		}

		return res;
	}
}
