package algo_2023_10_13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ 7490: 0 만들기
 * << 문제 >>
 * 1부터 N까지의 수를 오름차순으로 쓴 수열 1 2 3 ... N을 생각하자
 * 그리고 '+'나 '-'또는 ' '(공백)을 숫자 사이에 삽입하자(+는 더하기, -는 빼기, 공백은 숫자를 이어 붙이는 것을 뜻한다.)
 * 이렇게 만든 수식의 값을 계산하고 그 결과가 0이 될 수 있는지 살피자.
 * N이 주어졌을 때 수식의 결과가 0이 되는 모든 수식을 찾는 프로그램을 작성하라.
 * 
 * 첫 번째 줄에 테스트 케이스의 개수가 주어진다(<10)
 * 각 테스트 케이스에 자연수 N이 주어진다(3<=N<=9)
 * 
 * 각 테스트케이스에 대해 ASCII 순서에 따라 결과가 0이 되는 모든 수식을 출력한다.
 * 각 테스트 케이스 결과는 한 줄을 띄워 구분한다.
 * 
 * << 풀이 방법 >>
 * 오름차순 수열에 대해 N-1의 연산자를 조합한다.
 * 그 연산자에 따라 수식을 계산해서 0이 된다면 list에 삽입한다.
 * list 정렬한 결과를 출력한다.
 * 
 * + 더 나아가..
 * 정렬하지 않아도 ASCII 순서는 공백 -> + -> -
 * 연산자 배열 순서를 ' ','+','-'로 넣는다.
 * 계산을 위해 StringTokenizer로 문자열을 분리한다.
 * StringTokenizer boolean을 true로 설정한다면, 구분자까지 포함시켜서 문자열을 분리한다.
 */
public class BOJ_7490_0만들기_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T;
	static int N;
	
	static char[] oper= {' ','+','-'};
	static int[] operArr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트케이스 개수를 입력받는다.
		T=Integer.parseInt(br.readLine().trim());
		sb=new StringBuilder();
		
		for (int test_case=1;test_case<=T;test_case++) {
			N=Integer.parseInt(br.readLine().trim());
			operArr=new int[N-1];
			dupPerm(0);
			sb.append("\n");
		}
		System.out.print(sb);
	}
	// 가능한 모든 연산을 구한다.
	public static void dupPerm(int cnt) {
		if (cnt>=N-1) {
			String toCal="";
			// 식을 만든다.
			for (int num=1;num<N;num++) {
				toCal+=(num+Character.toString(oper[operArr[num-1]]));
			}
			toCal+=N;
			
			int resultSum=cal(toCal);
			
			if (resultSum==0) {
				sb.append(toCal).append("\n");
			}
			return;
		}
		
		for (int idx=0;idx<oper.length;idx++) {
			operArr[cnt]=idx;
			dupPerm(cnt+1);
		}
	}
	
	// 연산을 수행한다.
	public static int cal(String expr) {
		// 1. 공백 제거
		String newExpr=expr.replace(" ", "");
		
		// 2. 연산자('+','-') 기준으로 split
		// StringTokenizer boolean 값이 true 면 +,-로 String을 나누고
		// +,-까지 분리된 문자열에 포함시킨다.
		st=new StringTokenizer(newExpr,"+|-",true);
		int sum=Integer.parseInt(st.nextToken());
		
		// 공백은 이미 제외된 상태
		while(st.hasMoreTokens()) {
			String op=st.nextToken();
			
			if (op.equals("+")) {
				sum+=Integer.parseInt(st.nextToken());
			}
			else if(op.equals("-")) {
				sum-=Integer.parseInt(st.nextToken());
			}
		}
		
		return sum;
	}

}
