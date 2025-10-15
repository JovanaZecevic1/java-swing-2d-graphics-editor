package strategy;

import java.io.File;
import java.io.IOException;

public interface LoadStrategy{
	Object load(String path) throws IOException;

}
