package algo_2024_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11501_주식_김하연 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int T;

    public static void main(String[] args) throws IOException {

        br=new BufferedReader(new InputStreamReader(System.in));
        T=Integer.parseInt(br.readLine().trim());
        sb=new StringBuilder();
        for (int tc=0;tc<T;tc++){
            int N=Integer.parseInt(br.readLine().trim());
            int[] day=new int[N];
            st=new StringTokenizer(br.readLine().trim());
            for (int d=0;d<N;d++){
                day[d]=Integer.parseInt(st.nextToken());
            }

            int maxCost=day[N-1];
            long totalProfit=0;
            // 끝에서부터 탐색한다.
            for (int idx=N-2;idx>=0;idx--){
                // maxCost > 오늘 주식 : 주식을 판다.
                if (maxCost>=day[idx]){
                    totalProfit+=(maxCost-day[idx]);
                }
                // maxCost < 오늘 주식 : maxCost를 변경한다.
                else{
                    maxCost=day[idx];
                }
            }
            sb.append(totalProfit).append("\n");
        }
        System.out.print(sb);
    }
}
