package strategy;

import java.io.IOException;

public class LoadManager {

	 private LoadStrategy strategy;

	    public void setStrategy(LoadStrategy strategy) {
	        this.strategy = strategy;
	    }

	    public Object load(String path) throws IOException {
	        if (strategy == null) {
	            throw new IllegalStateException("Load strategy not set");
	        }
	        return strategy.load(path);
	    }
}
