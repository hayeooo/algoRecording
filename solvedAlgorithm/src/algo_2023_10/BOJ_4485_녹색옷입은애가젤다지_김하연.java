package algo_2023_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * BOJ 4485: 녹색 옷 입은 애가 젤다지?
 * 링크는 도둑루피만 가득한 N*N 크기의 동굴의 제일 왼쪽 위에 있다. 
 * 이 동굴의 반대편 출구, 제일 오른쪽 아래 칸인 [N-1][N-1]까지 이동해야 한다. 
 * 동굴의 각 칸마다 도둑루피가 있는데, 이 칸을 지나면 해당 도둑루피의 크기만큼 소지금을 잃게 된다.
 * 링크는 잃는 금액을 최소로 하여 건너편까지 이동해야 하며, 한 번에 상하좌우 인접한 곳으로 1칸씩 이동할 수 있다.
 * 링크가 잃을 수 밖에 없는 최소 금액
 * 
 * 각 테스트케이스의 첫째 줄에는 동굴의 크기를 나타내는 정수 N이 주어진다. N=0인 입력이 주어지면 전체 입력이 종료된다.
 * 
 * <<시도한 풀이>>
 * 1. 각 칸에 도달했을 때의 최소 도둑루피를 저장하는 dp배열을 활용한다.
 * 2. 각 칸에 도달했을 때 최솟값은 자신의 왼쪽, 위쪽에 저장된 dp값에 해당 칸의 도둑루피를 더한 최솟값을 저장한다.
 * => 각 칸에 도달할 순서를 정할 수 없다. 왼쪽 위부터 오른쪽 아래까지 순차적으로 dp 배열을 채운다면, 최솟값을 보장할 수 없다.
 * 
 * <<해결 방법>>
 * 도둑루피의 누적값이 작은 경로를 우선 탐색한다.
 * 1. priorityQueue를 사용하여 해당 칸의 위치와 도둑루피 누적값을 넣어준다.
 * 2. proirotyQueue에서 도둑루피 누적값이 작은 순으로 꺼내고, 인접한 칸 중 방문하지 않은 칸에 대해 도둑루피의 누적값을 구해 추가해준다.
 * 3. 꺼낸 값이 목적지라면 그 값을 출력해준다.
 */
public class BOJ_4485_녹색옷입은애가젤다지_김하연 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx= {-1,0,1,0};		// 북, 동, 남, 서
	static int[] dy= {0,1,0,-1};
	
	static class Info implements Comparable<Info>{
		int r;
		int c;
		int value;
		
		Info(){}
		
		Info(int r,int c,int value){
			this.r=r;
			this.c=c;
			this.value=value;
		}

		@Override
		public int compareTo(Info o) {
			return this.value-o.value;
		}
		
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		int problem=1;	// 문제 번호(출력을 위한 변수)
		while (true) {
			// 동굴의 크기
			N=Integer.parseInt(br.readLine());
			
			// N==0 전체 입력 종료
			if (N==0) {
				break;
			}
			
			map=new int[N][N];
			visited=new boolean[N][N];
			
			// 동굴 정보 입력받는다.
			for (int row=0;row<N;row++) {
				st=new StringTokenizer(br.readLine().trim());
				for (int col=0;col<N;col++) {
					map[row][col]=Integer.parseInt(st.nextToken());
				}
			}
			
			// 출발지부터 위치와 도둑루피 값을 저장한다.
			PriorityQueue<Info> pq=new PriorityQueue<>();
			pq.offer(new Info(0,0,map[0][0]));
			visited[0][0]=true;
			
			while (!pq.isEmpty()) {
				// 누적 루피값 중 최소값 우선 탐색
				Info curInfo=pq.poll();
				
				if (curInfo.r==N-1 && curInfo.c==N-1) {
					sb.append("Problem ").append(problem).append(": ").append(curInfo.value).append("\n");
					break;
				}
				// 상, 하, 좌, 우 인접한 칸 탐색
				for (int d=0;d<4;d++) {
					int nextR=curInfo.r+dx[d];
					int nextC=curInfo.c+dy[d];
					
					// 배열 범위를 넘어가는 경우
					if (nextR<0 || nextR>=N || nextC<0 || nextC>=N) {
						continue;
					}
					// 이미 방문한 칸은 다시 방문해봐야 누적 루피만 추가된다.(건너뛰기)
					if (visited[nextR][nextC]) continue;
					
					// 배열 범위를 넘어가지 않았고, 미방문 칸일 경우 
					visited[nextR][nextC]=true;
					pq.offer(new Info(nextR,nextC,curInfo.value+map[nextR][nextC]));
				}
			}
			problem++;
		}
		System.out.print(sb);
	}

}
