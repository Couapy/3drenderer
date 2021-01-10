package cloud.marchand.renderer.vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;

import javax.swing.JPanel;

import cloud.marchand.renderer.models.Espace3D;
import cloud.marchand.renderer.models.Link3D;
import cloud.marchand.renderer.models.Object3D;
import cloud.marchand.renderer.models.Point3D;
import cloud.marchand.renderer.models.Vision3D;

public class Canvas extends JPanel {

    public static final int NODE_THICKNESS = 5;

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
            Object3D object = iterator.next();
            drawObject(graphics, object);
        }
    }

    private void drawObject(Graphics graphics, Object3D object) {
        // Draw links
        Iterator<Link3D> linkIterator = object.getLinks().iterator();
        graphics.setColor(Color.BLUE);
        while (linkIterator.hasNext()) {
            Link3D link = linkIterator.next();
            Point pointVision1 = getIntersectionVision(link.getNode1());
            Point pointVision2 = getIntersectionVision(link.getNode2());
            graphics.drawLine((int) pointVision1.getX(), (int) pointVision1.getY(), (int) pointVision2.getX(), (int) pointVision2.getY());
        }

        // Draw nodes
        Iterator<Point3D> nodeIterator = object.getNodes().iterator();
        graphics.setColor(Color.GREEN);
        while (nodeIterator.hasNext()) {
            Point3D point = nodeIterator.next();
            Point pointVision = getIntersectionVision(point);
            graphics.drawOval((int) pointVision.getX(), (int) pointVision.getY(), NODE_THICKNESS, NODE_THICKNESS);
        }
    }

    private Point getIntersectionVision(Point3D point) {
        // TODO: donner l'intersection du plan de vision avec la droite oeil-point
        return new Point((int) point.getX(), (int) point.getY());
    } 

    /**
     * TODO
     * Pour afficher l'espace 3D :
     * * Afficher la sky box
     * * Déterminer les éléments qui sont affichables à l'écran
     * * Déterminer les faces à afficher à l'écran
     * * Déterminer les faces en premier plan
     * * Afficher toutes les faces à l'écran
     */
    
}
