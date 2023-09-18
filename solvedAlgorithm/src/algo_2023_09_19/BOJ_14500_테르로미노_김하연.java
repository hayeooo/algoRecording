package algo_2023_09_19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 14500: 테르로미노
 * 정사각형 4개를 이어 붙인 폴리오미노는 테르로미노라고 하며, 5가지가 있다.
 * 크기가 N*M인 테르로미노가 놓인 칸에 쓰여 있는 수들의 합을 최대로 하는 프로그램을 작성
 * 
 * 
 * 배열의 회전과 대칭
 * 각 테르로미노의 모양을 배열 형태로 저장한 후, 3번의 회전과 대칭 후 3번의 회전으로 얻어진 도형으로
 * 테르로미노가 놓인 칸에 쓰여 있는 수들의 합을 구한다.
 * => 정사각형 모양의 테르로미노는 회전이나 대칭시키지 않아도 된다.
 * 
 * << 다른 방법 >> 
 * DFS: (ㅜ,ㅗ,ㅏ,ㅓ) 모양을 위해 2번째 위치에서 한 번 더 탐색해야 한다.
 * Map의 크기는 최대 250,000 (4<=N,M<=500)
 * 한 점에서 DFS로 최대 탐색 가능한 경우는 진행할 때마다 4방향씩 탐색하기 때문에 4^3
 * 250,000 * (4^3) < 1억
 * 
 * << 알게 된 점 >>
 * 조건문에 따라 특정 위치에서 2개의 방향으로 탐색으로 DFS를 진행할 수 있다.
 * 
 */
public class BOJ_14500_테르로미노_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N,M;				// 종이의 세로 크기 N, 가로 크기 M
	static int[][] map;			// 종이에 쓰여 있는 숫자 정보를 저장하는 배열
	static boolean[][] visited;	// 방문 여부를 확인하는 배열
	
	static int[] dx= {-1,0,1,0};	// 북, 동, 남, 서	
	static int[] dy= {0,1,0,-1};
	
	static int maxSum;			// 최대 숫자 합을 저장할 변수
	
	public static void main(String[] args) throws IOException{
		
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		// 종이의 세로 크기와 가로 크기를 구한다.
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		// 종이 정보를 입력받는다.
		map=new int[N][M];
		visited=new boolean[N][M];
		
		for (int row=0;row<N;row++) {
			st=new StringTokenizer(br.readLine().trim());
			for (int col=0;col<M;col++) {
				map[row][col]=Integer.parseInt(st.nextToken());
			}
		}
		maxSum=0;
		for (int row=0;row<N;row++) {
			for (int col=0;col<M;col++) {
				visited[row][col]=true;
				dfs(row,col,1,map[row][col]);
				visited[row][col]=false;
			}
		}
		System.out.println(maxSum);
	}
	// 현재 위치와 테르로미노를 만든 정사각형의 개수, 현재까지 숫자의 합
	public static void dfs(int row,int col,int cnt, int total) {
		
		// 정사각형이 4개라면 최대값을 갱신하고 return, 종이 위에 테르로미노가 올라가야한다는 조건
		if (cnt==4) {
			maxSum=Math.max(total, maxSum);
			return;
		}
		
		// 아직 정사각형이 4개가 아닌 경우
		// 4방향 탐색
		for (int d=0;d<dx.length;d++) {
			// 배열 범위 탐색
			int nextRow=row+dx[d];
			int nextCol=col+dy[d];
			
			if (nextRow<0 || nextRow>=N || nextCol<0 || nextCol>=M) {
				continue;
			}
			// 이미 방문한 곳인지 확인
			if (visited[nextRow][nextCol]) {
				continue;
			}
			// 2번째 정사각형인 경우 (ㅗ) 모양을 만들기 위해 이전 위치에서 탐색
			if (cnt==2) {
				visited[nextRow][nextCol]=true;
				dfs(row,col,cnt+1,total+map[nextRow][nextCol]);
				visited[nextRow][nextCol]=false;
			}
			// 다음 정사각형을 정하고 DFS 돌림.
			visited[nextRow][nextCol]=true;
			dfs(nextRow,nextCol,cnt+1,total+map[nextRow][nextCol]);
			visited[nextRow][nextCol]=false;
		}
	}

}
