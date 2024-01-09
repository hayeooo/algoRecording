package algo_2024_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 1912 : 연속합
 * n개의 정수로 이루어진 임의의 수열이 주어진다.
 * 우리는 이 중 연속된 몇 개의 수를 선택해서 구할 수 있는 합 중 가장 큰 합을 구하려고 한다.
 * 단 수는 한 개 이상 선택해야 한다.
 *
 * [ 풀이 ]
 * 연속된 수를 선택했을 때, 가장 큰 합을 구하여야 한다.
 * 계산된 누적값의 최댓값을 넣을 지(연속), 연속하지 않고 자신의 값을 선택할지 두 가지 경우가 있다.
 */

public class BOJ_1912_연속합_김하연 {

    static BufferedReader br;
    static StringTokenizer st;

    static int n;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        br=new BufferedReader(new InputStreamReader(System.in));

        // 정수 n을 입력받는다.
        n=Integer.parseInt(br.readLine().trim());

        dp=new int[2][n];
        int maxValue=Integer.MIN_VALUE;

        st=new StringTokenizer(br.readLine().trim());
        for (int idx=0;idx<n;idx++){
            dp[0][idx]=Integer.parseInt(st.nextToken());
            if (idx==0) dp[1][idx]=dp[0][idx];
            maxValue=Math.max(maxValue,dp[0][idx]);
        }
        // 값을 비교하여 넣는다.
        // 0 이면 이전 값을 선택하지 않은 상태
        // 1 이면 이전 값을 포함한 상태
        for (int idx=1;idx<n;idx++){
            dp[1][idx]=Math.max(dp[0][idx-1],dp[1][idx-1])+dp[0][idx];
            maxValue=Math.max(dp[1][idx],maxValue);
        }
        System.out.println(maxValue);
    }
}
