import java.util.*;

public class LongestItemAssociation {
	public static void main(String[] args){
		String[][] itemAssociation = {{"item1", "item2"},
				{"item3", "item4"}, {"item4","item5"}};

		System.out.println(getLongestAssociation(itemAssociation));
	}

	public static List<String> getLongestAssociation(String[][] itemAssociation){
		if(itemAssociation.length == 0)
			return new LinkedList<>();
		List<String> res = new ArrayList<>();
		if(itemAssociation.length == 1){
			 Collections.addAll(res,itemAssociation[0]);
		}

		Map<String,String> adjList = new HashMap<>();
		for(String[] ass : itemAssociation){
			adjList.put(ass[0], ass[1]);
		}

		for(int i = 0; i < itemAssociation.length; i++){
			String item1 = itemAssociation[i][0];
			List<String> interimRes = new ArrayList<>();
			while(adjList.containsKey(item1)){
				String item2 = adjList.get(item1);
				interimRes.add(item1);
				//interimRes.add(item2);
				adjList.remove(item1);
				item1 = item2;
			}
			interimRes.add(item1);
			if(res.size() < interimRes.size()){
				res = interimRes;
			}
		}

		return res;





	}
}
