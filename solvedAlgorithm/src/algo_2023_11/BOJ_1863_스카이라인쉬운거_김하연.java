package algo_2023_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * BOJ 1863: 스카이라인 쉬운거
 * << 문제  >>
 * 도시에서 태양이 질 때 보이는 건물들의 윤곽을 스카이라인이라고 한다.
 * 스카이라인만을 보고서 도시에 세워진 건물이 몇 채인지 알아낼 수 있을까? 건물은 모두 직사각형 모양으로 밋밋하게 생겼다고 가정한다.
 * 건물이 최소한 몇 채인지 알아내는 프로그램을 작성한다.
 * 
 * << 풀이 방법 >>
 * 어떻게 풀어야 할 지 감이 잘 오지 않아 검색하였다.
 * 답을 구하기 위해 생각해야 했던 점
 * 최소한의 건물이 되기 위한 기준
 * : 건물의 높이가 달라질 때
 * 1. 건물의 높이가 높아진다면 : 건물이 하나 더 위치할 수 있음을 의미한다.
 * 2. 건물의 높이가 낮아진다면: 이전 개수에 포함시킨 건물인지 아닌지 판단해야 한다. ex) 높이가 2->3->1
 * 따라서, 이전 건물 높이 정보를 저장하는 자료 구조가 필요하다.
 * 저장된 건물의 높이보다 높다면 높이를 저장하고, 낮다면 낮은 높이보다 높은 건물들은 건물 개수로 인정한다.(Stack)
 * 
 */
public class BOJ_1863_스카이라인쉬운거_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int n;
	static int[] skyLine;
	static Stack<Integer> stack;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine().trim());
		
		// 스카이라인 고도가 바뀌는 지점의 좌표와 높이를 입력받는다.
		skyLine=new int[n];
		for (int idx=0;idx<n;idx++) {
			st=new StringTokenizer(br.readLine().trim());
			st.nextToken();
			skyLine[idx]=Integer.parseInt(st.nextToken());
		}
		int answer=0;
		stack=new Stack<Integer>();
		for (int idx=0;idx<n;idx++) {
			// System.out.println(stack.toString());
			// 현재 건물의 높이가 낮다면
			while (!stack.isEmpty() && stack.peek()>skyLine[idx]) {
				answer+=1;
				stack.pop();
			}
			
			// 현재 건물의 높이와 같다면
			if (!stack.isEmpty() && stack.peek()==skyLine[idx]) continue;
			
			// 현재 건물의 높이가 높다면
			if (skyLine[idx]>0) {
				stack.push(skyLine[idx]);
			}
			
		}
		
		// stack에 남은 건물들의 개수를 더한다
		answer+=stack.size();
		System.out.println(answer);
		
	}
}
