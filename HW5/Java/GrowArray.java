import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by yue on 4/9/2015.
 */
public class GrowArray {
    private int size;
    private int used;
    private int[] array;

    GrowArray(){
        size = 10;
        used = 0;
    }

    GrowArray(int num){
        size = num;
        used = num;
        array = new int[size];
        for(int i = 0; i < used; i++){
            array[i] = i+1;
        }
    }

    public void deleteStart(){
        int[] temp = array;
        used--;
        array = new int[used];
        for(int i=0; i < used; i++){
            array[i] = temp[i+1];
        }
    }

    public void deleteEnd(){
        used--;
    }

    @Override
    public String toString(){
        String s = "";
        for(int i = 0; i < used; i++){
            s += array[i] + " ";
        }
        return s;
    }

    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(new FileInputStream("hw4ainp.dat"));
        int N = in.nextInt();
        int M = in.nextInt();
        int P = in.nextInt();
        GrowArray a = new GrowArray(N);
        for(int i = 0; i < M; i++){
            a.deleteEnd();
        }
        for(int i = 0; i < P; i++){
            a.deleteStart();
        }
        System.out.print(a);
    }
}