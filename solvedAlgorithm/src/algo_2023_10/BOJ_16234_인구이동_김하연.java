package algo_2023_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BOJ 16234 : 인구 이동
 * N*N 크기의 땅이 있고, 땅은 1*1 개의 칸으로 나누어져 있다.
 * 각각의 땅에는 나라가 하나씩 존재하며, r행 c열에 있는 나라에는 A[r][c]명이 살고 있다. 
 * 인접한 나라 사이에는 국경선이 존재한다. 모든 나라는 1*1 크기이기 때문에, 모든 국경선은 정사각형 형태이다.
 * 
 * 인구 이동은 하루 동안 다음과 같이 진행된다.
 * 아래 방법에 의해 인구 이동이 없을 때까지 지속된다.
 * 1. 국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선은 오늘 하루 동안 연다.
 * 2. 위 조건에 의해 열어야 하는 국경선이 모두 열렸다면, 인구 이동을 시작한다.
 * 3. 국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루동안은 연합이라고 한다.
 * 4. 연합을 이루고 있는 각 칸의 인구 수는 (연합의 인구 수) / (연합을 이루고 있는 칸의 개수)가 된다. 소수점은 버린다.
 * 5. 연합을 해체하고, 모든 국경선을 닫는다.
 * 
 * 각 나라의 인구 수가 주어졌을 때, 인구 이동이 며칠 동안 발생하는지 구하는 프로그램을 작성한다.
 * 
 * << 풀이 방법 >> 
 * 두 나라의 인구 차이가 L명 이상, R명 이하인 국경선을 공유하는 모든 연합국을 구한다.
 * 인구를 이동시킨다.
 */
public class BOJ_16234_인구이동_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N, L, R;
	static int[][] map;
	static boolean[][] visited;				// 연합을 이룬 여부를 저장하는 배열
	static boolean isOpen;
	
	static int[] dx= {-1,0,1,0};			// 북, 동, 남, 서
	static int[] dy= {0,1,0,-1};
	
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
		
		// N개의 줄
		N=Integer.parseInt(st.nextToken());
		
		// L명 이상
		L=Integer.parseInt(st.nextToken());
		
		// R명 이하
		R=Integer.parseInt(st.nextToken());
		
		// 각 나라의 인구 수를 입력받는다.
		map=new int[N][N];
		for (int row=0;row<N;row++) {
			st=new StringTokenizer(br.readLine().trim());
			for (int col=0;col<N;col++) {
				map[row][col]=Integer.parseInt(st.nextToken());
			}
		}
		
		for (int day=1;;day++) {
			// 연합 여부를 저장하는 배열 초기화
			visited=new boolean[N][N];
			isOpen=false;
			
			for (int row=0;row<N;row++) {
				for (int col=0;col<N;col++) {
					// 연합을 이룬 적이 없는 나라인 경우
					if (!visited[row][col]) {
						bfs(row,col);
					}
				}
			}
			// 모든 나라를 방문함에도 불구하고 연합을 이루지 않은 경우
			if (!isOpen) {
				System.out.println(day-1);
				break;
			}
		}
	}
	
	public static void bfs(int startX,int startY) {
		Queue<Pos> que=new ArrayDeque<>();
		que.offer(new Pos(startX,startY));
		visited[startX][startY]=true;
		
		int totalPopulation=map[startX][startY];
		
		// 연합을 이루고 있는 나라의 위치 정보를 담는다.
		List<Pos> unionList=new ArrayList<>();
		unionList.add(new Pos(startX,startY));
		
		// 연합을 이룰 수 있는 모든 나라를 구한다.
		while(!que.isEmpty()) {
			Pos curPos=que.poll();
			
			int curX=curPos.x;
			int curY=curPos.y;
			
			for (int d=0;d<4;d++) {
				int nextX=curPos.x+dx[d];
				int nextY=curPos.y+dy[d];
				
				// 배열 범위를 넘어간 경우
				if (!isRange(nextX,nextY)) continue;
				
				// 이미 방문한 경우
				if (visited[nextX][nextY]) continue;
				
				// 현 나라와 L이상 R이하인 경우 연합국이다.
				int diff=Math.abs(map[curX][curY]-map[nextX][nextY]);
				if (diff>=L && diff<=R) {
					visited[nextX][nextY]=true;
					que.offer(new Pos(nextX,nextY));
					
					unionList.add(new Pos(nextX,nextY));
					totalPopulation+=map[nextX][nextY];
				}
			}
		}
		// 연합을 이룰 수 있는 나라가 2이상인 경우
		if (unionList.size()>=2) {
			isOpen=true;
			
			// 인구 이동을 진행시킨다.
			int population=totalPopulation/unionList.size();
			for(Pos pos:unionList) {
				map[pos.x][pos.y]=population;
			}
		}
	}
	
	public static boolean isRange(int x,int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}

}
