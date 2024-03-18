package com.matrix.Strategy;

public class Choelsky implements IStrategy{

    @Override
    public double[] findX(double[][] a, double[] b) {
            double[][] L = MatriceFunctions.cholesky(a);
            double[] y = MatriceFunctions.forward(L, b);
            double[][] LT=MatriceFunctions.transpose(L);
            return (MatriceFunctions.backward(LT, y));
    }
    
}
