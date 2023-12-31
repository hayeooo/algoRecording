package algo_2023_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * SWEA 1953: 탈주범 검거
 * 탈주범은 탈출한 지 한 시간 뒤, 맨홀 뚜껑을 통해 지하터널의 어느 한 지점으로 들어갔으며, 지하 터널 어딘가에서 은신 중이다.
 * 터널끼리 연결이 되어 있는 경우 이동이 가능하므로 탈주범이 있을 수 있는 위치의 개수를 계산해야 한다.
 * 탈주범은 시간당 1의 거리를 움직일 수 있다.
 * 지하 터널은 총 7종류의 터널 구조물로 구성되어 있다.
 * <<구조물>>
 * 1. 상, 하, 좌, 우에 있는 터널과 연결된다.
 * 2. 상, 하에 있는 터널이 연결된다.
 * 3. 좌, 우에 있는 터널이 연결된다.
 * 4. 상, 우에 있는 터널이 연결된다.
 * 5. 하, 우에 있는 터널이 연결된다.
 * 6. 하, 좌에 있는 터널이 연결된다.
 * 7. 상, 좌에 있는 터널이 연결된다.
 * 
 * 제한 시간동안 서로 연결되어 있는 구조물을 탐색하며 bfs를 수행한다.
 * 탐색한 위치가 한 번도 방문하지 않은 위치라면 탈주범이 있을 수 있는 장소 개수에 추가한다.
 * 구조물이 서로 연결 되어 있는지 확인, 구조물에 따라 다른 방향 주의
 * 
 * 기존 코드는 하드코딩 => 디버깅이 어렵다.
 * bfs를 쓰되, 현재 시간을 측정할 수 있어야 하며
 * 각 구조물이 탐색할 수 있는 방향 내에서 연결된 구조물이 있는지도 확인해야 한다. (typeTunnel 활용)
 */
class Pos{
	int x;
	int y;
	Pos(int x,int y){
		this.x=x;
		this.y=y;
	}
}
public class SWEA_1953_탈주범검거_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	
	static int T;			// 테스트케이스 개수
	static int N,M;			// 지하 터널 지도의 세로 크기, 가로 크기
	static int R,C;			// 맨홀 뚜껑의 세로 위치, 가로 위치
	static int L;			// 탈출 후 소요된 시간
	
	static int[][] map;
	static boolean[][] visited;
	
	// 각 구조물 별로 연결할 수 있는 방향
	static int[][] tunnel= {
			{0,0,0,0},				// 북, 동, 남, 서
			{1,1,1,1},
			{1,0,1,0},
			{0,1,0,1},
			{1,1,0,0},
			{0,1,1,0},
			{0,0,1,1},
			{1,0,0,1}
			
	};
	
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,1,0,-1};
	static int answer;
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		// 테스트케이스를 입력받는다.
		T=Integer.parseInt(br.readLine().trim());
		
		// 테스트케이스만큼 반복한다.
		for (int test_case=1;test_case<=T;test_case++) {
			// 지하 터널 지도의 세로 크기, 가로 크기, 맨홀 뚜껑이 위치한 장소의 세로 위치, 가로 위치
			// 탈출 후 소요된 시간을 입력받는다.
			st=new StringTokenizer(br.readLine().trim());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			R=Integer.parseInt(st.nextToken());
			C=Integer.parseInt(st.nextToken());
			L=Integer.parseInt(st.nextToken());
			
			map=new int[N][M];
			visited=new boolean[N][M];
			// N 줄에 지하 터널 지도 정보를 입력받는다.
			for (int row=0;row<N;row++) {
				st=new StringTokenizer(br.readLine().trim());
				for (int col=0;col<M;col++) {
					map[row][col]=Integer.parseInt(st.nextToken());
				}
			}
			answer=0;
			// 맨홀 뚜껑 위치에서 갈 수 있는 모든 공간을 찾아낸다.
			bfs(R,C);
//			for (int row=0;row<N;row++) {
//				System.out.println(Arrays.toString(visited[row]));
//			}
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
		
	}
	
	public static void bfs(int x,int y) {
		int hours=1;
		Deque<Pos> que=new ArrayDeque<>();
		que.offer(new Pos(x,y));
		visited[x][y]=true;
		answer=1;
		
		while (!que.isEmpty()) {
			if (hours>=L) {
				break;
			}
//			System.out.println("-----"+hours+"-----");
//			for (int row=0;row<N;row++) {
//				System.out.println(Arrays.toString(visited[row]));
//				
//			}
			int size=que.size();
			while (size-->0) {
				Pos cur=que.poll();
				// 현재 구조물 모양
				int type=map[cur.x][cur.y];
				
				// 현재 구조물과 연결된 방향 찾기
				for (int d=0;d<dx.length;d++) {
					// 이동할 수 없는 방향인 경우
					if (tunnel[type][d]==0) continue;
					
					// 이동할 수 있는 경우
					int nextX=cur.x+dx[d];
					int nextY=cur.y+dy[d];
					
					// 배열 범위를 넘어가는지 검사
					if (nextX<0 || nextX>=N || nextY<0 || nextY>=M) {
						continue;
					}
					// 이미 방문한 위치인지 검사
					if (visited[nextX][nextY]) continue;
					
					// 구조물이 있는지 검사
					if (map[nextX][nextY]==0) continue;
					
					// 현재 구조물과 연결되어 있는지 검사
					if (tunnel[map[nextX][nextY]][(d+2)%4]==1) {
						// 연결할 수 있다면 큐에 넣는다.
						visited[nextX][nextY]=true;
						que.offer(new Pos(nextX,nextY));
						answer+=1;
					}
				}
			}
			hours+=1;	// hours까지 작업을 마침
		}
	}
	
	
}
