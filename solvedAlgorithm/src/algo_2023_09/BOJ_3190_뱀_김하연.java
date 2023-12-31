package algo_2023_09;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 3190: 뱀
 * N*N 정사각 보드 위에서 몇몇 칸에 사과가 놓여 있다.
 * 보드의 상하좌우 끝에 벽이 있고, 게임이 시작할 때 뱀은 맨위 맨좌측에 위치하고 뱀의 길이는 1이다. 뱀은 처음에 오른쪽을 향한다.
 * <<게임 규칙>>
 * 뱀은 매 초마다 아래와 같이 이동을 한다.
 *  1. 먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
 *  2. 만약 벽이나 자기자신의 몸과 부딪히면 게임이 끝난다.
 *  3. 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
 *  4. 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
 * 사과의 위치와 뱀의 이동경로가 주어질 때 이 게임이 몇 초에 끝나는지 계산한다.
 * 
 * 사과가 위치한 곳 제대로 저장해야 함
 * tail을 어떻게 변경할 것인지-> 배열이 아니라 QUEUE에 뱀의 위치를 모두 저장한다. tail은 queue.poll()
 */

// 뱀의 방향 변환 정보를 저장하는 클래스
class DirectionInfo{
	int x;
	char c;
	
	DirectionInfo(int x,char c){
		this.x=x;
		this.c=c;
	}
}
public class BOJ_3190_뱀_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;				// 보드의 크기
	static int K;				// 사과의 개수
	static int L;				// 방향 변환 횟수
	
	static boolean[][] map;			// 뱀이 존재하는 곳을 저장할 배열
	static boolean[][] apple;		// 사과가 존재하는 곳을 저장할 배열
	static DirectionInfo[] dir_arr;	// 뱀의 방향 변환 정보를 담은 배열
	
	static int[] dx= {-1,0,1,0};	// 북, 동, 남, 서
	static int[] dy= {0,1,0,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 보드의 크기를 입력받는다.
		N=Integer.parseInt(br.readLine().trim());
		map=new boolean[N][N];
		apple=new boolean[N][N];
		
		// 사과의 개수를 입력받는다.
		K=Integer.parseInt(br.readLine().trim());
		
		// 사과의 위치를 저장한다.
		for (int idx=0;idx<K;idx++) {
			st=new StringTokenizer(br.readLine().trim());
			int r=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			
			apple[r][c]=true;
		}
		
		// 방향 변환 횟수를 입력받는다.
		L=Integer.parseInt(br.readLine().trim());
		dir_arr=new DirectionInfo[L];
		
		// 방향 변환 정보를 입력받는다.
		for (int idx=0;idx<L;idx++) {
			st=new StringTokenizer(br.readLine().trim());
			int x=Integer.parseInt(st.nextToken());
			char c=st.nextToken().charAt(0);
			
			dir_arr[idx]=new DirectionInfo(x,c);
		}
		
		// 게임을 시작한다.
		int headR=0,headC=0;
		int tailR=0,tailC=0;
		int cur_dir=1;
		int convert_ptr=0;		// 방향 변환 정보를 담은 배열을 가리키는 포인터
		// 매 초마다 이동을 한다.
		for (int sec=1;;sec++) {
			// 먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
			int nextHeadR=headR+dx[cur_dir];
			int nextHeadC=headC=dy[cur_dir];
			
			// 만약 벽에 부딪히면 게임이 끝난다. 
			if (nextHeadR<0 || nextHeadR>=N || nextHeadC<0 || nextHeadC>=N) {
				break;
			}
			// 자기자신의 몸과 부딪히면 게임이 끝난다.
			if (map[nextHeadR][nextHeadC]) {
				break;
			}
			// 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
			if (apple[nextHeadR][nextHeadC]) {
				apple[nextHeadR][nextHeadC]=false;
			
			}
			// sec 초가 끝난 뒤, 방향 회전
		}
	}

}
