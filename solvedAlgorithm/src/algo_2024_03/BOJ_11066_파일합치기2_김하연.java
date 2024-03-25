package algo_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11066_파일합치기2_김하연 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int[][] dp;
    static int[] files;
    static int T;

    public static void main(String[] args) throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));
        T=Integer.parseInt(br.readLine().trim());

        sb=new StringBuilder();

        for (int tc=0;tc<T;tc++){
            int K=Integer.parseInt(br.readLine().trim());
            files=new int[K+1];
            dp=new int[K+1][K+1];

            st=new StringTokenizer(br.readLine().trim());
            for (int idx=1;idx<=K;idx++){
                files[idx]=Integer.parseInt(st.nextToken())+files[idx-1];
            }
            // 가장 작은 연속 파일 합부터 구해나간다.
            for (int gap=1;gap<K;gap++){
                for (int start=1;start+gap<=K;start++){
                    int end=start+gap;
                    dp[start][end]=Integer.MAX_VALUE;
                    // 두 개의 묶음으로 나눈다.
                    for (int mid=start;mid<end;mid++){
                        dp[start][end]=Math.min(dp[start][end],dp[start][mid]+dp[mid+1][end]+files[end]-files[start-1]);
                    }
                }
            }
            sb.append(dp[1][K]).append("\n");
        }
        System.out.print(sb);
    }
}
