package cloud.marchand.renderer.vue.overlays;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;

import cloud.marchand.renderer.interfaces.Overlay;
import cloud.marchand.renderer.models.Espace3D;
import cloud.marchand.renderer.models.Link3D;
import cloud.marchand.renderer.models.Object3D;
import cloud.marchand.renderer.models.Vision3D;
import cloud.marchand.renderer.models.math.Vector3D;

/**
 * TODO
 * Pour afficher l'espace 3D :
 * * Déterminer les éléments qui sont affichables à l'écran
 * * Déterminer les faces à afficher à l'écran
 * * Déterminer les faces en premier plan
 * * Afficher toutes les faces à l'écran
 */
public class SkeletonDrawing extends Overlay {

    private static final int NODE_THICKNESS = 5;

    private static final Color NODE_COLOR = Color.GREEN;
    private static final Color LINK_COLOR = Color.MAGENTA;

    @Override
    public void draw(Graphics graphics, Espace3D espace, Vision3D vision) {
        Iterator<Object3D> iterator = espace.getObjects().iterator();
        while (iterator.hasNext()) {
            Object3D object = iterator.next();
            drawObject(graphics, object, vision);
        }
    }

    private void drawObject(Graphics graphics, Object3D object, Vision3D vision) {
        Vector3D[] nodes = object.getNodes();
        Link3D[] links = object.getLinks();

        graphics.setColor(LINK_COLOR);
        for (int i = 0; i < links.length; i++) {
            Point pointVision1 = getIntersectionVision(links[i].getNode1(), vision);
            Point pointVision2 = getIntersectionVision(links[i].getNode2(), vision);
            graphics.drawLine((int) pointVision1.getX(), (int) pointVision1.getY(), (int) pointVision2.getX(), (int) pointVision2.getY());
        }

        graphics.setColor(NODE_COLOR);
        for (int i = 0; i < nodes.length; i++) {
            Point pointVision = getIntersectionVision(nodes[i], vision);
            graphics.drawOval((int) pointVision.getX() - NODE_THICKNESS/2, (int) pointVision.getY() - NODE_THICKNESS/2, NODE_THICKNESS, NODE_THICKNESS);
        }
    }

    private Point getIntersectionVision(Vector3D point, Vision3D vision) {
        double distance = 3000; // point.distance(vision)
        double alpha = 1 / (distance - point.getZ());
        double[][] projection = {
            {alpha, 0, 0},
            {0, alpha, 0}
        };
        double[][] result = point.multiply(projection);
        System.out.print(result[0][0] * 1000 + " " + result[1][0] * 1000 + "\r");
        return new Point((int) (result[0][0] * 1000), (int) (result[1][0] * 1000));
    }
    
}
