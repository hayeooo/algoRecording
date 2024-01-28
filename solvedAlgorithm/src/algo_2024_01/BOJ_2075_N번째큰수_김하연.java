package algo_2024_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2075_N번째큰수_김하연 {

    static BufferedReader br;
    static StringTokenizer st;
    static int N;               // N번째 큰수
    static int[] num;

    public static void main(String[] args) throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));

        N=Integer.parseInt(br.readLine().trim());

        num=new int[N*N];

        // 표의 정보를 입력받는다.
        // 2차원 배열을 1차원 형태의 배열로 입력받는다.
        for (int row=0;row<N;row++){
            st=new StringTokenizer(br.readLine().trim());
            for (int col=0;col<N;col++){
                num[row*N+col]=Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(num);
        System.out.println(num[num.length-N]);
    }
}
