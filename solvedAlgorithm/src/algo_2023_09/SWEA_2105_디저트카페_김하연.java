package algo_2023_09;

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
	
	// 방향의 우선순위 우하 -> 좌하 -> 좌상 -> 우상 
	static int[] dx= {1,1,-1,-1};	
	static int[] dy= {1,-1,-1,1};
	
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
			dessert=new boolean[101];
			maxCnt=0;
			for (int row=0;row<N;row++) {
				for(int col=0;col<N;col++) {
					// 해당 지역에서 시작
					visited[row][col]=true;
					dessert[map[row][col]]=true;
					// 방향을 정해서 시작
					// 처음은 '우하'로 시작한다.
					// 다른 방향으로 탐색할 필요가 없는 이유는 다른 곳에서 시작한 탐색한 경로에 포함되기 때문이다.
					dfs(1,row,col,row,col,0);
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
	
	// 먹은 디저트 개수, 출발 위치, 현재 방향
	public static void dfs(int cnt, int r, int c, int initR, int initC, int prevD) {
		
		// 여기 조건문을 다니까 자꾸 -1이 나옴. 여기는 미방문 구역만 처리한다. 즉, 첫 방문지는 방문이 된 상태로 들어오기 때문에 여기를 지나갈  수 없다.
//		if (r==initR && c==initC && cnt>2) {
//			maxCnt=Math.max(maxCnt, cnt);
//			return;
//			
//		}
		
		// 이동 방향 (현재 방향에서 다음 방향으로만 이동, 이전 방향 선택하지 않는다.)
		for (int dir=prevD;dir<4;dir++) {
			int nextR=r+dx[dir];
			int nextC=c+dy[dir];
			
			// 배열 범위 내에 있을 경우
			if (isRange(nextR,nextC)) {
				if (nextR==initR && nextC==initC && cnt>2) {
					maxCnt=Math.max(maxCnt, cnt);
					return;
				}
				// 방문한 여력이 없고, 이미 먹은 디저트가 아니라면 다음 dfs를 수행
				if (!visited[nextR][nextC] && !dessert[map[nextR][nextC]]) {
					visited[nextR][nextC]=true;
					dessert[map[nextR][nextC]]=true;
					dfs(cnt+1,nextR,nextC,initR,initC,dir);
					visited[nextR][nextC]=false;
					dessert[map[nextR][nextC]]=false;
				}
			}
		}
	}
	public static boolean isRange(int x,int y) {
		if (x<0 || x>=N || y<0 || y>=N) {
			return false;
		}
		return true;
	}
}
