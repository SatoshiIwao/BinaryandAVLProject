
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.RadioMenuItemBuilder;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AVLMain extends Application {
	public static String type = "Integer";
	public static Label inslabel;
	public static Label splabel;
	public static Button insbtn;
	public static TextField resulttxt;
	public static TextField instxt;
	public static TextArea ta;
	public static Button removebtn;
	public static Group p ;
	public static MenuBar menubar;
	public static Slider slider;
	public static double RightX = 60f;
	public static double RightY = 60f;
	public static double LeftY = 60f;
	public static int RandomCheck = 0; // 0 : not random, 1 : random 
	public static ProgressBar pb;
	VBox VboxTop;
	HBox hboxBot;
	BorderPane border;
	BorderPane subborder;
	HBox uphbox;
	public static Scene sc;
	
	AVLTree<Integer> intTree;
	AVLTree<Double> dblTree;
	AVLTree<String> strTree;
	
	BinaryTree<Integer> intBT;
	BinaryTree<Double> doubleBT;
	BinaryTree<String> stringBT;
	
	public static RadioMenuItem BT;
	public static RadioMenuItem AVL;
	
	//public static SequentialTransition myanimationForRandom;
	public static Timeline mytimelineForRandom;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public static void main(String[] args)  {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		BTbuilder();
		treeGUI();
		MenuGUI();
		sceneGUI();
		
		primaryStage.setTitle("Binary Tree");
		primaryStage.setScene(sc);
		primaryStage.show();
	}
	
	public void sceneGUI() {
		double width = screenSize.getWidth() - 80;
		double height = screenSize.getHeight() - 80;
		border = new BorderPane();
		
		
		subborder.setTop(uphbox);
		subborder.setBottom(hboxBot);
		
		border.setTop(VboxTop);
		border.setBottom(subborder);
		border.setCenter(p);
		//border.setStyle("-fx-background-image : url(bg.jpg)");
		sc = new Scene(border, width, height);
		
	}
	
	public void BTbuilder() {
		intTree = new AVLTree<Integer>();
		dblTree = new AVLTree<Double>();
		strTree = new AVLTree<String>();
		
		intBT = new BinaryTree<Integer>();
		doubleBT = new BinaryTree<Double>();
		stringBT = new BinaryTree<String>();
	}
	
	public void treeGUI() {
		p = new Group();
	}
	
	@SuppressWarnings("deprecation")
	public void MenuGUI() {
		double screenwidth = screenSize.getWidth();
		VboxTop = new VBox();
		hboxBot = new HBox();
		hboxBot.setAlignment(Pos.CENTER);
		menubar = new MenuBar();
		
		ta = new TextArea("Result here...");
		ta.setPrefSize(screenwidth/2 - 50, 50);
	
		inslabel = new Label("Enter Your Data Here: ");
		insbtn = new Button("insert");
		instxt = new TextField();
		resulttxt = new TextField();
		removebtn = new Button("remove");
		
		//declare sub-menus and add to menubar - option values
		ToggleGroup tGroup = new ToggleGroup();
		Menu inputType = new Menu("Data Type");
		Menu edit = new Menu("Edit");
		Menu search = new Menu("Search");
		Menu traversals = new Menu("Traversals");
		Menu Tree = new Menu("Tree");
		menubar.getMenus().addAll(edit, search, traversals, inputType, Tree);
		
		//declare menuItems and add to submenu - option values
		MenuItem treesize = new MenuItem("Size of Tree");
		MenuItem reset = new MenuItem("Reset");
		MenuItem preorder = new MenuItem("Preorder");
		MenuItem inorder = new MenuItem("Inorder");
		MenuItem postorder = new MenuItem("Postorder");
		MenuItem lorder = new MenuItem("Level-order");
		MenuItem create = new MenuItem("Create Random");
		MenuItem insert = new MenuItem("Insert");
		MenuItem remove = new MenuItem("Remove");
		MenuItem exit = new MenuItem("Exit");
		MenuItem lookup = new MenuItem("Look Up Node");
		MenuItem successor = new MenuItem("Successor");
		MenuItem predecessor = new MenuItem("Predecessor");
		MenuItem max = new MenuItem("Node with maximum value");
		MenuItem min = new MenuItem("Node with minimum value");
		RadioMenuItem Int = RadioMenuItemBuilder.create().toggleGroup(tGroup).text("Integer").selected(true).build();
		RadioMenuItem Dbl = RadioMenuItemBuilder.create().toggleGroup(tGroup).text("Double").build();
		RadioMenuItem Str = RadioMenuItemBuilder.create().toggleGroup(tGroup).text("String").build();
		
		ToggleGroup treetoggle = new ToggleGroup();
		BT = new RadioMenuItem("Binary Tree");
		BT.setToggleGroup(treetoggle);
		BT.setSelected(true);
		AVL = new RadioMenuItem("AVL Tree");
		AVL.setToggleGroup(treetoggle);
		
		inputType.getItems().addAll(Int, Dbl, Str);
		edit.getItems().addAll(create, insert, remove, new SeparatorMenuItem(), treesize, reset, new SeparatorMenuItem(), exit);
		search.getItems().addAll(lookup, new SeparatorMenuItem(), successor, predecessor, new SeparatorMenuItem(), max, min);
		traversals.getItems().addAll(preorder, inorder, postorder, lorder);
		Tree.getItems().addAll(BT, AVL);
		
		
		// subborder =============================================
		subborder = new BorderPane();
		// subborder =============================================
		
		// uphbox================================================
		uphbox = new HBox(20);
		uphbox.setAlignment(Pos.CENTER);
		
		// uphbox================================================
		// progress label ====================================================================
		Label prolabel = new Label("Animation Progress :");
		//====================================================================================
		
		// progress label ====================================================================
		 pb = new ProgressBar();
		 pb.setPrefWidth(200f);
		//====================================================================================
		
		// slider ui ============================================================================
		slider = new Slider(50, 200, 100);
		slider.setMajorTickUnit(50f);
		slider.setBlockIncrement(50f);
		slider.setShowTickLabels(true);
		
		//=======================================================================================
		
		// slider label ==========================================================================
		splabel = new Label("Animation Speed :");
		
		
		// slider label ==========================================================================
		// animation control button 
		Button backToStart = new Button("<<");
		Button backPrevious = new Button("<");
		Button play = new Button("play");
		Button stop = new Button("stop");
		Button goNext = new Button(">");
		Button goLast = new Button(">>");
		
		backToStart.setStyle("-fx-background-radius: 50%;"+
				"-fx-min-width : 50px;"+
				"-fx-min-height : 50px;");
		
		backPrevious.setStyle("-fx-background-radius: 50%;"+
				"-fx-min-width : 50px;"+
				"-fx-min-height : 50px;");

		play.setStyle("-fx-background-radius: 50%;"+
				"-fx-min-width : 50px;"+
				"-fx-min-height : 50px;");

		stop.setStyle("-fx-background-radius: 50%;"+
				"-fx-min-width : 50px;"+
				"-fx-min-height : 50px;");

		goNext.setStyle("-fx-background-radius: 50%;"+
				"-fx-min-width : 50px;"+
				"-fx-min-height : 50px;");

		goLast.setStyle("-fx-background-radius: 50%;"+
				"-fx-min-width : 50px;"+
				"-fx-min-height : 50px;");

		Label anicon = new Label("Animation Control :");
		
		backToStart.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				
				if (BT.isSelected()) {
					if (RandomCheck == 0) {
						BinaryTree.MainTimeline.jumpTo(Duration.millis(0.0f));
						pb.setProgress(Duration.millis(0.0f).toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
					}

				}else if (AVL.isSelected()) {
					if (RandomCheck == 0) {
						AVLTree.MainTimeline.jumpTo(Duration.millis(0.0f));
						pb.setProgress(Duration.millis(0.0f).toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
					}
				}
			}
		});
		
		backPrevious.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				
				if (BT.isSelected()) {
					if (RandomCheck == 0) {
						Duration curDur = BinaryTree.MainTimeline.getCurrentTime();
						Duration prevDur = curDur.subtract(Duration.millis(BinaryTree.incrementDuration));
						pb.setProgress(prevDur.toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
						BinaryTree.MainTimeline.jumpTo(prevDur);
					}
				}else if (AVL.isSelected()) {
					if (RandomCheck ==0) {
						Duration curDur = AVLTree.MainTimeline.getCurrentTime();
						Duration prevDur = curDur.subtract(Duration.millis(AVLTree.incrementDuration));
						pb.setProgress(prevDur.toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
						AVLTree.MainTimeline.jumpTo(prevDur);
					}
				}	
			}
		});
		
		play.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				
				if (BT.isSelected()) {
					if (RandomCheck == 0) {
						BinaryTree.MainTimeline.play();
					}

				}else if (AVL.isSelected()) {
					if (RandomCheck == 0) {
						AVLTree.MainTimeline.play();
					}
				}
			}
		});
		
		stop.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				if (BT.isSelected()) {
					if (RandomCheck ==0) {
						BinaryTree.MainTimeline.pause();
					}
				}else if (AVL.isSelected()) {
					if (RandomCheck == 0) {
						AVLTree.MainTimeline.pause();
					}
				}	
			}
		});
		
		goNext.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				
				if (BT.isSelected()) {
					if (RandomCheck == 0) {
						Duration curDur = BinaryTree.MainTimeline.getCurrentTime();
						Duration nextDur = curDur.add(Duration.millis(BinaryTree.incrementDuration));
						pb.setProgress(nextDur.toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
						BinaryTree.MainTimeline.jumpTo(nextDur);
					}

				}else if (AVL.isSelected()) {
					if (RandomCheck == 0) {
						Duration curDur = AVLTree.MainTimeline.getCurrentTime();
						Duration nextDur = curDur.add(Duration.millis(AVLTree.incrementDuration));
						pb.setProgress(nextDur.toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
						AVLTree.MainTimeline.jumpTo(nextDur);
					}
				}
			}
		});
		
		goLast.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				
				if (BT.isSelected()) {
					if (RandomCheck == 0) {
						BinaryTree.MainTimeline.jumpTo(BinaryTree.MainTimeline.getCycleDuration());
						pb.setProgress(BinaryTree.MainTimeline.getCycleDuration().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
					}
				}else if (AVL.isSelected()) {
					if (RandomCheck == 0) {
						AVLTree.MainTimeline.jumpTo(AVLTree.MainTimeline.getCycleDuration());
						pb.setProgress(AVLTree.MainTimeline.getCycleDuration().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
					}
				}	
			}
		});
		

		//set scene and stage - must have
		VboxTop.getChildren().addAll(menubar);
		hboxBot.getChildren().addAll(inslabel,instxt,ta);
		uphbox.getChildren().addAll(splabel, slider, anicon ,backToStart, backPrevious, play, stop, goNext, goLast, prolabel, pb);
				
		
		//set KeyCombination for the menus
		insert.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
		remove.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN));
		create.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));
		
		//event for Radio Data Type
		Int.setOnAction(e -> {
			type = "Integer";
			p.getChildren().removeAll(p.getChildren());
			ta.clear();
			ta.setText("change data type to Integer successfully.");
		});
		
		Dbl.setOnAction(e -> {
			type = "Double";
			p.getChildren().removeAll(p.getChildren());
			ta.clear();
			ta.setText("change data type to Double successfully.");
		});
		
		Str.setOnAction(e -> {
			type = "String";
			p.getChildren().removeAll(p.getChildren());
			ta.clear();
			ta.setText("change data type to String successfully.");
		});
		
		BT.setOnAction(e -> {
			p.getChildren().removeAll(p.getChildren());
			ta.clear();
			ta.setText("change to Binary Tree successfully.");
		});
		
		AVL.setOnAction(e -> {
			p.getChildren().removeAll(p.getChildren());
			ta.clear();
			ta.setText("change to AVL Tree successfully.");
		});
		
		//reset tree
		reset.setOnAction(e -> {
			p.getChildren().removeAll(p.getChildren());
			
			if (BT.isSelected()) {
				if(type.equals("Integer")) {
					intBT = new BinaryTree<Integer>();
					ta.setText("reset tree successfully");
				}else if(type.equals("Double")) {
					doubleBT = new BinaryTree<Double>();
					ta.setText("reset tree successfully");
				}else {
					stringBT = new BinaryTree<String>();
					ta.setText("reset tree successfully");
				}
			}else if (AVL.isSelected()){
				if(type.equals("Integer")) {
					intTree = new AVLTree<Integer>();
					ta.setText("reset tree successfully");
				}else if(type.equals("Double")) {
					dblTree = new AVLTree<Double>();
					ta.setText("reset tree successfully");
				}else {
					strTree= new AVLTree<String>();
					ta.setText("reset tree successfully");
				}
			}

		});
		
		
		//event for Traversals
		preorder.setOnAction(e -> {
			// remove node after animation========
			Animation1.RemoveNodeLineAfterAnimation();
			// remove node after animation========
			String result = "";
			ta.clear();
			ta.setText("Preorder Traversals: ");
			if (BT.isSelected()) {
				if(type.equals("Integer")) {
					intBT.preorder();
				}else if(type.equals("Double")) {
					doubleBT.preorder();
				}else {
					stringBT.preorder();
				}
			}else if (AVL.isSelected()){
				if(type.equals("Integer")) {
					intTree.preorder();
				}else if(type.equals("Double")) {
					dblTree.preorder();
				}else {
					strTree.preorder();
				}
			}
			
			result = ta.getText();
			ta.setText(result.substring(0, result.length() - 4));
		});
		
		inorder.setOnAction( e -> {
			// remove node after animation========
			Animation1.RemoveNodeLineAfterAnimation();
			// remove node after animation========
			String result = "";
			ta.clear();
			ta.setText("Inorder Traversals: ");
			
			if (BT.isSelected()) {
				if(type.equals("Integer")) {
					intBT.inorder();
				}else if(type.equals("Double")) {
					doubleBT.inorder();
				}else {
					stringBT.inorder();
				}
			}else if (AVL.isSelected()){
				if(type.equals("Integer")) {
					intTree.inorder();
				}else if(type.equals("Double")) {
					dblTree.inorder();
				}else {
					strTree.inorder();
				}
			}
			
			result = ta.getText();
			ta.setText(result.substring(0, result.length() - 4));
		});
		
		postorder.setOnAction(e -> {
			
			// remove node after animation========
			Animation1.RemoveNodeLineAfterAnimation();
			// remove node after animation========

			
			String result = "";
			ta.clear();
			ta.setText("Postorder Traversals: ");
			if (BT.isSelected()) {
				if(type.equals("Integer")) {
					intBT.postorder();
				}else if(type.equals("Double")) {
					doubleBT.postorder();
				}else {
					stringBT.postorder();
				}
			}else if (AVL.isSelected()){
				if(type.equals("Integer")) {
					intTree.postorder();
				}else if(type.equals("Double")) {
					dblTree.postorder();
				}else {
					strTree.postorder();
				}
			}
			result = ta.getText();
			ta.setText(result.substring(0, result.length() - 4));
		});
		
		lorder.setOnAction(e -> {
			
			// remove node after animation========
			Animation1.RemoveNodeLineAfterAnimation();
			// remove node after animation========

			
			ta.clear();
			ta.setText("Level-order Traversals: ");
			if (BT.isSelected()) {
				if(type.equals("Integer")) {
					intBT.levelorder();
				}else if(type.equals("Double")) {
					doubleBT.levelorder();
				}else {
					stringBT.levelorder();
				}
			}else if (AVL.isSelected()){
				if(type.equals("Integer")) {
					intTree.levelorder();
				}else if(type.equals("Double")) {
					dblTree.levelorder();
				}else {
					strTree.levelorder();
				}
			}
		});
		
		//Exit event
		exit.setOnAction(e -> Platform.exit());
		
		//Event to get the size of the Tree, how many nodes are they
		treesize.setOnAction(e -> {
			
			// remove node after animation========
			Animation1.RemoveNodeLineAfterAnimation();
			// remove node after animation========
			
			ta.clear();
			if (BT.isSelected()) {
				if(type.equals("Integer")) {
					ta.setText("The tree has " + intBT.size() + " node(s)");
				}else if(type.equals("Double")) {
					ta.setText("The tree has " + doubleBT.size() + " node(s)");
				}else {
					ta.setText("The tree has " + stringBT.size() + " node(s)");
				}
			}else if (AVL.isSelected()) {
				if(type.equals("Integer")) {
					ta.setText("The tree has " + intTree.size() + " node(s)");
				}else if(type.equals("Double")) {
					ta.setText("The tree has " + dblTree.size() + " node(s)");
				}else {
					ta.setText("The tree has " + strTree.size() + " node(s)");
				}
			}
		});
		
		//Event for Successor
		successor.setOnAction(e -> {
			
			// remove node after animation========
			Animation1.RemoveNodeLineAfterAnimation();
			// remove node after animation========

			
			String value = instxt.getText();
			ta.clear();
			
			if (!value.isEmpty()) {
				if (BT.isSelected()) {
					if(type.equals("Integer")) {
						ta.setText("The successor of " + value + " is " + intBT.successor(Integer.parseInt(value)));
					}else if(type.equals("Double")) {
						ta.setText("The successor of " + value + " is " + doubleBT.successor(Double.parseDouble(value)));
					}else {
						ta.setText("The successor of " + value + " is " + stringBT.successor(value));
					}
				}else if (AVL.isSelected()) {
					if(type.equals("Integer")) {
						ta.setText("The successor of " + value + " is " + intTree.successor(Integer.parseInt(value)));
					}else if(type.equals("Double")) {
						ta.setText("The successor of " + value + " is " + dblTree.successor(Double.parseDouble(value)));
					}else {
						ta.setText("The successor of " + value + " is " + strTree.successor(value));
					}
				}
			}else {
				ta.clear();
				ta.setText("input value of node in 'Enter Your Data Here'.");
			}
			
		});
		
		//Event for Predecessor
		predecessor.setOnAction(e -> {
			
			// remove node after animation========
			Animation1.RemoveNodeLineAfterAnimation();
			// remove node after animation========			
			
			String value = instxt.getText();
			ta.clear();
			if (!value.isEmpty()) {
				if (BT.isSelected()) {
					if(type.equals("Integer")) {
						ta.setText("The predecessor of " + value + " is " + intBT.predecessor(Integer.parseInt(value)));
					}else if(type.equals("Double")) {
						ta.setText("The predecessor of " + value + " is " + doubleBT.predecessor(Double.parseDouble(value)));
					}else {
						ta.setText("The predecessor of " + value + " is " + stringBT.predecessor(value));
					}
				}else if (AVL.isSelected()) {
					if(type.equals("Integer")) {
						ta.setText("The predecessor of " + value + " is " + intTree.predecessor(Integer.parseInt(value)));
					}else if(type.equals("Double")) {
						ta.setText("The predecessor of " + value + " is " + dblTree.predecessor(Double.parseDouble(value)));
					}else {
						ta.setText("The predecessor of " + value + " is " + strTree.predecessor(value));
					}
				}
			}else {
				ta.clear();
				ta.setText("input value of node in 'Enter Your Data Here'.");
			}
			
		});
		
		//Event for Max Element
		max.setOnAction(e -> {
			
			// remove node after animation========
			Animation1.RemoveNodeLineAfterAnimation();
			// remove node after animation========

			
			ta.clear();
			
			if (BT.isSelected()) {
				if(type.equals("Integer")) {
					ta.setText("The node with maximum value is " + intBT.MaxElement());
				}else if(type.equals("Double")) {
					ta.setText("The node with maximum value is " + doubleBT.MaxElement());
				}else {
					ta.setText("The node with maximum value is " + stringBT.MaxElement());
				}

			}else if (AVL.isSelected()) {
				if(type.equals("Integer")) {
					ta.setText("The node with maximum value is " + intTree.MaxElement());
				}else if(type.equals("Double")) {
					ta.setText("The node with maximum value is " + dblTree.MaxElement());
				}else {
					ta.setText("The node with maximum value is " + strTree.MaxElement());
				}
			}
			
		});
		
		//Event for Min Element
		min.setOnAction(e -> {
			
			// remove node after animation========
			Animation1.RemoveNodeLineAfterAnimation();
			// remove node after animation========

			
			ta.clear();
			if (BT.isSelected()) {
				if(type.equals("Integer")) {
					ta.setText("The node with minimum value is " + intBT.MinElement());
				}else if(type.equals("Double")) {
					ta.setText("The node with minimum value is " + doubleBT.MinElement());
				}else {
					ta.setText("The node with minimum value is " + stringBT.MinElement());
				}

			}else if (AVL.isSelected()) {
				if(type.equals("Integer")) {
					ta.setText("The node with minimum value is " + intTree.MinElement());
				}else if(type.equals("Double")) {
					ta.setText("The node with minimum value is " + dblTree.MinElement());
				}else {
					ta.setText("The node with minimum value is " + strTree.MinElement());
				}
			}
			
		});
		
		//event for Insert Node to the Tree
		insert.setOnAction(e -> {
			
			// remove node after animation========
			Animation1.RemoveNodeLineAfterAnimation();
			// remove node after animation========

			
			// cancel random ================
			RandomCheck=0;
			// cancel random ================
			String value = instxt.getText();
			
			if (!value.isEmpty()) {
				if (BT.isSelected()) {
					if(type.equals("Integer")) {
						if (!DupliCheck(String.valueOf(value))) {				
							intBT.add(Integer.parseInt(value));
							ta.clear();
							ta.setText("inserted "+ value + " successfully");
						}
						else 
							ta.setText("Duplication");
						instxt.setText("");
					}else if(type.equals("Double")) {
						if (!DupliCheck(String.valueOf(value))) {				
							doubleBT.add(Double.parseDouble(value));
							ta.clear();
							ta.setText("inserted "+ value + " successfully");
						}
						else 
							ta.setText("Duplication");
						instxt.setText("");
					}else {
						if (!DupliCheck(value)) {				
							stringBT.add(value);
							ta.clear();
							ta.setText("inserted "+ value + "successfully");
						}
						else 
							ta.setText("Duplication");
						instxt.setText("");
					}
				}else if (AVL.isSelected()) {
					if(type.equals("Integer")) {
						if (!DupliCheck(String.valueOf(value))) {				
							intTree.add(Integer.parseInt(value));
							ta.clear();
							ta.setText("inserted "+ value + " successfully");
						}
						else 
							ta.setText("Duplication");
						instxt.setText("");
					}else if(type.equals("Double")) {
						if (!DupliCheck(String.valueOf(value))) {				
							dblTree.add(Double.parseDouble(value));
							ta.clear();
							ta.setText("inserted "+ value + " successfully");
						}
						else 
							ta.setText("Duplication");
						instxt.setText("");
					}else {
						if (!DupliCheck(value)) {				
							strTree.add(value);
							ta.clear();
							ta.setText("inserted "+ value + " successfully");
						}
						else 
							ta.setText("Duplication");
						instxt.setText("");
					}
				}
			}			
		});
		
		//event for Create Random Tree
		create.setOnAction(e -> {
			
			// remove node after animation========
			Animation1.RemoveNodeLineAfterAnimation();
			// remove node after animation========
			
			// enable random===============
			RandomCheck = 1;
			// enable random===============
			Random r = new Random();
			int nNodes = r.nextInt(7) + 1;
			String value = "";
			DecimalFormat df = new DecimalFormat("#.##");      
			if (BT.isSelected()) {
				if(type.equals("Integer")) {
					p.getChildren().removeAll(p.getChildren());
					intBT = new BinaryTree<Integer>();
					ArrayList<Integer> ranNum = new ArrayList<Integer>();
					
					int intele;
					for(int c = 0; c < nNodes; c++) {
						intele = r.nextInt(100);
						value = String.valueOf(intele);
						
						if (!intBT.search(intele)) {
							intBT.add(Integer.parseInt(value));	
							ranNum.add(intele);
						}
					}
					
					for (int i = 0;i<ranNum.size();i++) {
						p.getChildren().add(Animation1.genNodeForRandom(String.valueOf(ranNum.get(i)), intBT.getNode(ranNum.get(i)).xpos,intBT.getNode(ranNum.get(i)).ypos));
					}
					
					intBT.DrawLineForRandom();
					
				}else if(type.equals("Double")) {
					p.getChildren().removeAll(p.getChildren());
					doubleBT = new BinaryTree<Double>();
					ArrayList<Double> ranNum = new ArrayList<Double>();
					double dblele;
					for(int b = 0; b < nNodes; b++) {
						dblele = 0 + r.nextDouble() * 1;
						dblele = Double.valueOf(df.format(dblele));
						value = String.valueOf(dblele);
						
						if (!doubleBT.search(dblele)) {
							doubleBT.add(dblele);
							ranNum.add(dblele);
						}

					}
					
					for (int i = 0;i<ranNum.size();i++) {
						p.getChildren().add(Animation1.genNodeForRandom(String.valueOf(ranNum.get(i)), doubleBT.getNode(ranNum.get(i)).xpos, doubleBT.getNode(ranNum.get(i)).ypos));
					}
					
					doubleBT.DrawLineForRandom();
					
				}else {
					p.getChildren().removeAll(p.getChildren());
					stringBT = new BinaryTree<String>();
					ArrayList<String> ranNum = new ArrayList<String>();
					char ch = (char) (r.nextInt(26) + 65);
					value = String.valueOf(ch);			
					for(int b = 0; b < nNodes; b++) {
						ch = (char) (r.nextInt(26) + 65);
						value = String.valueOf(ch);
						
						if (!stringBT.search(value)) {
							stringBT.add(value);
							ranNum.add(value);
						}
					}
					
					for (int i = 0;i<ranNum.size();i++) {
						p.getChildren().add(Animation1.genNodeForRandom(String.valueOf(ranNum.get(i)), stringBT.getNode(ranNum.get(i)).xpos, stringBT.getNode(ranNum.get(i)).ypos));
					}
					
					stringBT.DrawLineForRandom();
				}

			}else if (AVL.isSelected()) {
				if(type.equals("Integer")) {
					p.getChildren().removeAll(p.getChildren());
					intTree = new AVLTree<Integer>();
					ArrayList<Integer> ranNum = new ArrayList<Integer>();
					int intele;
					for(int c = 0; c < nNodes; c++) {
						intele = r.nextInt(100);
						value = String.valueOf(intele);
						if (!intTree.search(intele)) {
							intTree.add(Integer.parseInt(value));
							ranNum.add(intele);
						}
					}

					for (int i = 0;i<ranNum.size();i++) {
						p.getChildren().add(Animation1.genNodeForRandom(String.valueOf(ranNum.get(i)), intTree.getNode(ranNum.get(i)).xpos,intTree.getNode(ranNum.get(i)).ypos));
					}
					
					intTree.DrawLineForRandom();
					
				}else if(type.equals("Double")) {
					p.getChildren().removeAll(p.getChildren());
					dblTree = new AVLTree<Double>();
					ArrayList<Double> ranNum = new ArrayList<Double>();
					double dblele;
					for(int b = 0; b < nNodes; b++) {
						dblele = 0 + r.nextDouble() * 1;
						dblele = Double.valueOf(df.format(dblele));
						value = String.valueOf(dblele);
						
						if (!dblTree.search(dblele)) {
							dblTree.add(dblele);
							ranNum.add(dblele);
						}
					}
					
					for (int i = 0;i<ranNum.size();i++) {
						p.getChildren().add(Animation1.genNodeForRandom(String.valueOf(ranNum.get(i)), dblTree.getNode(ranNum.get(i)).xpos, dblTree.getNode(ranNum.get(i)).ypos));
					}
					
					dblTree.DrawLineForRandom();
					
				}else {
					p.getChildren().removeAll(p.getChildren());
					strTree = new AVLTree<String>();
					ArrayList<String> ranNum = new ArrayList<String>();
					char ch = (char) (r.nextInt(26) + 65);
					value = String.valueOf(ch);			
					for(int b = 0; b < nNodes; b++) {
						ch = (char) (r.nextInt(26) + 65);
						value = String.valueOf(ch);
						
						if (!strTree.search(value)){
							strTree.add(value);
							ranNum.add(value);
						}						
					}
					
					for (int i = 0;i<ranNum.size();i++) {
						p.getChildren().add(Animation1.genNodeForRandom(String.valueOf(ranNum.get(i)), strTree.getNode(ranNum.get(i)).xpos, strTree.getNode(ranNum.get(i)).ypos));
					}
					
					strTree.DrawLineForRandom();
				}

			}
		});
		
		//Event for removing the node in the Tree
		remove.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				
				// remove node after animation========
				Animation1.RemoveNodeLineAfterAnimation();
				// remove node after animation========

				
				// cancel random ================
				RandomCheck=0;
				// cancel random ================
				
				if (BT.isSelected()) {
					if(type.equals("Integer")) {
						String value = instxt.getText();
						Integer intvalue = Integer.parseInt(value);
	
						intBT.Rm(intvalue);	
						instxt.setText("");
					}else if(type.equals("Double")) {
						String value = instxt.getText();
						double intvalue = Double.parseDouble(value);
						doubleBT.Rm(intvalue);
						instxt.setText("");
					}else {
						String value = instxt.getText();
						stringBT.Rm(value);
						instxt.setText("");
					}
				}else if (AVL.isSelected()) {
					if(type.equals("Integer")) {
						String value = instxt.getText();
						Integer intvalue = Integer.parseInt(value);
						intTree.Rm(intvalue);
						instxt.setText("");
					}else if(type.equals("Double")) {
						String value = instxt.getText();
						double intvalue = Double.parseDouble(value);
						dblTree.Rm(intvalue);
						instxt.setText("");
					}else {
						String value = instxt.getText();
						strTree.Rm(value);
						instxt.setText("");
					}
				}
			}
		});
		
	}
	
	public static void SliderValue() {
		
		BinaryTree.startMillisec = slider.getValue();
		AVLTree.startMillisec = slider.getValue();
		
		BinaryTree.incrementDuration = slider.getValue();
		AVLTree.incrementDuration = slider.getValue();
	}
	
	public boolean DupliCheck(String value) {
		if (BT.isSelected()) {
			if(type.equals("Integer")) {
				if (!intBT.isEmpty()) {
					return intBT.search(Integer.parseInt(value));
				}				
			}else if(type.equals("Double")) {
				if (!doubleBT.isEmpty()) {
					return doubleBT.search(Double.parseDouble(value));
				}			
			}else {
				if (!stringBT.isEmpty()) {
					return stringBT.search(value);
				}			
			}

		}else if (AVL.isSelected()) {
			if(type.equals("Integer")) {
				if (!intTree.isEmpty()) {
					return intTree.search(Integer.parseInt(value));
				}				
			}else if(type.equals("Double")) {
				if (!dblTree.isEmpty()) {
					return dblTree.search(Double.parseDouble(value));
				}			
			}else {
				if (!strTree.isEmpty()) {
					return strTree.search(value);
				}			
			}

		}
		
		return false;
	}
	
}
