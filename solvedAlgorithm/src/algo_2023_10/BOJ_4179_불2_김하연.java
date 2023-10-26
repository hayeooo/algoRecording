package algo_2023_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BOJ 4179: 불2
 * << 문제 >>
 * 미로에서의 지훈이의 위치와 불이 붙은 위치를 감안해서 지훈이가 불에 타기 전에 탈출할 수 있는지의 여부, 얼마나 빨리 탈출할 수 있는지를 결정해야 한다.
 * 지훈이와 불은 매 분마다 한 칸씩 수평 또는 수직으로 이동한다.
 * 불은 각 지점에서 네 방향으로 확산된다.
 * 지훈이는 미로의 가장자리에 접한 공간에서 탈출할 수 있다.
 * 지훈이와 불은 벽이 있는 공간을 통과하지 못한다.
 * 지훈이가 불이 도달하기 전에 미로를 탈출할 수 없는 경우 IMPOSSIBLE을 출력한다.
 * 지훈이가 미로를 탈출할 수 있는 경우에는 가장 빠른 탈출시간을 출력한다.
 * 
 * << 풀이 >>
 * 저번 풀이는 불, 지훈이가 도달하는 위치를 각각 BFS로 돌렸다.
 * 하지만, 동시에 할 수 없을까 생각이 들어 다시 풀어본다.
 * Queue에 불과 지훈이가 동시에 들어가야 하기 때문에 구별해야 한다.
 * **방문 표시도 지훈이와 불을 구분해야 한다. 이유: 지훈이가 방문한 곳에는 불이 이동할 수 있기 때문이다.
 * 처음 주어진 queue에 불이 먼저 들어가고 나중에 지훈이가 들어가야 한다.
 * 
 */
public class BOJ_4179_불2_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static int R,C;
	
	static char[][] map;
	static Pos jihunLoc;
	static Queue<Pos> que;			// bfs를 위해 사용하는 자료구조
	static boolean[][] visited;		// 불 또는 지훈이의 방문 여부를 저장하는 배열
	
	static int[] dx= {-1,0,1,0};	// 북, 동, 남, 서
	static int[] dy= {0,1,0,-1};
	
	static final int FIRE=0;
	static final int JIHUN=1;
	
	static int resultTime=-1;
	static class Pos{
		int r;
		int c;
		int type;			// 지훈, 불
		int time;
		
		public Pos(int r, int c, int type, int time) {
			super();
			this.r = r;
			this.c = c;
			this.type = type;
			this.time = time;
		}
		
	}
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 지도의 행의 개수와 열의 개수를 구한다.
		st=new StringTokenizer(br.readLine().trim());
		
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		map=new char[R][C];
		visited=new boolean[R][C];
		
		que=new LinkedList<>();
		// 지도의 정보를 입력받는다.
		for (int row=0;row<R;row++) {
			String line=br.readLine().trim();
			for (int col=0;col<C;col++) {
				map[row][col]=line.charAt(col);
				// 지훈이의 위치일 경우
				if (map[row][col]=='J') {
					// 처음 위치가 가장자리인 경우
					if (isOutline(row,col)) {
						resultTime=1;
						break;
					}
					jihunLoc=new Pos(row,col,JIHUN,0);
					visited[row][col]=true;
					map[row][col]='.';
				}
				// 불 위치인 경우
				else if (map[row][col]=='F') {
					que.add(new Pos(row,col,FIRE,0));
				}
			}
		}
		if (resultTime>-1) {
			System.out.println(resultTime);
			return;
		}
		
		// bfs를 수행한다.
		bfs();
		System.out.println(resultTime==-1?"IMPOSSIBLE":resultTime);
		
	}
	public static boolean isOutline(int row,int col) {
		return row==0 || row==R-1 || col==0 || col==C-1;
	}
	public static boolean isRange(int row,int col) {
		return row>=0 && row<R && col>=0 && col<C;
	}

	public static void bfs() {
		// 불의 위치를 queue에 저장했으니 지훈이의 위치를 저장한다.
		que.add(jihunLoc);
		
		while (!que.isEmpty()) {
			
			Pos pos=que.poll();
			
			// 지훈이가 가장자리에 도착했을 경우
			if (pos.type==JIHUN && isOutline(pos.r,pos.c)) {
				resultTime=pos.time+1;
				break;
			}
			
			// 그 외에 4방 탐색
			for (int d=0;d<dx.length;d++) {
				int nextR=pos.r+dx[d];
				int nextC=pos.c+dy[d];
				
				// 범위에서 벗어난 경우
				if (!isRange(nextR,nextC)) continue;
				
				// 벽인 경우 또는 이미 불인 경우
				if (map[nextR][nextC]=='#' || map[nextR][nextC]=='F') continue;
				
				// 그 외에는 확산 가능하다.
				// 지훈이 경우
				if (pos.type==JIHUN) {
					if (!visited[nextR][nextC]) {
						que.add(new Pos(nextR,nextC,pos.type,pos.time+1));
						visited[nextR][nextC]=true;	
					}
				}
				// 불인 경우
				else if(pos.type==FIRE) {
					que.add(new Pos(nextR,nextC,pos.type,pos.time+1));
					map[nextR][nextC]='F';
				}
			}
		}
		
	}
}
