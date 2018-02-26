package week_1.exercise_2;


/**
 * Search in a bitonic array. An array is bitonic if it is comprised of an increasing sequence of integers followed immediately by a decreasing sequence
 * of integers.
 * Write a program that, given a bitonic array of n distinct integer values, determines whether a given integer is in the array.
 * Standard version: Use ∼3lgn compares in the worst case.
 * Signing bonus: Use ∼2lgn compares in the worst case (and prove that no algorithm can guarantee to perform fewer than ∼2lgn compares in the worst case).
 *
 *
 * Hints: Standard version. First, find the maximum integer using ∼1lgn compares—this divides the array into the increasing and decreasing pieces.
 * Signing bonus. Do it without finding the maximum integer.
 */

//TODO How can you do it without finding the maximum integer?
public class FindElementInBitonicArray {

    public static void main(String[] args){


        int[] bitonicArray = new int[]{1,3,5,7,9,11,6,4,2,0};

        int key = 8;

        int bitonicPoint = findBitonicPoint(bitonicArray);

        if(bitonicArray[bitonicPoint] < key){
            System.out.println("Not found");
        }else if(bitonicPoint == key){
            System.out.println(String.format("Found, the index is %d", bitonicPoint));
        }else{
            int i = ascendingBinarySearch(key, 0, bitonicPoint, bitonicArray);
            if(i < 0) {
                i = descendingBinarySearch(key, bitonicPoint + 1, bitonicArray.length - 1, bitonicArray);
            }
            if(i <0){
                System.out.println("Not found");
            }else{
                System.out.println(String.format("Found, the index is %d", i));
            }
        }
    }

    //First we have to find the biggest value of this given array.Call it i
    //The left side of i is a ascending array, and the right side is a descending array.
    //If the number we want to seach for is bigger than i, then it do not exist in the bitonic array.
    //If not,we shall do two binary search respectively for these two subarrays.
    //Note: binary search's implementation is not same on ascending and descending array.
    private static int findBitonicPoint(int[] bitonicArr){

        int low = 0; int high = bitonicArr.length - 1;

        while(low <= high){
            if(low == high){
                //Only one element
                return low;
            }

            if(high == low + 1){
                //Only two element
                return bitonicArr[low] > bitonicArr[high] ? low : high;
            }

            int mid = (low + high) / 2;

            //The key point is, the two subarrays(ascending and descending) of the bitonic array might not be equal in length.
            //The original mid point of the bitonic array could be either in ascending array or descending array
            if(bitonicArr[mid] > bitonicArr[mid - 1] && bitonicArr[mid] > bitonicArr[mid + 1]){
                return mid;
            }else if(bitonicArr[mid] > bitonicArr[mid - 1] && bitonicArr[mid] < bitonicArr[mid + 1]){
                //In this situation, the biggest element is not in the mid place but lies in the right side of the bitonic array, we use the method of binary search
                low = mid + 1;
            }else if(bitonicArr[mid] > bitonicArr[mid + 1] && bitonicArr[mid] < bitonicArr[mid - 1]){
                //The biggest element lies in the left side of the bitonic array.
                high = mid - 1;
            }else{
                return -1;
            }
        }
        return -1;
    }

    //Return the index of key's value, reutrn -1 if key does not exist
    private static int ascendingBinarySearch(int key, int low, int high,int[] ascendingArray){
        while(low <= high){
            if(low == high){
                return key == ascendingArray[low] ? low : -1;
            }

            int mid = (low + high) / 2;
            if(ascendingArray[mid] == key){
                return mid;
            }else if(ascendingArray[mid] > key){
                high = mid - 1;
            }else{
                low = mid + 1;
            }
        }
        return -1;
    }

    private static int descendingBinarySearch(int key, int low, int high, int[] descendingArray){
        while(low <= high){
            if(low == high){
                return key == descendingArray[low] ? low : -1;
            }
            int mid = (low + high) / 2;
            if(descendingArray[mid] == key){
                return mid;
            }else if(descendingArray[mid] > key){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return -1;
    }



}
