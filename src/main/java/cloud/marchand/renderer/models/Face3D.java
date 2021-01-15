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
     * @param nodes points to connect
     */
    public Face3D(Vector3D[] nodes) {
        this.nodes = nodes;
    }

    /**
     * Give the nodes of the face
     * @return array of 3D points
     */
    public Vector3D[] getNodes() {
        return nodes;
    }

    /**
     * Set the nodes of the face
     * @param nodes array of 3D points
     */
    public void setNodes(Vector3D[] nodes) {
        this.nodes = nodes;
    }

}
