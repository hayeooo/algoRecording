package algo_2024_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [ 문제 ]
 * 강의실에서 대회를 치르려면 거리두기 수칙을 지켜야 한다.
 * 한 명씩 앉을 수 있는 테이블이 행마다 W개씩 H행에 걸쳐 있을 때, 모든 참가자는 세로로 N칸 또는 가로로 M칸 이상 비우고 앉아야 한다.
 * 즉, 다른 모든 참가자와 세로줄 번호의 차가 N보다 크거나 가로줄 번호의 차가 M보다 큰 곳에만 앉을 수 있다.
 * 강의실이 거리두기 수칙을 지키면서 최대 몇 명을 수용할 수 있는지 구해보자.
 *
 * [ 풀이 ]
 * 세로로 N칸 가로로 M칸인 직사각형에 사람을 왼쪽 위에 배치한다고 했을 경우 최대로 수용할 수 있다.(Greedy)
 * : 틀림 ( 왜 안나오는지 모르겠음)
 */
public class BOJ_23971_ZOAC4_김하연 {
    
    static BufferedReader br;
    static StringTokenizer st;
    
    static int H;   // 행
    static int W;   // 열
    static int N;   // 세로로 N 칸 거리두기
    static int M;   // 가로로 M 칸 거리두기

    public static void main(String[] args) throws IOException {

        br=new BufferedReader(new InputStreamReader(System.in));

        st=new StringTokenizer(br.readLine().trim());
        H=Integer.parseInt(st.nextToken());
        W=Integer.parseInt(st.nextToken());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        
        // 한 사람을 채웠다고 가정하고 그 사람부터 거리를 두었을 때 나오는 최대 수용 인원 수
        int h=(H-1)/(N+1)+1;
        int w=(W-1)/(M+1)+1;

        System.out.println(h*w);
    }
}
