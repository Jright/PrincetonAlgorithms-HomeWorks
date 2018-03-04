package week_2.exercise_2;

/**
 Question 3
 Dutch national flag. Given an array of N buckets, each containing a red, white, or blue pebble, sort them by color. The allowed operations are:
 swap(i,j): swap the pebble in bucket i with the pebble in bucket j.
 color(i): color of pebble in bucket i.
 The performance requirements are as follows:
 At most N calls to color().
 At most N calls to swap().
 Constant extra space.
 */

enum Pebble {
    Red,
    White,
    Blue
}


public class DutchFlag {

    private static Pebble[] pebbles;

    private static Pebble color(int i){
        if(i >= 0 && i < pebbles.length){
            return pebbles[i];
        }
        return null;
    }

    private static void swap(int i, int j){
        Pebble tmp = pebbles[i];
        pebbles[i] = pebbles[j];
        pebbles[j] = tmp;
    }


    /**
     * The key point is, the pebble has three different colors, so we compare each color to White,which is the middle color.
     * It is like compare real numbers to 0, if it is bigger than 0 then it is positive number.
     */
    private static int compare(Pebble pebble){
        Pebble white = Pebble.White;
        return pebble.ordinal() - white.ordinal();
    }

    private static void pebbleSort(Pebble[] pebbles){
        assert pebbles.length > 0;

        //start.end: we move Red to start, Blue to end, and runner go looping. White will stay the same position.
        int start= 0,runner = 0, end = pebbles.length - 1;
        while(runner <= end){
            int compare = compare(pebbles[runner]);
            if(compare > 0){ // pebbles[runner] is Blue, need to move down
                swap(runner++,end--);
            }else if(compare < 0){
                swap(runner++,start++);
            }else{
                runner++;
            }
        }
    }


}
