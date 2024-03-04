package algo_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Point{
    int x;
    int y;

    Point(int x,int y){
        this.x=x;
        this.y=y;
    }
}
public class BOJ_14940_최단거리_김하연 {

    private static BufferedReader br;
    private static StringTokenizer st;

    private static StringBuilder sb;
    
    private static int n;       // 세로의 크기
    private static int m;       // 가로의 크기

    private static int[][] map;
    private static int[][] visited;     // 목표지점까지 거리를 저장하는 배열

    private static Point dest;

    private static int[] dx={-1,0,1,0};     // 북, 동, 남, 서
    private static int[] dy={0,1,0,-1};

    public static void main(String[] args) throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));
        st=new StringTokenizer(br.readLine().trim());

        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());

        map=new int[n][m];
        visited=new int[n][m];

        for (int row=0;row<n;row++){
            st=new StringTokenizer(br.readLine().trim());
            for (int col=0;col<m;col++){
                map[row][col]=Integer.parseInt(st.nextToken());
                visited[row][col]=-1;
                if (map[row][col]==2){
                    dest=new Point(row,col);
                }
            }
        }
        
        // 목표지점부터 BFS 시작
        Queue<Point> que=new ArrayDeque<>();
        que.offer(dest);
        visited[dest.x][dest.y]=0;
        while(!que.isEmpty()){
            Point curPoint=que.poll();

            // 다음 위치를 찾는다.
            for (int d=0;d<4;d++){
                int nx=curPoint.x+dx[d];
                int ny=curPoint.y+dy[d];

                // 범위 확인
                if (!isRange(nx,ny)) continue;

                if (visited[nx][ny]>=0) continue;

                // 갈 수 없는 땅인 경우
                if (map[nx][ny]==0)continue;

                // 갈 수 있는 땅인 경우
                visited[nx][ny]=visited[curPoint.x][curPoint.y]+1;
                que.add(new Point(nx,ny));
            }
        }

        sb=new StringBuilder();
        for (int row=0;row<n;row++){
            for (int col=0;col<m;col++){
                if (map[row][col]==0){
                    sb.append(0).append(" ");
                    continue;
                }
                sb.append(visited[row][col]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
    private static boolean isRange(int x,int y){
        return x>=0 && x<n && y>=0 && y<m;
    }
}
