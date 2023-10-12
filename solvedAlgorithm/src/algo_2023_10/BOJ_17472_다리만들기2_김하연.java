package algo_2023_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BOJ 17472: 다리만들기
 * 섬으로 이루어진 나라가 있고, 모든 섬을 다리로 연결하려고 한다.
 * 이 나라의 지도는 N*M 크기의 이차원 격자로 나타낼 수 있고, 격자의 각 칸은 땅이거나 바다이다.
 * 섬은 연결된 땅이 상하좌우로 붙어있는 덩어리를 말한다.
 * 
 * 다리는 바다에만 건설할 수 있고, 다리의 길이는 다리가 격자에서 차지하는 칸의 수이다.
 * 다리를 연결해서 모든 섬을 연결하려고 한다.
 * 다리의 양 끝은 섬과 인접한 바다 위에 있어야 하고, 한 다리의 방향이 중간에 바뀌면 안된다.
 * 다리의 길이는 2 이상이어야 한다.
 * 
 * <<풀이 방법>> : 그래프로 접근
 * 1. 섬(node)을 구분하기 위한 bfs
 * 2. 섬과 섬을 연결하는 가능한 모든 다리(link)를 구한다.(그래프의 가능한 모든 간선 찾기)
 * 3. Kruskal algorithm으로 사이클을 형성하지 않으면서 최소 다리 길이를 구한다.
 * 
 */
class Pos{
	int r;
	int c;
	
	Pos(){}
	
	Pos(int r,int c){
		this.r=r;
		this.c=c;
	}
}
class MstNode implements Comparable<MstNode>{
	int node1,node2,length;
	
	public MstNode(int node1,int node2,int length) {
		this.node1=node1;
		this.node2=node2;
		this.length=length;
	}

	@Override
	public int compareTo(MstNode o) {
		return this.length-o.length;
	}
}

public class BOJ_17472_다리만들기2_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N,M;
	static int[][] map;
	static boolean[][] visited;
	static int islandNum;
	static PriorityQueue<MstNode> pq;
	static int[] parent;
	
	static int[] dx= {-1,0,1,0};		// 북, 동, 남, 서
	static int[] dy= {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		// 지도의 세로 크기와 가로 크기를 입력받는다.
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new int[N][M];
		
		for (int row=0;row<N;row++) {
			st=new StringTokenizer(br.readLine().trim());
			for (int col=0;col<M;col++) {
				map[row][col]=Integer.parseInt(st.nextToken());
			}
		}
		
		// 섬을 서로 구분해준다.
		islandNum=1;
		visited=new boolean[N][M];
		for (int row=0;row<N;row++) {
			for (int col=0;col<M;col++) {
				if (!visited[row][col] && map[row][col]!=0) {
					Pos pos=new Pos(row,col);
					islandBfs(islandNum,pos);
					islandNum+=1;
				}
			}
		}
		
		// 실제 섬의 개수
		islandNum-=1;
		
//		for (int row=0;row<N;row++) {
//			System.out.println(Arrays.toString(map[row]));
//		}
		
		// 세울 수 있는 모든 다리를 구한다.
		pq=new PriorityQueue<>();
		for (int row=0;row<N;row++) {
			for (int col=0;col<M;col++) {
				// 섬인 경우
				if (map[row][col]!=0) {
					for (int d=0;d<4;d++) {
						findBridge(row,col,map[row][col],d,0);
					}
					
				}
			}
		}
		
		// 연결할 수 있는 모든 다리 중 길이가 최소인 다리를 구한다.(Kruskal)
		System.out.println(kruskal());
	}
	
	public static int kruskal() {
		
		init();
		
		int bridgeLength=0;
		int bridgeCnt=0;
		
		while (!pq.isEmpty()) {
			// 다리의 길이가 작은 것부터 차례대로 섬에 연결한다.
			MstNode bridge=pq.poll();
			int node1=bridge.node1;
			int node2=bridge.node2;
			
			// 이미 같은 그룹에 속하는지 확인(cycle 형성하지 않기 위함)
			if (find(node1)!=find(node2)) {
				union(node1,node2);
				bridgeLength+=bridge.length;
				bridgeCnt++;
			}
		}
		
		// 모든 섬이 연결되어 있지 않은 경우 -1
		if (bridgeLength==0 || bridgeCnt!=islandNum-1) {
			bridgeLength=-1;
		}
		return bridgeLength;
	}
	
	// kruskal 부모 초기화
	public static void init() {
		parent=new int[islandNum+1];
		for (int land=1;land<islandNum;land++) {
			parent[land]=land;
		}
	}
	
	// 같은 그룹의 최상위 부모 return
	public static int find(int node) {
		if (parent[node]==node) {
			return node;
		}
		return parent[node]=find(parent[node]);
	}
	// 서로 다른 그룹에 있는 노드를 하나의 그룹으로 합치기
	public static void union(int node1,int node2) {
		int root1=find(node1);
		int root2=find(node2);
		
		if (root1>root2) {
			parent[root1]=parent[root2];
		}else {
			parent[root2]=parent[root1];
		}
	}
	// 세울 수 있는 모든 다리를 priorityQueue에 넣는다.(직접 구현)
	public static void findBridge(int row, int col, int islandNum,int d, int length) {
		
		while (true) {
			
			// 다음 위치
			row+=dx[d];
			col+=dy[d];
			
			// 지도의 범위를 넘어간 경우
			if (!isRange(row,col)) {
				break;
			}
			
			// 같은 섬인 경우
			if (map[row][col]==islandNum) {
				break;
			}
			
			// 바다인 경우
			if (map[row][col]==0) {
				length+=1;
				continue;
			}
			
			// 섬에 도착한 경우
			if (map[row][col]!=0 && map[row][col]!=islandNum) {
				if (length>=2) {
					// System.out.println(islandNum+" -> "+map[row][col]+" : "+length);
					pq.add(new MstNode(islandNum,map[row][col],length));
				}
				break;
			}
		}
		
	}
	
	// 각 섬에 고유 번호를 부여한다.
	public static void islandBfs(int num, Pos pos) {
		Queue<Pos> que=new ArrayDeque<>();
		que.offer(pos);
		visited[pos.r][pos.c]=true;
		map[pos.r][pos.c]=num;
		
		while (!que.isEmpty()) {
			Pos curPos=que.poll();
			
			for (int d=0;d<4;d++) {
				int nextR=curPos.r+dx[d];
				int nextC=curPos.c+dy[d];
				
				// 배열 범위 내에 있는지 확인
				if (!isRange(nextR,nextC)) continue;
				
				// 미방문 상태인 섬
				if (!visited[nextR][nextC] && map[nextR][nextC]!=0) {
					visited[nextR][nextC]=true;
					que.offer(new Pos(nextR,nextC));
					map[nextR][nextC]=num;
				}
				
			}
		}
		
	}
	// 배열의 범위 체크
	public static boolean isRange(int r,int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}
	
	

}
