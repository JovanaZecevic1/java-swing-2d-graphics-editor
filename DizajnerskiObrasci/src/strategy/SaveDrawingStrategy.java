package strategy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveDrawingStrategy implements SaveStrategy{
	 public void save(Object data, String path) throws IOException {
	        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
	        out.writeObject(data);
	        out.close();
	    }

}
