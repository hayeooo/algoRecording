package algo_2023_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * SWEA 1949: 등산로 조성
 * 등산로를 만들기 위한 부지는 N*N 크기를 가지고 있으며, 이곳에 최대한 긴 등산로를 만들 계획이다.
 * 1. 등산로는 가장 높은 봉우리에서 시작해야 한다.
 * 2. 등산로는 산으로 올라갈 수 있도록 반드시 높은 지형에서 낮은 지형으로 가로 또는 세로 방향으로 연결이 되어야 한다.
 * 3. 긴 등산로를 만들기 위해 딱 한 곳을 정해서 최대 K 깊이만큼 지형을 깎는 공사를 할 수 있다.
 * 
 * 이 때 만들 수 있는 가장 긴 등산로를 찾아 그 길이를 출력하는 프로그램을 작성한다.
 * 
 * 1. 가장 높은 봉우리를 찾는다.
 * 2. N*N 크기의 지도에 0~K만큼을 지형을 깎고 dfs를 수행하여 등산로 길이를 구한다.
 * 	2-1. 공사를 할 지형을 지정하는 방법? 최대한 적게 깎을수록 등산로 길이가 길어진다.
 * 		 공사를 해야하는 경우 : 인접한 지점의 높이가 같은 경우, 또는 높은 경우(낮춰야 한다)
 * 3. 최장 등산로 길이를 업데이트 한다.
 * 
 * dfs를 수행하면서 비교, 값을 갱신하는 작업 수행
 * 변경되는 값에 대해서 매개변수(*높이*)로 넘겨준다.
 */
public class SWEA_1949_등산로조성_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T;						// 테스트케이스 개수
	static int N, K;					// N*N 크기의 지도, 최대 공사 가능 깊이 K
	static int[][] map;					// 지도 정보를 저장하는 배열
	static List<int[]> maxHeightList;	// 최대 높이를 가지는 지형을 저장하는 배열
	static boolean[][] visited;			// 각 지형의 방문 여부를 저장하는 배열
	static int maxLength;
	
	static int[] dx= {-1,0,1,0};				// 북, 동, 남, 서
	static int[] dy= {0,1,0,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		// 테스트케이스를 입력받는다.
		T=Integer.parseInt(br.readLine().trim());
		for (int test_case=1;test_case<=T;test_case++) {
			// N, K를 입력 받는다.
			st=new StringTokenizer(br.readLine().trim());
			N=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			
			// 지도 정보를 입력 받으면서 최대 높이를 구한다.
			map=new int[N][N];
			int maxHeight=0;
			for (int row=0;row<N;row++) {
				st=new StringTokenizer(br.readLine().trim());
				for (int col=0;col<N;col++) {
					map[row][col]=Integer.parseInt(st.nextToken());
					maxHeight=Math.max(maxHeight, map[row][col]);
				}
			}
			
			// 최대 높이를 가진 지형의 위치를 저장한다.
			maxHeightList=new ArrayList<>();
			for (int row=0;row<N;row++) {
				for (int col=0;col<N;col++) {
					if (map[row][col]==maxHeight) {
						maxHeightList.add(new int[] {row,col});
					}
				}
			}
			
			// 최대 높이를 가진 지형의 위치부터 등산로 길이를 구한다.
			maxLength=0;
			visited=new boolean[N][N];
			for (int[] loc:maxHeightList) {
				visited[loc[0]][loc[1]]=true;
				dfs(loc[0],loc[1],maxHeight,1,false);
				visited[loc[0]][loc[1]]=false;
			}
			sb.append("#").append(test_case).append(" ").append(maxLength).append("\n");
		}
		System.out.println(sb);

	}
	// 최고점부터 dfs 탐색하며 지형을 깎는다.
	// 최장 등산로 길이를 매번 업데이트 한다.
	public static void dfs(int x,int y, int height, int length, boolean construct) {
		maxLength=Math.max(maxLength, length);
		// 네 방향 탐색
		for (int d=0;d<dx.length;d++) {
			
			int nextX=x+dx[d];
			int nextY=y+dy[d];
			
			// 배열 범위 확인
			if (nextX<0 || nextX>=N || nextY<0 || nextY>=N) {
				continue;
			}
			
			// 방문 여부 확인
			if (visited[nextX][nextY]) continue;
			
			// 다음으로 이동할 위치가 더 높을 경우
			if (map[nextX][nextY]>=height){
				// 아직 공사를 진행하지 않은 상태
				if (!construct) {	// 공사를 진행했다면 true, 진행하지 않았다면 false
					// K 범위 내로 지형을 깎을 수 있는 경우
					if (map[x][y]>map[nextX][nextY]-K) {
						visited[nextX][nextY]=true;
						dfs(nextX,nextY,height-1,length+1,true);
						visited[nextX][nextY]=false;
					}
				}
			}
			// 다음 이동할 높이가 현재 높이보다 낮은 경우 (공사를 수행하지 않는다.)
			else if (map[nextX][nextY]<height) {
				visited[nextX][nextY]=true;
				dfs(nextX,nextY,map[nextX][nextY],length+1,construct);
				visited[nextX][nextY]=false;
			}
		}
	}
	

}
