package algo_2024_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Node implements Comparable<Node>{
    int region;
    long cost;

    Node(int region,long cost){
        this.region=region;
        this.cost=cost;
    }

    @Override
    public int compareTo(Node other){
        return Long.compare(this.cost,other.cost);
    }
}
public class BOJ_24042_횡단보도_김하연 {

    static BufferedReader br;
    static StringTokenizer st;

    static int N;
    static int M;

    public static void main(String[] args) throws IOException {

        br=new BufferedReader(new InputStreamReader(System.in));
        st=new StringTokenizer(br.readLine().trim());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        List<Node>[] graph=new ArrayList[N+1];

        for (int idx=0;idx<=N;idx++){
            graph[idx]=new ArrayList<>();
        }
        // 순서를 저장해야 한다.
        for (int idx=0;idx<M;idx++){
            st=new StringTokenizer(br.readLine().trim());

            int region1=Integer.parseInt(st.nextToken());
            int region2=Integer.parseInt(st.nextToken());

            graph[region1].add(new Node(region2,idx));
            graph[region2].add(new Node(region1,idx));
        }

        // dijkstra
        long[] distance=new long[N+1];
        Arrays.fill(distance,Long.MAX_VALUE);
        // 1번 지역에서 출발
        distance[1]=0;

        PriorityQueue<Node> pq=new PriorityQueue<>();
        pq.add(new Node(1,0));
        while(!pq.isEmpty()){
            Node curNode=pq.poll();

            // 연결되어 있는 노드를 탐색한다.
            for (Node nextNode:graph[curNode.region]){
                long nextCost;
                if (curNode.cost<nextNode.cost){
                    nextCost=nextNode.cost+1;
                }
                else{
                    nextCost=(long)(Math.ceil((double)(curNode.cost-nextNode.cost)/M)*M)+nextNode.cost+1;
                }
                if (nextCost<distance[nextNode.region]){
                    distance[nextNode.region]=nextCost;
                    pq.add(new Node(nextNode.region,nextCost));
                }
            }
        }
        System.out.println(distance[N]);
    }

}
