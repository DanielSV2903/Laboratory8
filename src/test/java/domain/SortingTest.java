package domain;

import org.junit.jupiter.api.Test;
import util.Utility;

import javax.print.DocFlavor;

import static org.junit.jupiter.api.Assertions.*;

class SortingTest {
    private String elementarySorting(String algorithm,int[] a,int n){
        StringBuilder result=new StringBuilder();
        int copyOf[]= Utility.copyArray(a);
        switch (algorithm){
            case "bubble":
                Elementary.bubbleSort(a);
                break;
            case "improvedBubble":
                Elementary.improvedBubbleSort(a);
                break;
            case "selectionSort":
                Elementary.selectionSort(a);
                break;
            case "countingSort":
                Elementary.countingSort(a);
                break;
        }
        result.append("\n"+algorithm +"- Test\n" +
                "Algorithm: "+algorithm +"\n" +
                "Original Array: ");
        result.append(arrayToString(copyOf,copyOf.length));
        result.append("\nSorted Array: "+arrayToString(a,n));
        if (algorithm.equals("countingSort")){
            result.append("\nCounter Array: "+arrayToString(Elementary.getCounterArray(),a.length));
        }else {
        result.append("\nTotal Changes: "+Elementary.getTotalChanges());
        result.append("\nTotal Iterations: "+Elementary.getTotalIterations());
        }
        return result.toString();
    }

    private String arrayToString(int[] a,int n) {
        StringBuilder builder=new StringBuilder();
        builder.append("{");
        for (int i=0;i<n;i++){
            builder.append(a[i]+",");
        }
        builder.append("}");
        return builder.toString();
    }

    @Test
    void elementaryTest(){
    int[] a = util.Utility.getIntegerArray(1000);
    int[] b = util.Utility.copyArray(a);
    System.out.println(elementarySorting("bubble",a,50));
    System.out.println(elementarySorting("improvedBubble",b,100));
    System.out.println(elementarySorting("selectionSort",util.Utility.getIntegerArray(1000),150));
    System.out.println(elementarySorting("countingSort",util.Utility.getIntegerArray(1000),200));




    }

}