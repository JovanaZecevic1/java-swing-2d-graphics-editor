package geometry;

import java.awt.Color;
import java.awt.Graphics;


public class Point extends Shape {

	private int x;
	private int y;
	
	
	private static int maxX  = 500;
	public static int getMaxX() {
		return maxX;
	}
	
	

	public Point() {

	}

	
	public Point(int x, int y) {
		setX(x);
		setY(y);
		
	}
	
	
	public Point(int x, int y, boolean selected) {
		this(x, y);
		this.setSelected(selected);
	}
	
	
	public void setX(int xCoord) {
		if(xCoord > 0) {
			this.x = xCoord;
		}else {
			throw new IllegalArgumentException("Must be positive!");
		}
	}
	
	public int getX() {
		return x;
	}
	
	public void setY(int yCoord) {
		if(yCoord > 0) {
			this.y = yCoord;
		}else {
			throw new IllegalArgumentException("Must be positive!");
		}
	}
	
	public int getY() {
		return this.y;
	}
	
	
	public double distance(Point point) {
		//dy dx -> dx*dx + dy*dy -> sqrt
		int dx = this.x - point.x;
		int dy = this.y - point.y;
		double result = Math.sqrt(dx*dx + dy*dy);
		return result;
	}
	
	public double distance(int x, int y) {
		//dy dx -> dx*dx + dy*dy -> sqrt
		int dx = this.x - x;
		int dy = this.y - y;
		double result = Math.sqrt(dx*dx + dy*dy);
		return result;
	}
	
	public boolean contains(int x, int y) {
		return this.distance(new Point(x, y)) <= 2;
	}
	
	public boolean contains(Point click) {
		return this.distance(click) <= 2;
	}
	
	public void draw(Graphics g) {
		g.setColor(getOutlineColor());
		g.drawLine(this.x, this.y - 2, this.x, this.y + 2);
		g.drawLine(this.x - 2, this.y, this.x + 2, this.y);
		if(isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(x-2, y-2, 4, 4);
			g.setColor(Color.black);
		}

	}

	
	@Override //anotacija u Javi, nije obavezna, ali je dobra praksa navoditi je kada se vrsi redefinisanje
	public String toString() {
		return "Point(" + x + "," + y + ","  + getOutlineColor().getRGB() + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Point) {
			//downcast - kastovanje objekta iz objekta natklase u objekat potklase
			Point pom = (Point)obj;
			return (this.x == pom.x && this.y == pom.y);
		}else {
			return false;
		}
	}
	
	
	public static void staticMethod() {
		System.out.println("Staticka metoda");
	}



	@Override
	public void moveBy(int x, int y) {
		this.x += x;
		this.y += y;

	}


	@Override
	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}


	@Override
	public int compareTo(Object o) {
		if(o instanceof Point) {
			double d1 = this.distance(new Point(0,0));
			double d2 = ((Point) o).distance(new Point(0,0));
			return (int)(d1 - d2);
		}
		return 0;

	}
	@Override
	public Point clone(Shape newPoint) {
		Point point = (Point) newPoint;
		this.setX(point.getX());
		this.setY(point.getY());
		this.setOutlineColor(point.getOutlineColor());
		return this;
	}


}