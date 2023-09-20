package algo_2023_09_21;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * BOJ 15683: 감시
 * 사무실의 세로 크기 N과 가로 크기 M이 주어진다.
 * 사무실 각 칸의 정보가 주어진다. 0은 빈 칸, 6은 벽, 1~5는 CCTV를 나타낸다.
 * CCTV의 최대 개수는 8개를 넘지 않는다.
 * CCTV 번호에 따라 탐색할 수 있는 방향이 다르고 항상 90도 방향으로 회전해야 하며, 방향이 가로 또는 세로 방향이다.
 * 
 * CCTV 번호에 따라 탐색할 수 있는 방향과 개수를 배열에 저장한다.
 * Java 배열 초기화: 내부 요소 개수가 달라도 사용할 수 있다.
 * 배열 이름은 레퍼런스 변수로 각 배열에 대한 참조값을 저장한다.
 * cctv : cctv[0] 참조값을 저장
 * cctv[0]: cctv[0][0] 참조값 저장
 * cctv[1]: cctv[1][0] 참조값 저장 ...
 */
public class BOJ_15683_감시_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N,M;				// 지도의 세로 크기와 가로 크기
	static int[][] map;			// 사무실 각 칸의 정보를 저장하는 배열
	static boolean[][] visited;	// CCTV가 감시한 곳을 저장하는 배열
	
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,1,0,-1};
	
	// 0: 북, 1: 동, 2: 남, 3: 서
	static int[][][] cctv= {
			{{}},
			{{0},{1},{2},{3}},
			{{1,3},{0,2}},
			{{0,1},{1,2},{2,3},{3,0}},
			{{0,1,3},{0,1,2},{1,2,3},{2,3,0}},
			{{0,1,2,3}}
	};
	
	public static void main(String[] args) {
		
		
	}

}
