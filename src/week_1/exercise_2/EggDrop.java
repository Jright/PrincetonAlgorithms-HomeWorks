package week_1.exercise_2;


/**
 * Egg drop. Suppose that you have an n-story building (with floors 1 through n) and plenty of eggs.
 * An egg breaks if it is dropped from floor T or higher and does not break otherwise.
 * Your goal is to devise a strategy to determine the value of T given the following limitations on the number of eggs and tosses:
 * Version 0: 1 egg, ≤T tosses.
 * Version 1: ∼1lgn eggs and ∼1lgn tosses.
 * Version 2: ∼lgT eggs and ∼2lgT tosses.
 * Version 3: 2 eggs and ∼2 * sqrt(n) tosses.
 * Version 4: 2 eggs and ≤c * sqrt(T) tosses for some fixed constant c.
 */
public class EggDrop {

    /**
     * Version 0 ：No other choice but sequential search, since you only have one chance to play
     * Version 1 : binary search
     * Version 2 : //TODO No idea
     * Version 3 : First toss in floor 1, then toss it to each increment of //TODO no idea
     */

    //    Copied from http://seanzhou1023.blogspot.com/2017/04/quiz-2-princeton-algorithm.html
    //    Version 0 : try from floor 1 until it's broken
    //    Version 1: binary search
    //    Version 2 : try from 1 , 2, 4, ... the first number that is bigger than T , totally ~lgT tosses ,//TODO (Why another lgT why this value??) then do binary search another ~lgT tosses
    //    Version 3 :  try from n^0.5 , 2*n^0.5 , 3*n^0.5 , ... the first number that is bigger than T, used 1 egg and at most ~n^0.5 tosses. then we know T is between k*n^0.5 and (k+1)*n^0.5  , then we try from lower bound to upper bound , at most n^0.5 tosses and use another egg
    //  TODO  Version 4: try from 1, 1+2 , 1+2+3 , ..., 1+2+3 + ... +k the first number that is bigger than T, used 1 egg and  k tosses , then try in last k floor, so totally 2k tosses, 0.5 *k * (k + 1) = T , so k = 2^0.5 T^0.5
}
