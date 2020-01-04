
'''
The basic idea is, we do DFS through the graph and marked the order (or call it rank, or timestamp) it is in this DFS process. Bascially this records how far this vertex(or we say node, or server) away from the first vertex since you started your DFS.

While going along this DFS, you also need to mark the lowester order (or rank) of the node that can reach this node (except from its previous node). If there is any other connection to this node other than its previous node (the one that help you enter DFS of this level), then you know you have more than 1 connection to this vertex, then it is NOT a critical connection. A critical connection that connects two nodes, but these two nodes NOT necessarily have only one neighbor. They may have more other neighbors. The very special things about these two nodes are, their following nodes (except its parent/previous node) in DFS always come after itself. ("following nodes" means nodes that have higher rank).

Another confusion is, the num on the vertex (like a name for the vertex/server) is not necessarily same as the order/rank/timestamp, although they are all integers from 0~N-1. So in the following, if it is a rank, I always use parameter names like xxxRank, if it is a num (like a name) on the vertex, I use names like xxxVertex.

Probably still little confusing.... maybe look at codes can make you feel better.
'''


class Solution:
    def criticalConnections(self, n: int, connections: List[List[int]]) -> List[List[int]]:

	graph = [[] for _ in range(n)] ## vertex i ==> [its neighbors]
		
        currentRank = 0 ## please note this rank is NOT the num (name) of the vertex itself, it is the order of your DFS level
		
        lowestRank = [i for i in range(n)] ## here lowestRank[i] represents the lowest order of vertex that can reach this vertex i
		
        visited = [False for _ in range(n)] ## common DFS/BFS method to mark whether this node is seen before
        
        ## build graph:
        for connection in connections:
            ## this step is straightforward, build graph as you would normally do
            graph[connection[0]].append(connection[1])
            graph[connection[1]].append(connection[0])
        
        res = []
        prevVertex = -1 ## This -1 a dummy. Does not really matter in the beginning. 
		## It will be used in the following DFS because we need to know where the current DFS level comes from. 
		## You do not need to setup this parameter, I setup here ONLY because it is more clear to see what are passed on in the DFS method.
		
        currentVertex = 0 ## we start the DFS from vertex num 0 (its rank is also 0 of course)
        self._dfs(res, graph, lowestRank, visited, currentRank, prevVertex, currentVertex)
        return res
    
    def _dfs(self, res, graph, lowestRank, visited, currentRank, prevVertex, currentVertex):

        visited[currentVertex] = True 
        lowestRank[currentVertex] = currentRank

        for nextVertex in graph[currentVertex]:
            if nextVertex == prevVertex:
                continue ## do not include the the incoming path to this vertex since this is the possible ONLY bridge (critical connection) that every vertex needs.

            if not visited[nextVertex]:
                self._dfs(res, graph, lowestRank, visited, currentRank + 1, currentVertex, nextVertex)
				# We avoid visiting visited nodes here instead of doing it at the beginning of DFS - 
				# the reason is, even that nextVertex may be visited before, we still need to update my lowestRank using the visited vertex's information.

            lowestRank[currentVertex] = min(lowestRank[currentVertex], lowestRank[nextVertex]) 
			# take the min of the current vertex's and next vertex's ranking
            if lowestRank[nextVertex] >= currentRank + 1: ####### if all the neighbors lowest rank is higher than mine + 1, then it means I am one connecting critical connection ###
                res.append([currentVertex, nextVertex])
