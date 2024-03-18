package com.matrix.Factory;

import java.util.Random;

public class MatrixFactory implements IMatrixFactory {

    @Override
    public double[][] generateRandom(int n) {
        double[][] A = new double[n][n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = random.nextDouble();
            }
        }
        return A;
    }

    @Override
    public double[][] generateRandomvalid(int n) {
        double[][] A = this.generateRandom(n);
        for (int i = 0 ; i<n; i++){
            A[i][i] += 1.0;
        }
        return A;
    }

    @Override
    public double[][] generateRandomSDP(int n) {
        double[][] A = new double[n][n];
        Random random = new Random();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = random.nextDouble();
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                A[i][j] = A[j][i] = 0.5 * (A[i][j] + A[j][i]);
            }
        }

        for (int i = 0; i < n; i++) {
            A[i][i] += n;
        }
        
        return A;
    }
    

    @Override
    public double[][] generateRandomTriangle(int n) {
        double[][]A = new double[n][n];
        Random random = new Random();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                A[i][j] = random.nextDouble();
            }
        }
        
        for (int i = 0; i < n; i++) {
            A[i][i] += 1.0;
        }
        return A;
    }

    @Override
    public double[] generateConstant(int n) {
        double[] B = new double[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            B[i] = 100*random.nextDouble();
        }
        return B;
    }
    
    
}