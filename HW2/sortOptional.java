import java.util.Random;
import java.util.Stack;

/**
 * Created by yue on 3/3/2015.
 */
public class sortOptional {
    public void quickSort(int[] x, int left, int right){
        int middle = partition(x, left, right);
        int k = 100;
        if(x == null || left == right)
            return;
        if(right-left > k)
            quickSort(x, left, middle-1);
        if(right-middle+1 > k)
            quickSort(x, middle, right);
        insertionSort(x);
    }
    //no recursion for quick sort
    public void quickSort2(int[] x, int left, int right){
        int middle;
        int k = 100;
        Stack<Integer> startStack = new Stack<Integer>();
        Stack<Integer> endStack = new Stack<Integer>();

        startStack.push(left);
        endStack.push(right);
        while(!startStack.isEmpty()){
            left = startStack.pop();
            right = endStack.pop();
            middle = partition(x, left, right);
            //preparation for next iteration
            if(middle-left > k){
                startStack.push(left);
                endStack.push(middle);
            }
            if(right-middle+1 > k){
                startStack.push(middle+1);
                endStack.push(right);
            }
        }
        insertionSort(x);
    }

    private int partition(int[] x, int left, int right){
        int pivot = (x[left] + x[right]) / 2;
        while(left != right){
            while(left < right && x[left] <= pivot)
                left++;
            while(left < right && x[right] > pivot)
                right--;
            if(left != right) {
                int temp = x[right];
                x[right] = x[left];
                x[left] = temp;
            }
        }
        return left;
    }

    public int[] insertionSort(int[] x){
        int temp;
        for(int i = 1; i < x.length; i++){
            if(x[i] < x[i-1]){
                temp = x[i];
                int j = i;
                //must compare whether j > 0 firstly or there might be a index error
                for( ; j > 0 && x[j-1] > temp; j--){
                    x[j] = x[j-1];
                }
                x[j] = temp;
            }
        }
        return x;
    }
}
