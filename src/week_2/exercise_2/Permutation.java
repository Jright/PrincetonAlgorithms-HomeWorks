package week_2.exercise_2;

import edu.princeton.cs.algs4.Shell;

/**
 * Permutation. Given two integer arrays of size n, design a subquadratic algorithm to determine whether one is a permutation of the other.
 * That is, do they contain exactly the same entries but, possibly, in a different order.
 */
public class Permutation {

    public static void main(String[] args){
        Integer[] a = new Integer[]{1,4,7,9,11,24,56,23,770};
        Integer[] b = new Integer[]{2,6,4,7,11,23,45,77};


        System.out.print(checkPermutation(a,b));
    }


    private static boolean checkPermutation(Integer[] a, Integer[] b){
        Shell.sort(a);
        Shell.sort(b);

        for(int i = 0; i < a.length ; i++){
            if(a[i] != b[i]){
                return false;
            }
        }

        return true;
    }



}
