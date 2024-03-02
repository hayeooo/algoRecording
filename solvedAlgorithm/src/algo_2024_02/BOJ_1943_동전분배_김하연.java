package algo_2024_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Coin{
    int type;
    int count;
    public Coin(int type,int count){
        this.type=type;
        this.count=count;
    }
}
public class BOJ_1943_동전분배_김하연 {

    private static BufferedReader br;
    private static StringTokenizer st;

    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));
        sb=new StringBuilder();
        
        for (int tc=0;tc<3;tc++){
            int N=Integer.parseInt(br.readLine().trim());

            boolean[] dp=new boolean[100001];
            List<Coin> coins=new ArrayList<>();
            int sum=0;
            for (int idx=0;idx<N;idx++){
                st=new StringTokenizer(br.readLine().trim());
                int type=Integer.parseInt(st.nextToken());
                int count=Integer.parseInt(st.nextToken());

                coins.add(new Coin(type,count));
                sum+=(count*type);
                
                for (int c=1;c<=count;c++){
                    dp[type*c]=true;
                }
            }
            
            if (sum%2==1){
                sb.append(0).append("\n");
                continue;
            }
            else if(dp[sum/2]){
                sb.append(1).append("\n");
                continue;
            }
            // 큰 수부터 내려오면서 dp 수행
            // 작은 수부터 dp 배열을 채워나가면 중복되어서 계산할 수 있음
            for (Coin coin:coins){
                for (int value=sum/2;value>=coin.type;value--){
                    if (dp[value-coin.type]){
                        for (int cnt=1;cnt<=coin.count;cnt++){
                            if (value-coin.type+coin.type*cnt>sum/2){
                                break;
                            }
                            dp[value-coin.type+cnt*coin.type]=true;
                        }
                    }
                }
            }
            sb.append(dp[sum/2]?1:0).append("\n");
        }
        System.out.print(sb);
    }
}
