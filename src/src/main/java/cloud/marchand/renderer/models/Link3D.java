package cloud.marchand.renderer.models;

public class Link3D {

    private Point3D node1;
    private Point3D node2;

    public Link3D(Point3D node1, Point3D node2) {
        this.node1 = node1;
        this.node2 = node2;
    }

    public void setNode1(Point3D node1) {
        this.node1 = node1;
    }

    public void setNode2(Point3D node2) {
        this.node2 = node2;
    }
    
    public Point3D getNode2() {
        return node2;
    }

    public Point3D getNode1() {
        return node1;
    }

}
