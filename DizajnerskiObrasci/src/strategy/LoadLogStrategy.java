package strategy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;


public class LoadLogStrategy implements LoadStrategy{

	 @Override
	    public Object load(String path) throws IOException {
	        if (path == null) {
	            throw new IllegalArgumentException("Path cannot be null");
	        }
	        
	        List<String> entries = new ArrayList<>();
	        File inputFile = new File(path);
	        
	        BufferedReader reader = null;
	        try {
	            reader = new BufferedReader(new FileReader(inputFile));
	            String entry;
	            
	            while ((entry = reader.readLine()) != null) {
	                if (entry.trim().length() > 0) {
	                    entries.add(entry);
	                }
	            }
	            
	        } catch (IOException ex) {
	            throw new IOException("Could not load log file: " + ex.getMessage());
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e) {
	                   
	                }
	            }
	        }
	        
	        return entries;
	    }

}
