package cloud.marchand.renderer.vue.controller;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

import cloud.marchand.renderer.vue.Window;
/**
 * Control the resize event of the window.
 */
public class WindowResizeController extends ComponentAdapter {

    /**
     * Window observed.
     */
    private Window window;

    /**
     * Frame of the window observed.
     */
    private JFrame frame;

    /**
     * Initialize the controller.
     * @param window window to observe
     */
    public WindowResizeController(Window window) {
        this.window = window;
        this.frame = window.getFrame();
    }

    /**
     * Defines a new size to the canvas.
     * @param e graphical event send by the frame
     */
    @Override
    public void componentResized(ComponentEvent e) {
        window.getCanvas().setBounds(0, 0, frame.getWidth(), frame.getHeight());
    }
    
}
