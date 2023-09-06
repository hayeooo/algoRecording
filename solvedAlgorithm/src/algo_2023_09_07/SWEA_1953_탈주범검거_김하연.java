package algo_2023_09_07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
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
 */
class Location{
	int x;
	int y;
	int sec;
	
	Location(int x,int y,int sec){
		this.x=x;
		this.y=y;
		this.sec=sec;
	}
}
public class SWEA_1953_탈주범검거_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int T;		// 총 테스트케이스 개수
	static int N,M;		// 지하 터널 지도의 세로 크기, 가로 크기
	static int R,C,L;		// 맨홀 뚜껑이 위치한 장소의 세로 위치 R, 가로 위치 C, 탈출 후 소요된 시간 L
	static int[][] map;
	static boolean[][] visited;
	static int expected;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트 케이스 수를 입력받는다.
		T=Integer.parseInt(br.readLine().trim());
		
		// 테스트 케이스만큼 반복
		for (int test_case=1;test_case<=T;test_case++) {
			st=new StringTokenizer(br.readLine().trim());
			// 지하 터널 지도의 세로 크기, 가로 크기를 입력 받는다.
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			
			// 맨홀 뚜껑이 위치한 장소의 세로, 가로 위치를 입력 받는다.
			R=Integer.parseInt(st.nextToken());
			C=Integer.parseInt(st.nextToken());
			
			// 탈출 후 소요된 시간을 입력받는다.
			L=Integer.parseInt(st.nextToken());
			
			// 지하 터널 정보를 입력받는다.
			map=new int[N][M];
			visited=new boolean[N][M];
			for (int row=0;row<N;row++) {
				st=new StringTokenizer(br.readLine().trim());
				for (int col=0;col<M;col++) {
					map[row][col]=Integer.parseInt(st.nextToken());
				}
			}
			expected=0;
			// 맨홀 뚜껑이 위치한 자리부터 탈주범이 위치할 수 있는 장소의 개수를 구한다.
		}

	}
	
	public static void bfs(int x, int y) {
		Deque<Location> que=new ArrayDeque<>();
		
		que.add(new Location(x,y,1));
		visited[x][y]=true;
		
		// que가 빌 때까지 반복
		while (!que.isEmpty()) {
			Location cur=que.poll();
			
			if (cur.sec>L) continue;
			
			int structure=map[cur.x][cur.y];
			
			// 현재 구조물이 1
			if (structure==1) {
				// 4방향 탐색
				int[] dx= {-1,1,0,0};		// 북, 남, 동, 서
				int[] dy= {0,0,1,-1};
				
				for (int d=0;d<4;d++) {
					int nextX=cur.x+dx[d];
					int nextY=cur.y+dy[d];
					
					if (isRange(nextX,nextY) && !visited[nextX][nextY]) {
						if (isConnect(d,map[nextX][nextY])) {
							que.add(new Location(nextX,nextY,cur.sec+1));
						}
					}
				}
				
			}
			// 현재 구조물이 2
			else if (structure==2) {
				// 2방향 탐색
				int[] dx= {-1,1};	// 북, 남
				int[] dy= {0,0};
				
				for (int d=0;d<=2;d++) {
					int nextX=cur.x+dx[d];
					int nextY=cur.y+dy[d];
					
					if (isRange(nextX,nextY) & !visited[nextX][nextY]) {
						if (isConnect(d,map[nextX][nextY])) {
							que.add(new Location(nextX,nextY,cur.sec+1));
						}
					}
				}
			}
			// 현재 구조물이 3
			
			// 현재 구조물이 4
			
			// 현재 구조물이 5
			
			// 현재 구조물이 6
			
			// 현재 구조물이 7
			
		}
	}
	
	public static boolean isRange(int x,int y) {
		return (x>=0 && x<N &&y>=0 && y<M);
	}
	
	public static boolean isConnect(int d, int next) {
		// 북쪽을 바라볼 때 연결할 수 있는 구조물이 있는 경우
		if (d==0) {
			if (next==1 || next==2 || next==5 || next==6) return true;
		}
		// 남쪽을 바라볼 때 연결할 수 있는 구조물이 있는 경우
		else if(d==1) {
			if (next==1 || next==2 || next==4 || next==7) return true;
		}
		// 동쪽을 바라볼 때 연결할 수 있는 구조물이 있는 경우
		else if(d==2) {
			if (next==1 || next==3 || next==6 || next==7) return true;
		}
		// 서쪽을 바라볼 때 연결할 수 있는 구조물이 있는 경우
		else if(d==3) {
			if (next==1 || next==3 || next==4 || next==5) return true;
		}
		return false;
	}
	
	

}
