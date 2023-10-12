package algo_2023_10;

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
 * : dijkstra 다시 풀어보기, "어디서 방문 표시"를 해야하는지(중요)
 */

public class BOJ_5972_택배배송_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;		// 헛간의 개수
	static int M;		// 소들의 길 개수
	
	static List<Node>[] graph;
	static int[] dist;
	static boolean[] visited;
	
	static class Node implements Comparable<Node>{
		int num;
		int weight;
		
		Node(int num,int weight){
			this.num=num;
			this.weight=weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight-o.weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 헛간의 개수(N)와 소들의 길(M)을 입력받는다.
		st=new StringTokenizer(br.readLine().trim());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		// 그래프 정보를 담을 리스트를 초기화한다.
		graph=new ArrayList[N+1];
		dist=new int[N+1];
		for (int num=1;num<=N;num++) {
			graph[num]=new ArrayList<Node>();
			dist[num]=Integer.MAX_VALUE;
		}
		
		visited=new boolean[N+1];
		
		// M개의 소들의 길을 입력받는다.
		for (int idx=0;idx<M;idx++) {
			st=new StringTokenizer(br.readLine().trim());
			int node1=Integer.parseInt(st.nextToken());
			int node2=Integer.parseInt(st.nextToken());
			int weight=Integer.parseInt(st.nextToken());
			
			// 양방향이므로 두 노드에 저장한다.
			graph[node1].add(new Node(node2,weight));
			graph[node2].add(new Node(node1,weight));
		}
		
		// 현서는 헛간 1에서 출발한다.
		dist[1]=0;
		PriorityQueue<Node> pq=new PriorityQueue<>();
		pq.add(new Node(1,0));
		
		while(!pq.isEmpty()) {
			Node now=pq.poll();
			
			// 이미 방문한 노드인 경우
			// 더이상 볼 필요가 없다.(이미 최소 비용을 구한 후)
			if (visited[now.num]) continue;
			
			// 이제 최소 비용을 처리하므로 방문 처리
			visited[now.num]=true;
			
			// 인접한 노드를 방문해 최소 비용을 갱신한다.
			for (Node adjNode:graph[now.num]) {
				
				if (visited[adjNode.num]) continue;
				
				// 최소비용인 경우
				if (dist[adjNode.num]>adjNode.weight+dist[now.num]) {
					dist[adjNode.num]=adjNode.weight+dist[now.num];
					pq.add(new Node(adjNode.num,dist[adjNode.num]));
				}
			}
		}
		System.out.println(dist[N]);
	}

}
