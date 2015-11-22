##Algorithm
* Read in array from a txt file
* Implementation of multiple sorting algorithms, such as bubble sort, selection sort, insertion sort, heap sort, quick sort and merge sort.
* Optimized Solution: Knuth-optimized quicksort
  * the quicksort operates on all partitions > k elements.  The array is  partially sorted in this way, and then a single insertion sort is called to polish the results.

##Analyze (for Knuth-optimized quicksort)
This program constantly changes k, ranging from 5 to 195 with 5 increment for each step and output computation time in a txt file. (Orgainized in a excel file)

###1. n = 10^6
As we can see in this figure, the running time decreases when k increases and then increase again. So we can find that 150 is an optimal value for k when n = 10^6.

![image](https://github.com/liangyue268/CPE-593/raw/master/HW2/1.jpg)

###2.	n = 10^7
Likewise, 125 is an optimal value for k when n = 10^7.

![image](https://github.com/liangyue268/CPE-593/raw/master/HW2/2.jpg)

###3.	n = 10^8
110 is an optimal value for k when n = 10^8.

![image](https://github.com/liangyue268/CPE-593/raw/master/HW2/3.jpg)
