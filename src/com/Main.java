package com;

public class Main {

    public static void main(String[] args) {
        double[] x0 = new double[]{-100, -50}; // start point
        double[] x = new double[]{-100, -50};
        int iCount;
        double eps; // exit parameter of gradient method
        double eps2; // parameter of one-dimensional optimization

        // Search for the best parameter eps2 of one-dimensional optimization for each eps
        for (eps = 0.1; eps >= 0.001; eps /= 10) {
            System.out.print("ЭПСИЛОН ");
            System.out.println(eps);
            eps2 = 0.001;
            for (int i = 0; i <= 0; ++i) {
                iCount = GradientDescent.Optimize(x, FunctionSet::f, FunctionSet::gradf, eps, eps2);
                System.out.print("Параметр ОМ: ");
                System.out.println(eps2);
                System.out.print("Количество шагов: ");
                System.out.println(iCount);
                System.out.print("Точка минимума: ");
                System.out.print(x[0]); System.out.print(" "); System.out.println(x[1]);
                eps2 += 0.01;
                System.arraycopy(x0, 0, x, 0, x.length);
            }
            System.out.println();
        }
    }
}
