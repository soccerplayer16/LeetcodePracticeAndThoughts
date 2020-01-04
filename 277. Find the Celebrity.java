//
//With above figure, it might be easier to understand. The final solution (let's name it as "ans") must have this property: for all rows that has row = ans will be 0, for all the cols that has col = ans will be 1.

//So with this goal in mind, when we search for the ans, we do three loops as in my codes:
//(1) move cell index laterally, once it hits a "1", then update the ans = col index, then continue moving laterally starting from the diaognal cell (see the arrows in the figure), to its right. Once you hit another "1", then it means current ans is not valid, so update it again.... keep going laterally until you find an ans that has NO "1" behind it (from col == ans to col == n - 1).

//(2) Verify this ans laterally from col index == 0 to col index == ans - 1, need to check it is all 0 along this row.

//(3) Verify from top to bottom at row index = ans, make sure it is all 1.
//
//Codes:

public class Solution extends Relation {
	public int findCelebrity(int n) {

		int ans = 0;
		for (int col = 0; col < n; col++) {
			if (knows(ans, col)) {
				ans = col;
			}
		}

		///* at this point, we already find an ans which has all 0 at its row for col >= its row number//
		for (int col = 0; col < ans; col++) {
			if (knows(ans, col)) {
				return -1;
			}
		}

		for (int row = 0; row < n; row++) {
			if (!knows(row, ans)) {
				return -1;
			}
		}

		return ans;
	}
}
