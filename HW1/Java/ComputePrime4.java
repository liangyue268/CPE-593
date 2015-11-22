import java.util.Arrays;
import java.util.Scanner;

/**
 * 埃氏筛法 1.0
 * Created by yue on 1/26/2015.
 */
class BitVec{
    private long[] bits;
    private int size;
    int i =  Integer.MAX_VALUE;
    //initialization
    BitVec(int numBits){
        size = (numBits+1+63)/64;
        bits = new long[size];
        Arrays.fill(bits, ~0L);
    }
    //functions
    void set(int i){
        bits[i >> 6] |= (1L << (i & 0x3F));
    }
    void clear(int i){
        bits[i >> 6] &= ~(1L << (i & 0x3F));
    }
    boolean test(int i){
        return (bits[i >> 6] & (1L << (i & 0x3F))) != 0;
    }
}

public class ComputePrime4 {
    public static void main(String[] args) {
        int a = 0, b = 0;
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

        //compute prime
        BitVec prime = new BitVec(b);
        prime.clear(1);
        int range = (int) Math.sqrt(b);
        for(int i = 4; i <= b; i += 2)
            prime.clear(i);
        for(int i = 3; i <= range; i++){
            if(prime.test(i)){
                for(int j = i*i; j <= b; j += 2*i){
                    /*if(j+a == 1)
                        prime.clear(j);*/
                    //It is multiple of i
                    prime.clear(j);
                }
            }
        }

        //count prime number
        long sum = 0;
        for(int i = a; i <= b; i++)
            if(prime.test(i))
                sum += i;

        System.out.printf("Total prime numbers between %d and %d are %d\n",a,b,sum);

        long endTime=System.currentTimeMillis(); //endtime
        System.out.println("Total time: "+ (endTime-startTime)+"ms");
    }
}
