package cloud.marchand.renderer.vue.controller;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

import cloud.marchand.renderer.vue.Window;

public class WindowResizeController extends ComponentAdapter {

    private Window window;
    private JFrame frame;

    public WindowResizeController(Window window) {
        this.window = window;
        this.frame = window.getFrame();
    }

    @Override
    public void componentResized(ComponentEvent e) {
        window.getCanvas().setBounds(0, 0, frame.getWidth(), frame.getHeight());
    }
    
}
