package algo_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_11279_최대힙_김하연 {
    static BufferedReader br;
    static StringBuilder sb;
    static int N;

    public static void main(String[] args) throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));
        
        // 연산의 개수
        N=Integer.parseInt(br.readLine().trim());

        PriorityQueue<Integer> pq=new PriorityQueue<>();
        sb=new StringBuilder();

        // PriorityQueue는 기본적으로 최소힙이므로
        // 최대힙을 위해 음수로 만들어서 저장한다.
        for (int idx=0;idx<N;idx++){
            int num=Integer.parseInt(br.readLine().trim());

            if (num>0){
                pq.add(num*(-1));
            }
            else if(num==0){
                if (pq.isEmpty()){
                    sb.append(0).append("\n");
                    continue;
                }
                sb.append(pq.poll()*(-1)).append("\n");
            }
        }
        System.out.print(sb);
    }
}
