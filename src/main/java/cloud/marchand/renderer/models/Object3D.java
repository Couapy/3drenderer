package cloud.marchand.renderer.models;

import cloud.marchand.renderer.models.math.Vector3D;

/**
 * Represent a 3D object.
 */
public class Object3D {

    /**
     * Points of the object.
     */
    protected Vector3D[] nodes;

    /**
     * Faces of the object.
     */
    protected Face3D[] faces;

    /**
     * 3D center of the object.
     * Usefull to rotate the object around this point.
     */
    protected Vector3D centralPoint;

    /**
     * Create an empty object.
     */
    protected Object3D() {
        nodes = new Vector3D[0];
        faces = new Face3D[0];
        centralPoint = new Vector3D(0, 0, 0);
    }

    /**
     * Create object from definitions.
     * @param nodes 3D point array
     * @param faces face array
     * @param centralPoint central point of the object
     */
    public Object3D(Vector3D[] nodes, Face3D[] faces, Vector3D centralPoint) {
        this.nodes = nodes;
        this.faces = faces;
        this.centralPoint = centralPoint;
    }

    /**
     * Translate the object.
     * @param translationX increase x of translationX
     * @param translationY increase y of translationY
     * @param translationZ increase z of translationZ
     */
    public void translate(double translationX, double translationY, double translationZ) {
        for (int i = 0; i < nodes.length; i++) {
            nodes[i].translate(translationX, translationY, translationZ);
         }
         centralPoint.translate(translationX, translationY, translationZ);
    }

    /**
     * Rotate the object around the central point.
     * @param rotationX radians angle to rotate the point by x
     * @param rotationY radians angle to rotate the point by y
     * @param rotationZ radians angle to rotate the point by z
     */
    public void rotate(double rotationX, double rotationY, double rotationZ) {
        for (int i = 0; i < nodes.length; i++) {
           nodes[i].rotate(centralPoint, rotationX, rotationY, rotationZ);
        }
        centralPoint.rotate(centralPoint, rotationX, rotationY, rotationZ);
    }

    /**
     * Give the nodes of the object.
     * @return node array
     */
    public Vector3D[] getNodes() {
        return nodes;
    }

    /**
     * Give the faces of the object.
     * @return face array
     */
    public Face3D[] getFaces() {
        return faces;
    }

    /**
     * Give the central point of the object.
     * @return 3D point
     */
    public Vector3D getCentralPoint() {
        return centralPoint;
    }
    
}
