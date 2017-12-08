// Copyright (c) Jared Blackburn 2017
// It is licensed under the creative commons 4.0 attribution license:
// https://creativecommons.org/licenses/by/4.0/legalcode 
package jaredbgreat.arcade.util.math;

/**
 * Static bit methods as general bit-twiddling methods.
 * 
 * @author Jared Blackburn
 */
public class Bits {
    
    public static int setBit(int in, int bit) {
        return in | (1 << (bit - 1));
    }
    
    public static long setBit(long in, int bit) {
        return in | (1 << (bit - 1));
    }
    
    
    public static boolean checkBit(int in, int bit) {
        return ((in >> (bit - 1)) & 1) == 1;
    }
    
    
    public static boolean checkBit(long in, int bit) {
        return ((in >> (bit - 1)) & 1) == 1;
    }
    
    
    
    
}
