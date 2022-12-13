package cardealer.collections;

import cardealer.collections.chainlist.ChainList;
import cardealer.collections.chainlist.ChainedIterator;
import cardealer.collections.insertionordermap.InsertionOrderMap;
import cardealer.collections.linkedlist.LinkedList;
import cardealer.collections.sortedarray.SortedArray;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Moussa
 */
public class CollectionsDemo {
    public static void main(String[] args) {
        testSortedArray();
        System.out.println();
        testLinkedList();
        System.out.println();
        testChainList();
        System.out.println();
        testInsertionOrderMap();
    }

    public static void testSortedArray() {
        SortedArray<Integer> sortedArray = new SortedArray<>(Integer::compareTo);
        sortedArray.addAll(Arrays.asList(10, 1, 2, 5, 3, 4, 9, 8, 7, 6));
        for (Integer integer : sortedArray) {
            System.out.print(integer);
        }
    }

    public static void testLinkedList() {
        LinkedList<String> linkedlist = new LinkedList<>();
        linkedlist.add("Hello");
        linkedlist.add("how");
        linkedlist.add("are");
        linkedlist.add("You?");
        for (String s : linkedlist) {
            System.out.println(s);
        }
    }

    public static void testChainList() {
        ChainList<Object> chainList = new ChainList<>();
        chainList.add(Arrays.asList("Hello", "how", "are", "You?"));
        chainList.add(Arrays.asList("I", "am", "fine", "thank", "you."));
        chainList.add(Arrays.asList(1, 2, 3));
        for (ChainedIterator<Object> it = chainList.chainedIterator(); it.hasNext(); ) {
            Object s = it.next();
            System.out.println(s);
        }
    }

    public static void testInsertionOrderMap() {
        InsertionOrderMap<Integer, String> map = new InsertionOrderMap<>();

        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(5, "Five");
        for (Integer key : map.keyList()) {
            System.out.println(key + ": " + map.get(key));
        }

        System.out.println();

        map.put(1, "One again");
        map.put(2, "Two again");
        map.put(5, "Five again");
        map.put(0, "Zero");
        map.put(6, "Six");
        for (Integer key : map.keyList()) {
            System.out.println(key + ": " + map.get(key));
        }

        System.out.println();

        System.out.println("Default iteration order:");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}