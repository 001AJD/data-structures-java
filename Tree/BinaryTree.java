import java.util.ArrayList;

class Node {
	int key;
	Node left, right;

	Node(int item) {
		this.key = item;
		this.left = this.right = null;
	}
}

public class BinaryTree {
	Node root;

	BinaryTree() {
		root = null;
	}

	int getBinaryTreeHeight(Node n) {
		if (n == null) {
			return 0;
		}
		int lDepth = 1 + getBinaryTreeHeight(n.left);
		int rDepth = 1 + getBinaryTreeHeight(n.right);

		int max = Math.max(lDepth, rDepth);
		return max;
	}

	void preOrderTraversal(Node n) {
		if (n == null) {
			return;
		}
		System.out.print(n.key + " ");
		preOrderTraversal(n.left);
		preOrderTraversal(n.right);
	}

	void postOrderTraversal(Node n) {
		if (n == null) {
			return;
		}
		postOrderTraversal(n.left);
		postOrderTraversal(n.right);
		System.out.print(n.key + " ");
	}

	void inOrderTraversal(Node n) {
		if (n == null) {
			return;
		}
		inOrderTraversal(n.left);
		System.out.print(n.key + " ");
		inOrderTraversal(n.right);
	}

	boolean lookup(Node n, int target) {
		// case1 --> node is null
		if (n == null) {
			return false;
		}
		// case2 --> node contains the target
		if (n.key == target) {
			return true;
		}
		// case3 --> target < n.data
		if (target < n.key) {
			return (lookup(n.left, target));
		}
		// case4 --> target > n.data
		else {
			return (lookup(n.right, target));
		}
	}

	Node insertNode(Node n, int d) {
		// case 1 --> if n is null return new node
		if (n == null) {
			return (new Node(d));
		} else {
			// case 2 --> if d < n.key recur the left subtree
			if (d < n.key) {
				n.left = insertNode(n.left, d);
			}
			// case 3 --> if d > n.data recur the right subtree
			else {
				n.right = insertNode(n.right, d);
			}
		}

		return n;
	}

	public int getBinaryTreeSize(Node n)
	{
		// calculating the size of Binary Tree using Pre-Order Traversal
		if(n == null)
		{
			return 0;
		}
		else
		{
			return (getBinaryTreeSize(n.left) + 1 + getBinaryTreeSize(n.right));
		}
	}

	public int getMinDataValue(Node n)
	{
		// assumption --> The binary Search tree will be always non-empty
		Node current = n;
		// check if node has left child node
		if(n.left != null)
		{
			// set current node as left child node
			while(current.left != null){
				current = current.left;
			}
		}
		return current.key;
		
	}

	public static void printPaths(Node n)
	{
		int[] pathList = new int[1000];
		printPathsFromNodeToLeaf(n, pathList, 0);
	}

	public static void printPathsFromNodeToLeaf(Node n, int[] pathList, int pathLen)
	{
		// check if the node is null
		if(n == null){
			return;
		}
		// if node is not null add key to path list
		pathList[pathLen] = n.key;
		pathLen ++;
		// check if left and right subtree exists, if yes then recur the left and right subtree
		//if left and right subtree does not exists print the list
		if(n.left == null && n.right == null)
		{
			helperPrintPath(pathList, pathLen);
		}
		else
		{
			printPathsFromNodeToLeaf(n.left, pathList, pathLen);
			printPathsFromNodeToLeaf(n.right, pathList, pathLen);
		}
	}

	public static void helperPrintPath(int[] pathList, int pathLen)
	{
		for(int i = 0; i < pathLen; i++)
		{
			System.out.print(pathList[i] + " "); 
		}
		System.out.println();
	}

	public static void main(String args[]) {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(5);
		tree.root.left = new Node(3);
		tree.root.right = new Node(9);
		tree.root.left.left = new Node(1);
		tree.root.left.right = new Node(4);
		tree.root.right.left = new Node(6);
		System.out.println("Binary Search tree created...");

		System.out.println("Pre Order :");
		tree.preOrderTraversal(tree.root);
		System.out.println();

		System.out.println("Post Order :");
		tree.postOrderTraversal(tree.root);
		System.out.println();

		System.out.println("In Order :");
		tree.inOrderTraversal(tree.root);
		System.out.println();

		System.out.println("Height of tree => " + tree.getBinaryTreeHeight(tree.root));

		System.out.println("do we have 5 in the tree ..." + tree.lookup(tree.root, 5));
		System.out.println("do we have 10 in the tree ..." + tree.lookup(tree.root, 10));

		System.out.println("Let's Insert new node 10 in tree....");
		tree.insertNode(tree.root, 10);
		System.out.println("do we have 10 in the tree ..." + tree.lookup(tree.root, 10));

		System.out.println("Size of Binary Tree --> " + tree.getBinaryTreeSize(tree.root));

		System.out.println("Find the minimum data value from BST --> " + tree.getMinDataValue(tree.root));

		System.out.println("Print all paths in tree");
		printPaths(tree.root);
	}
}