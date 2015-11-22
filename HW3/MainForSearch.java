import java.io.*;
import java.util.ArrayList;

/**
 * Created by yue on 2/26/2015.
 */
public class MainForSearch {
    public static void main(String[] args) {
        String filePath = "./inp.dat";
        File file = new File(filePath);
        String temp;
        ArrayList<String> text = new ArrayList<String>();
        int length = 0, target = 0, line = 0;
        int[] x = null;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while((temp = reader.readLine()) != null){
                text.add(temp);
                line++;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        for(int i=1; i < line; i++){
            temp = text.get(i);
            if(i % 3 == 1){
                length = Integer.parseInt(temp);

            }
            else if(i % 3 == 2){
                String[] stringNum = temp.split(" ");
                x = new int[length];
                for(int j=0; j < length; j++){
                    x[j] = Integer.parseInt(stringNum[j]);
                }
            }
            else {
                target = Integer.parseInt(temp);
                search(x, target);
            }
        }
    }

    private static void search(int[] x, int target){
        BinarySearch search = new BinarySearch();
        search.quickSort(x, 0, x.length-1);
        int pos = search.binarySearch(x, target, 0, x.length-1);
        if(pos != -1)
            System.out.printf("%d %d\n", x[pos], search.count);
        else
            System.out.printf("NOT FOUND %d\n", search.count);
    }
}