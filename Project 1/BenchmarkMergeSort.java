/**
 * Author: Opeyemi Ajisegiri
 * Class:  CMSC 451
 * Project: 1
 * File: BenchmarkMergeSort.java
 * Purpose: The main class in the program running the JVM warm up and benchmarking mergesort.
 * **/

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BenchmarkMergeSort {
	public static void warmUP() {
        //Create new int array to be sorted
        int[] tempArray = new int[20];
        //Running a for loop to sort the array 100,000 times to warm up JVM
        //while continuously filling the array with random integers between 0 and 99.
        for(int i=0;i<1000;i++) {
        	MergeSort rec = new MergeSort();
        	MergeSort itr = new MergeSort();
            for(int k = 0; k < tempArray.length; k++) {
                tempArray[k] = (int) (Math.random() * 100 + 1);
            }
            //Recursively and iteratively sorting the tempArray
            rec.recursiveSort(tempArray);
            itr.iterativeSort(tempArray);
        }
	}
	
	public static void generateReport() throws IOException {
		MergeSort recursion = new MergeSort();
		MergeSort iterative = new MergeSort();
		
		File report = new File("../MergeSortReports");
		report.mkdir();
		
		PrintWriter recursive = new PrintWriter(new FileWriter("../MergeSortReports/recursion.txt"));
		PrintWriter iterate   = new PrintWriter(new FileWriter("../MergeSortReports/iteravtive.txt"));
		
		for(int i = 200; i <= 2000; i+=200) {
			//Printing the data size
			recursive.print(i + " ");
			iterate.print(i + " ");
			int[] array = new int[i];
			//Generate 50 dataset for each n
			for(int j = 0; j < 50; j++) {
				//Filling the array with random integers per array
				for(int b = 0; b < array.length; b++) {
					array[b] = (int)(Math.random()* 1000 + 1);
				}
				//Recursively and Iteratively sort the each array, printing the count and time efficiency
				recursion.recursiveSort(array);
				recursive.print(recursion.getCount() + " " + recursion.getTime() + " ");
				iterative.iterativeSort(array);
				iterate.print(iterative.getCount() + " " + iterative.getTime() + " ");
				//Reset counters
				recursion.setCount(0);
				iterative.setCount(0);
			}
			recursive.print("\n");
			iterate.print("\n");
		}
		recursive.close();
		iterate.close();
	}

	public static void main(String[] args) throws IOException {
		
		warmUP();
		generateReport();

	}

}
