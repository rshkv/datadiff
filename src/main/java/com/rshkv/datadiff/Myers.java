package com.rshkv.datadiff;

// https://blog.jcoglan.com/2017/02/12/the-myers-diff-algorithm-part-1/
public class Myers {
    private final String a;
    private final String b;

    public Myers(String a, String b) {
        this.a = a;
        this.b = b;
    }

    /** 
    * Iterates depths until all characters are matched.
    */
    public int shortestEdit() throws Exception {
        // Maximum depth is the sum of both lengths.
        // E.g. we have to delete all of a's characters and insert all of b's.
        int max = a.length() + b.length();
        // We're keeping track of the maximum numbers of characters we can consume in a.
        int[] maxConsumedA = new int[(2 * max) + 1];

        for (int depth = 0; depth <= max; depth++)
            // k is deletions - additions at a given depth.
            // k is in [-depth (all additions), depth (all deletions)].
            for (int k = -depth; k <= +depth; k += 2) {
                // We get to the current possible value of k at this depth via an addition iff k
                // shall match the depth or the number of matching characters is higher if we have
                // more preceding deletions.
                boolean addition = (k == -depth)
                        || (k < depth && maxConsumedA[max + k - 1] < maxConsumedA[max + k + 1]);
                // Number of consumed characters in a stays the same if we're adding a character.
                // We increment if we delete a character.
                int aConsumed = addition ? maxConsumedA[max + k + 1] : maxConsumedA[max + k - 1] + 1;
                // The charcters consumed (matched) in b is equal to the number of characters
                // consumed in a without deletions.
                int bConsumed = aConsumed - k;
                // Check if we get any charater 'for free' without requiring additional operations.
                while (aConsumed < a.length() && bConsumed < b.length() && a.charAt(aConsumed) == b.charAt(bConsumed)) {
                    aConsumed++;
                    bConsumed++;
                }
                // Return if we got all characters to match.
                if (aConsumed >= a.length() && bConsumed >= b.length())
                    return depth;
                // Otherwise remember the number of characters consumed using an additional addition/deletion.
                maxConsumedA[max + k] = aConsumed;
            }

        throw new Exception("failed to match all characters");
    }
}