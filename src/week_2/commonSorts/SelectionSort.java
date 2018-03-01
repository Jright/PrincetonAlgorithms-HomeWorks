package week_2.commonSorts;

public class SelectionSort {

    public static void main(String[] args){
        Integer[] array = new Integer[]{5,2,7,3,9,1,4,6,8};
        selectionSort(array);
        for(Integer i : array){
            System.out.print(i + " ");
        }
    }

    public static void selectionSort(Integer[] array){
        int N = array.length;

        for(int i = 0; i < N ; i++){
            int min = i;
            for(int j = i + 1; j < N; j++){
                if(less(array[j],array[min])){
                    min = j;
                }
            }
            exchange(array,i,min);
        }
    }

    private static boolean less(Comparable i, Comparable j){
        return i.compareTo(j) < 0;
    }

    private static void exchange(Comparable[] a,int i, int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


}
