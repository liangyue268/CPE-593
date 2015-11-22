import java.util.Random;
import java.util.Scanner;

/**
 * Miller-Rabin
 * Created by yue on 2/6/2015.
 */
public class ComputePrime6 {
    public int powerMod(int x, int n, int m){
        int prod = 1;
        while(n > 0){
            if(n % 2 != 0)
                prod = prod * x % m;
            x = x*x % m;
            n = n/2;
        }
        return prod;
    }

    public boolean fermat(int p, int k){
        int min = 2;
        int max = p-1;
        Random r = new Random();
        for(int i = 1; i <= k; i++){
            int a = r.nextInt(max) % (max-min+1) + min;
            if(powerMod(a, p-1, p) != 1)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int a = 0, b = 0;
        ComputePrime6 prime = new ComputePrime6();
        //set numbers
        try{
            Scanner in = new Scanner(System.in);
            System.out.print("Input a: ");
            a = in.nextInt();
            System.out.print("Input b: ");
            b = in.nextInt();
            if(a <= 0 || a > Integer.MAX_VALUE || b <= 0 || b > Integer.MAX_VALUE)
                throw new Exception("Unexpected number");
        }
        catch (Exception e){
            System.out.print("Invalid input");
            System.out.print("\nPlease try again!");
            System.exit(1);
        }

        long startTime=System.currentTimeMillis();//start time

        int count = 0;
        for(int i = a; i <= b; i++)
            if(prime.fermat(i, 10))
                count++;
        System.out.printf("Total prime numbers between %d and %d are %d\n",a,b,count);

        long endTime=System.currentTimeMillis(); //endtime
        System.out.println("Total time: "+ (endTime-startTime)+"ms");
    }
}
