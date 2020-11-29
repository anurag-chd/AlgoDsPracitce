import DataStructures.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class FindTiltBinaryTree {
	static int sum;
	public static void main (String args[]){
		Integer[] treeArr = {21,7,14,1,1,2,2,3,3};
		TreeNode root = new TreeNode(treeArr[0]);
		Queue<TreeNode> q = new LinkedList<>();

		q.add(root);
		int start = 1;
		while(start < treeArr.length){
			TreeNode temp = q.poll();
			if(treeArr[start] != null){
				temp.left = new TreeNode(treeArr[start]);
				q.add(temp.left);

			}
			start++;
			if(start < treeArr.length && treeArr[start] != null){
				temp.right = new TreeNode(treeArr[start]);
				q.add(temp.right);
			}
			start++;
		}

		System.out.println(findTilt(root));

	}

	public static int findTilt(TreeNode root) {
		if(root == null)
			return 0;
		sum =0;
		check(root);
		return sum;
	}

	public static int check(TreeNode root){
		if(root == null)
			return 0;
		int l = check(root.left);
		int r = check(root.right);
		sum += (r -l);
		System.out.println(sum);
		return root.val + l + r;
	}
}
