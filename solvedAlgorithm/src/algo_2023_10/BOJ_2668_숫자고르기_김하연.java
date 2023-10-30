package algo_2023_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



/*
 * BOJ 2668: 숫자 고르기
 * << 문제  >>
 * 세로 두 줄, 가로로 N개의 칸으로 이루어진 표가 있다. 첫째 줄의 각 칸에는 정수 1,2,..N이 차례대로 들어있고 둘째 줄의 각 칸에는
 * 1 이상 N이하인 정수가 들어있다. 첫째 줄에서 숫자를 적절히 뽑으면, 그 뽑힌 정수들이 이루는 집합과, 뽑힌 정수들의 바로 밑의 둘째 줄에 들어있는 정수들이 이루는 집합이 일치한다.
 * 이러한 조건을 만족시키도록 정수를 뽑되 최대로 많이 뽑는 방법을 찾는 프로그램을 작성하시오.
 * 
 * << 풀이 방법 >>
 * 시작점에서 출발한 숫자가 자기 자신으로 돌아온다면 첫째 줄에서 뽑힌 숫자와 둘째 줄에 들어있는 정수가 같게 된다.
 * 즉, 사이클이 발생 여부를 확인하면 된다.
 * 출발지점과 같다면 뽑힌 정수의 개수를 업데이트 한다.
 * 더 이상 방문할 숫자가 없다면 두 집합은 일치하지 않는다.
 */
public class BOJ_2668_숫자고르기_김하연 {
	
	static BufferedReader br;
	static int[] numbers;
	static int N;
	static boolean[] visited;
	static List<Integer> list;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		// 정수를 입력 받는다.
		N=Integer.parseInt(br.readLine().trim());
		
		numbers = new int[N+1];
		visited=new boolean[N+1];
		
		for (int idx=1;idx<=N;idx++) {
			numbers[idx]=Integer.parseInt(br.readLine().trim());
		}
		
		// 사이클이 발생하는지 확인한다.
		// 뽑힌 정수를 저장하는 리스트
		list=new ArrayList<>();
		// 자신에서 출발하여 자신으로 돌아오는지 확인한다.
		for (int idx=1;idx<=N;idx++) {
			visited[idx]=true;
			dfs(idx,idx);
			visited[idx]=false;
		}
		
		System.out.println(list.size());
		Collections.sort(list);
		for (int idx=0;idx<list.size();idx++) {
			System.out.println(list.get(idx));
		}
	}
	/**
	 * 
	 * @param start : 방문해야 하는 숫자
	 * @param target : 도착 지점
	 */
	public static void dfs(int start,int target) {
		// 방문하지 않은 숫자인 경우 dfs를 수행한다.
		if (!visited[numbers[start]]) {
			visited[start]=true;
			dfs(numbers[start],target);
			visited[start]=false;
		}
		
		// 출발지점과 같을 시 list에 넣는다.
		if (numbers[start]==target) {
			list.add(target);
		}
	}
}
