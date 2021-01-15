package cloud.marchand.renderer.models;

import cloud.marchand.renderer.models.math.Vector3D;

public class Link3D {

    private Vector3D node1;
    private Vector3D node2;

    public Link3D(Vector3D node1, Vector3D node2) {
        this.node1 = node1;
        this.node2 = node2;
    }

    public void setNode1(Vector3D node1) {
        this.node1 = node1;
    }

    public void setNode2(Vector3D node2) {
        this.node2 = node2;
    }
    
    public Vector3D getNode2() {
        return node2;
    }

    public Vector3D getNode1() {
        return node1;
    }

}
