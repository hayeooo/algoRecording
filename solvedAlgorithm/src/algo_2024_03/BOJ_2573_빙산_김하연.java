package algo_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573_빙산_김하연 {
    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static int M;
    static int[][] map;
    static int[] dx={-1,0,1,0};     // 북, 동, 남, 서
    static int[] dy={0,1,0,-1};

    static class Iceberg{
        int row;
        int col;
        Iceberg(int row, int col){
            this.row=row;
            this.col=col;
        }
    }

   public static void main(String[] args) throws IOException{
        br=new BufferedReader(new InputStreamReader(System.in));

        st=new StringTokenizer(br.readLine().trim());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        map=new int[N][M];

        for (int row=0;row<N;row++){
            st=new StringTokenizer(br.readLine().trim());
            for (int col=0;col<M;col++){
                map[row][col]=Integer.parseInt(st.nextToken());
            }
        }

        for (int year=1;;year++){
            // 빙산이 녹는다.
            melt();
            // 빙산의 개수를 센다
            int icebergCnt=countIceberg();
            // 빙산의 개수가 2 이상인 경우 결과를 출력한다.
            if (icebergCnt>=2){
                System.out.println(year);
                break;
            }
            else if(icebergCnt==0){
                System.out.println(0);
                break;
            }
        }
   }

   public static void melt(){
        Queue<Iceberg> que=new ArrayDeque<>();
        boolean[][] visited=new boolean[N][M];

        for (int row=0;row<N;row++){
            for (int col=0;col<M;col++){
                if (map[row][col]>0){
                    que.offer(new Iceberg(row,col));
                    visited[row][col]=true;
                }
            }
        }

        while(!que.isEmpty()){
            Iceberg curIceberg=que.poll();
            int water=0;
            for (int d=0;d<4;d++){
                int nr= curIceberg.row+dx[d];
                int nc= curIceberg.col+dy[d];

                if (!isRange(nr,nc)) continue;
                // 주변의 물의 개수를 센다.
                if (visited[nr][nc]) continue;
                if (map[nr][nc]==0) water++;
            }
            map[curIceberg.row][curIceberg.col]-=water;
            if (map[curIceberg.row][curIceberg.col]<0){
                map[curIceberg.row][curIceberg.col]=0;
            }
        }
   }
   public static void dfs(int row, int col, boolean[][] visited){
        visited[row][col]=true;
        for (int d=0;d<4;d++){
            int nr=row+dx[d];
            int nc=col+dy[d];
            if (isRange(nr,nc) && !visited[nr][nc] && map[nr][nc]>0){
                dfs(nr,nc,visited);
            }
        }
   }
    public static int countIceberg(){
        int result=0;

        boolean[][] visited=new boolean[N][M];
        for (int row=0;row<N;row++){
            for (int col=0;col<M;col++){
                if (!visited[row][col] && map[row][col]>0){
                    dfs(row,col,visited);
                    result+=1;
                }
            }
        }
        return result;
    }
   public static boolean isRange(int r,int c){
        return r>=0 && r<N && c>=0 && c<M;
   }
}
