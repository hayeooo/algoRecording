package algo_2023_09_26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * BOJ 17144: 미세먼지 안녕!
 * 1초 동안 아래 적힌 일이 순서대로 일어난다.
 * 1. 미세먼지가 확산, 미세먼지가 있는 모든 칸에서 동시에 일어난다.
 * 	(r,c)에 있는 미세먼지는 인접한 네 방향으로 확산된다.
 * 	인접한 방향에 공기청정기가 있거나, 칸이 없으면 그 방향으로는 확산이 일어나지 않는다.
 * 	(r,c)에 남은 미세먼지의 양은 A-(A/5)*(확산된 방향의 개수)
 */

class Pos{
	int x;
	int y;
	
	Pos(){}
	
	Pos(int x,int y){
		this.x=x;
		this.y=y;
	}
}
public class BOJ_17144_미세먼지안녕_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int R,C,T;			// 행, 열, 초 정보
	static int[][] map;
	static int[][] movedMap;	// 미세먼지가 이동한 후 map
	
	static int[] dx= {-1,0,1,0};	// 북, 동, 남, 서
	static int[] dy= {0,1,0,-1};
	
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 방의 크기와 T초를 입력받는다.
		st=new StringTokenizer(br.readLine().trim());
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		T=Integer.parseInt(st.nextToken());
		
		// 방의 정보를 입력받는다.
		map=new int[R][C];
		for (int row=0;row<R;row++) {
			st=new StringTokenizer(br.readLine().trim());
			for (int col=0;col<C;col++) {
				map[row][col]=Integer.parseInt(st.nextToken());
			}
		}
		
	
		// T초 동안 반복
		for (int sec=1;sec<=T;sec++) {
			movedMap=new int[R][C];
			// 원래 미세먼지가 있는 곳만 
			// 0,0부터 R-1,C-1까지 순차 탐색하며 미세먼지와 공기청정기의 위치를 저장한다.
			// 확산한 후, 미세면지는 movedMap에 저장된다.
			for (int row=0;row<R;row++) {
				for (int col=0;col<C;col++) {
					if (map[row][col]>0) {
						spreadVirus(row,col);
					}
				}
			}
			
			// movedMap에서 cleaner 작동
			
			
			// map 갱신
			
			
		}
		for (int row=0;row<R;row++) {
			System.out.println(Arrays.toString(map[row]));
		}
		
		
	}
	// x,y 위치에서의 미세먼지를 확산시킨다.
	public static void spreadVirus(int x, int y) {
		List<Pos> validPos=new ArrayList<>();		// 미세먼지가 이동할 수 있는 칸의 위치를 담은 arrayList
		
		movedMap[x][y]=map[x][y];
		
		// 4방향 탐색을 시도한다.
		for (int d=0;d<4;d++) {
			int nextX=x+dx[d];
			int nextY=y+dy[d];
			
			// 방을 벗어나는 경우
			if (nextX<0 || nextX>=R || nextY<0 || nextY>=C) {
				continue;
			}
			// 공기청정기가 있는 경우
			if (map[nextX][nextY]==-1) {
				continue;
			}
			validPos.add(new Pos(nextX,nextY));
		}
		
		// 확산되는 양
		int spreaded=map[x][y]/5;
		// 유효한 칸에 미세먼지를 더한다.
		for (Pos pos:validPos) {
			movedMap[pos.x][pos.y]+=spreaded;
		}
		// x,y에 남아있는 미세먼지 양
		movedMap[x][y]-=(spreaded*validPos.size());
		
		
		
	}
}
