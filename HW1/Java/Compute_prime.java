import java.util.Scanner;

/**
 * Created by yue on 1/24/2015.
 */
public class Compute_prime {
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
        int sum = 0,flag;
        for(long i = a;i <= b; i++){
            //For i == 1, ignore it
            if(i == 1)
                continue;
            //other cases
            flag = 1;
            long range = (long)Math.sqrt(i);
            for(long j = 2; j <= range; j++){
                if(i % j == 0){
                    flag = 0;
                    break;
                }
            }
            sum += flag;
        }
        System.out.printf("Total prime numbers between %d and %d are %d\n",a,b,sum);

        long endTime=System.currentTimeMillis(); //endtime
        System.out.println("Total time: "+ (endTime-startTime)+"ms");
    }
}