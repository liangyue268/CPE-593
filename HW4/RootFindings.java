/**
 * Created by yue on 2/26/2015.
 */
public class RootFindings {
    double function(double x){
        return x*x-2;
    }
    double derivativeOfFunction(double x){
        return 2*x;
    }

    double bisect(double a, double b, double delta){
        if(b-a <= delta)
            return b;
        double x = (b+a)/2;
        if(function(x) >= 0)
            return bisect(a, x, delta);
        else
            return bisect(x, b, delta);
    }
    //another way to do it
    double bisect2(double a, double b, double eps){
        double x = (a+b) / 2;
        int i = 0;
        while(b - a > eps){
            double y = function(x);
            if(y < 0)
                a = x;
            else if(y>0)
                b = x;
            x = (a+b)/2;
            i++;
        }
        System.out.println("iterations = "+i);
        return x;
    }

    double newton(double x, double delta){
        double nextX = x - function(x)/derivativeOfFunction(x);
        if(Math.abs(nextX - x) <= delta)
            return nextX;
        return newton(nextX, delta);
    }

    double newton2(double x, double delta){
        double nextX = x - function(x)/derivativeOfFunction(x);
        while(Math.abs(nextX - x) > delta){
            x = nextX;
            nextX = x - function(x)/derivativeOfFunction(x);
        }
        return nextX;
    }
}