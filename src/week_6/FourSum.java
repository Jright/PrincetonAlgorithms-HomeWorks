package week_6;

import java.util.*;

//4-SUM. Given an array a[ \; ] of n integers, the 4-SUM problem is to determine if there exist distinct indices i, j, k,
//        and l such that a[i] + a[j] = a[k] + a[l]. Design an algorithm for the 4-SUM problem that takes time proportional to n^2
//        (under suitable technical assumptions).

//TODO Wrong Answer
public class FourSum {

    public static void main(String[] args){
        ArrayList<Integer> list = new ArrayList<>();
//        1,0,-1,0,-2,2
        list.add(1);
        list.add(0);
        list.add(-1);
        list.add(0);
        list.add(-2);
        list.add(2);

        check4Sum(list);
    }

    public static void check4Sum(ArrayList<Integer> list){
        Hashtable<Integer, Map<Integer, Integer>> hashtable = new Hashtable<>();

        for(int i = 0; i < list.size(); i++){
            for(int j = i + 1; j < list.size(); j++){
                int key = list.get(i) + list.get(j);
                if(hashtable.containsKey(key)){
                    Map<Integer, Integer> integerIntegerMap = hashtable.get(key);

                    Set<Integer> integers = integerIntegerMap.keySet();
                    System.out.print("Found 4-sum: " + list.get(i) + "," + list.get(j) + ",");
                    for(Integer integer : integers){
                        System.out.print("," + integer + "," + integerIntegerMap.get(integer));
                    }
                    System.out.println();

                    integerIntegerMap.put(list.get(i), list.get(j));

                    hashtable.put(key,integerIntegerMap);

                }else{
                    HashMap<Integer, Integer> hashMap = new HashMap<>();
                    hashMap.put(list.get(i), list.get(j));
                    hashtable.put(key, hashMap);
                }
            }
        }
    }

}
