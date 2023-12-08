package algo_2023_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * BOJ 22866: 탑 보기
 * [ 문제 ]
 * 일직선으로 다양한 높이의 건물이 총 N개가 존재한다.
 * 각 건물 옥상에서 양 옆에 존재하는 건물의 옆을 몇 개 볼 수 있는지 궁금해졌다.
 * i번째 건물 기준으로 i-1, i-2, ... 1번째 건물은 왼쪽에 i+1, i+2, .. N번째 건물은 오른쪽에 있다.
 * 현재 있는 건물의 높이가 L이라고 가정하면 높이가 L보다 큰 곳의 건물만 볼 수 있다.
 * 각 건물에서 볼 수 있는 건물들이 어떤 것이 있는지 구해보자
 *
 * [ 풀이 ]
 * stack을 사용하여 해당 건물에서 볼 수 있는 건물들을 담는다.(왼쪽, 오른쪽 stack에 나눠서)
 * 스택에 담는 과정을 각 건물마다 수행한다.
 * O(n^2): 100,000 * 100,000 => 시간초과
 *
 * 구글링 결과 스택 하나로 왼->오, 오->왼 과정을 수행한다.
 * 이전 건물에서 볼 수 없었던 건물들은 현재 건물에서 동일하게 볼 수 없다.
 * 인접한 건물에서 볼 수 없는 건물들은 서로 연관이 있다.
 */
public class BOJ_22866_탑보기_김하연 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    
    static int N;               // 건물들의 개수
    static int[] height;        // 건물들의 높이
    static int[] near;          // 각 건물에 인접한 건물 번호
    static int[] cnt;           // 각 건물에서 볼 수 있는 건물 개수
    
   public static void main(String[] args) throws IOException{

       br=new BufferedReader(new InputStreamReader(System.in));

       // 건물 개수를 입력받는다.
       N=Integer.parseInt(br.readLine().trim());

       height=new int[N+1];
       near=new int[N+1];
       cnt=new int[N+1];

       // 건물들의 높이를 입력받는다.
       st=new StringTokenizer(br.readLine().trim());
       for (int idx=1;idx<=N;idx++){
           height[idx]=Integer.parseInt(st.nextToken());
           // 왼쪽에서 건물을 볼 수 없고, 오른쪽에서 건물을 볼 수 있을 때
           // 건물들 사이 거리를 계산할 때 오류가 없기 위함
           near[idx]=-100_000;
       }

       // 왼쪽에서 보이는 건물 개수와 인접 건물 번호를 저장한다.
       // stack은 오른쪽으로 이동하면서 push해야 한다.
       Stack<Integer> stack=new Stack<>();
       for (int idx=1;idx<=N;idx++){
           // 자신보다 건물 높이가 낮은 곳은 pop 한다.
           while (!stack.isEmpty() && height[stack.peek()]<=height[idx]){
               stack.pop();
           }
           // 현재 위치에서 볼 수 있는 건물 번호만 남았다.
           cnt[idx]=stack.size();
           if (stack.size()>0) near[idx]=stack.peek();
           stack.push(idx);
       }

       stack=new Stack<>();
       // 현재 건물에서 오른쪽에서 보이는 건물 개수와 인접 건물 번호를 저장한다.
       for (int idx=N;idx>0;idx--){
           // 자신보다 건물 높이가 낮은 곳은 pop 한다.
           while (!stack.isEmpty() && height[stack.peek()]<=height[idx]){
               stack.pop();
           }
           // 현재 위치에서 볼 수 있는 건물 번호가 남았다.
           cnt[idx]+=stack.size();
           if (stack.size()>0){
               near[idx]=(idx-near[idx]>stack.peek()-idx)?stack.peek():near[idx];
           }
           stack.push(idx);
       }

       sb=new StringBuilder();
       for (int idx=1;idx<=N;idx++){
           sb.append(cnt[idx]);
           if (cnt[idx]>0){
               sb.append(" ").append(near[idx]);
           }
           sb.append("\n");
       }
       System.out.print(sb);
   }
}
