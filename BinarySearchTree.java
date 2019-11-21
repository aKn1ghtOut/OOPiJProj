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
		{
      // System.out.println(val);
			color = cr;
		}

		if(left != null)
		((ExampleNode)left).searchNodes(val, cr);


		if(right != null)
		((ExampleNode)right).searchNodes(val, cr);
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
			ExampleNode en = new ExampleNode(value);

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
            root.right = deleteElement(root.right, (int) root.value);
			}

			// Returning the current root
			return root;
	}

	public static int inOrderSuccessor(TreeNode root){
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
			return;
		// Creaing a stack
		Stack<Node> s = new Stack<Node>();
    Node curr = root;

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

		// Empty Tree
		if(root == null)
			return;

		// print the current root value
		System.out.println(node.value + " ");

		// check the left child
		preOrder(root.left);

		// Check the right child
		preOrder(root.right);
	}

	// Postorder Traversal of the tree
	public String postOrder(TreeNode root) {
		// String to be returned
		String postString = "";

		// Empty Tree
		if(root == null)
			return;

		// check the left child
		postOrder(root.left);

		// Check the right child
		postOrder(root.right);

		// print the current root value
		System.out.println(node.value + " ");
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
