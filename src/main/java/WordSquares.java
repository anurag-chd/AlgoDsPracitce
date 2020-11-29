import java.util.*;

public class WordSquares {

	public static void main(String args[]){
		String[] arr = {"AREA", "BALL", "DEAR", "LADY", "LEAD", "YARD"};
		List<List<String>> res = new ArrayList<>();
		Map<Character, Set<String>> map = new HashMap<>();
		Set<String> visited = new HashSet<>();
		for(String s : arr){
			Set<String> l = map.getOrDefault(s.charAt(0), new HashSet<>());
			l.add(s);
			map.put(s.charAt(0), l);
		}
		for(String s : arr){
			visited.add(s);
			ArrayList<String> l = new ArrayList<>();
			l.add(s);
			check(s,l,s.length(),1,map, res, visited);
			visited.remove(s);


		}
		System.out.println(res);

	}

	public static void check(String s, ArrayList<String> l, int end, int pos, Map<Character,Set<String>> map, List<List<String>> res, Set<String> visited){
		if(pos == end){
			if(checkSquare(l)){
				res.add((ArrayList<String>)l.clone());
			}

			return;
		}
		if(!map.containsKey(s.charAt(pos)))
			return;

		for(String st : map.get(s.charAt(pos))){
			if(visited.contains(st)){
				continue;
			}
			visited.add(st);
			l.add(st);
			check(s,l, end, pos+1, map, res, visited);
			l.remove(st);
			visited.remove(st);
		}

	}

	public static boolean checkSquare( List<String> mat){
		for(int i = 0; i < mat.size(); i++){
			for(int j = i; j < mat.size(); j++){
				if(i == j){
					continue;
				}
				else{
					if(mat.get(i).charAt(j) != mat.get(j).charAt(i))
						return false;
				}
			}
		}
		return true;
	}


}
