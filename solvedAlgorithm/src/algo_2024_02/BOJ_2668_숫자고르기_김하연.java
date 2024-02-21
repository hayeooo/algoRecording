package algo_2024_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ_2668_숫자고르기_김하연 {

    static BufferedReader br;
    static StringBuilder sb;
    static int N;
    static int[] graph;
    static boolean[] visited;
    static List<Integer> connectList;

    public static void main(String[] args) throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));

        N=Integer.parseInt(br.readLine().trim());

        graph=new int[N+1];
        for (int idx=1;idx<=N;idx++){
            int node=Integer.parseInt(br.readLine().trim());
            graph[idx]=node;
        }

        // 1번부터 N번까지 탐색
        visited=new boolean[N+1];
        connectList=new ArrayList<Integer>();
        sb=new StringBuilder();

        for (int node=1;node<=N;node++){
            visited[node]=true;
            // 현재 노드를 출발지점으로 사이클이 발생하는지 확인
            dfs(node,node);
            visited[node]=false;
        }

        Collections.sort(connectList);
        sb.append(connectList.size()).append("\n");
        for (int node:connectList){
            sb.append(node).append("\n");
        }
        System.out.print(sb);
    }

    // 출발 지점과 동일한 지점으로 돌아올 시 두 집합이 일치
    public static void dfs(int start,int target){
        // 연결된 노드가 방문되지 않았을 경우, 인접한 노드로 탐색
        if(!visited[graph[start]]){
            visited[graph[start]]=true;
            dfs(graph[start],target);
            visited[graph[start]]=false;
        }
        if (graph[start]==target){
            connectList.add(graph[start]);
        }
    }
}
