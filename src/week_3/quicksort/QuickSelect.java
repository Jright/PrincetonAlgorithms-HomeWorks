package week_3.quicksort;


import edu.princeton.cs.algs4.StdRandom;

/**
 * Given an array of N items, find a k th smallest item.
 */
public class QuickSelect extends QuickSort{


    public static void main(String[] args){
        Integer[] array = new Integer[]{2,1,5,3,6,4,8,7,9,0};
        int i = quickSelect(array, 9);//Note: The smallest number of this array is not the 1st smallest item
        System.out.println("The 3rd smallest item of this array is: " + i);
    }

    public static int quickSelect(Comparable[] array, int k){
        StdRandom.shuffle(array);
        int low = 0, high = array.length - 1;
        while(low < high){
            int partition = partition(array, low, high);//array[partition] is the partition-th smallest item of this array.
            if(partition < k){
                low = partition + 1;
            }else if(partition > k){
                high = partition - 1;
            }else{
                return (int) array[k];
            }
        }
        return (int) array[k];
    }


}
