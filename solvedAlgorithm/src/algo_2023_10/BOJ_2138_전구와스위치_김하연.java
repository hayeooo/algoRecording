package algo_2023_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * BOJ 2138: 전구와 스위치
 * << 문제 >>
 * N 개의 스위치와 N 개의 전구가 있다.
 * 각각의 전구는 켜져 있는 상태와 꺼져 있는 상태 중 하나의 상태를 가진다.
 * i번 스위치를 누르면 i-1, i, i+1의 세 개의 전구의 상태가 바뀐다.
 * 즉, 꺼져 있는 전구는 켜지고, 켜져 있는 전구는 꺼지게 된다.
 * 1 번 스위치를 눌렀을 경우에는 1,2번 전구의 상태가 바뀌고, N번 스위치를 눌렀을 경우에는 N-1, N번 전구의 상태가 바뀐다.
 * N개의 전구들을 현재 상태와 우리가 만들고자 하는 상태가 주어졌을 때, 그 상태를 만들기 위해 스위츠를 최소 몇 번 누르면 되는지
 * 알아내는 프로그램을 작성한다.
 * 
 * << 풀이 방법 >>
 * 만들고자 하는 스위치의 모양과 같은지 비교하면서 스위치를 켰다가 꺼야한다.
 * 앞에서부터 차례대로 전구의 상태를 바꾸기 때문에 i-1번째가 만들고자 하는 스위치의 모양과 같은지 비교해야 한다.
 * 
 * << 주의 >>
 * 첫 번째 전구를 켜거나 끄는 상태로 고정하여 진행한다면 두 번째 전구 상태 변경에 의해 바뀔 수 있다.
 * 따라서, 첫 번째 전구를 켜거나 끈 두 상태를 모두 고려하여 시작해야 한다.
 * 전구의 상태를 바꾸는 기준은 i-1 전구이다.
 * 
 */
public class BOJ_2138_전구와스위치_김하연 {
	
	static BufferedReader br;
	static int N;		// 전구의 개수
	static char[][] light;
	static int[] cnt;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 전구의 개수
		N=Integer.parseInt(br.readLine().trim());
		
		// 전구의 상태를 저장할 배열
		light=new char[4][N];
		
		// 전구들의 현재 상태
		light[0]=br.readLine().trim().toCharArray();
		
		// 만들고자 하는 전구들의 상태
		light[1]=br.readLine().trim().toCharArray();
		
		// 현재 상태 값 복사
		for (int i=0;i<N;i++) {
			light[2][i]=light[0][i];
			light[3][i]=light[0][i];
		}
		
		cnt=new int[2];
		
		// 첫 번째 전구가 켜져있다고 가정
		if (light[2][0]=='0') {
			light[2][0]='1';
			convert(light,2,1);
			cnt[0]++;
		}
		
		// 첫 번째 전구가 꺼져있다고 가정
		if (light[3][0]=='1') {
			light[3][0]='0';
			convert(light,3,1);	
			cnt[1]++;
		}
		
		// 두 번째 전구부터 시작
		for (int i=1;i<N;i++) {
			// i-1번째 전구가 만들고자 하는 전구의 상태와 다르다면
			// 1. 첫 번째 전구가 켜져있다고 가정한 경우
			if (light[2][i-1]!=light[1][i-1]) {
				convert(light,2,i-1);
				convert(light,2,i);
				if (i<N-1) {
					convert(light,2,i+1);
				}
				cnt[0]++;
			}
			// 2. 첫 번째 전구가 꺼져있다고 가정한 경우
			if (light[3][i-1]!=light[1][i-1]) {
				convert(light,3,i-1);
				convert(light,3,i);
				if (i<N-1) {
					convert(light,3,i+1);
				}
				cnt[1]++;
			}
		}
		// 만들고자 한 전구와 맞는지 확인
		int minCnt=Integer.MAX_VALUE;
		for (int row=2;row<=3;row++) {
			boolean correct=true;
			for (int idx=0;idx<N;idx++) {
				if (light[1][idx]!=light[row][idx]) {
					correct=false;
					break;
				}
			}
			if (correct) {
				minCnt=Math.min(minCnt, cnt[row-2]);
			}
		}
		if (minCnt==Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(minCnt);
		}
	}
	
	public static void convert(char[][] arr, int i, int j) {
		if(arr[i][j]=='1') arr[i][j]='0';
		else if (arr[i][j]=='0') arr[i][j]='1';
	}

}
