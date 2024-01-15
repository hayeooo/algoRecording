package algo_2024_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ 10844 : 쉬운 계단 수
 * [ 문제 ]
 *  45656이란 수를 보자.
 *  이 수는 인접한 모든 자리의 차이가 1이다. 이런 수를 계단 수라고 한다.
 *  N이 주어질 때, 길이가 N인 계단 수가 총 몇 개 있는지 구해보자. 0으로 시작하는 수는 계단 수가 아니다.
 *
 *  [ 풀이 ]
 *  0으로 시작하는 수는 계단 수가 아닌 것을 주의하여 이전에 계산된 값으로 미래의 값을 구한다.(dp)
 *  0으로 시작하는 수를 고려하여 점화식을 세우는 것보다 마지막 수를 기준으로 점화식을 세우는 것이 더 직관적이다.
 *  시작하는 수로 해도 상관없긴 함! : 덧셈 과정에서 MOD 수행해야함 => 안해서 틀림
 */
public class BOJ_10844_쉬운계단수_김하연 {

    static BufferedReader br;
    static int N;
    static int DIVIDE=1_000_000_000;

    static long[][] dp;
    
    public static void main(String[] args) throws IOException {

        br=new BufferedReader(new InputStreamReader(System.in));

        N=Integer.parseInt(br.readLine().trim());

        dp=new long[N+1][10];

        for (int idx=1;idx<=9;idx++){
            dp[1][idx]=1;
        }

        for (int n=2;n<=N;n++){
            for (int end=0;end<=9;end++){

                // 마지막 수가 0이라면
                if (end==0){
                    // 무조건 1이 앞에 나와야 한다.
                    dp[n][end]=dp[n-1][1]%DIVIDE;
                }
                // 마지막 수가 9라면
                else if(end==9){
                    // 무조건 8이 앞에 나와야 한다.
                    dp[n][end]=dp[n-1][8]%DIVIDE;
                }
                // 그 외의 경우
                else{
                    dp[n][end]=(dp[n-1][end-1]+dp[n-1][end+1])%DIVIDE;
                }
            }
        }
        int sum=0;
        for (int idx=0;idx<=9;idx++){
            sum+=dp[N][idx];
            sum%=DIVIDE;
        }
        System.out.println(sum);

    }
}
