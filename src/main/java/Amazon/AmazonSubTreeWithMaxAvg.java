package Amazon;

import DataStructures.TreeNode;

import java.util.ArrayList;
import java.util.List;


// questions need to be checked

public class AmazonSubTreeWithMaxAvg {
	public static void main(String[] args){
		MAryTreeNode root = new MAryTreeNode();
		root.val = 1;
		root.children = new ArrayList<>();
		MAryTreeNode child1 = new MAryTreeNode();
		child1.val = -5;
		child1.children = new ArrayList<>();
		MAryTreeNode child2 = new MAryTreeNode();
		child2.val = 13;
		child2.children = new ArrayList<>();
		MAryTreeNode child3 = new MAryTreeNode();
		child3.val = 4;
		//root.children.add(child1);
	}

	public static MAryTreeNode subtreeMaxAvg(MAryTreeNode root){
		return root;
	}


}
class MAryTreeNode {
	int val;
	List<TreeNode> children;
}