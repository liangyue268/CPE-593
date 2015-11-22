import java.io.*;

/**
 * Created by yue on 2/12/2015.
 */
public class Main {
    public static void main(String[] args) {
        sort sort1 = new sort();
        String filePath = "./HW2.txt";
        int[] x = readUnsortedArray(filePath);
        sort1.quickSort(x, 0, x.length-1);
        System.out.print("Sorted array: ");
        for(int num: x)
            System.out.print(num+", ");
    }

    private static int[] readUnsortedArray(String filePath){
        File file = new File(filePath);
        String temp = null;
        int length = 0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            temp = reader.readLine();
            length = Integer.parseInt(temp);
            temp = reader.readLine();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String[] stringNum = temp.split(" ");
        int[] x = new int[length];
        for(int i=0; i < length; i++){
            x[i] = Integer.parseInt(stringNum[i]);
        }
        return x;
    }
}