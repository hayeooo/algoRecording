package algo_2023_09_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * SWEA 2105: 디저트 카페
 * 한 변의 길이가 N인 정사각형 모양을 가진 지역에 디저트 카페가 모여 있다.
 * 디저트 카페 투어는 어느 한 카페에서 출발하여 
 * 1. 대각선 방향으로 움직이고 
 * 2. 사각형 모양을 그리며 출발한 카페로 돌아와야 한다.
 * 카페 투어 중에 같은 숫자의 디저트를 팔고 있는 카페가 있으면 안된다.
 * 
 * 친구들과 디저트를 되도록 많이 먹으려고 한다.
 * 임의의 한 카페에서 출발하여 대각선 방향으로 움직이고
 * 서로 다른 디저트를 먹으면서 사각형 모양을 그리며 다시 출발점으로 돌아오는 경우
 * 디저트를 가장 많이 먹을 수 있는 경로를 찾고, 그 때의 디저트 수를 정답으로 출력하는 프로그램을 작성하라.
 * 만약, 디저트를 먹을 수 없는 경우 -1을 출력한다.
 */
public class SWEA_2105_디저트카페_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T;					// 총 테스트 케이스 개수
	static int N;					// 지역의 한 변의 길이
	static int[][] map;				// 지역의 디저트 종류 정보를 저장하는 배열
	static boolean[][] visited;		// 해당 지역의 방문 여부를 표시하는 배열
	static boolean[] dessert;		// 디저트 중복 여부를 저장하는 배열
	static int maxCnt;				// 먹을 수 있는 최대 디저트 수
	
	static int[] dx= {-1,1,1,-1};	// 북동, 남동, 남서, 북서
	static int[] dy= {1,1,-1,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		T=Integer.parseInt(br.readLine().trim());
		for (int test_case=1;test_case<=T;test_case++) {
			// 지역의 한 변의 길이를 입력받는다.
			N=Integer.parseInt(br.readLine().trim());
			
			map=new int[N][N];
			// 디저트 종류 정보를 입력받는다.
			for (int row=0;row<N;row++) {
				st=new StringTokenizer(br.readLine().trim());
				for (int col=0;col<N;col++) {
					map[row][col]=Integer.parseInt(st.nextToken());
				}
			}
			visited=new boolean[N][N];
			dessert=new boolean[N];
			maxCnt=0;
			for (int row=0;row<N;row++) {
				for(int col=0;col<N;col++) {
					// 해당 지역에서 시작
					visited[row][col]=true;
					dessert[map[row][col]]=true;
					// 방향을 정해서 시작
					for (int d=0;d<dx.length;d++) {
						dfs(row,col,d,1,row,col,1);
					}
					visited[row][col]=false;
					dessert[map[row][col]]=false;
				}
			}
			// maxCnt가 1이라면 디저트를 먹을 수 없는 경우이다.
			if (maxCnt<=1) {
				maxCnt=-1;
			}
			sb.append("#").append(test_case).append(" ").append(maxCnt).append("\n");
		}
		System.out.println(sb);
	}
	
	// 출발 위치, 현재 방향, 방향 전환 횟수
	public static void dfs(int cx,int cy, int d, int convertCnt, int startX, int startY, int cnt) {
		
		// 출발지로 되돌아오면 return
		if (convertCnt==4 && cx==startX && cy==startY) {
			maxCnt=Math.max(maxCnt, cnt);
			return;
		}
		// 방향 전환 횟수가 남아있다면, 방향을 전환할 수 있다.
		int nextX;
		int nextY;
		if (convertCnt<4) {
			int nextD=(d+1)%4;
			nextX=cx+dx[nextD];
			nextY=cy+dy[nextD];
			
			// 배열 범위, 이미 방문한 곳인지 확인, 디저트 중복 확인
			if (isRange(nextX,nextY) && !visited[nextX][nextY] && !dessert[map[nextX][nextY]]) {
				visited[nextX][nextY]=true;
				dessert[map[nextX][nextY]]=true;
				dfs(nextX,nextY,nextD,convertCnt+1,startX,startY,cnt+1);
				dessert[map[nextX][nextY]]=false;
				visited[nextX][nextY]=false;
			}
		}
		// 현 방향을 계속 유지한 채로 dfs를 수행한다.
		nextX=cx+dx[d];
		nextY=cy+dy[d];
		if (isRange(nextX,nextY) && !visited[nextX][nextY] && !dessert[map[nextX][nextY]]) {
			visited[nextX][nextY]=true;
			dessert[map[nextX][nextY]]=true;
			dfs(nextX,nextY,d,convertCnt,startX,startY,cnt+1);
			dessert[map[nextX][nextY]]=false;
			visited[nextX][nextY]=false;
		}
		
	}
	public static boolean isRange(int x,int y) {
		if (x<0 || x>=N || y<0 || y>=N) {
			return false;
		}
		return true;
	}
}
