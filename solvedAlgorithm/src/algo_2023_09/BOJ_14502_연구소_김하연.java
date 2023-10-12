package algo_2023_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
 * BOJ 14502: 연구소
 * 새로 세울 수 있는 벽의 개수는 3개를 세워야 한다.
 * 벽을 3개 세운 뒤, 바이러스가 퍼질 수 없는 곳은 안전 영역이다.
 * 연구소의 지도가 주어졌을 때 얻을 수 있는 안전 영역 크기의 최댓값을 구하는 프로그램
 * 
 * 지도의 정보가 0인 곳은 벽을 새롭게 세울 수 있는 곳이므로 이 위치들만 따로 list에 저장한다.
 * list의 인덱스로 조합을 구한다.
 * 3개의 벽을 선택했을 때, 바이러스를 퍼뜨린다.
 *  
 * 
 */

public class BOJ_14502_연구소_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N,M;					// 지도의 세로 크기 N. 가로 크기 M
	static int[][] map;				// 지도의 정보를 저장한다.
	static List<Pos> wallList;		// 벽 후보가 될 수 있는 위치를 저장한다.
	static List<Pos> virusList;		// 바이러스의 위치를 저장한다.
	
	static int zeroCnt;
	static int maxSafeZone;			// 최대 안전 영역 크기
	
	static int[] dx= {-1,0,1,0};	// 북, 동, 남, 서
	static int[] dy= {0,1,0,-1};
	static boolean[][] visited;
	
	static class Pos{
		int x;
		int y;
		
		Pos(){}
		
		Pos(int x,int y){
			this.x=x;
			this.y=y;
		}
	}
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		// 지도의 크기를 입력받는다.
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		// 지도의 정보를 입력받는다.
		map=new int[N][M];
		wallList=new ArrayList<>();
		virusList=new ArrayList<>();
		
		zeroCnt=0;
		for (int row=0;row<N;row++) {
			st=new StringTokenizer(br.readLine().trim());
			for (int col=0;col<M;col++) {
				map[row][col]=Integer.parseInt(st.nextToken());
				// 빈 칸인 경우
				// 벽을 세울 수 있다.
				if (map[row][col]==0) {
					zeroCnt+=1;
					wallList.add(new Pos(row,col));
				}
				// 바이러스인 경우
				else if(map[row][col]==2) {
					virusList.add(new Pos(row,col));
				}
			}
		}
		dfs(0,0);
		System.out.println(maxSafeZone);
	}
	
	// 벽을 세울 위치의 조합을 구한다.
	// 인덱스로 벽 3개를 조합한다.
	public static void dfs(int startIdx, int cnt) {
		
		// 벽 3개를 모두 세웠다면
		if (cnt==3) {
			visited=new boolean[N][M];
			// 바이러스가 퍼진다.
			for (int row=0;row<N;row++) {
				for (int col=0;col<M;col++) {
					if (map[row][col]==2 && !visited[row][col]) {
						virus(row,col);
					}
				}
			}
			int safeZone=0;
			// 최대 안전 영역 크기를 구한다.
			for (int row=0;row<N;row++) {
				for (int col=0;col<M;col++) {
					if (map[row][col]==0 && !visited[row][col]) {
						safeZone+=1;
					}
				}
			}
			maxSafeZone=Math.max(maxSafeZone, safeZone);
			return;
		}
		for (int idx=startIdx;idx<wallList.size();idx++) {
			// 벽을 세울 위치
			Pos pos=wallList.get(idx);
			if (map[pos.x][pos.y]==1) continue;
			// 벽을 세운다.
			map[pos.x][pos.y]=1;
		
			dfs(idx,cnt+1);
			
			// 벽을 빈 칸으로 만든다.
			map[pos.x][pos.y]=0;
		}
		
	}
	
	public static void virus(int x,int y) {
		int tmpZeroCnt=zeroCnt-3;
		
		Deque<Pos> que=new ArrayDeque<>();
		que.offer(new Pos(x,y));
		visited[x][y]=true;
		
		while (!que.isEmpty()) {
			Pos curPos=que.poll();
			
			for (int d=0;d<4;d++) {
				int nextX=curPos.x+dx[d];
				int nextY=curPos.y+dy[d];
				
				// 배열 범위 확인
				if (nextX<0 || nextX>=N || nextY<0 || nextY>=M) {
					continue;
				}
				// 이미 방문한 곳인지 확인
				if (visited[nextX][nextY]) continue;
				
				// 빈 칸인지 확인
				if (map[nextX][nextY]==0) {
					visited[nextX][nextY]=true;
					que.offer(new Pos(nextX,nextY));
					tmpZeroCnt-=1;
				}
			}
		}
		
	}

}
