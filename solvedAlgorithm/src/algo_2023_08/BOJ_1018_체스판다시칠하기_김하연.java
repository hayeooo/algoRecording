package algo_2023_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 8*8 크기의 체스판으로 잘라낸 후, 다시 정사각형을 다시 칠한다.
 * 1. W로 시작하는 경우
 * 2. B로 시작하는 경우
 */
public class BOJ_1018_체스판다시칠하기_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int M;			// 보드판 세로
	static int N;			// 보드판 가로
	
	static String[] board;	// 보드판 정보
	static char[] colors= {'W','B'};	
	static int minCnt=Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 보드판 세로, 가로 길이를 입력받는다.
		st=new StringTokenizer(br.readLine().trim());
		M=Integer.parseInt(st.nextToken());
		N=Integer.parseInt(st.nextToken());
		
		// 보드판 정보를 입력받는다.
		board=new String[M];
		for (int row=0;row<M;row++) {
			board[row]=br.readLine().trim();
		}
		
		// 8*8 체스판으로 쪼개서 모든 경우에 대해 탐색한다.
		for (int startRow=0;startRow<=M-8;startRow++) {
			for (int startCol=0;startCol<=N-8;startCol++) {
				// 맨 위쪽을 흰색으로 칠하는 경우
				int startWhiteCnt=colorBoard(startRow,startCol,0);
				minCnt=Math.min(startWhiteCnt, minCnt);
				
				// 맨 위쪽을 검은색으로 칠하는 경우
				int startBlackCnt=colorBoard(startRow,startCol,1);
				minCnt=Math.min(startBlackCnt, minCnt);
				
			}
		}
		System.out.println(minCnt);
		
	}
	// colorIdx에 해당하는 색깔로 보드를 칠한다.
	public static int colorBoard(int r,int c, int colorIdx) {
		int curIdx=colorIdx;
		int convertCnt=0;
		
		// r,c 위치로부터 8*8 크기의 정사각형에서 바꿔야할 색깔의 칸 수를 구한다.
		for (int row=r;row<r+8;row++) {
			for (int col=c;col<c+8;col++) {
				if (board[row].charAt(col)!=colors[curIdx]) {
					convertCnt+=1;
					
				}
				// 색깔 전환
				curIdx=1-curIdx;
			}
			// 행을 바꿀 때, 색깔 전환을 한 번 더 해야한다.
			curIdx=1-curIdx;
		}
		
		return convertCnt;
	}

}
