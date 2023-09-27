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
 * bfs를 사용해서 출발 노드에서 각 노드까지의 최단 거리를 저장한다.
 * 
 */
public class SWEA_1263_사람네트워크2_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static int[][] graph;
	static boolean[] visited;
	
	static int T;			// 테스트케이스 수
	static int N;			// 사람 수
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트케이스를 입력받는다.
		T=Integer.parseInt(br.readLine().trim());
		
		for (int test_case=1;test_case<=T;test_case++) {
			st=new StringTokenizer(br.readLine().trim());
			
			// 사람 수
			N=Integer.parseInt(st.nextToken());
			
			// 그래프 정보 입력
			for (int row=0;row<N;row++) {
				for (int col=0;col<N;col++) {
					graph[row][col]=Integer.parseInt(st.nextToken());
				}
			}
			
			// 0 ~ N-1 사람까지 bfs를 돌린다.
			
			
		}
	}
	
	public static void bfs(int start) {
		
		Queue<Integer> que=new ArrayDeque<>();
		
		// 자신을 제외한 모든 지점에서 최단 거리의 합을 구한다.
	}

}
