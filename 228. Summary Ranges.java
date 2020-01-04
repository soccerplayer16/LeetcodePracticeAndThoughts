# O(N) time, O(1) space. Loop through all the elements and see whether next element is continuous or not. If yes, keep moving the pointer. If not, stop moving and append to the result list.

class Solution {
	public List<String> summaryRanges(int[] nums) {
		List<String> res = new LinkedList<>();
		
		int i = 0;
		int start, end; 

		while (i < nums.length) {
			start = nums[i];
			while (i < nums.length - 1 && nums[i] == nums[i + 1] - 1) {
				i++;
			}
			if (i < nums.length) {
				end = nums[i];
			} else {
				end = start;
			}

			if (start == end) {
				res.add("" + start);
			} else {
				res.add("" + start + "->" + end);
			}
			i++;
		}
		return res;
	}
}
