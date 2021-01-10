package cloud.marchand.renderer.models;

import java.util.ArrayList;
import java.util.List;

import cloud.marchand.renderer.interfaces.Observable;

public class Object3D extends Observable {

    protected List<Point3D> nodes = new ArrayList<>();
    protected List<Link3D> links = new ArrayList<>();
    private double rotationX = 0.0;
    private double rotationY = 0.0;
    private double rotationZ = 0.0;

    public Object3D() {
    }

    public Object3D(List<Point3D> nodes, List<Link3D> links) {
        this.nodes = nodes;
        this.links = links;
    }

    public double getRotationX() {
        return rotationX;
    }
    
    public double getRotationY() {
        return rotationY;
    }

    public double getRotationZ() {
        return rotationZ;
    }
    
    public synchronized void rotateX(double rotationX) {
        this.rotationX += rotationX;
        while (this.rotationX < 0) {
            this.rotationX += 180;
        }
        while (this.rotationX >= 180) {
            this.rotationX -= 180;
        }

        // TODO: rotation around the axe
    }

    public synchronized void rotateY(double rotationY) {
        this.rotationY += rotationY;
        while (this.rotationX < 0) {
            this.rotationX += 180;
        }
        while (this.rotationY >= 180) {
            this.rotationY -= 180;
        }

        // TODO: rotation around the axe
    }

    public synchronized void rotateZ(double rotationZ) {
        this.rotationZ += rotationZ;
        while (this.rotationX < 0) {
            this.rotationX += 180;
        }
        while (this.rotationZ >= 180) {
            this.rotationZ -= 180;
        }

        // TODO: rotation around the axe
    }

}
