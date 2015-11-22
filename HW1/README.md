##Algorithm
I adopted [sieve of Eratosthenes](http://en.wikipedia.org/wiki/Sieve_of_Eratosthenes) algorithm to calculate prime numbers with data structure Bitvec. 

###Java
In Java, a one-dimensional array can only index numbers up to the maximal number of 32bit integer. So it may not possible to calculate the sum of prime numbers up to 10^12, but it runs fairly well for prime numbers up to 10^9. Here is the result that I compute for the sum of prime numbers from 1 to 10^9.

![imag](https://github.com/liangyue268/CPE-593/blob/master/HW1/1.jpg)

###C++
Due to the problem existed in Java, I wrote another version in C++. As expected, it support array index number up to 64bit integer when adopt unsinged long long type of integer. But another issue raised unexpected, it runs slower than my Java version, even though I changed nothing for my algorithm. Here is the result that I compute for the sum of prime numbers from 1 to 10^9 and from 1 to 10^10.

|data  |execution time|
|------|-------------:|
|10^9|      99.94sec|
|10^10| 1052.09sec|

![imag](https://github.com/liangyue268/CPE-593/blob/master/HW1/2.jpg)
![imag](https://github.com/liangyue268/CPE-593/blob/master/HW1/3.jpg)

##Analyze
The complexity of this algorithm should be O(sqrt(n)*loglog(n)), and as we can see, when we 10 times the value, it 10 times the calculation time. So I guess that it may take 100000sec to calculate the sum of prime numbers from 1 to $10^{12}$ for my C++ version.
