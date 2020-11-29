package Google;

import java.util.*;

public class GoogleNumberOfSubsets {
	static int count = 0;
	public static void main (String args[]){
		int nums [] = {2, 4, 2, 5, 7};
		int k = 10;
		count = 0;
		Arrays.sort(nums);
		Set<Integer> set = new HashSet<>();
		check(nums, k, 0 , new LinkedList<Integer>(), set);
		System.out.println(count);
		String s = "add";
		s.trim();
	}
	public static int getNum(List<Integer> l){
		int res = 0;
		for(int i : l)
			res = res * 10 + i;
		return res;
	}

	public static int countSubsets(int[] nums, int k) {
		Arrays.sort(nums);
		int count = 0;
		for (int lo = 0, hi = nums.length - 1; lo <= hi; ) {
			if (nums[lo] + nums[hi] > k) {
				hi--;
			} else {
				count += 1 << (hi - lo);
				lo++;
			}
		}
		return count;
	}



	public static void check(int nums[], int k, int start , List<Integer> res, Set<Integer> set ){
		if(res.size() > 0 && (res.get(0) + res.get(res.size() -1)) <= k){
			int n = getNum(res);
			//System.out.println(n);
			if(!set.contains(n)){
				set.add(n);
				//System.out.println(res.get(0)  + "   " + res.get(res.size()-1));
				count++;
			}
			/*else
				return;*/

		}
		/*else if(res.size() > 0 && (res.get(0) + res.get(res.size() -1)) > k){
			return;
		}*/
		if(start == nums.length)
			return;
		/*if(res.size() > 0 && (res.get(0) + res.get(res.size() -1)) <= k){
			int n = getNum(res);
			System.out.println(n);
			if(!set.contains(n)){
				set.add(n);
				//System.out.println(res.get(0)  + "   " + res.get(res.size()-1));
				count++;
			}
			*//*else
				return;*//*

		}
		else if(res.size() > 0 && (res.get(0) + res.get(res.size() -1)) > k){
			return;
		}*/
		res.add(nums[start]);
		//System.out.println("before   " + res);
		check(nums,k,start+1,res, set);
		res.remove(res.size()-1);
		//System.out.println("after   " + res);
		check(nums,k,start +1, res, set);
	}
}