package Amazon;


import java.util.*;

public class Java8Test {


	public static void main(String[] args){
		Map<Integer, DebtBalance> map = new HashMap<>();
		map.put(1,new DebtBalance("a",4));
		map.put(2,new DebtBalance("b",3));
		map.put(3,new DebtBalance("c",2));
		map.put(4,new DebtBalance("d",1));

		LinkedHashMap<Integer, DebtBalance> lm = new LinkedHashMap<>();

		map.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(x -> lm.put(x.getKey(), x.getValue()));
		System.out.println(lm);
	}
}

