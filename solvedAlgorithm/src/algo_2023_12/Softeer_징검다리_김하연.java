package algo_2023_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Softeer 징검다리
 * [ 문제 ]
 * 남북으로 흐르는 개울에 동서로 징검다리가 놓여져 있다.
 * 이 징검다리의 돌은 들쑥날쑥하여 높이가 모드 다른다. 철수는 개울의 서쪽에서 동쪽으로 높이가 점점 높은 돌을 밟으면서 개울을 지나가려고 한다.
 * 돌의 높이가 서쪽의 돌부터 동쪽 방향으로 주어졌을 때 철수가 밟을 수 있는 돌의 최대 개수는?
 * 
 * [ 풀이 ]
 * 최대 증가 수열 문제
 * 이진 탐색으로 풀어보자
 * LIS 길이를 구하면 된다.
 */
public class Softeer_징검다리_김하연 {
    static BufferedReader br;
    static StringTokenizer st;
    
    static int N;       // 돌의 개수를 입력받는다.
    static int[] arr;   // 돌의 높이를 저장하는 배열
    static int[] dp;    // 최장길이수열을 저장하는 배열
    public static void main(String[] args) throws IOException{
        
        br=new BufferedReader(new InputStreamReader(System.in));

        // 돌의 개수를 입력받는다.
        N=Integer.parseInt(br.readLine().trim());

        arr=new int[N];
        dp=new int[N];

        // 돌의 높이를 입력받는다.
        st=new StringTokenizer(br.readLine().trim());
        for (int idx=0;idx<N;idx++){
            arr[idx]=Integer.parseInt(st.nextToken());
        }

        // 최장길이수열에서 각 높이가 들어갈 수 있는 위치에 값을 넣는다.
        // 배열 내 값 자체가 최장길이수열을 나타내는 것이 아니다.
        // 최장길이수열을 보장하기 위한 값이 들어가있고 실제로는 길이를 구하는 것이다.
        int LIS=0;
        for (int idx=0;idx<N;idx++){
            int res=binarySearch(arr[idx],0,LIS,LIS+1);
            // 최장길이수열의 마지막 칸에 새로 들어와야 한다면
            if (res==-1){
                dp[LIS++]=arr[idx];
            }
            else{
                dp[res]=arr[idx];
            }
        }
        System.out.println(LIS);
        
    }
    
    // 최장길이수열 배열에 들어갈 위치를 return한다.

    /**
     * 
     * @param value : 최장길이수열에 넣을 값
     * @param start : 최장길이수열 탐색 시작지점
     * @param end : 최장길이수열 탐색 마지막 지점
     * @param size : 허용 가능한 최장길이수열의 크기
     * @return : 값이 들어갈 수 있는 위치
     */
    public static int binarySearch(int value, int start,int end, int size){
        int res=0;
        while (start<=end){
            int mid=(start+end)/2;
            if (value<=dp[mid]){
                end=mid-1;
                res=mid;
            }
            else{
                start=mid+1;
            }
        }
        // 최장길이수열의 마지막에 들어가야하는 경우
        if (start==size){
            res=-1;
        }
        return res;
    }
}
