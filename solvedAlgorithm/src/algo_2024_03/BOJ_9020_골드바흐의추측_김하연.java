package algo_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9020_골드바흐의추측_김하연 {

    static BufferedReader br;
    static StringBuilder sb;
    static int T;
    static boolean[] primeArr;
    static final int RANGE=10000;

    public static void main(String[] args) throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));

        T=Integer.parseInt(br.readLine().trim());

        primeArr=new boolean[RANGE+1];

        // 소수인지 아닌지 판별
        primeArr[2]=true;
        for (int num=3;num<=RANGE;num++){
            boolean isPrime=true;
            for (int divideNum=2;divideNum<num;divideNum++){
                if (num%divideNum==0){
                    isPrime=false;
                    break;
                }
            }
            primeArr[num]=isPrime;
        }
        sb=new StringBuilder();
        // 골드바흐파티션 구하기
        for (int idx=0;idx<T;idx++){
            int n=Integer.parseInt(br.readLine());
            // 두 소수의 차이가 가장 작은 것을 출력한다.
            for (int num=n/2;num<n;num++){
                if (primeArr[num]&&primeArr[n-num]){
                    sb.append(n-num).append(" ").append(num).append("\n");
                    break;
                }
            }
        }
        System.out.print(sb);
    }
}
