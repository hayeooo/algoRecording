package algo_2023_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 17070: 파이프 옮기기1
 * 파이프를 밀어서 벽을 긁지 않고 이동시키려고 한다.
 * 파이프를 밀 수 있는 방향은 총 3가지가 있으며, →, ↘, ↓ 방향이다. 회전은 45도만 회전시킬 수 있다.
 * 가로, 세로로 놓인 경우 이동 방법은 2 가지, 대각선 방향으로 놓여진 경우 3가지이다.
 * 
 * (0,0) 위치에서 가로로 시작하여 파이프 한쪽 끝을 (N-1, N-1)로 이동시키는 방법의 개수를 구한다.
 * 
 * 완전 탐색
 * 기준은 파이프 한쪽 끝을 기준으로 한다.
 * 이동하려는 방향에 벽이 있는지 확인한다.
 * 벽이 없을 경우, 이동할 수 있는 모든 방향에 대해 탐색을 한다.
 * 파이프 한쪽 끝이 (N-1,N-1)에 위치하였을 때, 이동시키는 방법에 1을 더한다.
 */
public class BOJ_17070_파이프옮기기1_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static final int horizon=0;			// 파이프의 방향
	static final int vertical=1;
	static final int diagonal=2;
	
	static int houseSize;				// 집의 크기
	static int[][] house;				// 집의 상태를 담은 배열
	
	static int wayToMove;				// 이동시키는 방법의 개수
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 집의 크기를 입력 받는다.
		houseSize=Integer.parseInt(br.readLine().trim());
		
		// 집 상태를 입력 받는다.
		house=new int[houseSize][houseSize];
		for (int row=0;row<houseSize;row++) {
			st=new StringTokenizer(br.readLine().trim());
			for (int col=0;col<houseSize;col++) {
				house[row][col]=Integer.parseInt(st.nextToken());
			}
		}
		
		wayToMove=0;
		dfs(0,1,horizon);
		System.out.println(wayToMove);
		
	}
	// 헤드 위치를 기준으로 가능한 모든 방향으로 이동시킨다.
	// 벽과 배열 범위를 유의해야 한다.
	public static void dfs(int x,int y, int d) {
		if (x==houseSize-1 && y==houseSize-1) {
			wayToMove+=1;
			return;
		}
		
		// 파이프를 이동시킨다.
		
		// 현재 방향이 가로인 경우
		// 1. 가로, 2. 대각선으로 이동할 수 있다.
		if (d==horizon) {
			// 가로
			if (y+1<houseSize && house[x][y+1]==0) {
				dfs(x,y+1,horizon);
			}
			
			// 대각선
			if (x+1<houseSize && y+1<houseSize && house[x+1][y]==0 && house[x][y+1]==0 && house[x+1][y+1]==0) {
				dfs(x+1,y+1,diagonal);
			}
			
		}
		
		// 현재 방향이 세로인 경우
		// 1. 세로, 2. 대각선으로 이동할 수 있다.
		else if(d==vertical) {
			// 세로
			if (x+1<houseSize && house[x+1][y]==0) {
				dfs(x+1,y,vertical);
			}
			// 대각선
			if (x+1<houseSize && y+1<houseSize && house[x+1][y]==0 && house[x][y+1]==0 && house[x+1][y+1]==0) {
				dfs(x+1,y+1,diagonal);
			}
		}
		
		// 현재 방향이 대각선인 경우
		// 1. 가로, 2. 세로, 3. 대각선으로 이동할 수 있다.
		else if(d==diagonal) {
			// 가로
			if (y+1<houseSize && house[x][y+1]==0) {
				dfs(x,y+1,horizon);
			}
			// 세로
			if (x+1<houseSize && house[x+1][y]==0) {
				dfs(x+1,y,vertical);
			}
			// 대각선
			if (x+1<houseSize && y+1<houseSize && house[x+1][y]==0 && house[x][y+1]==0 && house[x+1][y+1]==0) {
				dfs(x+1,y+1,diagonal);
			}
		}
	}
	

}
