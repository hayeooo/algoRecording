package algo_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1389_케빈베이컨의6단계법칙_김하연 {
    static BufferedReader br;
    static StringTokenizer st;
    static int N;               // 유저의 수
    static int M;               // 친구 관계 수
    static int[][] dp;          // 각 지점에서 최소 단계를 저장하는 배열
    public static void main(String[] args) throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));
        st=new StringTokenizer(br.readLine().trim());

        // 유저 수와 친구 관계 수를 입력받는다.
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        dp=new int[N+1][N+1];
        for (int idx=0;idx<=N;idx++){
            Arrays.fill(dp[idx],5001);
            dp[idx][idx]=0;
        }
        // 친구 관계를 입력받고 저장한다.
        for (int idx=0;idx<M;idx++){
            st=new StringTokenizer(br.readLine().trim());
            int A=Integer.parseInt(st.nextToken());
            int B=Integer.parseInt(st.nextToken());

            dp[A][B]=1;
            dp[B][A]=1;
        }

        for (int mid=1;mid<=N;mid++){
            for (int start=1;start<=N;start++){
                for(int end=1;end<=N;end++){
                    dp[start][end]=Math.min(dp[start][end],dp[start][mid]+dp[mid][end]);
                }
            }
        }
        // 모든 지점에서 모든 지점까지의 최단 거리를 구한 경우
        // 합이 가장 작은 사람을 출력한다.
        int minPerson=0;
        int minSum=Integer.MAX_VALUE;
        for (int person=1;person<=N;person++){
            int tmpSum=0;
            for (int otherPerson=1;otherPerson<=N;otherPerson++){
                tmpSum+=dp[person][otherPerson];
            }
            if (tmpSum<minSum){
                minSum=tmpSum;
                minPerson=person;
            }
            else if (tmpSum==minSum){
                if (minPerson>person){
                    minPerson=person;
                }
            }
        }
        System.out.println(minPerson);
    }
}
