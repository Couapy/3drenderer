package cloud.marchand.renderer.models;

import cloud.marchand.renderer.models.math.Vector3D;

/**
 * Point of view in a 3D space.
 */
public class Camera extends Vector3D {

    /**
     * Angle of the point of view.
     */
    private double anglecameraX;

    /**
     * Angle of the point of view.
     */
    private double anglecameraY;

    /**
     * Angle of the point of view.
     */
    private double anglecameraZ;

    /**
     * Create a default point of view.
     */
    public Camera() {
        super(0, 0, 0);
    }

    /**
     * Create a parameterized point of view.
     * @param origine 3D point in a 3D space
     */
    public Camera(Vector3D origine) {
        super(origine.getX(), origine.getY(), origine.getZ());
    }

    /**
     * Create a parameterized point of view.
     * @param origine 3D point in a 3D space
     * @param anglecameraX angle of the point of view
     * @param anglecameraY angle of the point of view
     * @param anglecameraZ angle of the point of view
     */
    public Camera(Vector3D origine, double anglecameraX, double anglecameraY, double anglecameraZ) {
        this(origine);
        this.setAnglecameraX(anglecameraX);
        this.setAnglecameraY(anglecameraY);
        this.setAnglecameraZ(anglecameraZ);
    }

    /**
     * Give the angle of the point of view.
     * @return angle of the point of view
     */
    public double getAnglecameraX() {
        return anglecameraX;
    }

    /**
     * Give the angle of the point of view.
     * @return angle of the point of view
     */
    public double getAnglecameraY() {
        return anglecameraY;
    }

    /**
     * Give the angle of the point of view.
     * @return angle of the point of view
     */
    public double getAnglecameraZ() {
        return anglecameraZ;
    }

    /**
     * Update the angle of the point of view.
     * @param anglecameraX angle of the point of view
     */
    public void setAnglecameraX(double anglecameraX) {
        this.anglecameraX = anglecameraX;
    }

    /**
     * Update the angle of the point of view.
     * @param anglecameraY angle of the point of view
     */
    public void setAnglecameraY(double anglecameraY) {
        this.anglecameraY = anglecameraY;
    }

    /**
     * Update the angle of the point of view.
     * @param anglecameraZ angle of the point of view
     */
    public void setAnglecameraZ(double anglecameraZ) {
        this.anglecameraZ = anglecameraZ;
    }

}
