package algo_2023_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 1년 동안 각 달의 수영장 이용 계획을 수립하고 가장 적은 비용으로 수영장을 이용할 수 있는 방법을 찾고 있다.
 * 판매하고 있는 이용권은 4 종류이다.
 * 1. 1일 이용권 : 1일 이용이 가능하다.
 * 2. 1달 이용권: 1달 동안 이용이 가능하다. 매달 1일부터 시작한다.
 * 3. 3달 이용권: 연속된 3달 동안 이용이 가능하다. 매달 1일부터 시작한다.
 * 4. 1년 이용권: 1년 동안 이용이 가능하다. 1년 이용권은 매년 1월 1일부터 시작한다.
 * 
 * 각 이용권의 요금과 각 달의 이용 계획이 입력으로 주어질 때,
 * 가장 적은 비용으로 수영장을 이용할 수 있는 방법을 찾고 그 비용을 정답으로 출력하는 프로그램을 작성하라.
 * 
 * 해당 월까지 수영 이용권을 구매할 때 가장 최소 비용을 담는 dp 배열을 이용한다.
 * 이용 계획이 0인 경우 앞에 있는 값을 그대로 가져온다.
 * 매달 1일 이용권과 1달 이용권을 고려한다.
 * 3월부터 12월까지 3달 이용권을 고려한다.
 * 12월에 1년 이용권을 고려한다.
 */
public class SWEA_1952_수영장_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T;				// 총 테스트 케이스 개수
	static int[] costs;			// 이용권 가격들
	static int[] plan;
	static int[] dp;			// 해당 월까지 이용권 최소 금액
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		// 테스트케이스 수를 입력받는다.
		T=Integer.parseInt(br.readLine().trim());
		
		for (int tc=1;tc<=T;tc++) {
			costs=new int[4];
			plan=new int[13];
			dp=new int[13];
			
			// 이용권 가격들을 입력받는다.
			st=new StringTokenizer(br.readLine().trim());
			for (int idx=0;idx<4;idx++) {
				costs[idx]=Integer.parseInt(st.nextToken());
			}
			
			// 12개월 이용 계획 정보를 입력받는다.
			st=new StringTokenizer(br.readLine().trim());
			for (int idx=1;idx<=12;idx++) {
				plan[idx]=Integer.parseInt(st.nextToken());
			}
			// 1월은 1일 이용권과 1개월 이용권 중 최솟값 저장(초기화)
			dp[1]=Math.min(plan[1]*costs[0], costs[1]);
			
			// 해당 월까지 가장 최소 금액을 저장한다.
			for (int month=2;month<=12;month++) {
				
				// 1일 이용권과 1개월 이용권 중 최솟값 저장
				dp[month]=Math.min(plan[month]*costs[0], costs[1])+dp[month-1];
				
				// 3개월 이용권 가능한 경우 고려
				if (month-2>0) {
					dp[month]=Math.min(dp[month], costs[2]+dp[month-3]);
				}
				if (month==12) {
					dp[month]=Math.min(dp[month], costs[3]);
				}
			}
			sb.append("#").append(tc).append(" ").append(dp[12]).append("\n");
			//System.out.println(Arrays.toString(dp));
		}
		System.out.println(sb);
	}

}
