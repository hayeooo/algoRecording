package algo_2024_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BOJ 1446: 지름길
 * 매일 아침, 세준이는 학교에 가기 위해서 차를 타고 D킬로미터 길이의 고속도로를 지난다.
 * 세준이는 이 고속도로에 지름길이 존재한다는 것을 알게 되었다. 모든 지름길은 일방통행이고, 고속도로를 역주행할 수는 없다.
 * 세준이가 운전해야 하는 거리의 최솟값을 출력하시오.
 *
 * [ 문제 풀이 ]
 * 해당 위치일 경우 최솟값을 저장하는 배열을 활용한다(dp)
 * 해당 위치에서 출발할 경우(x) 해당 위치에 도착한 경우 최솟값
 * 도착 지점을 중심으로 저장해야 한다.
 */
public class BOJ_1446_지름길_김하연 {

    static BufferedReader br;
    static StringTokenizer st;
    
    static int N;           // 지름길의 개수
    static int D;           // 고속도로의 길이
    static int[] dp;
    static List<Road>[] path;
    static int[] dist;

    static class Road{
        int start;
        int weight;

        Road(int start,int weight){
            this.start=start;
            this.weight=weight;
        }
    }
    public static void main(String[] args) throws IOException{

        br=new BufferedReader(new InputStreamReader(System.in));

        // 지름길의 개수(N)과 고속도로의 길이(D)를 입력받는다.
        st=new StringTokenizer(br.readLine().trim());
        N=Integer.parseInt(st.nextToken());
        D=Integer.parseInt(st.nextToken());

        path=new ArrayList[10001];
        for (int idx=0;idx<10001;idx++){
            path[idx]=new ArrayList<Road>();
        }
        // 지름길 정보를 입력받는다.
        for (int idx=0;idx<N;idx++){
            st=new StringTokenizer(br.readLine().trim());
            int start=Integer.parseInt(st.nextToken());
            int end=Integer.parseInt(st.nextToken());
            int weight=Integer.parseInt(st.nextToken());
            
            // 지름길인 경우 저장
            if (end-start>weight){
                path[end].add(new Road(start,weight));
            }
            
        }
        dist=new int[D+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[0]=0;
        
        // 해당 인덱스가 도착지점일 때 최솟값을 구한다.
        for (int idx=1;idx<=D;idx++){
            // 지름길이 존재하는 경우
            if (path[idx].size()>0){
                for (Road road:path[idx]){
                    // 같은 지름길 중에 값이 더 큰 경우 continue
                    if (dist[road.start]+road.weight>dist[idx]) continue;
                    // 최솟값이 될 수 있는 경우
                    dist[idx]=Math.min(dist[road.start]+road.weight,dist[idx-1]+1);
                }
            }
            else{
                dist[idx]=dist[idx-1]+1;
            }
        }
        System.out.println(dist[D]);
    }
}
