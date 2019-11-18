import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

class Start extends JFrame implements Runnable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	BufferedImage	bufferImage = (BufferedImage)createImage(1000, 1000);

	Graphics 		drawGraphics,
					bufferGraphics;

	JPanel			drawPane,
					cPane,
					controlPane,
					leftControlPanel,
					rightControlPanel,
					buttonsPanel;

	int 			vert_dist = 50,
					hori_dist = 50;

	TreeType		tr 	=	null;

	JButton		 	searchButton = new JButton("Search"),
					addButton = new JButton("Add"),
					deleteButton = new JButton("Delete");

	JRadioButton	AVLTree = new JRadioButton("AVT Tree"),
					BSTree = new JRadioButton("BST Tree"),
					RBTree = new JRadioButton("Red Black Tree");

	ButtonGroup		radioGroup = new ButtonGroup();

	JTextField		nodeField = new JTextField(20);
					

	@Override
	public void run() {

		if(bufferImage == null);
		bufferImage = (BufferedImage)createImage(1000, 1000);

		bufferGraphics = bufferImage.getGraphics();
		drawGraphics = drawPane.getGraphics();

		this.update(drawGraphics);

		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {
		}

		this.run();
	}

	public void update(Graphics g)
	{
		bufferGraphics.setColor(Color.BLACK);
		bufferGraphics.fillRect(0, 0, 1000, 1000);
		
		processTree();

		g.drawImage(bufferImage, 0, 0, Color.white, drawPane);
	}

	public void processTree()
	{
		if(tr == null)
		tr = new ExampleTree();

		TreeNode l = tr.getRoot();

		if(l == null)
		return;

		processNode(l, drawPane.getWidth() / 2, 50, drawPane.getWidth() / 4);

	}

	public void processNode(TreeNode tn, int x, int y, int d_x)
	{
		TreeNode 	left = tn.leftNode(),
					right = tn.rightNode();

		Color col = tn.getColor();
		col = col == null ? Color.blue : col;

		bufferGraphics.setColor(col);
		bufferGraphics.fillOval(x - 25, y - 25, 50, 50);

		bufferGraphics.setColor(Color.white);
		bufferGraphics.drawString(tn.value + "", x - 5, y - 5);

		if(left != null)
		{
			bufferGraphics.drawLine(x, y, x - d_x, y + vert_dist);
			processNode(left, x - d_x, y + vert_dist, (d_x / 2));
		}

		if(right != null)
		{
			bufferGraphics.drawLine(x, y, x + d_x, y + vert_dist);
			processNode(right, x + d_x, y + vert_dist, (d_x / 2));
		}
	}

	Start()
	{

		drawPane = new JPanel();
		cPane = new JPanel();
		controlPane = new JPanel();
		leftControlPanel = new JPanel();
		rightControlPanel = new JPanel();
		buttonsPanel = new JPanel();

		cPane.setLayout(new BorderLayout());
		controlPane.setLayout(new BoxLayout(controlPane, BoxLayout.X_AXIS));

		leftControlPanel.setLayout(new BoxLayout(leftControlPanel, BoxLayout.Y_AXIS));
		leftControlPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		radioGroup.add(BSTree);
		radioGroup.add(AVLTree);
		radioGroup.add(RBTree);

		leftControlPanel.add(BSTree);
		leftControlPanel.add(AVLTree);
		leftControlPanel.add(RBTree);

		rightControlPanel.setLayout(new BoxLayout(rightControlPanel, BoxLayout.Y_AXIS));
		rightControlPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		buttonsPanel.setLayout(new FlowLayout());
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);

		rightControlPanel.add(nodeField);

		buttonsPanel.add(addButton);

		addButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int val = Integer.parseInt(nodeField.getText());
				tr.insertElement(val);
			}
		});

		buttonsPanel.add(searchButton);
		buttonsPanel.add(deleteButton);

		rightControlPanel.add(buttonsPanel);

		controlPane.add(Box.createHorizontalGlue());
		controlPane.add(leftControlPanel);
		controlPane.add(Box.createHorizontalGlue());
		controlPane.add(rightControlPanel);
		controlPane.add(Box.createHorizontalGlue());

		cPane.add(drawPane, BorderLayout.CENTER);
		cPane.add(controlPane, BorderLayout.SOUTH);

		setContentPane(cPane);
		setSize(1000, 1000);
		setTitle("Tree Visualizer");
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		bufferImage = (BufferedImage)createImage(1000, 1000);

		bufferGraphics = bufferImage.getGraphics();
		drawGraphics = drawPane.getGraphics();
		new Thread(this).start();
	}

	public static void main(String args[])
	{
		new Start();
	}
	
}