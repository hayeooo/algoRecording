package algo_2023_10_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * SWEA 5643: 키순서
 * 학생들에 대하여 두 학생끼리 키를 비교한 결과의 일부가 주어져 있다.
 * N명의 학생들의 키는 모두 다르다고 가정한다.
 * 비교 결과로부터 모든 학생 중에서 키가 가장 작은 학생부터 자신이 몇 번째인지 알 수 있는 학생들도 있고
 * 그렇지 못한 학생들도 있다.
 * 학생들의 키를 비교한 결과가 주어질 때, 자신의 키가 몇 번째인지 알 수 있는 학생들이 모두 몇 명인지 계산하여 출력한다.
 * 
 * 자신보다 작은 사람의 수를 알고 자신보다 큰 사람 수를 구한다.
 * 
 * <<풀이 방법>>
 * 1. 자신보다 작은 사람의 수를 구한다.
 * 2. 자신보다 큰 사람 수를 구한다.
 * 3. 전체 인원수==(자신보다 작은 사람의 수)+(자신보다 큰 사람의 수)-1이면 자신의 키가 몇 번째인지 알 수 있다.
 * 
 */
public class SWEA_5643_키순서_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T;		// 테스트케이스 개수
	static int N,M;		// 학생들의 수, 두 학생의 키를 비교한 횟수
	static int[][] graph;	// 두 학생의 키 비교를 저장한 배열
	
	static int smallCnt,tallCnt;
	static int resultCnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		// 테스트케이스를 입력받는다.
		T=Integer.parseInt(br.readLine().trim());
		
		for (int test_case=1;test_case<=T;test_case++) {
			// 학생의 수를 입력받는다.
			N=Integer.parseInt(br.readLine().trim());
			graph=new int[N+1][N+1];
			
			// 두 학생의 키를 비교한 횟수를 입력받는다.
			M=Integer.parseInt(br.readLine().trim());
			for (int cnt=0;cnt<M;cnt++) {
				st=new StringTokenizer(br.readLine().trim());
				int small=Integer.parseInt(st.nextToken());
				int tall=Integer.parseInt(st.nextToken());
				
				graph[small][tall]=1;
			}
			
			resultCnt=0;
			// 자신의 키 순서를 알 수 있는지 확인
			for (int person=1;person<=N;person++) {
				smallCnt=0;
				findSmall(person);
				
				tallCnt=0;
				findTall(person);
				
				if (smallCnt+tallCnt==N-1) {
					resultCnt+=1;
				}
			}
			sb.append("#").append(test_case).append(" ").append(resultCnt).append("\n");
			
		}
		System.out.println(sb);
	}
	public static void findSmall(int start) {
		Queue<Integer> que=new ArrayDeque<>();
		boolean[] visited=new boolean[N+1];
		
		visited[start]=true;
		que.offer(start);
		
		while(!que.isEmpty()) {
			int to=que.poll();
			
			for (int person=1;person<=N;person++) {
				// 키가 작은 사람들 중 방문하지 않은 사람이 있다면
				if (!visited[person] && graph[person][to]==1) {
					que.offer(person);
					visited[person]=true;
					smallCnt+=1;
				}
			}
			
		}
		
		
	}
	public static void findTall(int start) {
		Queue<Integer> que=new ArrayDeque<>();
		boolean[] visited=new boolean[N+1];
		
		visited[start]=true;
		que.offer(start);
		
		while(!que.isEmpty()) {
			int from=que.poll();
			
			for (int person=1;person<=N;person++) {
				// 키가 큰 사람들 중 방문하지 않은 사람이 있다면
				if (!visited[person] && graph[from][person]==1) {
					que.offer(person);
					visited[person]=true;
					tallCnt+=1;
				}
			}
		}
	}

}
