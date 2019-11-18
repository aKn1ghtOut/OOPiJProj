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

public class ExampleTree extends TreeType
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

	public BinaryNode insertElement(int value) {

    // If the tree is empty
		if(rootNode == null)
			rootNode = new BinaryNode(value);
		else
		{
      // Creating a new node
      TreeNode curr = rootNode;
			ExampleNode en = new ExampleNode(value);

			if(value < rootNode.value)
			   curr.setLeftNode(en);
			else
			   curr.setRightNode(en);
		}
	}

	@Override
	public void deleteElement(int value) {

	}

}
