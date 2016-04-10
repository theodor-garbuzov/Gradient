package com;

public class GradientDescent {

    /**
     * Optimize function starting from a point x with provided accuracy
     * @param x - start point, at the end it will be optimum point
     * @param function - function to optimize
     * @param gradient - function's gradient
     * @param eps - accuracy
     */
    public static void Optimize(double[] x, Function function, Gradient gradient, double eps)
    {
        double[] x_new = new double[x.length];
        double[] grad = gradient.gradf(x);

        while (norm2(grad) > eps) {
            GetNewPointDichotomy(x, grad, x_new, function, eps);
            System.arraycopy(x_new, 0, x, 0, x_new.length);
            grad = gradient.gradf(x);
            System.out.print(x[0]); System.out.print(" "); System.out.println(x[1]);
        }

    }

    private static void GetNewPointDichotomy(double[] x, double[] grad, double[] x_new, Function function, double eps)
    {
        double lborder = 0, rborder = 10;
        double step1 = lborder, step2 = rborder, step;
        double delta = eps / 20;

        while (lborder - rborder > eps)
        {
            step1 = (lborder + rborder) / 2 - delta;
            step2 = (lborder + rborder) / 2 + delta;
            if (function.f(VectSum(x, NumVectMult(-step1, grad))) > function.f(VectSum(x, NumVectMult(-step1, grad))))
                lborder = step1;
            else
                rborder = step2;
        }
        step = (lborder + rborder) / 2;
        x_new = VectSum(x, NumVectMult(-step, grad));
    }

    private static void GetNewPointSplitting(double[] x, double[] grad, double[] x_new, Function function, double eps)
    {
        boolean exitCondition1, exitCondition2;
        double step = 2;
        do {
            step /= 2;
            for (int i = 0; i < x_new.length; ++i) {
                x_new[i] = x[i] - step * grad[i];
            }
            exitCondition1 = function.f(x_new) - function.f(x) < -eps * norm2(grad) * step;
            exitCondition2 = Math.abs(-eps * norm2(grad) * step) < Math.pow(10, -10);
        } while (!exitCondition1 && !exitCondition2);
    }

    /**
     * Vector sum
     * @param vect1 - vector 1
     * @param vect2 - vector 2
     * @return result vector
     */
    private static double[] VectSum(double[] vect1, double[] vect2)
    {
        assert(vect1.length == vect2.length);
        double[] result = new double[vect1.length];
        for (int i = 0; i < result.length; ++i)
            result[i] = vect1[i] + vect2[i];
        return result;
    }

    /**
     * Multiply vector by number
     * @param num - number
     * @param vect - vector
     * @return result vector
     */
    private static double[] NumVectMult(double num, double[] vect)
    {
        double[] result = new double[vect.length];
        for (int i = 0; i < result.length; ++i)
            result[i] = num * vect[i];
        return result;
    }

    /**
     * Norm of vector squared
     * @param vect - vector
     * @return norm of vector
     */
    private static double norm2(double[] vect)
    {
        double res = 0;
        for (double vectI : vect) res += vectI * vectI;
        return res;
    }
}
