package cloud.marchand.renderer.interfaces;

import java.awt.Graphics;

import cloud.marchand.renderer.models.Espace3D;
import cloud.marchand.renderer.models.Vision3D;

public abstract class Overlay {

    protected boolean mouseFocus;
    protected boolean keyboardFocus;

    public Overlay() {
        mouseFocus = false;
        keyboardFocus = false;
    }

    public abstract void draw(Graphics graphics, Espace3D espace, Vision3D vision);
    
}
