package algo_2023_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 9205: 맥주 마시면서 걸어가기
 * 
 * 상근이가 집에서 맥주 한 박스를 들고 출발한다.
 * 한 박스에 맥주가 20개가 들어있고 50미터에 한 병을 마신다.
 * 편의점에 들렸을 때, 빈 병을 버리고 새 맥주병을 산다. 박스에 들어있는 맥주는 20병을 넘을 수 없다.
 * 편의점을 나선 직후에도 50미터를 가기 전에 맥주 한 병을 마셔야 한다.
 * 
 * 편의점, 상근이네 집, 페스티벌의 좌표가 주어졌을 때 행복하게 페스티벌에 도착할 수 있는지 구한다.
 * 
 * 1. 테스트케이스 개수를 입력 받는다.
 * 2. 맥주를 파는 편의점의 개수를 입력받는다.
 * 3. 상근이네 집, 편의점, 페스티벌 좌표를 입력받는다.
 * 4. 맨해튼 거리를 기준으로 하기 때문에 그래프 형태로 표현한다.(bfs)
 * 5. 상근이네 집에서 출발하여 페스티벌 정점에 도착하면 happy, 도착하지 못하면  sad를 출력한다.
 * 
 * @author HAYEON
 *
 */

public class BOJ_9205_맥주마시면서걸어가기_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int T,N;
	static List<Integer>[] graph;
	static Pos[] node;
	static boolean[] visited;
	
	static class Pos{
		int x;
		int y;
		
		Pos(){}
		
		Pos(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		T=Integer.parseInt(br.readLine().trim());
		
		for (int test_case=0;test_case<T;test_case++) {
			N=Integer.parseInt(br.readLine().trim());
			
			node=new Pos[N+2];
			
			// N+2개의 위치를 입력받는다.
			
			// 상근이의 집 위치(0번 인덱스)
			st=new StringTokenizer(br.readLine().trim());
			node[0]=new Pos(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			
			// 편의점의 위치(1~N번 인덱스)
			for (int idx=1;idx<N+1;idx++) {
				st=new StringTokenizer(br.readLine().trim());
				node[idx]=new Pos(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			
			// 페스티벌의 위치(N+1 인덱스)
			st=new StringTokenizer(br.readLine().trim());
			node[N+1]=new Pos(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			
			// 각 정점과 연결된 정점을 저장하는 그래프
			graph=new ArrayList[N+2];
			
			for (int node=0;node<N+2;node++) {
				graph[node]=new ArrayList<>();
			}
			
			// 한 곳에서 다른 곳으로 이동할 때 거리가 1000을 넘으면 더 이상 가지 못한다.
			// 이를 바탕으로 그래프의 형태를 그린다.
			for (int first=0;first<N+1;first++) {
				for (int second=first+1;second<N+2;second++) {
					// 서로 연결할 수 있는 정점 인접리스트 형태로 저장하기
					if (getDistance(node[first].x,node[second].x,node[first].y,node[second].y)<=1000) {
						graph[first].add(second);
						graph[second].add(first);
					}
				}
			}
			// bfs 결과에 따라 happy 또는 sad를 출력한다.
			System.out.println(bfs(0)?"happy":"sad");
		}
	}
	
	public static boolean bfs(int startNode) {
		// 노드의 방문 여부를 저장하는 배열
		visited=new boolean[N+2];
		Queue<Integer> que=new ArrayDeque<>();
		
		
		que.add(startNode);
		visited[startNode]=true;
		
		while(!que.isEmpty()) {
			int curNode=que.poll();
			
			// 현재 노드와 연결된 다른 노드 중에서
			for (int idx=0;idx<graph[curNode].size();idx++) {
				int nextNode=graph[curNode].get(idx);
				// 이미 방문한 정점이면 pass 
				if (visited[nextNode]) continue;
				
				// 방문하지 않았다면 큐에 넣는다.
				que.add(nextNode);
				visited[nextNode]=true;
			}
		}
		// 도착지(node index : N+1)의 방문 여부를 return
		return visited[N+1];
		
	}
	// 맨해튼 거리를 구한다.
	public static int getDistance(int x1,int x2,int y1,int y2) {
		return (Math.abs(x1-x2)+Math.abs(y1-y2));
	}
}
