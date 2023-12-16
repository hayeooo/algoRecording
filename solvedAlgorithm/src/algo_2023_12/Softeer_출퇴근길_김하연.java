package algo_2023_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Softeer 출퇴근길
 * [ 문제 ]
 * 동환이의 출퇴근은 단방향 그래프로 나타낼 수 있다. 즉, 각 동네를 1부터 n까지의 번호가 매겨진 n개의 정점으로
 * m개의 일반통행의 도로를 단방향 간선으로 삼아 그래프를 만들 수 있다.
 * 이 때 동환이의 집과 회사가 각각 정점 S와 T로 나타난다고 하면 출퇴근길은 S와 T 사이의 경로로 나타난다.
 * 동환이의 출퇴근길을 본딴 그래프가 주어지면 S에서 T로 가는 출근길 경로와 T에서 S로 가는 퇴근길 경로에 모두 포함될 수 있는 정점의 개수를 세는 프로그램을 작성하시오.
 * 출근길 경로에 T는 마지막에 정확히 한 번만 등장하며, 퇴근길 경로도 마찬가지로 S는 마지막에 한 번만 등장한다.
 *
 * [ 풀이 ]
 * DFS를 사용하여 출근길에 지나친 간선의 개수 정보를 저장하고,
 * 퇴근길에 지나친 간선의 개수가 출근길에 지나친 간선인 경우 결과에 반영한다.
 * : DFS 결과가 도착지에 도착하지 않은 경우와 구분해서 결과에 반영해야 한다.
 *   즉, 유효한 경로에 속한 노드들만 개수로 인정해야 한다.
 *
 * [ 알게된 점 ]
 * DFS를 활용하면서 flag(결과가 유효한지 확인)을 사용하는 법
 *
 *
 */
public class Softeer_출퇴근길_김하연 {

    static BufferedReader br;
    static StringTokenizer st;

    static int n;       // 정점의 개수
    static int m;       // 간선의 개수
    static List<Integer>[] graph;   // 그래프 정보
    
    static int S;       // 집 정보
    static int T;       // 회사 정보

    static boolean[] goVisited;
    static boolean[] comeVisited;
    public static void main(String[] args) throws IOException{
        br=new BufferedReader(new InputStreamReader(System.in));

        // 정점과 간선의 개수를 입력받는다.
        st=new StringTokenizer(br.readLine().trim());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());

        // 그래프 정보를 입력받는다.
        graph=new ArrayList[n+1];
        for (int idx=0;idx<=n;idx++){
            graph[idx]=new ArrayList<>();
        }
        for (int idx=0;idx<m;idx++){
            st=new StringTokenizer(br.readLine().trim());
            int start=Integer.parseInt(st.nextToken());
            int dest=Integer.parseInt(st.nextToken());
            graph[start].add(dest);
        }

        // 집과 회사 정보를 입력받는다.
        st=new StringTokenizer(br.readLine().trim());
        S=Integer.parseInt(st.nextToken());
        T=Integer.parseInt(st.nextToken());

        goVisited=new boolean[n+1];
        comeVisited=new boolean[n+1];
        // 집에서 회사로 출근하는 길에 포함되는 노드 정보를 저장한다.
        goVisited[S]=true;
        goVisited[T]=true;
        go(S,T);
        // 회사에서 집으로 퇴근하는 길에 포함되는 노드 정보를 저장한다.
        comeVisited[S]=true;
        comeVisited[T]=true;
        come(T,S);

        // 중복되는 노드를 구한다.
        int count=0;
        for (int idx=1;idx<=n;idx++){
            if (idx==S || idx==T) continue;
            if (goVisited[idx] && comeVisited[idx]) count++;
        }

        System.out.println(count);
    }
    // 도착지에 도착하는 경로만 포함해야 한다.
    public static boolean go(int start,int dest){
        // 종료 조건
        // 더이상 이동할 노드가 없고, 도착지도 아닌 경우
        if (graph[start].size()==0 && start!=dest) return false;

        boolean valid=false;
        for (int next:graph[start]){
            // 다음 노드가 도착지인 경우
            if (next==dest){
                valid=true;
                continue;
            }
            // 도착이 보장되어 있는 노드인 경우
            if (goVisited[next]){
                valid=true;
                continue;
            }
            goVisited[next]=true;
            // 도착지에 갈 수 없는 경우, 제외시킨다.
            if (!go(next,dest)) goVisited[next]=false;
            else{
                valid=true;
            }
        }
        return valid;
    }

    public static boolean come(int t, int s){
        // 종료조건
        // 더이상 이동할 곳이 없고, 도착지가 아닌 경우
        if (graph[t].size()==0 && t!=s) return false;

        // 다음으로 이동할 노드를 탐색한다.
        boolean valid=false;
        for (int next:graph[t]){
            if (next==s){
                valid=true;
                continue;
            }
            if (comeVisited[next]){
                valid=true;
                continue;
            }
            comeVisited[next]=true;
            if(!come(next,s)) comeVisited[next]=false;
            else valid=true;
        }
        return valid;
    }
}
