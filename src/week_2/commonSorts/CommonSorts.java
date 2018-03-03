package week_2.commonSorts;

/**
 * The basic functions of common sort algorithms such as selection sorts and insertion sorts
 */
public class CommonSorts {

    protected static boolean less(Comparable i, Comparable j){
        return i.compareTo(j) < 0;
    }

    protected static void exchange(Comparable[] a,int i, int j){
        if(i != j){
            Comparable temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

}
