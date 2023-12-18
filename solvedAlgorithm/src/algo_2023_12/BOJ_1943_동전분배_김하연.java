package algo_2023_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * BOJ 1943: 동전 분배
 * [ 문제 ]
 * 원장 선생님께서 N가지 종류의 동전을 각각 몇 개씩 주셨을 때, 그 돈을 반으로 나눌 수 있는지 없는지 판단한다.
 *
 * [ 풀이 ]
 * 동전 금액의 합을 구한 후, 반으로 나눈다.
 * 그 값이 주어진 동전으로 만들어질 수 있는지 점검한다. ( Greedy algorithm )
 * 동전으로 동전 금액의 합을 반으로 나눈 금액을 만들 수 있다면 나머지 동전도 그 금액으로 만들 수 있다.
 * : 10%에서 실패 뜸
 * : 반례 데이터가 어떤 게 있는지 몰라서 일단 질문 게시판에 글 올려둠
 *
 * 구글링을 해보니까 dp로 푼다고 한다.
 * = 그리디로 풀 수 없는 데이터 셋이 존재한다는 뜻
 *
 */
public class BOJ_1943_동전분배_김하연 {
    static BufferedReader br;
    static StringTokenizer st;

    static StringBuilder sb;
    public static void main(String[] args) throws IOException{
        br=new BufferedReader(new InputStreamReader(System.in));
        sb=new StringBuilder();
        for (int tc=0;tc<3;tc++){
            int N=Integer.parseInt(br.readLine().trim());
            int[] coins=new int[N];
            int[] quantity=new int[N];
            boolean[] dp=new boolean[100000+1];     // i원을 동전으로 만들 수 있는지

            int sum=0;

            for (int idx=0;idx<N;idx++){
                st=new StringTokenizer(br.readLine().trim());
                int value=Integer.parseInt(st.nextToken());
                int count=Integer.parseInt(st.nextToken());

                coins[idx]=value;
                quantity[idx]=count;
                sum+=(value*count);
                for (int q=1;q<=count;q++){
                    dp[value*q]=true;
                }
            }

            // 홀수인 경우
            if (sum%2==1){
                sb.append(0).append("\n");
                continue;
            }else if (dp[sum/2]){
                sb.append(1).append("\n");
                continue;
            }

            // 각 동전이 만들 수 있는 가격을 표시한다.
            for (int idx=0;idx<N;idx++){
                int coin=coins[idx];
                int cnt=quantity[idx];
                // sum/2부터 감소하면서 dp 배열을 채워나가는 경우
                // 오름차순으로 dp 배열을 채워나가면 앞 순서에 true로 표시한 값이 중복되어 값이 갱신된다.
                // 주어진 동전 개수보다 더 많이 사용하게 된다.
                for (int value=sum/2;value>=coin;value--){      // dp[sum/2]==true이면 돈의 총합을 반으로 나눌 수 있다.
                    if (dp[value-coin]){
                        for (int q=1;q<=cnt;q++){
                            if (value-coin+q*coin>sum/2){
                                break;
                            }
                            dp[value-coin+q*coin]=true;
                        }
                    }
                }
            }
            sb.append(dp[sum/2]?1:0).append("\n");
        }
        System.out.print(sb);
    }
}
