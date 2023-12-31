package algo_2023_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * BOJ 2589: 보물섬
 * 보물섬 지도 내 L에 해당하는 위치에서 출발하는 BFS(최단 거리로 이동)에서 가장 긴 시간을 구한다.
 */
public class BOJ_2589_보물섬_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int height;					// 보물 지도의 세로 크기
	static int width;					// 보물 지도의 가로 크기
	static char[][] map;				// 보물 지도
	
	static int[] dx= {-1,0,1,0};		// 북, 동, 남, 서
	static int[] dy= {0,1,0,-1};			
	
	static int maxRes;
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 지도의 세로, 가로 크기 입력 받는다.
		st=new StringTokenizer(br.readLine().trim());
		height=Integer.parseInt(st.nextToken());
		width=Integer.parseInt(st.nextToken());
		
		// 보물 지도를 입력받는다.
		map=new char[height][width];
		for (int row=0;row<height;row++) {
			String line=br.readLine().trim();
			for (int col=0;col<width;col++) {
				map[row][col]=line.charAt(col);
			}
		}
		
		// L 위치에서 BFS를 시작한다.
		for (int row=0;row<height;row++) {
			for (int col=0;col<width;col++) {
				if (map[row][col]=='L') {
					bfs(row,col);
				}
			}
		}
		System.out.println(maxRes);
	

	}
	
	public static void bfs(int x,int y) {
		Deque<int[]> que=new ArrayDeque<>();
		int[][] visited=new int[height][width];
		for (int idx=0;idx<height;idx++) {
			Arrays.fill(visited[idx], -1);
		}
		
		que.offer(new int[] {x,y});
		visited[x][y]=0;
		int maxDist=0;
		
		while (!que.isEmpty()) {
			int[] curLocation=que.poll();
			int curX=curLocation[0];
			int curY=curLocation[1];
			
			for (int d=0;d<4;d++) {
				int nextX=curX+dx[d];
				int nextY=curY+dy[d];
				
				// 배열의 범위를 넘어간 경우
				if (nextX<0 || nextX>=height || nextY<0 || nextY>=width) {
					continue;
				}
				// 육지가 아닌 경우 무시
				if (map[nextX][nextY]!='L') continue;
				
				// 이미 방문한 경우
				if (visited[nextX][nextY]>-1) continue;
				
				// 방문하지 않은 경우, 현재 칸에서 이동거리를 1 만큼 더한다.
				visited[nextX][nextY]=visited[curX][curY]+1;
				que.offer(new int[] {nextX,nextY});
				
				// 최장 거리를 갱신한다.
				maxDist=Math.max(maxDist, visited[nextX][nextY]);
			}
		}
		maxRes=Math.max(maxDist,maxRes);
	}

}
