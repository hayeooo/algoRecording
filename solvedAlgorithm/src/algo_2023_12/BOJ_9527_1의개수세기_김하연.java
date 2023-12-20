package algo_2023_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 9527: 1의 개수 세기
 * [ 문제 ]
 * 두 자연수 A,B가 주어졌을 때 A<=x<=B를 만족하는 모든 x에 대해 x를 이진수로 표현했을 때 1의 개수의 합을 구하는 프로그램을 작성하시오.
 * f(x)=x를 이진수로 표현했을 때 1의 개수라고 정의한다.
 * 1<=A<=B<=10^16
 *
 * [ 풀이 ]
 * 처음에 dp 1차원 배열을 만들어 풀려고 했지만 최악의 경우 10^16이므로 시간초과된다.
 *
 */
public class BOJ_9527_1의개수세기_김하연 {

    static BufferedReader br;
    static StringTokenizer st;

    static long A;
    static long B;

    static long[] dp=new long[55];

    public static void main(String[] args) throws IOException {

        br=new BufferedReader(new InputStreamReader(System.in));
        st=new StringTokenizer(br.readLine().trim());

        A=Long.parseLong(st.nextToken());
        B=Long.parseLong(st.nextToken());

        setDp();
        long result=calOne(B)-calOne(A-1);
        System.out.println(result);


    }
    // 1~N 정수에 대한 1의 개수 구하는 함수
    static long calOne(long N){
        long count=N&1;
        // N보다 작은 2^n의 n의 최댓값
        int size=(int)(Math.log(N)/Math.log(2));
        // N-(1L<<i) : 지정된 1이 반복 사용될 개수
        // + 1 :1000 ..
        for (int i=size;i>0;i--){
            // 첫 번째 비트가 있을 경우
            if ((N&(1L<<i))!=0L){
                count+=dp[i-1]+(N-(1L<<i)+1);
                N-=(1L<<i);
            }
        }
        return count;
    }
    public static void setDp(){
        dp[0]=1;
        // 점화식 적용
        // DP[i-1]<<1 : DP[n-1]*2
        // 1L << i : 2^i
        for (int i=1;i<55;i++){
            dp[i]=(dp[i-1]<<1)+(1L<<i);
        }
    }
}
