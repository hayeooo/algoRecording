package algo_2024_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


public class BOJ_24042_횡단보도_김하연 {

    static BufferedReader br;
    static StringTokenizer st;

    static int N;
    static int M;


    public static void main(String[] args) throws IOException {

        br=new BufferedReader(new InputStreamReader(System.in));
        st=new StringTokenizer(br.readLine().trim());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        List<int[]> seq=new ArrayList<>();

        // 순서를 저장해야 한다.
        for (int idx=0;idx<M;idx++){
            st=new StringTokenizer(br.readLine().trim());

            int region1=Integer.parseInt(st.nextToken());
            int region2=Integer.parseInt(st.nextToken());

            int[] connectedRegions=new int[]{Math.min(region1,region2),Math.max(region1,region2)};

            seq.add(connectedRegions);
        }
        // 지역 1에서 출발하여 최소 시간을 저장하는 배열
        int[] time=new int[N+1];
        Arrays.fill(time,Integer.MAX_VALUE);
        time[1]=0;
        for (int sec=1;;sec++){

            // 횡단보도 초록불이 되는 곳
            int loc=(sec-1)%M;

            // 방문한 이력이 있는 경우 이동할 수 있다.
            int[] regions=seq.get(loc);
            if (time[regions[0]]!=Integer.MAX_VALUE && time[regions[1]]==Integer.MAX_VALUE){
                time[regions[1]]=sec;

                if(regions[1]==N) break;
            }
        }
        System.out.println(time[N]);
    }

}
