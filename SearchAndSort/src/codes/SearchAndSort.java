package codes;

import java.util.Scanner;
import java.util.Random;
import java.io.*;

public class SearchAndSort {
	
	public static int errorTrap (int min, int max) {               //errorTrap
		Scanner input = new Scanner(System.in);
		int value = 0;
		boolean valid = true;
		do {
			valid = true;
			try {
				value = input.nextInt();
			} catch (Exception e) {                           //catches invalid input
				valid = false;
				String garbage = input.nextLine();
				System.out.println("Invalid Input. Please try again");
			}
			if (valid == true && (value < min || value > max)) {        //catches value which exceeds the range
				valid = false;
				String garbage = input.nextLine();
				System.out.println("Invalid Input. Please try again");
			}
		}while(!valid);
		return(value);
	}
	
	public static void sequentiallyPopulate (int[] numberList) {
		for(int x = 0; x < 100; x++) {
			numberList[x] = x + 1;
		}
	}
	
	public static void randomlyPopulate (int[] numberList) {
		System.out.println("Please pick the range of the numbers which will be genereated randomly. (range of 1 all the way to 1000)");
		int max = errorTrap(0, 1000);
		int min = 1;
		for(int x = 0; x < 100; x++) {
			numberList[x] = (int)(Math.random()*(max-min+1)+min);
		}	
	}
	
	public static void shuffle (int[] numberList){
		Scanner input = new Scanner(System.in);
		Random rand = new Random();
		Thread shuffle = new Thread(new Runnable() {              //thread is created where a value on the array is switched with another random number in the array until there is an input
			public void run() {
				while(true) {
					int arrayPlace = rand.nextInt(100);      //thread constantly chooses random positions to switch with
					int switchWith = rand.nextInt(100);
					int temp = numberList[arrayPlace];
					numberList[arrayPlace] = numberList[switchWith];
					numberList[switchWith] = temp;
				}
			}
		});
		System.out.println("Enter any key to stop shuffle.");
		shuffle.start();
		input.nextLine();
		shuffle.stop();    
	}
	
	public static void view (int[] numberList) {
		for(int x = 0; x < 100; x++) {
			if(x % 10 == 0) {
				System.out.println();
				System.out.print(numberList[x]);
			}
			else {
				System.out.print("\t" + numberList[x]);
			}
		}
		System.out.println();
	}
	
	public static boolean checkAscending (int[] numberList) {
		boolean ascending = true;
		for(int x = 0; x < 99; x++) {
			if(numberList[x] > numberList[x+1]) {
				ascending = false;
				break;
			}
			else {
				ascending = true;
			}
		}
		return(ascending);
	}
	
	public static void search (int[] numberList) {
		System.out.println("What number would you like to search for?");
		int search = errorTrap(0,100);
		int searchAddress[] = new int[100];                //this is accounting for the possibility that there is more than just one possible place for a certain number to be
		int addressSlot = 0;
		for(int x = 0; x < 100; x++) {
			if(numberList[x] == search) {
				searchAddress[addressSlot] = x;            //numbers are switched
				addressSlot++;
			}
		}
		if(addressSlot == 0) {
			System.out.print(search + " is not found in the array.");
		}
		else {
			System.out.print(search + " is found in the following locations on the array (starting at 0):");
			for(int x = 0; x < addressSlot; x++) {
				System.out.print(" " + searchAddress[x]);	
			}
		}
		System.out.println();
	}
	
	public static void bubbleSort (int[] numberList) {
		for(int x = 0; x < 100; x++) {
			for(int y = x; y < 100; y++) {
				if(numberList[x] > numberList[y]) {     //method will scan for the lowest number which will fit each consecutive spot in the array
					int temp = numberList[x];
					numberList[x] = numberList[y];
					numberList[y] = temp;
				}
			}
		}
	}
	
	public static void selectionSort (int[] numberList) {
		int lowerBound = 0;
		int upperBound = 99;
		for(int x = 0; x < numberList.length; x++) {
			int highestNumberAddress = 0;
			int lowestNumberAddress = 99;
			if(lowerBound == upperBound - 1) {
				break;
			}
			for(int y = lowerBound; y <= upperBound; y++) {
				if(numberList[y] < numberList[lowestNumberAddress]) {    //selects highest and lowest number within the set boundaries
					lowestNumberAddress = y;
				}
				if(numberList[y] > numberList[highestNumberAddress]) {
					highestNumberAddress = y;
				}
			}
			if(highestNumberAddress == lowerBound) {
				highestNumberAddress = lowestNumberAddress;
			}
			int temp = numberList[lowerBound];
			numberList[lowerBound] = numberList[lowestNumberAddress];  //switches the lowest number in the set with the lower bound's number and the highest number in the set with the upper bound's number
			numberList[lowestNumberAddress] = temp;
			temp = numberList[upperBound];
			numberList[upperBound] = numberList[highestNumberAddress];
			numberList[highestNumberAddress] = temp;
			lowerBound++;                                              //boundary is shrinked by one number on each side
			upperBound--;
		}
	}
	
	public static void insertionSort (int[] numberList) {
		int referencePoint = 0;
		int checker;
		while(referencePoint != 100) {
			for(int x = referencePoint; x < numberList.length; x++) { //number is checked with the numbers to the left of the reference point and is inserted in by shifting the numbers from the checker to the number up by one and the number is switched into the new position
				if(numberList[referencePoint] > numberList[x]) {    
					checker = referencePoint - 1;
					if(checker == -1) {
						checker++;
						int temp = numberList[x];
						for(int shift = x; shift > checker; shift--) {
							numberList[shift] = numberList[shift - 1];
						}
						numberList[checker] = temp;
					}
					else {
						while(numberList[checker] > numberList[x]) {
							checker--;
							if(checker == -1) {
								checker = 0;
								break;
							}
						}
						int temp = numberList[x];
						for(int shift = x; shift > checker; shift--) {
							numberList[shift] = numberList[shift - 1];
						}
						numberList[checker + 1] = temp;
						referencePoint++;
					}
					if(referencePoint == 100) {
						break;
					}
				}
			}
			referencePoint++;
		}
	}
	
	public static void binarySearch(int[] numberList) {
		int search;
		int splitFactor = numberList.length/2;             
		int referencePoint = numberList.length/2;         //reference is already halfway through the array
		if(checkAscending(numberList) == false) {
			System.out.println("Number List is not in order, Binary search cannot be done.");
			return;
		}
		System.out.println("Please input the number you desire to search for.");
		search = errorTrap(1, 100);
		while(search != numberList[referencePoint]) {            
			if(search > numberList[referencePoint]) {			//if search value is over the reference point
				splitFactor /= 2;
				referencePoint = referencePoint + splitFactor;
			}
			if(search < numberList[referencePoint]) {				//if search value is under the reference point
				splitFactor/= 2;
				referencePoint = referencePoint - splitFactor;
			}
			if(splitFactor == 1) {
				break;
			}
		}
		if(splitFactor == 1) {
			System.out.println("Number cannot be found.");
		}
		else {
			System.out.println("Number is found in position " + referencePoint + ".");
		}
	}
	
	public static void quickSort(int[] numberList, int start, int finish) {
		int pivot = start;
		int lowerSlider = start;
		int upperSlider = finish;
		int temp;
		while(lowerSlider != upperSlider) {                       //pivot is on lower slider to make the process easier
			if(numberList[pivot] > numberList[upperSlider]) {      
				temp = numberList[pivot];
				numberList[pivot] = numberList[upperSlider];
				numberList[upperSlider] = temp;
				pivot = upperSlider;
			}
			else if(numberList[pivot] < numberList[lowerSlider]) {  //incorporates the special case
				temp = numberList[pivot];
				numberList[pivot] = numberList[lowerSlider];
				numberList[lowerSlider] = temp;
				pivot = lowerSlider;
			}
			if(upperSlider > pivot) {  //boundaries become smaller
				upperSlider--;
			}
			if(lowerSlider < pivot) {
				lowerSlider++;
			}
		}
		if(finish - upperSlider >= 2) {
			quickSort(numberList, upperSlider + 1, finish);       //finishes upper half
		}
		if(lowerSlider - start >= 2) {
			quickSort(numberList, start, lowerSlider - 1);		//finishes lower half
		}
	}
	
	public static int[] radixSort(int[] numberList) {
		int[] sortingStation = new int[100];
		int digits = 0;
		int sortingStationNextPlacement = 0;
		do {
			for(int digitValue = 0; digitValue < 10; digitValue++) {          //sorts array by certain digit
				for(int x = 0; x < 100; x++) {
					double compared = numberList[x]/Math.pow(10,digits);   //check entire array for certain digit
					int compare = (int) compared;							//downcast double to int as number never compared correctly (might be redundant now)
					if(compare % 10 == digitValue && sortingStationNextPlacement != 100) {
						sortingStation[sortingStationNextPlacement] = numberList[x];
						sortingStationNextPlacement++;
					}
				}
			}
			numberList = sortingStation;
			sortingStation = new int[100];								//array reset
			digits++; 														//new digit is checked
			sortingStationNextPlacement = 0;
		}while(digits != 4);
		return(numberList);
	}

	public static void mergeSort(int[] numberList, int start, int finish) {
		if(finish > start) {                      //until the length of the array is one, the algorithm will constantly split until there is only one number
			int middle = (start + finish)/2;
			mergeSort(numberList, start, middle);      //starts lower half
			mergeSort(numberList, middle + 1, finish); 		//because lower half is length 1, the upper half is also 1 therefore it will move to the merge
			merge(numberList, start, middle, finish);
		}
		
	}
	
	public static void merge(int[] numberList, int left, int middle, int right) {
		int leftLength = middle - left + 1, 		
				rightLength = right - middle;
		int[] leftArray = new int[leftLength], 
				rightArray = new int[rightLength];
		
		for (int x = 0; x < leftLength; x++) 
			leftArray[x] = numberList[left + x]; 
        for (int x = 0; x < rightLength; x++) 
        	rightArray[x] = numberList[middle + 1+ x];
        
        int i = 0, j = 0; 		//sets left and right sliders
        int k = left; 			//sets numberList slider
        
        while (i < leftLength && j < rightLength) { 		//main sort
            if (leftArray[i] <= rightArray[j]) { 
                numberList[k] = leftArray[i]; 
                i++; 
            } 
            else { 
                numberList[k] = rightArray[j]; 
                j++; 
            } 
            k++; 
        } 
  
        while (i < leftLength) { 					//extra left
            numberList[k] = leftArray[i]; 
            i++; 
            k++; 
        } 
  
        while (j < rightLength) { 					//extra right
            numberList[k] = rightArray[j]; 
            j++; 
            k++; 
        } 
	}
	
	public static void main(String[] args) throws IOException{
		Scanner input = new Scanner(System.in);
		Random rand = new Random();
		int[] numberList = new int[100];
		int menuChoice = 0;
		do {
			System.out.println("---------------------------------------------------------------------------");
			System.out.println("What would you like to do?\n0. Exit Program\n1. Populate Array Sequentially\t2. Populate Array Randomly"
					+ "\n3. Shuffle Array\t\t4. View Array\n5. Check if Ascending\t\t6. Search Array for certain Value"
					+ "\n7. Sort into Ascending Order\t8. Selection Sort\n"
					+ "9. Insertion Sort\t\t10. Binary Search\n11. Quick Sort\t\t\t12. Radic Sort\n13. Merge Sort");
			System.out.println("---------------------------------------------------------------------------");
			menuChoice = errorTrap(0,13);
			if (menuChoice == 1) {					//Sequentially Populate Array
				sequentiallyPopulate(numberList);
			}
			if (menuChoice == 2) {					//Randomly Populate Array
				randomlyPopulate(numberList);
			}
			if (menuChoice == 3) {					//Shuffle Array
				shuffle(numberList);         
			}
			if (menuChoice == 4) {					//View Array
				view(numberList);
			}
			if (menuChoice == 5) {					//Check if Ascending
				if(!checkAscending(numberList)) {
					System.out.println("Array is not in ascending order.");
				}
				else {
					System.out.println("Array is in ascending order.");
				}
			}
			if (menuChoice == 6) {					//Search for Array Value
				search(numberList);
			}
			if (menuChoice == 7) {   				//sort array into ascending order
				bubbleSort(numberList);
			}
			if (menuChoice == 8) {                  //Selection Sort
				selectionSort(numberList);
			}
			if (menuChoice == 9) {					//Insertion Sort using the same array
				insertionSort(numberList);
			}
			if(menuChoice == 10) {					//Binary Search	
				binarySearch(numberList);
			}
			if(menuChoice == 11) {					//Quick Sort
				quickSort(numberList, 0, 99);
			}
			if(menuChoice == 12) {					//Radix Sort
				numberList = radixSort(numberList);
			}
			if(menuChoice == 13) {					//Merge Sort
				mergeSort(numberList, 0, 99);
			}
		}while (menuChoice != 0);
	}
}




