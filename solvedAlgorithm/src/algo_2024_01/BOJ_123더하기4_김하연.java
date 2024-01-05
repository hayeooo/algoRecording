package algo_2024_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ : 1, 2, 3 더하기 4
 *
 * [ 문제 ]
 * 정수 4를 1, 2, 3의 합으로 나타내는 방법은 총 4가지가 있다.
 * 합을 나타낼 때는 수를 1개 이상 사용해야 한다.
 * 합을 이루고 있는 수의 순서만 다른 것은 같은 것으로 친다.
 *
 * 1+1+1+1
 * 2+1+1
 * 2+2
 * 1+3
 *
 * 정수 n이 주어졌을 때, n을 1,2,3의 합으로 나타내는 방법의 수를 구하는 프로그램을 작성하시오
 *
 * [ 풀이 ]
 * 이전 숫자의 방법의 수를 토대로 다음 큰 숫자의 방법의 수를 계산할 수 있겠다는 원리로 DP를 생각했다.
 * 하지만, 합을 이루고 있는 수가 중복된다는 문제를 발견하였다.
 * 합을 구하는 방법이 오름차순임을 보장하는 dp여야 한다.
 * dp[합의 크기][1 or 2 or 3이 맨 뒤로 나올 때] = 계산 방법의 수
 */
public class BOJ_123더하기4_김하연 {

    static BufferedReader br;
    static StringBuilder sb;


    static int T;           // 테스트 케이스 개수

    public static void main(String[] args) throws IOException {

        br=new BufferedReader(new InputStreamReader(System.in));

        T=Integer.parseInt(br.readLine().trim());
        sb=new StringBuilder();

        int[][] dp=new int[10001][4];
        dp[1][1]=1;
        dp[2][1]=1;
        dp[2][2]=1;
        dp[3][1]=1;
        dp[3][2]=1;
        dp[3][3]=1;

        for (int num=4;num<=10000;num++){
            dp[num][1]=dp[num-1][1];                // 마지막 연산 숫자가 1인 경우
            dp[num][2]=dp[num-2][1]+dp[num-2][2];   // 마지막 연산 숫자가 2인 경우
            dp[num][3]=dp[num-3][1]+dp[num-3][2]+dp[num-3][3];  // 마지막 연산 숫자가 3인 경우
        }

        for (int tc=0;tc<T;tc++){
            int n=Integer.parseInt(br.readLine().trim());
            sb.append(dp[n][1]+dp[n][2]+dp[n][3]).append("\n");
        }
        System.out.print(sb);
    }
}
