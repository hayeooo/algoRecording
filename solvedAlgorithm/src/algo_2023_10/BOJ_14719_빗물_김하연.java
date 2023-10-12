package algo_2023_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ 14719: 빗물 (Simulation)
 * 어떤 조건일 경우 물이 고이는지 
 * 
 * 2차원 세계에 블록이 쌓여있다. 비가 오면 블록 사이에 빗물이 고인다.
 * 비는 충분히 많이 온다. 고이는 빗물의 총량은 얼마일까?
 * 블록 내부의 빈 공간이 생길 수 없다. 2차원 세계의 바닥은 항상 막혀있다고 가정한다.
 * 
 * << 풀이 방법 >> 
 * 기둥을 기준으로 자신보다 왼쪽, 오른쪽 기둥을 찾는다.
 * 물이 채워지는 조건은 자신의 기둥 높이보다 오른쪽, 왼쪽 기둥의 높이가 클 때 물이 채워진다.
 * 채워지는 물의 양 = (오른쪽, 왼쪽 기둥 높이 중 높이가 낮은 기둥의 높이)-(자신의 높이)
 * 
 * 2차원 세계의 세로 길이 H과 세계의 가로 길이 W가 주어진다.(1<=H,W<=500)
 * 두 번째 줄에는 블록이 쌓인 높이를 의미하는 0이상 H이하의 정수가 2차원 세계의 맨 왼쪽 위치부터 차례대로 W개 주어진다.
 */
public class BOJ_14719_빗물_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int H,W;			// 2차원 세계의 세로 길이(H), 가로 길이(W)
	static int[] pillars;	// 기둥의 높이를 저장하는 배열
	static int waterAmount=0;	// 채워질 수 있는 물의 양
	
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		// 세로, 가로 길이를 입력받는다.
		H=Integer.parseInt(st.nextToken());
		W=Integer.parseInt(st.nextToken());
		
		pillars=new int[W];
		
		
		st=new StringTokenizer(br.readLine().trim());
		for (int idx=0;idx<W;idx++) {
			pillars[idx]=Integer.parseInt(st.nextToken());
		}
		
		for (int idx=0;idx<W;idx++) {
			// 자신의 왼쪽 기둥 중 가장 높은 기둥을 찾는다.
			int leftMax=pillars[idx], rightMax=pillars[idx];
			
			for (int left=idx-1;left>=0;left--) {
				leftMax=Math.max(leftMax, pillars[left]);
			}
			
			// 자신의 오른쪽 기둥 중 가장 높은 기둥을 찾는다.
			for (int right=idx+1;right<W;right++) {
				rightMax=Math.max(rightMax, pillars[right]);
			}
			
			// 자신의 왼쪽 기둥, 오른쪽 기둥이 자신의 기둥보다 클 때, (같으면 해당 기둥에 물이 채워지지 않는다.)
			// 현재 기둥에 채워질 수 있는 물의 양을 구한다.
			if (Math.min(leftMax, rightMax)>pillars[idx]) {
				waterAmount+=Math.min(leftMax, rightMax)-pillars[idx];
			}
		}
		System.out.println(waterAmount);
	}

}
