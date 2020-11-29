package Amazon;

import java.util.Arrays;

public class AmazonDivideArrayIntoThree {

	public static void main(String[] args){
		int[] arr = {2, 4, 5, 3, 3, 9, 2, 2, 2};
		System.out.println(check(arr));
	}

	public static boolean check(int[] arr){
		if(arr.length < 5)
			return false;
		int sum = Arrays.stream(arr).sum();
		int[] leftSum = new int[arr.length];
		int[] rightSum = new int[arr.length];

		leftSum[0]=0;
		int end = arr.length-1;
		rightSum[end] = 0;
		for(int i =1; i<= end ; i++){
			leftSum[i] = arr[i-1] + leftSum[i-1];
			rightSum[end-i] = arr[end-i +1] + rightSum[end-i +1];
		}
		//System.out.println(Arrays.toString(leftSum));
		//System.out.println(Arrays.toString(rightSum));

		int left = 1, right = end-1;
		while(left < right -1){
			int interSum = sum - arr[left] - arr[right];
			if(interSum %3 == 0){
				if(interSum/3 == leftSum[left] && leftSum[left] == rightSum[right])
					return true;
			}
			if(leftSum[left] > rightSum[right])
				right--;
			else if(leftSum[left] < rightSum[right])
				left++;
			else{
				left++;
				right--;
			}
		}

		return false;
	}

}
