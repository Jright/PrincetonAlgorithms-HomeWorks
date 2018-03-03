package week_2.commonSorts;

public class ShellSort extends CommonSorts {

    public static void main(String[] args) {
        Integer[] array = new Integer[]{5,2,7,3,9,1,4,6,8};
        shellSort(array);
        for(Integer i : array){
            System.out.print(i + " ");
        }
    }


    //Shell sort is a special edition of insertion sort, using a "jump comparison" which is to compare two elements with a interval position of X
    public static void shellSort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3){
            h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, ...  This number is given by Prof. Segewick
        }
        while (h >= 1) { //if h = 1 then it is standard insertion sort
            for (int i = h; i < N; i++) { //the loop will compare every element behinds h to the element located with h interval before.
                for (int j = i; j >= h; j -= h){
                    if(less(a[j], a[j - h])){
                        exchange(a, j, j - h);
                    }
                }
            }
            h = h / 3;// reduce the h to a third of itself. then shellsort again.
        }
    }


}
