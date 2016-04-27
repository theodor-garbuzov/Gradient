package com;

public class Main {

    public static void main(String[] args) {
        //double[] x0 = new double[]{-100, -50}; // start point
        double[] x = new double[]{-100, -50};
        int iCount;
        double eps = 0.001; // exit parameter of gradient method
        double eps2 = 0.1; // parameter of one-dimensional optimization
        int[] fCount = new int[]{0};

        // Search for the best parameter eps2 of one-dimensional optimization for each eps
        System.out.println("ЭПСИЛОН " + eps);
        iCount = GradientDescent.Optimize(x, FunctionSet::f, FunctionSet::gradf, eps, eps2, fCount);
        System.out.println("Параметр ОМ: " + eps2);
        System.out.println("Количество шагов: " + iCount);
        System.out.println("Точка минимума: " + x[0] + " " + x[1]);
        System.out.println(fCount[0]);
        //System.arraycopy(x0, 0, x, 0, x.length);
    }
}
