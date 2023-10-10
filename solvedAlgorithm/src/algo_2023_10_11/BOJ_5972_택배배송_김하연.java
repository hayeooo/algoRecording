package algo_2023_10_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * BOJ 5972: 택배 배송
 * 
 * << 문제  >>
 * 농부 현서는 농부 찬홍이에게 택배를 배달해줘야 한다.
 * 가는 길에 만나는 모든 소들에게 여물을 줘야 한다.
 * 현서는 최소한의 소들을 만나면서 지나간다.
 * N개의 헛간과, 소들의 길인 M개의 양방향 길이 그려져 있고, 각각의 길은 C마리의 소가 있다.
 * 소들의 길은 두 개의 떨어진 헛간인 Ai와 Bi를 잇는다.
 * 두 개의 헛간은 하나 이상의 길로 연결되어 있을 수 있다.
 * 농부 현서는 헛간 1에 있고 농부 찬홍이는 헛간 N에 있다.
 * 농부 현서의 지도가 주어지고 지나가는 길에 소를 만나면 줘야할 여물을 비용이 주어질 때 최소 여물은 얼마일까?
 * 
 * << 풀이 방법 >>
 * 헛간 1에서 출발하여 도달할 수 있는 헛간까지의 여물 비용을 계산하고
 * priorityQueue에 삽입하여 최소 여물 비용을 우선적으로 뽑는다.
 * 헛간 N이 나올 시, 경로 탐색을 중단한다.(최소 여물임을 보장하기 때문)
 * : dijkstra 다시 풀어보기
 */

public class BOJ_5972_택배배송_김하연 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int N, M;		// 농부 찬홍이의 위치(N), 소들의 길(M)
	static ArrayList<Road>[] graph;	// 각 헛간에서 갈 수 있는 헛간과 소 마리 수를 저장한다.
	static boolean[] visited;				// 출발지점부터 각 노드까지 최소 비용을 저장한다.
	static int[] dist;
	
	public static class Road implements Comparable<Road>{
		int num;
		int weight;
		
		Road(int num,int weight){
			this.num=num;
			this.weight=weight;
		}

		@Override
		public int compareTo(Road o) {
			return this.weight-o.weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// N, M 입력받는다.
		st=new StringTokenizer(br.readLine().trim());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		// graph 초기화
		graph=new ArrayList[N+1];
		visited=new boolean[N+1];
		dist=new int[N+1];
		
		for (int idx=0;idx<=N;idx++) {
			graph[idx]=new ArrayList<Road>();
			dist[idx]=Integer.MAX_VALUE;
		}
		
		// Ai,Bi,Ci 입력받는다.
		for (int idx=0;idx<M;idx++) {
			st=new StringTokenizer(br.readLine().trim());
			int Ai=Integer.parseInt(st.nextToken());
			int Bi=Integer.parseInt(st.nextToken());
			int Ci=Integer.parseInt(st.nextToken());
			
			// 양방향이므로 두 군데 모두 저장한다.
			graph[Ai].add(new Road(Bi,Ci));
			graph[Bi].add(new Road(Ai,Ci));
		}
		
		// 1번 헛간부터 인접한 헛간과 누적 여물비용을 priorityQueue에 넣는다.
		PriorityQueue<Road> que=new PriorityQueue<>();
		que.add(new Road(1,0));
		dist[1]=0;
		
		while(!que.isEmpty()) {
			Road now=que.poll();
			
			// 이미 최소 비용을 처리한 노드라면
			// 확인해봤자 최소비용 아님
			// 이미 다 확인해봄.
			if (visited[now.num]) continue;
			
			visited[now.num]=true;
			
			// 인접한 노드들 중 비용이 최소인 것만 queue에 넣는다.
			for (Road next:graph[now.num]) {
				if(visited[next.num]) continue;
				
				if (dist[next.num]>=dist[now.num]+next.weight) {
					dist[next.num]=dist[now.num]+next.weight;
					que.add(new Road(next.num,dist[next.num]));
				}
				
			}
		}
		// N 위치의 최소 비용을 출력한다.
		System.out.println(dist[N]);
		
	}

}
