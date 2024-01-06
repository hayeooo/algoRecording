package algo_2024_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 1929 : 소수 구하기
 *
 * [ 문제 ]
 * M이상 N이하의 소수를 모두 출력하는 프로그램을 작성하시오.
 *
 * [ 풀이 ]
 * N^(1/2)인 수까지 나눠떨어지지 않는 수 : 소수
 */
public class BOJ_1929_소수구하기_김하연 {

    static BufferedReader br;
    static StringTokenizer st;

    static StringBuilder sb;

    static int M;
    static int N;

    public static void main(String[] args) throws IOException {

        // M과 N을 입력받는다.
        br=new BufferedReader(new InputStreamReader(System.in));
        st=new StringTokenizer(br.readLine().trim());

        M=Integer.parseInt(st.nextToken());
        N=Integer.parseInt(st.nextToken());

        sb=new StringBuilder();

        // M이상 N이하 소수를 구한다.
        for (int num=M;num<=N;num++){
            // 1은 소수가 아니다.
            if (num==1){
                continue;
            }
            // 2는 소수이므로 결과값에 포함시킨다.
            if (num==2){
                sb.append(num).append("\n");
                continue;
            }
            // 2부터 해당 숫자의 제곱근까지 숫자 중
            // 나눠떨어지는 수가 있다면 소수가 아니다.
            boolean isPrime=true;
            for (int n=2;n<=(int)Math.sqrt(num);n++){
                if (num%n==0){
                    isPrime=false;
                    break;
                }
            }
            if (isPrime) sb.append(num).append("\n");
        }
        // 결과를 출력한다.
        System.out.print(sb);
    }
}
