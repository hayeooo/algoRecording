package algo_2023_10_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BOJ 14940: 쉬운 최단거리
 * 지도가 주어지면 모든 지점에 대해서 목표지점까지의 거리를 구한다.
 * 오직 가로와 세로로만 움직일 수 있다.
 * 
 * 지도의 크기 n과 m이 주어진다. n은 세로의 크기, m은 가로의 크기다.(2<=n<=1000, 2<=m<=1000)
 * 다음 n개의 줄에 m개의 숫자가 주어진다. 0은 갈 수 없는 땅이고 1은 갈 수 있는 땅, 2는 목표지점이다.
 * 입력에서 2는 단 한개이다.
 * 
 * 각 지점에서 목표지점까지의 거리를 출력한다. 원래 갈 수 없는 땅인 위치는 0을 출력하고, 
 * 원래 갈 수 있는 땅인 부분 중에서 도달할 수 없는 위치는 -1을 출력한다.
 * 
 * << 풀이 방법 >> 
 * 각 지점에서 목표지점까지의 거리는 목표지점에서 각 지점까지의 거리와 같다.
 * 목표지점에서 출발하여 각 지점까지의 최단 경로를 구한다.(BFS)
 * BFS 수행 후, 갈 수 있는 땅 중에서 방문하지 못한 곳은 도달할 수 없는 위치 이므로 -1를 대입한다.
 *
 * << BFS 시간 복잡도 >>
 * 인접 행렬 : O(N^2)
 * 인접 리스트 : O(N+E)
 * 
 */
public class BOJ_14940_쉬운최단거리_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int n,m;
	static int[][] map;
	static int[][] dist;			// 방문 여부 확인 및 목표지점까지 최단 거리를 저장하는 배열
	
	static int destX,destY;			// 목표 지점의 위치
	
	static int[] dx= {-1,0,1,0};	// 북, 동, 남, 서
	static int[] dy= {0,1,0,-1};
	
	static class Pos{
		int x;
		int y;
		
		Pos(){};
		
		Pos(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		st=new StringTokenizer(br.readLine().trim());
		// 지도의 크기 n과 m을 입력받는다.
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		// 지도 정보를 입력받는다.
		map=new int[n][m];
		dist=new int[n][m];
		
		for (int row=0;row<n;row++) {
			Arrays.fill(dist[row], -1);
		}
		
		for (int row=0;row<n;row++) {
			st=new StringTokenizer(br.readLine().trim());
			for (int col=0;col<m;col++) {
				map[row][col]=Integer.parseInt(st.nextToken());
				if (map[row][col]==2) {
					destX=row;
					destY=col;
				}
			}
		}
		
		bfs(destX,destY);
		
		// 출력할 문자열을 저장한다.
		sb=new StringBuilder();
		for (int row=0;row<n;row++) {
			for (int col=0;col<m;col++) {
				if (map[row][col]==0) {
					sb.append(0).append(" ");
					continue;
				}
				sb.append(dist[row][col]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
		
	}
	
	public static void bfs(int startX,int startY) {
		Queue<Pos> que=new ArrayDeque<>();
		
		// 출발 지점 queue에 삽입 + 방문 처리와 거리 업데이트
		que.offer(new Pos(startX,startY));
		// 출발지점과 도착지점이 자기자신인 경우 최단 거리 0
		dist[startX][startY]=0;
		
		while (!que.isEmpty()) {
			Pos curPos=que.poll();
			
			// 4방 탐색 (가로, 세로)
			for (int d=0;d<4;d++) {
				int nextX=curPos.x+dx[d];
				int nextY=curPos.y+dy[d];
				
				// 배열 범위를 넘어가는지 확인
				if (!isRange(nextX,nextY)) continue;
				
				// 이미 방문한 경우 pass
				if (dist[nextX][nextY]>=0) continue;
				
				// 갈 수 없는 땅인 경우
				if (map[nextX][nextY]==0) continue;
				
				// 미방문 땅이고 갈 수 있는 땅인 경우
				// queue에 넣어준다.
				que.offer(new Pos(nextX,nextY));
				// 방문 표시 및 거리를 업데이트 한다.
				dist[nextX][nextY]=dist[curPos.x][curPos.y]+1;
			}
		}
	}
	
	public static boolean isRange(int x, int y) {
		return x>=0 && x<n && y>=0 && y<m;
	}

}
