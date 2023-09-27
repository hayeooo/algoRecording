package algo_2023_09_27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**

BOJ 1194: 달이 차오른다, 가자.

민식이는 직사각형 미로 속에 있고 미로를 탈출하려고 한다.
미로는 다음과 같이 구성된다.
빈칸('.'), 벽('#'), 열쇠('a'~'f'), 문('A'~'F'), 민식이의 현재 위치('0'), 출구('1')
열쇠 칸에 처음 들어갈 때 열쇠를 잡는다.
문은 대응하는 열쇠가 있을 때만 이동할 수 있다. 
한 번의 움직임은 현재 위치에서 수평이나 수직으로 한 칸 이동한다.
미로를 탈출하는데 걸리는 이동 횟수의 최솟값을 구한다. 

가중치가 없이 이동횟수의 최솟값을 구하기 위해서 BFS를 사용한다.
아무 생각없이 BFS를 사용하려고 생각해보면, 이미 방문한 곳은 갈 수 없게 처리를 해놓는다.(최솟값)
즉, 열쇠를 획득하고 이미 방문한 경로로 다시 돌아갈 수 있는 상황을 계산할 수 없다.(경로 중복 발생)
열쇠 획득 상태를 고려하기 위해 visited 배열 차원의 개수를 더 늘려야한다.
 
key 상태에 따른 visited 차원 개수를 더 늘린다면 "key의 상태"를 수식으로 어떻게 나타낼 수 있는지 고민해야 한다.
key의 종류와 획득 여부를 동시에 나타낼 수 있는 비트마스킹을 사용한다.
'a'~'f'의 6가지 종류의 key가 있고 key를 소유하고 있다면 1, 없다면 0으로 표시한다.
110000, 1011000... 와 같이 이진수로 표현할 수 있고 이진수이기 때문에 숫자로 나타낼 수 있으며
visited차원에 접근할 때 활용할 수 있다. 

1. 미로의 세로 크기 N, 가로 크기 M, 미로의 모양을 입력받는다.
2.  각 열쇠를 한 개씩 가지고 있을 때의 상태를 Map에 저장한다.
3. 현재 위치로부터 bfs를 돌린다.
	3-1. 열쇠를 만난 경우, 열쇠를 획득함과 동시에 획득한 열쇠 상태에 맞는 visited 공간으로 이동한다.
	3-2. 문을 만났다면 현재 상태(획득한 key들을 알 수 있음)에서 문을 열 수 있는지 확인한다.
	3-3. 출구를 만나면 빠져나온다.(이동횟수의 최솟값) 
	
*/

class Info{
    int r;
    int c;
    int move;
    int status;
    Info(){}

Info(int r,int c,int move,int status){
    this.r=r;
    this.c=c;
    this.move=move;
    this.status=status;
}
}
public class BOJ_1194_달이차오른다가자_김하연 {
    static BufferedReader br;
    static StringTokenizer st;

    static int N,M;             // 미로의 세로 크기, 가로 크기
    static char[][] maze;       // 미로의 정보
    static Map<Character,Integer> keyMap;   // 열쇠의 정보를 저장하는 배열
    static boolean[][][] visited;   // 방문 여부를 표시하는 배열
    
    
    static int[] dx={-1,0,1,0};
    static int[] dy={0,1,0,-1};

    static int minDist=Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
	    br=new BufferedReader(new InputStreamReader(System.in));
	
	    // 미로의 세로, 가로 크기를 입력받는다.
	    st=new StringTokenizer(br.readLine().trim());
	    N=Integer.parseInt(st.nextToken());
	    M=Integer.parseInt(st.nextToken());
	
	    // 미로의 정보를 입력받는다.
	    maze=new char[N][M];
	    int startR = 0, startC=0;
	
	    for (int row=0;row<N;row++){
	        maze[row]=br.readLine().trim().toCharArray();
	        for (int col=0;col<M;col++){
	            if (maze[row][col]=='0'){
	                startR=row;
	                startC=col;
	            }
	        }
	    }
	    // key의 정보를 저장한다.
	    keyMap=new HashMap<>();
	    // a:97 f:102
	    for (char key='a';key<='f';key++){
	        keyMap.put(key,1<<(key-'a'));
	    }
	    
	    visited=new boolean[N][M][1<<7];
	    
	    System.out.println(bfs(startR,startC,0,0));
	
	
	    
	
	}

	// 현재 위치(r,c), 이동 횟수(move), 보유한 key의 상태(status)
	public static int bfs(int r,int c, int move, int status){
	    Queue<Info> que=new ArrayDeque<>();
	    que.offer(new Info(r,c,move,status));
	    visited[r][c][0]=true;
	    
	    while (!que.isEmpty()){
	        Info curInfo=que.poll();
	        int curMove=curInfo.move;
	        int curStatus=curInfo.status;
	        // 탈출구를 찾았다면
	        // bfs 종료
	        if (maze[curInfo.r][curInfo.c]=='1'){
	            return curMove;
	        }
	        // 현재 위치에서 4방 탐색
	        for (int d=0;d<dx.length;d++){
	            int nextR=curInfo.r+dx[d];
	            int nextC=curInfo.c+dy[d];
	
	            // 배열 범위
	            if (nextR<0 || nextR>=N || nextC<0 || nextC>=M){
	                continue;
	            }
	            // 이미 방문한 경우
	            if (visited[nextR][nextC][curStatus]){
	                continue;
	            }
	            // 벽인 경우
	            else if (maze[nextR][nextC]=='#'){
	                continue;
	            }
	            
	            // 열쇠인 경우
	            else if (maze[nextR][nextC]>='a' && maze[nextR][nextC]<='f'){
	                int nextStatus=curStatus|keyMap.get(maze[nextR][nextC]);
	                if (!visited[nextR][nextC][nextStatus]){
	                    visited[nextR][nextC][nextStatus]=true;
	                    que.add(new Info(nextR,nextC,curMove+1,nextStatus));
	                }
	            }
	            // 문인 경우
	            else if(maze[nextR][nextC]>='A' && maze[nextR][nextC]<='F'){
	                // 현재 가지고 있는 key로 문을 열 수 있는지 확인
	                // 현재 문을 열 수 있는 key를 구해야 한다.
	                int openKey=keyMap.get((char)(maze[nextR][nextC]-'A'+'a'));
	                // and 연산을 해본다.
	                if ((openKey&curStatus)==openKey){
	                    if (!visited[nextR][nextC][curStatus]){
	                        visited[nextR][nextC][curStatus]=true;
	                        que.add(new Info(nextR,nextC,curMove+1,curStatus));
	                    }
	                }
	            }
	            // 빈 칸인 경우
	            else{
	                visited[nextR][nextC][curStatus]=true;
	                que.offer(new Info(nextR,nextC,curMove+1,curStatus));
	                continue;
	            }
	        }
	    }
	    return -1;
	}
}