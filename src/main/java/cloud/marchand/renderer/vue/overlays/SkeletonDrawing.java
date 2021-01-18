package cloud.marchand.renderer.vue.overlays;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

import cloud.marchand.renderer.interfaces.Overlay;
import cloud.marchand.renderer.models.Espace3D;
import cloud.marchand.renderer.models.Face3D;
import cloud.marchand.renderer.models.Object3D;
import cloud.marchand.renderer.models.Camera;
import cloud.marchand.renderer.models.math.Matrix;
import cloud.marchand.renderer.models.math.Vector3D;

/**
 * TODO Pour afficher l'espace 3D :
 * Déterminer les éléments qui sont affichables à l'écran 
 */
public class SkeletonDrawing extends Overlay {

    private class SortByDistance implements Comparator<Face3D> {

        @Override
        public int compare(Face3D face1, Face3D face2) {
            Vector3D[] nodes1, nodes2;
            double face1DistanceAverage, face2DistanceAverage;

            nodes1 = face1.getNodes();
            face1DistanceAverage = 0d;
            for (int i = 0; i < nodes1.length; i++) {
                face1DistanceAverage += nodes1[i].distance(camera);
            }
            face1DistanceAverage /= nodes1.length;

            nodes2 = face2.getNodes();
            face2DistanceAverage = 0d;
            for (int i = 0; i < nodes2.length; i++) {
                face2DistanceAverage += nodes2[i].distance(camera);
            }
            face2DistanceAverage /= nodes2.length;

            if (face1DistanceAverage < face2DistanceAverage) {
                return 1;
            }
            else if (face1DistanceAverage < face2DistanceAverage) {
                return -1;
            }
            return 0;
        }
        
    }

    private static final Color LINK_COLOR = Color.MAGENTA;
    private static final int FACE_COLOR = 255;

    private Camera camera;
    private Espace3D espace;

    @Override
    public void draw(Graphics graphics, Espace3D espace, Camera camera) {
        this.espace = espace;
        this.camera = camera;
        Iterator<Object3D> iterator = espace.getObjects().iterator();
        while (iterator.hasNext()) {
            Object3D object = iterator.next();
            drawObject(graphics, object);
        }
    }

    private void drawObject(Graphics graphics, Object3D object) {
        /**
         * Get projected faces
         * Sort projected faces
         * Draw projected faces
         */

        // Get projected faces
        Face3D[] faces = object.getFaces();
        Vector3D[][] projectedFaces = new Vector3D[faces.length][3];
        for (int i = 0; i < faces.length; i++) {
            Vector3D[] nodes = faces[i].getNodes();
            for (int j = 0; j < nodes.length; j++) {
                projectedFaces[i][j] = getIntersectionCamera(graphics, nodes[j]);
            }
        }

        // Sort projected faces
        Arrays.sort(faces, new SortByDistance());

        // Draw faces
        for (int i = 0; i < projectedFaces.length; i++) {
            if (!faces[i].isVisible(camera)) {
                continue;
            }
            Vector3D[] nodes = faces[i].getNodes();
            int[] xPoints = new int[nodes.length];
            int[] yPoints = new int[nodes.length];
    
            for (int j = 0; j < nodes.length; j++) {
                Vector3D point = getIntersectionCamera(graphics, nodes[j]);
                xPoints[j] = (int) point.getX();
                yPoints[j] = (int) point.getY();
            }

            int color = FACE_COLOR;
            Vector3D light = espace.getLightDirection();
            Vector3D faceNormal = faces[i].getNormal();
            double product = faceNormal.getX() * light.getX() + faceNormal.getX() * light.getY() + faceNormal.getZ() * light.getZ();
            color *= product;
            if (color > 255) {
                color = 255;
            }
            else if (color < 0) {
                color = 0;
            }

            graphics.setColor(new Color(color, color, color));
            graphics.fillPolygon(xPoints, yPoints, nodes.length);
            graphics.setColor(LINK_COLOR);
            graphics.drawPolygon(xPoints, yPoints, nodes.length);
        }
    }

    /**
     * Give the projection point of a point.
     * @param graphics drawing
     * @param point 3D point
     * @return 2D projection
     */
    private Vector3D getIntersectionCamera(Graphics graphics, Vector3D point) {
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
        double[][] result = Matrix.multiply(newPoint, projection);

        // Essai 1

        // double distance = 2000; // point.distance(camera)
        // distance = point.distance(camera);
        // double alpha = 1 / (distance - point.getZ());
        // double[][] projection = {
        //     {alpha, 0, 0},
        //     {0, alpha, 0}
        // };
        // double[][] result = point.multiply(projection);
        return new Vector3D(result[0][0], result[1][0], result[2][0]);
    }
    
}
