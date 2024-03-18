package com.matrix.Strategy;

public class LU implements IStrategy {

    @Override
    public double[] findX(double[][] a, double[] b) 
    {
        int n = a.length;
        double[][] L = new double[n][n];
        double[][] U = new double[n][n];
        try{
            MatriceFunctions.luDecomposition(a, L, U);
        }catch(RuntimeException e){
            System.out.println("Error in the generation of L and U  !  A zero division detected ");
            return null;
        
        }
        double[] y = MatriceFunctions.forward(L, b);
        return (MatriceFunctions.backward(U, y));
    }   

    
}
