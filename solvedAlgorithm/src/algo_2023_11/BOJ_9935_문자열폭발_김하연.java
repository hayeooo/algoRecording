package algo_2023_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * BOJ 9935: 문자열 폭발
 * << 문제 >>
 * 상근이는 문자열에 폭발 문자열을 심어 놓았다. 폭발 문자열이 폭발하면 그 문자는 문자열에서 사라지며, 남은 문자열은 합쳐지게 된다.
 * 폭발은 다음과 같은 과정으로 진행된다.
 *  * 문자열이 폭발 문자열을 포함하고 있는 경우에, 모든 폭발 문자열이 폭발하게 된다. 남은 문자열을 순서대로 이어 붙여 새로운 문자열을 만든다.
 *  * 새로 생긴 문자열에 폭발 문자열이 포함되어 있을 수도 있다.
 *  * 폭발은 폭발 문자열이 문자열에 없을 때까지 계속된다.
 * 
 * << 풀이 방법 >> 
 * contains: O(nm)
 * replaceAll: O(n)
 * O(n^2*m): 시간 초과
 * 
 * 문자열의 길이는 1보다 크거나 같고, 1_000_000보다 작거나 같으므로 시간 제한 2초를 만족하기 위해 O(n)의 알고리즘을 완성해야 한다.
 * 한 번의 탐색을 진행하면서 비교 -> 해당 문자열 삭제 -> 인접한 곳에 같은 폭발 문자열이 있는지 확인 : 반복
 * 
 * String을 사용해서 메모리 초과남.
 * character 형태로 바꾸어서 다시 풀이
 * 문자열을 stack에 넣고 폭발 문자열보다 문자가 더 많이 들어가있을 경우
 * 폭발 문자열과 같은 문자열이 있는지 확인한다.
 */
public class BOJ_9935_문자열폭발_김하연 {
	
	static BufferedReader br;
	static StringBuilder sb;
	static String origin;			// 주어진 문자열
	static String bombStr;			// 폭발 문자열
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 문자열과 폭발 문자열을 입력받는다.
		origin=br.readLine().trim();
		bombStr=br.readLine().trim();
		
		Stack<Character> stack=new Stack<>();
		
		// stack.get으로 index를 활용하여 접근할 수 있다.
		for (int idx=0;idx<origin.length();idx++) {
			// stack에 하나씩 넣는다.
			stack.add(origin.charAt(idx));
			
			// 폭발 문자열 길이보다 같거나 큰 경우 같은 문자열이 포함되어 있는지 확인
			if (stack.size()>=bombStr.length()) {
				boolean isSame=true;	// 폭발 문자열이 있다고 가정
				int startIdx=stack.size()-bombStr.length();
				
				for (int bombIdx=0;bombIdx<bombStr.length();bombIdx++) {
					if (stack.get(startIdx+bombIdx)!=bombStr.charAt(bombIdx)) {
						isSame=false;
						break;
					}
				}
				// 폭발 문자열이 있다면
				// 끝에서부터 제거하면 폭발 문자열 길이만큼 제거한다.
				if (isSame) {
					for (int cnt=0;cnt<bombStr.length();cnt++) {
						stack.pop();
					}
				}
			}
		}
		
		if (stack.size()==0) {
			System.out.println("FRULA");
		}
		else {
			sb=new StringBuilder();
			for (int idx=0;idx<stack.size();idx++) {
				sb.append(stack.get(idx));
			}
			System.out.println(sb);
		}
	}
}
