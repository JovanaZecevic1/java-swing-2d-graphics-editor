package strategy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.DefaultListModel;


public class LoadLogStrategy implements LoadStrategy<DefaultListModel<String>> {

	@Override
	public void load(DefaultListModel<String> target, String path) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            target.clear(); // Čistimo prethodni log
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    target.addElement(line); // Dodajemo liniju u log
                }
            }
        }
    }

}
