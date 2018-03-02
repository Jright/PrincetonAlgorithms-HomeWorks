package week_2.commonSorts;


public class InsertionSort {

    public static void main(String[] args){
        Integer[] array = new Integer[]{5,2,7,3,9,1,4,6,8};
        insertionSort(array);
        for(Integer i : array){
            System.out.print(i + " ");
        }
    }

    private static void insertionSort(Integer[] array){
        for(int i = 0; i < array.length; i++){
            int j = i;
            for(; j > 0;j--){
                if(less(array[j],array[j - 1])){
                    exchange(array,j,j - 1);
                }else{
                    break;
                }
            }
        }
    }

    private static boolean less(Comparable i, Comparable j){
        return i.compareTo(j) < 0;
    }

    private static void exchange(Comparable[] a,int i, int j){
        if(i != j){
            Comparable temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }


}
