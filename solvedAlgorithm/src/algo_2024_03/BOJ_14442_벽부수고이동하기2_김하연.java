package algo_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ_14442_벽부수고이동하기2_김하연 {

    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static int M;
    static int K;
    static char[][] map;
    static int[][][] dist;

    static int[] dx={-1,0,1,0};         // 북, 동, 남, 서
    static int[] dy={0,1,0,-1};         
    static class Point{
        int row;
        int col;
        int destroyCnt;

        Point(int row,int col,int destroyCnt){
            this.row=row;
            this.col=col;
            this.destroyCnt=destroyCnt;
        }
    }

    public static void main(String[] args) throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));

        // N개의 줄에 M개의 숫자, 벽을 부술 수 있는 개수 K를 입력받는다.
        st=new StringTokenizer(br.readLine().trim());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        map=new char[N][M];
        dist=new int[K+1][N][M];
        // 지도 정보를 입력받는다.
        for (int row=0;row<N;row++){
            String line=br.readLine().trim();
            for (int col=0;col<M;col++){
                map[row][col]=line.charAt(col);
            }
        }
        // BFS 수행
        Queue<Point> que=new ArrayDeque<>();
        // (0,0)에서 출발
        dist[0][0][0]=1;
        que.offer(new Point(0,0,0));

        while(!que.isEmpty()){
            Point curPoint=que.poll();
            // 다음 위치 탐색
            for (int d=0;d<4;d++){
                int nr=curPoint.row+dx[d];
                int nc=curPoint.col+dy[d];

                // 범위 확인
                if (!isRange(nr,nc)) continue;

                // 벽인 경우
                if (map[nr][nc]=='1'){
                    // 벽을 부술 수 있고 방문하지 않은 곳인지 확인
                    if (curPoint.destroyCnt>=K) continue;

                    if(dist[curPoint.destroyCnt+1][nr][nc]==0 || dist[curPoint.destroyCnt][curPoint.row][curPoint.col]+1<dist[curPoint.destroyCnt+1][nr][nc]){
                        que.offer(new Point(nr,nc, curPoint.destroyCnt+1));
                        dist[curPoint.destroyCnt+1][nr][nc]=dist[curPoint.destroyCnt][curPoint.row][curPoint.col]+1;
                    }
                    continue;
                }
                
                // 벽이 아닐 때
                // 미방문한 곳인 경우
                if (dist[curPoint.destroyCnt][nr][nc]==0){
                    que.offer(new Point(nr,nc, curPoint.destroyCnt));
                    dist[curPoint.destroyCnt][nr][nc]=dist[curPoint.destroyCnt][curPoint.row][curPoint.col]+1;
                }
            }
        }
        int minDist=Integer.MAX_VALUE;
        for (int k=0;k<=K;k++){
            if (dist[k][N-1][M-1]==0) continue;
            minDist=Math.min(minDist,dist[k][N-1][M-1]);
        }
        System.out.print(minDist==Integer.MAX_VALUE?-1:minDist);
    }

    public static boolean isRange(int r,int c){
        return r>=0 && r<N && c>=0 && c<M;
    }
}
