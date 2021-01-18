package cloud.marchand.renderer.vue.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import cloud.marchand.renderer.App;

/**
 * Handler key pressures.
 */
public class KeyboardController implements KeyListener {

    /**
     * The main thread.
     */
    private App application;

    /**
     * Initialize the controller.
     * @param application main thread
     */
    public KeyboardController(App application) {
        this.application = application;
    }

    /**
     * Called just after the user types a Unicode character into the listened-to component.
     * @param e keyboard event
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Called just after the user presses a key while the listened-to component has the focus.
     * @param e keyboard event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            application.close();
            return;
        }
        application.setPressed(e.getKeyCode(), true);
    }

    /**
     * Called just after the user releases a key while the listened-to component has the focus.
     * @param e keyboard event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        application.setPressed(e.getKeyCode(), false);
    }
    
}
