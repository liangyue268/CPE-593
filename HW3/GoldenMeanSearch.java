/**
 * Created by yue on 3/28/2015.
 */
public class GoldenMeanSearch {
    final double phi = 1.618;
    public static void main(String[] args) {
        GoldenMeanSearch search = new GoldenMeanSearch();
        int[] x = new int[] {1,4,6,8,9,12,16,27,35,38,26,22,14,8,3};
        int pos = search.goldenMeanSearch(x, 0, x.length-1);
        System.out.print(x[pos]);
    }

    public int goldenMeanSearch(int[] array, int left, int right){
        int a = left, b = right;
        int s = b - a + 1;
        int x = a + (int)(s/phi);
        int y = b - (int)(s/phi);
        if(array[x] > array[y]){
            return goldenMeanSearch(array, y, b);
        }
        else if(array[x] < array[y]){
            return goldenMeanSearch(array, a, x);
        }
        else{
            return x;
        }
    }
}
