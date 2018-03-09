package week_3.mergesort;

public class MergeSortBottomUp extends MergeSort{

    public static void main(String[] args){
        Comparable[] array = {1,2,8,4,6,3,7,9,5,0};

        sortFromBottom(array);
        printArray(0,array);
    }

    public static void sortFromBottom(Comparable[] a){
        int width = 1;

        while(width <= a.length){
            int i = 0;
            System.out.println("width = " + width);
            for(; i + width <= a.length - 1; i += 2 * width){
                int low = i;
                int high = Math.min(i + 2 * width - 1, a.length - 1);
                int mid = low + width - 1;
                System.out.println("low = " + low + ",mid = " + mid +",high = " + high);
                merge(a,low,mid,high);
            }
            width = 2 * width;
        }
    }

    public static void merge(Comparable[] a,int low,int mid,int high){

        Comparable[] result = a.clone();
        int i = low;
        int j = mid + 1;
        int runner = low;
        while(i <= mid && j <= high && runner < high){
            result[runner++] = a[i].compareTo(a[j]) > 0 ? a[j++] : a[i++];
        }

        while(i <= mid){
            result[runner++] = a[i++];
        }

        while(j <= high){
            result[runner++] = a[j++];
        }

        for(int q = 0; q < result.length; q++){
            a[q] = result[q];
        }
    }

}
