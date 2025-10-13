package strategy;

import java.io.IOException;

public interface LoadStrategy <T>{
	 void load(T target, String path) throws IOException;

}
