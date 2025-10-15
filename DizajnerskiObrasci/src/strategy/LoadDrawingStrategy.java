package strategy;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import geometry.Shape;
public class LoadDrawingStrategy implements LoadStrategy{
	
	 @Override
	    public Object load(String path) throws IOException {
	        if (path == null) {
	            throw new IllegalArgumentException("Path cannot be null");
	        }
	        
	        File inputFile = new File(path);
	        ObjectInputStream objectStream = null;
	        
	        try {
	            FileInputStream fileStream = new FileInputStream(inputFile);
	            objectStream = new ObjectInputStream(fileStream);
	            Object data = objectStream.readObject();
	            return data;
	            
	        } catch (ClassNotFoundException ex) {
	            throw new IOException("Invalid drawing file format: " + ex.getMessage());
	        } catch (IOException ex) {
	            throw new IOException("Failed to load drawing file: " + ex.getMessage());
	        } finally {
	            if (objectStream != null) {
	                try {
	                    objectStream.close();
	                } catch (IOException e) {
	                    
	                }
	            }
	        }
	    }

}
