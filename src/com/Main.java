package com;

public class Main {

    public static void main(String[] args) {
        //double[] x0 = new double[]{-100, -50}; // start point
        double[] x = new double[]{-100, -50};
        int iCount;
        double eps = 0.001; // exit parameter of gradient method
        double eps2 = 0.001; // parameter of one-dimensional optimization
        Integer fCount = 0;

        // Search for the best parameter eps2 of one-dimensional optimization for each eps
        System.out.print("ЭПСИЛОН ");
        System.out.println(eps);
        eps2 = 0.001;
        iCount = GradientDescent.Optimize(x, FunctionSet::f, FunctionSet::gradf, eps, eps2, fCount);
        System.out.print("Параметр ОМ: ");
        System.out.println(eps2);
        System.out.print("Количество шагов: ");
        System.out.println(iCount);
        System.out.print("Точка минимума: ");
        System.out.print(x[0]); System.out.print(" "); System.out.println(x[1]);
        System.out.println(fCount);
        //System.arraycopy(x0, 0, x, 0, x.length);
    }
}
