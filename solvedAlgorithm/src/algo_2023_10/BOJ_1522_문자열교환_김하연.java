package algo_2023_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ 1522: 문자열 교환
 * << 문제 >>
 * a와 b로만 이루어진 문자열이 주어질 때, a를 모두 연속으로 만들기 위해서 필요한 교환의 회수를 최소로 하는 프로그램을 작성.
 * 문자열은 원형이기 때문에, 처음과 끝은 서로 인접해 있는 것이다.
 * 
 * << 풀이 >>
 * a와 b를 교환한다는 의미는 서로 이웃한 문자열만 교환할 수 있는 것이 아닌 멀리 떨어져 있는 문자열과 교환해도 된다.
 * a가 모두 연속하다면 문자열에 속한 a의 개수만큼 길이를 가지고 있다.
 * 문자열 슬라이딩 윈도우를 통해 해당 문자열에 속한 b의 개수만큼 슬라이딩 윈도우 밖에 있는 a와 교환한다면, a를 모두 연속으로 만들 수 있다.
 * 
 */
public class BOJ_1522_문자열교환_김하연 {
	
	static BufferedReader br;
	static String str;
	static int minCnt=Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 문자열을 입력받는다.
		str=br.readLine().trim();
		
		// a의 개수를 구한다.
		int aCnt=0;
		for (int idx=0;idx<str.length();idx++) {
			if (str.charAt(idx)=='a') {
				aCnt++;
			}
		}
		
		// 문자열이 원형이므로 문자열의 끝에 aCnt-1길이의 문자열을 더해준다.
		for (int idx=0;idx<aCnt-1;idx++) {
			str+=str.charAt(idx);
		}
		
		// 처음 값을 초기화한다.
		int bCnt=0;
		for (int idx=0;idx<aCnt;idx++) {
			if (str.charAt(idx)=='b') bCnt++;
		}
		minCnt=Math.min(minCnt, bCnt);
		
		// 슬라이딩 윈도우 크기는 aCnt이다.
		for (int start=1;start<=str.length()-aCnt;start++) {
			// 이전 값 제외시키기
			if (str.charAt(start-1)=='b') bCnt--;
			// 슬라이딩 윈도우 마지막 값 추가시키기
			if (str.charAt(start+aCnt-1)=='b') bCnt++;
			minCnt=Math.min(minCnt, bCnt);
		}
		
		// 결과값 출력하기
		System.out.println(minCnt);
	}

}
