/**
 * Created by yue on 4/3/2015.
 */
public class Queue {
    private int[] array;
    private int head,tail;

    public Queue(int size){
        array=new int[size];
        head=tail=0;
    }

    public void enqueue(int v){
        array[tail]=v;
        tail++;
        if(tail==head){
            //grow();
            int[] temp = new int[2*array.length];
            for(int i=0; i<array.length; i++){
                temp[i] = array[i];
            }
            array = temp;
        }
        if(tail>=array.length)
            tail=0;
    }

    public int dequeue(){
        int temp=array[head++];
        if(head>=array.length)
            head=0;
        return temp;
    }

    public boolean isEmpty(){
        return head==tail;
    }

    public static void main(String[] args) {
        Queue queue = new Queue(10);
        queue.enqueue(10);
        queue.enqueue(4);
        System.out.print(queue.dequeue() + " ");
        queue.enqueue(6);
        System.out.print(queue.dequeue() + " ");
        System.out.print(queue.dequeue() + " ");
    }
}
