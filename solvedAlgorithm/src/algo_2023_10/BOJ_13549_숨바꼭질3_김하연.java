package algo_2023_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BOJ 13549: 숨바꼭질 3
 * << 문제  >>
 * 수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점(0<=N<=100,000)에 있고, 동생은 점 K(0<=K<=100,000)에 있다.
 * 수빈이는 걷거나 순간 이동을 할 수 있다. 만약 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간 이동을 하는 경우에는 0초 후에 2*X의 위치로 이동하게 된다.
 * 수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 구하시오.
 * 
 * << 풀이 방법 >>
 * 첫 번째 생각: 
 * 동생이 해당 점에 위치할 수 있는 최소 시간을 저장한다.
 * 매 초마다 도착할 수 있는 점과 시간을 저장해서 priorityQueue에 넣는다.
 * 최소 시간을 기준으로 점에 대해 탐색을 수행한다.
 * 동생 위치에 도착했다면 반복을 멈춘다.
 * -> 틀림. 최소 시간을 기준으로 탐색한다고 해서 먼저 도착하는 점의 최소 시간을 보장할 수 없다.(순간이동 때문)
 * 따라서, 이미 방문한 지점이라도 해당 지점의 최소 시간보다 더 빨리 도착했다면 갱신해야 한다.
 * 
 * 두 번째 생각 :BFS
 * 이미 방문한 지점이라도 기존에 저장되어 있는 값보다 최소인 경우 갱신해야 한다.
 * 
 */
public class BOJ_13549_숨바꼭질3_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;						// 수빈이의 위치
	static int K;						// 동생의 위치
	static int[] visited;				// 방문 여부와 해당 지점에 도착했을 때의 최소 시간을 저장한다.
	
	static final int SIZE=100000;		// (0<=N,K<=100_000) 이동 가능 범위
	
	static class Pos{
		int x;
		int sec;
		
		Pos(int x,int sec){
			this.x=x;
			this.sec=sec;
		}
	}
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 수빈이와 동생의 위치를 입력받는다.
		st=new StringTokenizer(br.readLine().trim());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		visited=new int[SIZE+1];
		
		// 수빈이의 시작 지점에서 출발한다.
		Queue<Pos> que=new ArrayDeque<>();
		visited[N]=1;
		que.offer(new Pos(N,1));
		
		while(!que.isEmpty()) {
			Pos cur=que.poll();
			
			// 동생 위치에 도착하더라도 최소 시간이 아닐 수 있기 때문에 break를 걸면 안된다.
			
			// 배열 범위 조사
			// 걷는다면
			if (isRange(cur.x-1)) {
				if (visited[cur.x-1]==0 || visited[cur.x-1]>cur.sec+1) {
					visited[cur.x-1]=cur.sec+1;
					que.offer(new Pos(cur.x-1,cur.sec+1));
				}
			}
			if (isRange(cur.x+1)) {
				if (visited[cur.x+1]==0 || visited[cur.x+1]>cur.sec+1) {
					visited[cur.x+1]=cur.sec+1;
					que.offer(new Pos(cur.x+1,cur.sec+1));
				}
			}
			
			// 순간이동 한다면
			if (isRange(cur.x*2)) {
				if (visited[cur.x*2]==0 || visited[cur.x*2]>cur.sec) {
					visited[cur.x*2]=cur.sec;
					que.offer(new Pos(cur.x*2,cur.sec));
				}
			}
		}
		
		System.out.println(visited[K]-1);
	}
	
	public static boolean isRange(int x) {
		return x>=0 && x<=SIZE;
	}

}
