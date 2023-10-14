package algo_2023_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * BOJ 20437: 문자열 게임 2
 * << 문제 >>
 * 1. 알파벳 소문자로 이루어진 문자열 W가 주어진다.
 * 2. 양의 정수 K가 주어진다.
 * 3. 어떤 문자를 정확히 K개 포함하는 가장 짧은 연속 문자열의 길이를 구한다.
 * 4. 어떤 문자를 정확히 K개 포함하고, 문자열의 첫 번째와 마지막 글자가 해당 문자로 같은 가장 긴 연속 문자열의 길이를 구한다.
 * 
 * << 풀이 방법 >> 
 * 각 문자열에 대해 나온 알파벳의 위치를 List 형태로 저장한다.
 * k만큼의 길이를 가지고 있는 알파벳에 대해 문제의 3,4 조건에 맞는 문자열 길이를 구한다.
 */
public class BOJ_20437_문자열게임2_김하연 {
	
	static BufferedReader br;
	static StringBuilder sb;
	static int T, K;
	static String str;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		// 문자열 게임의 수를 입력받는다.
		T=Integer.parseInt(br.readLine().trim());
		for (int tc=0;tc<T;tc++) {
			
			String str=br.readLine().trim();
			K=Integer.parseInt(br.readLine());
			
			List<Integer>[] alphaLoc=new ArrayList[26];
			
			// list를 초기화한다.
			for (int idx=0;idx<alphaLoc.length;idx++) {
				alphaLoc[idx]=new ArrayList<>();
			}
			// 각 알파벳의 위치를 저장한다.
			for (int idx=0;idx<str.length();idx++) {
				char c=str.charAt(idx);
				alphaLoc[c-'a'].add(idx);
			}
			
			int minLength=Integer.MAX_VALUE;
			int maxLength=Integer.MIN_VALUE;
			int cnt=0;
			
			for (int idx=0;idx<26;idx++) {
				// 조건 3,4는 모두 어떤 문자를 정확히 K번 포함해야 한다.
				if (alphaLoc[idx].size()<K) {
					continue;
				}
				cnt++;
				for (int startIdx=0;startIdx<=alphaLoc[idx].size()-K;startIdx++) {
					
					// 가장 짧은 연속 문자열의 길이
					minLength=Math.min(minLength, alphaLoc[idx].get(startIdx+K-1)-alphaLoc[idx].get(startIdx)+1);
					
					// 문자열의 첫 번째와 마지막 글자가 해당 문자로 같은 가장 긴 연속 문자열 길이
					maxLength=Math.max(maxLength, alphaLoc[idx].get(startIdx+K-1)-alphaLoc[idx].get(startIdx)+1);
					
				}
			}
			
			// 문자열이 어떤 문자에 대해 K번을 포함한 경우가 없는 경우 -1 출력
			if (cnt==0) sb.append(-1).append("\n");
			else sb.append(minLength).append(" ").append(maxLength).append("\n");
		}
		System.out.print(sb);
	}

}
