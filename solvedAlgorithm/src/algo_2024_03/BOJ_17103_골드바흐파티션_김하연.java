package algo_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17103_골드바흐파티션_김하연 {

    static BufferedReader br;
    static StringBuilder sb;
    static int T;
    static int N;
    static boolean[] prime;

    public static void main(String[] args) throws IOException {

        br=new BufferedReader(new InputStreamReader(System.in));

        // prime 배열에 소수인지 아닌지 판별하는 배열을 채운다.
        prime=new boolean[1000001];
        prime[2]=true;
        for (int num=3;num<=1000000;num++){
            // 제곱근으로 나눈 수까지 확인
            boolean isPrime=true;
            for (int divide=2;divide<=(int)Math.sqrt(num);divide++){
                if (num%divide==0){
                    isPrime=false;
                    break;
                }
            }
            if(isPrime) prime[num]=true;
        }
        sb=new StringBuilder();
        T=Integer.parseInt(br.readLine().trim());
        for (int tc=0;tc<T;tc++){
            N=Integer.parseInt(br.readLine().trim());
            int cnt=0;
            for (int num=N/2;num>=2;num--){
                if(prime[num] && prime[N-num]) cnt+=1;
            }
            sb.append(cnt).append("\n");
        }
        System.out.print(sb);
    }
}
