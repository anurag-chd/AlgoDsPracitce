package DataStructures;

import java.util.*;

public class BinaryMinHeapWithMap<K,W extends Comparable>{
	Map<K,Integer> indexMap;
	List<Node> heap;
	int size;


	class Node{
		K key;
		W weight;
	}

	public BinaryMinHeapWithMap(){
		indexMap = new HashMap<>();
		heap = new ArrayList<>();
		this.size = 0;
	}

	public boolean isEmpty(){
		return heap.isEmpty();
	}

	public boolean containsKey(K key){
		return indexMap.containsKey(key);
	}

	public void add(K key, W weight){
		if(containsKey(key)){
			updateMinKeyWeight(key, weight);
		}
		else{
			Node node = new Node();
			node.key = key;
			node.weight = weight;

			heap.add(node);
			int index = this.size;
			indexMap.put(node.key,index);
			bottomUpSwapping(index);
			this.size++;
		}

	}

	public void bottomUpSwapping(int index){
		int parentIndex = (index-1)/2;

		while(parentIndex >= 0){
			Node parent = heap.get(parentIndex);
			Node currNode = heap.get(index);
			if(parent.weight.compareTo(currNode.weight) > 0){
				swap(parent, currNode, parentIndex, index);
				updateIndexMap(parent, currNode, parentIndex, index);
				index = parentIndex;
				parentIndex = (parentIndex -1)/2;
			}
			else{
				break;
			}
		}
	}

	public void updateMinKeyWeight(K key, W weight){
		int index = indexMap.get(key);
		heap.get(index).weight = weight;
		bottomUpSwapping(index);
	}

	public void swap(Node parent, Node child, int parentIndex, int childIndex){
		heap.set(parentIndex, child);
		heap.set(childIndex, parent);
	}

	public void updateIndexMap(Node parent, Node child, int parentIndex, int childIndex){
		indexMap.put(parent.key, childIndex);
		indexMap.put(child.key, parentIndex);
	}

	public K extractMin(){
		Node node = getMin();
		return node.key;
	}

	public Node peek(){
		return heap.get(0);
	}

	public Node getMin(){
		if(this.size == 0){
			throw new IllegalArgumentException();
		}
		Node res = new Node();
		res.weight = heap.get(0).weight;
		res.key = heap.get(0).key;
		indexMap.remove(heap.get(0).key);
		heap.get(0).key = heap.get(size-1).key;
		heap.get(0).weight = heap.get(size-1).weight;
		indexMap.remove(heap.get(size-1).key);
		heap.remove(size-1);
		this.size--;
		int index = 0;
		int leftChildIndex = ( 2 * index) +1;
		int rightChildIndex = (2 * index) +2;
		while(leftChildIndex < size){

			int smallerIndex = (rightChildIndex >= size ||
					(heap.get(leftChildIndex).weight.compareTo(heap.get(rightChildIndex).weight ) < 0)) ?
					leftChildIndex : rightChildIndex;
			Node child = heap.get(smallerIndex);
			Node parent = heap.get(index);
			if(parent.weight.compareTo(child.weight) > 0){
				swap(parent, child, index, smallerIndex);
				updateIndexMap(parent, child, index, smallerIndex);
				index = smallerIndex;
				leftChildIndex = (2 * index) +1;
				rightChildIndex = (2 * index) +2;
			}
			else
				break;
		}

		return res;
	}


}
