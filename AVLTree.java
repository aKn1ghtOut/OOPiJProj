import java.util.*;
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

  }

  // Utitlity function to right rotate subtree
  TreeNode rightRotate(TreeNode y)
  {

  }


	public void insertElement(int value) {

	}

	public TreeNode deleteElement(TreeNode root, int val) {
			// If the term is the node to be deleted
			if (root == null)
				return root;

			// If the the value is less than the root then move to the left side
			if(val < (int) root.value){
				root.left = deleteElement(root.left, val);
			}
			// If the the value is greater than the root then move to the right side
			else if (val > (int) root.value){
				root.right = deleteElement(root.right, val);
			}

			// if we have arrived at the leaf
			else{
            if (root.left == null)
                return root.right;
      			else if (root.right == null)
                return root.left;

						// Moving the data from the inorder Successor to the current position
						root.value = inOrderSuccessor(root.right);
            root.right = deleteElement(root.right, (int)root.value);
			}

			// Returning the current root
			return root;
	}

	public static int inOrderSuccessor(TreeNode root){
		// Added the changes - check if working
		if(root.left == null && root.right == null)
			return null;
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
		String inString  = "";

		// Empty Tree
		if(root == null)
			return inString;

		// Creaing a stack
		Stack<TreeNode> s = new Stack<TreeNode>();
    TreeNode curr = root;

		// Travering the tree
		while(curr != null || s.size() > 0){

			// Reaching the left most node and pusing values onto the stack
			while(curr != null){
				s.push(curr);
				curr = curr.left;
			}

			// Since curr is null right now, we assign the leftmost node to it and move upwards
			curr = s.pop();

			// Concatinating to the string
			inString += (String)curr.value;

			// After visiting the left nodes and the parents, we now visit the right nodes
			curr = curr.right;
		}

		return inString;
	}

	// Preorder Traversal of the tree
	public String preOrder(TreeNode root) {
		// String to be returned
		String preString = "";

		if(root = null)
			return preString;

		Stack<TreeNode> s = new Stack<TreeNode>();
		s.push(root);

		// Run while stack is not empty
		while(!s.empty()){

			// Printing the top item
			TreeNode curr = s.pop();
			preString += (String)(int)curr.value;

			// Push right and left children of the popped node to stack ( right first because fifo)
      if (curr.right != null)
          s.push(curr.right);
      if (curr.left != null)
          s.push(curr.left);
		}
		return preString;
}

	// Postorder Traversal of the tree
	public String postOrder(TreeNode root) {

		// String to be returned
		String postString = "";

		// Creating s1 and s2 which were declared in line 46
		s1 = new Stack<>();
		s2 = new Stack<>();

		if (root == null)
			return postString;

		// Push root to the firt stack
		s1.push(root);

		// Run while the first stack is filled
		while(!s1.isEmpty()){

			// Pop an item from s1 and push it to s2
		TreeNode temp = s1.pop();
		s2.push(temp);

		// Push left and right children of removed item to s1
		if(temp.left != null)
		 	s1.push(temp.left);
		if(temp.right != null)
			s1.push(temp.right);

		}

		// Print all elements of the second stack
		while(!s2.isEmpty()){
			TreeNode curr = s2.pop();
			postString += (String)curr.value;
		}

		return postString;

	}

// Overriding all the abstract functions
	@Override
	public void deleteElement(int value) {
		deleteElement(rootNode, value);
	}

	@Override
	public void	inOrder() {
		inOrder(rootNode);
	}

	@Override
	public void	preOrder() {
		preOrder(rootNode);
	}

	@Override
	public void postOrder() {
		postOrder(rootNode);
	}


}
