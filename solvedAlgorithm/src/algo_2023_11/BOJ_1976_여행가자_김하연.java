package algo_2023_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * BOJ 1976: 여행 가자
 * << 문제 >>
 * 동혁이는 친구들과 함께 여행을 가려고 한다.
 * 동혁이의 여행 일정이 주어졌을 때, 이 여행 경로가 가능한 것인지 알아본다. 중간에 다른 도시를 경우해서 여행을 할 수 있다.
 * 도시들의 개수와 도시들 간의 연결 여부가 주어져 있고, 동혁이의 여행 계획에 속한 도시들이 순서대로 주어졌을 때 가능한지 여부를 판별하는 프로그램을 작성한다.
 * 같은 도시를 여러 번 방문하는 것도 가능하다.
 * 
 * << 풀이 방법 >>
 * 임의의 위치에서 시작하여 BFS를 수행했을 때, 연결된 모든 도시에 도달할 수 있다.
 * BFS 수행 결과로 도시가 방문되지 않았을 경우 여행 경로의 목적을 달성할 수 없다.
 */
public class BOJ_1976_여행가자_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;		// 도시의 수
	static int M;		// 여행 계획에 속한 도시들의 수
	
	static int[][] adj;	// 인접한 도시들의 정보를 저장하는 배열
	static boolean[] visited;	// 도시 방문 여부를 저장하는 배열
	static boolean[] toVisit;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 도시의 수를 입력받는다.
		N=Integer.parseInt(br.readLine().trim());
		adj=new int[N+1][N+1];
		visited=new boolean[N+1];
		
		// 여행 계획에 속한 도시들의 수를 입력받는다.
		M=Integer.parseInt(br.readLine().trim());
		toVisit=new boolean[N+1];
		
		for (int row=1;row<=N;row++) {
			st=new StringTokenizer(br.readLine().trim());
			for (int col=1;col<=N;col++) {
				adj[row][col]=Integer.parseInt(st.nextToken());
			}
		}
		
		int startCity=0;
		// 여행 계획에 속한 도시들의 수를 입력 받는다. 겹치는 도시는 생략해도 된다.
		st=new StringTokenizer(br.readLine().trim());
		for (int cnt=0;cnt<M;cnt++) {
			int city=Integer.parseInt(st.nextToken());
			toVisit[city]=true;
			startCity=city;
		}
	
		// 임의의 위치에서 여행을 시작해본다.
		if (M==0) {
			System.out.println("YES");
			return;
		}
		// BFS 수행 결과가 저장된다.
		bfs(startCity);
		
		boolean result=true;
		for (int city=1;city<=N;city++) {
			if (toVisit[city]&&!visited[city]) {
				result=false;
				break;
			}
		}
		
		System.out.println(result?"YES":"NO");
	}
	
	public static void bfs(int startCity) {
		
		Queue<Integer> que=new ArrayDeque<>();
		visited[startCity]=true;
		que.add(startCity);
		
		while (!que.isEmpty()) {
			int curCity=que.poll();
			
			for (int nextCity=1;nextCity<=N;nextCity++) {
				
				// 현재 도시와 같은 도시라면 continue
				if (nextCity==curCity) continue;
				
				// 이미 방문한 곳이라면 continue
				if (visited[nextCity]) continue;
				
				// 현재 도시와 연결되어 있는 경우
				if (adj[curCity][nextCity]==1) {
					visited[nextCity]=true;
					que.add(nextCity);
				}
			}
		}
	}

}
