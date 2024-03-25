package algo_2024_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 서로 다른 색깔인 종이인 경우 4개로 자른다.
 * 4개로 잘린 종이의 색깔이 같은지 확인한다.
 * 이 과정을 반복하는 재귀함수를 작성한다.
 * 종료 조건은 종이의 길이가 1이거나 종이 색깔이 모두 같은 경우이다.
 */

public class BOJ_2630_색종이만들기_김하연 {

    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static int[][] paper;
    static int white;
    static int blue;

    static final int WHITE=0;
    static final int BLUE=1;

    public static void main(String[] args) throws IOException {

        br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine().trim());

        paper=new int[N][N];
        // 종이의 정보를 입력받는다.
        for (int row=0;row<N;row++){
            st=new StringTokenizer(br.readLine().trim());
            for (int col=0;col<N;col++){
                paper[row][col]=Integer.parseInt(st.nextToken());
            }
        }
        dfs(0,0,N);
        System.out.println(white);
        System.out.println(blue);

    }
    public static void dfs(int startX,int startY, int size){
        if (size==1 || isOneColor(startX,startY,size)){
            if(paper[startX][startY]==WHITE) white+=1;
            else if (paper[startX][startY]==BLUE) blue+=1;
            return;
        }
        int nextSize=size/2;
        dfs(startX,startY,nextSize);
        dfs(startX+nextSize,startY,nextSize);
        dfs(startX,startY+nextSize,nextSize);
        dfs(startX+nextSize,startY+nextSize,nextSize);

    }
    public static boolean isOneColor(int startX,int startY,int size){
        boolean isOne=true;
        for (int x=startX;x<startX+size;x++){
            for(int y=startY;y<startY+size;y++){
                if(paper[startX][startY]!=paper[x][y]){
                    isOne=false;
                    break;
                }
            }
            if(!isOne) break;
        }
        return isOne;
    }
}
