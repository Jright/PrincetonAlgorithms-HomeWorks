package week_3.quicksort;

import edu.princeton.cs.algs4.StdRandom;

/**
 * In case of many same-value items in an array with numerous length, this 3 way partitioning quick sort was introduced back in the 1990s
 */
public class ThreewayQuickSort extends QuickSort{

    public static void main(String[] args){
        Integer[] array = new Integer[]{1,6,22,43,-19,2,6,4,3,6,1,43,7,22,-19};
        threeWayQuickSort(array, 0 , array.length - 1);
//        Quick3way.sort(array);
//        AlgorithmUtil.printArray(0, array);
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

        int lowBorder = low;
        int highBorder = high;

        int runner = low;
        int v = a[low];//TODO without this v, the result would not be correct, why??
        while(runner <= highBorder){
            int compare = a[runner].compareTo(v);
//            System.out.println("runner = " + runner + ",a[runner] = " + a[runner] + ",low = " + low + ",a[low] = " + a[low]);
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
