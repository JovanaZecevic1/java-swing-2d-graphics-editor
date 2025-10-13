package strategy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveLogStrategy implements SaveStrategy {
	
	public void save(Object data, String path) throws IOException {
        List<String> log = (List<String>) data;
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        for (String line : log) {
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }
}
