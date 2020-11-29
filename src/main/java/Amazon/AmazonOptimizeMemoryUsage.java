package Amazon;

import javax.swing.*;
import java.util.*;

public class AmazonOptimizeMemoryUsage {

	public static void main(String[] args){
		int[] foregroundTasks = {6, 6, 6};
		int[] backgroundTasks = {1};
		int K = 7;
		List<int[]> ints = optimizeMemoryUsage(foregroundTasks, backgroundTasks, K);
		for(int[] i : ints){
			System.out.println(Arrays.toString(i));
		}
	}

	public static List<int[]> optimizeMemoryUsage(int[] foregroundTasks, int[] backgroundTasks, int K) {
		TreeMap<Integer, List<Integer>> foreGroundMap = new TreeMap<>(Collections.reverseOrder());
		TreeMap<Integer, List<Integer>> backGroundMap = new TreeMap<>(Comparator.reverseOrder());
		List<int[]> resList = new ArrayList<>();
		int maxUsage = 0;
		Set<Integer> usedTime = new HashSet<>();
		for(int i = 0; i< foregroundTasks.length; i++){
			if(foregroundTasks[i] == K){
				resList.add(new int[]{i,-1});
				maxUsage = K;
			}
			else if(foregroundTasks[i] < K){
				List<Integer> l = foreGroundMap.getOrDefault(foregroundTasks[i], new ArrayList<>());
				l.add(i);
				foreGroundMap.put(foregroundTasks[i],l);
			}
		}

		for(int i = 0; i< backgroundTasks.length ; i++){
			if(backgroundTasks[i] == K){
				resList.add(new int[]{-1,i});
				maxUsage = K;
			}
			else if(backgroundTasks[i] < K){
				List<Integer> l = backGroundMap.getOrDefault(backgroundTasks[i], new ArrayList<>());
				l.add(i);
				backGroundMap.put(backgroundTasks[i],l);
			}

		}



		for(int i  : foreGroundMap.keySet()){
			if(i > K){
				continue;
			}
			List<Integer> index1List = foreGroundMap.get(i);
				int rem = K -i;
				if(backGroundMap.containsKey(rem) ){
					List<Integer> index2List = backGroundMap.get(rem);
					if(maxUsage < K){
						List<int[]> l = new ArrayList<>();
						for(int j : index1List){
							for(int k : index2List){
								l.add(new int[]{j,k});
							}
						}
						resList = l;
						maxUsage = K;
					}
					else {
						for(int j : index1List){
							for(int k : index2List){
								resList.add(new int[]{j,k});
							}
						}
					}
				}
				else{
					if(backGroundMap.higherKey(rem)!= null){
						rem = backGroundMap.higherKey(rem);
						List<Integer> index2List = backGroundMap.get(rem);
						if(i + rem > maxUsage){
							List<int[]> l = new ArrayList<>();
							for(int j : index1List){
								for(int k : index2List){
									l.add(new int[]{j,k});
								}
							}
							resList = l;
							maxUsage = i + rem;
						}
						else if(i + rem == maxUsage){
							for(int j : index1List){
								for(int k : index2List){
									resList.add(new int[]{j,k});
								}
							}
						}
					}
				}

		}

		return resList;



	}
}
