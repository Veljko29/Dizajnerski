package mvc;

import javax.swing.JFrame;

public class Application {
    public static void main(String[] args) {
        // Pokreni DrawingFrame
        DrawingFrame frame = new DrawingFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}