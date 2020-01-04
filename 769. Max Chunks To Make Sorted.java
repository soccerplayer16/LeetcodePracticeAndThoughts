//
Though process: think about what are the conditions that we can make a cut after a number at index = i?
e.g.: 
xxxxxi|yyyy, this cut can be done if all the y are larger than xxxx and i -> it is so simple!

so we only need to know, at index = i, what is the min number after me, and what is the max number in the range from begeinning to me?

we can realize this by having two arrays: fmax (looking to the left), fmin (looking to the right)
//


class Solution {
    public int maxChunksToSorted(int[] arr) {
        
        int n = arr.length;
        int[] fmin = new int[n]; // look to the right, min num after i, excluding i
        int[] fmax = new int[n]; // look to the left, max num before i, excluding i
 
        fmin[n - 1] = Integer.MAX_VALUE; // just used to calcluate the index = n - 2
        fmax[0] = Integer.MIN_VALUE; // just used to calcluate the index = 1
        
        for (int i = 1; i < n; i++) {
            fmax[i] = Math.max(fmax[i - 1], arr[i - 1]);
        }
        
        for (int i = n - 2; i >= 0; i--) {
            fmin[i] = Math.min(fmin[i + 1], arr[i + 1]);
        }
        
        int cut = 0;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] < fmin[i] && fmax[i] < fmin[i]) { //i can cut after index = i or not//
                // the condition that can be cut is, (cur number is smaller than all the numbers after me)
                // && all the numbers before me + me are smaller than all the numbers after me.
                cut ++;
            }
        }
        return cut + 1; // chuck number == cut + 1
    }
}
