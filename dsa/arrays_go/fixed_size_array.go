package main

import (
	"fmt"
	"unsafe"
)

const ArraySize = 5

func main() {
	fixedSizeArrayExample()
}

func fixedSizeArrayExample() {
	var initialArray [ArraySize]float64 // Declare a fixed-size array with a size of 5
	fmt.Printf("Initial array: %v\n", initialArray)

	// Assign values to elements
	initialArray[0] = 10.8
	initialArray[1] = 14.3
	initialArray[2] = 13.5
	initialArray[3] = 12.1
	initialArray[4] = 9.7

	fmt.Printf("Array after assigning values: %v\n", initialArray)

	// Print the memory addresses of each element
	for i := 0; i < ArraySize; i++ {
		address := unsafe.Pointer(&initialArray[i])
		fmt.Printf("Element %d at address %p\n", i, address)
	}

	// Accessing an element by index: O(1) time complexity, O(1) space complexity
	thirdMeasurement := initialArray[2]
	fmt.Printf("Accessing element at index 2: %.1f\n", thirdMeasurement)

	// Searching for an element: O(n) time complexity, O(1) space complexity
	searchElement := 13.5
	searchIndex := -1
	for i := 0; i < ArraySize; i++ {
		if initialArray[i] == searchElement {
			searchIndex = i
			break
		}
	}
	fmt.Printf("Searching for %.1f at index: %d\n", searchElement, searchIndex)

	// Inserting an element at index 2: O(n) time complexity, O(n) space complexity
	newElement := 11.0
	insertIndex := 2
	arrayAfterInsertion := insertElement(initialArray, newElement, insertIndex)
	fmt.Printf("Array after inserting: %v\n", arrayAfterInsertion)

	// Deleting an element at index 4: O(n) time complexity, O(n) space complexity
	deleteIndex := 4
	arrayAfterDeletion := deleteElement(initialArray, deleteIndex)
	fmt.Printf("Updated array after deleting: %v\n", arrayAfterDeletion)
}

// Inserting an element: O(n) time complexity (due to shifting), O(n) space complexity (new array)
func insertElement(array [ArraySize]float64, element float64, index int) [ArraySize + 1]float64 {
	if index < 0 || index >= ArraySize {
		panic("Index is out of bounds")
	}

	var newArray [ArraySize + 1]float64

	for i := 0; i < ArraySize; i++ {
		if i == index {
			newArray[i] = element
		} else if i < index {
			newArray[i] = array[i]
		} else {
			newArray[i] = array[i-1]
		}
	}

	return newArray
}

// Deleting an element: O(n) time complexity (due to shifting), O(n) space complexity (new array)
func deleteElement(array [ArraySize]float64, index int) [ArraySize - 1]float64 {
	if index < 0 || index >= ArraySize {
		panic("Index is out of bounds")
	}

	var newArray [ArraySize - 1]float64

	for i := 0; i < ArraySize; i++ {
		if i != index {
			newArray[i] = array[i]
		}
	}

	return newArray
}
