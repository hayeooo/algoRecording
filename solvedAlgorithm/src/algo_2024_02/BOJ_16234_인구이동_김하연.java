package algo_2024_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Country{
    int row;
    int col;

    Country(int row,int col){
        this.row=row;
        this.col=col;
    }

}
public class BOJ_16234_인구이동_김하연{

    static BufferedReader br;
    static StringTokenizer st;
    static int N;       // N*N 땅의 크기
    static int L;       // 두 나라의 인구 차이 L명 이상
    static int R;       // R명 이하
    static int[][] map;
    static boolean[][] visited;

    static List<Country> connected;
    static int[] dx={-1,0,1,0};     // 북, 동, 남, 서
    static int[] dy={0,1,0,-1};

    public static void main(String[] args) throws IOException {

        br=new BufferedReader(new InputStreamReader(System.in));
        st=new StringTokenizer(br.readLine().trim());

        N=Integer.parseInt(st.nextToken());
        L=Integer.parseInt(st.nextToken());
        R=Integer.parseInt(st.nextToken());

        map=new int[N][N];
        for (int row=0;row<N;row++){
            st=new StringTokenizer(br.readLine().trim());
            for (int col=0;col<N;col++){
                map[row][col]=Integer.parseInt(st.nextToken());
            }
        }
        move();

    }

    public static void move() { 

        for (int day=0;;day++) {
            boolean isMove = false;
            visited = new boolean[N][N];
            for(int row = 0; row < N; row++) {
                for(int col = 0; col < N; col++) {
                    if(!visited[row][col]) {
                        int sum = bfs(row, col); //bfs탐색으로 열릴 수 있는 국경선 확인 하며 인구 이동할 총 인구수 반환
                        if(connected.size() > 1) {
                            for (Country c:connected){
                                map[c.row][c.col]=sum/connected.size();
                            }
                            isMove = true;
                        }
                    }
                }
            }
            if(!isMove) {
                System.out.println(day);
                break;
            }
        }
    }

    public static int bfs(int row,int col){
        // BFS 시작
        Queue<Country> que=new ArrayDeque<>();
        connected=new ArrayList<>();
        Country country=new Country(row,col);
        que.offer(country);
        connected.add(country);
        visited[row][col]=true;

        int sum=map[row][col];
        while (!que.isEmpty()){
            Country now=que.poll();
            for (int d=0;d<4;d++){
                int nr=now.row+dx[d];
                int nc=now.col+dy[d];
                if (!isRange(nr,nc)) continue;
                if (visited[nr][nc]) continue;

                int diff=Math.abs(map[now.row][now.col]-map[nr][nc]);
                if (diff>=L && diff<=R){
                    Country connectedCountry=new Country(nr,nc);
                    que.offer(connectedCountry);
                    connected.add(connectedCountry);
                    sum+=map[nr][nc];
                    visited[nr][nc]=true;
                }
            }
        }
        return sum;
    }
    public static boolean isRange(int r,int c){
        return r>=0 && r<N && c>=0 && c<N;
    }

}
