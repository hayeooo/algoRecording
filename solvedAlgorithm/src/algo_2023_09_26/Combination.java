package algo_2023_09_26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Combination {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N,M;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		st=new StringTokenizer(br.readLine().trim());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		map=new int[N][M];
		for (int row=0;row<N;row++) {
			st=new StringTokenizer(br.readLine().trim());
			for (int col=0;col<M;col++) {
				map[row][col]=Integer.parseInt(st.nextToken());
			}
		}
		
		comb(map,0,0);
		
	}
	
	static void comb(int[][] map, int start, int cnt) {
        if (cnt == 3) {
            for (int row=0;row<N;row++) {
            	System.out.println(Arrays.toString(map[row]));
            	
            }
            System.out.println("=================");
            return;
        }

	    for (int i = start; i < N*M; i++) {
	        int x = i / M;
	        int y = i % M;
	
	        if (map[x][y] == 0) {
	            map[x][y] = 3;
	            comb(map, i + 1, cnt + 1);
	            map[x][y] = 0;
	        }
	    }
	}

}
