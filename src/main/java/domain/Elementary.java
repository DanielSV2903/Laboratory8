/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import util.Utility;

/**
 *
 * @author Profesor Lic. Gilberth Chaves A.
 */
public class Elementary {
    public static int getTotalChanges() {
        return TotalChanges;
    }

    public static int getTotalIterations() {
        return TotalIterations;
    }

    public static void setTotalChanges(int totalChanges) {
        TotalChanges = totalChanges;
    }

    public static void setTotalIterations(int totalIterations) {
        TotalIterations = totalIterations;
    }

    private static int TotalChanges;
    private static int TotalIterations;
    private static int[] CounterArray;

    public static int[] getCounterArray() {
        return CounterArray;
    }

    public static void bubbleSort(int a[]){
        TotalIterations=0;
        TotalChanges=0;
        for(int i=1;i<a.length;i++){
            TotalIterations++;
            for(int j=0;j<a.length-i;j++){
                if(a[j]>a[j+1]){
                    int aux=a[j];
                    a[j]=a[j+1];
                    a[j+1]=aux;
                    TotalChanges++;
                }//if
            }//for j
        }
    }
    
    public static void improvedBubbleSort(int a[]){
        TotalIterations=0;
        TotalChanges=0;
	    boolean swapped = true; //intercambiado
	    for(int i=1;swapped;i++){
            swapped = false;
            TotalIterations++;
            for(int j=0;j<a.length-i;j++){
                if(a[j]>a[j+1]){
                    int aux=a[j];
                    a[j]=a[j+1];
                    a[j+1]=aux;
                    swapped = true;
                    TotalChanges++;
                }//if
            }//for j
	    }//for i
    }
    
    public static void selectionSort(int a[]){
        TotalIterations=0;
        TotalChanges=0;
        for(int i=0;i<a.length-1;i++){
            int min=a[i];
            int minIndex=i;
            for(int j=i+1;j<a.length;j++){
                if(a[j]<min){
                    min=a[j];
                    minIndex=j;
                    TotalChanges++;
                }//if
            }//for j
            a[minIndex]=a[i];
            a[i]=min;
            TotalIterations++;
        }//for i
    }
    
    public static void countingSort(int a[]) {
        TotalIterations=0;
        TotalChanges=0;
        int max = util.Utility.maxArray(a); //va de 0 hasta el elemento maximo
        // create buckets
        int counter[] = new int[max + 1];
        // fill buckets
        for (int element : a) {
            counter[element]++; //incrementa el num de ocurrencias del elemento
        }
        CounterArray= Utility.copyArray(counter);
        // sort array
        int index = 0;
        for (int i = 0; i < counter.length; i++) {
            TotalIterations++;
            while (counter[i]>0) { //significa q al menos hay un elemento (q existe)
                a[index++] = i;
                counter[i]--;
                TotalChanges++;
            }
        }//for i
    }

}
