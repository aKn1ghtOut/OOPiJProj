import java.util.*;
import java.awt.*;

abstract class TreeNode{

	protected int value = 0;
	protected Color color = Color.blue;

	protected TreeNode 	left = null, 
						right = null;

	public int getValue()
	{
		return value;
	}

	public TreeNode leftNode()
	{
		return left;
	}

	public TreeNode rightNode()
	{
		return right;
	}

	public Color getColor()
	{
		return color;
	}

	public void setColor(Color c)
	{
		color = c;
	}

	protected abstract void setLeftNode(TreeNode newLeft);
	
	protected abstract void setRightNode(TreeNode newRight);

}

abstract class TreeType
{
	protected TreeNode rootNode = null;

	public abstract TreeNode getRoot();

	public abstract void sortTree();

	public abstract void search(int value); //If this element was being searched for, set color to java.awt.Color.YELLOW else if active element(being moved around or something), set to java.awt.Color.BLACK, else set to NULL;

	public abstract void insertElement(int value);
	
	public abstract void deleteElement(int value);
}