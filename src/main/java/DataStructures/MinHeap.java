package DataStructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class  MinHeap <T extends Comparable>{
	int size;
	List<T> heap;


	public MinHeap(){
		this.size = 0;
		this.heap = new ArrayList<>();
	}

	public void add(T element){
		this.heap.add(element);
		int index = this.size;
		int parentIndex = (index-1)/2;
		while(parentIndex >=0){
			T parent = heap.get(parentIndex);
			if(element.compareTo(parent) < 0){
				heap.set(parentIndex, element);
				heap.set(index, parent);
				index = parentIndex;
				parentIndex = (index-1)/2;
			}
			else{
				break;
			}
		}
		this.size++;
	}

	public T poll(){
		if(this.size == 0)
			throw new IllegalArgumentException();
		T res = heap.get(0);
		heap.set(0, heap.get(this.size-1));
		heap.set(this.size-1, null);
		this.size--;
		int index = 0;
		int leftChildIndex = (index * 2) +1;
		int rightChildIndex = (index *2)+2;
		while(leftChildIndex <= size -1){
			int smallerElementIndex = (rightChildIndex >size-1) || (heap.get(leftChildIndex).compareTo(heap.get(rightChildIndex)) < 0) ? leftChildIndex : rightChildIndex;
			T child = heap.get(smallerElementIndex);
			T parent = heap.get(index);
			if(child.compareTo(parent) < 0){
				heap.set(smallerElementIndex,parent);
				heap.set(index, child);
				index = smallerElementIndex;
				leftChildIndex = (index * 2) +1;
				rightChildIndex = (index *2)+2;
			}
			else
				break;

		}

		return res;
	}
	@Override
	public String toString(){
		return heap.toString();
	}


	public static void main(String[] args){
		MinHeap<Integer> heap = new MinHeap<>();
		int[] arr = {10,12,13,4,5,16};
		for(int i : arr){
			heap.add(i);
		}

		System.out.println(heap);
		int start = 0;
		while(heap.size > 0){
			arr[start++] = heap.poll();
		}
		System.out.println(Arrays.toString(arr));
	}

}
