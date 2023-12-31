package algo_2023_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BOJ 2468: 안전 영역
 * 어떤 지역의 높이 정보를 파악하여 그 지역에 많은 비가 내렸을 때
 * 물에 잠기지 않는 안전한 영역이 최대로 몇 개가 만들어 지는 지를 조사한다.
 * 안전한 영역은 물에 잠기지 않는 지점들의 위, 아래, 오른쪽, 왼쪽으로 인접해있으며 그 크기가 최대인 영역이다.
 * 
 * 1. 지역의 정보를 입력받는다.
 * 2. 잠길 수 있는 모든 높이에 대해 안전한 영역 개수를 구한다.
 * 	2-1. 높이의 범위는 1이상 100 이하의 정수이다. 하지만, 모든 지역이 잠기지 않을 수 있으므로 0도 포함해야 한다.(주의)
 * 3. 높이가 >=N 인 지역에 대해 bfs를 수행한다.
 * 	3-1. 이미 방문한 지역에 중복으로 가지 않기 위해 방문 여부 정보를 담는 배열을 만든다.
 * 4. 가능한 모든 높이에 대해 bfs를 수행하였을 때, 안전한 영역의 최댓값을 저장한다.
 */
public class BOJ_2468_안전영역_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;				// 2차원 배열의 행과 열의 개수
	static int[][] map;			// 어떤 지역들의 높이를 저장하는 배열
	static boolean[][] visited;	// 안전한 영역을 탐색할 때 필요한 방문 여부 배열
	
	static int[] dx= {-1,0,1,0};	// 북, 동, 남, 서
	static int[] dy= {0,1,0,-1};
	
	static int maxSafeRegion=0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 2차원 배열의 행과 열의 개수를 입력받는다.
		N=Integer.parseInt(br.readLine());
		
		
		// 2차원 배열 높이 정보를 입력받는다.
		map=new int[N][N];
		for (int row=0;row<N;row++) {
			st=new StringTokenizer(br.readLine().trim());
			for (int col=0;col<N;col++) {
				map[row][col]=Integer.parseInt(st.nextToken());
				
			}
		}
		// 잠길 높이를 구한다.
		for (int height=0;height<=100;height++) {
			// 잠길 높이가 구해졌다면
			// 잠길 높이보다 높은 지역에 대한 bfs를 수행한다.
			int safeRegion=0;
			visited=new boolean[N][N];
			for (int row=0;row<N;row++) {
				for (int col=0;col<N;col++) {
					if (map[row][col]>height && !visited[row][col]) {
						bfs(row,col,height);
						safeRegion+=1;
					}
				}
			}
			// 각 잠길 높이에서 안전한 영역의 개수가 구해졌다면
			// 최댓값을 저장한다.
			maxSafeRegion=Math.max(maxSafeRegion, safeRegion);
		}
		
		// 결과를 출력한다.
		System.out.println(maxSafeRegion);
	}
	// 물에 잠기지 않는 안전한 영역을 표시한다.
	// 위, 아래, 오른쪽, 왼쪽 인접한 영역
	public static void bfs(int x,int y,int height) {
		Queue<int[]> que=new LinkedList<>();
		que.add(new int[] {x,y});
		visited[x][y]=true;
		
		while (!que.isEmpty()) {
			int[] curLoc=que.poll();
			int curX=curLoc[0];
			int curY=curLoc[1];
			
			// 인접한 영역 중
			// 안전한 지역을 고른다.
			for (int d=0;d<dx.length;d++) {
				int nextX=curX+dx[d];
				int nextY=curY+dy[d];
				
				// 배열의 범위를 벗어나는 경우
				if (nextX<0 || nextX>=N || nextY<0 || nextY>=N) {
					continue;
				}
				// 이미 방문한 지역인 경우
				if (visited[nextX][nextY]) continue;
				
				// 가라앉은 지역인 경우
				if (map[nextX][nextY]<=height) continue;
				
				// 안전한 영역인 경우 que에 추가
				que.add(new int[] {nextX,nextY});
				visited[nextX][nextY]=true;
			}
			
		}
		
	}

}
