package algo_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16236_아기상어_김하연 {

    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static int[][] map;
    static Pos babyShark;
    static int babySharkSize;
    static int fishCnt;
    static int time;
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
        br=new BufferedReader(new InputStreamReader(System.in));

        N=Integer.parseInt(br.readLine().trim());

        // 지도 정보를 입력받는다.
        map=new int[N][N];

        for (int row=0;row<N;row++){
            st=new StringTokenizer(br.readLine().trim());
            for (int col=0;col<N;col++){
                map[row][col]=Integer.parseInt(st.nextToken());
                if (map[row][col]==9){
                    babyShark=new Pos(row,col);
                }
            }
        }
        babySharkSize=2;
        fishCnt=0;
        time=0;
        // 상어가 어디로 이동할지 결정한다.(BFS)
        while (true){
            Pos fishPos=bfs(babyShark.r,babyShark.c);
            if (fishPos.r==-1 && fishPos.c==-1) break;

            // 물고기를 잡아먹고, 물고기 개수를 업데이트 한다.
            map[fishPos.r][fishPos.c]=0;
            fishCnt+=1;
            // 만약 아기상어 크기와 물고기 개수가 같다면
            // 아기상어의 크기를 늘린다.
            if (fishCnt==babySharkSize){
                fishCnt=0;
                babySharkSize+=1;
            }
        }

    }

    // 물고기를 잡아먹을 위치를 반환한다.
    public static Pos bfs(int row, int col){

    }
}
