Given the following file, read in the specification and use binary search to find the requested value in the list.

Output either the value found and how many iterations to find it or print "NOT FOUND" followed by the number of iterations before your code determined that it was not found.

Each case has three lines:
* First line: the number of elements
* Second line: a list of integers
* Third line: the number being searched for

Here is sample input:
>Cases: 3  
10  
1 3 6 10 15 20 21 22 25 26  
>4  
20  
1 3 6 10 15 20 21 22 25 26 27 28 30 31 32 40 41 45 50 51  
60  
20  
1 3 6 10 15 20 21 22 25 26 27 28 30 31 32 40 41 45 50 51  
27  

The output should be:
NOT FOUND 4  
NOT FOUND 5  
27 1  
