package Amazon;

import java.util.*;

public class AmazonPartitionString {
	public static void main(String[] args){
		String s = "baddacx";
		System.out.println(partitionString(s));
	}

	public static List<String> partitionString(String input){
		Map<Character, int[]> map = new HashMap<>();
		for(int i = 0; i< input.length(); i++){
			char c = input.charAt(i);
			int[] index = map.getOrDefault(c, new int[]{-1,-1});
			if(index[0] == -1){
				index[0] = i;
				index[1] = i;
			}
			else{
				index[1] = i;
			}
			map.put(c, index);
		}

		PriorityQueue<int[]> q = new PriorityQueue<>((a,b) -> Integer.compare(a[0], b[0]));
		for(Character c : map.keySet()){
			q.add(map.get(c));
		}

		List<String> resList = new ArrayList<>();

		while(!q.isEmpty()){
			int[] index = q.poll();
			while(!q.isEmpty() && q.peek()[0] <= index[1]){
				index[1] = Math.max(q.poll()[1], index[1]);
			}
			resList.add(input.substring(index[0], index[1]+1) );
		}

		return resList;

	}
}
