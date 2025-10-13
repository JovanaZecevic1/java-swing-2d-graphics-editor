package strategy;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import geometry.Shape;
public class LoadDrawingStrategy implements LoadStrategy<List<Shape>> {
	
	public void load(List<Shape> target, String path) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            List<Shape> loadedShapes = (List<Shape>) ois.readObject();
            target.clear();
            target.addAll(loadedShapes);
        } catch (ClassNotFoundException e) {
            throw new IOException("Error loading shapes: " + e.getMessage());
        }
    }

}
