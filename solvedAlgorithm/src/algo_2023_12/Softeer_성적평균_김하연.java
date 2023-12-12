package algo_2023_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Softeer Lv3 : 성적 평균
 * [ 문제 ]
 * N명의 학생들의 성적이 학번 순서대로 주어졌다.
 * 학번 구간 [A,B] 구간이 주어졌을 때 이 학생들 성적의 평균을 구하는 프로그램을 작성하라.
 *
 * [ 풀이 ]
 * 1<=N(학생수)<10^6
 * 1<=K(구간수)<10^4
 * 1<=S(학생의 성적)<=100
 * 매번 학생의 구간에 대한 덧셈과 평균을 계산한다면 시간 초과가 나온다.
 * 시간을 줄이기 위해 two pointer를 사용한다.
 */
public class Softeer_성적평균_김하연 {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int N;           // 학생 수
    static int K;           // 구간 수
    static int[] S;         // 학생의 성적
    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        // 학생 수와 구간 수를 입력받는다.
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 학생들의 성적을 입력받는다.
        S = new int[N + 1];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 1; idx <= N; idx++) {
            S[idx] = Integer.parseInt(st.nextToken());
        }
        sb=new StringBuilder();
        for (int k=0;k<K;k++){
            st=new StringTokenizer(br.readLine());
            int start=Integer.parseInt(st.nextToken());
            int end=Integer.parseInt(st.nextToken());

            int sum=0;
            for (int p=start;p<=end;p++){
                sum+=S[p];
            }
            sb.append(String.format("%.2f",sum*1.0/(end-start+1))).append("\n");
        }
        System.out.print(sb);
    }
}
