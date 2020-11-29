package Amazon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AmazonKString {
	public static void main(String[] args){
		String str = "awaglkg";
		int num = 4;
		System.out.println(getSubstring(str, num));
	}

	public static List<String> getSubstring(String str, int num){
		List<String> res = new ArrayList<>();
		if(str == null || str.length() < num){
			return res;
		}
		int[] charCount = new int[26];
		Set<Character> set = new HashSet<>();
		for(int i =0; i< num; i++){
			charCount[str.charAt(i) - 'a']++;
			if(!set.contains(str.charAt(i)) && charCount[str.charAt(i) - 'a'] > 1)
				set.add(str.charAt(i));
		}
		if(set.size() == 1){
			res.add(str.substring(0, num));
		}
		int start = 0;
		int end = num;
		while(end < str.length()){
			char charStart = str.charAt(start);
			charCount[charStart - 'a']--;
			if(charCount[charStart - 'a'] == 1){
				set.remove(charStart);
			}
			start++;
			char charEnd = str.charAt(end);
			charCount[charEnd - 'a']++;
			if(charCount[charEnd - 'a'] > 1)
				set.add(charEnd);
			end++;
			if(set.size() == 1){
				res.add(str.substring(start, end));
			}
		}




		return res;

	}
}
