package com.matrix.Strategy;


import java.util.stream.IntStream;
public class Jacobi implements IStrategy {

    @Override
    public double[] findX(double[][] a, double[] b) {
        int n = b.length;
        double[] x = new double[n];
        double[] y = x.clone();
        final double epsilon = 1e-6;
        
        while (true) {
            y = IntStream.range(0, n)
                         .parallel()
                         .mapToDouble(i -> calculateNewX(i, a, b, x))
                         .toArray();
            
            if (MatriceFunctions.norm(MatriceFunctions.minus(y, x)) < epsilon) {
                System.arraycopy(y, 0, x, 0, n); 
                break;
            }
            
            System.arraycopy(y, 0, x, 0, n);
            
            for (double num : x) {
                if (Double.isInfinite(num)) {
                    System.out.println("Array contains Infinity. Exiting Jacobi with no solution !.");
                    return null;
                }
            }
        }
        return x;
    }
    
    private double calculateNewX(int i, double[][] a, double[] b, double[] x) {
        int n = b.length;
        double sum = 0.0;
        for (int j = 0; j < n; j++) {
            if (j != i) {
                sum += a[i][j] * x[j];
            }
        }
        return (b[i] - sum) / a[i][i];
    }
}
