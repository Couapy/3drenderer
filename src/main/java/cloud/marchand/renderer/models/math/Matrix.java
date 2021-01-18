package cloud.marchand.renderer.models.math;

/**
 * Represent a matrix.
 */
public class Matrix {

    /**
     * Double matrix
     */
    protected double[][] matrix;

    /**
     * Create a default matrix with 1x1 size.
     */
    public Matrix() {
        this(1, 1);
    }
    
    /**
     * Create a matrix with parameterized sizes.
     * @param rows number of rows
     * @param cols number of columns
     */
    public Matrix(int rows, int cols) {
        this.matrix = new double[rows][cols];
    }

    /**
     * Create a matrix from an existing one.
     * @param matrix existant matrix
     */
    public Matrix(double[][] matrix) {
        this.matrix = matrix;
    }

    /**
     * Matrix multiplication operation.
     * @param matrixA matrix on the left
     * @param matrixB matrix on the right
     * @return result of the multiplication of matrixA by matrixB
     */
    public static double[][] multiply(double[][] matrixA, double[][] matrixB) {
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

    /**
     * Multiplicate this matrix with another.
     * @param matrix matrix to multiply with
     * @return result of the multiplication of this matrix by matrix parameterized
     */
    public double[][] multiply(double[][] matrix) {
        return multiply(matrix, this.matrix);
    }

    /**
     * Give the matrix
     * @return the matrix
     */
    public double[][] getMatrix() {
        return matrix;
    }

    /**
     * Give a readable description of the matrix.
     * @return readable string
     */
    public String toString() {
        String result = "";
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[0].length; y++) {
                result += String.format("%02.2f", matrix[x][y]) + " ";
            }
            result += "\n";
        }
        return result.substring(0, result.length()-1);
    }

}
