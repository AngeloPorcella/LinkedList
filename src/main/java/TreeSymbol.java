import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.TreeMap;

//Angelo Porcella
//CSCI232 Lab 3
//9/19/2022

public class TreeSymbol<KEY extends Comparable<KEY>, VALUE> {

    private final TreeMap<KEY, VALUE> st;//Generic TreeMap

    public TreeSymbol(){//Constructor for our Generic Symbol Table
          st = new TreeMap<>();//Uses Built In Tree Map
    }


    public void put(KEY key, VALUE value){//Inserts key and value to tree
        if (key == null) throw new IllegalArgumentException();
        st.put(key, value);
    }

    public VALUE get(KEY key){//Returns value from key
        if (key == null) throw new IllegalArgumentException();
        return st.get(key);
    }

    public KEY min(){
        return st.firstKey();
    }

    public KEY ceiling(KEY key){
        if (st.higherKey(key) == null){
            return key;
        }
        return st.higherKey(key);
    }

    public KEY floor(KEY key){
        if (st.lowerKey(key) == null) {
            return key;
        }
        return st.lowerKey(key);
    }

    public KEY max(){
        return st.lastKey();
    }

    public boolean contains(KEY key){//Checks if a key contains a value
        return st.containsKey(key);
    }

    public void delete(KEY key){//Removes key and value pair
        st.remove(key);
    }
    public void printAllElements(){//Prints set showing all the key value pairs in the map
        System.out.println(st.entrySet());
    }

    public static void main(String[] args) throws FileNotFoundException {//Driver

        TreeSymbol<Integer, Integer> intTable = new TreeSymbol<>();//Constructs new

        Scanner fileIn = new Scanner(System.in);//Scanner for filename
        System.out.println("Enter File Name");
        String filename = fileIn.nextLine();
        Scanner scan = new Scanner(new FileReader("src/main/resources/" + filename + ".txt"));

        while(scan.hasNext()){//Scans through file and adds key value pairs to the TreeMap
            Integer key = Integer.parseInt(scan.next());
            Integer value = Integer.parseInt(scan.next());
            intTable.put(key,value);
        }

        Scanner keySearch = new Scanner(System.in);//Scanner for key search
        System.out.println("Enter Key to Search");
        int k = Integer.parseInt(keySearch.nextLine());//What Key do you want to search for?

        if (intTable.contains(k)){//Checks to see if TreeMap has desired Key, then prints the pair and deletes it.
            System.out.println("The Key " + k +" Exists and it's value pair is " + intTable.get(k));
            System.out.println("Deleting Key/Value pair for key: " + k);
            intTable.delete(k);
            System.out.println("Current Key Value Pairs in the TreeMap: ");
            intTable.printAllElements();
        }
        else{
            System.out.println(k + " Is not within the TreeMap.");
        }
        System.out.println("Smallest Key is " + intTable.min() + " And it's value pair is " + intTable.get(intTable.min()));
        System.out.println("Largest Key is " + intTable.max() + " And it's value pair is " + intTable.get(intTable.max()));
        System.out.println("The Next Smallest Key after " + intTable.min() + " is " + intTable.ceiling(intTable.min()) +
            " and it's value pair is " + intTable.get(intTable.ceiling(intTable.min())));
        System.out.println("The Next Largest Key before " + intTable.max() + " is " + intTable.floor(intTable.max()) +
                " and it's value pair is " + intTable.get(intTable.floor(intTable.max())));
    }

}
