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

    @Override
    public String toString() {
        return "center(" + getX() + "," + getY() + "), r=" + getR();
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof HexagonAdapter) {
            return this.getR() - ((HexagonAdapter) o).getR();
        }
        return 0;
    }

    @Override
    public HexagonAdapter clone() {
        HexagonAdapter clone = new HexagonAdapter(
            this.getX(),
            this.getY(),
            this.getR()
        );
        clone.setOutlineColor(this.getOutlineColor());
        clone.setInnerColor(this.getInnerColor());
        clone.setSelected(this.isSelected());
        return clone;
    }
    
    @Override
    public String getShapeName() {
        return "Hexagon";
    }


}
