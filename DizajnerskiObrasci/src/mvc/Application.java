package mvc;

import java.awt.EventQueue;

public class Application {

	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Create MVC components
                    ModelDrawing model = new ModelDrawing();
                    FrameDrawing frame = new FrameDrawing(model);
                    ControllerDrawing controller = new ControllerDrawing(model, frame);

                    // Connect them together
                    frame.setController(controller);
                    frame.getView().setModel(model);

                    // Show the application
                    frame.setVisible(true);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("Error starting application: " + e.getMessage());
                }
            }
        });
	}
}