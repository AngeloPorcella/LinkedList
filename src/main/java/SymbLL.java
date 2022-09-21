import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SymbLL <K extends Comparable<K>, V> {

    static int n = 0;//Size of the linked list
    private Node<K,V> head;

    private static class Node<K,V>{//Nested Node Class

        private K key;
        private V val;
        private Node<K,V> next;

        public Node(K key, V val) {//Constructor for nodes
            this.key = key;
            this.val = val;
            this.next = null;
        }
    }
    public void put(K key, V val){
        if (head==null){
            head = new Node<>(key, val);
        }
        if(contains(key)) {
            replaceDupe(key, val);
            System.out.println(key + " just got it's value replaced with " + val);
        }
        else{
            Node<K,V> oldHead = head;
            head = new Node<>(key, val);
            head.next = oldHead;
        }
        n++;
//        System.out.println(n);
//        System.out.println(head.key);
    }

    public V get(K key){
        Node<K,V> current = head;
        while (current.key != key){
            if (current.next == null){
                return null;
            }
            current = current.next;
        }
        return current.val;
    }

    public void replaceDupe(K key, V val){//Checks for duplicate value in list then replaces it
        Node<K,V> current = head;
        while (current.key != key){
            if (current.next == null){
                throw new NoSuchElementException();
            }
            current = current.next;
        }
        current.val = val;
    }

    public boolean contains(K key){
        Node<K,V> current = head;
        while (current.key != key){
            if (current.next == null){
                return false;
            }
            current = current.next;
        }
        return true;
    }

    public Node<K,V> getNext(K key){
        Node<K,V> current = head;
        while (current.key != key){
            if (current.next == null){
                return null;
            }
            current = current.next;
        }
        return current.next;
    }

    public Node<K,V> getPrev(K key){
        Node<K,V> current = head;
        Node<K,V> previous = null;
        while (current.key != key){
            if (current.next == null){
                return null;
            }
            previous = current;
            current = current.next;
        }
        if (previous == null){return null;}
        return previous;
    }

    public void printListContents(){
        Node<K,V> current = head;
        while (current != null){
            System.out.println(current.key + "\t" + current.val);
            current = current.next;
        }
    }

    public V delete(K key){//Finish PLS
        if (contains(key)){
            Node<K,V> temp = head;
            Node<K,V> previousNode = getPrev(key);
            Node<K,V> nextNode = getPrev(key);
            if (previousNode == null){
                head = head.next;
                return temp.val;
            }
            else if (nextNode == null){
                temp = previousNode.next;
                previousNode.next = null;
                return temp.val;
            }
            else{
                temp = previousNode.next;
                previousNode.next = nextNode;
                return temp.val;
            }

        }
        else{
            throw new NoSuchElementException();
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        SymbLL<Integer, Integer> intList = new SymbLL<>();

        Scanner fileIn = new Scanner(System.in);//Scanner for filename
        System.out.println("Enter File Name");
        String filename = fileIn.nextLine();
        Scanner scan = new Scanner(new FileReader("src/main/resources/" + filename + ".txt"));

        while(scan.hasNext()){//Scans through file and adds key value pairs to the TreeMap
            Integer key = Integer.parseInt(scan.next());
            Integer value = Integer.parseInt(scan.next());
            intList.put(key,value);
        }
        Scanner keySearch = new Scanner(System.in);//Scanner for key search
        System.out.println("Enter Key to Search");
        int k = Integer.parseInt(keySearch.nextLine());//What Key do you want to search for?

        if (intList.contains(k)){//Checks to see if TreeMap has desired Key, then prints the pair and deletes it.
            System.out.println("The Key " + k +" Exists and it's value pair is " + intList.get(k));
            System.out.println("Deleting Key/Value pair for key: " + k);
            intList.delete(k);
            System.out.println("Current Key Value Pairs in the TreeMap: ");
            intList.printListContents();
        }
        else{
            System.out.println(k + " Is not within the Table.");
        }
//        System.out.println("The Head is" + intList.head.key);

//        while (intList.next != null){}
    }
}
