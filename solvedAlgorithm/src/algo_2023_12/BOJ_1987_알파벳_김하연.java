package algo_2023_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 1998 : 알파벳
 * 세로 R칸, 가로 C칸으로 된 표 모양의 보드가 있다.
 * 보드의 각 칸에는 대문자 알파벳이 하나씩 적혀 있고, 좌측 상단 칸(1행 1열)에는 말이 놓여 있다.
 * 말은 상하좌우로 인접한 네 칸 중의 한 칸으로 이동할 수 있는데,
 * 새로 이동한 칸에 적혀 있는 알파벳은 지금까지 지나온 모든 칸에 적혀있는 알파벳과는 달라야 한다.
 * 즉, 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.
 * 좌측 상단에서 시작해서, 말이 최대한 몇 칸을 지날 수 있는지를 구하는 프로그램을 작성하시옹.
 * 말이 지나는 칸은 좌측 상단의 칸도 포함된다.
 *
 * << 풀이 방법 >>
 * 조건이 추가된 DFS
 * 조건?
 * 1. 이미 방문한 곳은 가지 않는다.
 * 2. 이미 지나온 알파벳에 가지 않는다.
 */
public class BOJ_1987_알파벳_김하연 {

    static BufferedReader br;
    static StringTokenizer st;

    private static int R;       // 세로
    private static int C;       // 가로

    static char[][] alphas;
    static boolean[] checked=new boolean[26];
    static boolean[][] visited;

    static int[] dx={-1,0,1,0};     // 북, 동, 남, 서
    static int[] dy={0,1,0,-1};

    static int maxValue=0;

    public static void main(String[] args) throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));
        st=new StringTokenizer(br.readLine().trim());

        // 세로, 가로의 값을 입력받는다.
        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());

        alphas=new char[R][C];
        visited=new boolean[R][C];
        // 대문자 알파벳을 입력받는다.
        for (int r=0;r<R;r++){
            String line=br.readLine().trim();
            for (int c=0;c<C;c++){
                alphas[r][c]=line.charAt(c);
            }
        }
        
        // DFS를 구한다.
        visited[0][0]=true;
        checked[alphas[0][0]-'A']=true;
        dfs(0,0,1);

        System.out.print(maxValue);
    }
    public static void dfs(int x,int y,int cnt){
        maxValue=Math.max(maxValue,cnt);
        //System.out.println("현재 위치"+x+","+y);
        for (int d=0;d<dx.length;d++){
            // 다음 방향
            int nx=x+dx[d];
            int ny=y+dy[d];

            if (nx<0|| nx>=R || ny<0 || ny>=C){
                continue;
            }

            if (visited[nx][ny]){
                continue;
            }

            if (checked[alphas[nx][ny]-'A']){
                continue;
            }
            visited[nx][ny]=true;
            checked[alphas[nx][ny]-'A']=true;
            dfs(nx,ny,cnt+1);
            visited[nx][ny]=false;
            checked[alphas[nx][ny]-'A']=false;
        }

    }
}
