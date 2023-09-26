package algo_2023_09_26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * BOJ 2239: 스도쿠
 * 1. 0으로 되어있는 칸에는 모두 숫자를 채워야 한다.
 * 	1-1. 단, 답이 여러 개 있다면 그 중 사전식으로 출력해야 하므로 1부터 차례대로 넣어본다.
 * 2. 숫자를 하나씩 채워나가면서 가로, 세로, 사각형 검사를 시도한다.
 * 	  스도쿠를 만들 수 없는 경우를 빠르게 배제시키기 위함이다.
 * 
 */
class Loc{
	int x;
	int y;
	
	Loc(){}
	
	Loc(int x,int y){
		this.x=x;
		this.y=y;
	}
}
public class BOJ_2239_스도쿠_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static final int SIZE=9;
	static char[][] board=new char[SIZE][SIZE];
	
	static int zeroCnt;			// 숫자가 채워지지 않은 칸의 개수
	static List<Loc> zeroList;
	static boolean done=false;
	
	public static void main(String[] args) throws IOException {
		
		// board를 입력받는다
		br=new BufferedReader(new InputStreamReader(System.in));
		zeroList=new ArrayList<>();
		for (int row=0;row<SIZE;row++) {
			board[row]=br.readLine().trim().toCharArray();
			for (int col=0;col<SIZE;col++) {
				if (board[row][col]=='0') {
					zeroList.add(new Loc(row,col));
				}
			}
		}
		zeroCnt=zeroList.size();
		dfs(0,0);
	}
	
	public static void dfs(int idx,int cnt) {
		System.out.println("==============");
		for (int row=0;row<SIZE;row++) {
			System.out.println(Arrays.toString(board[row]));
		}
		// 숫자를 모두 채웠다면 return
		if (cnt==zeroCnt) {
			if (!done) {
				for (int row=0;row<SIZE;row++) {
					System.out.println(Arrays.toString(board[row]));
				}
				done=true;
			}
			return;
		}
		
		int curX=zeroList.get(idx).x;
		int curY=zeroList.get(idx).y;
		
		for (int num=1;num<=9;num++) {
			// 일단 스도쿠에 넣는다.
			board[curX][curY]=Integer.toString(num).charAt(0);
			
			// 올바른 스도쿠가 되는지 확인한다.
			if (checkSudoku(curX,curY)) {
				dfs(idx+1,cnt+1);
			}
		}
		return ;
	}
	
	public static boolean checkSudoku(int x,int y) {
		boolean[] chosen=null;
		
		// 가로 방향
		chosen=new boolean[10];
		for (int col=0;col<9;col++) {
			int num = board[x][col]-'0';
			// 0인 경우 무시
			if (num==0) continue;
			// 중복된 숫자인 경우 false
			if (chosen[num]) return false;
			
			chosen[num]=true;
		}
		
		// 세로 방향
		chosen=new boolean[10];
		for (int row=0;row<9;row++) {
			int num=board[row][y]-'0';
			// 0인 경우 무시
			if (num==0) continue;
			// 중복된 숫자인 경우 false
			if (chosen[num]) return false;
			chosen[num]=true;
		}
		
		// 3*3 사각형 모양
		chosen=new boolean[10];
		int startX=x/3;
		int startY=y/3;
		for (int row=startX;row<startX+3;row++) {
			for (int col=startY;col<startY+3;col++) {
				int num=board[row][col]-'0';
				// 0인 경우 무시
				if (num==0) continue;
				// 중복된 숫자인 경우 false
				if (chosen[num])return false;
				chosen[num]=true;
			}
		}
		
		return true;
	}

}
