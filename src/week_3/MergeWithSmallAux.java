package week_3;

import java.util.Arrays;

/**
 * Created by jeremy on 2017 Y 6 M 29 D.
 *
 * We need to sort an array using mergesort which has two separated sorted part,and we are required to use a auxilliary
 * array which is only half of the length of the original array.(Normally mergesort requires a same length of an array for support)
 *
 * Copied https://github.com/guibin/Knowledge/blob/master/libs/lib.algorithm/src/main/java/guibin/zhang/onlinecourse/MergeWithSmallAux.java
 */
public class MergeWithSmallAux {

    /**
     * @param origin original array
     * @param aux Auxiliary array is only half length of origin.
     * @param N length of auxiliary array and half of the length of origin.
     */
    public void mergeWithSmallerAuxiliaryArray(Comparable[] origin, Comparable[] aux, int N) {

        //copy the first half of origin array to aux array
        for(int k = 0; k < N; k ++) {
            aux[k] = origin[k];
        }

        int auxIndex = 0, rightPartOrigin = N, resultIndex = 0;

        while (resultIndex < origin.length) {

            if (auxIndex >= N)
                //already used up the aux array(which is the left part of the origin array)
                origin[resultIndex++] = origin[rightPartOrigin++];
            else if (rightPartOrigin >= origin.length)
                //right part of origin array had used up,so we must fill the result array with what's
                //left in the aux array
                origin[resultIndex++] = aux[auxIndex++];
            else if (aux[auxIndex].compareTo(origin[rightPartOrigin]) < 0)
                //both the left and right array has element to compare
                origin[resultIndex++] = aux[auxIndex++];
            else {
                origin[resultIndex++] = origin[rightPartOrigin++];
            }
        }
    }

    public static void main(String[] args) {

        Comparable[] a = {40, 61, 70, 71, 99, 20, 51, 55, 75, 100};
        MergeWithSmallAux m = new MergeWithSmallAux();
        int N = a.length / 2;
        Comparable[] aux = new Comparable[N];
        m.mergeWithSmallerAuxiliaryArray(a, aux, N);
        System.out.println("After merging:");
        Arrays.stream(a).forEach((c) -> System.out.print(c + ","));
        System.out.println();
    }
}
