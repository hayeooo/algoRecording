package algo_2024_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ 1932 : 정수 삼각형
 * [ 문제 ]
 * 맨 위층부터 시작해서 아래에 있는 수 중 하나를 선택하여 아래층으로 내려올 때, 이제까지 선택된 수의 합이 최대가 되는 경로를 구하는 프로그램을 작성
 * 아래층에 있는 현재 층에서 선택된 수의 대각선 왼쪽 또는 대각선 오른쪽에 있는 것 중에서만 선택할 수 있다.
 * 삼각형의 크기는 1 이상 500 이하이다.
 * 삼각형을 이루고 있는 수는 모두 정수이며, 범위는 0이상 9999 이하이다.
 *
 * [ 풀이 ]
 * 매번 최대가 되는 경로를 구한다면(brute force) 2^500 : 시간초과
 * 최대가 될 수 있는 경로를 기록한다.
 * 선택한 수의 최대 합은 9999*500이므로 int 범위를 벗어나지 않는다.
 */
public class BOJ_1932_정수삼각형_김하연 {

    static BufferedReader br;
    static StringTokenizer st;
    static int n;

    static int[][] triangle;

    public static void main(String[] args) throws IOException {

        br=new BufferedReader(new InputStreamReader(System.in));

        // 삼각형의 크기 입력
        n=Integer.parseInt(br.readLine().trim());

        triangle=new int[n][n];

        // 삼각형의 정보를 입력받는다.
        for (int row=0;row<n;row++){
            st=new StringTokenizer(br.readLine().trim());
            for (int col=0;col<=row;col++){
                triangle[row][col]=Integer.parseInt(st.nextToken());
            }
        }

        for (int row=1;row<n;row++){
            for (int col=0;col<=row;col++){
                // 왼쪽 가장자리인 경우
                if (col==0){
                    triangle[row][col]+=triangle[row-1][col];
                }
                // 오른쪽 가장자리인 경우
                else if(col==row){
                    triangle[row][col]+=triangle[row-1][col-1];
                }
                // 둘 다 아닌 경우, 왼쪽 대각선 오른쪽 대각선 위 중 큰 값을 더한다.
                else{
                    triangle[row][col]+=Math.max(triangle[row-1][col],triangle[row-1][col-1]);
                }
            }
        }

        int maxValue=0;
        for (int idx=0;idx<n;idx++){
            maxValue=Math.max(maxValue,triangle[n-1][idx]);
        }

        System.out.println(maxValue);
    }
}
