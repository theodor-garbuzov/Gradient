package com;

public class GradientDescent {

    /**
     * Optimize function starting from a point x with provided exit parameter
     *
     * @param x        - start point, at the end it will be optimum point
     * @param function - function to optimize
     * @param gradient - function's gradient
     * @param eps      - exit parameter
     * @param eps2     - exit parameter of one-dimensional optimization
     * @return iCount -  number of method iterations
     */
    public static int Optimize(double[] x, Function function, Gradient gradient, double eps, double eps2, Integer fCount) {
        double[] x_new = new double[x.length];
        double[] grad = gradient.gradf(x);
        double[] x1 = new double[x.length], x2 = new double[x.length]; // step vectors
        double step;
        int iCount = 0;
        
        while (norm2(grad) > eps) {
            // get new point x_new = x - step * grad
            step = GetNewStepDichotomy(x, grad, x_new, function, eps2);
            System.arraycopy(VectorSum(x, NumberVectorMult(-step, grad)), 0, x_new, 0, x_new.length);
            
            // x2 = x1
            // x1 = x_new - x
            System.arraycopy(x1, 0, x2, 0, x.length);
            x1 = VectorSum(x_new, NumberVectorMult(-1, x));
            // print x1 * x2
            if (iCount > 0) {
                System.out.print("(x_{k+1} - x_k) * (x_k - x_{k-1}) = ");
                System.out.println(VectorVectorMult(x1, x2)); // x1 and x2 have to be orthogonal
            }
            
            // x = x_new
            System.arraycopy(x_new, 0, x, 0, x_new.length);
            
            grad = gradient.gradf(x);
            iCount++;
            fCount.;

            /*System.out.print(iCount); System.out.print("-й шаг. Градиент: "); System.out.println(norm2(grad));
            System.out.print(x[0]); System.out.print(" "); System.out.println(x[1]);*/
        }
        return iCount;
    }

    private static double GetNewStepDichotomy(double[] x, double[] grad, double[] x_new, Function function, double eps) {
        double left = 0, right = 5; // начальный интервал неопределённости шага
        double step1, step2;
        double delta = eps / 20;
        while (right - left > eps) {
            step1 = (left + right) / 2 - delta;
            step2 = (left + right) / 2 + delta;
            if (function.f(VectorSum(x, NumberVectorMult(-step1, grad))) > function.f(VectorSum(x, NumberVectorMult(-step2, grad))))
                left = step1;
            else
                right = step2;
            assert (left < right);
        }
        return (left + right) / 2;
    }

    /**
     * Vector sum
     *
     * @param v1 - vector 1
     * @param v2 - vector 2
     * @return result vector
     */
    private static double[] VectorSum(double[] v1, double[] v2) {
        assert (v1.length == v2.length);
        double[] result = new double[v1.length];
        for (int i = 0; i < result.length; ++i)
            result[i] = v1[i] + v2[i];
        return result;
    }

    /**
     * Multiply vector by number
     *
     * @param num  - number
     * @param v - vector
     * @return result vector
     */
    private static double[] NumberVectorMult(double num, double[] v) {
        double[] result = new double[v.length];
        for (int i = 0; i < result.length; ++i)
            result[i] = num * v[i];
        return result;
    }

    /**
     * Multiply vectors
     *
     * @param v1 - vector 1
     * @param v2 - vector 2
     * @return result of multiplication
     */
    private static double VectorVectorMult(double[] v1, double[] v2) {
        double result = 0;
        for (int i = 0; i < v1.length; ++i)
            result += v1[i] * v2[i];
        return result;
    }

    /**
     * Norm of vector squared
     *
     * @param v - vector
     * @return norm of vector squared
     */
    private static double norm2(double[] v)
    {
        double res = 0;
        for (double vI : v) res += vI * vI;
        return res;
    }
}
