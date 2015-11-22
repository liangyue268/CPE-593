import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by yue on 3/8/2015.
 */
interface Func{
    double fa(double x);
    double fb(double x);
    double fc(double x);
}

public class GaussianQuadrature {
    public static double gaussianQuadrature2nd(Func func, double a, double b, double h, String flag){
        double mid = (b + a) / 2;
        double x1, x2, S;
        x1 = mid + h * Math.sqrt(1/3.0);
        x2 = mid - h * Math.sqrt(1/3.0);
        switch(flag){
            case "fa":
                S = func.fa(x1) + func.fa(x2);
                break;
            case "fb":
                S = func.fb(x1) + func.fb(x2);
                break;
            case "fc":
                S = func.fc(x1) + func.fc(x2);
                break;
            default:
                S = 0;
        }
        double I = S * h;
        return I;
    }

    public static double gaussianQuadrature3rd(Func func, double a, double b, double h, String flag){
        double mid = (b + a) / 2;
        double x1, x2, x3, S;
        x1 = mid + h * Math.sqrt(3/5.0);
        x2 = mid;
        x3 = mid - h * Math.sqrt(3/5.0);
        switch(flag){
            case "fa":
                S = 5/9.0*func.fa(x1) + 8/9.0*func.fa(x2) + 5/9.0*func.fa(x3);
                break;
            case "fb":
                S = 5/9.0*func.fb(x1) + 8/9.0*func.fb(x2) + 5/9.0*func.fb(x3);
                break;
            case "fc":
                S = 5/9.0*func.fc(x1) + 8/9.0*func.fc(x2) + 5/9.0*func.fc(x3);
                break;
            default:
                S = 0;
        }
        double I = S * h;
        return I;
    }

    public void iterate2nd(Func func, double a, double b, double eps, String flag) {
        double h = (b - a) / 2;
        double I1, I2;

        I2 = gaussianQuadrature2nd(func, a, b, h, flag);
        int n = 2;
        do{
            I1 = I2;
            I2 = 0;
            h /= 2;
            double x1 = a, x2 = x1+2*h;
            for(int i = 0; i < n; i++){
                I2 += gaussianQuadrature2nd(func, x1, x2, h, flag);
                x1 = x2;
                x2 = x1+2*h;
            }
            n *= 2;
        }
        while(Math.abs(I2-I1) > eps);
        //System.out.print(I2 + "  " + n/2 + "  ");
        System.out.printf("%.8f  %d  ", I2, n/2);
    }

    public void iterate3rd(Func func, double a, double b, double eps, String flag) {
        double h = (b - a) / 2;
        double I1, I2;

        I2 = gaussianQuadrature3rd(func, a, b, h, flag);
        int n = 2;
        do{
            I1 = I2;
            I2 = 0;
            h /= 2;
            double x1 = a, x2 = x1+2*h;
            for(int i = 0; i < n; i++){
                I2 += gaussianQuadrature3rd(func, x1, x2, h, flag);
                x1 = x2;
                x2 = x1+2*h;
            }
            n *= 2;
        }
        while(Math.abs(I2-I1) > eps);
        //System.out.println(I2 + "  " + n/2);
        System.out.printf("%.8f  %d\n", I2, n/2);
    }

    public static void main(String[] args) throws IOException{
        Func func = new Func() {
            @Override
            public double fa(double x) {
                return Math.pow(x, 2);
            }

            @Override
            public double fb(double x) {
                return Math.pow(x, 3);
            }

            @Override
            public double fc(double x) {
                return x * Math.exp(-x);
            }
        };
        GaussianQuadrature quad = new GaussianQuadrature();

        Scanner in = new Scanner(new FileInputStream("HW2b.txt"));
        int cases = Integer.parseInt(in.nextLine().split(" ")[1]);
        for(int i=0; i<cases; i++) {
            String[] temp = in.nextLine().split("\\s+");
            String flag = temp[0];
            double a = Float.parseFloat(temp[1]);
            double b = Float.parseFloat(temp[2]);
            double eps = Float.parseFloat(temp[3]);

            quad.iterate2nd(func, a, b, eps, flag);
            quad.iterate3rd(func, a, b, eps, flag);
        }
    }
}