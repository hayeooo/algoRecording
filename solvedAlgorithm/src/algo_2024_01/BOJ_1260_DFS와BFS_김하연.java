package algo_2024_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ 1260: DFS와 BFS
 *
 * [ 문제 ]
 * 그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오.
 * 단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고,
 * 더 이상 방문할 수 있는 점이 없는 경우 종료한다.
 * 정점 번호는 1번부터 N번까지이다.
 *
 * [ 문제 풀이 ]
 * 간선이 연결하는 두 정점의 번호를 인접 행렬 형태로 저장하여 정점 번호가 작은 것 먼저 방문하도록 한다.
 */
public class BOJ_1260_DFS와BFS_김하연 {

    static BufferedReader br;
    static StringTokenizer st;
    static int N;       // 정점의 개수
    static int M;       // 간선의 개수
    static int V;       // 탐색을 시작할 정점의 번호

    static boolean[][] adj;
    static boolean[] visited;

    static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        br=new BufferedReader(new InputStreamReader(System.in));

        // 정점의 개수, 간선의 개수, 탐색을 시작할 정점의 번호를 입력받는다.
        st=new StringTokenizer(br.readLine().trim());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        V=Integer.parseInt(st.nextToken());

        adj=new boolean[N+1][N+1];

        // 간선의 정보를 입력받는다.(양방향)
        for (int idx=0;idx<M;idx++){
            st=new StringTokenizer(br.readLine().trim());
            int node1=Integer.parseInt(st.nextToken());
            int node2=Integer.parseInt(st.nextToken());

            adj[node1][node2]=true;
            adj[node2][node1]=true;
        }
        sb=new StringBuilder();

        visited=new boolean[N+1];
        dfs(V);
        sb.append("\n");

        bfs(V);
        System.out.print(sb);
    }
    public static void bfs(int start){
        visited=new boolean[N+1];
        Queue<Integer> que=new ArrayDeque<>();

        que.offer(start);
        visited[start]=true;

        while (!que.isEmpty()){
            int cur=que.poll();
            sb.append(cur).append(" ");

            // 인접한 노드들 탐색
            for (int next=1;next<=N;next++){
                // 이미 방문한 곳은 pass
                if(visited[next]) continue;
                // 인접하지 않은 곳은 pass
                if (!adj[cur][next]) continue;
                que.offer(next);
                visited[next]=true;
            }
        }
    }

    public static void dfs(int node){
        if (visited[node]) return;

        visited[node]=true;
        sb.append(node).append(" ");

        for (int next=1;next<=N;next++){
            if (visited[next]) continue;
            if (!adj[node][next]) continue;
            dfs(next);
        }
    }
}
