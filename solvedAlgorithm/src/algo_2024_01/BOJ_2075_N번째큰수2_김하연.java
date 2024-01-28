package algo_2024_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 우선순위 큐를 활용하여 문제를 해결할 수 있다.
 */
public class BOJ_2075_N번째큰수2_김하연 {

    static BufferedReader br;
    static StringTokenizer st;

    static int N;

    public static void main(String[] args) throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine().trim());

        PriorityQueue<Integer> pq=new PriorityQueue<>();

        for (int row=0;row<N;row++){
            st=new StringTokenizer(br.readLine().trim());
            for (int col=0;col<N;col++){
                // 내림차순으로 저장
                pq.offer((-1)*Integer.parseInt(st.nextToken()));
            }
        }

        for (int idx=0;idx<N-1;idx++){
            pq.poll();
        }
        System.out.println(pq.poll()*(-1));
    }
}
