package algo_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2294_동전2_김하연 {
    static BufferedReader br;
    static StringTokenizer st;
    static int n;               // n가지의 동전
    static int k;               // 가치의 합 k원
    static int[] dp;

    public static void main(String[] args) throws IOException {
        
        br=new BufferedReader(new InputStreamReader(System.in));
        
        st=new StringTokenizer(br.readLine().trim());
        n=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());

        dp=new int[k+1];
        Arrays.fill(dp,Integer.MAX_VALUE);

        HashSet<Integer> coinSet=new HashSet<>();

        for (int idx=0;idx<n;idx++){
            int coin=Integer.parseInt(br.readLine().trim());
            coinSet.add(coin);
            for (int cnt=1;;cnt+=1){
                if (coin*cnt>k) break;
                dp[coin*cnt]=Math.min(dp[coin*cnt],cnt);
            }
        }

        ArrayList<Integer> coinList=new ArrayList<>(coinSet);
        Collections.sort(coinList);

        for(int coin:coinList){
            for (int amount=coin+1;amount<=k;amount++){
                if(dp[amount-coin]==Integer.MAX_VALUE) continue;
                dp[amount]=Math.min(dp[amount],dp[amount-coin]+1);
            }
        }
        // 만들 수 없는 동전인 경우 -1 출력
        // 문제 꼼꼼하게 읽기
        System.out.print(dp[k]==Integer.MAX_VALUE?-1:dp[k]);
    }
}
