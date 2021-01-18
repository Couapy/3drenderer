package cloud.marchand.renderer.models.math;

/**
 * Represent a 3D vector.
 */
public class Vector3D extends Matrix {

    /**
     * Create a vector.
     */
    public Vector3D() {
        this(0, 0, 0);
    }

    /**
     * Create a vector with coordinates.
     * @param x x composant
     * @param y y composant
     * @param z z composant
     */
    public Vector3D(double x, double y, double z) {
        super(coordinatesToMatrix(x, y, z));
    }

    /**
     * Create a vector from a matrix.
     * @param vector array vector
     */
    public Vector3D(double[] vector) {
        super(vectorToMatrix(vector));
    }

    /**
     * Convert coordinates to matrix.
     * @param x x composant
     * @param y y composant
     * @param z z composant
     * @return array of composants
     */
    private static double[][] coordinatesToMatrix(double x, double y, double z) {
        double vector[][] = { { x }, { y }, { z } };
        return vector;
    }

    /**
     * Convert a vector to a Matrix.
     * @param vector vertor to convert
     * @return a matrix based on vector
     */
    private static double[][] vectorToMatrix(double[] vector) {
        double[][] matrix = new double[vector.length][1];
        for (int i = 0; i < vector.length; i++) {
            matrix[i][0] = vector[i];
        }
        return matrix;
    }

    /**
     * Translate the vector.
     * @param translationX increase x of translationX
     * @param translationY increase y of translationY
     * @param translationZ increase z of translationZ
     */
    public void translate(double translationX, double translationY, double translationZ) {
        setX(getX() + translationX);
        setY(getY() + translationY);
        setZ(getZ() + translationZ);
    }

    /**
     * Rotate the point around an origin.
     * @param origin center of the rotation
     * @param rotationX radians angle to rotate the point by x
     * @param rotationY radians angle to rotate the point by y
     * @param rotationZ radians angle to rotate the point by z
     */
    public void rotate(Vector3D origin, double rotationX, double rotationY, double rotationZ) {
        translate(-origin.getX(), -origin.getY(), -origin.getZ());

        if (rotationX != 0d) {
            double rotXmatrix[][] = {
                {1d, 0d, 0d},
                {0d, Math.cos(rotationX), -Math.sin(rotationX)},
                {0d, Math.sin(rotationX),  Math.cos(rotationX)}
            };
            matrix = multiply(rotXmatrix, matrix);
        }
        if (rotationY != 0d) {
            double rotYmatrix[][] = {
                {Math.cos(rotationY), 0d, Math.sin(rotationY)},
                {0d, 1d, 0d},
                {-1d * Math.sin(rotationY), 0d,  Math.cos(rotationY)}
            };
            matrix = multiply(rotYmatrix, matrix);
        }
        if (rotationZ != 0d) {
            double rotZmatrix[][] = {
                {Math.cos(rotationZ), -Math.sin(rotationZ), 0d},
                {Math.sin(rotationZ),  Math.cos(rotationZ), 0d},
                {0d, 0d, 1d}
            };
            matrix = multiply(rotZmatrix, matrix);
        }

        translate(origin.getX(), origin.getY(), origin.getZ());
    }

    /**
     * Scale the vector size
     * @param scale size factor
     */
    public void scale(double scale) {
        matrix[0][0] *= scale;
        matrix[1][0] *= scale;
        matrix[2][0] *= scale;
    }

    /**
     * Calculate distance between this vector and another.
     * @param vector other vector to calculate the distance between them
     * @return the distance between them
     */
    public double distance(Vector3D vector) {
        return Math.sqrt(
            Math.pow(getX() - vector.getX(), 2) + 
            Math.pow(getY() - vector.getY(), 2) + 
            Math.pow(getZ() - vector.getZ(), 2)
        );
    }

    /**
     * Give the length of the vector.
     * @return the length of the vector
     */
	public double length() {
		return this.distance(new Vector3D(0, 0, 0));
    }

    /**
     * Give the value x of the vector.
     * @return x composant
     */
    public double getX() {
        return matrix[0][0];
    }

    /**
     * Give the value y of the vector.
     * @return y composant
     */
    public double getY() {
        return matrix[1][0];
    }

    /**
     * Give the value z of the vector.
     * @return z composant
     */
    public double getZ() {
        return matrix[2][0];
    }

    /**
     * Defines the x composant of the vector.
     * @param x x composant of the vector
     */
    public void setX(double x) {
        matrix[0][0] = x;
    }

    /**
     * Defines the y composant of the vector.
     * @param y y composant of the vector
     */
    public void setY(double y) {
        matrix[1][0] = y;
    }

    /**
     * Defines the z composant of the vector.
     * @param z z composant of the vector
     */
    public void setZ(double z) {
        matrix[2][0] = z;
    }

    public boolean equals(Vector3D vector) {
        if (matrix[0][0] == vector.getX() && matrix[1][0] == vector.getY() && matrix[2][0] == vector.getZ()) {
            return true;
        }
        return false;
    }

    /**
     * Give a readable description of the vector.
     * @return readable string
     */
    public String toString() {
        return String.format("(%02.2f, %02.2f, %02.2f)", matrix[0][0], matrix[1][0], matrix[2][0]);
    }

}
