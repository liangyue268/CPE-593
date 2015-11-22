import java.io.FileInputStream;
import java.util.Scanner;

/**
 * Created by yue on 4/26/2015.
 */
public class MultiplicationTest {
    public static void main(String[] args) throws Exception{
        Matrix a, b;

        String filePath = "HW5c5.txt";
        Scanner in = new Scanner(new FileInputStream(filePath));
        int cases = Integer.parseInt(in.nextLine().split(" ")[1]);
        while(cases > 0){
            String line = in.nextLine();
            String[] temp = line.split(" ");
            int rows = Integer.parseInt(temp[0]);
            int cols = Integer.parseInt(temp[1]);

            a = Matrix.inputMatrix(in, rows, cols);
            b = Matrix.inputMatrix(in, rows, cols);

            long startTime=System.currentTimeMillis();//start time

            Matrix result = a.multiply(b);
            //System.out.println(result);
            long endTime=System.currentTimeMillis(); //endtime
            System.out.println("Total time: "+ (endTime-startTime)+"ms");
            cases--;
        }
    }
}