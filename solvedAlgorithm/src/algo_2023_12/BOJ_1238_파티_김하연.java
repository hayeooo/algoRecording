package algo_2023_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BOJ 2631: 줄세우기
 *
 * [ 문제 ]
 * N 개의 숫자로 구분된 각각의 마을에 한 명의 학생이 살고 있다.
 * 어느 날 이 N명의 학생이 X(1<=X<=N)번 마을에 모여서 파티를 벌이기로 했다.
 * 이 마을 사이에는 총 M개의 단방향 도로들이 있고 i번째 길을 지나는데 T의 시간을 소비한다.
 * 각각의 학생들은 파티에 참석하기 위해 걸어가서 다시 그들의 마을로 돌아와야 한다.
 * 도로들은 단방향이기 때문에 그들이 오고 가는 길이 다를지도 모른다.
 * N명의 학생들 중 오고 가는데 가장 많은 시간을 소비하는 학생은 누구일지 구하여라
 *
 * [ 풀이 ]
 * 배열은 인접리스트 형태로 저장한다.
 * Dijkstra algorithm
 * 출발점에서 도착까지 최단거리를 구하는 알고리즘 / 그래프의 최단 경로 구하는 알고리즘
 * 하나의 정점에서 출발하는 최단 거리를 구함
 * 음수 가중치 없어야 함
 * 인접 행렬로 표현된 그래프의 경우 시간 복잡도 O(n^2)
 * 우선순위 큐 사용하여 시간 복잡도 O(mlogn)까지 낮출 수 있다.
 * 출발지점에서 각 정점까지의 최단 거리를 배열에 저장하고, 미방문 노드 중 최단 거리일 경우 그 값을 갱신한다.
 */
public class BOJ_1238_파티_김하연 {

    static BufferedReader br;
    static StringTokenizer st;

    static int N;       // N명의 학생
    static int M;       // M개의 단방향 도로
    static int X;       // 도착지
    
    static List<Node>[] graph;

    static class Node implements Comparable<Node>{
        int num;
        int cost;

        Node(int num,int cost){
            this.num=num;
            this.cost=cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost,o.cost);
        }
    }
    public static void main(String[] args) throws IOException {

        br=new BufferedReader(new InputStreamReader((System.in)));

        // N, M, X의 정보를 입력받는다.
        st=new StringTokenizer(br.readLine().trim());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());

        graph=new ArrayList[N+1];

        for (int idx=0;idx<=N;idx++){
            graph[idx]=new ArrayList<>();
        }
        // 간선 정보를 입력받는다.
        for (int idx=0;idx<M;idx++){
            st=new StringTokenizer(br.readLine().trim());
            int start=Integer.parseInt(st.nextToken());
            int end=Integer.parseInt(st.nextToken());
            int time=Integer.parseInt(st.nextToken());

            graph[start].add(new Node(end,time));
        }
        int maxTime=0;
        for (int n=1;n<=N;n++){
            if (n==X) continue;
            int goTime=dijkstra(n,X);
            int comeTime=dijkstra(X,n);
            maxTime=Math.max(maxTime,goTime+comeTime);
        }
        System.out.println(maxTime);
    }

    public static int dijkstra(int start, int end){

        boolean[] visited=new boolean[N+1];
        int[] dist=new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> pq=new PriorityQueue<>();
        pq.offer(new Node(start,0));
        dist[start]=0;

        while (!pq.isEmpty()){
            Node curNode=pq.poll();

            // 방문 표시
            visited[curNode.num]=true;

            // 미방문 노드 중 최단시간인 경우 업데이트
            for (Node nextNode:graph[curNode.num]){
                if (!visited[nextNode.num] && dist[nextNode.num]>dist[curNode.num]+nextNode.cost){
                    dist[nextNode.num]=dist[curNode.num]+nextNode.cost;
                    pq.add(new Node(nextNode.num,dist[nextNode.num]));
                }
            }
        }
        return dist[end];
    }
}
