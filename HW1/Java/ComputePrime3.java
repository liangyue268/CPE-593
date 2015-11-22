import java.util.Scanner;

/**
 * PrimeWheel 2.0
 * Created by yue on 1/24/2015.
 */
public class ComputePrime3 {

    private static int WheelFactorization(long startCircles, long endCircles, int startPosition, int endPosition, int factor, int[] cases){
        //parameters
        int sum = 0, flag;
        long x; //possible prime
        //computation
        for(long numberOfCircles = startCircles; numberOfCircles <= endCircles; numberOfCircles++) //for each circle
            for(int i : cases){ //possible numbers in each circle
                if(numberOfCircles == startCircles && i < startPosition){ //don't reach the first possible number
                    continue;
                }
                if(numberOfCircles == endCircles && i > endPosition){ //reach the last possible number
                    break;
                }
                x = numberOfCircles * factor + i;
                //For x == 1, ignore it.
                /*if(x == 1)
                    continue;*/
                //other cases
                flag = 1;
                long range = (long)Math.sqrt(x);
                for(long j = 2; j <= range; j++){
                    if(x % j == 0){
                        flag = 0;
                        break;
                    }
                }
                sum += flag;
                }
        return sum;
    }
    public static void main(String[] args) {
        //parameters
        final int N = 2*3*5;
        int[] cases = new int[] {1,2,3,5,7,11,13,17,19,23,29};//Known prime numbers under 30
        int sum = 0;
        //set numbers
        Scanner in = new Scanner(System.in);
        System.out.print("Input a: ");
        long a = in.nextLong();
        System.out.print("Input b: ");
        long b = in.nextLong();
        //compute prime
        long startTime=System.currentTimeMillis();//start time

        long startCircles = a/N; int startPosition = (int)a%N;
        long endCircles = b/N; int endPosition = (int) b%N;
        if(b < N){
            for(int i = (int)a; i <= b; i++)
                for(int prime : cases){
                    //For i == 1, ignore it.
                    if(i == 1)
                        continue;
                    //other cases
                    if(i == prime)
                        sum++;
                }
        }
        else{
            sum = WheelFactorization(startCircles, endCircles, startPosition, endPosition, N, cases);
        }
        System.out.printf("Total prime numbers between %d and %d are %d\n",a,b,sum);

        long endTime=System.currentTimeMillis(); //endtime
        System.out.println("Total time: "+ (endTime-startTime)+"ms");
    }
}