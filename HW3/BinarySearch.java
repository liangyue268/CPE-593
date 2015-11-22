/**
 * Created by yue on 2/26/2015.
 */
public class BinarySearch {
    int count = 0;

    public int binarySearch(int[] x, int target, int left, int right){
        count++;
        if(left > right)
            return -1;
        int mid = (left+right+1)/2;
        if(x[mid] < target)
            return binarySearch(x, target, mid+1, right);
        else if(x[mid] > target)
            return binarySearch(x, target, left, mid-1);
        else
            return mid;
    }

    public void quickSort(int[] x, int left, int right){
        int middle = partition(x, left, right);
        if(x == null || left == right)
            return;
        quickSort(x, left, middle-1);
        quickSort(x, middle, right);
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
}