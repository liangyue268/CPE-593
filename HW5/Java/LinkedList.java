import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by yue on 4/3/2015.
 */
public class LinkedList {
    private static class Node{
        int val;
        Node next;
        Node prev;
        public Node(int v,Node n,Node p){
            val=v;
            next=n;
            prev=p;
        }
    }
    private Node head;
    private Node tail;

    public LinkedList(){
        head=null;
        tail = null;
    }

    public LinkedList(int num){
        for(int i=0; i<num; i++){
            addLast(i+1);
        }
    }

    public void addFirst(int v){
        head=new Node(v,head,null);
        Node ptr = head;
        while(ptr.next != null){
            ptr = ptr.next;
        }
        tail = ptr;
    }

    public void deleteFirst(){
        if(head == null){
            return;
        }
        if(head == tail){ // only has one element
            tail = null;
        }
        head = head.next;
        head.prev = null;
    }

    public void addLast(int v){
        if(tail == null){
            tail=new Node(v,null,null);
            head = tail;
            return;
        }
        tail.next = new Node(v,null,tail);
        tail = tail.next;
    }

    public void deleteLast(){
        if(tail == null){
            return;
        }
        if(head == tail){
            head = null;
            tail = null;
        }
        tail = tail.prev;
        tail.next = null;
    }

    public void insertMiddle(int tar,int v){
        Node temp;
        for(Node p=head;p!=null;p=p.next){
            if(p.val==tar){
                temp=p.prev;
                p.prev=new Node(v,p,p.prev);
                if(temp!=null)
                    temp.next=p.prev;
                else
                    head = p.prev;
            }
        }
    }

    @Override
    public String toString(){
        String s = "";
        Node p = head;
        while(p != null){
            s += p.val + " ";
            p = p.next;
        }
        return s;
    }

    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(new FileInputStream("hw4ainp.dat"));
        int N = in.nextInt();
        int M = in.nextInt();
        int P = in.nextInt();
        LinkedList list = new LinkedList(N);
        for(int i=0; i<M; i++){
            list.deleteLast();
        }
        for(int i=0; i<P; i++){
            list.deleteFirst();
        }
        System.out.print(list);
    }
}