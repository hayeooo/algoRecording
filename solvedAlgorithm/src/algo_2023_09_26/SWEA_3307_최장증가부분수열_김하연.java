package algo_2023_09_26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * SWEA 3307: 최장증가부분수열
 * 주어진 두 수열의 최장 증가 부분 수열(Longest Increasing Subsequence)의 길이를 계산한다.
 * 
 * 풀이 방법: DP
 * DP 배열에 저장되는 값: 각 원소까지 도달했을 때, 최장 부분 수열의 길이를 저장한다.
 * 현재 원소를 기준으로 앞 원소들 중 
 * (자신보다 작은 원소의 수열의 길이+1) 또는 (현재 원소의 기존에 저장된 최장 부분수열 길이 값) 중
 * 큰 값을 저장한다.
 */
public class SWEA_3307_최장증가부분수열_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N,T;
	static int[] seq;		// 수열 숫자를 저장하는 배열
	static int[] dp;		
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		
		// 테스트케이스 수를 입력받는다.
		T=Integer.parseInt(br.readLine().trim());
		
		for (int test_case=1;test_case<=T;test_case++) {
			// 수열의 길이를 입력받는다.
			N=Integer.parseInt(br.readLine().trim());
			
			// 수열을 입력받는다.
			seq=new int[N];
			dp=new int[N];
			
			st=new StringTokenizer(br.readLine().trim());
			for (int idx=0;idx<N;idx++) {
				seq[idx]=Integer.parseInt(st.nextToken());
			}
			
			// 각 원소의 최대 길이는 1
			Arrays.fill(dp, 1);
			
			// 현재 원소를 기준으로
			for (int cur=1;cur<N;cur++) {
				// 앞에 있는 원소들 중 
				for (int prev=0;prev<cur;prev++) {
					// 자신보다 작거나 같은 것 중에서
					if (seq[cur]>=seq[prev]) {
						// 최장 수열의 길이를 저장한다.
						dp[cur]=Math.max(dp[prev]+1,dp[cur]);
					}
				}
			}
			
			// 최장 부분 수열의 길이를 찾는다.
			int maxLength=Integer.MIN_VALUE;
			for (int idx=0;idx<N;idx++) {
				maxLength=Math.max(maxLength, dp[idx]);
			}
			// 값을 출력한다.
			System.out.println("#"+test_case+" "+maxLength);
			
	}
	
	}
}
