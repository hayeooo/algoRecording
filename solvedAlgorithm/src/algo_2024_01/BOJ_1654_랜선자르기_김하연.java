package algo_2024_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ 1654 : 랜선 자르기
 * [ 문제 ]
 * N개의 같은 길이의 랜선으로 만들고 싶기 때문에 K개의 랜선을 잘라서 만들어야 한다.
 * 편의를 위해 랜선을 자르거나 만들 때 손실되는 길이는 없다고 가정하며 기존의 K개의 랜선으로 N개의 랜선ㅇ르 만들 수 없는 경우는 없다고 가정하자.
 *
 * [ 풀이 ]
 * 1부터 K개의 랜선 중 가장 짧은 길이의 랜선까지 parametric search를 하여 N개를 만들 수 있는 최대 길이를 구한다.
 */
public class BOJ_1654_랜선자르기_김하연 {

    static BufferedReader br;
    static StringTokenizer st;

    static int K;           // 랜선의 개수
    static int N;           // 필요한 랜선의 개수

    static long[] lines;    // 이미 가지고 있는 랜선

    public static void main(String[] args) throws IOException{

        br=new BufferedReader(new InputStreamReader(System.in));

        st=new StringTokenizer(br.readLine().trim());

        K=Integer.parseInt(st.nextToken());
        N=Integer.parseInt(st.nextToken());

        lines=new long[K];
        long maxLength=0;
        for (int idx=0;idx<K;idx++){
            long line=Long.parseLong(br.readLine().trim());
            lines[idx]=line;
            maxLength=Math.max(maxLength,lines[idx]);
        }
        long result=binarySearch(1,maxLength);
        System.out.println(result);
    }

    public static long binarySearch(long start,long end){

        while(start<=end){
            long mid=(start+end)/2;

            // 만들 수 있는 랜선의 길이를 구한다.
            int cnt=0;
            for (int idx=0;idx<K;idx++){
                cnt+=(lines[idx]/mid);
            }
            if (cnt>=N){
                start=mid+1;
            }
            else{
                end=mid-1;
            }
        }
        return start-1;
    }
}
