package algo_2023_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * << 문제  >>
 * 가장 많은 고층 빌딩이 보이는 고층 빌딩을 찾으려고 한다.
 * 빌딩은 총 N개가 있는데 빌딩은 선분으로 나타낸다.
 * i번째 빌딩은 (i,0)부터 (i,높이)의 선분으로 나타낼 수 있다.
 * 고층 빌딩 A에서 다른 고층 빌딩 B가 볼 수 있는 빌딩이 되려면 두 지붕을 잇는 선분이 A와 B를 제외한 다른 고층 빌딩을 지나거나 접하지 않아야 한다.
 * 가장 많은 고층 빌딩이 보이는 빌딩을 구하고, 거기서 보이는 빌딩의 수를 출력하는 프로그램을 작성한다.
 * 
 * << 풀이 방법  >>
 * 임의의 한 빌딩을 고층 빌딩이 보이는 빌딩으로 가정하고 그 위치에서 보이는 빌딩의 수를 구한다.
 * 자신보다 왼쪽에 있는 빌딩은 이전에 저장한 값(건물을 관찰할 수 있는지 여부를 저장하는 배열)을 통해 확인한다.
 * 즉, 이전 빌딩 기준으로 현재 빌딩을 관찰할 수 있다면 현재 빌딩을 기준으로 이전 빌딩 또한 관찰할 수 있다.
 * 오른쪽에 있는 빌딩은 기존에 저장한 기울기보다 클 시, 건물을 관찰할 수 있다.
 * 기울기 비교는 나눗셈보다 곱셈을 활용한다. java에서 나눗셈은 정수 결과가 나와 정확하지 않을 수 있기 때문이다.
 */
public class BOJ_1027_고층건물_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;		// 건물의 개수
	static int[] buildings;	//건물들의 높이
	static boolean[][] check; 	// 건물을 관찰할 수 있는지 여부를 저장하는 배열
	static int maxCnt=0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 건물의 개수를 입력 받는다.
		N=Integer.parseInt(br.readLine().trim());
		
		// 건물들의 높이를 입력받는다.
		buildings=new int[N];
		check=new boolean[N][N];
		
		st=new StringTokenizer(br.readLine().trim());
		for (int idx=0;idx<N;idx++) {
			buildings[idx]=Integer.parseInt(st.nextToken());
		}
		
		for (int curBuilding=0;curBuilding<N;curBuilding++) {
			int cnt=0;
			long tx=1;
			long ty=-1000000001;
			
			// 현재 위치의 빌딩보다 오른쪽에 있는 빌딩 높이 비교
			for (int nextBuilding=curBuilding+1;nextBuilding<N;nextBuilding++) {
				// 기울기 비교
				long dx=nextBuilding-curBuilding;
				long dy=buildings[nextBuilding]-buildings[curBuilding];
				
				// 기존 기울기보다 더 크다면 건물을 관찰할 수 있다.
				if (tx*dy>ty*dx) {
					check[curBuilding][nextBuilding]=true;
					tx=dx;
					ty=dy;
					cnt++;
				}
			}
			
			// 현재 위치의 빌딩보다 왼쪽에 있는 빌딩 높이 비교
			for (int prevBuilding=0;prevBuilding<curBuilding;prevBuilding++) {
				if (check[prevBuilding][curBuilding]) cnt++;
			}
			
			maxCnt=Math.max(cnt,maxCnt);
		}
		System.out.println(maxCnt);
	}
}
