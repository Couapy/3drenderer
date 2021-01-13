package cloud.marchand.renderer.models.math;

public class Matrix {

    private double[][] matrix;
    private int rows;
    private int cols;

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

    public Matrix multiply(Matrix m) {
        if (cols != m.getRows()) {
            System.out.println("Les matrices n'ont pas les mêmes tailles :");
            System.out.println("  - " + rows + "x" + cols);
            System.out.println("  - " + m.getRows() + "x" + m.getCols());
            return null;
        }
        double[][] matrixB = m.getMatrix();
        double[][] result = new double[rows][m.getCols()];
        for (int x = 0; x < result.length; x++) {
            for (int y = 0; y < result[0].length; y++) {
                double sum = 0d;
                for (int k = 0; k < cols; k++) {
                    sum += matrix[x][k] * matrixB[k][y];
                }
                result[x][y] = sum;
            }
        }
        return new Matrix(result);
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
