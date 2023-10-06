package algo_2023_10_06;

import java.util.Arrays;

/*
 * Counting Sort [계수 정렬 / 카운팅 정렬]
 * 시간 복잡도: O(N)
 * 
 * 1. array를 한 번 순회하면서 각 값이 나올 때마다 해당 값을 counting 한다.
 * 2. count 배열의 각 값을 누적합으로 변환한다.
 * 3. count 배열의 각 값으로 대응되는 위치에 배정한다.
 */
public class CountingSort_곽민우_김하연 {
	
	// 원본 데이터
	static int[] array= {7,2,3,5,7,1,4,6,7,5,0,1};
	
	// 개수 누적합을 저장할 배열
	static int[] count;
	
	// 정렬 결과를 저장할 배열
	static int[] sorted;
	
	public static void main(String[] args) {
		
		int maxNum=Integer.MIN_VALUE;
		
		// 배열 내의 최댓값 구하기
		for (int idx=0;idx<array.length;idx++) {
			maxNum=Math.max(maxNum, array[idx]);
		}
		
		// 배열 크기 초기화
		count=new int[maxNum+1];
		sorted=new int[array.length];
		
		// 각 숫자의 개수를 저장한다.
		for (int idx=0;idx<array.length;idx++) {
			count[array[idx]]+=1;
		}
		
		// 숫자 개수의 누적합을 구한다.
		for (int idx=1;idx<count.length;idx++) {
			count[idx]+=count[idx-1];
		}
		// count를 1씩 제거해가면서 정렬한다.
		// 스테이블 유지를 위해 array 끝에서부터 정렬
		for (int idx=array.length-1;idx>=0;idx--) {
			int num=array[idx];
			sorted[--count[num]]=num;
		}
		System.out.println("Before sorting: "+Arrays.toString(array));
		System.out.println("After sorting: "+Arrays.toString(sorted));
	}

}
