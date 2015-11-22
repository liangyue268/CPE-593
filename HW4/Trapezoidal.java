/**
 * Created by yue on 3/7/2015.
 */
public class Trapezoidal {
    public static double trapezoidal(FuncOneVar func, double a, double b, double eps) {
        double h = b - a;
        double S = (func.f(a) + func.f(b)) / 2; // average height of func
        double I2 = S * h;
        double I1;
        int n = 1;
        do {
            I1 = I2;
            h /= 2;
            double x = a + h;
            for (int i = 0; i < n; i++, x += 2*h)
                S += func.f(x);
            I2 = S * h;
            n *= 2;
        }
        while (Math.abs(I2-I1) > eps);
        return I2;
    }

    public static void main(String[] args) {
        FuncOneVar func = new FuncOneVar() {
            @Override
            public double f(double x) {
                return x*x;
            }
        };
        System.out.print(trapezoidal(func, 0, 2, 0.0001));
    }
}