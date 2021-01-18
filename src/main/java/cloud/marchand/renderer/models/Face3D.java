package cloud.marchand.renderer.models;

import cloud.marchand.renderer.models.math.Vector3D;

/**
 * Face of an 3D object.
 */
public class Face3D {

    /**
     * Point in the 3D space which defines the face.
     */
    private Vector3D[] nodes;

    /**
     * Create a face from points.
     * 
     * @param node1 first point
     * @param node2 second point
     * @param node3 third point
     */
    public Face3D(Vector3D node1, Vector3D node2, Vector3D node3) {
        nodes = new Vector3D[] { node1, node2, node3 };
    }

    /**
     * Give the nodes of the face
     * 
     * @return array of 3D points
     */
    public Vector3D[] getNodes() {
        return nodes;
    }

    /**
     * Calculate the normal vector of the face
     * @return the normal
     */
	public Vector3D getNormal() {
        double[] line1, line2;

        line1 = new double[] {
            nodes[1].getX() - nodes[0].getX(),
            nodes[1].getY() - nodes[0].getY(),
            nodes[1].getZ() - nodes[0].getZ()
        };
        line2 = new double[] {
            nodes[2].getX() - nodes[0].getX(),
            nodes[2].getY() - nodes[0].getY(),
            nodes[2].getZ() - nodes[0].getZ()
        };
        double[] normal = new double[]{
            line1[1] * line2[2] - line1[2] * line2[1],
            line1[2] * line2[0] - line1[0] * line2[2],
            line1[0] * line2[1] - line1[1] * line2[0]
        };
        Vector3D normalVector = new Vector3D(normal[0], normal[1], normal[2]);
        normalVector.multiply(1/normalVector.length());
        return normalVector;
	}

    /**
     * Indicates if the face if visible.
     * @param camera point of view
     * @return true if the face is visible
     */
	public boolean isVisible(Camera camera) {
        Vector3D normal = getNormal();
        // double vectorProduct = normal.getX() * (nodes[0].getX() - camera.getX()) +
        //                        normal.getY() * (nodes[0].getY() - camera.getY()) +
        //                        normal.getZ() * (nodes[0].getZ() - camera.getZ());
        // if (vectorProduct < 0.0d) {
        if (normal.getZ() < 0.0d) {
            return true;
        }
        return false;
	}

}
