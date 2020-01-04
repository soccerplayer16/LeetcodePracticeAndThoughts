The idea is straightforard - we first go through the String and count the number of appearance for each letters. Then we use a pointer i to go through every letter in the string. Once we see a letter (at index = i) that has counts less than k; we know the final valid answer must NOT include this letter. ==> then we SHOULD SEARCH FOR ANSWERS from substring before i, and after i. The divide and conquer part then becomes: search(start of string, i - 1), and then search(i + 1, end of string).

So the main function would be just one loop to loop through index i to check whether the letter at position i is invalid (count < k). If it is invalid, then search before and after it.



class Solution {
	public int longestSubstring(String s, int k) {
		int n = s.length();
		return helper(0, n - 1, s, k); /* to avoid doing hard copy of substring, I used start and end as the beginning and ending of the substring we need to do the search. This helper function returns the max length that satisfies criteria for the substring between index 0 to n - 1 */
	}

	private int helper(int start, int end, String s, int k) {

		if (end - start + 1 < k) {
			return 0; /* stop searching if substring is already too short */
		}
		
		/*  redo the counting process for the current substring */
		int[] letters = new int[26];
		for (int i = start; i <= end; i++) {
			letters[s.charAt(i) - 'a'] ++;
		}

		int left = start;
		int res = 0;
		/*  key loop process */
		for (int i = start; i <= end; i++) {
			if (letters[s.charAt(i) - 'a'] < k && letters[s.charAt(i) - 'a'] > 0) { /* we land at index == i where this letter is INVALID, then we need divide and conquer*/
				int subres = helper(left, i - 1, s, k); /* chopped part */
				res = Math.max(res, subres);
				left = i + 1; /* later part, basically this loop ensures that the string is CHOPPED into multiple pieces with INVALID letters EXCLUDED */
			}
		}
		
		/* if the left is never updated, that means we never met an invalid letter in the entire substring, so we can directly return the lenght of this substring. */
		if (left == start) {
			return end - start + 1;
		}

		int subres = helper(left, end, s, k); /* we still have the last part not searched yet */
		res = Math.max(res, subres);
		return res;
	}

}
