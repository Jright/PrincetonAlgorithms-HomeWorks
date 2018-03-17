package week_3;

public class AlgorithmUtil {

    public static void printArray(int count,Comparable[] array){
        System.out.print(count + ": ");
        for(Comparable c1 : array){
            System.out.print(c1 + " ");
        }
        System.out.println();
    }

    public static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0 ? true : false;
    }

    public static void exchange(Comparable[] array, int i, int j){

        assert i >= 0 && i < array.length;
        assert j >= 0 && j < array.length;

        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
