package observer;
import javax.swing.JButton;

import geometry.Shape;
public class ButtonObserver {
	    public static void disableButtonsIfNotSelected(JButton bringToFrontButton, JButton bringToBackButton, boolean shape) {
	        bringToFrontButton.setEnabled(shape);
	        bringToBackButton.setEnabled(shape);
	    }
}