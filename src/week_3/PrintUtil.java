package week_3;

public class PrintUtil {

    public static void printArray(int count,Comparable[] array){
        System.out.print(count + ": ");
        for(Comparable c1 : array){
            System.out.print(c1 + " ");
        }
        System.out.println();
    }
}
