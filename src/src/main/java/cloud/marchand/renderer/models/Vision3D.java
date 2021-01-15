package cloud.marchand.renderer.models;

import cloud.marchand.renderer.models.math.Vector3D;

public class Vision3D extends Vector3D {

    private double angleVisionX;
    private double angleVisionY;
    private double angleVisionZ;

    public Vision3D() {
        super(0, 0, 0);
    }

    public Vision3D(Vector3D origine) {
        super(origine.getX(), origine.getY(), origine.getZ());
    }

    public Vision3D(Vector3D origine, double angleVisionX, double angleVisionY, double angleVisionZ) {
        this(origine);
        this.setAngleVisionX(angleVisionX);
        this.setAngleVisionY(angleVisionY);
        this.setAngleVisionZ(angleVisionZ);
    }

    public double getAngleVisionX() {
        return angleVisionX;
    }

    public double getAngleVisionY() {
        return angleVisionY;
    }

    public double getAngleVisionZ() {
        return angleVisionZ;
    }

    public void setAngleVisionX(double angleVisionX) {
        this.angleVisionX = angleVisionX;
    }

    public void setAngleVisionY(double angleVisionY) {
        this.angleVisionY = angleVisionY;
    }

    public void setAngleVisionZ(double angleVisionZ) {
        this.angleVisionZ = angleVisionZ;
    }

}
