package algo_2023_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ 2531: 회전 초밥
 * << 문제 >>
 * 회전 초밥 음식점에는 회전하는 벨트 위에 여러 가지 종류의 초밥이 접시에 담겨 놓여 있고, 손님은 이 중에서 자기가 좋아하는 초밥을 골라서 먹는다.
 * 초밥의 종류를 번호로 표현하고 벨트 위에는 같은 종류의 초밥이 둘 이상 있을 수 있다.
 * 다음과 같이 두 가지 행사를 진행한다.
 * 	1. 원래 회전 초밥은 손님이 마음대로 초밥을 고르고, 먹은 초밥만큼 식대를 계산하지만, 벨트의 임의의 한 위치부터 k개의 접시를 연속해서 먹을 경우 할인된 가격으로 제공한다.
 * 	2. 각 고객에게 초밥의 종류 하나가 쓰인 쿠폰을 발행하고, 1번 행사에 참가할 경우 이 쿠폰에 적혀진 종류의 초밥 하나를 추가로 무료로 제공한다. 만약 이 번호에 적이 초밥이 현재 벨트 위에 없을 경우,
 * 		요리사가 새로 만들어 손님에게 제공한다.
 * 회전 초밥 음식점의 벨트 상태, 메뉴에 있는 초밥의 가짓수, 연속해서 먹는 접시의 개수, 쿠폰 번호가 주어졌을 때, 손님이 먹을 수 있는 초밥 가짓수의 최댓값을 구하여라.
 * 
 * << 풀이 방법 >>
 * 초밥 벨트에서 k개의 연속된 접시를 고르는 시작점과 끝점을 지정한다.
 * 한 칸씩 이동하며 k개의 접시에 들어있는 초밥의 개수를 저장한다.
 * 새로운 초밥 종류가 들어올 경우 가짓수+1, 기존에 1개였던 초밥의 종류가 포함되지 않은 경우 가짓수-1
 * 위 과정을 진행하며 최대 가짓수를 계산한다.
 * 
 */
public class BOJ_2531_회전초밥_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N,d,k,c;		// 접시의 수, 초밥의 가짓수, 연속해서 먹는 접시의 수, 쿠폰 번호
	static int[] sushi;		// sushi의 종류마다 가지는 가짓수 개수
	static int[] dishes;	// 회전 초밥 벨트에 놓인 초밥 정보
	static int maxVariety=0;	// 최대 가짓수
	
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 접시의 수(N), 초밥의 가짓수(d), 연속해서 먹는 접시의 수(k), 쿠폰 번호(c)를 입력받는다.
		st=new StringTokenizer(br.readLine().trim());
		N=Integer.parseInt(st.nextToken());
		d=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken());
		
		// 회전 초밥 벨트에 놓인 초밥 정보를 입력받는다.
		dishes=new int[N];
		sushi=new int[d+1];
		for (int idx=0;idx<N;idx++) {
			dishes[idx]=Integer.parseInt(br.readLine().trim());
		}
		
		int cnt=0;
		for (int idx=0;idx<k;idx++) {
			int num=dishes[idx];
			// 새로운 초밥이라면
			if (sushi[num]==0) {
				cnt++;
			}
			// 해당 초밥 종류를 추가한다.
			sushi[num]+=1;
		}
		// 쿠폰에 해당하는 초밥을 선택하지 않았을 경우
		if (sushi[c]==0) cnt++;
		sushi[c]++;
		maxVariety=Math.max(maxVariety, cnt);
		
		//System.out.println(Arrays.toString(sushi));
		//System.out.println("cnt: "+cnt);
		
		for (int start=1;start<N;start++) {
			// 이전 초밥의 종류를 차감한다.
			int prev=dishes[start-1];
			sushi[prev]--;
			if (sushi[prev]==0) {
				cnt--;
			}
			// 새로운 초밥의 종류를 더한다.
			int next=dishes[(start+k-1)%N];
			if (sushi[next]==0) cnt++;
			sushi[next]++;
			
			// 최대 가짓수를 갱신한다.
			maxVariety=Math.max(maxVariety, cnt);
		}
		// 최대 가짓수를 출력한다.
		System.out.println(maxVariety);

	}

}
