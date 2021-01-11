package cloud.marchand.renderer.models.geometricShapes;

public class Cube extends Parallelepiped {

    public Cube() {
        super(100, 100, 100);
    }

    public Cube(int width) {
        super(width, width, width);
    }

}
