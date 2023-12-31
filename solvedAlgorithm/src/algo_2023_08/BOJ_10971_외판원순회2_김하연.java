package algo_2023_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 
 * 완전 탐색
 * 임의의 도시에서 출발해서 모든 도시를 돌고 출발지로 돌아왔을 때
 * 가장 적은 비용이 드는 경로를 구한다.
 * 
 * 1. 임의의 도시에서 출발한다. (결과적으로 사이클을 형성하기 때문에, 출발지를 다르게 할 필요가 없다.)
 * 2. 갈 수 있는 경로 중 방문하지 않은 도시를 선택한다.
 * 3. 모든 도시를 다 방문하였을 때, 출발지로 돌아오는 경로가 있다면 비용을 계산하고 최솟값을 갱신한다.
 */
public class BOJ_10971_외판원순회2_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;						// 도시의 수
	static int[][] cost;				// 도시 간 이동 비용
	static boolean[] visited;			// 해당 도시를 방문했는지 여부를 저장하는 배열
	static int minCost;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 도시의 수를 입력받는다.
		N=Integer.parseInt(br.readLine());
		
		cost=new int[N][N];
		visited=new boolean[N];
		
		// 도시 간 이동 비용을 저장한다.
		for (int row=0;row<N;row++) {
			st=new StringTokenizer(br.readLine().trim());
			for (int col=0;col<N;col++) {
				cost[row][col]=Integer.parseInt(st.nextToken());
			}
		}
		minCost=Integer.MAX_VALUE;
		visited[0]=true;
		dfs(0,1,0);
		System.out.println(minCost);

	}
	
	public static void dfs(int city, int cnt, int totalCost) {
		
		// 모든 도시를 다 방문하였을 경우
		if(cnt==N) {
			// 출발지와 연결되었는지 확인한다.
			if (cost[city][0]!=0) {
				minCost=Math.min(minCost, totalCost+cost[city][0]);
			}
			return;
		}
		
		for (int nextCity=0;nextCity<N;nextCity++) {
			
			if (cost[city][nextCity]==0) continue;
			if (visited[nextCity]) continue;
			
			visited[nextCity]=true;
			dfs(nextCity,cnt+1,totalCost+cost[city][nextCity]);
			visited[nextCity]=false;
		}
	}

}
