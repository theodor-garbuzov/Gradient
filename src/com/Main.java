package com;

public class Main {

    public static void main(String[] args) {
        double[] x = new double[]{0, 0};
        int iCount = GradientDescent.Optimize(x, FunctionSet::f, FunctionSet::gradf, 0.01);
        System.out.print("Result: ");
        System.out.print(x[0]); System.out.print(" "); System.out.println(x[1]);
        System.out.println(iCount);
    }
}
