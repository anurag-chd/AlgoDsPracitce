package Amazon;

import java.util.*;

public class AmazonDebtRecords {
	public static void main (String[] args){
		List<debtRecord> records = new ArrayList<>();
		records.add(new debtRecord("Alex", "Blake", 2));
		records.add(new debtRecord("Blake", "Alex", 2));
		records.add(new debtRecord("Casey", "Alex", 5));
		records.add(new debtRecord("Blake", "Casey", 7));
		records.add(new debtRecord("Alex", "Blake", 4));
		records.add(new debtRecord("Alex", "Casey", 4));

		System.out.println(minimumDebtMembers(records));


	}

	public static List<String> minimumDebtMembers(List<debtRecord> records){
		Map<String, DebtBalance> map = new HashMap<>();
		for(debtRecord r : records){
			DebtBalance borDB = map.getOrDefault(r.borrower, new DebtBalance(r.borrower,0));
			DebtBalance lendDB = map.getOrDefault(r.lender,new DebtBalance(r.lender,0));
			borDB.bal -= r.amount;
			lendDB.bal += r.amount;
			map.put(r.borrower, borDB);
			map.put(r.lender, lendDB);
		}
		/*LinkedHashMap<String,Amazon.DebtBalance> set = new LinkedHashMap<>();
		map.entrySet().stream().sorted(Map.Entry.<String, Amazon.DebtBalance>comparingByValue())
				.forEachOrdered(x -> set.put(x.getKey(), x.getValue()));*/


		/*Set<Amazon.DebtBalance> set = map.entrySet().stream().sorted(Map.Entry.<String, Amazon.DebtBalance>comparingByValue()).map(e -> e.getValue()).collect(Collectors.toSet());*/
		/*Map<String, Amazon.DebtBalance> set = map.entrySet().stream()
				.sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
						(oldValue, newValue) -> oldValue, LinkedHashMap::new));*/
		PriorityQueue<DebtBalance> q = new PriorityQueue<>();
		DebtBalance a = new DebtBalance("",1);
		q.remove(a);
		for(String name : map.keySet()){
			q.add(map.get(name));
		}
		List<String> res = new ArrayList<>();
		DebtBalance minDB = q.peek();
		if(minDB.bal < 0){
			//for(Amazon.DebtBalance db : set){
			while(!q.isEmpty()){
				DebtBalance db = q.poll();
				if(db.bal == minDB.bal){
					res.add(db.name);
				}
				else{
					break;
				}
			}
		}

		return res;

	}
}

class DebtBalance implements Comparable, Comparator{
	String name;
	int bal;

	public DebtBalance(String name, int bal){
		this.name = name;
		this.bal = bal;
	}


	@Override
	public int compareTo(Object o) {
		DebtBalance obj = (DebtBalance) o;
		if(obj.bal == this.bal){
			return this.name.compareTo(obj.name);
		}
		else{
			return Integer.compare(this.bal, obj.bal);
		}
	}

	@Override
	public int compare(Object o1, Object o2) {
		DebtBalance obj1 = (DebtBalance) o1;
		DebtBalance obj2 = (DebtBalance) o2;
		if(obj1.bal == obj2.bal){
			return obj1.name.compareTo(obj2.name);
		}
		else{
			return Integer.compare(obj1.bal, obj2.bal);
		}
	}
}

class debtRecord
{
	String borrower = "";
	String lender = "";
	int amount = 0;
	debtRecord()
	{
		// empty constructor
	}
	debtRecord(String borrower, String lender, int amount)
	{
		this.borrower = borrower;
		this.lender = lender;
		this.amount = amount;
	}
}
