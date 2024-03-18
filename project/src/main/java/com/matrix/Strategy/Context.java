package com.matrix.Strategy;


public class Context {
    private final IStrategy DEFAULTSTRATEGY= new Gauss();
    private IStrategy strategy;
    private StrategyEnum strategyType = StrategyEnum.Gauss;
     
    public StrategyEnum gStrategy(){
        return this.strategyType;
    }
    public void setStrategy(IStrategy strategy) {
        this.strategy = strategy;
    }

    public Context(IStrategy strategy) {
        this.strategy = strategy;
        this.strategyType=StrategyEnum.Gauss;
    }
    public Context() {
        this.strategy = DEFAULTSTRATEGY;
    }

    public void setStrategy(StrategyEnum strategy){
        this.strategyType=strategy;
        switch(strategy)
            {
            case Choelsky->
                this.strategy=new Choelsky();
            case Gauss->
                this.strategy=new Gauss();
            case GaussSeidel->
                this.strategy=new GaussSeidel();
            case Jacobi->
                this.strategy=new Jacobi();
            case LU->
                this.strategy=new LU();
            default->
                this.strategy=DEFAULTSTRATEGY;
            }

    }

    public double[] findSolution(double[][] a , double[] b ){
        double[] x =null;
        if(MatriceFunctions.isSingleton(a))
            System.out.println("Error ! we can not find a solution  ! this matrice is a Singleton  ! "+
            "\n  finding a solution is not stable by the methods Gauss, GS , Jacobi , LU and Choelsky ");
        else
        {
            x=this.strategy.findX(a,b) ;
        }
        return x;
    }
    


    public void setBestStrategy(double [][] a){
        IStrategy bestStrategy =DEFAULTSTRATEGY;
        if (MatriceFunctions.isPositiveDefinite(a)) {
            this.strategyType=StrategyEnum.Choelsky;
            bestStrategy = new Choelsky();
        } else if (MatriceFunctions.isDiagonallyDominant(a)) {
            this.strategyType=StrategyEnum.GaussSeidel;
            bestStrategy = new GaussSeidel();
        } 
        setStrategy(bestStrategy);
    }


}