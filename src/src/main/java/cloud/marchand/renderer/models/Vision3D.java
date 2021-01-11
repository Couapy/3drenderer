package cloud.marchand.renderer.models;

public class Vision3D extends Point3D {

    /**
     * Angles d'orientation de la vue
     */
    private double angleX;
    private double angleY;
    
    /**
     * Angle du champ de vision de la vue
     */
    private double angleVisionX;
    private double angleVisionY;

    public Vision3D() {
        super(0, 0, 0);
    }

    public Vision3D(Point3D origine) {
        super(origine.getX(), origine.getY(), origine.getZ());
    }

    public Vision3D(Point3D origine, double angleX, double angleY) {
        this(origine);
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
