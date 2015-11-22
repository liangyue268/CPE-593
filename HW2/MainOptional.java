import java.util.Random;

/**
 * Created by yue on 3/1/2015.
 */
public class MainOptional {
    static int[] x;
    public static void main(String[] args) {
        sortOptional sort1 = new sortOptional();
        //generate unsorted array
        int length = 1000000;
        x = new int[length];
        Random r = new Random();
        for(int i=0; i<length; i++){
            x[i] = r.nextInt(length);
        }
        //start sorting
        long startTime=System.currentTimeMillis();//start time
        sort1.quickSort(x, 0, x.length-1);
        long endTime=System.currentTimeMillis(); //endtime
        System.out.println("Total time: "+ (endTime-startTime)+"ms");
    }
}