package algo_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_3190_뱀_김하연 {

    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static int K;
    static int L;
    static int[][] map;
    static int[] dx={0,1,0,-1};               // 동, 남, 서, 북
    static int[] dy={1,0,-1,0};
    static final int BLANK=0;
    static final int SNAKE=1;
    static final int APPLE=2;

    static class Point{
        int r;
        int c;
        Point(int r,int c){
            this.r=r;
            this.c=c;
        }
        @Override
        public String toString(){
            return "r: "+r+", c: "+c;
        }
    }
    static class ChangeInfo{
        int time;
        String dir;
        public ChangeInfo(int time,String dir){
            this.time=time;
            this.dir=dir;
        }
    }
    static Queue<Point> snakePoints;
    static List<ChangeInfo> changeInfoList;

    public static void main(String[] args) throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));

        N=Integer.parseInt(br.readLine().trim());
        map=new int[N][N];

        // 사과의 위치를 입력받는다.
        K=Integer.parseInt(br.readLine().trim());
        for (int idx=0;idx<K;idx++){
            st=new StringTokenizer(br.readLine().trim());
            int row=Integer.parseInt(st.nextToken())-1;
            int col=Integer.parseInt(st.nextToken())-1;

            map[row][col]=APPLE;
        }
        // 방향 전환 정보를 입력받는다.
        changeInfoList=new ArrayList<>();
        L=Integer.parseInt(br.readLine().trim());
        for (int idx=0;idx<L;idx++){
            st=new StringTokenizer(br.readLine().trim());
            changeInfoList.add(new ChangeInfo(Integer.parseInt(st.nextToken()),st.nextToken()));
        }

        // 뱀은 (0,0)에서 출발한다.
        snakePoints=new ArrayDeque<>();
        Point headPoint=new Point(0,0);
        int curDir=0;               // 처음에는 오른쪽 방향으로 이동
        map[0][0]=SNAKE;
        snakePoints.offer(headPoint);

        for (int time=1;;time++){
            // 몸의 길이를 늘려 머리를 다음 칸에 위치시킨다.
            int nextRow=headPoint.r+dx[curDir];
            int nextCol=headPoint.c+dy[curDir];

            // 벽이나 자기 자신의 몸에 부딪히는 경우
            if (!isRange(nextRow,nextCol) || map[nextRow][nextCol]==SNAKE){
                System.out.print(time);
                break;
            }

            // 이동한 칸에 사과가 없는 경우
            if(map[nextRow][nextCol]==BLANK){
                // 몸 길이를 줄인다.
                Point tailPoint=snakePoints.poll();
                map[tailPoint.r][tailPoint.c]=BLANK;
            }
            headPoint=new Point(nextRow,nextCol);
            snakePoints.offer(headPoint);
            map[nextRow][nextCol]=SNAKE;
            // 방향을 전환해야 하는 경우
            for (ChangeInfo info: changeInfoList){
                if (info.time==time){
                    if (info.dir.equals("L")){
                        curDir=(curDir-1)%4;
                    }
                    else if(info.dir.equals("D")){
                        curDir=(curDir+1)%4;
                    }
                    break;
                }
            }
        }

    }
    public static void printMap(){
        for (int row=0;row<N;row++){
            System.out.println(Arrays.toString(map[row]));
        }
    }
    public static boolean isRange(int row, int col){
        return row>=0 && row<N && col>=0 && col<N;
    }
}
