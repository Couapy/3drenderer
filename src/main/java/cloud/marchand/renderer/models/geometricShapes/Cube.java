package cloud.marchand.renderer.models.geometricShapes;

/**
 * Represent a cube inside a 3D space
 */
public class Cube extends Parallelepiped {

    /**
     * Create a cube of 100 wide
     */
    public Cube() {
        super(100, 100, 100);
    }

    /**
     * Create a cube with parameterized wide
     * @param width side length
     */
    public Cube(int width) {
        super(width, width, width);
    }

}
