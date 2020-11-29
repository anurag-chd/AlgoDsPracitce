import DataStructures.ListNode;
import DataStructures.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class LinkedListInBinaryTree {


	public static void main(String args[]){
		int[] l = {1,4,2,6,8};
		Integer[] treeArr = {1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3};

		ListNode head = new ListNode(l[0]);
		ListNode curr = head;
		for(int i = 1; i < l.length; i++){
			curr.next = new ListNode(l[i]);
			curr = curr.next;
		}

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

		printTree(root);
		System.out.println();
		printList(head);
		System.out.println();


		System.out.println(isSubPath(head, root));



	}

	public static void printList(ListNode head){
		while(head != null){
			System.out.print(head.val + ", ");
			head = head.next;
		}
		System.out.println();
	}

	public static void printTree(TreeNode root){
		if(root == null)
			return;
		System.out.print(root.val + ", ");
		printTree(root.left);
		printTree(root.right);
	}


	public static boolean isSubPath(ListNode head, TreeNode root) {
		//Stack<DataStructures.TreeNode> stack = new Stack<>();
        Deque<TreeNode> stack = new LinkedList<>();
        stack.add(root);//stack.push(root);
        while(!stack.isEmpty()){
            TreeNode temp =   stack.poll();//stack.pop();

            if(temp.val == head.val){
                if(check2(head, temp))
                    return true;
            }

            if(temp.left != null)
                stack.add(temp.left);//stack.push(root.right);
            if(temp.right != null)
                stack.add(temp.right);//stack.push(root.left);
        }
        return false;
		/*boolean ans = check1(head, root);
		if(root!= null && !ans){
			ans = isSubPath(head,root.left) || isSubPath(head, root.right);
		}
		return ans;*/

	}

	public static boolean check2(ListNode head, TreeNode root){
		if(head.next == null)
			return true;
		if(root.left != null && head.next.val == root.left.val ){
			if(check2(head.next, root.left))
				return true;
		}
		if(root.right != null && head.next.val == root.right.val){
			if(check2(head.next, root.right))
				return true;
		}
		return false;

	}


	public static boolean check1(ListNode head, TreeNode root){
		if(head == null)
			return true;
		if(root == null)
			return false;
		if(root.val == head.val){
			return check1(head.next, root.left) || check1(head.next, root.right);
		}
		return false;
	}



}
