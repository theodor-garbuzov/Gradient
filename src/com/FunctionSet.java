package com;

import static com.VectorOperations.MatrixVectorMult;

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
        double[] g = new double[x.length];
        g[0] = 2*x[0] + b*Math.exp(b*x[0] + c*x[1]) - 1;
        g[1] = 2*a*x[1] + c*Math.exp(b*x[0] + c*x[1]) + 2;
        return g;
    }

    // gradient multiplied by matrix F
    public static double[] gradf2(double[] x)
    {
        double[] g = new double[x.length];
        double angle = Math.PI / 18; // 10 degrees
        g[0] = 2*x[0] + b*Math.exp(b*x[0] + c*x[1]) - 1;
        g[1] = 2*a*x[1] + c*Math.exp(b*x[0] + c*x[1]) + 2;
        double[][] F = new double[][]{{Math.cos(angle)*Math.cos(angle) + 1, -Math.sin(angle)*Math.cos(angle)},
                                      {-Math.sin(angle)*Math.cos(angle), Math.sin(angle)*Math.sin(angle) + 1}};
        return MatrixVectorMult(F, g);
    }

    public static double g(double[] x)
    {
        return a*x[0] + x[1] + 4*Math.sqrt(1 + b*x[0]*x[0] + c*x[1]*x[1]);
    }
    public static double[] gradg(double[] x)
    {
        double[] g = new double[x.length];
        g[0] = a + 4*b*x[0] / (Math.sqrt(1 + b*x[0]*x[0] + c*x[1]*x[1]));
        g[1] = 1 + 4*c*x[1] / (Math.sqrt(1 + b*x[0]*x[0] + c*x[1]*x[1]));
        return g;
    }
}
