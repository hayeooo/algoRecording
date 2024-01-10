package algo_2024_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * BOJ 1002 : 터렛
 * 조규현과 백승환에게 류재명의 위치를 계산하라는 명령을 내렸다.
 * 조규현과 백승환은 각각 자신의 터렛 위치에서 현재 적까지의 거리를 계산했다.
 * 조규현의 좌표 (x1,y1)와 백승환의 좌표 (x2,y2)가 주어지고, 조규현이 계산한 류재명과의 거리가 r1과 백승환이 계산한 류재명과의 거리 r2가 주어졌을 때,
 * 류재명이 있을 수 있는 좌표의 수를 출력하는 프로그램을 작성하시오.
 * 
 * [ 문제 ]
 * 두 개의 원 접점의 개수 구하기
 * 두 원의 중심 거리가 반지름의 합과 같다면 접점 1개
 * 두 원의 중심 거리가 반지름의 합보다 작다면 접점 2개
 * 두 원의 중심 거리가 반지름의 합보다 크다면 접점 0개
 * 두 원이 일치한다면 접점 무한대
 */

public class BOJ_1002_터렛_김하연 {

    static BufferedReader br;
    static StringTokenizer st;

    static StringBuilder sb;
    static int T;

    public static void main(String[] args) throws IOException {

        br=new BufferedReader(new InputStreamReader(System.in));

        T=Integer.parseInt(br.readLine().trim());

        sb=new StringBuilder();

        for (int tc=0;tc<T;tc++){
            st=new StringTokenizer(br.readLine().trim());
            int x1=Integer.parseInt(st.nextToken());
            int y1=Integer.parseInt(st.nextToken());
            int r1=Integer.parseInt(st.nextToken());

            int x2=Integer.parseInt(st.nextToken());
            int y2=Integer.parseInt(st.nextToken());
            int r2=Integer.parseInt(st.nextToken());

            // 두 원의 중심 사이의 거리
            int distance=(int)(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));

            // 위치가 무한할 경우
            if (x1==x2 && y1==y2 && r1==r2){
                sb.append(-1).append("\n");
            }

            // 두 원이 접하는 경우
            else if (distance==Math.pow(r1+r2,2) || distance==Math.pow(r1-r2,2)){
                sb.append(1).append("\n");
            }

            // 두 원이 겹치지 않는 경우
            else if (distance>Math.pow(r1+r2,2) || distance<Math.pow(r1-r2,2)){
                sb.append(0).append("\n");
            }
            else{
                sb.append(2).append("\n");
            }
        }
        System.out.print(sb);
    }
}
