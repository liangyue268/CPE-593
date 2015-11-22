import java.util.Random;

/**
 * Created by yue on 3/29/2015.
 */
public class Romberg {
    public static double romberg(FuncOneVar func, double a, double b, double eps) {
        double h = b - a;
        double S = (func.f(a) + func.f(b)) / 2; // average height of func
        double I1 = S * h;

        h /= 2;
        double x = a + h;
        S += func.f(x);
        double I2 = S *h;
        double R2 = (4*I2-I1)/3;
        double I4;
        double R1;
        int n = 2;
        do {
            R1 = R2;
            h /= 2;
            x = a + h;
            for (int i = 0; i < n; i++, x += 2*h)
                S += func.f(x);
            I4 = S * h;
            n *= 2;
            R2 = (4*I4-I2)/3;
            I2 = I4;
        }
        while (Math.abs(R2-R1) > eps);
        return R2;
    }

    public static void main(String[] args) {
        FuncOneVar func = new FuncOneVar() {
            @Override
            public double f(double x) {
                return x*x*x*x;
            }
        };
        System.out.print(romberg(func, 0, 2, 0.0001));
    }
}
