package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D; 
/*nasledjivanje omogucava da se postojece klase koriste za kreiranje novih
nalsedjuju se svi atributi i metode koji nisu deklarisani kao private*/
public class Donut extends Circle {
	private int innerRadius;
	
	public Donut() {
	}
	/*super upucuje poziv roditeljskoj (nadklasi) klasi*/
	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		if(innerRadius>radius || innerRadius<0) {
			throw new IllegalArgumentException("Inner radius must be greater than zero and less than radius!");
		}
		else
			this.innerRadius=innerRadius;
	}
	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		//this.setCenter(center);
		//this.setRadius(radius);
		this.setSelected(selected);
	}
	public boolean contains(int x, int y) {
		double dFromCenter = this.getCenter().distance(x, y);
		return super.contains(x, y) && dFromCenter > this.innerRadius; 
	}
	public boolean contains(Point p) {
		double dFromCenter = this.getCenter().distance(p.getX(), p.getY());
		return super.contains(p.getX(), p.getY()) && dFromCenter > this.innerRadius;
	}
	public int getInnerRadius() {
		return innerRadius;
	}
	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	
	public void draw(Graphics g) {
		/*super.draw(g);
		g.setColor(Color.WHITE);
		g.fillOval(getCenter().getX() - innerRadius, getCenter().getY() - innerRadius, 2 * innerRadius, 2 *innerRadius);
		g.setColor(getOutlineColor());
		g.drawOval(this.getCenter().getX() - this.innerRadius, this.getCenter().getY() - this.innerRadius, 2* this.innerRadius, 2*this.innerRadius);
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(getCenter().getX() - 2, getCenter().getY() - 2, 4, 4);
			g.drawRect(getCenter().getX() - innerRadius - 2, getCenter().getY() - 2, 4, 4);
			g.drawRect(getCenter().getX() + innerRadius - 2, getCenter().getY() - 2, 4, 4);
			g.drawRect(getCenter().getX() - 2, getCenter().getY() - innerRadius - 2, 4, 4);
			g.drawRect(getCenter().getX() - 2, getCenter().getY() + innerRadius - 2, 4, 4);
			g.setColor(Color.black);
		}*/
		
		Graphics2D g2d = (Graphics2D) g;
		
	    // spoljašnji krug
	    Ellipse2D outerCircle = new Ellipse2D.Double(
	        getCenter().getX() - getRadius(),
	        getCenter().getY() - getRadius(),
	        getRadius() * 2,
	        getRadius() * 2
	    );
	    // unutrašnji krug (rupa)
	    Ellipse2D innerCircle = new Ellipse2D.Double(
	        getCenter().getX() - innerRadius,
	        getCenter().getY() - innerRadius,
	        innerRadius * 2,
	        innerRadius * 2
	    );
	    
	    // Area za donut (spoljašnji - unutrašnji)
	    Area donutArea = new Area(outerCircle);
	    donutArea.subtract(new Area(innerCircle));
	    // innerColor (samo prsten)
	    g2d.setColor(getInnerColor());
	    g2d.fill(donutArea);
	    

	    // ivice
	    g2d.setColor(getOutlineColor());
	    g2d.draw(outerCircle);   // Spoljna ivica
	    g2d.draw(innerCircle);   // Unutrašnja ivica

	    if (isSelected()) {
	        g.setColor(Color.BLUE);
	        
	        // Centar
	        g.drawRect(getCenter().getX() - 2, getCenter().getY() - 2, 4, 4);
	        
	        // Levo
	        g.drawRect(getCenter().getX() - innerRadius - 2, getCenter().getY() - 2, 4, 4);
	        
	        // Desno
	        g.drawRect(getCenter().getX() + innerRadius - 2, getCenter().getY() - 2, 4, 4);
	        
	        // Gore
	        g.drawRect(getCenter().getX() - 2, getCenter().getY() - innerRadius - 2, 4, 4);
	        
	        // Dole
	        g.drawRect(getCenter().getX() - 2, getCenter().getY() + innerRadius - 2, 4, 4);
	    }

	}

	/*super se moze iskoristiti kako bi se pristupilo public varijabli ili metodi nadklase*/
	@Override
	public String toString() {
		return "Donut(" + getCenter().getX() + "," + getCenter().getY() + "," + getRadius() + "," + innerRadius + ","  + getOutlineColor().getRGB() + "," + getInnerColor().getRGB() + ")";
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Donut) {
			Donut d = (Donut) obj;
			//this.getCenter().equals(d.getCenter()) && this.getRadius() == d.getRadius() && this.innerRadius == d.innerRadius
			if(super.equals(d) && this.innerRadius == d.innerRadius) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}

	@Override
	public Donut clone(Shape newDonut) {
		Donut donut = (Donut) newDonut;
		this.setCenter(donut.getCenter());
		this.setRadius(donut.getRadius());
		this.setInnerRadius(donut.getInnerRadius());
		this.setOutlineColor(donut.getOutlineColor());
		this.setInnerColor(donut.getInnerColor());
		return this;
	}



}