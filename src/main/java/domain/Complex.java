/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import util.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Profesor Gilberth Chaves A <gchavesav@ucr.ac.cr>
 */
public class Complex {
    private static int counterRadix[];

    public static int[] getCounterRadix() {
        return counterRadix;
    }

    public static int[] getPivotArray() {
        return pivotArray;
    }

    public static int[] getLowArray() {
        return lowArray;
    }

    public static int[] getHighArray() {
        return highArray;
    }

    private static int[] pivotArray;
    private static int[] lowArray;
    private static int[] highArray;
    private static int RecursiveCount;

    public static List<int[]> getGapsList() {
        return gapsList;
    }

    private static List<int[]> gapsList;
    public static int[] getCounterArray() {
        return counterRadix;
    }
    public static int getRecursiveCount() {return RecursiveCount;}

    public static List<Integer> getGapValues() {
        return gapValues;
    }

    private static List<Integer> gapValues;

    static {
        gapsList = new ArrayList<>();
        gapValues = new ArrayList<>();
    }

    // Método para inicializar los arreglos pivotArray
    public static void initArrays(int size) {
        pivotArray = new int[size];
        lowArray = new int[size];
        highArray = new int[size];
        RecursiveCount = 0;
    }

    // QuickSort modificado para guardar los pivots
    public static void quickSort(int arr[], int low, int high, int pivotIndex, int lowIndex, int highIndex) {
        if (low < high) {
            int i = low;
            int j = high;
            int pivot = arr[(low + high) / 2];

            pivotArray[pivotIndex] = pivot;
            comprobadorTamanoArrays(arr,lowIndex,highIndex);
            lowArray[lowIndex] = low;
            highArray[highIndex] = high;
            RecursiveCount++;

            do {
                while (arr[i] < pivot) {
                    i++;
                }
                while (arr[j] > pivot) {
                    j--;
                }

                lowIndex++;
                highIndex++;
                comprobadorTamanoArrays(arr,lowIndex,highIndex);
                lowArray[lowIndex]=i;
                highArray[highIndex]=j;

                if (i <= j) {
                    int aux = arr[i];
                    arr[i] = arr[j];
                    arr[j] = aux;
                    i++;
                    j--;
                }
            } while (i <= j);

            if (low < j) quickSort(arr, low, j, pivotIndex + 1, lowIndex+1, highIndex+1);
            if (i < high) quickSort(arr, i, high, pivotIndex + 1, lowIndex+1, highIndex+1);
        }
    }
    private static void comprobadorTamanoArrays(int arr[], int lowIndex, int highIndex){
        int[] aux = null;
        if (lowIndex==arr.length) {
            aux = Utility.copyArray(lowArray);
            lowArray=new int[aux.length+10];
            for (int k=0;k<aux.length;k++) lowArray[k] = aux[k];
        }
        if (highIndex==arr.length) {
            aux = Utility.copyArray(highArray);
            highArray=new int[aux.length+10];
            for (int k=0;k<aux.length;k++) highArray[k] = aux[k];
        }
    }

    public static void radixSort(int a[], int n){
        // Find the maximum number to know number of digits
        int m = util.Utility.maxArray(a); //va de 0 hasta el elemento maximo

        // Do counting sort for every digit. Note that instead
        // of passing digit number, exp is passed. exp is 10^i
        // where i is current digit number
        for (int exp = 1; m/exp > 0; exp *= 10)
            countSort(a, n, exp);
    }

    // A function to do counting sort of a[] according to
    // the digit represented by exp.
    private static void countSort(int a[], int n, int exp){
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];

        // Store count of occurrences in count[]
        for (i = 0; i < n; i++) {
            count[(a[i] / exp) % 10]++;
        }
        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        counterRadix= Utility.copyArray(count);

        // Build the output array
        for (i = n - 1; i >= 0; i--) {
            output[count[ (a[i]/exp)%10 ] - 1] = a[i];
            count[ (a[i]/exp)%10 ]--;
        }

        // Copy the output array to a[], so that a[] now
        // contains sorted numbers according to curent digit
        for (i = 0; i < n; i++)
            a[i] = output[i];


    }

    public static void mergeSort(int a[], int tmp[], int low, int high, int index){

        lowArray[index] = low;
        highArray[index] = high;

        RecursiveCount++;
        if(low<high){

            int center = (low+high)/2;

            mergeSort(a,tmp,low,center, index+1);
            mergeSort(a,tmp,center+1,high, index+1);
            merge(a,tmp,low,center+1,high);
        }//if
    }

    private static void merge(int a[], int tmp[], int lowIndex, int highIndex, int endIndex){
	int leftEnd = highIndex - 1;
	int tmpPos = lowIndex;
	int numElements = endIndex - lowIndex + 1;
	while( lowIndex <= leftEnd && highIndex <= endIndex )
            if(a[lowIndex]<=a[highIndex]) {
                tmp[tmpPos++] = a[lowIndex++];
            }
            else{
                tmp[tmpPos++] = a[highIndex++];
            }
	while(lowIndex<= leftEnd) {
        tmp[tmpPos++] = a[lowIndex++];
    }
	while( highIndex <= endIndex ) {
        tmp[tmpPos++] = a[highIndex++];
    }
	for( int i=0;i<numElements;i++,endIndex--)
            a[endIndex] = tmp[endIndex];
    }

    public static void shellSort(int a[]) {
        int n = a.length;
        int x=0;
        // Start with a big gap, then reduce the gap
        for (int gap = n/2; gap > 0; gap /= 2){
            gapValues.add(gap);
                // Do a gapped insertion sort for this gap size.
            // The first gap elements a[0..gap-1] are already 
            // in gapped order keep adding one more element 
            // until the entire array is gap sorted
            if (x<3){
                int[] copyOf = Utility.copyArray(a);
                gapsList.add(copyOf);
                x++;
            }
            for (int i = gap; i < n; i += 1){
                // add a[i] to the elements that have been gap
                // sorted save a[i] in temp and make a hole at 
                // position i 
                int temp = a[i];
                // shift earlier gap-sorted elements up until
                // the correct location for a[i] is found
                int j;
                for (j = i; j >= gap && a[j - gap] > temp; j -= gap)
                    a[j] = a[j - gap];

                // put temp (the original a[i]) in its correct 
                // location 
                a[j] = temp; 
            }
        } 
    }

}