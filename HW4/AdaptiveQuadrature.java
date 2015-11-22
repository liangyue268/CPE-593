/**
 * Created by yue on 3/7/2015.
 */
public class AdaptiveQuadrature {
    public static double adaptiveQuadrature(FuncOneVar func, double I1, double a, double b, double eps) {
        // Compute level I2 sum
        double h = b - a;
        double S = (func.f(a) + func.f(b)) / 2; // average height of func
        double I2 = S * h;
        /*int n = 1;
        do {
            I1 = I2;
            h /= 2;
            double x = h;
            for (int i = 0; i < n; i++, x += 2*h)
                S += func.f(x);
            I2 = S * h;
            n *= 2;
        }
        while (Math.abs(I2-I1) > eps);*/

        // if it's good enough, get out now
        if ( Math.abs(I2 - I1) < eps)
            return I2;
        double mid = (a+b) / 2;
        return adaptiveQuadrature(func, I2/2, a, mid, eps/2) + adaptiveQuadrature(func, I2/2, mid, b, eps/2);
    }

    public static void main(String[] args) {
        FuncOneVar func = new FuncOneVar() {
            @Override
            public double f(double x) {
                return x*x;
            }
        };
        System.out.print(adaptiveQuadrature(func, 0, 0, 2, 0.01));
    }
}
