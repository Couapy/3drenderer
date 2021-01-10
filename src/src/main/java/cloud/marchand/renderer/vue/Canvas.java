package cloud.marchand.renderer.vue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import cloud.marchand.renderer.models.Espace3D;
import cloud.marchand.renderer.models.Object3D;
import cloud.marchand.renderer.models.Vision3D;

public class Canvas extends JPanel {

    private static final long serialVersionUID = -3586206390830967265L;
    private Espace3D espace;
    private Vision3D vision;

    public Canvas(Espace3D espace, Vision3D vision) {
        this.espace = espace;
        this.vision = vision;
        setBackground(Color.BLACK);
    }
    
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Iterator<Object3D> iterator = espace.getObjects().iterator();
        while (iterator.hasNext()) {
        }
    }

    // private void drawFace(Graphics graphics, Face face) {
    //     Point firstPoint = null, lastPoint = null;
    //     Iterator<Point> iterator = face.getPoints().iterator();
    //     while (iterator.hasNext()) {
    //         Point point = iterator.next();
    //         if (firstPoint == null) {
    //             firstPoint = point;
    //             lastPoint = point;
    //             continue;
    //         }
            
    //         graphics.drawLine((int) point.getX(), (int) point.getY(), (int) lastPoint.getX(), (int) lastPoint.getY());

    //         lastPoint = point;
    //     }
    // }
    
}
