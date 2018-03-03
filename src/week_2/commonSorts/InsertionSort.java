package week_2.commonSorts;


public class InsertionSort extends CommonSorts{

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
}
