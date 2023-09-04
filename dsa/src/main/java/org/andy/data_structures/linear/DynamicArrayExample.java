package org.andy.data_structures.linear;

import java.util.ArrayList;

public class DynamicArrayExample {

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();

        arrayList.add(10); // O(1) average time complexity, O(1) space complexity
        arrayList.add(20);
        arrayList.add(30);

        System.out.println("Dynamic ArrayList: " + arrayList + ", Size: " + arrayList.size());

        arrayList.add(1, 15); // O(n) time complexity, O(1) space complexity
        arrayList.set(2, 25); // O(1) time complexity, O(1) space complexity
        arrayList.remove(Integer.valueOf(15)); // Remove by value: O(n) time complexity, O(1) space complexity

        System.out.println("Dynamic ArrayList after modifications: " + arrayList + ", Size: " + arrayList.size());

        int elementAtIndex2 = arrayList.get(2); // O(1) time complexity, O(1) space complexity
        System.out.println("Element at index 2: " + elementAtIndex2);

        arrayList.clear(); // O(1) time complexity, O(1) space complexity
        System.out.println("Dynamic ArrayList after clearing: " + arrayList + ", Size: " + arrayList.size());
    }
}
