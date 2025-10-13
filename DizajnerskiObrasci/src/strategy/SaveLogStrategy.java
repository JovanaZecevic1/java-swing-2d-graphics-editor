package strategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveLogStrategy implements SaveStrategy {
	
	public void save(Object data, String path) throws IOException {
		if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        
        if (!(data instanceof List)) {
            throw new IllegalArgumentException("Expected List type for log data");
        }
        
        List<String> entries = (List<String>) data;
        File outputFile = new File(path);
        
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(outputFile));
            
            for (String entry : entries) {
                writer.write(entry);
                writer.newLine();
            }
            
        } catch (IOException ex) {
            throw new IOException("Could not save log file: " + ex.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                 
                }
            }
        }
    }
}
