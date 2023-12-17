package algo_2023_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * BOJ 19637: IF문 좀 대신 써줘
 * 캐릭터의 전투력에 맞는 칭호를 출력하는 프로그램을 작성한다.
 *
 * [ 풀이 ]
 * 해당 칭호의 전투력 상한값은 10^9 이하의 음이 아닌 정수가 주어지므로 타입은 long으로 한다.
 * 비내림차순으로 상한값이 주어졌으므로, 전투력에 맞는 값을 비교하여 찾는다.
 * 최악의 경우 10^5 * 10^5 : 시간 초과를 하므로 천투력에 맞는 값을 찾기 위한 시간을 줄여야 한다.
 * : 이분 탐색을 이용한다.
 */
public class BOJ_19637_IF문좀대신써줘_김하연 {

    static BufferedReader br;
    static StringTokenizer st;

    static StringBuilder sb;
    static String[] word;
    static Long[] value;
    static int N;           // 칭호의 개수
    static int M;           // 캐릭터들의 개수


    public static void main(String[] args) throws IOException {
        br=new BufferedReader(new InputStreamReader(System.in));

        // 칭호의 개수, 캐릭터들의 개수를 입력받는다.
        st=new StringTokenizer(br.readLine().trim());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());

        word=new String[N];
        value=new Long[N];
        // 각 칭호의 이름과 전투력 상한값을 입력받는다.
        for (int idx=0;idx<N;idx++){
            st=new StringTokenizer(br.readLine().trim());
            word[idx]=st.nextToken();
            value[idx]=Long.parseLong(st.nextToken());
        }
        sb=new StringBuilder();
        for (int idx=0;idx<M;idx++){
            long key=Long.parseLong(br.readLine().trim());
            int res=binarySearch(key,0,N-1);
            if (res==-1){
                sb.append(word[0]).append("\n");
            }else{
                sb.append(word[res]).append("\n");
            }
        }
        System.out.print(sb);
    }

    public static int binarySearch(long key,int start,int end){

        int res=-1;
        while (start<=end){
            if (start<0){
                break;
            }
            int mid=(start+end)/2;
            if (key<=value[mid]){
                end=mid-1;
                res=mid;
            }
            else{
                start=mid+1;
            }
        }
        return res;
    }
}
