/**
 * Created by yue on 3/24/2015.
 */
public class LinkedList2 {
    private static class Node {
        int val;
        Node next;
        Node prev;
        public Node(int v, Node n, Node p){
            val = v;
            next = n;
            prev = p;
        }
    }
    private Node head;
    public LinkedList2(){
        head = null;
    }

    public void addFirst(int v){
        head = new Node(v, head, null);
    }

    public void addLast(int v){
        Node temp = head;
        if(temp == null){
            return;
        }
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = new Node(v, null, temp);
    }

    public void insertMiddle(int tar, int v){
        Node temp;
        for(Node p = head; p != null; p = p.next){
            if(p.val == tar){
                temp  =p.prev;
                p.prev = new Node(v, p, p.prev);
                if(temp != null){
                    temp.next = p.prev;
                }
            }
        }
    }
}
