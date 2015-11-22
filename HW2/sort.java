import java.util.Random;

/**
 * Created by yue on 2/25/2015.
 */
public class sort {
    public void quickSort(int[] x, int left, int right){
        int middle = partition(x, left, right);
        int k = 4;
        if(middle-left > k)
            quickSort(x, left, middle-1);
        if(right-middle+1 > k)
            quickSort(x, middle, right);
        insertionSort(x);
    }

    private int partition(int[] x, int left, int right){
        int pivot = new Random().nextInt(right - left + 1) + left;
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

    private int[] insertionSort(int[] x){
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