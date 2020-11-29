import java.util.Arrays;

public class SortingWithHeapify {

	public static void main(String[] args){
		int[] arr = {10,12,13,4,5,16,3};
		System.out.println(Arrays.toString(heapSort(arr)));
	}

	public static int[] heapSort(int[] arr){
		heapify(arr);
		System.out.println(Arrays.toString(arr));
		for(int i =0; i< arr.length; i++){
			pollAndSet(arr,arr.length -1-i);
		}
		return arr;
	}

	public static void pollAndSet(int[] arr, int end){
		int index = 0;
		int temp = arr[index];
		arr[index] = arr[end];
		arr[end] = temp;
		end--;
		while((index * 2) +1 < end){
			int leftChild = index *2 +1;
			int rightChild = index*2 + 2;
			int smallerIndex = rightChild > end || (arr[rightChild] > arr[leftChild])?leftChild: rightChild;
			if (arr[smallerIndex] < arr[index]) {
				int small = arr[smallerIndex];
				arr[smallerIndex] = arr[index];
				arr[index] = small;
				index = smallerIndex;
			}
			else
				break;
		}



	}

	public static void heapify(int[] arr ){
		int end = arr.length-1;
		int index = end;
		while(index >= 0){
			int start = index;
			while((start * 2) +1 < end){
				int leftChild = start *2 +1;
				int rightChild = start*2 + 2;
				int smallerIndex = rightChild > end || (arr[rightChild] > arr[leftChild])?leftChild: rightChild;
				if (arr[smallerIndex] < arr[start]) {
					int small = arr[smallerIndex];
					arr[smallerIndex] = arr[start];
					arr[start] = small;
					start = smallerIndex;
				}
				else{
					break;
				}
			}
			index--;
		}
	}
}
