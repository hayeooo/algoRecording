package algo_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1446_지름길_김하연 {
    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static int D;
    static class Path{
        int start;
        int weight;
        Path(int start,int weight){
            this.start=start;
            this.weight=weight;
        }
    }
    static List<Path>[] pathList;

    public static void main(String[] args) throws IOException {

        br=new BufferedReader(new InputStreamReader(System.in));
        st=new StringTokenizer(br.readLine().trim());

        N=Integer.parseInt(st.nextToken());
        D=Integer.parseInt(st.nextToken());

        pathList=new ArrayList[10001];
        for (int d=0;d<=10000;d++){
            pathList[d]=new ArrayList<>();
        }

        // 지름길 정보를 입력받는다.
        for (int idx=0;idx<N;idx++){
            st=new StringTokenizer(br.readLine().trim());
            int start=Integer.parseInt(st.nextToken());
            int end=Integer.parseInt(st.nextToken());
            int weight=Integer.parseInt(st.nextToken());

            if (end-start>weight){
                pathList[end].add(new Path(start,weight));
            }
        }

        // 해당 지점이 도착지점일 때 최솟값을 저장한다.
        int[] dp=new int[D+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=0;

        // 도착 지점을 기준으로 dp를 update한다.
        for (int d=1;d<=D;d++){
            if (!pathList[d].isEmpty()){
                // 지름길 중 가장 최솟값을 저장한다.
                for (Path path:pathList[d]){
                    if (dp[d]<dp[path.start]+path.weight) continue;
                    dp[d]=Math.min(dp[d-1]+1,dp[path.start]+path.weight);
                }
            }else{
                dp[d]=dp[d-1]+1;
            }
        }
        System.out.println(dp[D]);
    }
}
