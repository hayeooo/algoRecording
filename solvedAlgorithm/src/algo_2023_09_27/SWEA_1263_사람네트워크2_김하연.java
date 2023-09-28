package algo_2023_09_27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * SWEA 1263: 사람 네트워크2
 * 
 * 사람 네트워크에서 누가 가장 영향력 있는 사람인지 알 수 있는 척도로서 다음을 분석한다.
 * N: 입력 사람 네트워크(그래프)의 노드 수
 * Closeness Centrality(CC): 네트워크 상에서 한 사용자가 다른 모든 사람에게 얼마나 가까운가
 * CC(i) = SUM(dist(i,j)) 단, dist(i,j)는 노드 i로부터 노드 j까지의 최단 거리이다.
 * 
 * 입력으로 주어지는 사람의 네트워크에서 자신을 연결하는 간선은 없다.
 * 
 * 1. bfs를 사용해서 특정 노드에서 모든 노드까지의 최단 거리 합을 저장한다.
 * 2. 최단 거리 합 중 가장 작은 값을 업데이트 한다.
 * 
 */

class Node{
	int num;
	int dist;
	
	Node(){}
	
	Node(int num,int dist){
		this.num=num;
		this.dist=dist;
	}
}
public class SWEA_1263_사람네트워크2_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int[][] graph;			// 인접 행렬
	static boolean[] visited;		// 각 노드 방문 여부를 저장하는 배열
	
	static int T;			// 테스트케이스 수
	static int N;			// 사람 수
	static int minDist;		// CC값 최솟값
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		// 테스트케이스를 입력받는다.
		T=Integer.parseInt(br.readLine().trim());
		
		for (int test_case=1;test_case<=T;test_case++) {
			st=new StringTokenizer(br.readLine().trim());
			
			// 사람 수
			N=Integer.parseInt(st.nextToken());
			graph=new int[N][N];
			
			// 그래프 정보 입력
			for (int row=0;row<N;row++) {
				for (int col=0;col<N;col++) {
					graph[row][col]=Integer.parseInt(st.nextToken());
				}
			}
			
			minDist=Integer.MAX_VALUE;
			
			// 0 ~ N-1 사람까지 bfs를 돌린다.
			for (int idx=0;idx<N;idx++) {
				minDist=Math.min(minDist,bfs(idx));
			}
			// 결과 추가
			sb.append("#").append(test_case).append(" ").append(minDist).append("\n");
		}
		System.out.println(sb);
	}
	
	public static int bfs(int start) {
		
		Queue<Node> que=new ArrayDeque<>();
		visited=new boolean[N];
		
		que.add(new Node(start,0));
		visited[start]=true;
		int totalDist=0;
		
		// 자신을 제외한 모든 지점에서 최단 거리의 합을 구한다.
		while (!que.isEmpty()) {
			Node curNode=que.poll();
			
			for (int nextNode=0;nextNode<N;nextNode++) {
				// 아직 방문하지 않았고 서로 연결되어 있는 노드라면
				if (!visited[nextNode] && graph[curNode.num][nextNode]==1) {
					// 큐에 넣는다
					que.add(new Node(nextNode,curNode.dist+1));
					visited[nextNode]=true;
					totalDist+=(curNode.dist+1);
				}
			}
		}
		
		return totalDist;
	}

}
