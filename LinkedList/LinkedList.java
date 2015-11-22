/**
 * Created by yue on 3/24/2015.
 */
public class LinkedList {
    private static class Node {
        int val;
        Node next;
        public Node(int v, Node n){
            val = v;
            next = n;
        }
    }
    private Node head;
    public LinkedList(){
        head = null;
    }

    public void addFirst(int v){
        head = new Node(v, head);
    }

    public void addLast(int v){
        Node temp = head;
        if(temp == null){
            return;
        }
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = new Node(v, null);
    }
}
