package algo_2024_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 1138 : 한 줄로 서기
 *
 * [ 문제 ]
 * 사람들은 자기보다 큰 사람이 왼쪽에 몇 명 있었는지만 기억한다. N 명의 사람이 있고 사람들의 키는 1부터 N까지 모두 다르다.
 * 각 사람들이 기억하는 정보가 주어질 때, 줄을 어떻게 서야 하는지 출력하는 프로그램을 작성하시오.
 *
 * [ 풀이 ]
 * 위상정렬을 생각했으나 왼쪽부터 자기보다 큰 사람의 수를 갖고 정렬을 할 수 없다.
 * 사람의 수가 10보다 작거나 같으므로 줄을 설 수 있는 모든 조합을 고려한다.
 * 각 사람들이 기억하는 정보가 일치하는 줄을 출력한다.
 *
 */
public class BOJ_1138_한줄로서기_김하연 {

    static BufferedReader br;
    static StringTokenizer st;

    static StringBuilder sb;
    static int N;
    static int[] arr;
    static int[] ans;
    public static void main(String[] args) throws IOException {

        br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine().trim());

        arr=new int[N+1];
        ans=new int[N];
        st=new StringTokenizer(br.readLine().trim());
        for (int idx=1;idx<=N;idx++){
            arr[idx]=Integer.parseInt(st.nextToken());
        }

        // 키가 작은 순서대로 줄을 선다.
        for (int idx=1;idx<=N;idx++){
            int cnt=arr[idx];
            for (int position=0;position<N;position++){
                // 자신보다 큰 숫자가 들어갈 공간을 남겨두고 자신의 자리를 찾은 경우
                if (cnt==0 && ans[position]==0){
                    ans[position]=idx;
                    break;
                }
                // 자신보다 큰 수가 들어갈 위치인 경우
                if (ans[position]==0) cnt-=1;
            }
        }
        sb=new StringBuilder();
        for (int idx=0;idx<N;idx++){
            sb.append(ans[idx]).append(" ");
        }
        System.out.println(sb);
    }

}
