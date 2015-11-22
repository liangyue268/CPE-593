import java.io.File;
import java.util.Scanner;

/**
 * Created by yue on 5/1/2015.
 */
class Node{
    String key;
    int val;
    Node next;

    Node(String key, int val, Node next){
        this.key = key;
        this.val = val;
        this.next = next;
    }
}

class Pair{
    String key;
    int val;
     Pair(String key, int val){
         this.key = key;
         this.val = val;
     }
}

class Iterator{
    int current;
    int size;
    Node[] table;
    Node temp;

    Iterator(HashMap m){
        current = -1;
        size = m.size;
        table = m.table;
    }

    boolean hasNext(){
        current++;
        size--;
        if(current == table.length){
            return false;
        }
        while(table[current] == null){
            if(size == 0)
                return false;
            current++;
            size--;
        }
        temp = table[current];
        return true;
    }

    Pair getPair(){
        if(temp != null){
            Pair pair = new Pair(temp.key, temp.val);
            temp = temp.next;
            return pair;
        }
        return null;
    }
}

public class HashMap {
    int size;
    int used;
    Node[] table;

    HashMap(int initialSize){
        size = initialSize;
        used = 0;
        table = new Node[size];
    }

    private int hash(String key){
        int sum = 0;
        for (int i = 0; i < key.length(); i++)
            sum = sum + (sum << 13) + (sum >> 17) + key.charAt(i);
        return sum & (size-1);
    }

    private void grow(){
        System.out.println("doubling size, the new size is " + size);
        Iterator iterator = new Iterator(this);

        size *= 2;
        used = 0;
        table = new Node[size];

        while(iterator.hasNext()){
            Pair pair = iterator.getPair();
            while(pair != null){
                int val = pair.val;
                String key = pair.key;
                add(key, val);
                pair = iterator.getPair();
            }
        }
    }

    public int RSHash(String str)
    {
        int b     = 378551;
        int a     = 63689;
        long hash = 0;
        for(int i = 0; i < str.length(); i++)
        {
            hash = hash * a + str.charAt(i);
            a    = a * b;
        }
        return (int)Math.abs(hash % size);
    }

    public void add(String key, int val){
        int i = hash(key);
        Node temp = table[i];
        while(temp != null){
            if(temp.key.equals(key)) {
                temp.val = val;
                return;
            }
            temp = temp.next;
        }
        table[i] = new Node(key, val, table[i]);
        //when adding NEW element, used add 1
        used++;
        if(used == size){
            grow();
        }
    }

    public Integer get(String key){
        int i = hash(key);
        Node temp = table[i];
        while(temp != null){
            if(temp.key.equals(key)){
                return temp.val;
            }
            temp = temp.next;
        }
        return null;
    }

    public static void main(String[] args) throws Exception{
        HashMap dictionary = new HashMap(50000);
        HashMap wrongDict = new HashMap(50000);

        //read in dictionary
        Scanner in = new Scanner(new File("wordsEn.txt"));
        while(in.hasNextLine()){
            String dictWord = in.nextLine();
            dictionary.add(dictWord, 0);
        }

        //read in book
        in = new Scanner(new File("CompletWorksOfShakespeare.txt"));
        while(in.hasNextLine()){
            String line = in.nextLine();
            String[] words = line.split("[\\s\\.\\,\\;\\:]+");
            for(String word:words){
                if(word.equals(""))
                    continue;
                String lowerCaseWord = word.toLowerCase();
                Integer val = dictionary.get(lowerCaseWord);//case-insensitive
                if(val != null){
                    dictionary.add(lowerCaseWord, val+1);
                }
                else{
                    Integer val2 = wrongDict.get(lowerCaseWord);
                    if(val2 != null){
                        wrongDict.add(lowerCaseWord, val2 + 1);
                    }
                    else{
                        wrongDict.add(lowerCaseWord, 1);
                    }
                }
            }
        }

        //get top 100 words and its frequency
        int count = 0;
        int[] vals = new int[100];
        String[] keys = new String[100];
        Iterator iterator = new Iterator(dictionary);
        while(iterator.hasNext()){
            Pair pair = iterator.getPair();
            while(pair != null){
                count++;
                int val = pair.val;
                String key = pair.key;
                //insertion sort
                int idx = vals.length - 1;
                if(vals[idx] < val){
                    while(idx >= 1 && vals[idx-1] < val){
                        vals[idx] = vals[idx-1];
                        keys[idx] = keys[idx-1];
                        idx--;
                    }
                    vals[idx] = val;
                    keys[idx] = key;
                }
                pair = iterator.getPair();
            }
        }

        //display number of words and wrongWords
        System.out.printf("There are %d words in the dictionary\n", dictionary.used);
        System.out.printf("There are %d mis-spelled words in the book\n", wrongDict.used);

        //display top 10 words
        System.out.println("--------Top 10 words (case-insensitive)--------");
        for(int i = 0; i < 10; i++){
            System.out.println(keys[i] + "==>" + vals[i]);
        }
    }
}