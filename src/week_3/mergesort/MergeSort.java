package week_3.mergesort;

import week_3.AlgorithmUtil;

/**
 * Created by jright on 2017/6/28.
 */
public class MergeSort {

    protected static int mergeCount = 0;
    public static void main(String[] args) {
        Comparable[] array = {4,2,1,3,5,8,6,9,7,0};
        Comparable[] aux = array.clone();
        sort(array,aux,0,array.length - 1);
        for(Comparable i : aux){
            System.out.print(i + " ");
        }
    }

    /**
     * This merge function is simply merge two array(each of this two arrays has a half sorted subarray)
     * @param a
     * @param result
     * @param low
     * @param mid
     * @param high
     */
    public static void merge(Comparable[] a, Comparable[] result, int low, int mid, int high) {
        mergeCount++;
        System.out.println("Merge: low = " + low + ",mid = " + mid + ",high = " + high + ",MergeCount: " + mergeCount);

        //now we merge the two sorted subarrays of array a
        int i = low, j = mid + 1;//Two pointers point to the start of two sorted subarrays respectively
        int k = low;

        while (i <= mid && j <= high) {
            int compare = a[i].compareTo(a[j]);
            result[k] = compare > 0 ? a[j++] : a[i++];
            k++;
        }

        AlgorithmUtil.printArray(1,result);

        while(i <= mid){
            result[k] = a[i++];
            k++;
        }

        AlgorithmUtil.printArray(2,result);

        while(j <= high){
            result[k] = a[j++];
//            System.out.println("result[k] = " + result[k]);
            k++;
        }

        AlgorithmUtil.printArray(3,result);
        for(int q = 0; q < result.length; q++){
            a[q] = result[q];
        }
    }

    public static void sort(Comparable[] a,Comparable[] result,int low,int high){
        if(low >= high){
            return;//return if there is only one element of these two array
        }
        int mid = low + (high - low) / 2;
        sort(a,result,low,mid); //sort the left half of array a, will return directly when there is only one element of the array.
        sort(a,result,mid + 1,high);

        //In this step, we already know that the left half and the right half of array is sorted. And it is possible that this array is already sorted
        //So we add one check to see if it is already sorted. In this example, if we add this judgement, the total run times of merge has reduced from 9 to 7
        if(a[mid].compareTo(a[mid + 1]) < 0){
            System.out.println("Sort 3, no need to merge, already sorted, a[mid] = " + a[mid] + ",a[mid + 1] = " + a[mid + 1]);
            return;
        }
        merge(a,result,low,mid,high);//if the program executed to this line, we merge a array with two elements first, then return to the last sort() call
    }

    private static boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int j = i + 1;
            int result = a[i].compareTo(a[j]);
            if (result > 0) {
                return false;
            } else if (result <= 0) {
                break;
            }
        }
        return true;
    }
    //递归实现归并排序
//    static void merge_sort_recursive(int[] arr, int[] result, int start, int end) {
//        if (start >= end)//如果目前的数组只有一个元素则返回
//            return;
//        int len = end - start, mid = (len >> 1) + start;
//        int start1 = start, end1 = mid;
//        int start2 = mid + 1, end2 = end;
//        merge_sort_recursive(arr, result, start1, end1);//拆解左边数组
//        merge_sort_recursive(arr, result, start2, end2);//拆解右边数组
//        int k = start;
//        while (start1 <= end1 && start2 <= end2)//循环，把左右两个数组的较小值赋给结果数组
//            result[k++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
//        while (start1 <= end1)//循环，把未排序的左边剩余元素赋值给结果数组
//            result[k++] = arr[start1++];
//        while (start2 <= end2)//循环，把未排序的右边剩余元素赋值给结果数组
//            result[k++] = arr[start2++];
//        for (k = start; k <= end; k++)//把目前的结果存放回原来的数组
//            arr[k] = result[k];
//    }

//    public static void merge_sort(int[] arr) {
//        int len = arr.length;
//        int[] result = new int[len];
//        merge_sort_recursive(arr, result, 0, len - 1);
//    }


}
