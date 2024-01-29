package algo_2024_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BOJ 2304 : 창고 다각형
 */
public class BOJ_2304_창고다각형_김하연 {

    static BufferedReader br;
    static StringTokenizer st;
    static int N;               // 기둥의 개수


    public static void main(String[] args) throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));

        // 기둥의 개수를 입력 받는다.
        N=Integer.parseInt(br.readLine().trim());

        int[] poles=new int[1001];
        int start=Integer.MAX_VALUE;
        int end=Integer.MIN_VALUE;
        int highestIdx=0;
        int highest=0;
        for (int idx=0;idx<N;idx++){
            st=new StringTokenizer(br.readLine().trim());
            int loc=Integer.parseInt(st.nextToken());
            int height=Integer.parseInt(st.nextToken());

            poles[loc]=height;

            start=Math.min(start,loc);
            end=Math.max(end,loc);
            if (height>highest){
                highest=height;
                highestIdx=loc;
            }
        }
        // 왼쪽에서부터 자신보다 큰 높이를 더한다.
        int area=0;
        int prevHighHeight=0;         // 기준 높이
        for (int idx=start;idx<=highestIdx;idx++){
            if (prevHighHeight<=poles[idx]){
                area+=poles[idx];
                prevHighHeight=poles[idx];
            }
            else{
                area+=prevHighHeight;
            }
        }
        prevHighHeight=0;
        // 오른쪽에서부터 자신보다 큰 높이를 구한다.
        for (int idx=end;idx>highestIdx;idx--){
            if(prevHighHeight<=poles[idx]){
                area+=poles[idx];
                prevHighHeight=poles[idx];
            }
            else{
                area+=prevHighHeight;
            }

        }

        System.out.println(area);
    }
}
