package cloud.marchand.renderer.models.math;

public class Matrix {

    protected double[][] matrix;
    protected int rows;
    protected int cols;

    public Matrix() {
        this(1, 1);
    }
    
    public Matrix(int rows, int cols) {
        this.matrix = new double[rows][cols];
        this.rows = rows;
        this.cols = cols;
    }

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
        this.rows = matrix.length;
        this.cols = matrix[0].length;
    }

    protected static double[][] multiply(double[][] matrixA, double[][] matrixB) {
        if (matrixA[0].length != matrixB.length) {
            return null;
        }
        double[][] result = new double[matrixA.length][matrixB[0].length];
        for (int x = 0; x < result.length; x++) {
            for (int y = 0; y < result[0].length; y++) {
                double sum = 0d;
                for (int k = 0; k < matrixA[0].length; k++) {
                    sum += matrixA[x][k] * matrixB[k][y];
                }
                result[x][y] = sum;
            }
        }
        return result;
    }

    public double[][] multiply(double[][] matrix) {
        return multiply(matrix, this.matrix);
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void set(Matrix m) {
        matrix = m.getMatrix();
        m.rows = m.getRows();
        m.cols = m.getCols();
    }

    public String toString() {
        String result = "";
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                result += String.format("%02.2f", matrix[x][y]) + " ";
            }
            result += "\n";
        }
        return result.substring(0, result.length()-1);
    }

}
