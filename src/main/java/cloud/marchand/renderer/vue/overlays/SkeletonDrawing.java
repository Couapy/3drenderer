package cloud.marchand.renderer.vue.overlays;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;

import cloud.marchand.renderer.interfaces.Overlay;
import cloud.marchand.renderer.models.Espace3D;
import cloud.marchand.renderer.models.Face3D;
import cloud.marchand.renderer.models.Object3D;
import cloud.marchand.renderer.models.Camera;
import cloud.marchand.renderer.models.math.Matrix;
import cloud.marchand.renderer.models.math.Vector3D;

/**
 * TODO Pour afficher l'espace 3D : * Déterminer les éléments qui sont
 * affichables à l'écran * Déterminer les faces à afficher à l'écran *
 * Déterminer les faces en premier plan * Afficher toutes les faces à l'écran
 */
public class SkeletonDrawing extends Overlay {

    private static final int NODE_THICKNESS = 5;

    private static final Color NODE_COLOR = Color.GREEN;
    private static final Color LINK_COLOR = Color.MAGENTA;
    private static final Color FACE_COLOR = Color.DARK_GRAY;

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

        for (int i = 0; i < faces.length; i++) {
            drawFace(graphics, faces[i], camera);
        }

        graphics.setColor(NODE_COLOR);
        for (int i = 0; i < nodes.length; i++) {
            Point pointcamera = getIntersectionCamera(graphics, nodes[i], camera);
            graphics.drawOval((int) pointcamera.getX() - NODE_THICKNESS / 2,
                    (int) pointcamera.getY() - NODE_THICKNESS / 2, NODE_THICKNESS, NODE_THICKNESS);
        }
    }

    private void drawFace(Graphics graphics, Face3D face, Camera camera) {
        Vector3D[] nodes = face.getNodes();
        int[] xPoints = new int[nodes.length];
        int[] yPoints = new int[nodes.length];

        for (int i = 0; i < nodes.length; i++) {
            Point point = getIntersectionCamera(graphics, nodes[i], camera);
            xPoints[i] = (int) point.getX();
            yPoints[i] = (int) point.getY();
        }

        int color = 70;

        graphics.setColor(new Color(color, color, color));
        graphics.fillPolygon(xPoints, yPoints, nodes.length);
        graphics.setColor(LINK_COLOR);
        graphics.drawPolygon(xPoints, yPoints, nodes.length);
    }

    private Point getIntersectionCamera(Graphics graphics, Vector3D point, Camera camera) {
        Rectangle graphicsBounds = graphics.getClipBounds();

        // Vector3D p = new Vector3D(point.getX(), point.getY(), point.getZ());
        // p.translate(camera.getX(), camera.getY(), camera.getZ());

        // double[][] d = new double[][]{
        //     {point.getX() - camera.getX()},
        //     {point.getY() - camera.getY()},
        //     {point.getZ() - camera.getZ()}
        // };
        // double[][] orientationX = new double[][]{
        //     {1d, 0d, 0d},
        //     {0d, Math.cos(camera.getAnglecameraX()), Math.sin(camera.getAnglecameraX())},
        //     {0d, -Math.sin(camera.getAnglecameraX()), Math.cos(camera.getAnglecameraX())}
        // };
        // double[][] orientationY = new double[][]{
        //     {Math.cos(camera.getAnglecameraY()), 0d, -Math.sin(camera.getAnglecameraY())},
        //     {0d, 1d, 0d},
        //     {Math.sin(camera.getAnglecameraY()), 0d, Math.cos(camera.getAnglecameraY())}
        // };
        // double[][] orientationZ = new double[][]{
        //     {Math.cos(camera.getAnglecameraZ()), Math.sin(camera.getAnglecameraZ()), 0d},
        //     {-Math.sin(camera.getAnglecameraZ()), Math.cos(camera.getAnglecameraZ()), 0d},
        //     {0d, 0d, 1d}
        // };

        // d = Matrix.multiply(orientationZ, d);
        // d = Matrix.multiply(orientationY, d);
        // d = Matrix.multiply(orientationX, d);

        // double ex = graphicsBounds.getWidth() / 2;
        // double ey = graphicsBounds.getHeight() / 2;
        // double ez = point.distance(camera);
        
        // double[][] transform = new double[][]{
        //     {1d, 0d, ex / ez},
        //     {0d, 1d, ey / ez},
        //     {0d, 0d, 1/ez}
        // };

        // double[][] f = Matrix.multiply(transform, d);

        // double bx = f[0][0] / f[2][0];
        // double by = f[1][0] / f[2][0];

        // return new Point((int) bx, (int) by);


        // Essai deux

        double fNear = 0.1d;
        double fFar = 10000d;
        double fFov = Math.PI / 2;
        double fAspectRation = graphicsBounds.getHeight() / graphicsBounds.getHeight();

        double[][] projection = new double[][]{
            {fAspectRation * fFov, 0d, 0d, 0d},
            {0d, fFov, 0d, 0d},
            {0d, 0d, fFar / (fFar - fNear), 1d},
            {0d, 0d, (-fFar * fNear) / (fFar - fNear), 0d},
        };

        
        double[][] newPoint = new double[][] {
            { point.getX() },
            { point.getY() },
            { point.getZ() },
            { point.distance(camera) }
        };
        double [][] result = Matrix.multiply(newPoint, projection);

        // Essai 1

        // double distance = 2000; // point.distance(camera)
        // distance = point.distance(camera);
        // double alpha = 1 / (distance - point.getZ());
        // double[][] projection = {
        //     {alpha, 0, 0},
        //     {0, alpha, 0}
        // };
        // double[][] result = point.multiply(projection);
        return new Point((int) (result[0][0] * 1000 + 200), (int) (result[1][0] * 1000 + 200));
    }
    
}
