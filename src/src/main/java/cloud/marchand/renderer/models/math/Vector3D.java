package cloud.marchand.renderer.models.math;

public class Vector3D extends Matrix {

    public Vector3D() {
        this(0, 0, 0);
    }

    public Vector3D(double x, double y, double z) {
        super(coordinatesToMatrix(x, y, z));
    }

    public Vector3D(double[] vector) {
        super(vectorToMatrix(vector));
    }

    private static double[][] coordinatesToMatrix(double x, double y, double z) {
        double vector[][] = { { x }, { y }, { z } };
        return vector;
    }

    private static double[][] vectorToMatrix(double[] vector) {
        double[][] matrix = new double[vector.length][1];
        for (int i = 0; i < vector.length; i++) {
            matrix[i][0] = vector[i];
        }
        return matrix;
    }

    public void translate(double translationX, double translationY, double translationZ) {
        setX(getX() + translationX);
        setY(getY() + translationY);
        setZ(getZ() + translationZ);
    }

    public void rotate(Vector3D origin, double rotationX, double rotationY, double rotationZ) {
        double matrix[][] = { { getX() - origin.getX() }, { getY() - origin.getY() }, { getZ() - origin.getZ() }, };
        double result[][] = matrix;

        result[0][0] -= origin.getX();
        result[1][0] -= origin.getY();
        result[2][0] -= origin.getZ();

        if (rotationX != 0d) {
            double rotXmatrix[][] = {
                {1d, 0d, 0d},
                {0d, Math.cos(rotationX), -1d * Math.sin(rotationX)},
                {0d, Math.sin(rotationX),  Math.sin(rotationX)}
            };
            result = multiply(rotXmatrix, result);
        }
        if (rotationY != 0d) {
            double rotYmatrix[][] = {
                {Math.cos(rotationY), 0d, Math.sin(rotationY)},
                {0d, 1d, 0d},
                {-1d * Math.sin(rotationY), 0d,  Math.cos(rotationY)}
            };
            result = multiply(rotYmatrix, result);
        }
        if (rotationZ != 0d) {
            double rotZmatrix[][] = {
                {Math.cos(rotationZ), -1d * Math.sin(rotationZ), 0d},
                {Math.sin(rotationZ),  Math.sin(rotationZ), 0d},
                {0d, 0d, 1d}
            };
            result = multiply(rotZmatrix, result);
        }

        result[0][0] += origin.getX();
        result[1][0] += origin.getY();
        result[2][0] += origin.getZ();

        setX(result[0][0]);
        setY(result[1][0]);
        setZ(result[2][0]);
    }

    public double distance(Vector3D vector) {
        return Math.sqrt(
            Math.pow(getX() - vector.getX(), 2) + 
            Math.pow(getY() - vector.getY(), 2) + 
            Math.pow(getZ() - vector.getZ(), 2)
        );
    }

    public double getX() {
        return matrix[0][0];
    }

    public double getY() {
        return matrix[1][0];
    }

    public double getZ() {
        return matrix[2][0];
    }

    public void setX(double x) {
        matrix[0][0] = x;
    }

    public void setY(double y) {
        matrix[1][0] = y;
    }

    public void setZ(double z) {
        matrix[2][0] = z;
    }

    public String toString() {
        return String.format("(%02.2f, %02.2f, %02.2f)", matrix[0][0], matrix[1][0], matrix[2][0]);
    }
}
