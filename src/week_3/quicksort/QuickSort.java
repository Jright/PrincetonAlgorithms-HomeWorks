package week_3.quicksort;

public class QuickSort {

    private static int partition(Comparable[] a, int low, int high){
        //1. a[low] = v
        int i = low, j = high + 1;
        while(true){
            while(less(a[++i], a[low])){ //Only those element bigger than v could escape out of this loop
                if(i == high){
                    break;
                }
            }

            while(less(a[low], a[--j])){ //Only those element smaller than v could escape out of this loop
                if(j == low){
                    break;
                }
            }

            if(i >= j){ //check if i meets j already
                break;
            }

            exchange(a,i,j); // exchange those two 'unsorted' elements and then repeat the two loops
        }

        exchange(a, low, j); // move v to j's position, so that all elements in left of j is smaller than a[j], the elements in right are all bigger than a[j]
        return j;
    }


    private static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0 ? true : false;
    }

    private static void exchange(Comparable[] array, int i, int j){

        assert i >= 0 && i < array.length;
        assert j >= 0 && j < array.length;

        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
