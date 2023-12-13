package algo_2023_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [ 문제 ]
 * 차량이 이동 가능한 시나리오의 수를 찾으라는 업무 지시를 받았다.
 * 이동은 숫자 0과 1로만 이루어져 있는 n*n 크기의 격자 위에서 일어난다.
 * 숫자 0은 빈 칸을 의미하며, 숫자 1은 해당 칸이 벽으로 막혀 있음을 의미한다.
 * 차량은 n*n 격자 내에서 m개의 지점을 순서대로 방문하려고 한다. 이 때 이동은 항상 상하좌우 중 인접한 칸으로만 이동하되
 * 벽은 지나갈 수 없으며, 한 번 지났던 지점은 다시 방문해서 안된다.
 * 이러한 조건 하에서 차량이 이동 가능한 서로 다른 가지 수를 구하는 프로그램을 작성한다.
 *
 * [ 풀이 ]
 * DFS로 이동 가능한 모든 경우의 수를 구하고
 * m개의 지점을 방문했는지 확인한다. : 틀림
 * m개의 순서를 보장하면서 이동해야 한다. : 방문 순서 index로 체크한다.
 * 인접행렬을 사용하고 시간 복잡도는 O(n^2)
 */
public class Softeer_순서대로방문하기_김하연 {

    static BufferedReader br;
    static StringTokenizer st;
    
    static int n;           // 격자의 크기
    static int m;           // 방문해야 하는 칸의 수
    static int[][] map;     // 격자 정보를 저장하는 배열
    static List<Pos> posList;   // 방문해야 하는 위치를 저장하는 리스트
    static int[] dx={-1,0,1,0};     // 북, 동, 남, 서
    static int[] dy={0,1,0,-1};
    static boolean[][] visited;
    static int count;
    
    static class Pos {
        int x;
        int y;
        
        Pos(int x,int y){
            this.x=x;
            this.y=y;
        }

        public boolean equals(Pos p){
            return x==p.x&&y==p.y;
        }
    }

    public static void main(String[] args) throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));

        // 격자의 크기와 순서대로 방문해야 하는 칸의 수를 입력받는다.
        st=new StringTokenizer(br.readLine().trim());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        
        // 격자 정보를 입력받는다.
        map=new int[n][n];
        for (int row=0;row<n;row++){
            st=new StringTokenizer(br.readLine().trim());
            for (int col=0;col<n;col++){
                map[row][col]=Integer.parseInt(st.nextToken());
            }
        }

        // 방문해야 하는 위치를 입력받는다.
        posList=new ArrayList<>();
        for (int idx=0;idx<m;idx++){
            st=new StringTokenizer(br.readLine().trim());
            int x=Integer.parseInt(st.nextToken())-1;
            int y=Integer.parseInt(st.nextToken())-1;
            posList.add(new Pos(x,y));
        }

        // 출발 지점
        Pos startPos=posList.get(0);
        // 도착 지점
        Pos destPos=posList.get(m-1);
        visited=new boolean[n][n];
        visited[startPos.x][startPos.y]=true;
        count=0;
        dfs(startPos,1);
        System.out.println(count);
    }
    public static void dfs(Pos pos, int next){
        // 종료 조건
        if (pos.equals(posList.get(next))){
            if (next==m-1){
                count++;
                return;
            }
            next++;
        }
        for (int d=0;d<dx.length;d++){
            int nx=pos.x+dx[d];
            int ny=pos.y+dy[d];
            // 격자 범위를 벗어나는 경우 제외
            if (!isRange(nx,ny)) continue;
            // 이미 방문한 경우 제외
            if (visited[nx][ny]) continue;
            // 벽인 경우 제외
            if (map[nx][ny]==1) continue;

            // 모든 조건을 통과한 경우
            // 방문할 수 있는 칸이다
            visited[nx][ny]=true;
            dfs(new Pos(nx,ny),next);
            visited[nx][ny]=false;
        }
    }
    // 현재 위치가 배열 범위 안에 들어가는지 확인한다.
    public static boolean isRange(int x,int y){
        return x>=0 && x<n && y>=0 && y<n;
    }

}
