package week_1.exercise_2;

import java.util.*;

/**
 * 3-SUM in quadratic time. Design an algorithm for the 3-SUM problem that takes time proportional to n2 in the worst case.
 * You may assume that you can sort the n integers in time proportional to n2 or better.
 *
 *
 */
public class Quadratic3Sum {

    public static void main(String[] args){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = -5; i <= 5; i++){
            list.add(i);
        }
        calculate3Sum(list);
    }


    //We need to sort this array first, then pick each one element by order, say it is i.
    //Next we will use a pointer pointed i + 1(name it as j) and a pointer pointed to the last element(name it k).
    //Because this array is sorted, so the idea is hold i still, then move j to the right and k to the left until j >= k,
    //During this circulation we will check if array[i] + array[j] = -array[k]. If so, record the array of array[i],array[j],array[k] to a HashMap
    //If not, Then we set i = i + 1 and reinitialize j as i + 1 and k as last element of the original array to do the next circulation
    public static void calculate3Sum(ArrayList<Integer> array){

        Collections.sort(array);

        int resultCount = 0;
        HashMap<Integer,ArrayList<Integer>> arrayListHashMap = new HashMap<>();


        //We need to find a two-sum in the array's two elements so that a + b = -c;
        //Initially j should be i + 1 and k should be the last element
        for(int i = 0; i < array.size() - 2; i++){

            int j = i + 1;
            int k = array.size() - 1;
            while(j < k){
                if(array.get(i) + array.get(j) + array.get(k) == 0){
                    //The result is 0, so we record the array, then add 1 to j(If you do not know why, just make a array from -5 to 5, add it up,then you will know)
                    resultCount++;
                    ArrayList<Integer> resultList = new ArrayList<>();
                    resultList.add(array.get(i));
                    resultList.add(array.get(j));
                    resultList.add(array.get(k));
                    arrayListHashMap.put(resultCount,resultList);
                    j++;
                }else if(array.get(i) + array.get(j) + array.get(k) > 0){
                    //array[k]'s value is too high, so we minus 1 to k
                    k--;
                }else{
                    //array[j]'s value is low so add 1 to j
                    j++;
                }
            }
        }

        Iterator<Map.Entry<Integer, ArrayList<Integer>>> iterator = arrayListHashMap.entrySet().iterator();
        while(iterator.hasNext()){
            ArrayList<Integer> value = iterator.next().getValue();
            for(Integer i : value){
                System.out.print(i.toString() + " ");
            }
            System.out.println();
        }
    }

}
