package week_3.quicksort;

import edu.princeton.cs.algs4.Quick3way;
import edu.princeton.cs.algs4.StdRandom;
import week_3.PrintUtil;

/**
 * In case of many same-value items in an array with numerous length, this 3 way partitioning quick sort was introduced back in the 1990s
 * FIXME Wrong output
 */
public class ThreewayQuickSort extends QuickSort{

    public static void main(String[] args){
        Integer[] array = new Integer[]{1,6,22,43,-19,2,6,4,3,6,1,43,7,22,-19};
        threeWayQuickSort(array, 0 , array.length - 1);
//        Quick3way.sort(array);
        PrintUtil.printArray(0, array);
    }

    private static void threeWayQuickSort(Integer[] a){
        StdRandom.shuffle(a);
        threeWayQuickSort(a,0, a.length - 1);
        assert isSorted(a);
    }

    private static void threeWayQuickSort(Integer[] a, int low, int high){

        if(high <= low){
            return;
        }

        StdRandom.shuffle(a);

        int lowBorder = low;
        int highBorder = high;

        int runner = low;

        while(runner <= highBorder){
            int compare = a[runner].compareTo(a[low]);
            if(compare > 0){
                exchange(a,runner, highBorder--);
            }else if(compare < 0){
                exchange(a,lowBorder++, runner++);
            }else{
                runner++;
            }
        }

        threeWayQuickSort(a, low,lowBorder - 1);
        threeWayQuickSort(a, highBorder + 1, high);
        assert isSorted(a, low, high);
    }

    /***************************************************************************
     *  Check if array is sorted - useful for debugging.
     ***************************************************************************/
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


}
