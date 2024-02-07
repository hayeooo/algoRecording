package algo_2024_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 모든 경우의 수를 고려하기 N,M이 1이상 1000이하이므로 시간초과 발생 우려
 * dp로 해결 하지만 한 번 탐사한 지역은 탐사하지 않고 왼쪽 오른쪽 방향이 있으므로 이를 고려해야 한다.
 */
public class BOJ_2169_로봇조종하기_김하연 {

    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static int M;

    static int[][] dp;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));
        st=new StringTokenizer(br.readLine().trim());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        arr=new int[N][M];
        dp=new int[N][M];

        for (int row=0;row<N;row++){
            st=new StringTokenizer(br.readLine().trim());
            for (int col=0;col<M;col++){
                arr[row][col]=Integer.parseInt(st.nextToken());
                dp[row][col]=Integer.MIN_VALUE;
            }
        }
        // (1,1)에서 시작
        dp[0][0]=arr[0][0];
        for (int col=1;col<M;col++){
            dp[0][col]=dp[0][col-1]+arr[0][col];
        }

        for (int row=1;row<N;row++){
            int[] leftDown=new int[M];
            int [] rightDown=new int[M];

            leftDown[0]=dp[row-1][0]+arr[row][0];
            rightDown[M-1]=dp[row-1][M-1]+arr[row][M-1];
            for (int col=1;col<M;col++){
                leftDown[col]=Math.max(leftDown[col-1]+arr[row][col],dp[row-1][col]+arr[row][col]);
            }

            for (int col=M-2;col>=0;col--){
                rightDown[col]=Math.max(rightDown[col+1]+arr[row][col],dp[row-1][col]+arr[row][col]);
            }
            for (int col=0;col<M;col++){
                dp[row][col]=Math.max(leftDown[col],rightDown[col]);
            }

        }
        System.out.println(dp[N-1][M-1]);
    }
}
