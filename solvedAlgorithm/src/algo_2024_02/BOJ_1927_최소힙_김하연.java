package algo_2024_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1927_최소힙_김하연 {

    static BufferedReader br;
    static StringBuilder sb;
    static int N;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));
        // 연산의 개수를 입력받는다.
        N=Integer.parseInt(br.readLine().trim());

        pq=new PriorityQueue<>();
        sb=new StringBuilder();
        for (int idx=0;idx<N;idx++){
            int x=Integer.parseInt(br.readLine().trim());
            // x가 0이면 가장 작은 값을 출력하고 배열이 비어있는 경우 0을 출력한다.
            if (x==0){
                if(!pq.isEmpty()){
                    sb.append(pq.poll()).append("\n");
                }else{
                    sb.append(0).append("\n");
                }
                continue;
            }
            // 그렇지 않은 경우 값을 넣는다.
            pq.add(x);
        }
        System.out.print(sb);
    }
}
