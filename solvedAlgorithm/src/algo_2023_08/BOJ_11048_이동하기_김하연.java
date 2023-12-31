package algo_2023_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11048_이동하기_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N,M;				// 미로의 크기
	
	static int[][] maze;
	static int[][] candyCnt;
	
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 미로의 크기 N, M을 입력받는다.
		st=new StringTokenizer(br.readLine().trim());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		maze=new int[N][M];
		candyCnt=new int[N][M];
		
		// 미로에 놓인 사탕의 수를 입력받는다.
		for (int row=0;row<N;row++) {
			st=new StringTokenizer(br.readLine().trim());
			for(int col=0;col<M;col++) {
				maze[row][col]=Integer.parseInt(st.nextToken());
			}
		}
		//candyCnt[0][0]=maze[0][0];
		
		// 출발지에서 각 칸에서 최대로 얻을 수 있는 사탕의 수를 구한다.
		// 대각선으로 바로 이동하는 것보다 아래->오른쪽으로 이동하는 것이 더 많은 사탕을 얻을 수 있다.
		for (int row=0;row<N;row++) {
			for (int col=0;col<M;col++) {
				candyCnt[row][col]=maze[row][col];
				
				if (row-1>=0) {
					candyCnt[row][col]=Math.max(candyCnt[row-1][col]+maze[row][col], candyCnt[row][col]);
				}
				if (col-1>=0) {
					candyCnt[row][col]=Math.max(candyCnt[row][col-1]+maze[row][col], candyCnt[row][col]);
				}
			}
		}
		
		System.out.println(candyCnt[N-1][M-1]);

	}

}
