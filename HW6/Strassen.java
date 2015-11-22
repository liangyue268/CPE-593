import java.io.FileInputStream;
import java.util.Scanner;

/**
 * Created by yue on 4/24/2015.
 */
public class Strassen{
    public static Matrix padding(Matrix a){
        int length = (int)Math.pow(2, Math.ceil(Math.log(Math.max(a.rows, a.cols)) / Math.log(2)));

        Matrix paddedMatrix = new Matrix(length, length);
        for(int i=0; i<a.rows; i++){
            for(int j=0; j<a.cols; j++){
                paddedMatrix.m[i*length + j] = a.m[i*a.cols + j];
            }
            for(int j=a.cols; j<length; j++){
                paddedMatrix.m[i*length + j] = 0;
            }
        }
        for(int i=a.rows; i<length; i++){
            for(int j=0; j<length; j++){
                paddedMatrix.m[i*length + j] = 0;
            }
        }
        return paddedMatrix;
    }

    public static Matrix cut(Matrix a, int rows,int cols){
        Matrix temp = new Matrix(rows, cols);
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                temp.m[i*cols + j] = a.m[i*a.cols + j];
            }
        }
        return temp;
    }

    public static Matrix strassenRecur(Matrix a, Matrix b, int n){
        if(n == 2){
            return a.multiply(b);
        }
        else {
            //initializing 4 sub-matrices
            int newSize = n / 2;
            Matrix a11 = new Matrix(newSize, newSize);
            Matrix a12 = new Matrix(newSize, newSize);
            Matrix a21 = new Matrix(newSize, newSize);
            Matrix a22 = new Matrix(newSize, newSize);

            Matrix b11 = new Matrix(newSize, newSize);
            Matrix b12 = new Matrix(newSize, newSize);
            Matrix b21 = new Matrix(newSize, newSize);
            Matrix b22 = new Matrix(newSize, newSize);

            Matrix aTemp;
            Matrix bTemp;

            // dividing the matrices in 4 sub-matrices
            for (int i = 0; i < newSize; i++){
                for (int j = 0; j < newSize; j++){
                    a11.m[i*newSize + j] = a.m[i*n + j];
                    a12.m[i*newSize + j] = a.m[i*n + j+newSize];
                    a21.m[i*newSize + j] = a.m[(i+newSize)*n + j];
                    a22.m[i*newSize + j] = a.m[(i+newSize)*n + j+newSize];

                    b11.m[i*newSize + j] = b.m[i*n + j];
                    b12.m[i*newSize + j] = b.m[i*n + j+newSize];
                    b21.m[i*newSize + j] = b.m[(i+newSize)*n + j];
                    b22.m[i*newSize + j] = b.m[(i+newSize)*n + j+newSize];
                }
            }

            // Calculating h1 to h7
            // h1 = (a11+a22) * (b11+b22)
            aTemp = a11.add(a22);
            bTemp = b11.add(b22);
            //Matrix h1 = strassenRecur(aTemp, bTemp, newSize);
            Matrix h1 = aTemp.multiply(bTemp);
            // h2 = (a21+a22) * (b11)
            aTemp = a21.add(a22);
            bTemp = b11;
            //Matrix h2 = strassenRecur(aTemp, bTemp, newSize);
            Matrix h2 = aTemp.multiply(bTemp);
            // h3 = (a11) * (b12 - b22)
            aTemp = a11;
            bTemp = b12.subtract(b22);
            //Matrix h3 = strassenRecur(aTemp, bTemp, newSize);
            Matrix h3 = aTemp.multiply(bTemp);
            // h4 = (a22) * (b21 - b11)
            aTemp = a22;
            bTemp = b21.subtract(b11);
            //Matrix h4 = strassenRecur(aTemp, bTemp, newSize);
            Matrix h4 = aTemp.multiply(bTemp);
            // h5 = (a11+a12) * (b22)
            aTemp = a11.add(a12);
            bTemp = b22;
            //Matrix h5 = strassenRecur(aTemp, bTemp, newSize);
            Matrix h5 = aTemp.multiply(bTemp);
            // h6 = (a21-a11) * (b11+b12)
            aTemp = a21.subtract(a11);
            bTemp = b11.add(b12);
            //Matrix h6 = strassenRecur(aTemp, bTemp, newSize);
            Matrix h6 = aTemp.multiply(bTemp);
            // h7 = (a12-a22) * (b21+b22)
            aTemp = a12.subtract(a22);
            bTemp = b21.add(b22);
            // h7 = strassenRecur(aTemp, bTemp, newSize);
            Matrix h7 = aTemp.multiply(bTemp);

            // calculating c11, c12, c21, c22
            // c11 = h1 + h4 - h5 + h7
            Matrix c11 = h1.add(h4).subtract(h5).add(h7);
            // c12 = h3 + h5
            Matrix c12 = h3.add(h5);
            // c21 = h2 + h4
            Matrix c21 = h2.add(h4);
            // c22 = h1 + h3 - h2 + h6
            Matrix c22 = h1.add(h3).subtract(h2).add(h6);

            // Grouping the results obtained in a single matrix
            Matrix c = new Matrix(n, n);
            for (int i = 0; i < newSize; i++){
                for (int j = 0; j < newSize; j++) {
                    c.m[i*n + j] = c11.m[i*newSize + j];
                    c.m[i*n + j+newSize] = c12.m[i*newSize + j];
                    c.m[(i+newSize)*n + j] = c21.m[i*newSize + j];
                    c.m[(i+newSize)*n + j+newSize] = c22.m[i*newSize + j];
                }
            }
            return c;
        }
    }

    public static void main(String[] args) throws Exception {
        Matrix a, b;

        String filePath = "HW5c.txt";
        Scanner in = new Scanner(new FileInputStream(filePath));
        int cases = Integer.parseInt(in.nextLine().split(" ")[1]);
        while(cases > 0){
            String line = in.nextLine();
            String[] temp = line.split(" ");
            int rows = Integer.parseInt(temp[0]);
            int cols = Integer.parseInt(temp[1]);

            a = Matrix.inputMatrix(in, rows, cols);
            b = Matrix.inputMatrix(in, rows, cols);

            a = padding(a);
            b = padding(b);
            int n = a.cols;

            long startTime=System.currentTimeMillis();//start time

            Matrix result = strassenRecur(a, b, n);
            result = cut(result, rows, cols); // cut into original size
            System.out.println(result);
            long endTime=System.currentTimeMillis(); //endtime
            System.out.println("Total time: "+ (endTime-startTime)+"ms");
            cases--;
        }
    }
}