package algo_2023_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [ 문제 ]
 * n대의 자동차의 실제 연비 값이 주어졌을 때, q개의 질의에 대해 임의로 3대의 자동차를 골라 테스트하여
 * 중앙값이 mi값이 나오는 서로 다른 경우의 수를 구하는 프로그램을 작성한다.
 *
 * [ 풀이 ]
 * 3대의 자동차 중 중앙값이 되려면 주어진 숫자보다 작은 수, 큰 수가 각각 하나씩 존재해야 한다.
 * 주어진 자동차 연비를 정렬하고, 이분 탐색을 통해 주어진 숫자의 위치를 구한다.
 * 그 위치를 기준으로 (작은 수의 개수)*(큰 수의 개수)가 중앙값이 나오는 서로 다른 경우의 수이다.
 */
public class Softeer_자동차테스트_김하연 {
    
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    
    static int n;           // 자동차의 연비에 해당하는 값
    static int q;           // 테스트 결과로 기대하는 값 개수
    static long[] fuels;     // 연료 정보를 저장하는 배열

    public static void main(String[] args) throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));

        // n, q의 값을 입력받는다.
        st=new StringTokenizer(br.readLine().trim());
        n=Integer.parseInt(st.nextToken());
        q=Integer.parseInt(st.nextToken());

        // 연료 정보를 입력받는다.
        fuels=new long[n];
        st=new StringTokenizer(br.readLine().trim());
        for (int idx=0;idx<n;idx++){
            long fuel=Long.parseLong(st.nextToken());
            fuels[idx]=fuel;
        }

        // 테스트값을 입력받는다.
        sb=new StringBuilder();
        Arrays.sort(fuels);
        for (int idx=0;idx<q;idx++){
            long m=Long.parseLong(br.readLine().trim());
            // 연료의 위치를 구한다
            int res=binarySearch(m,0,fuels.length-1);
            // 연료가 존재하지 않는 경우, 중앙값을 구하는 경우의 수가 0이다.
            if (res==-1) sb.append(0).append("\n");

            // 연료가 존재하는 경우, (연료보다 작은 수의 개수)*(연료보다 큰 수의 개수)가 경우의 수가 된다.
            else{
                int point=res+1;
                int less=point-1;
                int more=fuels.length-point;
                sb.append(less*more).append("\n");
            }
        }
        // 결과를 출력한다.
        System.out.print(sb);
    }
    // 이진탐색으로 연료의 위치를 구한다.
    public static int binarySearch(long value,int start,int end){
        int res=-1;

        while(start<=end){
            if (start<0 || end>=fuels.length) break;
            int mid=(start+end)/2;
            if (value<fuels[mid]){
                end=mid-1;
            }
            else if(value>fuels[mid]){
                start=mid+1;
            }
            else if(value==fuels[mid]){
                res=mid;
                break;
            }
        }

        return res;
    }
}
