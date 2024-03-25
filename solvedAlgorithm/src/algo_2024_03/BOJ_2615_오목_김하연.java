package algo_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2615_오목_김하연 {
    static BufferedReader br;
    static StringTokenizer st;
    static int[][] map;
    static int[][][] visited;

    static int[] dx={-1,0,1,1};
    static int[] dy={1,1,1,0};

    public static void main(String[] args) throws IOException {

        br=new BufferedReader(new InputStreamReader(System.in));

        map=new int[21][21];
        visited=new int[21][21][4];

        for (int row=1;row<20;row++){
            st=new StringTokenizer(br.readLine().trim());
            for (int col=1;col<20;col++){
                map[row][col]=Integer.parseInt(st.nextToken());
            }
        }
        System.out.print(serialCount());
    }
    public static String serialCount(){

        for (int col=1;col<20;col++){
            for (int row=1;row<20;row++){
                if (map[row][col]!=0){
                    for (int d=0;d<4;d++){
                        if (visited[row][col][d]==0 && dfs(row,col,d,map[row][col])==5){
                            return map[row][col]+"\n"+row+" "+col+"\n";
                        }
                    }
                }
            }
        }
        return "0";

    }

    public static int dfs(int x,int y,int d,int color){
        int nx=x+dx[d];
        int ny=y+dy[d];

        if (map[nx][ny]==color){
            return visited[nx][ny][d]=dfs(nx,ny,d,color)+1;
        }
        return 1;
    }
}
