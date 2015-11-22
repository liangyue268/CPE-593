/**
 * Created by yue on 3/3/2015.
 */

import java.io.File;
import java.io.FileWriter;

public class optional {
    int[] data;

    public static void quickSort(int[] a, int left, int right, int k) {
        if(right-left>k){//if elements>k, use quick sort
            if(left>=right)
                return;
            int first=left;
            int last=right;
            int pivot= (a[left]+a[right])/2;
            while(first<last){
                while(first<last & a[last]>=pivot)
                    last--;
                a[first]=a[last];

                while(first<last & a[first]<=pivot)
                    first++;
                a[last]=a[first];
            }
            a[first]=pivot;
            quickSort(a, left, first-1, k);
            quickSort(a, first+1, right, k);
        }
        else{//if elements<=k, use insertion sort.
            int i,j;
            int Temp=0;
            for(i=left+1;i<right+1;i++){
                Temp=a[i];
                for(j=i-1;j>left-1;j--){
                    if(Temp<a[j]){
                        a[j+1]=a[j];
                    }
                    else
                        break;
                }
                a[j+1]=Temp;
            }
        }
    }

    public void writeFile(long[] a){
        try {
            File fos = new File("time3.txt"); //change here to what file to save
            FileWriter out = new FileWriter(fos);
            for(int i=0;i<a.length;i++){
                String s = Long.toString(a[i]);
                out.write(s);
                out.write("\n");
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] a ){
        optional sort = new optional();
        int num = 100000000;//change n
        sort.data = new int [num];
        int k = 5;
        int[] copyOfData = new int[num];
        for (int i=0;i<num;i++){
            sort.data[i] = (int)(Math.random()*num);
            copyOfData[i] = sort.data[i];
        }
        long totalTime[]=new long [200/5 - 1];
        int idx = 0; //index for totalTime
        while(k < 200){
            long beginTime = System.currentTimeMillis();
            sort.quickSort(copyOfData, 0, copyOfData.length-1, k);
            long endTime = System.currentTimeMillis();
            long costTime = endTime - beginTime;
            System.out.println(costTime + "ms");
            totalTime[idx] = costTime;
            idx++;
            k += 5;
            for (int i=0; i<num; i++){
                copyOfData[i] = sort.data[i];
            }
        }
        sort.writeFile(totalTime);
    }
}