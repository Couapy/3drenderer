package cloud.marchand.renderer.models;

import cloud.marchand.renderer.models.math.Vector3D;

/**
 * Connect two 3D points together.
 */
public class Link3D {

    /**
     * First point.
     */
    private Vector3D node1;

    /**
     * Second point.
     */
    private Vector3D node2;

    /**
     * Create a link.
     * @param node1 first point
     * @param node2 second point
     */
    public Link3D(Vector3D node1, Vector3D node2) {
        this.node1 = node1;
        this.node2 = node2;
    }

    /**
     * Give the first point.
     * @return first node of the link
     */
    public Vector3D getNode2() {
        return node2;
    }

    /**
     * Give the second point.
     * @return second node of the link
     */
    public Vector3D getNode1() {
        return node1;
    }

}
