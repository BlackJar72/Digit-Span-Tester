package edu.utk.web.enlil;

/*
// <P><UL><LI>Digit Span Tester version 1.0</LI>
// <LI>Copyright (C) 2011 Jared Blackburn.</LI>
//
// <P>This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation; either version 2
// of the License, or (at your option) any later version.</P>
//
// <P>This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.</P>
//
// <P>You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.</P>
//
// <P>Jared Blackburn<BR></BR>
// <A HREF ="http://www.facebook.com/Jared.Blackburn1">http://www.facebook.com/Jared.Blackburn1</A></P>
//
// For more information about the GNU General Public Liscnece see:
// http://www.gnu.org/copyleft/gpl.html
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author Jared Blackburn
 */
public class RandomSequencer {

    private static final String ZERO  = " 0 ";
    private static final String ONE   = " 1 ";
    private static final String TWO   = " 2 ";
    private static final String THREE = " 3 ";
    private static final String FOUR  = " 4 ";
    private static final String FIVE  = " 5 ";
    private static final String SIX   = " 6 ";
    private static final String SEVEN = " 7 ";
    private static final String EIGHT = " 8 ";
    private static final String NINE  = " 9 ";

    private static final String[] NUM_BASE = { ZERO, ONE, TWO, THREE, FOUR,
            FIVE, SIX, SEVEN, EIGHT, NINE};
    private static final int NUM_VALS = 10;

    private ArrayList<String> numList = new ArrayList<String>();
    private HashSet<String> sourceSet = new HashSet<String>();
    private HashSet<String> extraSet = new HashSet<String>();
    private HashSet<String> holdingSet = new HashSet<String>();
    private HashSet<String> toRemoveLater = new HashSet<String>();

    private String[] sequence;

    private Random shuffler = new Random(System.nanoTime());

    //Control codes from the generate mehtod
    public static final int RANDOM = 1;
    public static final int SIMPLE = 2;
    public static final int ALT1   = 3;
    public static final int ALT2   = 4;
    public static final int ALT3   = 5;
    public static final int ALT4   = 6;
    public static final int ALT5   = 7;
    public static final int ALT6   = 8;
    public static final int ALT7   = 9;
    public static final int ALT8   = 0xA;

    private int toPull = 0;
    private int pulled = 0;
    private int current = 0;
    private int last = 0;
    private int distance = 0;
    private int notUse = 0;
    private int counter = 0;
    private String numeral = "";

    //The following are for use in altShuffle8
    private int subSequenceDown = 0;
    private int subSequenceUp = 0;
    private int[] currentSubSequence;
    private int[] previousSubSequence;


    private void initVariables() {
        counter = 0;
        toPull = 64;
        pulled = 64;
        last = 64;
        distance = 64;
        notUse = 64;
        numeral = "";
    }


    private void initSets() {
        sourceSet.clear();
        extraSet.clear();
        holdingSet.clear();
        toRemoveLater.clear();
        sourceSet.addAll(Arrays.asList(NUM_BASE));
        holdingSet.addAll(Arrays.asList(NUM_BASE));
    }


    private void initList() {
        numList.clear();
        numList.addAll(Arrays.asList(NUM_BASE));
    }


    /**
     * This will create a sequence based on random numbers, in which each
     * numeral from "0" to "9" has an equal chance of occurring in each
     * position.  Numbers may be repeated and there are no rules governing
     * what will appear where.
     *
     * @param howMany the length of the sequence
     */
    public void randomSelect(int howMany) {
        sequence = new String[howMany];
        for(int i = 0; i < howMany; i++) {
            sequence[i] = NUM_BASE[(int)(shuffler.nextInt(NUM_VALS))];
        }
    }


    /**
     * This uses Java Collections built in shuffle() method to create a
     * random digit sequence in which no number is repeated.  The shuffles
     * are fair (equal chance of any number at any location) but are placed
     * with removal (no numeral can repeat in the sequence).  There are no
     * other rules governing the order beyond the lack of duplicates.
     *
     * @param howMany the length of the sequence
     */
    public void simpleShuffle(int howMany) {
        sequence = new String[howMany];
        initList();
        Collections.shuffle(numList, shuffler);
        for(int i = 0; i < howMany; i++) {
            sequence[i] = numList.get(i);
        }
    }


    /**
     * <P>This will shuffle the sequence, producing results similar to
     * a simple shuffle, but without any digits in there natural order.
     * More specifically, no two numerals in a row may be exactly one more
     * one less than than each other.  Thus "5 6" or "9 8" will never
     * occur.</P>
     *
     * <P>Note that restriction is only applied if doing so will not reduce
     * the pool of potential next numerals to an empty set.  If no possible
     * numerals exist that would follow the limitation, then those
     * limitations will not apply, allowing a forbidden sub-sequence at the
     * end of a long sequence.</P>
     *
     * @param howMany the length of the sequence
     */
    public void altShuffle1(int howMany) {
        initVariables();
        sequence = new String[howMany];
        initSets();
        for(int i = 0; i < howMany; i++) {
            numList.clear();
            numList.addAll(holdingSet);
            holdingSet.addAll(sourceSet);
            toPull = shuffler.nextInt(numList.size());
            numeral = numList.get(toPull);
            sequence[i] = numeral;
            holdingSet.remove(numeral);
            sourceSet.remove(numeral);
            // If there will be no more possible items, just quit
            if(holdingSet.isEmpty() || sourceSet.isEmpty()) break;
            removeBordering();
        }
    }


    /**
     * <P>This will shuffle the sequence, producing results similar to
     * a simple shuffle.  However, no skip distance may be repeated
     * back-to-back.  In other words, if the current numeral changed by
     * n from the last numeral, the next numeral differ by a value other
     * than n -- thus "2 4 6" (+2 twice), "1 4 7" (+3 twice), or "5 3 1"
     * (-2 twice) will not occur.</P>
     *
     * <P>Note that restriction is only applied if doing so will not reduce
     * the pool of potential next numerals to an empty set.  If no possible
     * numerals exist that would follow the limitation, then those
     * limitations will not apply, allowing a forbidden sub-sequence at the
     * end of a long sequence.</P>
     *
     * @param howMany the length of the sequence
     */
    public void altShuffle2(int howMany) {
        initVariables();
        sequence = new String[howMany];
        initSets();
        for(int i = 0; i < howMany; i++) {
            numList.clear();
            numList.addAll(holdingSet);
            holdingSet.addAll(sourceSet);
            toPull = shuffler.nextInt(numList.size());
            numeral = numList.get(toPull);
            sequence[i] = numeral;
            holdingSet.remove(numeral);
            sourceSet.remove(numeral);
            // If there will be no more possible items, just quit
            // This should never actually happen.
            if(holdingSet.isEmpty() || sourceSet.isEmpty()) break;
            // Remove duplicate skips
            removePattern();
        }
    }


    /**
     * <P>This will shuffle the sequence, producing results similar to
     * a simple shuffle, but with two difference:</P>
     *
     * <UL><LI>No skip distance may be repeated
     * back-to-back.  In other words, if the current numeral changed by
     * n from the last numeral, the next numeral must differ by a value other
     * than n -- thus "2 4 6" (+2 twice), "1 4 7" (+3 twice), or "5 3 1"
     * (-2 twice) will not occur.</LI>
     *
     * <LI>No two numerals in a row may be exactly one more one less than
     * than each other.  Thus "5 6" or "9 8" will never occur.</LI></UL>
     *
     * <P>This is essential a combination of altShuffle1 and altShuffle2,
     * in terms of added, pattern-eliminating limitations.</P>
     *
     * <P>Note that restrictions are only applied if doing so will not reduce
     * the pool of potential next numerals to an empty set.  If no possible
     * numerals exist that would follow the limitation, then those
     * limitations will not apply, allowing a forbidden sub-sequence at the
     * end of a long sequence.</P>
     *
     * @param howMany the length of the sequence
     */
    public void altShuffle3(int howMany) {
        initVariables();
        sequence = new String[howMany];
        initSets();
        for(int i = 0; i < howMany; i++) {
            numList.clear();
            numList.addAll(holdingSet);
            holdingSet.addAll(sourceSet);
            toPull = shuffler.nextInt(numList.size());
            numeral = numList.get(toPull);
            sequence[i] = numeral;
            holdingSet.remove(numeral);
            sourceSet.remove(numeral);
            // If there will be no more possible items, just quit
            if(holdingSet.isEmpty() || sourceSet.isEmpty()) break;
            removeBordering();
            // Remove duplicate skips
            removePattern();
        }
    }


    /**
     * This is the same as altShuffle1 except that the same numeral may
     * appear up to twice in the same sequence (but never twice in a row).
     *
     * @param howMany the length of the sequence
     */
    public void altShuffle4(int howMany) {
        initVariables();
        sequence = new String[howMany];
        initSets();
        for(int i = 0; i < howMany; i++) {
            numList.clear();
            numList.addAll(holdingSet);
            holdingSet.addAll(sourceSet);
            toPull = shuffler.nextInt(numList.size());
            numeral = numList.get(toPull);
            sequence[i] = numeral;
            holdingSet.remove(numeral);
            // If this is the second occurance of a numeral remove it
            // premenantly, if its the first record that it has been used
            // once.
            if(toRemoveLater.contains(numeral)) {
                sourceSet.remove(numeral);
            } else {
                toRemoveLater.add(numeral);
            }
            // If there will be no more possible items, just quit
            if(holdingSet.isEmpty() || sourceSet.isEmpty()) break;
            removeBordering();
        }
    }


    /**
     * This is the same as altShuffle2 except that the same numeral may
     * appear up to twice in the same sequence (but never twice in a row).
     *
     * @param howMany the length of the sequence
     */
    public void altShuffle5(int howMany) {
        initVariables();
        sequence = new String[howMany];
        initSets();
        for(int i = 0; i < howMany; i++) {
            numList.clear();
            numList.addAll(holdingSet);
            holdingSet.addAll(sourceSet);
            toPull = shuffler.nextInt(numList.size());
            numeral = numList.get(toPull);
            sequence[i] = numeral;
            holdingSet.remove(numeral);
            // If this is the second occurance of a numeral remove it
            // premenantly, if its the first record that it has been used
            // once.
            if(toRemoveLater.contains(numeral)) {
                sourceSet.remove(numeral);
            } else {
                toRemoveLater.add(numeral);
            }
            // If there will be no more possible items, just quit.
            if(holdingSet.isEmpty() || sourceSet.isEmpty()) break;
            // Remove duplicate skips
            removePattern();
        }
    }


    /**
     * This is the same as altShuffle3 except that the same numeral may
     * appear up to twice in the same sequence (but never twice in a row).
     *
     * @param howMany the length of the sequence
     */
    public void altShuffle6(int howMany) {
        initVariables();
        sequence = new String[howMany];
        initSets();
        for(int i = 0; i < howMany; i++) {
            numList.clear();
            numList.addAll(holdingSet);
            holdingSet.addAll(sourceSet);
            toPull = shuffler.nextInt(numList.size());
            numeral = numList.get(toPull);
            sequence[i] = numeral;
            holdingSet.remove(numeral);
            // If this is the second occurance of a numeral remove it
            // premenantly, if its the first record that it has been used
            // once.
            if(toRemoveLater.contains(numeral)) {
                sourceSet.remove(numeral);
            } else {
                toRemoveLater.add(numeral);
            }
            // If there will be no more possible items, just quit
            if(holdingSet.isEmpty() || sourceSet.isEmpty()) break;
            removeBordering();
            // Remove duplicate skips
            removePattern();
        }
    }


    /**
     * This is the same as altShuffle3 except that the same numeral may
     * appear up to twice in the same sequence (but never twice in a row).
     *
     * @param howMany the length of the sequence
     */
    public void altShuffle7(int howMany) {
        initVariables();
        sequence = new String[howMany];
        initSets();
        for(int i = 0; i < howMany; i++) {
            numList.clear();
            numList.addAll(holdingSet);
            holdingSet.addAll(Arrays.asList(NUM_BASE));
            toPull = shuffler.nextInt(numList.size());
            numeral = numList.get(toPull);
            sequence[i] = numeral;
            holdingSet.remove(numeral);
            // If there will be no more possible items, just quit
            if(holdingSet.isEmpty()) break;
            removeBordering();
            // Remove duplicate skips
            removePattern();
        }
    }


    private void removeBordering() {
        // Reomve boardering numerals from holdingSet
        extraSet.clear();
        extraSet.addAll(holdingSet);
        pulled = Integer.parseInt(numeral.trim());
        if(pulled > 0)
            extraSet.remove(NUM_BASE[pulled - 1]);
        if(pulled < (NUM_BASE.length - 1))
            extraSet.remove(NUM_BASE[pulled + 1]);
        if(!extraSet.isEmpty()) {
            holdingSet.clear();
            holdingSet.addAll(extraSet);
        }
}


    private void removePattern() {
        // Remove duplicate skips
        pulled = Integer.parseInt(numeral.trim());
        distance = last - pulled;
        notUse = pulled - distance;
        if((holdingSet.size() > 1) && (notUse >= 0 && notUse <= 9))
            holdingSet.remove(NUM_BASE[notUse]);
        if((holdingSet.size() > 1) && (last >= 0 && last <= 9))
            holdingSet.remove(NUM_BASE[last]);
        last = pulled;
    }


    private void removeVerticle(int [] previous) {
        // Remove digit fount in same position in last sequence
        if(counter >= previous.length) return;
        if(previous[counter] < 0 || previous[counter] > 9) return;
        if(holdingSet.size() > 1) holdingSet.remove(NUM_BASE[previous[counter]]);
        // The first digit should not be the same as the last of the previous
        // sequence, either.
        if(counter == 0)
            if(previous[previous.length - 1] >= 0
                    && previous[previous.length - 1] <= 9)
                holdingSet.remove(NUM_BASE[previous[previous.length - 1]]);
    }


    private void removeSubVals(int [] previous) {
        if(previous == null) return;
        // Remove digit fount in same position in last sequence
        if(subSequenceUp >= previous.length) return;
        if(previous[subSequenceUp] < 0 || previous[subSequenceUp] > 9) return;
        if(holdingSet.size() > 1) holdingSet.remove(NUM_BASE[previous[subSequenceUp]]);
        // The first digit should not be the same as the last of the previous
        // sequence, either.
        if(subSequenceUp == 0)
            if(previous[previous.length - 1] >= 0
                    && previous[previous.length - 1] <= 9)
                holdingSet.remove(NUM_BASE[previous[previous.length - 1]]);
    }


    /**
     * <P>This will shuffle the sequence, producing results similar to
     * a simple shuffle, but without any digits in there natural order.
     * More specifically, no two numerals in a row may be exactly one more
     * one less than than each other.  Thus "5 6" or "9 8" will never
     * occur.</P>
     *
     * <P>Note that restriction is only applied if doing so will not reduce
     * the pool of potential next numerals to an empty set.  If no possible
     * numerals exist that would follow the limitation, then those
     * limitations will not apply, allowing a forbidden sub-sequence at the
     * end of a long sequence.</P>
     *
     * @param howMany the length of the sequence
     * @param previous the last sequence generated
     */
    public void altShuffle1(int howMany, int [] previous) {
        initVariables();
        sequence = new String[howMany];
        initSets();
        for(int i = 0; i < howMany; i++) {
            removeVerticle(previous);
            numList.clear();
            numList.addAll(holdingSet);
            holdingSet.addAll(sourceSet);
            toPull = shuffler.nextInt(numList.size());
            numeral = numList.get(toPull);
            sequence[i] = numeral;
            holdingSet.remove(numeral);
            sourceSet.remove(numeral);
            // If there will be no more possible items, just quit
            if(holdingSet.isEmpty() || sourceSet.isEmpty()) break;
            removeBordering();
        }
    }


    /**
     * <P>This will shuffle the sequence, producing results similar to
     * a simple shuffle.  However, no skip distance may be repeated
     * back-to-back.  In other words, if the current numeral changed by
     * n from the last numeral, the next numeral differ by a value other
     * than n -- thus "2 4 6" (+2 twice), "1 4 7" (+3 twice), or "5 3 1"
     * (-2 twice) will not occur.</P>
     *
     * <P>Note that restriction is only applied if doing so will not reduce
     * the pool of potential next numerals to an empty set.  If no possible
     * numerals exist that would follow the limitation, then those
     * limitations will not apply, allowing a forbidden sub-sequence at the
     * end of a long sequence.</P>
     *
     * @param howMany the length of the sequence
     * @param previous the last sequence generated
     */
    public void altShuffle2(int howMany, int [] previous) {
        initVariables();
        sequence = new String[howMany];
        initSets();
        for(int i = 0; i < howMany; i++) {
            removeVerticle(previous);
            numList.clear();
            numList.addAll(holdingSet);
            holdingSet.addAll(sourceSet);
            toPull = shuffler.nextInt(numList.size());
            numeral = numList.get(toPull);
            sequence[i] = numeral;
            holdingSet.remove(numeral);
            sourceSet.remove(numeral);
            // If there will be no more possible items, just quit
            // This should never actually happen.
            if(holdingSet.isEmpty() || sourceSet.isEmpty()) break;
            // Remove duplicate skips
            removePattern();
        }
    }


    /**
     * <P>This will shuffle the sequence, producing results similar to
     * a simple shuffle, but with two difference:</P>
     *
     * <UL><LI>No skip distance may be repeated
     * back-to-back.  In other words, if the current numeral changed by
     * n from the last numeral, the next numeral must differ by a value other
     * than n -- thus "2 4 6" (+2 twice), "1 4 7" (+3 twice), or "5 3 1"
     * (-2 twice) will not occur.</LI>
     *
     * <LI>No two numerals in a row may be exactly one more one less than
     * than each other.  Thus "5 6" or "9 8" will never occur.</LI></UL>
     *
     * <P>This is essential a combination of altShuffle1 and altShuffle2,
     * in terms of added, pattern-eliminating limitations.</P>
     *
     * <P>Note that restrictions are only applied if doing so will not reduce
     * the pool of potential next numerals to an empty set.  If no possible
     * numerals exist that would follow the limitation, then those
     * limitations will not apply, allowing a forbidden sub-sequence at the
     * end of a long sequence.</P>
     *
     * @param howMany the length of the sequence
     * @param previous the last sequence generated
     */
    public void altShuffle3(int howMany, int [] previous) {
        initVariables();
        sequence = new String[howMany];
        initSets();
        for(int i = 0; i < howMany; i++) {
            removeVerticle(previous);
            counter++;
            numList.clear();
            numList.addAll(holdingSet);
            holdingSet.addAll(sourceSet);
            toPull = shuffler.nextInt(numList.size());
            numeral = numList.get(toPull);
            sequence[i] = numeral;
            holdingSet.remove(numeral);
            sourceSet.remove(numeral);
            // If there will be no more possible items, just quit
            if(holdingSet.isEmpty() || sourceSet.isEmpty()) break;
            removeBordering();
            // Remove duplicate skips
            removePattern();
        }
    }


    /**
     * This is the same as altShuffle1 except that the same numeral may
     * appear up to twice in the same sequence (but never twice in a row).
     *
     * @param howMany the length of the sequence
     * @param previous the last sequence generated
     */
    public void altShuffle4(int howMany, int [] previous) {
        initVariables();
        sequence = new String[howMany];
        initSets();
        for(int i = 0; i < howMany; i++) {
            removeVerticle(previous);
            numList.clear();
            numList.addAll(holdingSet);
            holdingSet.addAll(sourceSet);
            toPull = shuffler.nextInt(numList.size());
            numeral = numList.get(toPull);
            sequence[i] = numeral;
            holdingSet.remove(numeral);
            // If this is the second occurance of a numeral remove it
            // premenantly, if its the first record that it has been used
            // once.
            if(toRemoveLater.contains(numeral)) {
                sourceSet.remove(numeral);
            } else {
                toRemoveLater.add(numeral);
            }
            // If there will be no more possible items, just quit
            if(holdingSet.isEmpty() || sourceSet.isEmpty()) break;
            removeBordering();
        }
    }


    /**
     * This is the same as altShuffle2 except that the same numeral may
     * appear up to twice in the same sequence (but never twice in a row).
     *
     * @param howMany the length of the sequence
     * @param previous the last sequence generated
     */
    public void altShuffle5(int howMany, int [] previous) {
        initVariables();
        sequence = new String[howMany];
        initSets();
        for(int i = 0; i < howMany; i++) {
            removeVerticle(previous);
            numList.clear();
            numList.addAll(holdingSet);
            holdingSet.addAll(sourceSet);
            toPull = shuffler.nextInt(numList.size());
            numeral = numList.get(toPull);
            sequence[i] = numeral;
            holdingSet.remove(numeral);
            // If this is the second occurance of a numeral remove it
            // premenantly, if its the first record that it has been used
            // once.
            if(toRemoveLater.contains(numeral)) {
                sourceSet.remove(numeral);
            } else {
                toRemoveLater.add(numeral);
            }
            // If there will be no more possible items, just quit.
            if(holdingSet.isEmpty() || sourceSet.isEmpty()) break;
            // Remove duplicate skips
            removePattern();
        }
    }


    /**
     * This is the same as altShuffle3 except that the same numeral may
     * appear up to twice in the same sequence (but never twice in a row).
     *
     * @param howMany the length of the sequence
     * @param previous the last sequence generated
     */
    public void altShuffle6(int howMany, int [] previous) {
        initVariables();
        sequence = new String[howMany];
        initSets();
        for(int i = 0; i < howMany; i++) {
            removeVerticle(previous);
            counter++;
            numList.clear();
            numList.addAll(holdingSet);
            holdingSet.addAll(sourceSet);
            toPull = shuffler.nextInt(numList.size());
            numeral = numList.get(toPull);
            sequence[i] = numeral;
            holdingSet.remove(numeral);
            // If this is the second occurance of a numeral remove it
            // premenantly, if its the first record that it has been used
            // once.
            if(toRemoveLater.contains(numeral)) {
                sourceSet.remove(numeral);
            } else {
                toRemoveLater.add(numeral);
            }
            // If there will be no more possible items, just quit
            if(holdingSet.isEmpty() || sourceSet.isEmpty()) break;
            removeBordering();
            // Remove duplicate skips
            removePattern();
        }
    }


    /**
     * This is the same as altShuffle3 and altShuffle6 except that the same
     * numeral may appear an unlimited number of times in the same sequence
     * (but never twice in a row).
     *
     * @param howMany the length of the sequence
     * @param previous the last sequence generated
     */
    public void altShuffle7(int howMany, int [] previous) {
        initVariables();
        sequence = new String[howMany];
        initSets();
        for(int i = 0; i < howMany; i++) {
            removeVerticle(previous);
            counter++;
            numList.clear();
            numList.addAll(holdingSet);
            holdingSet.addAll(Arrays.asList(NUM_BASE));
            toPull = shuffler.nextInt(numList.size());
            numeral = numList.get(toPull);
            sequence[i] = numeral;
            holdingSet.remove(numeral);
            // If there will be no more possible items, just quit
            if(holdingSet.isEmpty()) break;
            removeBordering();
            // Remove duplicate skips
            removePattern();
        }
    }


    /**
     * This is similar to altShuffle3, altShuffle6, and altShuffle7 except
     * that the same numeral may appear an unlimited number of times in the
     * same sequence while not allowing it to reappear too soon.  This is to
     * create more usable long sequences.
     *
     * Technically, the sequence generated will be a series of random length
     * sub-sequences generated as in altShuffle3.  Each sub-sequence has a
     * length between three and seven (inclusive).
     *
     * @param howMany the length of the sequence
     */
    public void altShuffle8(int howMany) {
        initVariables();
        subSequenceDown = shuffler.nextInt(5) + 3;
        subSequenceUp = 0;
        currentSubSequence = new int[subSequenceDown];
        sequence = new String[howMany];
        initSets();
        for(int i = 0; i < howMany; i++) {
            removeSubVals(previousSubSequence);
            numList.clear();
            numList.addAll(holdingSet);
            holdingSet.addAll(sourceSet);
            if(--subSequenceDown == 0) {
                sourceSet.addAll(Arrays.asList(NUM_BASE));;
                subSequenceDown = shuffler.nextInt(5) + 3;
                subSequenceUp = 0;
                previousSubSequence = currentSubSequence;
                currentSubSequence = new int[subSequenceDown];
            }
            toPull = shuffler.nextInt(numList.size());
            numeral = numList.get(toPull);
            sequence[i] = numeral;
            holdingSet.remove(numeral);
            sourceSet.remove(numeral);
            currentSubSequence[subSequenceUp++] = i;
            // If there will be no more possible items, just quit
            if(holdingSet.isEmpty() || sourceSet.isEmpty()) break;
            removeBordering();
            // Remove duplicate skips
            removePattern();
        }
    }


    /**
     * This is similar to altShuffle3, altShuffle6, and altShuffle7 except
     * that the same numeral may appear an unlimited number of times in the
     * same sequence while not allowing it to reappear too soon.  This is to
     * create more usable long sequences.
     *
     * Technically, the sequence generated will be a series of random length
     * sub-sequences generated as in altShuffle3.  Each sub-sequence has a
     * length between three and seven (inclusive).
     *
     * @param howMany the length of the sequence
     * @param previous the last sequence generated
     */
    public void altShuffle8(int howMany, int [] previous) {
        initVariables();
        subSequenceDown = shuffler.nextInt(5) + 3;
        subSequenceUp = 0;
        currentSubSequence = new int[subSequenceDown];
        sequence = new String[howMany];
        initSets();
        for(int i = 0; i < howMany; i++) {
            removeSubVals(previousSubSequence);
            removeVerticle(previous);
            counter++;
            numList.clear();
            numList.addAll(holdingSet);
            holdingSet.addAll(sourceSet);
            if(--subSequenceDown == 0) {
                sourceSet.addAll(Arrays.asList(NUM_BASE));;
                subSequenceDown = shuffler.nextInt(5) + 3;
                subSequenceUp = 0;
                previousSubSequence = currentSubSequence;
                currentSubSequence = new int[subSequenceDown];
            }
            toPull = shuffler.nextInt(numList.size());
            numeral = numList.get(toPull);
            sequence[i] = numeral;
            holdingSet.remove(numeral);
            sourceSet.remove(numeral);
            currentSubSequence[subSequenceUp++] = i;
            // If there will be no more possible items, just quit
            if(holdingSet.isEmpty() || sourceSet.isEmpty()) break;
            removeBordering();
            // Remove duplicate skips
            removePattern();
        }
    }


    /**
     * Returns a new String array identical to most recently generated
     * sequence.  If no sequence has been generated (i.e., if none of the
     * shuffling methods have been used) a value of null will be returned.
     *
     * @return String[]
     */
    public String[] getSeqeunce() {
        if(sequence == null) return null;
        return sequence.clone();
    }


    /**
     * This will return the last generated sequence.  If no sequence has
     * been generated (i.e., if none of the shuffling methods have been
     * used) an empty String will be returned.
     *
     * @return String
     */
    @Override
    public String toString() {
        if(sequence == null) return "";
        if(sequence.length == 0) return "";
        StringBuilder output = new StringBuilder();
        for(int i =0; i < sequence.length; i++) {
            output.append(sequence[i]);
        }
        return output.toString();
    }


    /**
     * This will return an int array representing the generated sequence.
     * If no sequence has been generated (i.e., if none of the shuffling
     * methods have been used) an empty array will be returned.
     *
     * @return int[]
     */
    public int[] toInts() {
        if(sequence == null) return new int[0];
        int[] output = new int[sequence.length];
        if(sequence.length == 0) return output;
        for(int i = 0; i < sequence.length; i++) {
            output[i] = Integer.parseInt(sequence[i].trim());
        }
        return output;
    }


    /**
     * This will return a string representation of a newly generated
     * sequence.  The sequence will be generated based on the
     * simpleShuffle() method.  This is the same as <CODE>generate(int
     * howMany, int which)</CODE> in which the variable "<CODE>which</CODE>"
     * is passed the constant <CODE>SIMPLE</CODE>.
     *
     * @param howMany
     * @return String
     */
    public String generate(int howMany) {
        simpleShuffle(howMany);
        return toString();
    }

    /**
     * This will return a string representation of a newly generated
     * sequence.  The sequence will be generated based on a method
     * determined by the variable which; valid constants include:<OL>
     * <LI><CODE>RANDOM</CODE></LI>
     * <LI><CODE>SIMPLE</CODE></LI>
     * <LI><CODE>ALT1</CODE></LI>
     * <LI><CODE>ALT2</CODE></LI>
     * <LI><CODE>ALT3</CODE></LI>
     * <LI><CODE>ALT4</CODE></LI>
     * <LI><CODE>ALT5</CODE></LI>
     * <LI><CODE>ALT6</CODE></LI>
     * <LI><CODE>ALT7</CODE></LI>
     * <LI><CODE>ALT8</CODE></LI></OL>
     *
     * @param howMany
     * @return String
     */
    public String generate(int howMany, int which) {
        generation(howMany, which);
        return toString();
    }

    /**
     * This will return int[] representation of a newly generated
     * sequence.  The sequence will be generated based on a method
     * determined by the variable which; valid constants include:<OL>
     * <LI><CODE>RANDOM</CODE></LI>
     * <LI><CODE>SIMPLE</CODE></LI>
     * <LI><CODE>ALT1</CODE></LI>
     * <LI><CODE>ALT2</CODE></LI>
     * <LI><CODE>ALT3</CODE></LI>
     * <LI><CODE>ALT4</CODE></LI>
     * <LI><CODE>ALT5</CODE></LI>
     * <LI><CODE>ALT6</CODE></LI>
     * <LI><CODE>ALT7</CODE></LI>
     * <LI><CODE>ALT8</CODE></LI></OL>
     *
     * @param howMany
     * @return and array of ints
     */
    public int[] generateInts (int howMany, int which) {
        generation(howMany, which);
        return toInts();
    }

    /**
     * This will return a string representation of a newly generated
     * sequence.  The sequence will be generated based on a method
     * determined by the variable which; valid constants include:<OL>
     * <LI><CODE>RANDOM</CODE></LI>
     * <LI><CODE>SIMPLE</CODE></LI>
     * <LI><CODE>ALT1</CODE></LI>
     * <LI><CODE>ALT2</CODE></LI>
     * <LI><CODE>ALT3</CODE></LI>
     * <LI><CODE>ALT4</CODE></LI>
     * <LI><CODE>ALT5</CODE></LI>
     * <LI><CODE>ALT6</CODE></LI>
     * <LI><CODE>ALT7</CODE></LI>
     * <LI><CODE>ALT8</CODE></LI></OL>
     *
     * @param howMany
     * @return String
     */
    public String generate(int howMany, int which, int [] previous) {
        generation(howMany, which, previous);
        return toString();
    }

    /**
     * This will return int[] representation of a newly generated
     * sequence.  The sequence will be generated based on a method
     * determined by the variable which; valid constants include:<OL>
     * <LI><CODE>RANDOM</CODE></LI>
     * <LI><CODE>SIMPLE</CODE></LI>
     * <LI><CODE>ALT1</CODE></LI>
     * <LI><CODE>ALT2</CODE></LI>
     * <LI><CODE>ALT3</CODE></LI>
     * <LI><CODE>ALT4</CODE></LI>
     * <LI><CODE>ALT5</CODE></LI>
     * <LI><CODE>ALT6</CODE></LI>
     * <LI><CODE>ALT7</CODE></LI>
     * <LI><CODE>ALT8</CODE></LI></OL>
     *
     * @param howMany
     * @return and array of ints
     */
    public int[] generateInts (int howMany, int which, int [] previous) {
        generation(howMany, which, previous);
        return toInts();
    }


    /**
     * This will generate a new sequence, returning nothing.
     * The sequence will be generated based on a method
     * determined by the variable which; valid constants include:<OL>
     * <LI><CODE>RANDOM</CODE></LI>
     * <LI><CODE>SIMPLE</CODE></LI>
     * <LI><CODE>ALT1</CODE></LI>
     * <LI><CODE>ALT2</CODE></LI>
     * <LI><CODE>ALT3</CODE></LI>
     * <LI><CODE>ALT4</CODE></LI>
     * <LI><CODE>ALT5</CODE></LI>
     * <LI><CODE>ALT6</CODE></LI>
     * <LI><CODE>ALT7</CODE></LI>
     * <LI><CODE>ALT8</CODE></LI></OL>
     *
     * @param howMany
     * @return String
     */
    public void generation(int howMany, int which) {
        switch(which) {
            case RANDOM:
                randomSelect(howMany);
                break;
            case SIMPLE:
                simpleShuffle(howMany);
                break;
            case ALT1:
                altShuffle1(howMany);
                break;
            case ALT2:
                altShuffle2(howMany);
                break;
            case ALT3:
                altShuffle3(howMany);
                break;
            case ALT4:
                altShuffle4(howMany);
                break;
            case ALT5:
                altShuffle5(howMany);
                break;
            case ALT6:
                altShuffle6(howMany);
                break;
            case ALT7:
                altShuffle7(howMany);
                break;
            case ALT8:
                altShuffle8(howMany);
                break;
            default:
                simpleShuffle(howMany);
                break;
        }
    }


    /**
     * This will generate a new sequence, returning nothing.
     * The sequence will be generated based on a method
     * determined by the variable which; valid constants include:<OL>
     * <LI><CODE>RANDOM</CODE></LI>
     * <LI><CODE>SIMPLE</CODE></LI>
     * <LI><CODE>ALT1</CODE></LI>
     * <LI><CODE>ALT2</CODE></LI>
     * <LI><CODE>ALT3</CODE></LI>
     * <LI><CODE>ALT4</CODE></LI>
     * <LI><CODE>ALT5</CODE></LI>
     * <LI><CODE>ALT6</CODE></LI>
     * <LI><CODE>ALT7</CODE></LI>
     * <LI><CODE>ALT8</CODE></LI></OL>
     *
     * @param howMany
     * @return String
     */
    public void generation(int howMany, int which, int [] previous) {
        switch(which) {
            case RANDOM:
                randomSelect(howMany);
                break;
            case SIMPLE:
                simpleShuffle(howMany);
                break;
            case ALT1:
                altShuffle1(howMany, previous);
                break;
            case ALT2:
                altShuffle2(howMany, previous);
                break;
            case ALT3:
                altShuffle3(howMany, previous);
                break;
            case ALT4:
                altShuffle4(howMany, previous);
                break;
            case ALT5:
                altShuffle5(howMany, previous);
                break;
            case ALT6:
                altShuffle6(howMany, previous);
                break;
            case ALT7:
                altShuffle7(howMany, previous);
                break;
            case ALT8:
                altShuffle8(howMany, previous);
                break;
            default:
                simpleShuffle(howMany);
                break;
        }
    }
}
