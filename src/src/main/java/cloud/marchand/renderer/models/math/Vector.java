package cloud.marchand.renderer.models.math;

public class Vector extends Matrix {

    public Vector() {
        this(1);
    }

    public Vector(int rows) {
        super(rows, 1);
    }

    public Vector(double[] vector) {
        super(vectorToMatrix(vector));
    }

    private static double[][] vectorToMatrix(double[] vector) {
        double[][] matrix = new double[vector.length][1];
        for (int i = 0; i < vector.length; i++) {
            matrix[i][0] = vector[i];
        }
        return matrix;
    }
    
}
