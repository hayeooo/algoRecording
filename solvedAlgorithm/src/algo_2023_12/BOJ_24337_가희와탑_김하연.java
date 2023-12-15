package algo_2023_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BOJ 24337: 가희와 탑
 * 일직선으로 다양한 높이의 건물들이 N개 존재한다.
 * 가희는 건물들의 왼쪽에, 단비는 건물들의 오른쪽에 있다.
 * 일직선 상에 가희와 단비, 건물들은 아래와 같은 순서로 배치되어 있다.
 *  * 가희의 오른쪽에는 1번 건물이 있다.
 *  * x가 1이상 N-1이하의 정수일 때, x번 건물의 오른쪽에는 x+1번 건물이 있다.
 *  * N번 건물의 오른쪽에는 단비가 있다.
 *
 * 가희와 단비가 볼 수 있는 건물은 아래와 같다.
 *  * 가희는 1번 건물을 볼 수 있다.
 *  * k번 건물보다 왼쪽에 있는 건물들이 모두 k번 건물보다 높이가 낮다면, 가희는 k번 건물을 볼 수 있다.
 *  * 단비는 N번 건물을 볼 수 있다.
 *  * k번 건물보다 오른쪽에 있는 건물들이 모두 k번 건물보다 높이가 낮다면, 단비는 k번 건물을 볼 수 있다.
 * 가희와 단비 사이에 있는 건물의 개수 N과 가희가 볼 수 있는 건물의 개수 a, 단비가 볼 수 있는 건물의 개수 b가 주어진다.
 * 사전 순으로 가장 앞서는 N개의 건물 높이 정보를 출력한다.
 *
 * [ 풀이 ]
 * 가희 입장에서 왼쪽부터 오른쪽으로 채우고
 * 단비 입장에서 오른쪽부터 왼쪽으로 채운 결과가 적절한지 판단한다.
 * 첫 풀이, 배열을 이용해서 풀이하려고 했지만, 사전 순 정렬이 이루어지지 않았다.
 *
 * 조건
 * 보이는 건물의 개수 합 -1 > 건물 개수 : invalid
 * 사전 순 조건
 * 가장 높은 건물을 보이는 개수 하나에 포함해야 한다.
 * 1이 최대한 앞으로 나와야 한다.
 */
public class BOJ_24337_가희와탑_김하연 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    
    static int N;       // 건물 개수
    static int a;       // 가희가 볼 수 있는 건물의 개수
    static int b;       // 단비가 볼 수 있는 건물의 개수
    public static void main(String[] args) throws IOException{
        br=new BufferedReader(new InputStreamReader(System.in));

        // 건물의 개수, 가희와 단비가 볼 수 있는 건물의 개수를 입력받는다.
        st=new StringTokenizer(br.readLine().trim());
        N=Integer.parseInt(st.nextToken());
        a=Integer.parseInt(st.nextToken());
        b=Integer.parseInt(st.nextToken());

        // a+b-1>N이면 조건에 맞는 건물들의 높이 정보를 구할 수 없다.
        if (a+b-1>N){
            System.out.print(-1);
            return;
        }
        // 건물의 높이 정보를 저장할 list
        List<Integer> buildings=new ArrayList<>();

        // 가희가 볼 수 있는 건물 정보를 입력한다.
        for (int height=1;height<a;height++){
            buildings.add(height);
        }

        // 가희와 단비가 볼 수 있는 겹치는 건물은 가장 높은 건물로 입력한다.
        buildings.add(Math.max(a,b));

        // 단비가 볼 수 있는 건물 정보를 입력한다.
        for (int height=b-1;height>=1;height--){
            buildings.add(height);
        }

        // 건물 수를 다 채우지 못했을 경우
        // 사전 순대로 가장 앞선 것을 출력하려면
        // 앞쪽 숫자에 1을 최대한 많이 넣어야 한다.
        while (buildings.size()<N){
            // 가희가 볼 수 있는 건물의 개수가 1이라면
            // 가장 앞에 보이는 건물 뒤로 1을 넣어야 한다.
            if (a==1){
                buildings.add(1,1);
            }
            // 그렇지 않은 경우
            // 가장 앞쪽에 1을 채운다.
            else{
                buildings.add(0,1);
            }
        }

        // 결과를 출력한다.
        sb=new StringBuilder();
        for (int idx=0;idx<buildings.size();idx++){
            sb.append(buildings.get(idx)).append(" ");
        }
        System.out.print(sb);
    }
}
