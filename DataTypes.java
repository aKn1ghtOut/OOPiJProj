import java.util.*;
import java.awt.*;

abstract class TreeNode{

	private int value = 0;
	private int color = null;

	private TreeNode left, right;

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

	protected abstract void setLeftNode(TreeNode newLeft);
	
	protected abstract void setRightNode(TreeNode newLeft);

}

abstract class TreeType
{
	private TreeNode rootNode = null;

	public abstract TreeNode getRoot();

	public abstract void sortTree();

	public abstract void search(int value); //If this element was being searched for, set color to java.awt.Color.YELLOW else if active element(being moved around or something), set to java.awt.Color.BLACK, else set to NULL;

	public abstract void insertElement(int value);
	
	public abstract void deleteElement(int value);
}