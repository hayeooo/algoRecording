package algo_2023_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ 2631: 줄세우기
 * 이분 탐색 방법 말고 동적계획법으로 풀어보자
 * 시간복잡도: O(n^2) 76ms
 */
public class BOJ_2631_줄세우기_DP_김하연 {

    static BufferedReader br;

    static int N;
    static int[] numbers;
    static int[] dp;
    static int LIS=0;
    public static void main(String[] args) throws IOException {

        br=new BufferedReader(new InputStreamReader(System.in));

        // 아이들의 수를 입력받는다.
        N=Integer.parseInt(br.readLine().trim());

        numbers=new int[N];
        dp=new int[N];

        for (int idx=0;idx<N;idx++){
            numbers[idx]=Integer.parseInt(br.readLine().trim());
        }

        for (int cur=0;cur<N;cur++){
            // 자기 자신의 길이는 기본적으로 1이다.
            dp[cur]=1;

            // 이전 값들 중에 자신보다 작은 숫자의 최장증가수열 길이에 자기 자신을 더한 값과 기존 값 중 큰 값을 저장한다.
            for (int prev=0;prev<cur;prev++){
                if (numbers[prev]<numbers[cur]){
                    dp[cur]=Math.max(dp[cur],dp[prev]+1);
                }
            }
            LIS=Math.max(dp[cur],LIS);
        }

        System.out.println(N-LIS);

    }

}
