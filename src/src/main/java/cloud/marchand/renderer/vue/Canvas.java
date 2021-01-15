package cloud.marchand.renderer.vue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

import cloud.marchand.renderer.interfaces.Overlay;
import cloud.marchand.renderer.models.Espace3D;
import cloud.marchand.renderer.models.Vision3D;

public class Canvas extends JPanel {

    private static final long serialVersionUID = -3586206390830967265L;
    private Espace3D espace;
    private Vision3D vision;
    private List<Overlay> overlays = new ArrayList<>();

    public Canvas(Espace3D espace, Vision3D vision) {
        this.espace = espace;
        this.vision = vision;
    }
    
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        setBackground(Color.BLACK);
        Iterator<Overlay> iterator = overlays.iterator();
        while (iterator.hasNext()) {
            Overlay overlay = iterator.next();
            overlay.draw(graphics, espace, vision);
        }
    }

    public void setOverlays(List<Overlay> overlays) {
        this.overlays = overlays;
    }

    public void addOverlay(Overlay overlay) {
        this.overlays.add(overlay);
    }
    
}
