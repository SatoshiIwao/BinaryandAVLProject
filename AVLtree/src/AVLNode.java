

public class AVLNode<T> {
	
	
	public T element;
	public AVLNode<T> left;
	public AVLNode<T> right;
	public int height;
	public double xpos;
	public double ypos;
	
	double startX = 0f;
	double startY = 0f;
	
	
	public AVLNode(T e) {
		
		this.element = e;
		this.left = null;
		this.right = null;
		this.height = 0;
		this.xpos = startX;
		this.ypos = startY;
	}
	
	public AVLNode(T e, double x, double y) {
		
		this.element = e;
		this.left = null;
		this.right = null;
		this.height = 0;
		this.xpos =  x;
		this.ypos = y;
	}
}
