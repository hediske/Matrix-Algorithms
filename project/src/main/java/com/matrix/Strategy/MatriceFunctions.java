package com.matrix.Strategy;

import java.util.Arrays;

import javax.management.RuntimeErrorException;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
public final class MatriceFunctions {

    public static double norm(double[] numbers){
        return Math.sqrt(Arrays.stream(numbers).parallel().reduce(0.0, (accum,curr)-> accum+curr*curr));
    } 

    public static double[] matriceArithOperation(double [] x ,double [] y,char op ){
        if(x.length!=y.length)
            return null ;
        int n = x.length;
        double[] z = new double[n];
        switch (op) {
            case '+' -> { for(int i =0; i<n;i++){    z[i]= x[i]+y[i];}  }
            case '*' -> { for(int i =0; i<n;i++){    z[i]= x[i]*y[i];}  }
            case '-' -> { for(int i =0; i<n;i++){    z[i]= x[i]-y[i];}  }
        }

        return z;
    }

    public static double[] add(double [] x ,double [] y){
        return matriceArithOperation(x,y,'+');
    }
   
    public static double[] minus(double [] x ,double [] y){
        return matriceArithOperation(x,y,'-');
    }
    
    public static double[] mult(double [] x ,double [] y){
        return matriceArithOperation(x,y,'*');
    }

    public static void  print(double[][] a) {
        if(a ==null)
        {
            System.out.println("error in the matrice");
            return;
        }
        int rows = a.length;
        int colms = a[0].length;
        for(int i =0 ; i < rows ;i++){
            System.out.print("| ");
            for (int j=0 ; j < colms ; j++)
                {   
                    System.out.print(String.format("%.3f", a[i][j]));
                    System.out.print(" ");
                }
            System.out.print("|");
            System.out.println();
        }
    }

 
    public static boolean isLowerTriangleMatrix(double[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (matrix[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isUpperTriangleMatrix(double [][] matrix){
        int n = matrix.length;
        for (int i = 0 ; i<n ; i++){
            for (int j =i+1 ; j<n ; j++){
                if(matrix[i][j]!=0){
                    return false;
                }
            }
        }
        return true;

    }
 
    public static void triangulize(double [][] a)
    {
        int n = a.length;
        for (int k =0 ; k<n ;k++){
            int pivot = k;
            for (int j = k + 1; j < n; j++) {//find the pivot as in the method gauss
                if (Math.abs(a[j][k]) > Math.abs(a[pivot][k])) {
                    pivot = j;
                }
            }

            if(k!=pivot){
                double[] temp =  a[k];
                a[k]=a[pivot];
                a[pivot]=temp;
                  
            }
            for (int i = k + 1; i < n; i++) {
                double factor = a[i][k] / a[k][k];
                for (int j = i; j < n; j++) {
                    a[i][j] -= factor * a[k][j];
                }

            }
        }
    
    }


    public static void triangulize(double [][] a,double [] b) throws RuntimeException
    {
        int n = a.length;
        for (int k =0 ; k<n ;k++){
            int pivot = k;
            for (int j = k + 1; j < n; j++) {
                if (Math.abs(a[j][k]) > Math.abs(a[pivot][k])) {
                    pivot = j;
                }
            }

            if(k!=pivot){
                double[] temp =  a[k];
                a[k]=a[pivot];
                a[pivot]=temp;
                double temp2 = b[k];
                b[k]=b[pivot];
                b[pivot]=temp2;
                  
            }
            for (int i = k + 1; i < n; i++) {
                if(a[k][k]==0){
                    throw new RuntimeException("Division By Zero occured");
                }
                double factor = a[i][k] / a[k][k];
                for (int j = k; j < n; j++) {
                    a[i][j] -= factor * a[k][j];
                }
                b[i] -= factor*b[k];

            }
        }
    }

    public static double[] backward(double[][] a , double[] b ){
        int n = b.length;
        double[] x = new double[n];
        for (int i = n-1 ; i>=0;i--){
            double sum=0.0;
            for(int j =i+1;j<n;j++){
                sum+=a[i][j]*x[j];
            }
            x[i] = (b[i] - sum) / a[i][i];
        }
        return x;
    }

    public static double[] forward(double[][] a , double[] b ){
        int n = b.length;
        double[] x = new double[n];
        for (int i = 0 ; i<n;i++){
            double sum=0.0;
            for(int j =0;j<i;j++){
                sum += a[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / a[i][i];
        }
        return x;
    }
    public static double[][] cholesky(double[][] A) {
        int n = A.length;
        double[][] L = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                double sum = 0;
                for (int k = 0; k < j; k++) {
                    sum += L[i][k] * L[j][k];
                }

                if (i == j) {
                    L[i][j] = Math.sqrt(A[i][i] - sum);
                } else {
                    L[i][j] = (1.0 / L[j][j]) * (A[i][j] - sum);
                }
            }
        }

        return L;
    }
    public static double[][] transpose(double[][] a){
        int n = a.length;
        double[][] result = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[j][i] = a[i][j];
            }
        }
        return result;

    }

    public static void luDecomposition(double[][] A, double[][] L, double[][] U) throws RuntimeException {
        int n = A.length;
        Long start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            L[i][i] = 1.0;
            System.arraycopy(A[i], 0, U[i], 0, n);
        }
        System.out.println(System.nanoTime()-start);
        for (int k = 0; k < n - 1; k++) {
            for (int i = k + 1; i < n; i++) {
                // if(U[k][k]==0) 
                //     throw new RuntimeException("zero division");
                L[i][k] = U[i][k] / U[k][k];
                for (int j = k; j < n; j++) {
                    U[i][j] -= L[i][k] * U[k][j];
                }
            }
        }
    }

    public static boolean isCarre(double [][] a){
        int rows = a.length;
        int colms = a[0].length;
        return rows==colms;
    }
    public static boolean isSingleton(double [][] a){
        int n = a.length;
        if(isCarre(a)){
            for(int i =0 ; i<n ; i++){
                if(a[i][i]==0.0)
                    return true;
            }
            return false;
        }
        return true;
    }

    public static boolean isDiagonallyDominant(double[][]a ){
        int n = a.length;
        if(isCarre(a)){
            for(int i =0 ; i<n ; i++){
                double diagonal = Math.abs(a[i][i]);
                double rowSum = 0.0;
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        rowSum += Math.abs(a[i][j]);
                    }
                }
                if (diagonal <= rowSum) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    public static boolean isSymmetric(double [][] a) {
        if(isCarre(a)){
            int n = a.length;
            for (int i =0 ; i<n; i++){
                for (int j=0; j<n ; j++){
                    if (a[i][j] != a[j][i]) {
                        return false; 
                    }
                }
            }   
            return true;
        }
        return false;
    }

    public static boolean isPositiveDefinite(double[][] a) {
        if (!isSymmetric(a)) {
            return false;
        }
        EigenDecomposition eigenDecomposition = new EigenDecomposition(new Array2DRowRealMatrix(a));
        double[] eigenvalues = eigenDecomposition.getRealEigenvalues();
        for (double eigenvalue : eigenvalues) {
            if (eigenvalue <= 0) {
                return false; 
            }
        }
        return true;
    }

    public static void printVector(double[] b){
        int n=b.length;
        for(int i=0;i<n;i++){
            System.out.println("| "+String.format("%.3f", b[i])+" |");

        }
    }


    public static double[][] copy( double [][] a){
        double[][] aCopy = new double[a.length][a[0].length];
            for (int i = 0; i < a.length; i++) {
                System.arraycopy(a[i], 0, aCopy[i], 0, a[i].length);
            }
        return aCopy;
    }

    public static double[] copy( double [] b){
        double[] bCopy = new double[b.length];
            System.arraycopy(b, 0, bCopy, 0, b.length);
        return bCopy;
    }




}
