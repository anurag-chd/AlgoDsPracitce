package Amazon;

import java.util.*;

public class AmazonRearrangeString {
	public static void main(String[] args){
		String str = "swerawea";
		int k = 3;
		System.out.println(rearrangeString(str, k));
	}

	public static String rearrangeString(String str, int k) {
		if(k == 0)
			return str;
		Map<Character, Integer> map = new HashMap<>();
		for(char c : str.toCharArray()){
			map.put(c, map.getOrDefault(c,0) +1);
		}
		LinkedHashMap<Character, Integer> sortedMap = new LinkedHashMap<>();
		map.entrySet().stream().sorted(Map.Entry.comparingByValue(Collections.reverseOrder())).forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
		System.out.println(sortedMap);
		char[] resStrArr = new char[str.length()];
		Arrays.fill(resStrArr,'-');
		int index = 0;
		for(char c : sortedMap.keySet()){
			int total = sortedMap.get(c);
			int start = index;
			while(start < str.length() && total > 0){
				resStrArr[start] = c;
				start = start+k;
				total--;
				if(start >= str.length() && total > 0){
					return "";
				}
			}
			while(index < str.length() && resStrArr[index] != '-')
				index++;

		}

		return new String(resStrArr);
	}
}
