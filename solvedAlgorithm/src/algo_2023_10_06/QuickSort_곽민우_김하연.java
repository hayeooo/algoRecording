package algo_2023_10_06;

import java.util.Arrays;

/*
 * QuickSort
 * 배열 가운데 하나의 원소를 고르며, 그 원소를 pivot이라고 한다.
 * pivot을 기준으로 2개의 부분 배열로 분할한다.
 * 피벗을 기준으로 피벗 앞에는 피벗보다 작은 수가, 오른쪽에는 피벗보다 큰 수가 옮겨진다.
 * 피벗을 제외한 왼쪽 리스트와 오른쪽 리스트를 다시 정렬한다.
 * 부분 리스트들이 더 이상 분할이 불가능할 때까지 반복한다.
 */
public class QuickSort_곽민우_김하연 {
	
	static int[] arr = { 3, 1, 5, 6, 20, 10, 7, 11, 15, 9 };
	
	public static void main(String[] args) {
		quickSort(arr,0,arr.length-1);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void quickSort(int[] arr, int start, int end) {
		// 원소가 하나일 경우 return
		if (start>=end) {
			return;
		}
		
		int pivot=start;
		int low=start+1;
		int high=end;
		
		while (low<=high) {
			// low: pivot보다 큰 숫자의 위치를 찾는다.
			while (low<=end && arr[low]<=arr[pivot]) {
				low++;
			}
			// high: pivot보다 작은 숫자의 위치를 찾는다.
			while (high>start && arr[high]>=arr[pivot]) {
				high--;
			}
			// 서로 교차할 때
			// : low index에서 pivot보다 큰 숫자가 없다.
			// : high index에서 pivot보다 작은 숫자가 없다.
			// 즉, pivot이 들아갈 위치를 찾았다.
			if (low>high) {
				swap(arr,high,pivot);
			}
			// 그렇지 않은 경우
			// 두 원소의 위치를 바꾼다.
			else {
				swap(arr,high,low);
			}
		}
		// pivot을 중심으로 두 부분으로 쪼갠다.
		// 현재 pivot의 위치는 high에 있다.
		quickSort(arr,start,high-1);
		quickSort(arr,high+1,end);
	}
	
	public static void swap(int[] arr, int idx1, int idx2) {
		int tmp=arr[idx1];
		arr[idx1]=arr[idx2];
		arr[idx2]=tmp;
	}

}
