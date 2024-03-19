package algo_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11066_파일합치기_김하연 {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int T;
    static int[] files;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));

        T=Integer.parseInt(br.readLine().trim());
        sb=new StringBuilder();
        for (int tc=0;tc<T;tc++){
            int K=Integer.parseInt(br.readLine().trim());

            files=new int[K+1];
            dp=new int[K+1][K+1];

            // 연속된 합을 구하기 위해 파일의 누적합을 구한다.
            st=new StringTokenizer(br.readLine().trim());
            for (int idx=1;idx<=K;idx++){
                int fileSize=Integer.parseInt(st.nextToken());
                files[idx]=files[idx-1]+fileSize;
            }

            for (int gap=1;gap<K;gap++){
                for (int start=1;start+gap<=K;start++){
                    int end=start+gap;
                    dp[start][end]=Integer.MAX_VALUE;
                    for (int mid=start;mid<start+gap;mid++){
                        dp[start][end]=Math.min(dp[start][end],dp[start][mid]+dp[mid+1][end]+files[end]-files[start-1]);
                    }
                }
            }
            sb.append(dp[1][K]).append("\n");
        }
        System.out.print(sb);
    }
}
