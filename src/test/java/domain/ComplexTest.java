package domain;

import org.junit.jupiter.api.Test;
import util.Utility;

import static org.junit.jupiter.api.Assertions.*;

class ComplexTest {
    private String complexSorting(String algorithm,int[] a,int n){
        StringBuilder result=new StringBuilder();
        int copyOf[]= Utility.copyArray(a);
        switch (algorithm){
            case "quickSort":
                Complex.initArrays(a.length);
                Complex.quickSort(a,0,a.length-1, 0, 0, 0);
                break;
            case "radixSort":
                Complex.radixSort(a,n);
                break;
            case "mergeSort":
                Complex.initArrays(a.length);
                Complex.mergeSort(a,new int[a.length],0,a.length-1, 0);
                break;
            case "shellSort":
                Complex.shellSort(a);
                break;
        }
        result.append("\n"+algorithm +"- Test\n" +
                "Algorithm: "+algorithm +"\n" +
                "Original Array: ");
        switch(algorithm){
            case "quickSort":
                result.append(arrayToString(copyOf,n));
                break;
            case "radixSort":
                result.append(arrayToString(copyOf,n));
                break;
            case "mergeSort":
                result.append(arrayToString(copyOf,n));
                break;
            case "shellSort":
                result.append(arrayToString(copyOf,n));
                break;
        }
        result.append("\nSorted Array: "+arrayToString(a,n));
        if (algorithm.equals("radixSort")){
            result.append("\nCounter Array: "+arrayToString(Complex.getCounterRadix(),Complex.getCounterRadix().length));
        } else if (algorithm.equals("shellSort")){
            result.append("\nGap (n/2) subArray1"+arrayToString(Complex.getGapsList().get(0),150));
            result.append("\nGap (n/2) subArray2"+arrayToString(Complex.getGapsList().get(1),150));
            result.append("\nGap (n/2) subArray3"+arrayToString(Complex.getGapsList().get(2),150));
        } else {
            result.append("\nLow: " + arrayToString(Complex.getLowArray(), n));
            result.append("\nHigh: " + arrayToString(Complex.getHighArray(), n));
            if (algorithm.equals("quickSort"))
                result.append("\nPivot: " + arrayToString(Complex.getPivotArray(), n));
            result.append("\nRecursive Calls: " + Complex.getRecursiveCount());
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
    void complexTest(){
        System.out.println(complexSorting("radixSort", util.Utility.getIntegerArray(40000), 50));
        System.out.println(complexSorting("shellSort", util.Utility.getIntegerArray(5000), 150));
        System.out.println(complexSorting("quickSort",  util.Utility.getIntegerArray(8000), 30));
        System.out.println(complexSorting("mergeSort", util.Utility.getIntegerArray(15000), 100));
    }

}