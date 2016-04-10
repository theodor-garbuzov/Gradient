package com;

/**
 * Set of functions and their gradients
 */
public class FunctionSet {
    static double a = 1;
    static double b = 2;
    static double c = 3;

    public static double f(double x[])
    {
        return x[0]*x[0] + a*x[1]*x[1] + Math.exp(b*x[0] + c*x[1]) - x[0] + 2*x[1];
    }
    public static double[] gradf(double[] x)
    {
        double g1 = 2*x[0] + b*Math.exp(b*x[0]) - 1;
        double g2 = 2*a*x[1] + c*Math.exp(c*x[1]) + 2;
        return new double[]{g1, g2};
    }

    public static double g(double[] x)
    {
        return a*x[0] + x[1] + 4*Math.sqrt(1 + b*x[0]*x[0] + c*x[1]*x[1]);
    }
    public static double[] gradg(double[] x)
    {
        double g1 = a + 4*b*x[0] / (Math.sqrt(1 + b*x[0]*x[0] + c*x[1]*x[1]));
        double g2 = 1 + 4*c*x[1] / (Math.sqrt(1 + b*x[0]*x[0] + c*x[1]*x[1]));
        return new double[]{g1, g2};
    }
}
