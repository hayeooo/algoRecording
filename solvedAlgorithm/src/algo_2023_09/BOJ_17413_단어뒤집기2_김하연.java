package algo_2023_09;
/*
 * BOJ 174143: 단어뒤집기 2
 * 
 * 문자열 S가 주어졌을 때, 이 문자열에서 단어만 뒤집으려고 한다.
 * 1. 알파벳 소문자, 숫자, 공백, 특수문자 (<, >)로만 이루어져 있다.
 * 2. 문자열의 시작과 끝은 공백이 아니다.
 * 3. <, >가 문자열에 있는 경우 번갈아가면서 등장하며, '<'가 먼저 등장한다.
 * 
 * 단어는 알파벳 소문자와 숫자로 이루어진 부분 문자열이고, 연속하는 두 단어는 공백 하나로 구분한다.
 * 태그는 단어가 아니며, 태그와 단어 사이에는 공백이 없다.
 * 
 * <풀이 방법>
 * 문자열 내 문자를 하나씩 탐색하며
 * 1. 알파벳으로 시작하는 경우
 * 	stack에 공백/태그를 발견하기 전까지 삽입하고, 뒤부터 차례로 꺼내어 결과 문자열에 저장한다.
 * 2. 태그인경우
 * 	'>' 문자를 만날 때까지, 결과 문자열에 저장한다.
 * 3. 공백인 경우
 * 	결과 문자열에 저장한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_17413_단어뒤집기2_김하연 {
	
	static BufferedReader br;
	static StringBuilder sb;
	
	static String str;
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		str=br.readLine().trim();
		sb=new StringBuilder();
		
		Stack<Character> stack=new Stack<>();
		
		boolean isOpen=false;
		for (int idx=0;idx<str.length();idx++) {

			char curChar=str.charAt(idx);
			
			if (curChar=='<') {
				while(!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				isOpen=true;
				sb.append(curChar);
				continue;
			}
			// 태그
			if (isOpen) {
				sb.append(curChar);
				if (curChar=='>') {
					isOpen=false;
				}
				continue;
			}
			// 태그 내 존재하는 공백 이외의 공백인 경우
			if (curChar==' ') {
				while(!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				sb.append(curChar);
				continue;
			}
			// 단어인 경우
			if ((curChar>='a' && curChar<='z') || (curChar>='0' && curChar<='9')) {
				stack.add(curChar);
			}
		}
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		System.out.println(sb);
	}

}
