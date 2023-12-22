package algo_2023_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * BOJ 17615: 볼 모으기
 *
 * [ 문제 ]
 * 빨간색 볼과 파란색 볼이 일직선상에 섞여 놓여 있을 때, 볼을 옮겨서 같은 색 볼끼리 인접하게 놓이도록 하려고 한다.
 * 볼을 옮기는 규칙은 다음과 같다.
 *  1. 바로 옆에 다른 색깔의 볼이 있으면 그 볼을 모두 뛰어 넘어 옮길 수 있다. 즉, 빨간색 볼은 옆에 있는 파란색 볼 무더기를 한 번에 뛰어 넘어 옮길 수 있다.
 *  유사하게, 파란색 볼은 옆에 있는 빨간색 볼 무더기를 한 번에 뛰어 넘어 옮길 수 있다.
 *  2. 옮길 수 있는 볼의 색깔을 한 가지이다. 즉, 빨간색 볼을 처음에 옮겼으면 다음에도 빨간색 볼만 옮길 수 있다. 유사하게, 파란색 볼을 처음에 옮겼으면
 *  다음에도 파란색 볼만 옮길 수 있다.
 *
 *  [ 풀이 ]
 *  1. 빨간색 볼을 옮기는 시도와 파란색 볼을 옮기는 시도를 해본다.
 *      1-1. 공을 왼쪽으로 옮기는 시도와 오른쪽으로 옮기는 시도를 한다.
 *
 */
public class BOJ_17615_볼모으기_김하연 {

    static BufferedReader br;
    static int N;
    static char[] balls;

    static int minCount=Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));

        N=Integer.parseInt(br.readLine().trim());
        balls=br.readLine().trim().toCharArray();

        // 빨간색 볼을 오른쪽으로 옮기는 경우
        // 파란색 볼이 나온 시점부터 빨간색 볼을 발견할 경우 무조건 옮겨야 한다.
        boolean flag=false;
        int redCount=0;
        for (int idx=N-1;idx>=0;idx--){
            if (balls[idx]=='B') flag=true;
            if (flag && balls[idx]=='R'){
                redCount++;
            }
        }
        minCount=Math.min(minCount,redCount);

        // 빨간색 볼을 왼쪽으로 옮기는 경우
        // 파란색 볼이 나온 시점부터 빨간색 볼을 발견할 경우 무조건 옮겨야 한다.
        flag=false;
        redCount=0;
        for (int idx=0;idx<N;idx++){
            if (balls[idx]=='B') flag=true;
            if (flag && balls[idx]=='R') redCount++;
        }
        minCount=Math.min(minCount,redCount);

        // 파란색 볼을 오른쪽으로 옮기는 경우
        // 빨간색 볼이 나온 시점부터 파란색 볼을 발견할 경우 무조건 옮겨야 한다.
        flag=false;
        int blueCount=0;
        for (int idx=N-1;idx>=0;idx--){
            if (balls[idx]=='R') flag=true;
            if (flag&&balls[idx]=='B'){
                blueCount++;
            }
        }
        minCount=Math.min(minCount,blueCount);

        // 파란색 볼을 왼쪽으로 옮기는 경우
        // 빨간색 볼이 나온 시점부터 파란색 볼을 발견할 경우 무조건 옮겨야 한다.
        flag=false;
        blueCount=0;
        for (int idx=0;idx<N;idx++){
            if (balls[idx]=='R') flag=true;
            if (flag && balls[idx]=='B') blueCount++;
        }
        minCount=Math.min(minCount,blueCount);

        System.out.println(minCount);
    }

}
