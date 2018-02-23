package week_3;

/**
 * Created by jright on 2017/6/28.
 */
public class MergeSort {

    int[] array = {2,1,5,3,8,0,3,7};

//    public static void main(String[] args){
//
//    }

    //递归实现归并排序
    static void merge_sort_recursive(int[] arr, int[] result, int start, int end) {
        if (start >= end)//如果目前的数组只有一个元素则返回
            return;
        int len = end - start, mid = (len >> 1) + start;
        int start1 = start, end1 = mid;
        int start2 = mid + 1, end2 = end;
        merge_sort_recursive(arr, result, start1, end1);//拆解左边数组
        merge_sort_recursive(arr, result, start2, end2);//拆解右边数组
        int k = start;
        while (start1 <= end1 && start2 <= end2)//循环，把左右两个数组的较小值赋给结果数组
            result[k++] = arr[start1] < arr[start2] ? arr[start1++] : arr[start2++];
        while (start1 <= end1)//循环，把未排序的左边剩余元素赋值给结果数组
            result[k++] = arr[start1++];
        while (start2 <= end2)//循环，把未排序的右边剩余元素赋值给结果数组
            result[k++] = arr[start2++];
        for (k = start; k <= end; k++)//把目前的结果存放回原来的数组
            arr[k] = result[k];
    }
    public static void merge_sort(int[] arr) {
        int len = arr.length;
        int[] result = new int[len];
        merge_sort_recursive(arr, result, 0, len - 1);
    }

}
