package algo_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16236_아기상어_김하연 {

    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static int[][] map;
    static int[][] dist;
    static int sharkX;
    static int sharkY;
    static int minX;
    static int minY;
    static int sharkSize;
    static int eat;
    static int minTime;
    static int totalTime;
    static int[] dx={-1,0,1,0};     // 북, 동 ,남, 서
    static int[] dy={0,1,0,-1};
    static class Pos{
        int r;
        int c;

        Pos(int r,int c){
            this.r=r;
            this.c=c;
        }
    }
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine().trim());

        // 지도 정보를 입력받는다.
        map = new int[N][N];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < N; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
                if (map[row][col] == 9) {
                    map[row][col]=0;
                    sharkX=row;
                    sharkY=col;
                }
            }
        }

        sharkSize=2;
        totalTime=0;

        while (true){
            minTime=Integer.MAX_VALUE;
            minX=-1;
            minY=-1;
            dist=new int[N][N];
            bfs(sharkX,sharkY);
            if (minX==-1 && minY==-1) break;
            // 잡아먹을 물고기가 있다면
            // 물고기를 먹고 위치를 옮긴다.
            // 시간을 업데이트 한다.
            map[minX][minY]=0;
            sharkX=minX;
            sharkY=minY;
            totalTime+=dist[minX][minY];
            eat+=1;
            if (eat==sharkSize){
                sharkSize+=1;
                eat=0;
            }

        }
        System.out.print(totalTime);
    }

    public static void bfs(int x, int y){
        Queue<Pos> que=new ArrayDeque<>();
        que.add(new Pos(x,y));

        while(!que.isEmpty()){
            Pos curPos=que.poll();

            for (int d=0;d<4;d++){
                int nextR=curPos.r+dx[d];
                int nextC=curPos.c+dy[d];

                // 배열 밖 범위
                if(!isRange(nextR,nextC))continue;
                // 상어 크기보다 큰 곳은 못 지나감
                if (map[nextR][nextC]>sharkSize)continue;
                // 이미 지나간 곳
                if (dist[nextR][nextC]>0)continue;

                // 방문할 수 있음
                dist[nextR][nextC]=dist[curPos.r][curPos.c]+1;
                que.add(new Pos(nextR,nextC));

                //자신의 크기보다 작은 물고기인 경우 최소 경로를 비교한다.
                if (map[nextR][nextC]<sharkSize && map[nextR][nextC]!=0){
                    // 최소 경로가 같은 경우
                    if (dist[nextR][nextC]==minTime){
                        // 위쪽에 있는 경우
                        if (nextR<minX){
                            minX=nextR;
                            minY=nextC;
                        }
                        else if(nextR==minX && nextC<minY){
                            minX=nextR;
                            minY=nextC;
                        }
                    }
                    // 최소 경로가 더 작은 경우
                    else if(dist[nextR][nextC]<minTime){
                        minX=nextR;
                        minY=nextC;
                        minTime=dist[nextR][nextC];
                    }
                }

            }
        }
    }
    public static boolean isRange(int r, int c){
        return r>=0 && r<N && c>=0 && c<N;
    }

}
