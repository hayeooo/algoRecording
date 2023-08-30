package algo_2023_08_30;
/*
 * BOJ 1600: 말이 되고픈 원숭이
 * 현 위치에서 갈 수 있는 모든 방향에 대해 이동한다.
 * 1. 인접한 네 방향 2. 말의 움직임
 * 장애물은 피해야 하고 이미 방문한 칸은 방문 표시를 해야한다.
 * 
 * DFS => 시간 초과 
 * BFS: 말의 움직임 횟수를 추가적으로 관리해야 한다.
 * 말 움직임 횟수를 관리하지 않으면, 
 * 현재 칸에 말로 이동해서 도착하든 원숭이로 이동하든 둘 중 하나라도 방문했을 때
 * 방문 처리가 되기 때문에 다양한 경우의 수를 고려할 수 없다.
 */

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Status{
	int x;
	int y;
	int chance;
	int cnt;			// 이동횟수
	Status(int x,int y,int chance, int cnt){
		this.x=x;
		this.y=y;
		this.chance=chance;
		this.cnt=cnt;
	}
}
public class BOJ_1600_말이되고픈원숭이_김하연 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int K;				// 말 움직임 횟수
	static int W, H;			// 격자판의 가로, 세로 길이
	static int[][] board;		// 격자판 정보
	
	static int[] dx= {-1,0,1,0};	// 북, 동, 남, 서
	static int[] dy= {0,1,0,-1};
	static int[] horseDx= {-1,-2,-2,-1,1,2,2,1};
	static int[] horseDy= {-2,-1,1,2,2,1,-1,-2};
	static boolean[][][] visited;
	static int minCnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 말 움직임 횟수 입력 받는다.
		K=Integer.parseInt(br.readLine().trim());
		
		// 격자판의 가로, 세로 길이를 입력 받는다.
		st=new StringTokenizer(br.readLine().trim());
		W=Integer.parseInt(st.nextToken());
		H=Integer.parseInt(st.nextToken());
		
		// 격자판 정보를 입력 받는다.
		board=new int[H][W];
		for (int h=0;h<H;h++) {
			st=new StringTokenizer(br.readLine().trim());
			for (int w=0;w<W;w++) {
				board[h][w]=Integer.parseInt(st.nextToken());
			}
		}
		
		visited=new boolean[K+1][H][W];
		minCnt=Integer.MAX_VALUE;
		bfs(0,0);
		System.out.println(minCnt==Integer.MAX_VALUE?-1:minCnt);

	}
	
	public static void bfs(int x,int y) {
		
		Queue<Status> que=new LinkedList<>();
		que.offer(new Status(x,y,0,0));
		visited[0][x][y]=true;
		
		while(!que.isEmpty()) {
			Status curStatus=que.poll();
			int curX=curStatus.x;
			int curY=curStatus.y;
			int chance=curStatus.chance;
			int cnt=curStatus.cnt;
			
			if (curX==H-1 && curY==W-1) {
				minCnt=Math.min(minCnt, cnt);
				return;
			}
			
			// 현 위치에서 원숭이 모드로 이동하는 경우
			for (int d=0;d<dx.length;d++) {
				int nextX=curX+dx[d];
				int nextY=curY+dy[d];
				
				// 배열 범위를 넘어가는 경우
				if (nextX<0 || nextX>=H || nextY<0 || nextY>=W) {
					continue;
				}
				// 이미 방문한 경우
				if (visited[chance][nextX][nextY]) continue;
				
				// 장애물이 있는 경우
				if (board[nextX][nextY]==1) continue;
				
				visited[chance][nextX][nextY]=true;
				que.add(new Status(nextX,nextY,chance,cnt+1));
			}
			// 현 위치에서 말 모드로 이동하는 경우 (기회는 K번)
			if (chance<K) {
				for (int d=0;d<horseDx.length;d++) {
					int nextX=curX+horseDx[d];
					int nextY=curY+horseDy[d];
					
					// 배열 범위를 넘어가는 경우
					if (nextX<0 || nextX>=H || nextY<0 || nextY>=W) {
						continue;
					}
					
					// 이미 방문한 경우
					if (visited[chance+1][nextX][nextY]) continue;
					
					// 장애물이 있는 경우
					if (board[nextX][nextY]==1) continue;
					
					visited[chance+1][nextX][nextY]=true;
					que.add(new Status(nextX,nextY,chance+1,cnt+1));
				}
				
			}
		}
		
	}
	

}
