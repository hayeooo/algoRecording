package algo_2023_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BON 3055: 탈출
 * 
 * <<문제>>
 * 고슴도치는 제일 친한 친구인 비버의 굴로 가능한 빨리 도망가 홍수를 피하려고 한다.
 * 지도는 R행과 C열로 이루어져 있다. 
 * 비어있는 곳은 '.', 물이 차있는 지역은 '*', 돌은 'X', 비버의 굴은 'D', 고슴도치의 위치는 'S'로 나타내어져 있다.
 * 매 분마다 고슴도치는 현재 있는 칸과 인접한 네 칸 중 하나로 이동할 수 있다. 물도 매 분마다 비어있는 칸으로 확장한다.
 * <<제약 사항>>
 * 물과 고슴도치는 돌을 통과할 수 없다.
 * 고슴도치는 물로 차있는 구역으로 이동할 수 없고, 물도 비버의 소굴로 이동할 수 없다.
 * 고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다.
 * <<결과>>
 * 고슴도치가 안전하게 비버의 굴로 이동하기 위해 필요한 최소 시간을 구하여라.
 * 안전하게 비버의 굴로 이동할 수 없다면 "KAKTUS"를 출력한다.
 * 
 * <<풀이방법>>
 * 1. 입력 받은 지도에서 물을 확장시킨다. 각 칸에 물이 도착할 시간을 예측할 수 있다.
 * 2. 물이 도착할 시간을 알고 있으므로, 고슴도치는 다음 이동할 칸의 물 도착 시간이 현재 고슴도치 시간과 같거나 작으면 이동할 수 없다.
 * 3. 물을 피해서 비버의 굴에 도착한다면 시간을 출력하고 도착할 수 없다면 KAKTUS를 출력한다.
 * 
 * @author HAYEON
 *
 */

public class BOJ_3055_탈출_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int R,C;				// 지도의 행, 열 정보
	static char[][] map;		// 지도 정보
	static int[][] water;		// 물 도착 예정 시간을 저장하는 배열
	static int[][] dochi;	// 고슴도치의 방문 여부를 저장하는 배열
	static Pos dochiPos;		// 고슴도치 출발 위치
	static Pos destPos;				// 굴의 위치
	
	static int[] dx= {-1,0,1,0};	// 북, 동, 남, 서
	static int[] dy= {0,1,0,-1};
	
	static class Pos{
		int r;
		int c;
		
		Pos(){}
		
		Pos(int r,int c){
			this.r=r;
			this.c=c;
		}
	}
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 지도의 행, 열 정보를 입력 받는다.
		st=new StringTokenizer(br.readLine().trim());
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		
		// 지도 정보를 입력받는다.
		map=new char[R][C];
		for (int row=0;row<R;row++) {
			String line=br.readLine().trim();
			for (int col=0;col<C;col++) {
				map[row][col]=line.charAt(col);
				if (map[row][col]=='S') {
					dochiPos=new Pos(row,col);
				}
				else if(map[row][col]=='D') {
					destPos=new Pos(row,col);
				}
			}
		}
		
		water=new int[R][C];
		dochi=new int[R][C];
		
		for (int row=0;row<R;row++) {
			Arrays.fill(water[row], -1);
			Arrays.fill(dochi[row], -1);
		}
		// 물이 있는 위치에서 시작한다.
		for (int row=0;row<R;row++) {
			for (int col=0;col<C;col++) {
				// 물인 경우
				if (map[row][col]=='*') {
					expandWater(row,col);
				}
			}
		}
		// 고슴도치가 이동한다.
		moveDochi(dochiPos.r,dochiPos.c);
		
		// 결과를 출력한다.
		System.out.println(dochi[destPos.r][destPos.c]==-1?"KAKTUS":dochi[destPos.r][destPos.c]);
	}
	
	public static void expandWater(int row,int col) {
		Queue<Pos> que=new ArrayDeque<>();
		que.offer(new Pos(row,col));
		water[row][col]=0;
		
		while (!que.isEmpty()) {
			Pos curPos=que.poll();
			
			// 4방 탐색
			for (int d=0;d<4;d++) {
				int nextRow=curPos.r+dx[d];
				int nextCol=curPos.c+dy[d];
				
				// 지도의 범위를 벗어난 경우
				if (!isRange(nextRow,nextCol)) {
					continue;
				}
				// 이미 방문한 곳인 경우
				if (water[nextRow][nextCol]>-1) {
					continue;
				}
				// 돌 또는 비버의 굴인 경우
				if (map[nextRow][nextCol]=='X' || map[nextRow][nextCol]=='D') {
					continue;
				}
				if (map[nextRow][nextCol]=='.') {
					que.offer(new Pos(nextRow,nextCol));
					water[nextRow][nextCol]=water[curPos.r][curPos.c]+1;
				}
				
				
			}
		}
		
	}
	
	public static void moveDochi(int r,int c) {
		Queue<Pos> que=new ArrayDeque<>();
		que.offer(new Pos(r,c));
		dochi[r][c]=0;
		
		while (!que.isEmpty()) {
			
			Pos curPos=que.poll();
			
			// 비버의 굴에 도착한 경우 break
			if (curPos.r==destPos.r && curPos.c==destPos.c) {
				break;
			}
			
			for (int d=0;d<4;d++) {
				int nextRow=curPos.r+dx[d];
				int nextCol=curPos.c+dy[d];
				
				// 배열의 범위를 넘어간 경우
				if (!isRange(nextRow,nextCol)) {
					continue;
				}
				// 이미 방문한 곳인 경우
				if (dochi[nextRow][nextCol]>-1) {
					continue;
				}
				// 물이나 돌인 경우
				if (map[nextRow][nextCol]=='*' || map[nextRow][nextCol]=='X') {
					continue;
				}
				// 나머지는 빈칸
				if (water[nextRow][nextCol]==-1) {
					que.offer(new Pos(nextRow,nextCol));
					dochi[nextRow][nextCol]=dochi[curPos.r][curPos.c]+1;
					continue;
				}
				// 굴이거나 물보다 더 빠른 시간인 경우
				if (dochi[curPos.r][curPos.c]+1<water[nextRow][nextCol]) {
					que.offer(new Pos(nextRow,nextCol));
					dochi[nextRow][nextCol]=dochi[curPos.r][curPos.c]+1;
				}
			}
		}
		
	}
	
	public static boolean isRange(int row, int col) {
		return row>=0 && row<R && col>=0 && col<C;
	}
	

}
