package algo_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 01 Knapsack
public class BOJ_12865_평범한배낭_김하연 {

    static BufferedReader br;
    static StringTokenizer st;
    static int[][] dp;
    static int N;
    static int K;

    public static void main(String[] args) throws IOException {

        br=new BufferedReader(new InputStreamReader(System.in));
        st=new StringTokenizer(br.readLine().trim());

        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        dp=new int[N+1][K+1];
        for (int item=1;item<=N;item++){
            st=new StringTokenizer(br.readLine().trim());
            int W=Integer.parseInt(st.nextToken());
            int V=Integer.parseInt(st.nextToken());

            for (int weight=W;weight<=K;weight++){
                dp[item][weight]=Math.max(dp[item-1][weight-W]+V,dp[item-1][weight]);
            }
        }
        System.out.println(dp[N][K]);
    }
}
