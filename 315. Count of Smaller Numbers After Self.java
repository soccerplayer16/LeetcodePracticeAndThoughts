# This problem is interesting, we need to get how many numbers after current number are smaller than current number.
# We adopt the idea of inserting nodes in BST, and count how many counts/nodes are on my left subtree

# Technically this is O(N2) method in worse case (the input array is descending order from left to right, in which case, the BST is a linked list). In a random case, the amortized time complexity is O(NlogN).

class Solution {

	private class Node {
		int val;
		int preCount; // count how many numbers are smaller than this number
		int dup; // count how many numbers are duplicates at this number
		Node left, right;
		private Node (int v, int pre) {
			val = v;
			preCount = pre; // initialization ==> how many numbers are smaller than this number (node)
			dup = 1; // how many duplicates for this number
			left = null;
			right = null;
		}
	}

	public List<Integer> countSmaller(int[] nums) {

		if (nums == null || nums.length == 0) {
			return new ArrayList<Integer>();
		}

		int n = nums.length;
		Integer[] res = new Integer[n];

		// initialization of the BST, use last number as root of the tree //
		Node root = new Node(nums[n - 1], 0);
		res[n - 1] = 0; // root.preCount

		for (int i = n - 2; i >= 0; i--) {
			Node node = new Node(nums[i], 0);
			helper(res, node, root, 0, i); // traverse all the elements from right to left
		}

		return Arrays.asList(res);
	}

	private void helper(Integer[] res, Node node, Node root, int pre, int i) {
		// 3 cases -> where to insert the new node //
		if (node.val == root.val) {
			root.dup ++;
			res[i] = pre + root.preCount;
		} else if (node.val < root.val) {
			root.preCount ++;
			if (root.left == null) {
				root.left = node;
				res[i] = pre;
			} else {
				helper(res, node, root.left, pre, i);
			}
		} else {
			if (root.right == null) {
				root.right = node;
				res[i] = pre + root.preCount + root.dup;
			} else {
				helper(res, node, root.right, pre + root.preCount + root.dup, i);
			}
		}
	}
}
