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
 * 규칙이 있는 점화식을 활용하여 시간복잡도를 log만큼 줄여야 한다.
 *
 */
public class BOJ_9527_1의개수세기_김하연 {

    static BufferedReader br;
    static StringTokenizer st;

    static long A;
    static long B;

    static long[] dp;

    public static void main(String[] args) throws IOException{
        dp=new long[55];

        br=new BufferedReader(new InputStreamReader(System.in));
        st=new StringTokenizer(br.readLine().trim());

        A=Long.parseLong(st.nextToken());
        B=Long.parseLong(st.nextToken());

        setDp();

        long result=calOne(B)-calOne(A-1);
        System.out.print(result);

    }

    public static long calOne(long N){
        long count=N&1;
        int size=(int)(Math.log(N)/Math.log(2));
        //System.out.println(size);
        for (int i=size;i>0;i--){
            // 첫번째 자리가 1이면
            if ((N&(1L<<i))!=0L){
                count+=dp[i-1]+(N-(1L<<i))+1;
                N-=(1L<<i);
            }
        }
        return count;
    }
    public static void setDp(){
        dp[0]=1;
        for (int i=1;i<55;i++){
            dp[i]=(dp[i-1]<<1)+(1L<<i);
        }
    }
}
