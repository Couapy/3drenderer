package cloud.marchand.renderer.models;

import cloud.marchand.renderer.models.math.Vector3D;

public class Object3D {

    protected Vector3D[] nodes;
    protected Link3D[] links;
    protected Face3D[] faces;

    protected Vector3D centralPoint;

    public Object3D() {
        nodes = new Vector3D[0];
        links = new Link3D[0];
        faces = new Face3D[0];
    }

    public Object3D(Vector3D[] nodes, Link3D[] links, Face3D[] faces, Vector3D centralPoint) {
        this.setNodes(nodes);
        this.setLinks(links);
        this.setFaces(faces);
        this.setCentralPoint(centralPoint);
    }

    public void translate(double translationX, double translationY, double translationZ) {
        for (int i = 0; i < nodes.length; i++) {
            nodes[i].translate(translationX, translationY, translationZ);
         }
         centralPoint.translate(translationX, translationY, translationZ);
    }

    public void rotate(double rotationX, double rotationY, double rotationZ) {
        for (int i = 0; i < nodes.length; i++) {
           nodes[i].rotate(centralPoint, rotationX, rotationY, rotationZ);
        }
        centralPoint.rotate(centralPoint, rotationX, rotationY, rotationZ);
    }

    public Vector3D[] getNodes() {
        return nodes;
    }

    public Link3D[] getLinks() {
        return links;
    }

    public Face3D[] getFaces() {
        return faces;
    }

    public Vector3D getCentralPoint() {
        return centralPoint;
    }

    public void setNodes(Vector3D[] nodes) {
        this.nodes = nodes;
    }

    public void setLinks(Link3D[] links) {
        this.links = links;
    }

    public void setFaces(Face3D[] faces) {
        this.faces = faces;
    }

    public void setCentralPoint(Vector3D centralPoint) {
        this.centralPoint = centralPoint;
    }
    
}
