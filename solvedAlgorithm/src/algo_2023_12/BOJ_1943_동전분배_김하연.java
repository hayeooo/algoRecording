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
    static class Coin implements Comparable<Coin>{
        int value;
        int quantity;

        Coin(int value, int quantity){
            this.value=value;
            this.quantity=quantity;
        }

        @Override
        public int compareTo(Coin o) {
            return Integer.compare(this.value,o.value);
        }
    }

    public static void main(String[] args) throws IOException{

        br=new BufferedReader(new InputStreamReader(System.in));
        sb=new StringBuilder();

        for (int tc=0;tc<3;tc++){
            int N=Integer.parseInt(br.readLine().trim());
            Coin[] coins=new Coin[N];
            boolean[] dp=new boolean[100000+1];     // 동전으로 i원 만들 수 있는지
            int sum=0;
            for (int idx=0;idx<N;idx++){
                st=new StringTokenizer(br.readLine().trim());
                int value=Integer.parseInt(st.nextToken());
                int quantity=Integer.parseInt(st.nextToken());

                coins[idx]=new Coin(value,quantity);
                sum+=(value*quantity);

                for (int q=1;q<=quantity;q++){
                    dp[value*q]=true;
                }
            }
            // 홀수인 경우 반으로 나눌 수 없다.
            if (sum%2==1){
                sb.append(0).append("\n");
                continue;
            }else if (dp[sum/2]){
                sb.append(1).append("\n");
                continue;
            }

            dp[0]=true;
            // 주어진 동전으로 sum/2원을 만들 수 있는지 확인
            for (int idx=0;idx<N;idx++){
                int value=coins[idx].value;
                int quantity=coins[idx].quantity;

                for (int cost=sum/2;cost>=value;cost--){
                    if (dp[cost-value]){
                        for (int q=1;q<=quantity;q++){
                            if (cost-value+value*q>sum/2) break;
                            dp[cost-value+value*q]=true;
                        }
                    }
                }
            }
            sb.append(dp[sum/2]?1:0).append("\n");
        }
        System.out.print(sb);
    }
}
