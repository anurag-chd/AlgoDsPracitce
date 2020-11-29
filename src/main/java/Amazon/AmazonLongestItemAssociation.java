package Amazon;

import java.util.*;

public class AmazonLongestItemAssociation {
	public static void main(String[] args){
		List<List<String>> items = new ArrayList<>();
		String[][] products = {{"product1", "product2", "product3"},
				{"product5", "product2"},{"product6", "product7"},{"product8", "product7"}};
		for(int i = 0; i< products.length; i++){
			items.add(Arrays.asList(products[i]));
		}
		System.out.println(findLargestGroup(items));

	}

	public static List<String> findLargestGroup(List<List<String>> items){
		Map<String, Set<String>> adjList = new HashMap<>();
		for(List<String> l : items){
			for(String item : l){
				Set<String> set = adjList.getOrDefault(item, new HashSet<>());
				set.addAll(l);
				set.remove(item);
				adjList.put(item, set);
			}
		}

		Set<String> visited = new HashSet<>();
		List<String> res = new ArrayList<>();
		for(String item : adjList.keySet()){
			if(!visited.contains(item)){
				List<String> iterList = bfs(item, adjList, visited);
				if(iterList.size() > res.size())
					res = iterList;
			}
		}
		return res;
	}

	public static List<String> bfs(String item, Map<String,Set<String>> adjList, Set<String> visited){
		Queue<String> q = new LinkedList<>();
		q.add(item);
		visited.add(item);
		List<String> res = new ArrayList<>();
		while(!q.isEmpty()){
			String product = q.poll();
			res.add(product);
			for(String p : adjList.get(product)){
				if(!visited.contains(p)){
					visited.add(p);
					q.add(p);
				}
			}
		}
		return res;
	}
}
