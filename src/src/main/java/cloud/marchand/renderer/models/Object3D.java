package cloud.marchand.renderer.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cloud.marchand.renderer.interfaces.Observable;

public class Object3D extends Observable {

    protected List<Point3D> nodes = new ArrayList<>();
    protected List<Link3D> links = new ArrayList<>();
    protected Point3D centralPoint;

    public Object3D() {
    }

    public Object3D(List<Point3D> nodes, List<Link3D> links) {
        this.nodes = nodes;
        this.links = links;
    }

    public List<Point3D> getNodes() {
        return nodes;
    }

    public List<Link3D> getLinks() {
        return links;
    }

    public Point3D getCentralPoint() {
        return centralPoint;
    }

    public void rotate(double rotationX, double rotationY, double rotationZ) {
        rotateX(rotationX);
        rotateY(rotationY);
        rotateZ(rotationZ);
    }

    public void rotateX(double rotationX) {
        Iterator<Point3D> iterator = nodes.iterator();
        while (iterator.hasNext()) {
            Point3D point = iterator.next();
            point.rotateX(centralPoint, rotationX);
        }

        setChanged();
        notifyObservers();
    }

    public void rotateY(double rotationY) {
        Iterator<Point3D> iterator = nodes.iterator();
        while (iterator.hasNext()) {
            Point3D point = iterator.next();
            point.rotateY(centralPoint, rotationY);
        }

        setChanged();
        notifyObservers();
    }

    public void rotateZ(double rotationZ) {
        Iterator<Point3D> iterator = nodes.iterator();
        while (iterator.hasNext()) {
            Point3D point = iterator.next();
            point.rotateZ(centralPoint, rotationZ);
        }

        setChanged();
        notifyObservers();
    }

    public void translate(double x, double y, double z) {
        Iterator<Point3D> iterator = nodes.iterator();
        while (iterator.hasNext()) {
            Point3D point = iterator.next();
            point.translate(x, y, z);
        }
        centralPoint.translate(x, y, z);

        setChanged();
        notifyObservers();
    }

}
