package cloud.marchand.renderer.models;

public class Vision3D extends Point3D {

    private double angleX;
    private double angleY;

    public Vision3D() {
        super(0, 0, 0);
    }

    public Vision3D(double x, double y, double z) {
        super(x, y, z);
    }

    public Vision3D(double x, double y, double z, double angleX, double angleY) {
        super(x, y, z);
        this.setAngleX(angleX);
        this.setAngleY(angleY);
    }

    public double getAngleY() {
        return angleY;
    }

    public void setAngleY(double angleY) {
        this.angleY = angleY;
    }

    public double getAngleX() {
        return angleX;
    }

    public void setAngleX(double angleX) {
        this.angleX = angleX;
    }

}
