package algo_2023_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BOJ 2239: 스도쿠
 * 9*9 크기의 보드가 있을 때, 각 행과 각 열 , 3*3 크기의 보드에 1~9까지의 숫자가 중복 없이 나타나도록 보드를 채우면 된다.
 * 하다 만 스도쿠 퍼즐이 주어졌을 때, 마저 끝내는 프로그램을 작성한다.
 *
 * 1. 숫자를 채워야 할 공간을 저장한다.
 * 2. 그 공간에 1~9까지 숫자를 채워넣으면서 중복되는 숫자가 있는지 확인한다.
 * 3. 숫자를 채워야 할 모든 공간에 숫자를 넣고 조건에 맞는 스도쿠가 완성된다면 그 결과를 출력한다.
 */


public class BOJ_2239_스도쿠_김하연 {
	
	static BufferedReader br;
	static StringBuilder sb;
	
	static final int SIZE=9;
	static char[][] map;
	static List<Pos> zeroPosList;
	static boolean done=false;
	
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
    	
    	// 스도쿠의 정보를 입력받는다.
    	// 크기는 SIZE*SIZE
    	map=new char[SIZE][SIZE];
    	zeroPosList=new ArrayList<Pos>();
    	
    	for (int row=0;row<SIZE;row++) {
    		String line=br.readLine().trim();
    		for (int col=0;col<SIZE;col++) {
    			map[row][col]=line.charAt(col);
    			if (map[row][col]=='0') {
    				zeroPosList.add(new Pos(row,col));
    			}
    		}
    	}
    	matchNum(0);
    }
    
    public static void matchNum(int cnt) {
    	
    	if (cnt==zeroPosList.size()) {
    		done=true;
    		
    		// 스도쿠 값을 출력한다.
    		sb=new StringBuilder();
    		for (int row=0;row<SIZE;row++) {
    			for (int col=0;col<SIZE;col++) {
    				sb.append(map[row][col]);
    			}
    			sb.append("\n");
    		}
    		System.out.print(sb);
    		
    		return;
    	}
    	
    	if (done) return;
    	
    	int curR=zeroPosList.get(cnt).x;
    	int curC=zeroPosList.get(cnt).y;
    	
    	for (char num='1';num<='9';num++) {
    		// 숫자를 하나씩 채워넣는다.
    		
    		if (isValid(curR,curC,num)) {
    			map[curR][curC]=num;
    			matchNum(cnt+1);
    			
    		}
    	}
    	map[curR][curC]='0';
    	
    }
    public static boolean isValid(int r,int c, char value) {
    	
    	// 가로 ,세로
    	for (int idx=0;idx<SIZE;idx++) {
    		// System.out.println("map: "+map[r][idx]+", num: "+value);
    		if (map[r][idx]==value) return false;
    		if (map[idx][c]==value) return false;
    	}
    	int startR=(r/3)*3;
    	int startC=(c/3)*3;
    	// 3*3 정사각형
    	for (int row=startR;row<startR+3;row++) {
    		for (int col=startC;col<startC+3;col++) {
    			if (map[row][col]==value) {
    				return false;
    			}
    		}
    	}
    	
    	return true;
    }
    public static boolean isValid(int r, int c) {
  
    	// 가로, 세로
    	for (int idx=0;idx<SIZE;idx++) {
    		if (idx!=c && map[r][idx]==map[r][c]) return false;
    		if (idx!=r && map[idx][c]==map[r][c]) return false;
    	}
    	
    	// 3*3 정사각형
    	int startR=(r/3)*3;
    	int startC=(c/3)*3;
    	
    	for (int row=startR;row<startR+3;row++) {
    		for (int col=startC;col<startC+3;col++) {
    			if (row==r && col==c) continue;
    			if (map[row][col]==map[r][c]) {
    				return false;
    			}
    		}
    	}
    	
    	return true;
    }
}

