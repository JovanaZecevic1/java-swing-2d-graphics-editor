package strategy;

import java.io.IOException;

public class SaveManager {
	private SaveStrategy strategy;

    public void setStrategy(SaveStrategy strategy) {
        this.strategy = strategy;
    }

    public void save(Object data, String path) throws IOException {
        if (strategy == null) {
            throw new IllegalStateException("Save strategy not set");
        }
        strategy.save(data, path);
    }

}
