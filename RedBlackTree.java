import java.util.*;
import java.awt.*;

/* This is a class to implement binary search tree */

class RedBlackNode extends TreeNode
{

  int color;

  /* Constructor */
  public RedBlackNode(int elem)
  {
     this(elem, null, null);
  }
  /* Constructor */
  public RedBlackNode(int elem, RedBlackNode lt, RedBlackNode rt)
  {
     left = lt;
     right = rt;
     element = elem;
     color = 1;
  }
}

public class RedBlackTree extends TreeType
{
  // Important class variables

  private RedBlackNode current;
  private RedBlackNode parent;
  private RedBlackNode grand;
  private RedBlackNode great;
  private RedBlackNode header;
  private static RedBlackNode nullNode;

  // static initializer for nullNode
  static
  {
     nullNode = new RedBlackNode(0);
     nullNode.left = nullNode;
     nullNode.right = nullNode;
  }
  // Black - 1  RED - 0
  static final int BLACK = 1;
  static final int RED   = 0;

  // Constructor
  public RedBlackTree(int num){
    header = new RedBlackTree(num);
    header.left = nullNode;
    header.right = nullNode;
  }

	@Override
	public TreeNode getRoot() {
		// TODO Auto-generated method stub
		return rootNode;
	}

	// @Override
	// public void sortTree() {
	// 	// TODO Auto-generated method stub
	// }

	@Override
	public void search(int value) {
		((BinaryNode)rootNode).searchNodes(value, Color.RED);
	}

	// Insertion function
	void binaryInsert(int key) {
		insertElement(key);
	}

  // UTILITY FUNCTIONS

  // Counting the number of nodes
  public int countNodes(){
     return countNodes(header.right);
  }
  private int countNodes(RedBlackNode r){
       if (r == nullNode)
           return 0;
       else
       {
           int l = 1;
           l += countNodes(r.left);
           l += countNodes(r.right);
           return l;
       }
   }

   // Rotation functions
   private RedBlackNode rotate(int val, RedBlackNode parent){
     if(val < parent.element)
          return parent.left = val < parent.left.element ? rotateWithLeftChild(parent.left) : rotateWithRightChild(parent.left) ;
    else
          return parent.right = val < parent.right.element ? rotateWithLeftChild(parent.right) : rotateWithRightChild(parent.right);
   }

  // Rotation with the left child
  private RedBlackNode rotateWithLeftChild(RedBlackNode k2){
    RedBlackNode k1 = k2.left;
    k2.left = k1.right;
    k1.right = k2;
    return k1;
  }

  // Rotation with the right child
  private RedBlackNode rotateWithRightChild(RedBlackNode k1){
    RedBlackNode k2 = k1.right;
    k1.right = k2.left;
    k2.left = k1;
    return k2;
  }

  // function to check if the tree is empty
  public boolean isEmpty(){
    return header.right == nullNode;
  }

  // function to return the maximum of two integers
  int max(int a, int b){
    return (a > b) ? a : b;
  }

	public void insertElement(int val) {
    current = parent = grand = great;
    nullNode.value = val;

    while(current.value != val)
    {
      great = grand;
      grand = parent;
      parent = current;
      current = (val < current.value) ? current. : current.right;

      // Check if two red children and fix if so
      if (current.left.color == RED && current.right.color == RED)
          handleReorient(val);
    }

    // Insertion fails if already present
      if (current != nullNode)
         return;

      current = new RedBlackNode(val, nullNode, nullNode);
      // Attach to the parent
      if(item < parent.element)
        parent.left = current;
      else
        parent.right = current;
      handleReorient(val);
	}

  // Reorientation after changing function
  private void handleReorient(int val){
    // Color flipping
    current.color = RED;
    current.left.color = BLACK;
    current.right.color = BLACK;

    if(parent.color = RED)
    {
      // Have to rightRotate
      grand.color = RED;
      if((val < grand.element) != (val < parent.element))
        parent = rotate(val, grand); // Start dbl rotate
      current = rotate(val, great);
      current.color = BLACK;
    }
    // Make root color black
    header.right.color = BLACK;
  }



	public TreeNode deleteElement(TreeNode root, int val) {

	}

  // Search function
  void searchNodes(RedBlackNode r, int val, Color cr)
  {
    while ((r != nullNode) && !found)
         {
             int rval = r.element;
             if (val < rval)
                 r = r.left;
             else if (val > rval)
                 r = r.right;
             else
             {
                r.color = cr
                break;
             }
             found = searchNodes(r, val, cr);
         }
         return found;

    if(value == val)
      this.color = cr;
    else
      this.color = defaultColor;

    if(left != null)
    ((BinaryNode)left).searchNodes(val, cr);


    if(right != null)
    ((BinaryNode)right).searchNodes(val, cr);
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
