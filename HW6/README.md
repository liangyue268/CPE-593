##Algorithm
* [Regular matrix multiplication](https://en.wikipedia.org/wiki/Matrix_multiplication)
* [strassen’s matrix multiplication](https://en.wikipedia.org/wiki/Strassen_algorithm)

##Validation and comparison

First of all, I used some small matrices as input given by the question, it can calculate the right answer which means the function works well. 

Next, I tested on matrices with different size to compare strassen’s algorithm with regular multiplication. From the results given below, we can see that strassens’s algorithm does improve efficiency of matrix multiplication.

Note: Strassen.java is used for strassens’s algorithm and MultiplicationTest.java is used for regular multiplication, and Matrix.java provides some basic functions that they both need. Test matrices are stored in txt files.

| size | regular multiplication| strassen’s matrix multiplication|
|------|:----:|:----:|
|256*256| 43ms| 45ms|
|512*512| 306ms| 253ms|
|1024*1024| 8911ms| 2013ms|
|2048*2048| 101681ms| 61493|
