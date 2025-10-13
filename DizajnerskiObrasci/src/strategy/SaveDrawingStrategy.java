package strategy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveDrawingStrategy implements SaveStrategy{
	 public void save(Object data, String path) throws IOException {
		 if (data == null) {
	            throw new IllegalArgumentException("Data cannot be null");
	        }
	        
	        File outputFile = new File(path);
	        
	        try (ObjectOutputStream objectStream = new ObjectOutputStream(new FileOutputStream(outputFile))) {
	            objectStream.writeObject(data);
	        } catch (IOException ex) {
	            throw new IOException("Failed to serialize drawing data: " + ex.getMessage());
	        }
	    }
	    

}
