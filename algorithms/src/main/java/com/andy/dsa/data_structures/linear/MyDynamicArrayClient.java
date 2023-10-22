package com.andy.dsa.data_structures.linear;

public class MyDynamicArrayClient {
    public static void main(String[] args) {
        MyDynamicArray<Integer> myDynamicArray = new MyDynamicArray<>();

        myDynamicArray.add(10);
        myDynamicArray.add(20);
        myDynamicArray.add(30);

        System.out.println("Dynamic Array: " + myDynamicArray + ", Size: " + myDynamicArray.size());

        myDynamicArray.add(1, 15);
        myDynamicArray.set(2, 25);
        myDynamicArray.remove(15);

        System.out.println("Dynamic Array after modifications: " + myDynamicArray + ", Size: " + myDynamicArray.size());

        int elementAtIndex2 = myDynamicArray.get(2);
        System.out.println("Element at index 2: " + elementAtIndex2);

        System.out.println("Iterating through the array...");
        for(Integer element : myDynamicArray) {
            System.out.println(element);
        }
    }
}
