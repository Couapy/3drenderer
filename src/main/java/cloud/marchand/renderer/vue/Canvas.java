package cloud.marchand.renderer.vue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

import cloud.marchand.renderer.interfaces.Overlay;
import cloud.marchand.renderer.models.Espace3D;
import cloud.marchand.renderer.models.Camera;

/**
 * Drawing zone for 3D space.
 */
public class Canvas extends JPanel {

    /**
     * Useful for the serialisation.
     * But object not serialized, present for avoid warning message.
     */
    private static final long serialVersionUID = -3586206390830967265L;

    /**
     * 3D space to represent.
     */
    private Espace3D espace;

    /**
     * Point of view of the application.
     */
    private Camera camera;

    /**
     * Layers of the drawing zone.
     * @see cloud.marchand.renderer.interfaces.Overlay
     */
    private List<Overlay> overlays = new ArrayList<>();

    /**
     * Create a new canvas from a point of view inside a 3D space
     * @param espace 3D space
     * @param camera point of view
     */
    public Canvas(Espace3D espace, Camera camera) {
        this.espace = espace;
        this.camera = camera;
    }
    
    /**
     * Update the overlays.
     */
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        setBackground(Color.BLACK);
        Iterator<Overlay> iterator = overlays.iterator();
        while (iterator.hasNext()) {
            Overlay overlay = iterator.next();
            overlay.draw(graphics, espace, camera);
        }
    }

    /**
     * Defines the layers of the drawing.
     * @param overlays list of overlays
     */
    public void setOverlays(List<Overlay> overlays) {
        this.overlays = overlays;
    }

    /**
     * Add a new overlay on the top of the drawing.
     * @param overlay new overlay to add
     */
    public void addOverlay(Overlay overlay) {
        this.overlays.add(overlay);
    }
    
}
