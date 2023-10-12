package algo_2023_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * SWEA 7465: 창용 마을 무리의 개수
 * 
 * 창용 마을에는 N명의 사람이 살고 있다.
 * 사람은 1번부터 N번 사람까지 번호가 붙어져있다.
 * 두 사람은 서로를 알고 있는 관계일 수 있고, 아닐 수 있다.
 * 두 사람이 서로 아는 관계이거나 몇 사람을 거쳐서 알 수 있는 관계라면, 이러한 사람들을 모두 다 묶어서 하나의 무리라고 한다.
 * 창용 마을에 몇 개의 무리가 존재하는지 계산한다.
 * 
 * << 풀이 방법>> 
 * 서로를 알고 있는 두 사람의 번호가 주어진다.
 * 인접 리스트 형태의 그래프 형태로 바꾸고 BFS로 무리의 개수를 구한다.
 */
public class SWEA_7465_창용마을무리의개수_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T;			// 테스트케이스 개수
	static int N, M;		// N: 사람의 수, M: 서로를 알고 있는 사람의 관계 수
	static ArrayList<Integer>[] graph;	// 각 번호의 사람들이 서로 알고 있는 사람 정보를 담은 리스트
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		// 테스트케이스 개수를 입력받는다.
		T=Integer.parseInt(br.readLine().trim());
		
		for (int test_case=1;test_case<=T;test_case++) {
			st=new StringTokenizer(br.readLine().trim());
			
			// 사람의 수(N)와 서로를 알고 있는 사람의 수(M)을 입력받는다.
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			
			// 서로를 알고 있는 사람 정보를 입력 받기 전, 초기화
			visited=new boolean[N+1];
			graph=new ArrayList[N+1];
			for (int idx=0;idx<=N;idx++) {
				graph[idx]=new ArrayList<>();
			}
			
			// 서로를 알고 있는 사람의 관계 정보를 입력받는다.
			for (int idx=0;idx<M;idx++) {
				st=new StringTokenizer(br.readLine().trim());
				
				int node1=Integer.parseInt(st.nextToken());
				int node2=Integer.parseInt(st.nextToken());
				
				graph[node1].add(node2);
				graph[node2].add(node1);
			}
			
			int group=0;			// 몇 개의 무리를 가지고 있는지 저장하는 변수
			for (int person=1;person<=N;person++) {
				if(!visited[person]) {
					bfs(person);
					group++;
				}
			}
			sb.append("#").append(test_case).append(" ").append(group).append("\n");
		}
		System.out.print(sb);
	}
	
	public static void bfs(int start) {
		Queue<Integer> que=new ArrayDeque<>();
		que.offer(start);
		visited[start]=true;
		
		while (!que.isEmpty()) {
			int curNode=que.poll();
			
			// 현 번호가 알고 있는 사람(인접 노드)
			for (int adj:graph[curNode]) {
				// 이미 무리에 들어간 경우
				if (visited[adj]) continue;
				
				// 무리에 들어가지 않은 알고 있는 사람인 경우
				// que에 넣는다.
				que.offer(adj);
				visited[adj]=true;
			} 
		}
	}

}
