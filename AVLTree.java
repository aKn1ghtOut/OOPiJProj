import java.util.*;

import javax.lang.model.util.ElementScanner6;

import java.awt.*;

/* This is a class to implement AVL tree */

class AVLNode extends TreeNode
{
  protected int height = 1;
	@Override
	protected void setLeftNode(TreeNode newLeft) {
		this.left = newLeft;
	}

	@Override
	protected void setRightNode(TreeNode newRight) {
		this.right = newRight;
	}

	AVLNode(int val)
	{
		value = val;
	}

	void searchNodes(int val, Color cr)
	{

		if(value == val)
		{
      // System.out.println(val);
			color = cr;
		}

		if(left != null)
		((AVLNode)left).searchNodes(val, cr);


		if(right != null)
		((AVLNode)right).searchNodes(val, cr);
	}

}

public class AVLTree extends TreeType
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
		((AVLNode)rootNode).searchNodes(value, Color.RED);
	}

	// Insertion function
	void binaryInsert(int key) {
		insertElement(key);
	}

  // Utility functions

  // function to return the maximum of two integers
  int max(int a, int b){
    return (a > b) ? a : b;
  }

  // function to calculate the height of the tree
  int height(TreeNode root){
	if (root == null)
		return 0; 
	return ((AVLNode)root).height; 
  }

  // Utitlity function to right rotate subtree
  TreeNode rightRotate(TreeNode y){
	AVLNode x = (AVLNode)y.left; 
	TreeNode T2 = x.right; 

	// Perform rotation 
	x.right = y; 
	y.left = T2; 

	// Update heights 
	x.height = max(height(x.left), height(x.right)) + 1; 
	((AVLNode)y).height = max(height(y.left), height(y.right)) + 1;
	
	// Returning the root
	return x; 
	}

// Utility function to right rotate subtree 
TreeNode leftRotate(TreeNode x) { 
	AVLNode y = (AVLNode)x.right; 
	TreeNode T2 = y.left; 

	// Perform rotation 
	y.left = x; 
	x.right = T2; 

	//  Update heights 
	((AVLNode)x).height = max(height(x.left), height(x.right)) + 1; 
	y.height = max(height(y.left), height(y.right)) + 1; 

	// Return new root 
	return y; 
} 

	// Function to find the balance factor of the tree
	int getBalance(TreeNode N) { 
		if (N == null) 
			return 0; 
	
		return height(N.left) - height(N.right); 
	}  

	// Insertion function 
	void insertElement(TreeNode root, int val){
		root = insert(root, val); 
	}
	public TreeNode insert(TreeNode root, int val) {
		/* 1. Perform BST Insertion */ 
		// If the tree is empty
		if(root == null)
			root = new AVLNode(val);
		
		if(val < root.value)
			root.left = insert(root.left, val); 
		else if(val > root.value)
			root.left = insert(root.left, val);
		// If same, then nothing
		else 
			return root; 
		/* 2. Update the height of the ancestor */ 
		int h = ((AVLNode)root).height; 
		h = 1 + max(height(root.left), height(root.right)); 
			
		/* If the node became unbalanced */
		int balance = getBalance(root); 

		// Left Left Case
		if(balance > 1 && val < root.left.value)
			return leftRotate(root); 
		
		// Right Right Case 
		if(balance < -1 && val > root.right.value)
			return rightRotate(root); 
		
		// Left Right Case 
		if(balance > 1 && val > root.left.value){
			root.left = leftRotate(root.left); 
			return rightRotate(root); 
		}
		// Right Left Case
		if(balance < -1 && val > root.right.value){
			root.right = rightRotate(root.left); 
			return leftRotate(root); 		
		}

		return root;
	}

	public void deleteElement(TreeNode root, int val) {
			
	}


	// Inorder Traversal of the tree
	public String inOrder(TreeNode root) {
		// String to be returned
		String inString = "";

		// Empty Tree
		if(root == null)
			return "";

		// check the left child
		inString += inOrder(root.left);

		inString += root.value + " ";

		// Check the right child
		inString += inOrder(root.right);

		// print the current root value
		return inString; 
}
	// PreOrder Traversal of the tree
	public String preOrder(TreeNode root) {
		// String to be returned
		String preString = "";

		// Empty Tree
		if(root == null)
			return "";

		preString += root.value + " ";

		// check the left child
		preString += preOrder(root.left);

		// Check the right child
		preString += preOrder(root.right);

		// print the current root value
		return preString;	
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
	public void insertElement(int value) {
		insertElement(rootNode, value);
	}

	@Override
	public String inOrder() {
		return inOrder(rootNode);
	}

	@Override
	public String preOrder() {
		return preOrder(rootNode);
	}

	@Override
	public String postOrder() {
		return postOrder(rootNode);
	}


}
