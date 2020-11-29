package Amazon;

import java.util.*;

public class AmazonFillTruck {
	public static void main(String[] args){
		int num=3;

		int[] boxes={1,2,3};

		int unitSize=3;

		int[] unitsPerBox= {3,2,1};

		int truckSize = 3;

		System.out.println(fillTheTruck(num, boxes, unitSize, unitsPerBox, truckSize));


	}

	public static int fillTheTruck(int num, int[] boxes, int unitSize, int[] unitsPerBox, long truckSize) {
		Map<Integer, List<Integer>> sizeIndexMap = new TreeMap<>(Collections.reverseOrder());
		for(int i = 0; i< unitsPerBox.length; i++){
			List<Integer> indexList = sizeIndexMap.getOrDefault(unitsPerBox[i], new ArrayList<>());
			indexList.add(i);
			sizeIndexMap.put(unitsPerBox[i], indexList);
		}
		int sum = 0;
		for(int size : sizeIndexMap.keySet()){
			for(int i : sizeIndexMap.get(size)){
				int box = boxes[i];
				if(box < truckSize){
					sum += (box * size);
					truckSize -= box;
				}
				else{
					sum += (truckSize * size);
					return sum;
				}
			}
		}

		return sum;

	}
}
