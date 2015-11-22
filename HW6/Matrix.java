import java.io.*;
import java.util.Scanner;

/**
 * Created by yue on 4/9/2015.
 */
public class Matrix {
    protected int rows;
    protected int cols;
    protected double[] m;

    Matrix(int rows, int cols){
        this.cols = cols;
        this.rows = rows;
        this.m = new double[cols*rows];
    }

    Matrix(Matrix orig){
        this.cols = orig.cols;
        this.rows = orig.rows;
        this.m = new double[cols*rows];
        for(int i=0; i<m.length; i++){
            this.m[i] = orig.m[i];
        }
    }

    public Matrix add(Matrix b){
        if(this.rows != b.rows || this.cols != b.cols)
            return null;
        Matrix x = new Matrix(this.rows, this.cols);
        for(int i=0; i<x.m.length; i++){
            x.m[i] = this.m[i] + b.m[i];
        }
        return x;
    }

    public Matrix subtract(Matrix b){
        if(this.rows != b.rows || this.cols != b.cols)
            return null;
        Matrix x = new Matrix(this.rows, this.cols);
        for(int i=0; i<x.m.length; i++){
            x.m[i] = this.m[i] - b.m[i];
        }
        return x;
    }

    public Matrix multiply(Matrix b){
        if(this.cols != b.rows)
            return null;
        Matrix x = new Matrix(this.rows, b.cols);
        int c=0;
        for(int i=0; i<this.rows; i++){
            for(int j=0; j<b.cols; j++){
                double temp = 0.0;
                for(int k=0; k<this.cols; k++){
                    temp += this.m[i*this.cols+k] * b.m[k*b.cols+j];
                }
                x.m[c++] = temp;
            }
        }
        return x;
    }

    private void swapRows(int pivotRow, int selectedRow){
        for(int i=0; i<cols; i++){
            double temp = m[pivotRow*cols+i];
            m[pivotRow*cols+i] = m[selectedRow*cols+i];
            m[selectedRow*cols+i] = temp;
        }
    }

    public Matrix solve(){
        if(cols != rows+1)
            return null;

        for(int i=0; i<rows-1; i++){
            //pivoting
            int pivotRow = i;
            for(int p=i; p<rows; p++){
                if(Math.abs(m[p*cols+i]) > Math.abs(m[pivotRow*cols+i]))
                    pivotRow = p;
            }
            if(pivotRow != i)
                swapRows(pivotRow, i);
            //divide by the first element
            double divisor = m[i*cols+i];
            for(int j=i; j<cols; j++){
                m[i*cols+j] /= divisor;
            }
            //elimination  j:working row
            for(int j=i+1; j<rows; j++){
                double factor = -m[j*cols+i];
                for(int k=i; k<cols; k++){
                    m[j*cols+k] += factor * m[i*cols+k];
                }
            }
        }
        //divide the last row
        double divisor = m[(rows-1)*cols+(cols-2)];
        m[(rows-1)*cols+(cols-2)] /= divisor;
        m[(rows-1)*cols+(cols-1)] /= divisor;

        //return result matrix
        Matrix result = new Matrix(rows, 1);
        for(int i=rows-1; i>=0; i--) {
            double temp = m[i * cols + cols - 1];
            for (int j = i + 1; j < cols - 1; j++) {
                temp += -m[i * cols + j] * result.m[j];
            }
            result.m[i] = temp;
        }
        return result;
    }

    public static Matrix concat(Matrix a, Matrix c){
        if(a.rows != c.rows)
            return null;
        Matrix ac = new Matrix(a.rows, a.cols+c.cols);
        int n = 0;
        for(int i=0; i<a.rows; i++){
            for(int j=0; j<a.cols; j++){
                ac.m[n] = a.m[i*a.rows + j];
                n++;
            }
            //ac.m[n] = c.m[i];
            for(int j=0; j<c.cols; j++){
                ac.m[n] = c.m[i*c.cols + j];
                n++;
            }
        }
        return ac;
    }

    public Matrix transpose(){
        Matrix temp = new Matrix(cols, rows);
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                temp.m[j*rows + i] = this.m[i*cols + j];
            }
        }
        return temp;
    }

    @Override
    public String toString(){
        String s = "";
        int c = 0;
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                //s += m[i*cols + j] + " ";
                //s += m[c++] + " ";
                s += String.format("% 8.4f", m[c++]);
            }
            if(i != rows-1){
                s += "\n";
            }
        }
        return s;
    }

    public static Matrix inputMatrix(Scanner in, int rows, int cols) throws Exception{
        Matrix a = new Matrix(rows, cols);
        int c = 0;
        for(int i = 0; i < rows; i++){
            String[] temp = in.nextLine().split(" ");
            for(int j = 0; j < cols; j++){
                a.m[c++] = Double.parseDouble(temp[j]);
            }
        }
        return a;
    }

    public static void main(String[] args) throws Exception{
        Matrix a=null, b=null, c=null, ac=null;
        PrintWriter out = null;

        String filePath = "HW5.txt";
        Scanner in = new Scanner(new FileInputStream(filePath));
        int cases = Integer.parseInt(in.nextLine().split(" ")[1]);
        while(cases > 0){
            String line = in.nextLine();
            String[] temp = line.split(" ");
            int rows = Integer.parseInt(temp[0]);
            int cols = Integer.parseInt(temp[1]);

            a = Matrix.inputMatrix(in, rows, cols);
            b = Matrix.inputMatrix(in, rows, cols);
            c = Matrix.inputMatrix(in, 1, cols);
            ac = Matrix.concat(a, c.transpose());
            cases--;

            out = new PrintWriter("result.txt");
            out.println(a.add(b));
            out.println(a.multiply(b));
            out.println(ac.solve().transpose());

        }
        in.close();
        out.close();
        /*System.out.println(a.add(b));
        System.out.println(a.multiply(b));
        System.out.println(ac.solve());
        /*Matrix b = new Matrix(a);
        System.out.println(a.add(b));
        System.out.println("--------------");
        System.out.println(a.multiply(b));*/
    }
}