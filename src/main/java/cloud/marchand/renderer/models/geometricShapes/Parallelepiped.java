package cloud.marchand.renderer.models.geometricShapes;

import cloud.marchand.renderer.models.Face3D;
import cloud.marchand.renderer.models.Object3D;
import cloud.marchand.renderer.models.math.Vector3D;

/**
 * Represent a cube inside a 3D space
 */
public class Parallelepiped extends Object3D {

    /**
     * Create a parallelepiped width 100x200x300 default size.
     */
    public Parallelepiped() {
        this(100, 200, 300);
    }

    /**
     * Create a parallelepiped with parameterized sizes.
     * @param width width of the parallelepiped
     * @param deep deep of the parallelepiped
     * @param height height of the parallelepiped
     */
    public Parallelepiped(double width, double deep, double height) {
        super();

        defineNodes(width, deep, height);
        defineFaces();

        centralPoint = new Vector3D(width / 2, deep / 2, height / 2);
    }

    /**
     * Create points of the object.
     * @param width width of the parallelepiped
     * @param deep deep of the parallelepiped
     * @param height height of the parallelepiped
     */
    private void defineNodes(double width, double deep, double height) {
        nodes = new Vector3D[]{
            new Vector3D(0,     0,    0),
            new Vector3D(width, 0,    0),
            new Vector3D(width, deep, 0),
            new Vector3D(0,     deep, 0),
            new Vector3D(0,     0,    height),
            new Vector3D(width, 0,    height),
            new Vector3D(width, deep, height),
            new Vector3D(0,     deep, height)
        };
    }

    /**
     * Links points to make faces.
     */
    private void defineFaces() {
        faces = new Face3D[]{
            new Face3D(nodes[0], nodes[1], nodes[2]),
            new Face3D(nodes[0], nodes[2], nodes[3]),
            new Face3D(nodes[0], nodes[4], nodes[5]),
            new Face3D(nodes[0], nodes[1], nodes[5]),
            new Face3D(nodes[1], nodes[2], nodes[5]),
            new Face3D(nodes[2], nodes[5], nodes[6]),
            new Face3D(nodes[2], nodes[6], nodes[7]),
            new Face3D(nodes[2], nodes[3], nodes[7]),
            new Face3D(nodes[0], nodes[3], nodes[7]),
            new Face3D(nodes[0], nodes[4], nodes[7]),
            new Face3D(nodes[4], nodes[5], nodes[7]),
            new Face3D(nodes[5], nodes[6], nodes[7])
        };
    }
    
}
