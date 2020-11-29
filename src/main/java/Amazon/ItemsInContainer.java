package Amazon;

import java.util.Arrays;

public class ItemsInContainer {

	public static void main(String[] args){
		String s ="|**|*|*";
		int[] startIndices = {1,1};
		int[] endIndices = {5,6};

		int[] res = getItemsInContainer(s, startIndices, endIndices);
		System.out.println(Arrays.toString(res));
	}


	public static int[] getItemsInContainer(String s , int[] startIndices, int[] endIndices){
		if(s == null || s.length() == 0 || startIndices == null || endIndices == null || startIndices.length == 0 || endIndices.length == 0){
			return new int[0];
		}

		int index = 0;
		while(index < s.length() && s.charAt(index) != '|'){
			index++;
		}
		index++;

		int[] nums = new int[s.length()];
		int count = 0;
		int total = 0;
		while(index < s.length()){
			if(s.charAt(index) == '*'){
				count++;
			}
			else{
				total += count;
				count = 0;
			}
			nums[index] = total;
			index++;
		}

		int[] res = new int[startIndices.length];
		for(int i = 0; i< startIndices.length; i++){
			res[i] = nums[endIndices[i]-1] - nums[startIndices[i]-1];
		}
		return res;
	}
}
