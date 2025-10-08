package mvc;

import java.awt.EventQueue;

public class Application {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
            FrameDrawing frame = new FrameDrawing();
            frame.setVisible(true);
        });

	}

}