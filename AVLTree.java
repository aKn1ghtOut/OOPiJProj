import java.awt.*;

/* This is a class to implement AVL tree */

class AVLNode extends TreeNode
{
  protected int height = 1;
  protected AVLNode lt, rt;
  protected int key;  

	@Override
	protected void setLeftNode(final TreeNode newLeft) {
		this.left = newLeft;
	}

	@Override
	protected void setRightNode(final TreeNode newRight) {
		this.right = newRight;
	}

	AVLNode(final int val)
	{
		value = val;
	}

	void searchNodes(final int val, final Color cr)
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
  int height(AVLNode root){
	if (root == null)
		return 0; 
	return root.height; 
  }

  // Utitlity function to right rotate subtree
  AVLNode rightRotate(AVLNode y){
	AVLNode x = y.lt; 
	AVLNode T2 = x.rt; 

	// Perform rotation 
	x.rt = y; 
	y.rt = T2; 

	// Update heights 
	x.height = max(height(x.lt), height(x.rt)) + 1; 
	y.height = max(height(y.lt), height(y.rt)) + 1;
	
	// Returning the root
	return x; 
	}

// Utility function to right rotate subtree 
AVLNode leftRotate(AVLNode x) { 
	AVLNode y = x.rt; 
	AVLNode T2 = x.rt; 

	// Perform rotation 
	y.lt = x; 
	x.rt = T2;  

	// Update heights 
	x.height = max(height(x.lt), height(x.rt)) + 1; 
	y.height = max(height(y.lt), height(y.rt)) + 1;
	
	// Returning the root
	return y;
} 

	// Function to find the balance factor of the tree
	int getBalance(AVLNode N) { 
		if (N == null) 
			return 0; 
	
		return height(N.lt) - height(N.rt); 
	}  

	// Insertion function 
	void insertElement(AVLNode root, int val){
		root = insert(root, val); 
	}
	public AVLNode insert(AVLNode root, int val) {
		/* 1. Perform BST Insertion */ 
		// If the tree is empty
		if(root == null)
			root = new AVLNode(val);
		
		if(val < root.key)
			root.left = insert(root.lt, val); 
		else if(val > root.key)
			root.left = insert(root.rt, val);
		// If same, then nothing
		else 
			return root; 
		/* 2. Update the height of the ancestor */ 
		root.height = 1 + max(height(root.lt), height(root.rt)); 
			
		/* If the node became unbalanced */
		int balance = getBalance(root); 

		// Left Left Case
		if(balance > 1 && val < root.lt.key)
			return rightRotate(root); 
		
		// Right Right Case 
		if(balance < -1 && val > root.rt.key)
			return leftRotate(root); 
		
		// Left Right Case 
		if(balance > 1 && val > root.lt.key){
			root.left = leftRotate(root.lt); 
			return rightRotate(root); 
		}
		// Right Left Case
		if(balance < -1 && val > root.rt.key){
			root.right = rightRotate(root.lt); 
			return leftRotate(root); 		
		}

		return root;
	}

	public void deleteElement(final TreeNode root, final int val) {
			
	}


	// Inorder Traversal of the tree
	public String inOrder(AVLNode root) {
		// String to be returned
		String inString = "";

		// Empty Tree
		if(root == null)
			return "";

		// check the left child
		inString += inOrder(root.lt);

		inString += root.value + " ";

		// Check the right child
		inString += inOrder(root.rt);

		// print the current root value
		return inString; 
}
	// PreOrder Traversal of the tree
	public String preOrder(AVLNode root) {
		// String to be returned
		String preString = "";

		// Empty Tree
		if(root == null)
			return "";

		preString += root.value + " ";

		// check the left child
		preString += preOrder(root.lt);

		// Check the right child
		preString += preOrder(root.rt);

		// print the current root value
		return preString;	
	}

	// Postorder Traversal of the tree
	public String postOrder(AVLNode root) {
		// String to be returned
		String postString = "";

		// Empty Tree
		if(root == null)
			return "";

		// check the left child
		postString += postOrder(root.lt);

		// Check the right child
		postString += postOrder(root.rt);

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
		insertElement((AVLNode)rootNode, value);
	}

	@Override
	public String inOrder() {
		return inOrder((AVLNode)rootNode);
	}

	@Override
	public String preOrder() {
		return preOrder((AVLNode)rootNode);
	}

	@Override
	public String postOrder() {
		return postOrder((AVLNode)rootNode);
	}


}
