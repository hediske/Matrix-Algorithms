package com.matrix.Strategy;

public class Gauss implements IStrategy {


    

    @Override
    public double[] findX(double[][] a, double[] b) {
        

        
        if(MatriceFunctions.isLowerTriangleMatrix(a)){
            return MatriceFunctions.forward(a,b);
        }
        if(!MatriceFunctions.isUpperTriangleMatrix(a)){
            try{
                MatriceFunctions.triangulize(a,b);
            }
            catch(RuntimeException e){
                System.out.println("Error in the trinagulisation of the matrix  !  A zero division detected ");
                return null;
            }

        }
            return MatriceFunctions.backward(a,b);
    }
    
}
