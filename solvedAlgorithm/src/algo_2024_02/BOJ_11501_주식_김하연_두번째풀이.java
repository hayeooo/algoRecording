package algo_2024_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11501_주식_김하연_두번째풀이 {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int T;

    public static void main(String[] args) throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));

        T=Integer.parseInt(br.readLine().trim());
        sb=new StringBuilder();
        for (int tc=0;tc<T;tc++){
            // 날의 수
            int day=Integer.parseInt(br.readLine().trim());
            int[] cost=new int[day];
            // 주가를 입력받는다.
            st=new StringTokenizer(br.readLine().trim());
            for (int d=0;d<day;d++){
                cost[d]=Integer.parseInt(st.nextToken());
            }

            // 미래부터 주식 가격이 가장 큰 것을 찾아 차익을 더한다.
            long profit=0;
            int maxCost=cost[day-1];
            for (int d=day-2;d>=0;d--){
                if (cost[d]<maxCost) profit+=(maxCost-cost[d]);
                maxCost=Math.max(maxCost,cost[d]);
            }
            sb.append(profit).append("\n");
        }
        System.out.print(sb);
    }
}
