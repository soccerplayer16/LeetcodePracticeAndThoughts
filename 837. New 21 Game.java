//This is a very interesting problem. It is not difficult to realize this problem should be resolved by DP because the scores are based on previous attempt and previous scores.
//We should be able to realize that we need to define a f[i] represents the probability to get i points. 
//Starting point is f[0] = 1 because the probablity to be at point == 0 is 100%.
// f[i] = p * f[i - 1] + p * f[i - 2] + .... p * f[i - w]. But all these sum have to be valid.
// What are the conditions to make sure which item is valid or not? It depends on K and W.
// If i < K, that means you can continue drawing at i; otherwise, you will stop there. Can not move further.
// Continue from above, if i >= W, then f[i-W-1]... would NOT be possibly to contribute to f[i]
// The trick is to use a preProbSum to compute valid f[i-1] + f[i - 2] + ...f[i-W]

