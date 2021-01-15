package cloud.marchand.renderer.vue.overlays;

import java.awt.Color;
import java.awt.Graphics;

import cloud.marchand.renderer.interfaces.Overlay;
import cloud.marchand.renderer.models.Espace3D;
import cloud.marchand.renderer.models.Vision3D;

public class FPSCounter extends Overlay {

    private long lastTime = 0;
    private int FPS = 0;
    private int FPS_AVERAGE = 0;

    @Override
    public void draw(Graphics graphics, Espace3D espace, Vision3D vision) {
        if (System.currentTimeMillis() > lastTime + 1000) {
            lastTime = System.currentTimeMillis();
            FPS_AVERAGE = FPS;
            FPS = 0;
        }
        FPS++;
        if (FPS_AVERAGE > 120) {
            graphics.setColor(Color.GREEN);
        }
        else if (FPS_AVERAGE > 60) {
            graphics.setColor(Color.ORANGE);
        }
        else {
            graphics.setColor(Color.RED);
        }
        graphics.drawString("FPS " + FPS_AVERAGE, 0, 10);
    }
    
}
