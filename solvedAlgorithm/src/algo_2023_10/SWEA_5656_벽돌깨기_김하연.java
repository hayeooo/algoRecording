package algo_2023_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * SWEA 5656: 벽돌깨기
 * 구슬은 N번만 쏠 수 있고, 벽돌들의 정보는 아래와 같이 W*H 배열로 주어진다.
 * 0은 빈 공간을 의미하며, 그 외 숫자는 벽돌을 의미한다.
 * 
 * 게임 규칙
 * 1. 구슬은 좌, 우로만 움직일 수 있어서 항상 맨 위에 있는 벽돌만 깨트릴 수 있다.
 * 2. 벽돌은 숫자 1~9로 표현되며, 구슬이 명중한 벽돌은 상하좌우로 (벽돌에 적힌 숫자1)칸만큼 같이 제거된다.
 * 3. 제거되는 범위 내에 있는 벽돌은 동시에 제거된다.
 * 
 * N 개의 벽돌을 떨어트려 최대한 많은 벽돌을 제거하려고 한다.
 * N, W, H 그리고 벽돌들의 정보가 주어질 때, 남은 벽돌의 개수를 구하라!
 * 
 * 1. 최대한 많은 벽돌을 깨트리기 위해 가능한 모든 경우를 시도해본다.(중복 순열)
 * 2. 주어진 벽돌을 깨뜨린 경우, 제거되는 범위에 들어간 벽돌을 표시하고 제거한다.
 * 3. 남은 벽돌을 떨어트린다.
 * 4. 각 조합을 수행한 경우 남은 벽돌의 최솟값을 업데이트 한다.
 * 
 */
public class SWEA_5656_벽돌깨기_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T;			// 테스트케이스 개수
	static int N,W,H;		// 구슬을 쏠 수 있는 횟수, 가로(W), 세로(H)
	static int[][] map;
	static int[][] tmpMap;
	static boolean[][] isRemoved;	// 제거될 벽돌을 표시하는 배열
	static int[] comb;
	static int[] dx= {-1,0,1,0};	// 북, 동, 남, 서
	static int[] dy= {0,1,0,-1};
	
	static int minBrick;
	
	static class Pos{
		int r;
		int c;
		
		Pos(){}
		
		Pos(int r, int c){
			this.r=r;
			this.c=c;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		// 테스트케이스 개수를 입력받는다.
		T=Integer.parseInt(br.readLine().trim());
		
		for (int test_case=1;test_case<=T;test_case++) {
			st=new StringTokenizer(br.readLine().trim());
			// 구슬을 쏠 수 있는 횟수, 지도의 가로, 세로를 입력받는다.
			N=Integer.parseInt(st.nextToken());
			W=Integer.parseInt(st.nextToken());
			H=Integer.parseInt(st.nextToken());
			
			// 지도의 정보를 입력받는다.
			map=new int[H][W];
			for (int row=0;row<H;row++) {
				st=new StringTokenizer(br.readLine().trim());
				for (int col=0;col<W;col++) {
					map[row][col]=Integer.parseInt(st.nextToken());
				}
			}
			minBrick=Integer.MAX_VALUE;
			comb=new int[N];
			dupPerm(0);
			sb.append("#").append(test_case).append(" ").append(minBrick).append("\n");
		}
		System.out.print(sb);
	}
	
	// 가능한 모든 조합
	public static void dupPerm(int cnt) {
		// 구슬이 깰 열의 조합을 모두 구한 경우
		if (cnt==N) {
			copyMap();
			
			// 순서대로 벽돌을 깨뜨린다.
			for (int col:comb) {
				removeBrick(col);
			}
			
			// 남은 벽돌의 개수를 세고 최솟값을 없데이트 한다.
			int brickCnt=0;
			for (int row=0;row<H;row++) {
				for (int col=0;col<W;col++) {
					if (tmpMap[row][col]!=0) {
						brickCnt+=1;
					}
				}
			}
			minBrick=Math.min(minBrick, brickCnt);
			return;
		}
		// 다음 차례에 깰 열을 고른다.
		for (int idx=0;idx<W;idx++) {
			comb[cnt]=idx;
			dupPerm(cnt+1);
		}
	}
	
	// 맵 복사
	public static void copyMap(){
		tmpMap=new int[H][W];
		for (int row=0;row<H;row++) {
			for (int col=0;col<W;col++) {
				tmpMap[row][col]=map[row][col];
			}
		}
	}
	
	// 해당 열 벽돌 제거
	public static void removeBrick(int col) {
		int top=-1;
		// 해당 열에서 가장 높은 위치를 알아낸다.
		for (int row=0;row<H;row++) {
			if(tmpMap[row][col]!=0) {
				top=row;
				break;
			}
		}
		// 해당 열에서 벽돌을 깰 수 없는 경우
		if (top==-1) {
			return;
		}
		
		// BFS로 없어질 수 있는 모든 벽돌을 구한다.
		isRemoved=new boolean[H][W];
		Queue<Pos> que=new ArrayDeque<>();
		que.offer(new Pos(top,col));
		isRemoved[top][col]=true;
		
		while (!que.isEmpty()) {
			Pos curPos=que.poll();
			
			for (int d=0;d<4;d++) {
				int dCnt=1;
				// 가능한 모든 벽돌 que에 집어넣기
				while (true) {
					int nextR=curPos.r+dCnt*dx[d];
					int nextC=curPos.c+dCnt*dy[d];
					
					// 깰 수 있는 벽돌의 범위
					if (dCnt>tmpMap[curPos.r][curPos.c]-1) {
						break;
					}
					dCnt++;
					// 배열 범위에서 벗어날 경우 break;
					if (!isRange(nextR,nextC)) break;
					
					// 이미 제거될 벽돌인 경우 
					if (isRemoved[nextR][nextC]) continue;
					
					// 배열 범위에서 벗어나지 않고 깰 수 있는 벽돌 범위 내에 있는 경우
					// 방문 표시와 que에 추가
					if (tmpMap[nextR][nextC]!=0) {
						isRemoved[nextR][nextC]=true;
						que.offer(new Pos(nextR,nextC));						
					}
					
				}
			}
		}
		// 해당 영역에 true인 벽돌들을 0으로 표시한다.
		for (int r=0;r<H;r++) {
			for (int c=0;c<W;c++) {
				if (isRemoved[r][c]) {
					tmpMap[r][c]=0;
				}
			}
		}
		
		// 벽돌을 떨어뜨린다.
		downBrick();
		
	}
	
	// 벽돌을 아래로 떨어뜨린다.
	public static void downBrick() {
		// tmpMap 바꾸기
		// 맨 아래 row에서부터 올라가면서 0을 발견한 경우 Queue에 넣고 0으로 바꾸기
		// 0인 곳을 발견하면 queue에서 뽑아서 넣기
		Queue<Integer> que=new ArrayDeque<>();
		
		for (int col=0;col<W;col++) {
			// 벽돌인 경우
			for (int row=H-1;row>=0;row--) {
				if (tmpMap[row][col]!=0) {
					que.offer(tmpMap[row][col]);
					tmpMap[row][col]=0;
				}
			}
			// 0인 칸에 벽돌 채워넣기
			for (int row=H-1;row>=0;row--) {
				if (que.isEmpty()) {
					tmpMap[row][col]=0;
				}
				else {
					tmpMap[row][col]=que.poll();
				}
			}
		}
		
		
	}
	public static boolean isRange(int r, int c) {
		return r>=0 && r<H && c>=0 && c<W;
	}
	
}
