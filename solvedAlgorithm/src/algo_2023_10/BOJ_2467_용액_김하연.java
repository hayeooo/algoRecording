package algo_2023_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 2467: 용액
 * 각 용액에는 그 용액의 특성을 나타내는 하나의 정수가 주어져있다.
 * 산성 용액의 특성값은 1부터 1_000_000_000까지의 양의 정수로 나타내고, 
 * 알칼리성 용액의 특성값은 -1부터 -1_000_000_000까지의 음의 정수로 나타낸다.
 * 같은 양의 두 용액을 혼합한 용액의 특성값은 혼합에 사용된 각 용액의 특성값의 합으로 정의한다.
 * 이 연구소에서 같은 양의 두 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들려고 한다.
 * 두 종류의 알칼리성 용액만으로나 혹은 두 종류의 산성 용액만으로 특성값이 0에 가장 가까운 혼합 용액을 만드는 경우도 존재할 수 있다.
 * 산성 용액과 알칼리성 용액의 특성값이 정렬된 순서로 주어졌을 때, 
 * 이 중 두 개의 서로 다른 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액을 찾는 프로그램을 작성하시오.
 * 특성값이 0에 가장 가까운 용액을 만들어내는 경우가 두 개 이상일 경우에는 그 중 아무거나 하나 출력한다.
 * 
 * << 풀이 방법 >> 
 * Two Pointer;
 * 오름차순으로 정렬되어 있으므로,
 * 왼쪽 포인터, 오른쪽 포인터를 정의하여
 * 1. 두 용액의 특성값 합 < 0 : 왼쪽 포인터를 오른쪽으로 이동시킨다.
 * 2. 두 용액의 특성값 합 > 0 : 오른쪽 포인터를 왼쪽으로 이동시킨다.
 * 절댓값으로 최솟값을 비교하여 두 용액의 특성값을 저장한다.
 */
public class BOJ_2467_용액_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int N;			// 전체 용액의 수
	static int[] liquid;	// 용액의 특성값
	static int[] resultLiquid;	// 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액을 저장하는 배열
	static int minValue=Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 전체 용액의 수를 입력받는다.
		N=Integer.parseInt(br.readLine().trim());
		
		// 용액의 특성값을 입력받는다.
		liquid=new int[N];
		st=new StringTokenizer(br.readLine().trim());
		
		for (int idx=0;idx<N;idx++) {
			liquid[idx]=Integer.parseInt(st.nextToken());
		}
		
		int leftPointer=0;
		int rightPointer=N-1;
		resultLiquid=new int[2];
		
		while (leftPointer<rightPointer) {
			// 특성값이 0에 가장 가까운 용액인지 판단
			if (Math.abs(liquid[leftPointer]+liquid[rightPointer])<minValue) {
				resultLiquid[0]=liquid[leftPointer];
				resultLiquid[1]=liquid[rightPointer];
				minValue=Math.abs(liquid[leftPointer]+liquid[rightPointer]);
			}
			// 1. 두 용액의 특성값 합 < 0
			if (liquid[leftPointer]+liquid[rightPointer]<0) {
				// 왼쪽 포인터를 오른쪽으로 이동시킨다.
				leftPointer++;
			}
			// 2. 두 용액의 특성값 합 > 0
			else if(liquid[leftPointer]+liquid[rightPointer]>=0) {
				// 오른쪽 포인터를 왼쪽으로 이동시킨다.
				rightPointer--;
			}
		}
		sb=new StringBuilder();
		for (int idx=0;idx<resultLiquid.length;idx++) {
			sb.append(resultLiquid[idx]).append(" ");
		}
		System.out.println(sb);
	}

}
