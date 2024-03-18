package com.matrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import com.matrix.Factory.MatrixFactory;
import com.matrix.Strategy.Context;
import com.matrix.Strategy.MatriceFunctions;
import com.matrix.Strategy.StrategyEnum;

public class App 
{
    public static void main( String[] args ) throws FileNotFoundException
    {   
        System.out.println("\n\n");
        System.out.println("Welcome !! Let's start Solving Linear Problems  ");
        System.out.println("--------------------------------------------------------------");
        System.out.println("\n\n");
        Scanner scanner = new Scanner(System.in);
        Context context = new Context();
        MatrixFactory matrixFactory = new MatrixFactory();
            Program : while(true){
                double[][] a =null ;
                double[] b = null ;
                char fdecis = prompt("Hello , do you want to solve a problem or try a random matrix ? y for solving a linear problem . N for random data ");
                if(fdecis=='y'){
                    char decision = prompt("Do you want to provide the file for matrix ? ");
                    HashMap <String,Object> map ;
                    if(decision=='y')  //from a file
                    {
                        System.out.println("---- File  : ");
                        System.out.println("Please provide the filename for the matrix data  : ");
                        String file="";
                        do{
                            file = scanner.nextLine();
                            if(file =="")
                            System.out.println("Please provide a valid filename  ! ");
                        }
                        while(file =="");
                        map=readMatrixFromSource(file);
                    }else{
                        System.out.println("---- User  : ");
                        map=readMatrixFromSource(null);
                    }
                    a=(double[][]) map.get("matrix");
                    b=(double[]) map.get("constants");
                    System.out.println("------------------ D A T A  L O A D E D 🆗 ------------------");
                    System.out.println("\n");
                }
                else {
                    System.out.println("Please provide the matrix size  : ");
                    int size = Integer.parseInt(scanner.nextLine());
                    System.out.println("How Do you want the matrix to be  ? : \n1:Random \n2Not Singular \n3Triangular \n4SDP");
                    String type = scanner.nextLine();
                    switch (type) {
                        case "1" ->a=matrixFactory.generateRandom(size);
                        default ->a=matrixFactory.generateRandom(size);
                        case "2"->a=matrixFactory.generateRandomvalid(size);
                        case "3"->a=matrixFactory.generateRandomTriangle(size);
                        case "4"->a=matrixFactory.generateRandomSDP(size);
                    }
                    b=matrixFactory.generateConstant(size);
                }
                while(true){
                    System.out.println("Matrice :");
                    // MatriceFunctions.print(a);
                    System.out.println("\n");
                    System.out.println("Constant :");
                    // MatriceFunctions.printVector(b);
                    System.out.println("\n");
                    System.out.println("--------------------------------------------------------------");
                    System.out.println("\nPlease provide the strategy to solve the problem  : \n1:Gauss \n2:GaussSeidel \n3:Jacobi \n4:LU \n5:Choelsky \n6:Best \nBy default :Gauss");
                    String strategy  =  scanner.nextLine();
                    switch (strategy) {
                        case "1" -> context.setStrategy(StrategyEnum.Gauss);
                        case "2" -> context.setStrategy(StrategyEnum.GaussSeidel);
                        case "3" -> context.setStrategy(StrategyEnum.Jacobi);
                        case "4" -> context.setStrategy(StrategyEnum.LU);
                        case "5" -> context.setStrategy(StrategyEnum.Choelsky);
                        case "6" -> context.setBestStrategy(a);
                        default -> context.setStrategy(StrategyEnum.Gauss);
                    }
                    System.out.println();
                    System.out.println("--------------------------------------------------------------");
                    System.out.println();

                    System.out.println("Using Strategy  :  "+context.gStrategy());
                    double[][] aCopy = MatriceFunctions.copy(a);
                    double[] bCopy = MatriceFunctions.copy(b);
                    long start = System.nanoTime();
                    double[] x = context.findSolution(aCopy , bCopy);
                    long end = System.nanoTime();
                    System.out.println();
                    System.out.println("--------------------------------------------------------------");
                    System.out.println();
                    if(x==null){
                        System.out.println("No Solution found");
                    }
                    else{
                        System.out.println("the solution is  : ");
                        // MatriceFunctions.printVector(x);
                    }
                    System.out.println("  ----  Exection time  ----  : "+(end-start));
                    System.out.println();
                    System.out.println("--------------------------------------------------------------");
                    System.out.println();                
                    System.out.println();
                    System.out.println();
                    char c = prompt("Do you want to continue  ? ");
                    if(c=='N')
                        break Program;
                    c = prompt("Do you want to load data again ? ");
                    if(c=='y')
                        break;
                                        
                }
        }
        scanner.close();
    }



    public static HashMap<String,Object> readMatrixFromSource(String filename) throws FileNotFoundException {
        Scanner scanner ;
        int rows ;
        int cols;
        if(filename==null){
            scanner= new Scanner(System.in);
            System.out.println("Enter the number of rows:");
            rows = scanner.nextInt();
            System.out.println("Enter the number of columns:");
            cols = scanner.nextInt();
            
        }
        else
        {
            scanner= new Scanner(new File("src/main/resources/"+filename));
            rows = scanner.nextInt();
            cols = scanner.nextInt();
            scanner.nextLine();
        }
 
        double[][] a = new double[rows][cols];
        double[] b = new double[rows];

        System.out.println("Getting the matrix data:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String s= scanner.next();
                a[i][j] = Double.parseDouble(s);
            }
        }
        System.out.println("Getting the data of the constant b");
        for (int i = 0; i < rows; i++) {
            String s= scanner.next();
            b[i] = Double.parseDouble(s);
        }

        HashMap<String, Object> result = new HashMap<>();
        result.put("matrix", a);
        result.put("constants", b);
        return result;

    }

    public static char prompt(String string){
        System.out.println(string);
        boolean nodecision=false;
        char decision = '0' ;
        Scanner scanner =new Scanner(System.in);
        while(!nodecision)
        {
            System.out.println("y/N : ");
            String str = scanner.nextLine();
            decision = (str=="") ? 'e' : str.charAt(0);
            if(decision =='y' || decision == 'N')  
                nodecision=true;
        }

        return decision;
    }

}
