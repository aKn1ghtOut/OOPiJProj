import java.util.*;
import java.awt.*;

/* This is a class to implement binary search tree */

class BinaryNode extends TreeNode
{

	@Override
	protected void setLeftNode(TreeNode newLeft) {
		this.left = newLeft;
	}

	@Override
	protected void setRightNode(TreeNode newRight) {
		this.right = newRight;
	}

	BinaryNode(int val)
	{
		value = val;
	}

	void searchNodes(int val, Color cr)
	{

		if(value == val)
			this.color = cr;
		else
			this.color = defaultColor;

		if(left != null)
		((BinaryNode)left).searchNodes(val, cr);


		if(right != null)
		((BinaryNode)right).searchNodes(val, cr);
	}

}

public class BinarySearchTree extends TreeType
{

	@Override
	public TreeNode getRoot() {
		// TODO Auto-generated method stub
		return rootNode;
	}

	@Override
	public void sortTree() {
		// TODO Auto-generated method stub

	}

	@Override
	public void search(int value) {
		((BinaryNode)rootNode).searchNodes(value, Color.RED);
	}

	// Insertion function
	void binaryInsert(int key) {
		insertElement(key);
	}

	public void insertElement(int value) {

    // If the tree is empty
		if(rootNode == null)
			rootNode = new BinaryNode(value);
		else
		{
      // Creating a new node
      TreeNode curr = rootNode;
			BinaryNode en = new BinaryNode(value);

			int inserted = 0;

			while(inserted != 1)
			{
				if(value < curr.value)
				{
					if(curr.leftNode() != null)
					curr = curr.leftNode();
					else
					{
						curr.setLeftNode(en);
						inserted = 1;
					}
				}
				else
				{
					if(curr.rightNode() != null)
					curr = curr.rightNode();
					else
					{
						curr.setRightNode(en);
						inserted = 1;
					}
				}
			}
		}
	}

	// BinarySearchTree Deletion
	void deleteElement(TreeNode root, int val){
		root = deleteRec(root, val);
	}
	public TreeNode deleteRec(TreeNode root, int val) {
			// If the term is the node to be deleted
			if (root == null)
				return root;

			// If the the value is less than the root then move to the left side
			if(val < (int) root.value)
				root.left = deleteRec(root.left, val);

			// If the the value is greater than the root then move to the right side
			else if (val > (int) root.value)
				root.right = deleteRec(root.right, val);

			// if we have arrived at the leaf
			else{
            if (root.left == null)
                return root.right;
      			else if (root.right == null)
                return root.left;

						// Moving the data from the inorder Successor to the current position
						root.value = inOrderSuccessor(root.right);
            root.right = deleteRec(root.right, (int)root.value);
			}

			// Returning the current root
			return root;
	}

	public static int inOrderSuccessor(TreeNode root){
		// Added the changes - check if working
		// if(root.left == null && root.right == null)
		// 	return -1;
		int min = (int)root.value;
		while(root.left != null){
			min = (int)root.left.value;
			root = root.left;
		}
		return min;
	}

	// Inorder Traversal of the tree
	public String inOrder(TreeNode root) {
		// String to be returned
		String postString = "";

		// Empty Tree
		if(root == null)
			return "";

		// check the left child
		postString += inOrder(root.left);

		postString += root.value + " ";

		// Check the right child
		postString += inOrder(root.right);

		// print the current root value
		return postString;
	}

	// Preorder Traversal of the tree
	public String preOrder(TreeNode root) {
		// String to be returned
		String postString = "";

		// Empty Tree
		if(root == null)
			return "";

		postString += root.value + " ";

		// check the left child
		postString += preOrder(root.left);

		// Check the right child
		postString += preOrder(root.right);

		// print the current root value
		return postString;
	}

	// Postorder Traversal of the tree
	public String postOrder(TreeNode root) {

		// String to be returned
		String postString = "";

		// Empty Tree
		if(root == null)
			return "";

		// check the left child
		postString += postOrder(root.left);

		// Check the right child
		postString += postOrder(root.right);

		postString += root.value + " ";

		// print the current root value
		return postString;
	}

// Overriding all the abstract functions
	@Override
	public void deleteElement(int value) {
		deleteElement(rootNode, value);
	}

	@Override
	public String inOrder() {
		// TODO Auto-generated method stub
		return inOrder(rootNode);
	}

	@Override
	public String preOrder() {
		// TODO Auto-generated method stub
		return preOrder(rootNode);
	}

	@Override
	public String postOrder() {
		// TODO Auto-generated method stub
		return postOrder(rootNode);
	}

}
