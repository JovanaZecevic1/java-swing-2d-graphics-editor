package strategy;

import java.io.IOException;

public class LoadManager <T> {

	private LoadStrategy<T> strategy;

    public void setStrategy(LoadStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public void load(T target, String path) throws IOException {
        if (strategy != null) {
            strategy.load(target, path);
        }
    }
}
