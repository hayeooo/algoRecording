package algo_2024_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 20922 : 겹치는 건 싫어
 * [ 문제 ]
 * 수열에서 같은 원소가 여러 개 들어 있는 수열을 싫어한다.
 * 도현이를 위해 같은 원소가 K개 이하로 들어 있는 최장 연속 부분 수열의 길이를 구하려고 한다.
 * 100000 이하의 양의 정수로 이루어진 길이가 N인 수열이 주어진다.
 * 이 수욜에서 같은 정수를 K개 이하로 포함한 최장 연속 부분 수열의 길이를 구하는 프로그램을 작성해보자
 *
 * [ 풀이 ]
 * 수열에 등장하는 숫자의 개수를 세면서 최대로 늘릴 수 있을 때까지 수열의 길이를 구한다.
 * K개를 초과하는 경우 조건을 만족할 때까지 수열의 길이를 줄여나가면서 최장 연속 부분 수열의 길이를 찾는다.
 */
public class BOJ_20922_겹치는건싫어_김하연 {

    static BufferedReader br;
    static StringTokenizer st;
    static int N;               // 길이가 N인 수열
    static int K;               // 같은 정수 K 개 이하

    static int[] seq;
    static int[] count;
    public static void main(String[] args) throws IOException {

        br=new BufferedReader(new InputStreamReader(System.in));
        st=new StringTokenizer(br.readLine().trim());

        // N과 K를 입력받는다.
        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        seq=new int[N];
        count=new int[200001];

        // 수열을 입력받는다.
        st=new StringTokenizer(br.readLine().trim());
        for (int idx=0;idx<N;idx++){
            seq[idx]=Integer.parseInt(st.nextToken());
        }

        int maxLength=0;
        int start=0;
        int end=0;
        while (end<N){
            // 오른쪽으로 최대한 간다.
            while (end<N && count[seq[end]]+1<=K){
                count[seq[end]]+=1;
                end+=1;
            }
            maxLength=Math.max(end-start,maxLength);
            count[seq[start]]-=1;
            start++;
        }
        System.out.println(maxLength);
    }


}
