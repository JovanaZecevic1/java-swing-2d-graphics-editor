package strategy;

import java.io.IOException;

public interface SaveStrategy {
	void save(Object data, String path) throws IOException;

}
