package algo_2024_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_22251_빌런호석_김하연 {

    static BufferedReader br;
    static StringTokenizer st;
    static int N;               // N 이하의 수
    static int K;               // K 자리 수
    static int P;               // P 개를 반전
    static int X;               // 현재 X 층에 멈춰 있음

    static int[][] led={
            {1,1,1,1,1,1,0},    // 0
            {0,1,1,0,0,0,0},    // 1
            {1,1,0,1,1,0,1},    // 2
            {1,1,1,1,0,0,1},    // 3
            {0,1,1,0,0,1,1},    // 4
            {1,0,1,1,0,1,1},    // 5
            {1,0,1,1,1,1,1},    // 6
            {1,1,1,0,0,0,0},    // 7
            {1,1,1,1,1,1,1},    // 8
            {1,1,1,1,0,1,1}     // 9
    };


    public static void main(String[] args) throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));
        st=new StringTokenizer(br.readLine().trim());

        N=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());
        P=Integer.parseInt(st.nextToken());
        X=Integer.parseInt(st.nextToken());

        // 현재 층수를 K 자리 수의 led 모양으로 바꾼다.
        int[] now=convertToLED(X);
        int result=0;
        // 1층부터 N층까지 P개 이하로 반전할 수 있는 수를 구한다.
        for (int floor=1;floor<=N;floor++){
            if (floor==X) continue;
            // 비교할 층을 LED 형태로 바꾼다
            int[] other=convertToLED(floor);
            int diffCount=countDiff(now,other);
            if (diffCount>=1 && diffCount<=P) result+=1;
        }

        System.out.println(result);

    }
    public static int countDiff(int[] now, int[] other){
        int count=0;

        for (int i=0;i<K;i++){
            int nowNum=now[i];
            int otherNum=other[i];
            for (int j=0;j<7;j++){
                if (led[nowNum][j]!=led[otherNum][j]){
                    count++;
                }
            }
        }

        return count;
    }
    public static int[] convertToLED(int num){
        int[] result=new int[K];

        for (int idx=K-1;idx>=0;idx--){
            result[idx]=num%10;
            num/=10;
        }

        return result;
    }
}
