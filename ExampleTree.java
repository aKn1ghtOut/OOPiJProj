package project;


import java.util.*;
import java.awt.*;

class ExampleNode extends TreeNode
{

	@Override
	protected void setLeftNode(TreeNode newLeft) {
		this.left = newLeft;
	}

	@Override
	protected void setRightNode(TreeNode newRight) {
		this.right = newRight;
	}

	ExampleNode(int val)
	{
		value = val;
	}

	void searchNodes(int val, Color cr)
	{

		if(value == val)
		{
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
		((ExampleNode)rootNode).searchNodes(value, Color.RED);
	}

	@Override
	public void insertElement(int value) {
		if(rootNode == null)
			rootNode = new ExampleNode(value);
		else
		{
			TreeNode curr = rootNode;
			ExampleNode en = new ExampleNode(value);
			int ran = (int)(Math.random() * 2);

			while((ran == 0 ? curr.leftNode() : curr.rightNode()) != null)
			{
				curr = ran == 0 ? curr.leftNode() : curr.rightNode();
				ran = (int)(Math.random() * 2);
			}

			if(ran == 0)
			curr.setLeftNode(en);
			else
			curr.setRightNode(en);
		}
	}

	@Override
	public void deleteElement(int value) {
	}

}
