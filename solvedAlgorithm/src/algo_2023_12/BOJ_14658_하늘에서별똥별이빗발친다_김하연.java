package algo_2023_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [ 문제 ]
 * 지표면에 떨어지는 별똥별의 수를 최소화해야 한다.
 * 욱제는 L*L 크기의 트램펄린을 준비했다.
 * 별똥별이 어디로 떨어질지는 이미 알고 있기 때문에, 욱제는 이 트램펄린으로 최대한 많은 별똥별을 우주로 튕겨낼 계획이다.
 * 최대한 많은 별똥별을 튕겨내도록 트램펄린을 배치했을 때, 지구에는 몇 개의 별똥별이 부딪히게 될까?
 * 별똥별이 떨어지는 위치는 겹치지 않으며 별똥별은 트램펄린의 모서리에 부딪혀도 튕겨나간다.
 *
 * [ 문제 풀이 ]
 * 0,0 에서 시작할 필요가 있는가?
 * x의 최솟값에서 x의 최댓값으로 범위를 설정한다.
 * 한 칸씩 이동하면서 트램펄린에 속한 별똥별의 개수는 몇 개인지 구한다.
 * 최악의 경우 L이 1이고 N,M이 500,000, K가 100 이라면
 * 500,000 * 500,000 * 100 : 시간초과
 * 트램펄린의 위치 개수를 제한해야 한다.
 * 한 별똥별의 x 위치와 다른 별똥별의 y 위치를 기준으로 한 트램펄린은 2개의 별똥별을 포함하는 트램펄린이 될 수 있다.
 * 이 안에서 별똥별이 몇 개 포함되는지 확인한다.
 * 100 * 100 * 100 : 통과
 *
 */
public class BOJ_14658_하늘에서별똥별이빗발친다_김하연 {

    static BufferedReader br;
    static StringTokenizer st;

    static int N;           // 별똥별이 떨어지는 구역의 가로 길이
    static int M;           // 별똥별이 떨어지는 구역의 세로 길이
    static int L;           // 트램펄린의 한 변의 길이
    static int K;           // 별똥별의 수

    static class Star{
        int x;
        int y;
        Star(int x,int y){
            this.x=x;
            this.y=y;
        }
    }

    static List<Star> stars;
    static int maxCount=0;
    public static void main(String[] args) throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));
        // N, M ,L, K를 입력받는다.
        st=new StringTokenizer(br.readLine().trim());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        L=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(st.nextToken());

        stars=new ArrayList<>();

        // 별똥별이 떨어지는 위치를 입력받는다.
        for (int idx=0;idx<K;idx++){
            st=new StringTokenizer(br.readLine().trim());
            int x=Integer.parseInt(st.nextToken());
            int y=Integer.parseInt(st.nextToken());

            // 위치를 저장한다.
            stars.add(new Star(x,y));
        }

        // 별똥별의 위치를 기준으로 트램펄린을 놓을 위치를 선정한다.
        for (Star starX:stars){
            for (Star starY:stars){
                int startX=starX.x;
                int startY=starY.y;
                // 해당 위치에 트램펄린을 놓았을 때 포함되는 별똥별의 개수를 구한다.
                maxCount=Math.max(maxCount,countStars(startX,startY));
            }
        }
        // 지구에 부딪히는 별똥별의 개수를 출력한다.
        System.out.println(K-maxCount);
    }
    public static int countStars(int startX,int startY){
        int count=0;
        for (Star star : stars){
            if (startX<=star.x && star.x<=startX+L && startY<=star.y && star.y<=startY+L){
                count++;
            }
        }

        return count;
    }
}
