package geometry;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;

public class HexagonAdapter extends Shape{
	private Hexagon hexagon;

    public HexagonAdapter(int x, int y, int r) {
        this.hexagon = new Hexagon(x, y, r);
    }


    public Hexagon getHexagon() {
        return hexagon;
    }

    @Override
    public boolean contains(int x, int y) {
        return hexagon.doesContain(x, y);
    }

    @Override
    public void draw(Graphics g) {
        hexagon.paint(g);
        if (isSelected()) {
            g.setColor(Color.BLUE);
            g.drawRect(hexagon.getX() - 3, hexagon.getY() - 3, 6, 6);
        }
    }

    @Override
    public void moveBy(int dx, int dy) {
        hexagon.setX(hexagon.getX() + dx);
        hexagon.setY(hexagon.getY() + dy);
    }

    @Override
    public void moveTo(int x, int y) {
        hexagon.setX(x);
        hexagon.setY(y);
    }

    @Override
    public boolean isSelected() {
        return hexagon.isSelected();
    }

    @Override
    public void setSelected(boolean selected) {
        hexagon.setSelected(selected);
    }

    // SAMO SHAPE METODE ZA BOJE ✅
    @Override
    public void setOutlineColor(Color color) {
        hexagon.setBorderColor(color);
    }

    @Override
    public Color getOutlineColor() {
        return hexagon.getBorderColor();
    }

    @Override
    public void setInnerColor(Color color) {
        hexagon.setAreaColor(color);
    }

    @Override
    public Color getInnerColor() {
        return hexagon.getAreaColor();
    }

    public int getX() {
        return hexagon.getX();
    }

    public int getY() {
        return hexagon.getY();
    }

    public int getR() {
        return hexagon.getR();
    }

    public void setR(int r) {
        hexagon.setR(r);
    }
    public void setX(int x) {
        hexagon.setX(x);
    }

    public void setY(int y) {
        hexagon.setY(y);
    }

    @Override
    public String toString() {
    	return "HexagonAdapter(" + hexagon.getX() + "," + hexagon.getY() + "," + hexagon.getR() + ","  + getOutlineColor().getRGB() + "," + getInnerColor().getRGB() + ")";
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof HexagonAdapter) {
            return this.getR() - ((HexagonAdapter) o).getR();
        }
        return 0;
    }
    public Point getCenter() {
		return new Point(this.hexagon.getX(), this.hexagon.getY());
	}

	public void setCenter(Point center) {
		this.hexagon.setX(center.getX());
		this.hexagon.setY(center.getY());
	}
	
	 public void setRadius(int radius) {
	    	if (radius < 0)
				throw new IllegalArgumentException("Invalid");
	        this.hexagon.setR(radius);
	    }
	    public int getRadius() {
	    	return this.hexagon.getR();
	    }

	    @Override
	    public HexagonAdapter clone() {
	        HexagonAdapter hex = new HexagonAdapter(
	            this.getX(),
	            this.getY(),
	            this.getR()
	        );
	        
	        hex.setOutlineColor(this.getOutlineColor());
	        hex.setInnerColor(this.getInnerColor());
	        
	        return hex;
	    }

    
    @Override
    public String getShapeName() {
        return "Hexagon";
    }


}
