package cloud.marchand.renderer.models.geometricShapes;

import cloud.marchand.renderer.models.Link3D;
import cloud.marchand.renderer.models.Object3D;
import cloud.marchand.renderer.models.Point3D;

public class Cube extends Object3D {

    public Cube() {
        this(100);
    }

    public Cube(int width) {
        // Create nodes of cube
        // Bottom square
        nodes.add(new Point3D(0,     0,     0));
        nodes.add(new Point3D(width, 0,     0));
        nodes.add(new Point3D(0,     width, 0));
        nodes.add(new Point3D(width, width, 0));
        // Top square
        nodes.add(new Point3D(0,     0,     width));
        nodes.add(new Point3D(width, 0,     width));
        nodes.add(new Point3D(0,     width, width));
        nodes.add(new Point3D(width, width, width));

        // Bottom square
        links.add(new Link3D(nodes.get(0), nodes.get(1)));
        links.add(new Link3D(nodes.get(1), nodes.get(2)));
        links.add(new Link3D(nodes.get(2), nodes.get(3)));
        links.add(new Link3D(nodes.get(3), nodes.get(0)));
        // Top square
        links.add(new Link3D(nodes.get(4), nodes.get(5)));
        links.add(new Link3D(nodes.get(5), nodes.get(6)));
        links.add(new Link3D(nodes.get(6), nodes.get(7)));
        links.add(new Link3D(nodes.get(7), nodes.get(4)));
        // Between squares
        links.add(new Link3D(nodes.get(0), nodes.get(4)));
        links.add(new Link3D(nodes.get(1), nodes.get(5)));
        links.add(new Link3D(nodes.get(2), nodes.get(6)));
        links.add(new Link3D(nodes.get(3), nodes.get(7)));
    }
    
}
