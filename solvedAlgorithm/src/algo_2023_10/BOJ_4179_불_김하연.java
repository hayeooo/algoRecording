package algo_2023_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BOJ 4179: 불!
 * << 문제  >>
 * 지훈이를 미로에서 탈출하도록 도와주자!
 * 미로에서의 지훈이의 위치와 불이 붙은 위치를 감안해서 지훈이가 불에 타기 전에 탈출할 수 있는지의 여부, 그리고 얼마나 빨리 탈출할 수 있는지를 결정해야 한다.
 * 지훈이와 불은 매 분마다 한 칸씩 수평 또는 수직으로 이동한다.
 * 불은 각 지점에서 네 방향으로 확산된다.
 * 지훈이는 미로의 가장자리에 접한 공간에서 탈출할 수 있다.
 * 지훈이와 불은 벽이 있는 공간을 통과하지 못한다.
 * 
 * << 풀이 >>
 * 불이 지나갈 수 있는 공간에 가장 빠르게 도착하는 시간을 구한다.
 * 지훈이는 불이 도착하기 전에 빈 공간을 통과하면서 미로를 탈출한다.
 * BFS로 불이 각 칸에 이동한 최소 시간을 구한 후, 지훈이는 현재 시간과 인접한 칸에 불이 도착할 시간을 비교한 후 이동한다.
 * 불의 개수는 여러 개일 수도 있다.
 */
public class BOJ_4179_불_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int R,C;
	static char[][] map;
	static int[][] fireMap;
	static int[][] jihunMap;
	
	static int[] dx= {-1,0,1,0};		// 북, 동, 남, 서
	static int[] dy= {0,1,0,-1};
	
	static int resultTime=-1;			// 지훈이가 탈출한 시간
	
	static class Pos{
		int x;
		int y;
		
		Pos(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		// 지도의 크기와 지도 정보를 입력 받는다.
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		
		map=new char[R][C];
		fireMap=new int[R][C];
		jihunMap=new int[R][C];
		
		// 입력 받으면서 불의 위치를 queue에 넣는다.
		Queue<Pos> fireQue=new ArrayDeque<>();
		Pos jihunPos=null;
		
		for (int row=0;row<R;row++) {
			String line=br.readLine().trim();
			// 모든 칸을 미방문 처리
			Arrays.fill(fireMap[row],-1);
			Arrays.fill(jihunMap[row],-1);
			for (int col=0;col<C;col++) {
				map[row][col]=line.charAt(col);
				if (map[row][col]=='J') {
					jihunPos=new Pos(row,col);
					// map[row][col]='.';
				}
				if (map[row][col]=='F') {
					fireQue.add(new Pos(row,col));
					fireMap[row][col]=0;
				}
			}
		}
		
		// 불을 퍼뜨린다.
		while (!fireQue.isEmpty()) {
			Pos curPos=fireQue.poll();
			
			// 4방향 탐색을 한다.
			for (int d=0;d<dx.length;d++) {
				int nextX=curPos.x+dx[d];
				int nextY=curPos.y+dy[d];
				
				// 배열 범위 확인
				if (!isRange(nextX,nextY)) continue;
				
				// 벽이 아니고 미방문 칸이라면 불이 확산된다.
				if (map[nextX][nextY]!='#' && fireMap[nextX][nextY]==-1) {
					// 방문 처리를 하고 que에 넣는다.
					fireMap[nextX][nextY]=fireMap[curPos.x][curPos.y]+1;
					fireQue.add(new Pos(nextX,nextY));
				}
					
			}
				
		}
		
		// 지훈이가 미로를 탈출한다.
		Queue<Pos> jihunQue=new ArrayDeque<>();
		jihunMap[jihunPos.x][jihunPos.y]=0;
		jihunQue.add(jihunPos);
		
		while (!jihunQue.isEmpty()) {
			Pos curPos=jihunQue.poll();
			
			// 현재 위치가 가장자리에 접한 공간이라면
			if (isOutline(curPos.x,curPos.y)) {
				resultTime=jihunMap[curPos.x][curPos.y]+1;
				break;
			}
			// 4방 탐색을 한다.
			for (int d=0;d<dx.length;d++) {
				int nextX=curPos.x+dx[d];
				int nextY=curPos.y+dy[d];
				
				// 배열 범위 확인
				if(!isRange(nextX,nextY)) continue;
				
				// 벽이거나 불이 이미 먼저 도착하는 경우(** 불의 방문 여부도 확인해야 한다. **)
				if (map[nextX][nextY]=='#' || (fireMap[nextX][nextY]>-1 && fireMap[nextX][nextY]<=jihunMap[curPos.x][curPos.y]+1)) {
					continue;
				}
				// 지훈이가 이미 방문한 곳인 경우
				if (jihunMap[nextX][nextY]>-1) continue;
				
				// 방문 표시를 하고 que에 넣는다.
				jihunMap[nextX][nextY]=jihunMap[curPos.x][curPos.y]+1;
				jihunQue.add(new Pos(nextX,nextY));
			}
		}
		System.out.println(resultTime==-1?"IMPOSSIBLE":resultTime);
	}
	
	public static boolean isOutline(int x,int y) {
		return x==0 || y==0 || x==R-1 || y==C-1;
	}
	public static boolean isRange(int x,int y) {
		return x>=0 && x<R && y>=0 && y<C;
	}
}
