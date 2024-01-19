package algo_2024_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [ 문제 ]
 * 수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점(0<=N<=100,000)에 있고,
 * 동생은 점 K(0<=K<=100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다.
 * 만약 수빈이의 위치가 X일 때 걷는다면 X-1 또는 X+1로 이동하게 된다.
 * 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
 *
 * 수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.
 */
public class BOJ_1697_숨바꼭질_김하연 {

    static BufferedReader br;
    static StringTokenizer st;
    static int N;       // 수빈이가 있는 위치
    static int K;       // 동생이 있는 위치

    static int[] visited;
    public static void main(String[] args) throws IOException {

        br=new BufferedReader(new InputStreamReader(System.in));

        st=new StringTokenizer(br.readLine().trim());

        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        visited=new int[100001];


        Queue<Integer> que=new ArrayDeque<>();
        visited[N]=1;
        que.offer(N);

        while(!que.isEmpty()){
            int cur=que.poll();

            // 이동할 수 있는 칸 고려
            if (isRange(cur-1) && visited[cur-1]==0){
                visited[cur-1]=visited[cur]+1;
                que.offer(cur-1);
            }
            if (isRange(cur+1) && visited[cur+1]==0){
                visited[cur+1]=visited[cur]+1;
                que.offer(cur+1);
            }
            if (isRange(cur*2) && visited[2*cur]==0){
                visited[cur*2]=visited[cur]+1;
                que.offer(cur*2);
            }

        }

        System.out.println(visited[K]-1);
    }

    public static boolean isRange(int node){
        return node>=0 && node<=100000;
    }


}
