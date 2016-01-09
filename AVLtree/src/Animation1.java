


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.transform.*;

public class Animation1 {

	Label l;
	static Group p;
	Scene sc;
	Button start;
	Button stop;
	Button first;
	Button prev;
	static ProgressBar pb;
	
	double X = 800f/2f;
	double Y = 500f/5f;
	public static double RightX = 30f;
	public static double RightY = 30f;
	public static double LeftY = 30f;
	
	public static StackPane removalNode= null;
	public static Line removalLine= null;
	
	static SequentialTransition sq;
	static Timeline mytime;
	
	StackPane node50;
	StackPane node60;
	Line line60;
	StackPane node65;
	Line line65;
	Circle ball;
	
	
	public static double speedmillis = 200.00f;
	
	// project gui stuff======================================================================================================
	
	public static Color ChooseRandomColor() {
		
		Random rand = new Random();
		
		double r = rand.nextDouble();
		double g = rand.nextDouble();
		double b = rand.nextDouble();
		 
		Color col = new Color(r, g, b, 1.0f);
		
		return col;
		
	}

	public static StackPane genNode(String e, double x, double y) {
		Circle node = new Circle(15f,  Color.rgb(0,0,0));		        
		node.setEffect(new Lighting());
		node.setScaleX(0.0f);
		node.setScaleY(0.0f);
		node.setFill(ChooseRandomColor());
		//node.setOpacity(0.0f);
        //create a text inside a circle
	    Text text = new Text(e);
	        text.setStroke(Color.WHITE);
	        text.setScaleX(0.0f);
	        text.setScaleY(0.0f);
	       // text.setOpacity(0.0f);
	        //create a layout for circle with text inside
	    StackPane stack = new StackPane();
	    stack.setLayoutX(x);
	    stack.setLayoutY(y);
	    stack.getChildren().addAll(node,text);
	    
	    if (e.contains(".")) {
	    	e = e.replace(".", "dot");
	    }
	    
	    stack.setId("node"+e);
	    
	    return stack;
	}
	
	public static Line genRightLine(double x, double y, String e) {
		Line line = new Line(x+RightX, y+RightY, x+RightX, y+RightY);
		line.setStrokeWidth(2.0f);
		
		 if (e.contains(".")) {
		    	e = e.replace(".", "dot");
		    }
		 
		line.setId("line"+e);
		line.setStrokeLineCap(StrokeLineCap.ROUND);
		line.setStroke(Color.web("green", 0.5f));
		return line;
	}
		
	public static Line genLeftLine(double x, double y, String e) {
		Line line = new Line(x, y+LeftY, x, y+LeftY);
		line.setStrokeWidth(2.0f);
		
		if (e.contains(".")) {
	    	e = e.replace(".", "dot");
	    }
		
		line.setId("line"+e);
		line.setStrokeLineCap(StrokeLineCap.ROUND);
		line.setStroke(Color.web("green", 0.5f));
		return line;
	}
	
	public static Line genRightLineForRandom(double x, double y, String e) {
		Line line = new Line(x+RightX, y+RightY, x+RightX, y+RightY);
		line.setStrokeWidth(2.0f);
		
		 if (e.contains(".")) {
		    	e = e.replace(".", "dot");
		    }
		 
		line.setId("line"+e);
		line.setStrokeLineCap(StrokeLineCap.ROUND);
		line.setStroke(Color.web("green", 0.5f));
		return line;
	}

	
	public static Line genLeftLineForRandom(double x, double y, String e) {
		Line line = new Line(x, y+LeftY, x, y+LeftY);
		line.setStrokeWidth(2.0f);
		
		if (e.contains(".")) {
	    	e = e.replace(".", "dot");
	    }
		
		line.setId("line"+e);
		line.setStrokeLineCap(StrokeLineCap.ROUND);
		line.setStroke(Color.web("green", 0.5f));
		return line;
	}
	
	public static Line genLineForRandom(String e, double sx, double sy, double ex, double ey){
		Line line = new Line(sx, sy, ex, ey);
		line.setStrokeWidth(2.0f);
		
		if (e.contains(".")) {
	    	e = e.replace(".", "dot");
	    }
		
		line.setId("line"+e);
		line.setStrokeLineCap(StrokeLineCap.ROUND);
		line.setStroke(Color.web("green", 0.5f));
		return line;
	}
	
	public static StackPane genNodeForRandom(String e, double x, double y) {
		Circle node = new Circle(15f,  Color.rgb(0,0,0));		        
		node.setEffect(new Lighting());
		node.setFill(ChooseRandomColor());
		
        //create a text inside a circle
	    Text text = new Text(e);
	    text.setStroke(Color.WHITE);
	        //create a layout for circle with text inside
	    StackPane stack = new StackPane();
	    stack.setLayoutX(x);
	    stack.setLayoutY(y);
	    stack.getChildren().addAll(node,text);
	    
	    if (e.contains(".")) {
	    	e = e.replace(".", "dot");
	    }
	    
	    stack.setId("node"+e);
	    
	    return stack;
	}
	
	public static Circle genBall() {
		Circle ball = new Circle(10f, Color.rgb(0,0,0));
		ball.setStrokeType(StrokeType.OUTSIDE);
		ball.setStroke(Color.web("black", 0.16));
		ball.setStrokeWidth(3.0);
		ball.setEffect(new BoxBlur(10,10,3));
		ball.setId("ball");
		ball.setScaleX(0.0f);
		ball.setScaleY(0.0f);
	
		
		return ball;
	}
	// component of animation frame==============================================================================
	// scale node==================================================================================
	
	public static ArrayList<KeyFrame> ScaleNodeFrame(StackPane node, int cue) {
		
		//KeyFrame[] portionOfFrame = new KeyFrame[3];
		
		ArrayList<KeyFrame> portionOfFrame = new ArrayList<KeyFrame>();
		
		Circle c = (Circle) node.getChildren().get(0);
		Text t = (Text) node.getChildren().get(1);

		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec), ""+cue, 
				new EventHandler<ActionEvent>(){@Override
				public void handle(ActionEvent event) {
					if (AVLMain.BT.isSelected()) {
						AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
					}else if (AVLMain.AVL.isSelected()) {
						AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
					}
				}},
				new KeyValue(c.scaleXProperty(), c.getScaleX()), 
				new KeyValue(c.scaleYProperty(), c.getScaleY()), 
				new KeyValue(t.scaleXProperty(), t.getScaleX()), 
				new KeyValue(t.scaleYProperty(), t.getScaleY())));
				
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec+=AVLTree.incrementDuration),
				new EventHandler<ActionEvent>(){@Override
				public void handle(ActionEvent event) {
					if (AVLMain.BT.isSelected()) {
						AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
					}else if (AVLMain.AVL.isSelected()) {
						AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
					}
				}},
				new KeyValue(c.scaleXProperty(), 2.0f), 
				new KeyValue(c.scaleYProperty(), 2.0f), 
				new KeyValue(t.scaleXProperty(), 2.0f), 
				new KeyValue(t.scaleYProperty(), 2.0f))); 
		
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec+=(2*AVLTree.incrementDuration)),
				new EventHandler<ActionEvent>(){@Override
				public void handle(ActionEvent event) {
					if (AVLMain.BT.isSelected()) {
						AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
					}else if (AVLMain.AVL.isSelected()) {
						AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
					}
				}},
				new KeyValue(c.scaleXProperty(), 1.0f), 
				new KeyValue(c.scaleYProperty(), 1.0f), 
				new KeyValue(t.scaleXProperty(), 1.0f), 
				new KeyValue(t.scaleYProperty(), 1.0f)));
		
		return portionOfFrame;	
	}
	
	
	// insert new node from fadein to scale up===================================================================
	public static ArrayList<KeyFrame> InsertNewNodeFrame(StackPane node, int cue) {

		//KeyFrame[] portionOfFrame = new KeyFrame[3];
		
		ArrayList<KeyFrame> portionOfFrame = new ArrayList<KeyFrame>();
		
		Circle c = (Circle) node.getChildren().get(0);
		Text t = (Text) node.getChildren().get(1);
		
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec), ""+cue,
				new EventHandler<ActionEvent>(){@Override
				public void handle(ActionEvent event) {
					
					if (AVLMain.BT.isSelected()) {
						AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
					}else if (AVLMain.AVL.isSelected()) {
						AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
					}
				}},
				new KeyValue(c.scaleXProperty(), c.getScaleX()), 
				new KeyValue(c.scaleYProperty(), c.getScaleY()), 
				new KeyValue(t.scaleXProperty(), t.getScaleX()), 
				new KeyValue(t.scaleYProperty(), t.getScaleY())));
	
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec+=AVLTree.incrementDuration), 
				new EventHandler<ActionEvent>(){@Override
				public void handle(ActionEvent event) {
					if (AVLMain.BT.isSelected()) {
						AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
					}else if (AVLMain.AVL.isSelected()) {
						AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
					}
				}},
				new KeyValue(c.scaleXProperty(), 2.0f), 
				new KeyValue(c.scaleYProperty(), 2.0f), 
				new KeyValue(t.scaleXProperty(), 2.0f), 
				new KeyValue(t.scaleYProperty(), 2.0f))); 
		
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec+=(2*AVLTree.incrementDuration)), 
				new EventHandler<ActionEvent>(){@Override
				public void handle(ActionEvent event) {
					if (AVLMain.BT.isSelected()) {
						AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
					}else if (AVLMain.AVL.isSelected()) {
						AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
					}
				}},
				new KeyValue(c.scaleXProperty(), 1.0f), 
				new KeyValue(c.scaleYProperty(), 1.0f), 
				new KeyValue(t.scaleXProperty(), 1.0f), 
				new KeyValue(t.scaleYProperty(), 1.0f)));
		
		return portionOfFrame;	
	}
	
	
	// hold up target node during program finding successor or predecessor ==================================
	public static ArrayList<KeyFrame> holdUpTargetNodeFrame(StackPane node, int cue) {
		
		//KeyFrame[] portionOfFrame = new KeyFrame[2];
		
		ArrayList<KeyFrame> portionOfFrame = new ArrayList<KeyFrame>();
		
		Circle c = (Circle) node.getChildren().get(0);
		Text t = (Text) node.getChildren().get(1);
		
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec), ""+cue, 
				new EventHandler<ActionEvent>(){@Override
				public void handle(ActionEvent event) {
					if (AVLMain.BT.isSelected()) {
						AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
					}else if (AVLMain.AVL.isSelected()) {
						AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
					}
				}},
				new KeyValue(c.scaleXProperty(), c.getScaleX()), 
				new KeyValue(c.scaleYProperty(), c.getScaleY()), 
				new KeyValue(t.scaleXProperty(), t.getScaleX()), 
				new KeyValue(t.scaleYProperty(), t.getScaleY())));
		
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec+=AVLTree.incrementDuration), 
				new EventHandler<ActionEvent>(){@Override
				public void handle(ActionEvent event) {
					if (AVLMain.BT.isSelected()) {
						AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
					}else if (AVLMain.AVL.isSelected()) {
						AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
					}	
				}},
				new KeyValue(c.scaleXProperty(), 2.0f), 
				new KeyValue(c.scaleYProperty(), 2.0f), 
				new KeyValue(t.scaleXProperty(), 2.0f), 
				new KeyValue(t.scaleYProperty(), 2.0f)));
		
		return portionOfFrame;
	}

	// hold up target node during program finding successor or predecessor ==================================
	public static ArrayList<KeyFrame> deleteNodeLineFrame(StackPane node, Line line, int cue) {
		
		//KeyFrame[] portionOfFrame = new KeyFrame[4];
		
		ArrayList<KeyFrame> portionOfFrame = new ArrayList<KeyFrame>();
		
		Circle c = (Circle) node.getChildren().get(0);
		Text t = (Text) node.getChildren().get(1);
		
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec), ""+cue, 
				new EventHandler<ActionEvent>(){@Override
				public void handle(ActionEvent event) {
					if (AVLMain.BT.isSelected()) {
						AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
					}else if (AVLMain.AVL.isSelected()) {
						AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
					}
				}},
				new KeyValue(c.scaleXProperty(), c.getScaleX()), 
				new KeyValue(c.scaleYProperty(), c.getScaleY()), 
				new KeyValue(t.scaleXProperty(), t.getScaleX()), 
				new KeyValue(t.scaleYProperty(), t.getScaleY())));
		
		
		if (line!=null) {
			// get original start and end point of line 
			portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec), 
					new EventHandler<ActionEvent>(){@Override
					public void handle(ActionEvent event) {
						if (AVLMain.BT.isSelected()) {
							AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
						}else if (AVLMain.AVL.isSelected()) {
							AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
						}
					}},
					new KeyValue(line.scaleXProperty(), line.getScaleX()), 
					new KeyValue(line.scaleYProperty(), line.getScaleY())));
		}
		
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec+=AVLTree.incrementDuration),
				new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						
						if (AVLMain.BT.isSelected()) {
							AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
						}else if (AVLMain.AVL.isSelected()) {
							AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
						}						
						//AVLMain.p.getChildren().remove(node);
						removalNode = node;
					}
				},
				new KeyValue(c.scaleXProperty(), 0.0f), 
				new KeyValue(c.scaleYProperty(), 0.0f), 
				new KeyValue(t.scaleXProperty(), 0.0f),
				new KeyValue(t.scaleYProperty(), 0.0f)));
		
		if (line!=null) {
			portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec+=AVLTree.incrementDuration), 
					new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							
							if (AVLMain.BT.isSelected()) {
								AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
							}else if (AVLMain.AVL.isSelected()) {
								AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
							}
					
							//AVLMain.p.getChildren().remove(line);	
							removalLine = line;
						}
					},
					new KeyValue(line.scaleXProperty(), 0.0f),
					new KeyValue(line.scaleYProperty(), 0.0f)));
			
		}
		
		return portionOfFrame;
	}
	
	
	// delete only node ======================================================================================
	public static ArrayList<KeyFrame> deleteOnlyNodeFrame(StackPane node, int cue) {
		
		//KeyFrame[] portionOfFrame = new KeyFrame[2];
		
		ArrayList<KeyFrame> portionOfFrame = new ArrayList<KeyFrame>();
		
		Circle c = (Circle) node.getChildren().get(0);
		Text t = (Text) node.getChildren().get(1);
		
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec), ""+cue, 
				new EventHandler<ActionEvent>(){@Override
				public void handle(ActionEvent event) {
					if (AVLMain.BT.isSelected()) {
						AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
					}else if (AVLMain.AVL.isSelected()) {
						AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
					}
				}},
				new KeyValue(c.scaleXProperty(), c.getScaleX()), 
				new KeyValue(c.scaleYProperty(), c.getScaleY()), 
				new KeyValue(t.scaleXProperty(), t.getScaleX()), 
				new KeyValue(t.scaleYProperty(), t.getScaleY())));
		
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec+=AVLTree.incrementDuration),
				new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if (AVLMain.BT.isSelected()) {
					AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
				}else if (AVLMain.AVL.isSelected()) {
					AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
				}
				//AVLMain.p.getChildren().remove(node);
				removalNode = node;
			}
		},
		new KeyValue(c.scaleXProperty(), 0.0f), 
		new KeyValue(c.scaleYProperty(), 0.0f), 
		new KeyValue(t.scaleXProperty(), 0.0f),
		new KeyValue(t.scaleYProperty(), 0.0f)));
		
		return portionOfFrame;
	}
	
	// delete only node ======================================================================================
	public static ArrayList<KeyFrame> moveSopAndDeleteLineFrame(StackPane Sopnode, Line line, 
			double targetx, double targety, 
			StackPane targetNode, int cue) {
		
		//KeyFrame[] portionOfFrame = new KeyFrame[6];
		
		ArrayList<KeyFrame> portionOfFrame = new ArrayList<KeyFrame>();
		
		Circle tarc = (Circle) targetNode.getChildren().get(0);
		Text tart = (Text) targetNode.getChildren().get(1);
		
		// get original position =================================================
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec), ""+cue, 
				new EventHandler<ActionEvent>(){@Override
				public void handle(ActionEvent event) {
					if (AVLMain.BT.isSelected()) {
						AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
					}else if (AVLMain.AVL.isSelected()) {
						AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
					}
				}},
				new KeyValue(tarc.scaleXProperty(), tarc.getScaleX()), 
				new KeyValue(tarc.scaleYProperty(), tarc.getScaleY()), 
				new KeyValue(tart.scaleXProperty(), tart.getScaleX()), 
				new KeyValue(tart.scaleYProperty(), tart.getScaleY())));
		
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec), 
				new KeyValue(Sopnode.layoutXProperty(), Sopnode.getLayoutX()), 
				new KeyValue(Sopnode.layoutYProperty(), Sopnode.getLayoutY())));
		
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec), 
				new KeyValue(line.scaleXProperty(), line.getScaleX()), 
				new KeyValue(line.scaleYProperty(), line.getScaleY())));
		// =========================================================================
		
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec+=AVLTree.incrementDuration),
				new EventHandler<ActionEvent>(){@Override
				public void handle(ActionEvent event) {
					if (AVLMain.BT.isSelected()) {
						AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
					}else if (AVLMain.AVL.isSelected()) {
						AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
					}
				}},
				new KeyValue(Sopnode.layoutXProperty(), targetx), 
				new KeyValue(Sopnode.layoutYProperty(), targety))); 
		
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec+=AVLTree.incrementDuration),
				new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						//AVLMain.p.getChildren().remove(line);
						removalLine = line;
					}
					
				},
				new KeyValue(line.scaleXProperty(), 0.0f), 
				new KeyValue(line.scaleYProperty(), 0.0f))); 
		
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec+=AVLTree.incrementDuration),					
			new KeyValue(tarc.scaleXProperty(), 0.0f), 
			new KeyValue(tarc.scaleYProperty(), 0.0f), 
			new KeyValue(tart.scaleXProperty(), 0.0f),
			new KeyValue(tart.scaleYProperty(), 0.0f)));
		
		return portionOfFrame;
	}
	
	// draw new line and follow ball (fade in ball ==> line and ball move at the same time ==> fadeout ball)
	public static ArrayList<KeyFrame> NewLineTraverseFrame(Line line, Circle ball, double endx, double endy, int cue) {
		
		//KeyFrame[] portionOfFrame = new KeyFrame[9];
		
		ArrayList<KeyFrame> portionOfFrame = new ArrayList<KeyFrame>();
		
				
		// move to ball start point of line with scale 0
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec), ""+cue,
				new EventHandler<ActionEvent>(){@Override
				public void handle(ActionEvent event) {
					if (AVLMain.BT.isSelected()) {
						AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
					}else if (AVLMain.AVL.isSelected()) {
						AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
					}
				}},
				new KeyValue(ball.layoutXProperty(), line.getStartX()), 
				new KeyValue(ball.layoutYProperty(), line.getStartY())));  
		
		// fade in ball at start point 
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec), 
				new KeyValue(ball.scaleXProperty(), ball.getScaleX()), 
				new KeyValue(ball.scaleYProperty(), ball.getScaleY())));
		

		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec+=AVLTree.incrementDuration), 
				new EventHandler<ActionEvent>(){@Override
				public void handle(ActionEvent event) {
					if (AVLMain.BT.isSelected()) {
						AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
					}else if (AVLMain.AVL.isSelected()) {
						AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
					}
				}},
				new KeyValue(ball.scaleXProperty(), 1.0f), 
				new KeyValue(ball.scaleYProperty(), 1.0f)));

		
		// change position =======================================================
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec+=AVLTree.incrementDuration), 
				new KeyValue(line.startXProperty(), line.getStartX()), 
				new KeyValue(line.startYProperty(), line.getStartY()),
				new KeyValue(line.endXProperty(), line.getEndX()), 
				new KeyValue(line.endYProperty(), line.getEndY())));
		
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec+=AVLTree.incrementDuration), 
				new KeyValue(ball.layoutXProperty(),line.getStartX()), 
				new KeyValue(ball.layoutYProperty(), line.getStartY())));

		
		KeyFrame tempFrame = new KeyFrame(Duration.millis(AVLTree.startMillisec+=(2*AVLTree.incrementDuration)), 
				new EventHandler<ActionEvent>(){@Override
					public void handle(ActionEvent event) {
					if (AVLMain.BT.isSelected()) {
						AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
					}else if (AVLMain.AVL.isSelected()) {
						AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
					}
				}},
				new KeyValue(line.startXProperty(), line.getStartX()), 
				new KeyValue(line.startYProperty(), line.getStartY()),
				new KeyValue(line.endXProperty(), endx), 
				new KeyValue(line.endYProperty(), endy)); 
		
		portionOfFrame.add(tempFrame);
				
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec+=(2*AVLTree.incrementDuration)), 
				new KeyValue(ball.layoutXProperty(), endx), 
				new KeyValue(ball.layoutYProperty(), endy)));
		
		// fade out ball ===========================================================

		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec+=(3*AVLTree.incrementDuration)), 
				new EventHandler<ActionEvent>(){@Override
					public void handle(ActionEvent event) {
					if (AVLMain.BT.isSelected()) {
						AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
					}else if (AVLMain.AVL.isSelected()) {
						AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
					}
				}},
				new KeyValue(ball.scaleXProperty(), ball.getScaleX()), 
				new KeyValue(ball.scaleYProperty(), ball.getScaleY())));
		

		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec+=(4*AVLTree.incrementDuration)), 
				new EventHandler<ActionEvent>(){@Override
					public void handle(ActionEvent event) {
					if (AVLMain.BT.isSelected()) {
						AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
					}else if (AVLMain.AVL.isSelected()) {
						AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
					}
				}},
				new KeyValue(ball.scaleXProperty(), 0.0f), 
				new KeyValue(ball.scaleYProperty(), 0.0f)));

		
		return portionOfFrame;
	}
	
	
	// ball movement on line that is already existing (fadein ball ==> move ball ==> fadeout ball)
	public static ArrayList<KeyFrame> ExistingLineTraverseFrame(Line line, Circle ball, int cue) {
		
		//KeyFrame[] portionOfFrame = new KeyFrame[7];
		
		ArrayList<KeyFrame> portionOfFrame = new ArrayList<KeyFrame>();
		
		// move to ball start point of line with scale 0
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec), ""+cue,
				new EventHandler<ActionEvent>(){@Override
			public void handle(ActionEvent event) {
					if (AVLMain.BT.isSelected()) {
						AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
					}else if (AVLMain.AVL.isSelected()) {
						AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
					}
				}},
				new KeyValue(ball.layoutXProperty(), line.getStartX()), 
				new KeyValue(ball.layoutYProperty(), line.getStartY())));
		
		// fade in ball at start point 
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec), 
				new KeyValue(ball.scaleXProperty(), ball.getScaleX()), 
				new KeyValue(ball.scaleYProperty(), ball.getScaleY())));
		

		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec+=(1*AVLTree.incrementDuration)), 
				new EventHandler<ActionEvent>(){@Override
			public void handle(ActionEvent event) {
					if (AVLMain.BT.isSelected()) {
						AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
					}else if (AVLMain.AVL.isSelected()) {
						AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
					}
				}},
				new KeyValue(ball.scaleXProperty(), 1.0f), 
				new KeyValue(ball.scaleYProperty(), 1.0f)));

		// change position =======================================================
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec+=(2*AVLTree.incrementDuration)), 
				new EventHandler<ActionEvent>(){@Override
			public void handle(ActionEvent event) {
					if (AVLMain.BT.isSelected()) {
						AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
					}else if (AVLMain.AVL.isSelected()) {
						AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
					}
				}},
				new KeyValue(ball.layoutXProperty(), line.getStartX()), 
				new KeyValue(ball.layoutYProperty(), line.getStartY())));
		
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec+=(3*AVLTree.incrementDuration)), 
				new EventHandler<ActionEvent>(){@Override
			public void handle(ActionEvent event) {
					if (AVLMain.BT.isSelected()) {
						AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
					}else if (AVLMain.AVL.isSelected()) {
						AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
					}
				}},
				new KeyValue(ball.layoutXProperty(), line.getEndX()), 
				new KeyValue(ball.layoutYProperty(), line.getEndY())));

		// fade out ball ===========================================================

		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec+=(4*AVLTree.incrementDuration)), 
				new EventHandler<ActionEvent>(){@Override
			public void handle(ActionEvent event) {
					if (AVLMain.BT.isSelected()) {
						AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
					}else if (AVLMain.AVL.isSelected()) {
						AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
					}
				}},
				new KeyValue(ball.scaleXProperty(), ball.getScaleX()), 
				new KeyValue(ball.scaleYProperty(), ball.getScaleY())));
		

		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec+=(5*AVLTree.incrementDuration)), 
				new EventHandler<ActionEvent>(){@Override
			public void handle(ActionEvent event) {
					if (AVLMain.BT.isSelected()) {
						AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
					}else if (AVLMain.AVL.isSelected()) {
						AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
					}
				}},
				new KeyValue(ball.scaleXProperty(), 0.0f), 
				new KeyValue(ball.scaleYProperty(), 0.0f)));

		
		return portionOfFrame;
		 
	}
	
	
	public static ArrayList<KeyFrame> moveNodeFrame(StackPane node1, double endxpos1, double endypos1,
			StackPane node2, double endxpos2, double endypos2,
			StackPane node3, double endxpos3, double endypos3,
			Line line4, double xstartpos4, double ystartpos4, double xendpos4, double yendpos4, 
			Line line5, double xstartpos5, double ystartpos5, double xendpos5, double yendpos5, double oldendx, double oldendy, int cue) {
		
		//KeyFrame[] portionOfFrame = new KeyFrame[2];
		
		ArrayList<KeyFrame> portionOfFrame = new ArrayList<KeyFrame>();
		
		// get original point ===============================================================================
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec), ""+cue, 
				new EventHandler<ActionEvent>(){@Override
			public void handle(ActionEvent event) {
					if (AVLMain.BT.isSelected()) {
						AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
					}else if (AVLMain.AVL.isSelected()) {
						AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
					}
				}},
				new KeyValue(node1.layoutXProperty(), node1.getLayoutX()), 
				new KeyValue(node1.layoutYProperty(), node1.getLayoutY()),
				new KeyValue(node2.layoutXProperty(), node2.getLayoutX()), 
				new KeyValue(node2.layoutYProperty(), node2.getLayoutY()),
				new KeyValue(node3.layoutXProperty(), node3.getLayoutX()), 
				new KeyValue(node3.layoutYProperty(), node3.getLayoutY()),
				new KeyValue(line4.startXProperty(), line4.getStartX()), 
				new KeyValue(line4.startYProperty(), line4.getStartY()),
				new KeyValue(line4.endXProperty(), line4.getEndX()), 
				new KeyValue(line4.endYProperty(), line4.getEndY()),
				new KeyValue(line5.startXProperty(), line5.getStartX()), 
				new KeyValue(line5.startYProperty(), line5.getStartY()),
				new KeyValue(line5.endXProperty(), oldendx), 
				new KeyValue(line5.endYProperty(), oldendy)
				));
			
		// get  ===============================================================================
		portionOfFrame.add(new KeyFrame(Duration.millis(AVLTree.startMillisec+=(1*AVLTree.incrementDuration)),  
				new EventHandler<ActionEvent>(){@Override
			public void handle(ActionEvent event) {
					if (AVLMain.BT.isSelected()) {
						AVLMain.pb.setProgress(BinaryTree.MainTimeline.getCurrentTime().toMillis()/BinaryTree.MainTimeline.getCycleDuration().toMillis());
					}else if (AVLMain.AVL.isSelected()) {
						AVLMain.pb.setProgress(AVLTree.MainTimeline.getCurrentTime().toMillis()/AVLTree.MainTimeline.getCycleDuration().toMillis());
					}
				}},
				new KeyValue(node1.layoutXProperty(), endxpos1), 
				new KeyValue(node1.layoutYProperty(), endypos1),
				new KeyValue(node2.layoutXProperty(), endxpos2), 
				new KeyValue(node2.layoutYProperty(), endypos2),
				new KeyValue(node3.layoutXProperty(), endxpos3), 
				new KeyValue(node3.layoutYProperty(), endypos3),
				new KeyValue(line4.startXProperty(), xstartpos4), 
				new KeyValue(line4.startYProperty(), ystartpos4),
				new KeyValue(line4.endXProperty(), xendpos4), 
				new KeyValue(line4.endYProperty(), yendpos4),
				new KeyValue(line5.startXProperty(), xstartpos5), 
				new KeyValue(line5.startYProperty(), ystartpos5),
				new KeyValue(line5.endXProperty(), xendpos5), 
				new KeyValue(line5.endYProperty(), yendpos5)));
	
		return portionOfFrame;
		
	}

	public static void RemoveNodeLineAfterAnimation() {
		if (removalNode!=null) {
			AVLMain.p.getChildren().remove(removalNode);
			removalNode = null;
		}
		
		if (removalLine!=null) {
			AVLMain.p.getChildren().remove(removalLine);
			removalLine = null;
		}

	}
	
	
}
