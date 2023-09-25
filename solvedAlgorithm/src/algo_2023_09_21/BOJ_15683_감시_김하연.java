package algo_2023_09_21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * BOJ 15683: 감시
 * 사무실의 세로 크기 N과 가로 크기 M이 주어진다.
 * 사무실 각 칸의 정보가 주어진다. 0은 빈 칸, 6은 벽, 1~5는 CCTV를 나타낸다.
 * CCTV의 최대 개수는 8개를 넘지 않는다.
 * CCTV 번호에 따라 탐색할 수 있는 방향이 다르고 항상 90도 방향으로 회전해야 하며, 방향이 가로 또는 세로 방향이다.
 * 
 * CCTV 번호에 따라 탐색할 수 있는 방향과 개수를 배열에 저장한다.
 * Java 배열 초기화: 내부 요소 개수가 달라도 사용할 수 있다.
 * 배열 이름은 레퍼런스 변수로 각 배열에 대한 참조값을 저장한다.
 * cctv : cctv[0] 참조값을 저장
 * cctv[0]: cctv[0][0] 참조값 저장
 * cctv[1]: cctv[1][0] 참조값 저장 ...
 */
class CCTVInfo{
	int x;
	int y;
	int type;
	
	CCTVInfo(int x,int y,int type){
		this.x=x;
		this.y=y;
	}
}
public class BOJ_15683_감시_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N,M;				// 지도의 세로 크기와 가로 크기
	static int[][] map;			// 사무실 각 칸의 정보를 저장하는 배열
	static boolean[][] visited;	// CCTV가 감시한 곳을 저장하는 배열
	
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,1,0,-1};
	
	// 0: 북, 1: 동, 2: 남, 3: 서
	static int[][][] cctv= {
			{{}},
			{{0},{1},{2},{3}},
			{{1,3},{0,2}},
			{{0,1},{1,2},{2,3},{3,0}},
			{{0,1,3},{0,1,2},{1,2,3},{2,3,0}},
			{{0,1,2,3}}
	};
	
	static List<CCTVInfo> cctvs;			// cctv위치를 저장해놓은 리스트
	static int minCnt;
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		// 사무실의 세로 크기 N과 가로 크기 M을 입력받는다.
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		map=new int[N][M];
		cctvs=new ArrayList<CCTVInfo>();
		minCnt=0;
		
		for (int row=0;row<N;row++) {
			st=new StringTokenizer(br.readLine().trim());
			
			for (int col=0;col<M;col++) {
				map[row][col]=Integer.parseInt(st.nextToken());
				
				if (map[row][col]>=1 && map[row][col]<=5) {
					cctvs.add(new CCTVInfo(row,col,map[row][col]));
				}
				// 사각지대 초기값 만들기
				if (map[row][col]==0) {
					minCnt+=1;
				}
			}
		}
		if (cctvs.size()>0) {
			dfs(0);
		}
		
		System.out.println(minCnt);
		
	}
	
	public static void dfs(int cctvIdx) {
		
		// 모든 cctv 방향 탐색을 진행했다면
		if (cctvIdx>=cctvs.size()) {
			// 사각지대 개수 구하기
			int unwatched=getUnwatched();
			System.out.println("unwatched: "+unwatched);
			minCnt=Math.min(minCnt, unwatched);
			
			return;
		}
		CCTVInfo curCCTV=cctvs.get(cctvIdx);
		int cctvX=curCCTV.x;
		int cctvY=curCCTV.y;
		int cctvType=curCCTV.type;
		
		// 여기서 잘못됨..
		// cctv 방향 배열 크기 다시 계산하기
		for (int idx=0;idx<cctv[cctvType].length;idx++) {
			int[] eachDir=cctv[cctvType][idx];
			
			for (int curDir:eachDir) {
				System.out.println("dir: "+curDir);
				// 방향 배열 길이만큼 visited true로 표시
				watch(cctvX,cctvY,curDir,true);
				
				// 다음 cctv 방향 탐색
				dfs(cctvIdx+1);
				
				// 현 cctv가 탐색한 통로 visited false로 바꾸기
				watch(cctvX,cctvY,curDir,false);
				
			}
		}
		
	}
	
	public static void watch(int x,int y,int d,boolean value) {
		int cnt=1;
		
		while (true) {
			int nextX=x+cnt*dx[d];
			int nextY=y+cnt*dy[d];
			
			// 배열의 범위를 벗어나는 경우 break
			if (nextX<0 || nextX>=N || nextY<0 || nextY>=M) {
				break;
			}
			// 벽을 만난 경우 break
			if (map[nextX][nextY]==6) {
				break;
			}
			// visited[nextX][nextY]=value;로 설정하고
			visited[nextX][nextY]=value;
			// 다음 칸 탐색
			cnt+=1;
		}
		
	}
	// 사각지대 개수 구하기 map 숫자 값이 0이면서 visited[i][j]==false인 곳
	public static int getUnwatched() {
		int total=0;
		for (int row=0;row<N;row++) {
			for (int col=0;col<M;col++) {
				if (map[row][col]==0 && !visited[row][col]) {
					total+=1;
				}
			}
		}
		return total;
	}

}
