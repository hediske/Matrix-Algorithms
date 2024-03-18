package com.matrix.Factory;

public interface IMatrixFactory {

    double[][]  generateRandom(int size);
    double[][] generateRandomvalid(int size);
    double[][] generateRandomSDP(int size);
    double[][] generateRandomTriangle(int size);
    double[]   generateConstant(int n);
}
