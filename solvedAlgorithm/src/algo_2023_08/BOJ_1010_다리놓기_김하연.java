package algo_2023_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 1010: 다리 놓기
 * 서쪽의 사이트와 동쪽의 사이트를 다리로 연결하려고 한다.
 * 다리를 최대한 많이 지으려고 하기 때문에 서쪽의 사이트 개수만큼(N개) 다리를 지으려고 한다.
 * 다리끼리는 서로 겹쳐질 수 없다고 할 때 다리를 지을 수 있는 경우의 수를 구한다.
 * 
 * M 개의 다리 중 N개를 고르는 조합
 * 
 */
public class BOJ_1010_다리놓기_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T;			// 테스트케이스 수
	static int N;			// 강 서쪽 사이트 개수
	static int M;			// 강 동쪽 사이트 개수(N<=M)
	static int[][] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트케이스 수를 입력 받는다.
		T=Integer.parseInt(br.readLine().trim());
		
		sb=new StringBuilder();
		
		for (int tc=0;tc<T;tc++) {
			// 강의 서쪽과 동쪽 사이트 개수를 입력받는다.
			st=new StringTokenizer(br.readLine().trim());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			
			dp=new int[M+1][N+1];
			
			for (int m=1;m<=M;m++) {
				for (int n=0;n<=Math.min(n,N);n++) {
					if (n==0 || m==n) {
						dp[m][n]=1;
					}
					else {
						dp[m][n]=dp[m-1][n-1]+dp[m-1][n];
					}
				}
			}
			sb.append(dp[M][N]+"\n");
		}
		System.out.println(sb);
	}

}
