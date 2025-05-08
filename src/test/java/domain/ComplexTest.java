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
                Complex.quickSort(a,0,a.length-1);
                break;
            case "radixSort":
                Complex.radixSort(a,n);
                break;
            case "mergeSort":
                Complex.mergeSort(a,new int[a.length],0,a.length-1);
                break;
            case "shellSort":
                Complex.shellSort(a);
                break;
        }
        result.append("\n"+algorithm +"- Test\n" +
                "Algorithm: "+algorithm +"\n" +
                "Original Array: ");
        result.append(arrayToString(copyOf,copyOf.length));
        result.append("\nSorted Array: "+arrayToString(a,n));
        if (algorithm.equals("radixSort")){
            result.append("\nCounter Array: "+arrayToString(Complex.getCounterArray(),n));
        }
        if (algorithm.equals("shellSort")){
            result.append("\nGap (n/2) subArray1"+arrayToString(Complex.getGapsList().get(0),n));
            result.append("\nGap (n/2) subArray2"+arrayToString(Complex.getGapsList().get(1),n));
            result.append("\nGap (n/2) subArray3"+arrayToString(Complex.getGapsList().get(2),n));
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
        System.out.println(complexSorting("radixSort",
                util.Utility.getIntegerArray(40000), 50));
        System.out.println(complexSorting("shellSort",
                util.Utility.getIntegerArray(5000), 150));
    }

}