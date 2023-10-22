package algo_2023_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ 12912: A와 B 2
 * << 문제 >>
 * 두 문자열 S와 T가 주어졌을 때, S를 T로 바꾸는 게임이다.
 * 문자열을 바꿀 때는 다음과 같은 두 가지 연산만 가능하다.
 * 1. 문자열 뒤에 A를 추가한다.
 * 2. 문자열 뒤에 B를 추가하고 문자열을 뒤집는다.
 * 주어진 조건을 이용하여 S를 T로 만들 수 있는지 없는지 알아내는 프로그램을 작성하시오.
 * 
 * << 풀이 방법 >> 
 * Greedy한 접근 방법이 없다. 문자열을 계속 뒤집는다면 T로 만들 수 있는지 없는지 계속 바뀌기 때문이다.
 * 그러나 모든 경우를 다 고려할 경우 (1<=S의 길이<=49, 2<=T의 길이<=50, S의 길이 < T의 길이), 최악일 때 2^50이므로 시간초과가 발생한다.
 * 최대한 모든 경우를 고려하되, A와 B의 개수를 파악하여 Backtracking으로 실행시간을 줄인다.
 * ==> 시간 초과
 * 
 * 풀이 참고
 * : S에 문자열을 추가하는 방식이 아닌 T에서 문자를 제거하는 방식으로 진행, substring 사용
 * 경우의 수가 줄어든다. 실행시간을 줄일 수 있다.
 */
public class BOJ_12912_A와B2_김하연 {
	
	static BufferedReader br;
	static StringBuilder sb;
	static String S;			// 문자열 S
	static String T;			// 문자열 T
	
	static boolean possible;
	
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 문자열 S와 T를 입력받는다.
		S=br.readLine().trim();
		T=br.readLine().trim();
		
		possible=false;
		dfs(T);
		System.out.println(possible?1:0);
	}
	
	public static void dfs(String str) {
		
		// 길이가 같다면 S와 같은 문자열인지 확인
		if (str.length()==S.length()) {
			if (str.equals(S)) possible=true;
			return;
		}
		
		// 첫 번째 문자가 B인 경우
		if (str.charAt(0)=='B') {
			sb=new StringBuilder(str.substring(1)).reverse();
			dfs(sb.toString());
		}
		
		// 마지막 문자가 A인 경우
		if (str.charAt(str.length()-1)=='A') {
			dfs(str.substring(0,str.length()-1));
		}
	}
	
	

}
