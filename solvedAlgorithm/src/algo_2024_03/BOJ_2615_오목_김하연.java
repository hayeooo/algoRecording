package algo_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2615_오목_김하연 {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int[] dx={0,1,1,1};     // 동, 동남, 남, 남서
    static int[] dy={1,1,0,-1};
    static char[][] map;
    static boolean[][] visited;
    static final int N = 19;

    static class Pos{
        int x;
        int y;
        char color;
        Pos(int x, int y, char color){
            this.x=x;
            this.y=y;
            this.color=color;
        }
    }
    public static void main(String[] args) throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));

        // 19 * 19
        map = new char[N][N];
        visited =new boolean[N][N];

        for (int row=0;row<N;row++){
            st=new StringTokenizer(br.readLine().trim());
            for (int col=0;col<N;col++){
                map[row][col]=st.nextToken().charAt(0);
            }
        }
        boolean find=false;
        Pos win=new Pos(-1,-1, '0');
        sb=new StringBuilder();

        for (int row=0;row<N;row++){
            for (int col=0;col<N;col++){
                if (map[row][col]=='1' || map[row][col]=='2'){
                    if (!visited[row][col]){
                        for (int d=0;d<dx.length;d++) {
                            int serialCnt = dfs(map[row][col], row, col, d, 1);
                            if (serialCnt == 5) {
                                win = new Pos(row, col, map[row][col]);
                                find = true;
                                break;
                            }
                        }
                    }
                }
                if (find) break;
            }
            if (find) break;
        }
        sb.append(win.color).append("\n");
        if (win.x>=0 && win.y>=0){
            sb.append(win.x).append(" ").append(win.y);
        }
        System.out.print(sb);
    }
    // 방향도 고려해야 한다.
    public static int dfs(char color, int x, int y, int d, int count){
        // 범위를 넘어간 경우
        if (!isRange(x,y)) return count;
        
        // 알이 놓이지 않은 자리거나 다른 색깔인 경우
        if (map[x][y]==0 || map[x][y]!=color) return count;
        
        // 그 외의 경우는 같은 색깔인 알인 경우
        // 개수를 더하고 다음 위치를 찾는다.
        visited[x][y]=true;
        count+=1;
        int nx=x+dx[d];
        int ny=y+dy[d];
        return dfs(color,nx,ny,d,count);
    }

    public static boolean isRange(int x,int y){
        return x>=0 && x<N && y>=0 && y<N;
    }
}
