package algo_2023_10;

import java.util.Arrays;

/*
 * Merge Sort
 * 시간 복잡도 : O(nlogn)
 * 원소가 저장되어 있는 배열을 계속 쪼개서 길이가 1인 배열을 만들고, 그 이후 정렬하면서 합치는 알고리즘
 * binary tree 형태로 쪼개기 때문에 가질 수 있는 최대 깊이는 logn, 각 분할(n개) 별로 합병을 진행
 */
public class MergeSort_곽민우_김하연 {
	
	// 원본 데이터
	static int[] array= {7,2,3,5,7,1,4,6,7,5,0,1};
	static int[] sorted;
	
	public static void main(String[] args) {
		
		sorted= new int[array.length];
		sort(array,0,array.length-1);
		System.out.println(Arrays.toString(sorted));
	}
	
	public static void sort(int[] arr, int left, int right) {
		
		// 쪼갠 원소의 길이가 1이 아닐 때 쪼갠다.
		if (left<right) {
			int mid=(left+right)/2;
			
			// 두 개로 쪼갠다.
			sort(arr,left,mid);
			sort(arr,mid+1,right);
			
			// 쪼갠 두 개의 그룹을 하나로 합친다.
			merge(arr,left,mid,right);
		}
	}
	
	public static void merge(int[] arr, int left, int mid, int right) {
		
		int leftIndex=left;
		int rightIndex=mid+1;
		int index=left;
		
		// 분할된 두 개의 배열을 합친다.
		while (leftIndex<=mid && rightIndex<=right) {
			if (arr[leftIndex]<=arr[rightIndex]) {
				sorted[index++]=arr[leftIndex++];
			}
			else {
				sorted[index++]=arr[rightIndex++];
			}
		}
		
		// 남아있는 값을 복사한다.
		// 왼쪽 배열은 모두 확인했을 경우
		// 오른쪽 배열을 복사해야 한다.
		if (leftIndex>mid) {
			for (int idx=rightIndex;idx<=right;idx++) {
				sorted[index++]=arr[idx];
			}
		}
		// 오른쪽 배열 요소를 모두 확인했을 경우
		// 남아있는 왼쪽 배열 요소를 복사해야 한다.
		else {
			for (int idx=leftIndex;idx<=mid;idx++) {
				sorted[index++]=arr[idx];
			}
		}
		
		// 정렬된 결과를 저장한다.(배열 복사)
		for (int idx=left;idx<=right;idx++) {
			arr[idx]=sorted[idx];
		}
	}
}
