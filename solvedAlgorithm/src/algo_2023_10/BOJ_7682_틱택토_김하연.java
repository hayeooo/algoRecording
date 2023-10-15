package algo_2023_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ 7642 : 틱택토
 * << 문제 >>
 * 틱택토 게임은 두 명의 사람이 번갈아가며 말을 놓는 게임이다.
 * 게임판은 3*3 격자판이며, 처음에는 비어있다.
 * 두 사람은 X 또는 O 말을 번갈아가며 놓는데, 반드시 첫 번째 사람이 X를 놓고 두 번째 사람이 O를 놓는다.
 * 어느 때든지 한 사람의 말이 가로, 세로, 대각선 방향으로 3칸을 잇는 데 성공하면 게임은 즉시 끝난다.
 * 게임판이 가득 차도 게임은 끝난다.
 * 게임판의 상태가 주어지면, 그 상태가 틱택토 게임에서 발생할 수 있는 최종 상태인지 판별하시오
 * 
 * << 풀이 방법 >>
 * 조건을 꼼꼼하게 따져야 한다!
 * 1. 3*3 게임판에 현재 상태를 넣으며 X의 개수와 O의 개수를 확인한다.
 * 	1-1. 승부에 상관없이 invalid한 경우
 * 		x의 개수보다 o의 개수가 많을 때
 * 		x의 개수와 o의 개수 차이가 1보다 더 많이 날 때
 * 
 * 2. 말의 개수로 발생할 수 있는 상태인 경우, 최종 상태이기 위해 가로, 세로, 대각선 방향으로 3칸을 잇는지 확인해야 한다.
 * 	2-1. 게임이 끝난 후 invalid한 경우
 * 		둘 다 이긴 경우
 * 		x가 이겼는데 x의 개수와 o의 개수가 같을 때
 * 		o가 이겼는데 x의 개수가 더 많을 때
 * 		아무도 이기지 않았는데 게임판을 꽉 채우지 못했을 때
 * 
 */
public class BOJ_7682_틱택토_김하연 {
	
	static BufferedReader br;
	static StringBuilder sb;
	
	static char[][] gameBoard;
	
	static final int SIZE=9;
	
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		while (true) {
			
			String game=br.readLine().trim();
			
			if (game.equals("end")) break;
			
			// gameBoard에 상태 정보를 저장하면서
			// 'X'와 'O'의 개수를 구한다.
			int xCnt=0;
			int oCnt=0;
			gameBoard=new char[SIZE/3][SIZE/3];
			for (int idx=0;idx<SIZE;idx++) {
				gameBoard[idx/3][idx%3]=game.charAt(idx);
				if (game.charAt(idx)=='X') xCnt++;
				else if(game.charAt(idx)=='O') oCnt++;
			}
			// 'X'보다 'O'가 많은 경우 또는 'X'와 'O' 개수 차이가 1보다 많이 나는 경우
			if (xCnt<oCnt || xCnt-oCnt>1) {
				sb.append("invalid").append("\n");
				continue;
			}
			
			// 게임을 진행해본다.
			boolean xWin=doGame('X');
			boolean oWin=doGame('O');
			
			// 'X'와 'O'가 동시에 이기는 경우는 없다.
			if (xWin && oWin) {
				sb.append("invalid").append("\n");
				continue;
			}
			// 'X'가 이겼는데 O가 말을 더 놓는 경우는 없다.
			if (xWin && xCnt==oCnt) {
				sb.append("invalid").append("\n");
				continue;
			}
			// 'O'가 이겼는데 'X'가 말을 더 놓는 경우는 없다.
			if (oWin && xCnt==oCnt+1) {
				sb.append("invalid").append("\n");
				continue;
			}
			// 'X'와 'O' 모두 이기지 않고 말의 합이 9가 아닌 경우는 없다.
			if (!xWin && !oWin && xCnt+oCnt!=SIZE) {
				sb.append("invalid").append("\n");
				continue;
			}
			// 그 외는 valid한 게임이다.
			sb.append("valid").append("\n");
		}
		System.out.print(sb);
	}
	
	public static boolean doGame(char c) {
		// 가로, 세로
		for (int i=0;i<SIZE/3;i++) {
			if (gameBoard[i][0]==c && gameBoard[i][1]==c && gameBoard[i][2]==c) {
				return true;
			}
			if (gameBoard[0][i]==c && gameBoard[1][i]==c && gameBoard[2][i]==c) {
				return true;
			}
		}
		
		// 대각선(우상향)
		if (gameBoard[0][0]==c && gameBoard[1][1]==c && gameBoard[2][2]==c) {
			return true;
		}
		// 대각선(우하향)
		if (gameBoard[2][0]==c && gameBoard[1][1]==c && gameBoard[0][2]==c) {
			return true;
		}
		return false;
	}
}
