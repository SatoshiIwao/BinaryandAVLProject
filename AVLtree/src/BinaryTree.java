import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;


// fix a node's xpos and ypos here, then apply to the Main class

public class BinaryTree<T extends Comparable<T>> extends Application {

	
	private AVLNode<T> root;
	private int size;
	private AVLNode<T> succ;
	private AVLNode<T> pred;
	private double n=1f;
	private String space = "";
	private Circle ball;
	
	static double startX = 0f;
	static double startY = 0f;
	
	public static double RightX = 30f;
	public static double RightY = 30f;
	public static double LeftY = 30f;
	
	private double previousLineStartPointX = 0f;
	private double previousLineStartPointY = 0f;
	
	public static double startMillisec = 100.0f;
	public static double incrementDuration = 100.0f;
	public static int cuepoint = 0;
	
	public static Timeline MainTimeline;
	
	AVLNode<T> tarNode = null;
	
	double tarXpos = 0f;
	double tarYpos = 0f;
	
	public BinaryTree() {
		this.root = null;
		this.size=0;
		this.succ = null;
		this.pred = null;
	}
	
	public BinaryTree(T e) {
		this.root = new AVLNode<T>(e);
		this.size=1;
		this.succ = null;
		this.pred = null;
		
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return (this.size == 0);
	}
	
    public boolean add(T e){ 
		
		if (!search(e)){
			// gui===============================================
			 if (AVLMain.RandomCheck==0){
				AVLMain.SliderValue();	
				cuepoint = 0;
				MainTimeline = new Timeline();
			}
			
			 if (AVLMain.p.lookup("#ball")==null) {
				ball = Animation1.genBall();
				AVLMain.p.getChildren().addAll(ball);
			}
			// gui===============================================
			
			this.n =1.0f;
			previousLineStartPointX = 0f;
			previousLineStartPointY = 0f;
			
			
			
			space += "          ";
			root = add(this.root, e, startX, startY);
			this.size++;
			
			// gui===============================================
			if (AVLMain.RandomCheck==0) {
				MainTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(AVLTree.startMillisec), "last",
						new EventHandler<ActionEvent>() {

							public void handle(ActionEvent arg0) {
								AVLMain.pb.setProgress(MainTimeline.getCurrentTime().toMillis()/MainTimeline.getCycleDuration().toMillis());
								MainTimeline.pause();	
							}
					
						},
						(KeyValue) null
						));
				
				MainTimeline.setCycleCount(MainTimeline.INDEFINITE);
				MainTimeline.setAutoReverse(false);
				MainTimeline.play();
				AVLTree.startMillisec = 100.0f;
			}
			cuepoint = 0;
			
			// gui===============================================

		
			//this.displayNodeLine();
			return true;
		}
		return false;
	}
	
	private AVLNode<T> add(AVLNode<T> node, T e, double xpos, double ypos){
		
		if (node == null) {
			AVLNode<T> newNode = new AVLNode<T>(e, xpos, ypos); 
			String value = ""+e;
			
			if (node != this.root) {
				// insert new line and ball ===new version=====GUI======================================================
				if (AVLMain.RandomCheck==0) {
					Line line = null;
					
					if (previousLineStartPointX<xpos) {				
						line = Animation1.genRightLine(previousLineStartPointX, previousLineStartPointY, value);
						MainTimeline.getKeyFrames().addAll(Animation1.NewLineTraverseFrame(line, ball, xpos, ypos, (++cuepoint)));
						
						AVLMain.p.getChildren().addAll(line);
					}else if ((previousLineStartPointX>xpos)){
						line =  Animation1.genLeftLine(previousLineStartPointX, previousLineStartPointY, value);
						
						MainTimeline.getKeyFrames().addAll(Animation1.NewLineTraverseFrame(line, ball, xpos+RightX, ypos, (++cuepoint))); 
						
						AVLMain.p.getChildren().addAll(line);
					}
				}
				// insert new line and ball =================GUI======================================================	
			}
			// insert new node ==new version=======GUI================================================================			
			if (AVLMain.RandomCheck==0) {
				StackPane RootNode = Animation1.genNode(value, xpos, ypos);
				
				MainTimeline.getKeyFrames().addAll(Animation1.InsertNewNodeFrame(RootNode, (++cuepoint)));
				
				AVLMain.p.getChildren().add(RootNode);
			}
			// insert root node =========================================================================			
			return newNode;
		}
		
		// draw left line ================GUI==============================================
		if (AVLMain.RandomCheck==0) {
			String lineId = "line"+node.element;
			
			if (lineId.contains(".")) {
				lineId = lineId.replace(".", "dot");
			}
			
			if (AVLMain.p.lookup("#"+lineId)==null) {
				
			}
			else {
				Line line = (Line) AVLMain.p.lookup("#"+lineId);
				MainTimeline.getKeyFrames().addAll(Animation1.ExistingLineTraverseFrame(line, ball, (++cuepoint)));
			}
			
			previousLineStartPointX = xpos;
			previousLineStartPointY = ypos;
		}
		// draw left line ================GUI==============================================
		
		
		// add current node scale to animation ======================GUI=================================
		if (AVLMain.RandomCheck==0) {
			String nodeEle = ""+node.element;
			StackPane Node = getStackPaneFromNode(node);
			
			MainTimeline.getKeyFrames().addAll(Animation1.ScaleNodeFrame(Node, (++cuepoint)));
		}
		// add current node scale to animation ======================GUI=================================
			
		if (e.compareTo(node.element)<0) { // e<node			
						
			this.n+=1.5;
			space += "          ";
			node.left = add(node.left, e, (xpos-(2000*(1/(Math.pow(n, 2))))), (ypos+100.00));
			
			this.n-=1.5;
		}
		else if (e.compareTo(node.element)>0) { // e > node
			
			this.n+=1.5;
			node.right = add(node.right, e,(xpos+(2000*(1/(Math.pow(n, 2))))), (ypos+100.00));
			
			this.n-=1.5;
		}
		return node;
	}
	
	public StackPane getStackPaneFromNode(AVLNode<T> node) {
		
		if (node != null) {
		
			String e = ""+node.element;
			
			 if (e.contains(".")) {
			    	e = e.replace(".", "dot");
			 }
			
			StackPane sp = (StackPane) AVLMain.p.lookup("#node"+e);
			
			return sp;
		}
		return null;
	}
	
	public Line getLineFromNode(AVLNode<T> node) {
		
		if (node != null) {
			
			String e = ""+node.element;
			
			 if (e.contains(".")) {
			    	e = e.replace(".", "dot");
			 }
			
			Line line = (Line) AVLMain.p.lookup("#line"+e);
			return line;
		}
		return null;
	}
	
	public void changeLineID(AVLNode<T> oldnode, AVLNode<T> newnode) {
		
		if (oldnode != null) {
			if (newnode != null) {
				
				String e = ""+oldnode.element;
				
				 if (e.contains(".")) {
				    	e = e.replace(".", "dot");
				 }
				
				Line line = (Line) AVLMain.p.lookup("#line"+e);
				
				if (line!=null) {
					
					String e1 = ""+newnode.element;
					
					if (e1.contains(".")) {
					   	e1 = e1.replace(".", "dot");
					}
					
					line.setId("line"+e1);
				}
			}
		}
	}
	
	public ArrayList<KeyFrame> PickUpStackPaneLine(StackPane nodepane, Line line, AVLNode<T> node, double startx, double starty, double endx, double endy, int cue) {
		
		ArrayList<KeyFrame> portionOfFrame = new ArrayList<KeyFrame>();
		
		// get original position 
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec), ""+cue, 
				new EventHandler<ActionEvent>(){@Override
					public void handle(ActionEvent event) {
					
					if (AVLMain.BT.isSelected()) {
						AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getTotalDuration().toMillis());
					}else if (AVLMain.AVL.isSelected()) {
						AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getTotalDuration().toMillis());
					}
				}},
				new KeyValue(nodepane.layoutXProperty(), nodepane.getLayoutX()), 
				new KeyValue(nodepane.layoutYProperty(), nodepane.getLayoutY()), 
				new KeyValue(line.startXProperty(), line.getStartX()), 
				new KeyValue(line.startYProperty(), line.getStartY()),
				new KeyValue(line.endXProperty(), line.getEndX()), 
				new KeyValue(line.endYProperty(), line.getEndY())));
		
		// get original position 
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec+=(1*BinaryTree.incrementDuration)), 
				new EventHandler<ActionEvent>(){@Override
					public void handle(ActionEvent event) {
					if (AVLMain.BT.isSelected()) {
						AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getTotalDuration().toMillis());
					}else if (AVLMain.AVL.isSelected()) {
						AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getTotalDuration().toMillis());
					}
				}},
				new KeyValue(nodepane.layoutXProperty(), node.xpos), 
				new KeyValue(nodepane.layoutYProperty(), node.ypos), 
				new KeyValue(line.startXProperty(), startx), 
				new KeyValue(line.startYProperty(), starty),
				new KeyValue(line.endXProperty(), endx), 
				new KeyValue(line.endYProperty(), endy)));
		
		return portionOfFrame;
	}
	
	private void RepositionNodeLine(AVLNode<T> node, double n, double xpos, double ypos, 
			double linestartx, double linestarty, int leftrightcheck) {
		int rightleftcheck = 0; // 0 : default, -1 : left, 1 : right
		
		RepositionNodeLineRecursion(node, n, xpos, ypos, linestartx, linestarty, leftrightcheck);
		
	}
	
	private void RepositionNodeLineRecursion(AVLNode<T> node, double n, double xpos, double ypos, 
			double linestartx, double linestarty, int leftrightcheck) {
		
		if (node != null) {
			
			node.xpos = xpos;
			node.ypos = ypos;
			
			if (AVLMain.RandomCheck==0) {
				StackPane nodepane = getStackPaneFromNode(node);
				Line line = getLineFromNode(node);
				
				if (leftrightcheck == -1) {
					MainTimeline.getKeyFrames().addAll(PickUpStackPaneLine(nodepane, line, node, linestartx, linestarty, node.xpos+RightX, node.ypos, cuepoint));
					// check after ====================================================================================================
				}
				else if (leftrightcheck == 1) {
					MainTimeline.getKeyFrames().addAll(PickUpStackPaneLine(nodepane, line, node, linestartx, linestarty, node.xpos, node.ypos, cuepoint));
					// check after ====================================================================================================
				}
			}
						
			if (node.left!=null) {
				RepositionNodeLineRecursion(node.left, n+1.5f, xpos-(2000*(1/(Math.pow((n+1.5), 2)))), (ypos+100.00f), node.xpos, node.ypos+LeftY, -1);
			}
			
			if (node.right != null) {
				RepositionNodeLineRecursion(node.right, n+1.5f, xpos+(2000*(1/(Math.pow((n+1.5), 2)))), (ypos+100.00f), node.xpos+RightX, node.ypos+RightY, 1);
			}
		}
	}
	
	public AVLNode<T> getNode(T e) {
		
		if (!this.isEmpty()) {
			if (this.search(e)) {
				return getNode(this.root, e);
			}
		}
		return null;
	}
	
	private AVLNode<T> getNode(AVLNode<T> node, T e) {
		
			if (e.compareTo(node.element)>0) {
				return getNode(node.right, e);
			}
			else if (e.compareTo(node.element)<0) {
				return getNode(node.left, e);
			}  
			else if (e.compareTo(node.element)==0) {
				return node;
			}
		
		return node;
	}
	
	public boolean search(T e) {
		if (!this.isEmpty()) {
			
			boolean be = false;
			
			be = search(this.root, e, be);
			
			return be;
		}
		else  {
			return false;
		}	
	}
	// return boolean (true : found, false: no found)
	private boolean search(AVLNode<T> node,T e, boolean be) {
		if (node == null) {
			be = false;
			return be;
		}
		else {
			if (e.compareTo(node.element)>0) {
				be = search(node.right, e, be);
			}
			else if (e.compareTo(node.element)<0) {
				be = search(node.left, e, be);
			}  
			else if (e.compareTo(node.element)==0) {
				be = true;
				return be;
			}
		}
		return be;
	}
	
	public T MaxElement() {
		return MaxRecursion(this.root).element;
	}
	
	private AVLNode<T> MaxRecursion(AVLNode<T> node) {
		if(node.right == null) {
			return node;
		}
		else 
			return MaxRecursion(node.right);
	}
	
	public T MinElement() {
		return MinRecursion(this.root).element;
	}
	
	private AVLNode<T> MinRecursion(AVLNode<T> node) {
		if (node.left==null) {
			return node;
		}
		else 
			return MinRecursion(node.left);
	}
	
	public T Predecessor(AVLNode<T> node, T e) {
		if (!this.isEmpty()) {
			if (this.search(e)) {
				if (e.compareTo(this.MinElement())!=0) {
					
					AVLNode<T> pd = PredRec(node, e);
					
					if (pd == null) {
						return null;
					}
					else 
						return pd.element;
					
				}
			}
		}
		return null;
	}
	
	public T predecessor(T e) {
		if (!this.isEmpty()) {
			if (this.search(e)) {
				if (e.compareTo(this.MinElement())!=0) {
					
					AVLNode<T> pd = PredRec(this.root, e);
					
					if (pd == null) {
						return null;
					}
					else 
						return pd.element;
				}
			}
		}
		return null; 
	}
	
	private AVLNode<T> PredRec(AVLNode<T> node, T e) {
		//node>e
		if (node.element.compareTo(e)>0) {
			return PredRec(node.left, e);
		}//node<e
		else if (node.element.compareTo(e)<0) {
			this.pred = node;
			return PredRec(node.right, e);
		}
		else if (node.element.compareTo(e)==0) {
			if (node.left!=null) {
				return PredRightRecursion(node.left, e);
			}
			else 
				return this.pred;
		}
		return node;
	}
	
	private AVLNode<T> PredRightRecursion(AVLNode<T> node, T e) {
		
		if (node.right == null) {
			return node;
		}
		else {
			node = PredRightRecursion(node.right, e);
		}
		return node;
	}
	
	public T Successor(AVLNode<T> node, T e) {
		if (!this.isEmpty()) {
			if (this.search(e)) {
				if (e.compareTo(this.MaxElement())!=0) {
					
					AVLNode<T> sc = SuccRec(node, e);
					
					if (sc == null) {
						return null;
					}
					else 
						return sc.element;
				}
			}
		}
		return null; 
	}
	
	public T successor(T e) {
		if (!this.isEmpty()) {
			if (this.search(e)) {
				if (e.compareTo(this.MaxElement())!=0) {
					
					AVLNode<T> sc = SuccRec(this.root, e);
					
					if (sc == null) {
						return null;
					}
					else 
						return sc.element;
				}
			}
		}
		return null; 
	}
	
	private AVLNode<T> SuccRec(AVLNode<T> node, T e) {
		//node>e
		if (node.element.compareTo(e)>0) {
			this.succ = node;
			return SuccRec(node.left, e);
		}//node<e
		else if (node.element.compareTo(e)<0) {
			return SuccRec(node.right, e);
		}
		else if (node.element.compareTo(e)==0) {
			if (node.right!=null) {
				return succLeftRecursion(node.right, e);
			}
			else 
				return this.succ;
		}
		return node;
	}
	
	private AVLNode<T> succLeftRecursion(AVLNode<T> node, T e) {
		
		if (node.left == null) {
			return node;
		}
		else {
			node = succLeftRecursion(node.left, e);
		}
		return node;
	}
	
	private class SubAVLNode<T extends Comparable<T>> {
		AVLNode<T> sp; // successor or predecessor 
		AVLNode<T> tree; // tree that successor or predecessor was removed
		
		public SubAVLNode(AVLNode<T> s) {
			this.sp = s;
			this.tree = null;
		}
		
		public SubAVLNode(AVLNode<T> s, AVLNode<T> t) {
			this.sp = s;
			this.tree = t;

		}
	}
	
	public T Rm(T e) {
		if (!this.isEmpty()) {
			if (this.search(e)) {
				
				// gui===============================================
				 if (AVLMain.RandomCheck==0){
						AVLMain.SliderValue();	
						cuepoint = 0;
						MainTimeline = new Timeline();
					}
					
					 if (AVLMain.p.lookup("#ball")==null) {
						ball = Animation1.genBall();
						AVLMain.p.getChildren().addAll(ball);
					}
				// gui===============================================
				
				SubAVLNode<T> sn = RR(this.root, e, 1.0f);
				
				this.root = sn.tree;
				// gui===============================================
				if (AVLMain.RandomCheck==0) {
					MainTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(AVLTree.startMillisec), "last",
							new EventHandler<ActionEvent>() {

								public void handle(ActionEvent arg0) {
									AVLMain.pb.setProgress(MainTimeline.getCurrentTime().toMillis()/MainTimeline.getCycleDuration().toMillis());
									
									MainTimeline.pause();	
								}
						
							},
							(KeyValue) null
							));
					
					MainTimeline.setCycleCount(MainTimeline.INDEFINITE);
					MainTimeline.setAutoReverse(false);
					MainTimeline.play();
					AVLTree.startMillisec = 100.0f;
				}
				cuepoint = 0;		
				// gui===============================================
				
				//this.displayNodeLine();
				
				
				AVLMain.ta.clear();
				AVLMain.ta.setText("removed "+ sn.sp.element+ " successfully");
				
				this.size--;
				return sn.sp.element;
			}
			AVLMain.ta.clear();
			AVLMain.ta.setText("invalid value");
		}
		AVLMain.ta.clear();
		AVLMain.ta.setText("tree is empty!");
		
		return null; 
	}
	
	private SubAVLNode<T> RR(AVLNode<T> node, T e, double num) {
		
		// draw left line ================GUI==============================================
		if (node != this.root) {
			String lineId = "line"+node.element;
						
			 if (lineId.contains(".")) {
				 lineId = lineId.replace(".", "dot");
			 }
			
			Line line = (Line) AVLMain.p.lookup("#"+lineId);			
			
			MainTimeline.getKeyFrames().addAll(Animation1.ExistingLineTraverseFrame(line, ball, (++cuepoint)));
		}
		// draw left line ================GUI==============================================
		
		
		// add current node scale to animation ======================GUI=================================
		String nodeEle = ""+node.element;
		StackPane Node = getStackPaneFromNode(node);
		
		MainTimeline.getKeyFrames().addAll(Animation1.ScaleNodeFrame(Node, (++cuepoint)));
		// add current node scale to animation ======================GUI=================================

		if (node.element.compareTo(e)>0) {
			SubAVLNode<T> sn = RR(node.left, e, (num+1.5f));//===============================================
			
			node.left = sn.tree;//===============================================
			
			sn.tree = node;//===============================================
			
			return sn;
		}
		
		if (node.element.compareTo(e)<0) {
			SubAVLNode<T> sn = RR(node.right, e, (num+1.5f));//===============================================
			
			node.right = sn.tree;//===============================================
			
			sn.tree = node;//===============================================
			
			space = space.substring(0, space.length()-10);
			return sn;
		}
		
		if (this.NumChild(node.element)==0) {
			SubAVLNode<T> sn = new SubAVLNode(node, null);
			
			// delete node and line ========GUI=============================================================			
			
			StackPane removeNode = getStackPaneFromNode(node);
			Line removeLine = getLineFromNode(node);
			
			MainTimeline.getKeyFrames().addAll(Animation1.deleteNodeLineFrame(removeNode, removeLine, (++cuepoint)));
						
			// delete node and line ========GUI=============================================================
			
			return sn;
			
		}
				
		if (this.NumChild(node.element)>=1)	{
			//=========hold up Target node until you find out successor or predecessor======GUI==========
			StackPane tarPane = getStackPaneFromNode(node);
			//myanimation.getChildren().add(Animation1.holdUpTargetNode(tarPane));
			
			MainTimeline.getKeyFrames().addAll(Animation1.holdUpTargetNodeFrame(tarPane, (++cuepoint)));
			
			
			//=========hold up Target node until you find out successor or predecessor======GUI==========
			
			// ========keep target node position======GUI===============
			tarXpos = node.xpos;
			tarYpos = node.ypos;
			
			tarNode = new AVLNode(node.element, node.xpos, node.ypos);
			// ========keep target node position======GUI===============

			SubAVLNode<T> sn = null;
			if (node.right!= null) {
				sn = RRS(node.right, e, (num+1.5f));
				
				AVLNode<T> temp = node;
				
				node = sn.sp;
				
				node.left = temp.left;
								
				sn.sp = temp;
				
				node.right = sn.tree;
				
				// let's move portion tree affected by sop movement and remove target node=============================
				if (node.right != null) {
					RepositionNodeLine(node.right, (num+1.5f),  tarNode.xpos+(2000*(1/(Math.pow((num+1.5f), 2)))), tarNode.ypos+100.00f, tarNode.xpos+RightX, tarNode.ypos+RightY, 1);
				}
				
				StackPane removeNode = getStackPaneFromNode(tarNode);
				
				//myanimation.getChildren().add(Animation1.deleteOnlyNode(removeNode));
				
				MainTimeline.getKeyFrames().addAll(Animation1.deleteOnlyNodeFrame(removeNode, (++cuepoint)));
				
				
				//===============================================================================
				
				sn.tree = node;
	
				return sn;
			}
			else if (node.left != null) {
				sn = RRP(node.left, e, (num+1.5f));
				AVLNode<T> temp = node;
				
				node = sn.sp;
						
				node.right = temp.right;
								
				sn.sp = temp;
				
				node.left = sn.tree;
				
				// let's move portion tree affected by sop movement and remove target node=============================
				if (node.left != null) {
					RepositionNodeLine(node.left, (num+1.5f),  tarNode.xpos-(2000*(1/(Math.pow((num+1.5f), 2)))), tarNode.ypos+100.00f, tarNode.xpos, tarNode.ypos+LeftY, -1);
				}
				
				StackPane removeNode = getStackPaneFromNode(tarNode);
				
				//myanimation.getChildren().add(Animation1.deleteOnlyNode(removeNode));
				
				MainTimeline.getKeyFrames().addAll(Animation1.deleteOnlyNodeFrame(removeNode, (++cuepoint)));
				
				//===============================================================================
				
				sn.tree = node;
			
				return sn;	
			}
		}	
		
		return null;
	}

	private SubAVLNode<T> RRS(AVLNode<T> node, T e, double num) {
		
		if (node == null) {
			return null;
		}
		
		// draw left line ================GUI==============================================
		if (node != this.root) {
			String lineId = "line"+node.element;
			
			if (lineId.contains(".")) {
				lineId = lineId.replace(".", "dot");
			}
			
			Line line = (Line) AVLMain.p.lookup("#"+lineId);			
			
			MainTimeline.getKeyFrames().addAll(Animation1.ExistingLineTraverseFrame(line, ball, (++cuepoint)));			
		}
		// draw left line ================GUI==============================================
		/*
				^-----^
			   ( - _ - )
			   | |   | | 
		*/
		// add current node scale to animation ======================GUI=================================
		String nodeEle = ""+node.element;
		StackPane Node = getStackPaneFromNode(node);
		
		MainTimeline.getKeyFrames().addAll(Animation1.ScaleNodeFrame(Node, (++cuepoint)));
		
		// add current node scale to animation ======================GUI=================================

		if (node.left==null) {
			
			SubAVLNode<T> sn = new SubAVLNode(node, node.right);
			
			// let's move sop (successor or predecessor) to the target position and delete sop line=========================
			StackPane SopNode = getStackPaneFromNode(sn.sp);
			Line removeLine = getLineFromNode(sn.sp);
			StackPane scaleDownNode = getStackPaneFromNode(tarNode);
			
			//myanimation.getChildren().add(Animation1.moveSopAndDeleteLine(SopNode, removeLine, tarNode.xpos, tarNode.ypos, scaleDownNode));
			
			MainTimeline.getKeyFrames().addAll(Animation1.moveSopAndDeleteLineFrame(SopNode, removeLine, tarNode.xpos, tarNode.ypos, scaleDownNode, (++cuepoint)));
				
			node.xpos = tarNode.xpos;
			node.ypos = tarNode.ypos; // reassign sop node position to target node position (fields of position)
			
			changeLineID(tarNode, sn.sp);
			//=================================
			return sn;
		}
		
		if (node.left != null) {
			SubAVLNode<T> sn = RRS(node.left, e, (num+1.5f));
			
			node.left = sn.tree;
			
			sn.tree = node;
		
			return sn;
		}
		
		return null;
	}
	
	private SubAVLNode<T> RRP(AVLNode<T> node, T e, double num) {
		if (node == null) {
			return null;
		}
		
		// draw left line ================GUI==============================================
		if (node != this.root) {
			String lineId = "line"+node.element;
			
			if (lineId.contains(".")) {
				lineId = lineId.replace(".", "dot");
			}
			
			Line line = (Line) AVLMain.p.lookup("#"+lineId);			
				
			MainTimeline.getKeyFrames().addAll(Animation1.ExistingLineTraverseFrame(line, ball, (++cuepoint)));
		
		}
		// draw left line ================GUI==============================================
		
		
		// add current node scale to animation ======================GUI=================================
		String nodeEle = ""+node.element;
		StackPane Node = getStackPaneFromNode(node);
	
		MainTimeline.getKeyFrames().addAll(Animation1.ScaleNodeFrame(Node, (++cuepoint)));
		// add current node scale to animation ======================GUI=================================

		
		
		if (node.right==null) {
			
			SubAVLNode<T> sn = new SubAVLNode(node, node.left);
			
			// let's move sop (successor or predecessor) to the target position and delete sop line=========================
			StackPane SopNode = getStackPaneFromNode(sn.sp);
			Line removeLine = getLineFromNode(sn.sp);
			StackPane scaleDownNode = getStackPaneFromNode(tarNode);
			
			//myanimation.getChildren().add(Animation1.moveSopAndDeleteLine(SopNode, removeLine, tarNode.xpos, tarNode.ypos, scaleDownNode));
			MainTimeline.getKeyFrames().addAll(Animation1.moveSopAndDeleteLineFrame(SopNode, removeLine, tarNode.xpos, tarNode.ypos, scaleDownNode, (++cuepoint)));
		
			node.xpos = tarNode.xpos;
			node.ypos = tarNode.ypos; // reassign sop node position to target node position (fields of position)

			
			changeLineID(tarNode, sn.sp);
			//=====and change target line id with sop line id============================
	
			return sn;
		}
		
		if (node.right != null) {
			SubAVLNode<T> sn = RRP(node.right, e, (num+1.5f));
			
			node.right = sn.tree;
			
			sn.tree = node;
			
			return sn;
		}
		
		return null;
	}
	
	public int NumChild(T e) {
		if (!this.isEmpty()) {
			if (this.search(e)) {
				return NumChild(this.root, e);
			}
		}
		return -1;
	}
	
	public int NumChild(AVLNode<T> node,T e) {
		
		if (e.compareTo(node.element)>0) {
			return NumChild(node.right, e);
		}
		else if (e.compareTo(node.element)<0) {
			return NumChild(node.left, e);
		}  
		else if (e.compareTo(node.element)==0) {
			if (node.right == null && node.left == null) {
				return 0;
			}
			else if (node.right == null|| node.left == null) {
				return 1;
			}
			else if (node.right!= null && node.left!=null) {
				return 2;
			}
		}
		return -1;
	}
	
	
	
	public void preorder() {
		
		if (!this.isEmpty()){
			// gui===============================================
			AVLMain.SliderValue();
			MainTimeline = new Timeline();
			
			cuepoint = 0;
			// gui===============================================
		
			preorder(this.root);
			
			// gui===============================================
			//Animation1.PlayAnimation(myanimation);
			
			MainTimeline.play();
			
			// gui===============================================
		}
	}
	
	// element, left, then right
	private void preorder(AVLNode<T> node)
	{
		if (node!=null) {
			
			AVLMain.ta.appendText(String.valueOf(node.element) + " -> ");
			
			// add current node scale to animation ======================GUI=================================
			String nodeEle = ""+node.element;
			StackPane Node = getStackPaneFromNode(node);
			MainTimeline.getKeyFrames().addAll(Animation1.ScaleNodeFrame(Node, ++cuepoint));
			
			// add current node scale to animation ======================GUI=================================
			
			preorder(node.left);
			preorder(node.right);
		}
	}
	
	public void inorder() { 
		
		if (!this.isEmpty()){
			// gui===============================================
			AVLMain.SliderValue();
			MainTimeline = new Timeline();
			cuepoint = 0;
			// gui===============================================
			
			inorder(this.root);
			
			// gui===============================================		
			MainTimeline.play();
			// gui===============================================
		}
		
	}
	// left, element, then right
	private void inorder(AVLNode<T> node)
	{
		if (node!=null) {
			inorder(node.left);
			AVLMain.ta.appendText(String.valueOf(node.element) + " -> ");
			
			// add current node scale to animation ======================GUI=================================
			String nodeEle = ""+node.element;
			StackPane Node = getStackPaneFromNode(node);
			
			MainTimeline.getKeyFrames().addAll(Animation1.ScaleNodeFrame(Node, ++cuepoint));
			
			// add current node scale to animation ======================GUI=================================
			
			
			inorder(node.right);
		}
	}
	
	public void postorder() { 
		
		if (!this.isEmpty()){
			// gui===============================================
			AVLMain.SliderValue();
			MainTimeline = new Timeline();
			cuepoint = 0;
			// gui===============================================
		
			postorder(this.root);
			
			// gui===============================================
			MainTimeline.play();
			
			// gui===============================================
		}
	}
	// left, right, then element;
	public void postorder(AVLNode<T> node)
	{
		if (node!=null) {
			postorder(node.left);
			postorder(node.right);
			AVLMain.ta.appendText(String.valueOf(node.element) + " -> ");
			
			// add current node scale to animation ======================GUI=================================
			String nodeEle = ""+node.element;
			StackPane Node = getStackPaneFromNode(node);
			//myanimation.getChildren().addAll(Animation1.ScaleNode(Node));
			MainTimeline.getKeyFrames().addAll(Animation1.ScaleNodeFrame(Node, ++cuepoint));
		
			
			// add current node scale to animation ======================GUI=================================
			
		}
	}
	//based on height;
	public void levelorder() {
		
		if (!this.isEmpty()){
			// gui===============================================
			AVLMain.SliderValue();
			//myanimation = new SequentialTransition();
			MainTimeline = new Timeline();
			
			
			cuepoint = 0;
			// gui===============================================
	
			this.LevelOrder(this.root);
			
			// gui===============================================
				//Animation1.PlayAnimation(myanimation);			
			MainTimeline.play();
			
			// gui===============================================
		}
	}
	
	private void LevelOrder(AVLNode<T> node) {
		String result = "";
		Queue<AVLNode<T>> nodeQ = new LinkedList<AVLNode<T>>();
		
		if (node!=null) {
			nodeQ.add(node);
			while(!nodeQ.isEmpty()){
				AVLNode<T> next = nodeQ.remove();
				result += (String.valueOf(next.element) + " -> ");
				
				// add current node scale to animation ======================GUI=================================
				String nodeEle = ""+next.element;
				StackPane Node = getStackPaneFromNode(next);
				MainTimeline.getKeyFrames().addAll(Animation1.ScaleNodeFrame(Node, ++cuepoint));

				
				// add current node scale to animation ======================GUI=================================
				
				if(next.left != null){
					nodeQ.add(next.left);
				}
				if(next.right != null){
					nodeQ.add(next.right);
				}
			}
			AVLMain.ta.appendText(result.substring(0,result.length() - 4));
		}
	}

	@Override
	public void start(Stage arg0) throws Exception {
		
	}
	
	public void DrawLineForRandom() {
		int leftrightCheck = 0; // 0 : default , -1 : left, 1 : right
	
		DrawLineForRandomRecursion(this.root, this.root.xpos, this.root.ypos, leftrightCheck);
	
	}
	
	public void DrawLineForRandomRecursion(AVLNode<T> node, double parentxpos, double parentypos, int check) {
		
		if (node!=null) {
			if (check==-1){
				if (node!=this.root) {
					AVLMain.p.getChildren().add(Animation1.genLineForRandom(""+node.element, parentxpos ,parentypos+LeftY, node.xpos+RightX, node.ypos));
				}
				
			}else if (check==1) {
				if (node != this.root) {
					AVLMain.p.getChildren().add(Animation1.genLineForRandom(""+node.element, parentxpos+RightX, parentypos+RightY, node.xpos, node.ypos));
				}
			}
			
			if (node.left!=null) {
				DrawLineForRandomRecursion(node.left, node.xpos, node.ypos, -1);
			}
			
			if (node.right!=null) {
				check = 1;
				DrawLineForRandomRecursion(node.right, node.xpos, node.ypos, 1);
			}	
		}
	}
}
