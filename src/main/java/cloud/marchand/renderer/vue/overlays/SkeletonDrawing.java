package cloud.marchand.renderer.vue.overlays;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;

import cloud.marchand.renderer.interfaces.Overlay;
import cloud.marchand.renderer.models.Espace3D;
import cloud.marchand.renderer.models.Face3D;
import cloud.marchand.renderer.models.Object3D;
import cloud.marchand.renderer.models.Camera;
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
    public void draw(Graphics graphics, Espace3D espace, Camera camera) {
        Iterator<Object3D> iterator = espace.getObjects().iterator();
        while (iterator.hasNext()) {
            Object3D object = iterator.next();
            drawObject(graphics, object, camera);
        }
    }

    private void drawObject(Graphics graphics, Object3D object, Camera camera) {
        Vector3D[] nodes = object.getNodes();
        Face3D[] faces = object.getFaces();

        graphics.setColor(LINK_COLOR);
        for (int i = 0; i < faces.length; i++) {
            drawFace(graphics, faces[i], camera);
        }

        graphics.setColor(NODE_COLOR);
        for (int i = 0; i < nodes.length; i++) {
            Point pointcamera = getIntersectionCamera(nodes[i], camera);
            graphics.drawOval((int) pointcamera.getX() - NODE_THICKNESS/2, (int) pointcamera.getY() - NODE_THICKNESS/2, NODE_THICKNESS, NODE_THICKNESS);
        }
    }

    private void drawFace(Graphics graphics, Face3D face, Camera camera) {
        Vector3D[] nodes = face.getNodes();
        int[] xPoints = new int[nodes.length];
        int[] yPoints = new int[nodes.length];

        for (int i = 0; i < nodes.length; i++) {
            Point point = getIntersectionCamera(nodes[i], camera);
            xPoints[i] = (int) point.getX();
            yPoints[i] = (int) point.getY();
        }

        graphics.drawPolygon(xPoints, yPoints, nodes.length);
    }

    private Point getIntersectionCamera(Vector3D point, Camera camera) {
        double distance = 2000; // point.distance(camera)
        distance = point.distance(camera);
        double alpha = 1 / (distance - point.getZ());
        double[][] projection = {
            {alpha, 0, 0},
            {0, alpha, 0}
        };
        double[][] result = point.multiply(projection);
        return new Point((int) (result[0][0] * 1000 + 200), (int) (result[1][0] * 1000 + 200));
    }
    
}
