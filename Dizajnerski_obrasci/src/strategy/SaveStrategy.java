package strategy;

import java.io.IOException;

import mvc.DrawingModel;

public interface SaveStrategy {
	void save(DrawingModel model, String filePath) throws IOException;
    DrawingModel load(String filePath) throws IOException, ClassNotFoundException;
}
