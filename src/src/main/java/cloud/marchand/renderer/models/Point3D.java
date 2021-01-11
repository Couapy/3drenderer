package cloud.marchand.renderer.models;

public class Point3D {

    private double x;
    private double y;
    private double z;

    public Point3D(double x, double y, double z) {
        this.setX(x);
        this.setY(y);
        this.setZ(z);
    }

    public void translate(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
    }

    public void rotate(Point3D origine, double rotationX, double rotationY, double rotationZ) {
        rotateX(origine, rotationX);
        rotateY(origine, rotationY);
        rotateZ(origine, rotationZ);
    }

    public void rotateX(Point3D origine, double rotationX) {
        double y = this.y * Math.cos(rotationX) - this.z * Math.sin(rotationX);
        double z = this.y * Math.sin(rotationX) + this.z * Math.cos(rotationX);
        this.y = y;
        this.z = z;
    }

    public void rotateY(Point3D origine, double rotationY) {
        double x = this.x * Math.cos(rotationY) - this.z * Math.sin(rotationY);
        double z = this.x * Math.sin(rotationY) + this.z * Math.cos(rotationY);
        this.x = x;
        this.z = z;
    }

    public void rotateZ(Point3D origine, double rotationZ) {
        double x = this.x * Math.cos(rotationZ) - this.y * Math.sin(rotationZ);
        double y = this.x * Math.sin(rotationZ) + this.y * Math.cos(rotationZ);
        this.x = x;
        this.y = y;
    }

    public double distance(Point3D point) {
        return Math.sqrt(
            Math.pow(point.getX() - x, 2) +
            Math.pow(point.getY() - y, 2) +
            Math.pow(point.getZ() - z, 2)
        );    
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getY() {
        return z;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public String toString() {
        return String.format("(%.2f, %.2f, %.2f)", x, y, z);
    }
    
}
