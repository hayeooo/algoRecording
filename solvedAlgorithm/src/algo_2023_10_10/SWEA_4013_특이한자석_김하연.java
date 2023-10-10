package algo_2023_10_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


/*
 * SWEA 4013: 특이한 자석
 * 
 * 판에 4개의 자석이 놓여져 있고, 각 자석은 8개의 날(튀어나온 곳)을 가지고 있다.
 * 자석의 갈 날마다 N극 또는 S극의 자성을 가지고 있다.
 * 임의의 자석을 1칸씩 K번 회전시킬 때, 붙어 있는 자석은 서로 붙어 있는 날의 자성과 다를 경우에만 인력에 의해 반대 방향으로 1칸 회전된다.
 * 모든 회전이 끝난 후, 아래와 같은 방법으로 점수를 계산하여 점수의 총 합을 출력한다.
 * 1번 자석에서 빨간색 화살표 위치에 있는 날의 자성의 N극이면 0점, S극이면 1점을 획득한다.
 * 2번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N극이면 0점, S극이면 2점을 획득한다.
 * 3번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N극이면 0점, S극이면 4점을 획득한다.
 * 4번 자석에서 빨간색 화살표 위치에 있는 날의 자성이 N극이면 0점, S극이면 8점을 획득한다.
 * 
 * 
 * << 풀이 방법 >>
 * 1. 회전시키려는 자석을 중심으로 인접한 자석 중 서로 붙어 있는 날의 자성이 다른 자석의 회전 방향을 저장한다.
 * 2. 저장한 회전 방향을 토대로 자석을 회전시킨다.
 * 3. 위 과정을 K번 반복한다.
 */
public class SWEA_4013_특이한자석_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T;			// 테스트케이스 개수
	static int K;			// 자석을 회전시키는 개수
	static LinkedList<Integer>[] magnets;
	
	static final int LEFT=6;
	static final int RIGHT=2;
	static final int N=0;
	static final int S=1;
	
	public static class Magnet{
		int num;
		int dir;		// 회전 방향 (시계방향 :1, 반시계방향: -1)
		
		Magnet(int num, int dir){
			this.num=num;
			this.dir=dir;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트케이스 수를 입력받는다.
		T=Integer.parseInt(br.readLine().trim());
		sb=new StringBuilder();
		
		for (int test_case=1;test_case<=T;test_case++) {
			
			// 자석을 회전시키는 개수를 입력받는다.
			K=Integer.parseInt(br.readLine().trim());
			magnets=new LinkedList[4];
			
			// 1번부터 4번 자석까지 각각 8개 날의 자성 정보를 입력받는다.
			for (int magnetNum=0;magnetNum<4;magnetNum++) {
				magnets[magnetNum]=new LinkedList<>();
				st=new StringTokenizer(br.readLine().trim());
				
				for (int idx=0;idx<8;idx++) {
					magnets[magnetNum].add(Integer.parseInt(st.nextToken()));
				}
			}
			
			// K번 동안 자석을 회전시킨다.
			for (int k=0;k<K;k++) {
				st=new StringTokenizer(br.readLine().trim());
				
				// 회전시키려는 자석의 번호, 회전 방향을 입력받는다.
				int magnetNum=Integer.parseInt(st.nextToken())-1;
				int dir=Integer.parseInt(st.nextToken());
				
				// 자석을 회전한다.
				rotate(magnetNum,dir);
			}
			
			// K번 회전시킨 후, 점수를 구한다.
			int totalScore=0;
			for (int idx=0;idx<4;idx++) {
				if(magnets[idx].get(0)==S) {
					totalScore+=Math.pow(2, idx);
				}
			}
			sb.append("#").append(test_case).append(" ").append(totalScore).append("\n");
		}
		System.out.print(sb);
	}
	// 회전할 수 있는 모든 자석을 구하고 
	// 한꺼번에 회전해야 한다.
	public static void rotate(int magnetNum, int dir) {
		
		// 회전방향을 담은 배열
		int[] rotateDir=new int[4];
		rotateDir[magnetNum]=dir;
		
		// 현 자석 위치를 기준으로 왼쪽 자석
		for (int curMagnet=magnetNum;curMagnet>0;curMagnet--) {
			
			int leftMagnet=curMagnet-1;
			
			// 현 자석의 왼쪽 날과 왼쪽 자석의 오른쪽 날 비교
			// 서로 붙어 있는 날의 자성이 같을 경우
			if (magnets[curMagnet].get(LEFT)==magnets[leftMagnet].get(RIGHT)) break;
			
			// 서로 붙어 있는 날의 자성이 다른 경우
			// 현 자석의 방향과 반대 방향으로 돌아야 한다.
			else {
				rotateDir[leftMagnet]=rotateDir[curMagnet]*(-1);
			}
			
		}
		// 현 자석 위치를 기준으로 오른쪽 자석
		for (int curMagnet=magnetNum;curMagnet<3;curMagnet++) {
			
			int rightMagnet=curMagnet+1;
			
			// 현 자석의 오른쪽 날과 오른쪽 자석의 왼쪽 날 비교
			// 서로 붙어 있는 날의 자성이 같을 경우
			if (magnets[curMagnet].get(RIGHT)==magnets[rightMagnet].get(LEFT)) break;
			
			// 서로 붙어 있는 날의 자성이 다른 경우
			// 현 자석의 방향과 반대 방향으로 돌아야 한다.
			else {
				rotateDir[rightMagnet]=rotateDir[curMagnet]*(-1);
			}
		}
		// 각 자석의 회전 방향이 정해졌다면 자석을 회전시킨다.
		for (int num=0;num<4;num++) {
			
			// 회전하지 않아도 되는 경우
			if (rotateDir[num]==0) continue;
			
			// 시계 방향으로 회전해야 하는 경우
			else if(rotateDir[num]==1) {
				// 맨 뒤 날이 맨 앞으로 와야 한다.
				int toMove=magnets[num].removeLast();
				magnets[num].addFirst(toMove);
			}
			// 반시계 방향으로 회전해야 하는 경우
			else if(rotateDir[num]==-1) {
				// 맨 앞 날이 맨 뒤로 가야한다.
				int toMove=magnets[num].removeFirst();
				magnets[num].add(toMove);
			}
		}
		
	}

}
