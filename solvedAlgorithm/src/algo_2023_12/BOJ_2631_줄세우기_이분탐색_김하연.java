package algo_2023_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * BOJ 2631: 줄세우기
 * 선생님은 1번부터 N번까지 번호가 적혀있는 번호표를 아이들의 가슴에 붙여주었다.
 * 선생님은 목적지까지 번호순서대로 일렬로 걸어가도록 하였다. 이동 도중에 보니 아이들의 번호 순서가 바뀌었다.
 * 선생님은 다시 번호 순서대로 줄을 세우기 위해 아이들의 위치를 옮기려고 한다. 아이들이 혼란스러워하지 않도록 하기 위해
 * 위치를 옮기는 아이들의 수를 최소로 하려고 한다.
 *
 * << 문제 풀이 >>
 * Greedy하게 생각해보자
 * 현재 아이 숫자 + 1 != 다음 아이 숫자 라면
 * 위치가 잘못되었으므로 자기 자리로 위치를 이동시킨다. : arraylist의 add method를 사용한다.
 * 원래 자기 자리로 가는 것인 순서대로 줄을 세우는 방법의 최소이다. -> 오히려 더 많이 움직이게 된다.
 * 그렇다면 이전 아이 번호 + 1이 자신 번호인 곳을 찾아서 넣으면 되지 않을까?
 * => 성립하지 않는 곳이 있다.
 * 문제와 같이 3 7 5 2 6 4인 곳에서 7번 아이를 1번 뒤로 옮기므로 이전 아이 번호 +1이 자신 번호임이 성립하지 않는다.
 * 
 * 구글링하여 얻은 답! LIS
 * 굳이 어린이 한 명씩 옮기려고 하지 않아도 된다.
 * 이미 오름차순으로 정렬되어 있는 어린이들은 움직일 필요가 없기 때문에 고정된 어린이 수를 뺀 나머지 어린이들은 위치를 옮겨야 한다.
 *
 * 동적계획법 풀이: O(n^2), 이분 탐색 풀이 : O(nlonn)
 * 이분 탐색으로 풀어보자!
 * 배열의 총 길이 자체가 LIS 값이 된다
 * i 번째 위치에서 1차원 dp 테이블에 채워진 원소의 총 길이는 전체 수열에서 해당 위치에서 가질 수 있는 LIS의 길이
 * 원소의 값들은 i번째 원소가 부분 수열의 길이를 늘릴 가능성이 있는지 파악할 원소로 구성
 *
 * 80ms
 */
public class BOJ_2631_줄세우기_이분탐색_김하연{

    static BufferedReader br;
    static int[] numbers;
    static int[] dp;
    static int LIS=0;

    public static void main(String[] args) throws IOException {

        br=new BufferedReader(new InputStreamReader(System.in));

        // 아이들의 수를 입력 받는다.
        int N=Integer.parseInt(br.readLine().trim());

        numbers=new int[N];
        dp=new int[N];

        for (int idx=0;idx<N;idx++){
            numbers[idx]=Integer.parseInt(br.readLine().trim());
        }

        // 최장 증가 길이 수열을 구한다.
        for (int idx=0;idx<N;idx++){
            int childNum=numbers[idx];

            // dp 배열에서 childNum이 들어갈 위치를 구한다.
            int res=binarySearch(childNum,0,LIS,LIS+1);

            // 제일 큰 숫자인 경우
            // 맨 마지막 칸에 넣는다.
            if (res==-1){
                dp[LIS++]=childNum;
            }
            else{
                dp[res]=childNum;
            }
        }
        // 전체 학생 수에서 최장증가길이 수열 값만큼 빼면
        // 움직여야 할 아이들의 수가 나온다.
        System.out.println(N-LIS);
    }

    public static int binarySearch(int value, int start, int end, int size){
        int res=0;

        while (start<=end){
            int mid=(start+end)/2;

            if (value<=dp[mid]){
                res=mid;
                end=mid-1;
            }
            else{
                start=mid+1;
            }
        }
        if (start==size){
            return -1;
        }
        return res;
    }
}
