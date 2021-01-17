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
     * @param nodes points to connect
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

}
