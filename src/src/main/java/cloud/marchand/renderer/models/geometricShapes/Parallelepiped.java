package cloud.marchand.renderer.models.geometricShapes;

import cloud.marchand.renderer.models.Link3D;
import cloud.marchand.renderer.models.Object3D;
import cloud.marchand.renderer.models.math.Vector3D;

public class Parallelepiped extends Object3D {

    public Parallelepiped() {
        this(100, 200, 300);
    }

    public Parallelepiped(double width, double deep, double height) {
        super();

        nodes = new Vector3D[8];
        links = new Link3D[12];
        // TODO: faces
        // Create nodes of cube
        // Bottom square
        nodes[0] = new Vector3D(0,     0,    0);
        nodes[1] = new Vector3D(width, 0,    0);
        nodes[2] = new Vector3D(0,     deep, 0);
        nodes[3] = new Vector3D(width, deep, 0);
        // Top square
        nodes[4] = new Vector3D(0,     0,    height);
        nodes[5] = new Vector3D(width, 0,    height);
        nodes[6] = new Vector3D(0,     deep, height);
        nodes[7] = new Vector3D(width, deep, height);

        // Bottom square
        links[0] = new Link3D(nodes[0], nodes[1]);
        links[1] = new Link3D(nodes[1], nodes[3]);
        links[2] = new Link3D(nodes[3], nodes[2]);
        links[3] = new Link3D(nodes[2], nodes[0]);
        // Top square
        links[4] = new Link3D(nodes[4], nodes[5]);
        links[5] = new Link3D(nodes[5], nodes[7]);
        links[6] = new Link3D(nodes[7], nodes[6]);
        links[7] = new Link3D(nodes[6], nodes[4]);
        // Between squares
        links[8] = new Link3D(nodes[0], nodes[4]);
        links[9] = new Link3D(nodes[1], nodes[5]);
        links[10] = new Link3D(nodes[2], nodes[6]);
        links[11] = new Link3D(nodes[3], nodes[7]);
        
        // Central point of the object
        centralPoint = new Vector3D(width / 2, deep / 2, height / 2);
    }
    
}
