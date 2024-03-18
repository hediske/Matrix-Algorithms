package com.matrix.Strategy;

import java.util.Arrays;

public class GaussSeidel implements IStrategy {

    @Override
    public double[] findX(double[][] a, double[] b) {
        int n = b.length;
        double[] x = new double[n];
        final double epsilon = 1e-6;
        while (true) {
           double[] oldX=Arrays.copyOf(x, n);
            for (int i = 0; i < n; i++) {
                double sum = 0.0;
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        sum += a[i][j] * x[j];
                    }
                }
                x[i] = (b[i] - sum) / a[i][i];
            }
            if(MatriceFunctions.norm(MatriceFunctions.minus(oldX, x))<epsilon)
                break;
            for (double num : x) {
                if (Double.isInfinite(num)) {
                    System.out.println("Array contains Infinity. Exiting Gauss Seidel with no solution !.");
                    return null;
                }
            }
        }
        return x;
    }
}
