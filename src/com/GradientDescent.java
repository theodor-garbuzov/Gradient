package com;

import static com.VectorOperations.*;

public class GradientDescent {

    /**
     * Optimize function starting from a point x with provided exit parameter
     *
     * @param x        - start point, at the end it will be optimum point
     * @param function - function to optimize
     * @param gradient - function's gradient
     * @param eps      - exit parameter
     * @param eps1     - exit parameter of one-dimensional optimization
     * @param fCount   - "pointer" to a number of function calculations
     * @return iteration  -  number of method iterations
     */
    public static int Optimize(double[] x, Function function, Gradient gradient, double eps, double eps1, int[] fCount) {
        double[] x_new = new double[x.length];          // x_{k+1}
        double[] grad = gradient.gradf(x);              // current gradient
        double[][] xArray = new double[10][x.length];   // vector of points
        double[] step = new double[10];                 // vector of steps (alpha_k)
        int iteration = 0;

        System.arraycopy(x, 0, xArray[0], 0, x.length);
        
        while (norm2(grad) > eps) {
            // get new point x_new = x - step * grad
            step[iteration] = GetNewStepDichotomy(x, grad, x_new, function, eps1, fCount);
            System.arraycopy(VectorSum(x, NumberVectorMult(-step[iteration], grad)), 0, x_new, 0, x_new.length);
            System.out.println("\nalpha_" + (iteration+1) + ": " + step[iteration]);

            // x = x_new
            System.arraycopy(x_new, 0, x, 0, x_new.length);
            System.arraycopy(x, 0, xArray[iteration + 1], 0, x.length);

            // print
            if (iteration > 0) {
                double[] x1 = VectorSum(xArray[iteration+1], NumberVectorMult(-1, xArray[iteration]));
                double[] x2 = VectorSum(xArray[iteration], NumberVectorMult(-1, xArray[iteration-1]));
                System.out.println("(x_{k+1} - x_k) * (x_k - x_{k-1}) = " + VectorVectorMult(x1, x2));
            }
            if (iteration > 1) {
                double item1 = VectorVectorMult(gradient.gradf(xArray[iteration ]),
                        VectorSum(gradient.gradf(xArray[iteration-1]), gradient.gradf(xArray[iteration-2])));
                double item2 = VectorVectorMult(gradient.gradf(xArray[iteration - 1]),
                        VectorSum(gradient.gradf(xArray[iteration]), gradient.gradf(xArray[iteration-1])));
                double range = eps1 * (step[iteration+1] * item1 + step[iteration] * item2);
                System.out.println("Оценка: " + range);
            }

            grad = gradient.gradf(x);
            fCount[0] += grad.length;
            iteration++;

            System.out.println("Градиент: " + norm2(grad));
            System.out.println("Точка: " + x[0] + " " + x[1]);
        }
        return iteration;
    }

    private static double GetNewStepDichotomy(double[] x, double[] grad, double[] x_new,
                                              Function function, double eps, int[] fCount) {
        double left = 0, right = 1; // начальный интервал неопределённости шага
        double step1, step2;
        double delta = eps / 20;
        while (right - left > eps) {
            step1 = (left + right) / 2 - delta;
            step2 = (left + right) / 2 + delta;
            fCount[0] += 2;
            if (function.f(VectorSum(x, NumberVectorMult(-step1, grad))) >
                    function.f(VectorSum(x, NumberVectorMult(-step2, grad))))
                left = step1;
            else
                right = step2;
            assert (left < right);
        }
        return (left + right) / 2;
    }
}
