import java.util.*;

public class SubstringsKSize {
	public static void main(String[] args){
		String s = "abcabc";
		int k =3;
		System.out.println(getKSubstrings(s, k));


		Map<Integer, Integer> map = new HashMap<>();
		map.put(1,4);
		map.put(2,3);
		map.put(3,2);
		map.put(4,1);
		System.out.println(Collections.max(map.entrySet(),Map.Entry.comparingByValue()).getKey());


	}

	public static List<String> getKSubstrings(String s, int k){
		char[] charCount = new char[26];
		Set<String> res = new HashSet<>();
		if(s == null || s.length() < k)
			return new ArrayList<>();

		int start = 0;
		int end = 0;
		Set<Character> set = new HashSet<>();
		while(end < k && end < s.length()){
			char c = s.charAt(end);
			charCount[c - 'a']++;
			if(charCount[c - 'a'] > 1){
				set.add(c);
			}
			end++;

		}

		if(set.size() == 0)
			res.add(s.substring(start, end));

		while(end < s.length()){
			char startChar = s.charAt(start);
			charCount[startChar - 'a']--;
			if(charCount[startChar - 'a'] < 2){
				set.remove(startChar);
			}
			start++;

			char endChar = s.charAt(end);
			charCount[endChar - 'a']++;
			if(charCount[endChar - 'a'] > 1){
				set.add(endChar);
			}
			end++;
			if(set.size() == 0){
				res.add(s.substring(start, end));
			}

		}


		List<String> resL = new ArrayList<>();
		resL.addAll(res);
		return resL;
	}
}
