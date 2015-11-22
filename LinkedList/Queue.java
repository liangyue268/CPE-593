import java.lang.reflect.Array;

/**
 * Created by yue on 3/24/2015.
 */
public class Queue {
    private int[] array;
    private int head, tail;

    public Queue(int size){
        array = new int[size];
        head = 0;
        tail = 0;
    }

    public void enqueue(int v){
        array[tail] = v;
        tail++;
        if(tail == array.length)
            tail = 0;
        if(tail == head){
            //grow();
        }
    }

    public int dequeue(){
        int temp = array[head++];
        if(head >= array.length)
            head = 0;
        return temp;
    }

    public boolean isEmpty(){
        return head == tail;
    }
}
