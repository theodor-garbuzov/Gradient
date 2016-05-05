package com;

public class Main {

    public static void main(String[] args) {
        //double[] x0 = new double[]{-100, -50};
        double[] x = new double[]{1, 1}; // start point
        double eps = 0.00001;            // exit parameter of gradient method
        double eps1 = 0.008;             // parameter of one-dimensional optimization
        int[] fCount = new int[]{0};     // number of function calculations
        int iterations;

        System.out.println("ЭПСИЛОН " + eps);
        System.out.println("Параметр ОМ: " + eps1);
        iterations = GradientDescent.Optimize(x, FunctionSet::f, FunctionSet::gradf2, eps, eps1, fCount);
        System.out.println("\nКоличество шагов: " + iterations);
        System.out.println("Точка минимума: " + x[0] + " " + x[1]);
        System.out.println("Количество вычислений функции: " + fCount[0]);
        //System.arraycopy(x0, 0, x, 0, x.length);
    }
}
