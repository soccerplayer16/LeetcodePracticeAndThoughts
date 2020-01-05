//This is a very interesting problem. It is not difficult to realize this problem should be resolved by DP because the scores are based on previous attempt and previous scores.
//We should be able to realize that we need to define a f[i] represents the probability to get i points. 
//Starting point is f[0] = 1 because the probablity to be at point == 0 is 100%.
// f[i] = p * f[i - 1] + p * f[i - 2] + .... p * f[i - w]. But all these sum have to be valid.
// What are the conditions to make sure which item is valid or not? It depends on K and W.
// If i < K, that means you can continue drawing at i; otherwise, you will stop there. Can not move further.
// Continue from above, if i >= W, then f[i-W-1]... would NOT be possibly to contribute to f[i]
// The trick is to use a preProbSum to compute valid f[i-1] + f[i - 2] + ...f[i-W]

/// 如何得到O(N）时间 需要记录前面的window概率和 /////
class Solution {
    public double new21Game(int N, int K, int W) {
        
        if (K == 0) {
            return 1; 
        }
        
        /// f[x] represents the probilibty to get x points! ///
        double p = 1.0 * 1/W;
        double[] f = new double[N + 1];
        f[0] = 1;
        
        double preWSum = 1; // prevoius window sum
        ///  .... i-W-1, (i-W, ....i - 1), i
        for (int i = 1; i <= N; i++) {
            f[i] = p * preWSum; //1st time preWSum = 1 (always start from 0 points, so preWSum == 0 at this moment)
            /// Here p * preWSum的意义在于，到达前面所有f[i-1]到f[i-W]这些点的概率是preWSum,在这个基础上，从其中任意一个再拿到一个1...W之间的数的概率是p, 所以是p * preWSum
            
            /// 更新preWSum，如果这个i小于K，也就是说这个i可以做一个合法的中间点，所以要加到preWSum里面（如果i>=K,那i就不合法做中间点了，就不能加了）
            if (i < K) {
                preWSum += f[i]; // if i < k, that means, it is possible to have a mid step at this step, so preWSum should add f[i]
            }
            
            /// 如果i>=W了，那下一个i就要>W了，就需要在preWSum中减去开头的那个点的概率了，因为从最开头那个点没法到达下一个i了
            if (i - W >= 0) { // if i - W >= 0, the preWSum should be updated, sliding window!
                preWSum -= f[i - W];
            }
        }
        
        double res = 0;
        for (int i = K; i <= N; i++) {
            res += f[i];
        }
        
        return res;
    }
}
