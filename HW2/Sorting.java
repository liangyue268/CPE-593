import java.lang.reflect.Array;
import java.sql.Ref;
import java.util.Arrays;

/**
 * Created by yue on 2/12/2015.
 */
public class Sorting {
    public int[] bubbleSort(int[] x){
        boolean done = false;
        for(int i = 0; i < x.length && !done; i++) {
            done = true;
            for (int j = 0; j < x.length-1-i; j++) {
                if (x[j] > x[j + 1]) {
                    done = false;
                    int temp = x[j + 1];
                    x[j + 1] = x[j];
                    x[j] = temp;
                }
            }
        }
        return x;
    }

    public int[] selectionSort(int[] x){
        for(int i = 0; i < x.length; i++){
            int pos = 0;
            for(int j = 1; j < x.length-i; j++){
                if(x[j] > x[pos]){
                    pos = j;
                }
            }
            int temp = x[x.length-i-1];
            x[x.length-i-1] = x[pos];
            x[pos] = temp;
        }
        return  x;
    }

    public int[] insertionSort(int[] x){
        int temp;
        for(int i = 1; i < x.length; i++){
            if(x[i] < x[i-1]){
                temp = x[i];
                int j = i;
                //must compare whether j > 0 first or there might be a index error
                for( ; j > 0 && x[j-1] > temp; j--){
                    x[j] = x[j-1];
                }
                x[j] = temp;
            }
        }
        return x;
    }

    public void quickSort(int[] x, int left, int right){
        if(x == null || left == right)
            return;
        int middle = partition(x, left, right);
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

    public int[] heapSort(int[] x){
        int length = x.length;
        for(int i=length-1; i>0; i--){
            buildTree(x, i);
            int temp = x[0];
            x[0] = x[i];
            x[i] = temp;
        }
        return x;
    }

    private void buildTree(int[] x, int idx){
        int node = (idx -1)/2;// the last node that has at least one child(left child).
        for( ; node >= 0; node--){
            treeAdjust(x, node, idx);
        }
    }
    private void treeAdjust(int[] x, int node, int idx) {
        int lchild = 2 * node + 1;
        int rchild = 2 * node + 2;
        if (lchild <= idx && x[lchild] > x[node]) {
            if (rchild <= idx && x[rchild] > x[lchild]) {
                int temp = x[rchild];
                x[rchild] = x[node];
                x[node] = temp;
                treeAdjust(x, rchild, idx);
            }
            else {
                int temp = x[lchild];
                x[lchild] = x[node];
                x[node] = temp;
                treeAdjust(x, lchild, idx);
            }
        }
        if (rchild <= idx && x[rchild] > x[node]) {
            int temp = x[rchild];
            x[rchild] = x[node];
            x[node] = temp;
            treeAdjust(x, rchild, idx);
        }
    }

    public void mergeSort(int[] x){
        int[] temp = new int[x.length];
        merge(x, temp, 0, x.length-1);
    }

    private void merge(int[] x, int[] temp, int left, int right){
        //step1: split array
        if(left == right)
            return;
        int mid = (left+right)/2;
        merge(x, temp, left, mid);
        merge(x, temp, mid+1, right);
        //step2: sorting
        for(int i=left; i<=right; i++){
            temp[i] = x[i];
        }
        int i =left;
        int j = mid+1;
        int idx =left;

        while(i <= mid && j <= right){
            if(temp[i] < temp[j]){
                x[idx++] = temp[i++];
            }
            else{
                x[idx++] = temp[j++];
            }
        }
        while(i <= mid){
            x[idx++] = temp[i++];
        }
        while(j <= right){
            x[idx++] = temp[j++];
        }
    }
}
