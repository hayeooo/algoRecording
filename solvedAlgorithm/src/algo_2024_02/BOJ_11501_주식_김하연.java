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
            int[] arr=new int[N];
            st=new StringTokenizer(br.readLine().trim());
            for (int day=0;day<N;day++){
                arr[day]=Integer.parseInt(st.nextToken());
            }
            long totalProfit=0;
            int max=arr[N-1];
            for (int day=N-2;day>=0;day--){
                // 미래 주가가 더 높은 경우
                if (max>=arr[day]){
                    totalProfit+=(max-arr[day]);
                }
                else{
                    max=arr[day];
                }
            }
            sb.append(totalProfit).append("\n");
        }
        System.out.print(sb);
    }
}
