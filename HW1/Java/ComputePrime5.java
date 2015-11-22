import java.util.Arrays;
import java.util.Scanner;

/**
 * 埃氏筛法 2.0
 * Created by yue on 1/27/2015.
 */
public class ComputePrime5 {
    public static void main(String[] args) {
        long a = 0, b = 0;
        //set numbers
        try{
            Scanner in = new Scanner(System.in);
            System.out.print("Input a: ");
            a = in.nextLong();
            System.out.print("Input b: ");
            b = in.nextLong();
            if(a <= 0 || a > Long.MAX_VALUE || b <= 0 || b > Long.MAX_VALUE)
                throw new Exception("Unexpected number");
        }
        catch (Exception e){
            System.out.print("Invalid input");
            System.out.print("\nPlease try again!");
            System.exit(1);
        }

        long startTime=System.currentTimeMillis();//start time

        //compute prime
        //initialization: determine the size of array and fill it with true
        boolean[][][][] primes;
        if(b < (Integer.MAX_VALUE/2)){
            primes = new boolean[1][1][1][(int)b%(Integer.MAX_VALUE/2)+1];
        }
        else if(b < Integer.MAX_VALUE){
            primes = new boolean[1][1][2][Integer.MAX_VALUE/2];
        }
        else if(b < 3/2*Integer.MAX_VALUE){
            primes = new boolean[1][(int)(b/Integer.MAX_VALUE)%(Integer.MAX_VALUE/2) + 1][2][Integer.MAX_VALUE/2];
        }
        else{
            primes = new boolean[2][Integer.MAX_VALUE/2][2][Integer.MAX_VALUE/2];
        }
        for(boolean[][][] prime1 : primes)
            for(boolean[][] prime2: prime1)
                for(boolean[] prime3 : prime2)
                    Arrays.fill(prime3, true);
        primes[0][0][0][1] = false;

        //Sieve of Eratosthenes algorithm
        long range = (long) Math.sqrt(b);
        for(long j=4; j <= b; j += 2)
            primes[(int)(j/Integer.MAX_VALUE)/(Integer.MAX_VALUE/2)][(int)(j/Integer.MAX_VALUE)%(Integer.MAX_VALUE/2)][(int)j/(Integer.MAX_VALUE/2)][(int)j%(Integer.MAX_VALUE/2)] = false;
        for(long i = 3; i <= range; i++){
            if(primes[(int)(i/Integer.MAX_VALUE)/(Integer.MAX_VALUE/2)][(int)(i/Integer.MAX_VALUE)%(Integer.MAX_VALUE/2)][(int)i/(Integer.MAX_VALUE/2)][(int)i%(Integer.MAX_VALUE/2)]){
                for(long j = i*i; j <= b; j += 2*i){
                    //It is multiple of i
                    primes[(int)(j/Integer.MAX_VALUE)/(Integer.MAX_VALUE/2)][(int)(j/Integer.MAX_VALUE)%(Integer.MAX_VALUE/2)][(int)j/(Integer.MAX_VALUE/2)][(int)j%(Integer.MAX_VALUE/2)] = false;
                }
            }
        }

        //count prime number
        int count = 0;
        for(long i = a; i <= b; i++)
            if(primes[(int)(i/Integer.MAX_VALUE)/(Integer.MAX_VALUE/2)][(int)(i/Integer.MAX_VALUE)%(Integer.MAX_VALUE/2)][(int)i/(Integer.MAX_VALUE/2)][(int)i%(Integer.MAX_VALUE/2)])
                count++;

        System.out.printf("Total prime numbers between %d and %d are %d\n",a,b,count);

        long endTime=System.currentTimeMillis(); //endtime
        System.out.println("Total time: "+ (endTime-startTime)+"ms");
    }
}
