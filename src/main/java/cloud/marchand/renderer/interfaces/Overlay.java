package cloud.marchand.renderer.interfaces;

import java.awt.Graphics;

import cloud.marchand.renderer.models.Espace3D;
import cloud.marchand.renderer.models.Camera;

/**
 * Layer of a drawing zone.
 */
public abstract class Overlay {

    /**
     * Indicates if the overlay need the mouse focus.
     */
    protected boolean mouseFocus;

    /**
     * Indicates if the overlay need the keyboard focus.
     */
    protected boolean keyboardFocus;

    /**
     * Create a new overlay instance.
     */
    public Overlay() {
        mouseFocus = false;
        keyboardFocus = false;
    }

    /**
     * Draw the overlay on the drawing zone.
     * @param graphics graphical zone to draw in
     * @param espace 3D space
     * @param camera point of view
     */
    public abstract void draw(Graphics graphics, Espace3D espace, Camera camera);
    
}
