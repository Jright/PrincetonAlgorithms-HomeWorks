package week_2.exercise_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Intersection of two sets. Given two arrays a[] and b[], each containing n distinct 2D points in the plane,
 * design a subquadratic algorithm to count the number of points that are contained both in array a[] and array b[].
 *
 * //TODO Actually this problem is designed to use shellsort to resolve
 */
public class IntersectionOfTwoSets {

    public static void main(String[] args){
        int[] a = new int[]{1,4,7,9,11,24,56,23,770};
        int[] b = new int[]{2,6,4,7,11,23,45,77};

        List<Integer> commonElemets = findCommonElemets(a, b);
        for(Integer i : commonElemets){
            System.out.print(i + " ");
        }
    }

    private static List<Integer> findCommonElemets(int[] a, int[] b){
        HashMap<Integer,Integer> map = new HashMap<>();
        ArrayList<Integer> commonElements = new ArrayList<>();
        int count = 0;
        for(Integer i : a){
            map.put(i,count);
            count++;
        }
        for(Integer j : b){
            if(map.containsKey(j)){
                commonElements.add(j);
            }
        }

        return commonElements;

    }


}
