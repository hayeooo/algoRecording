package algo_2024_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ 1931 : 회의실 배정
 * [ 문제 ]
 * 한 개의 회의실이 있는데 이를 사용하고자 하는 N개의 회의에 대하여 회의실 사용표를 만들려고 한다.
 * 각 회의 I에 대해 시작시간과 끝나는 시간이 주어져 있고, 각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 회의의 최대 개수를 찾아보자.
 * 단, 회의는 한번 시작하면 중간에 중단될 수 없으며 한 회의가 끝나는 것과 동시에 다음 회의가 시작될 수 있다.
 * 회의의 시작시간과 끝나는 시간이 같을 수도 있다. 이 경우에는 시작하자마자 끝나는 것으로 생각하면 된다.
 *
 * [ 풀이 ]
 * 회의가 끝나는 시간을 기준으로 오름차순 정렬한다.
 * 회의가 끝나는 시간이 같을 경우 시작시간이 빠른 순대로 정렬한다.
 * 회의가 빨리 끝날수록 다음 회의가 들어갈 공간이 많기 때문이다.(Greedy)
 *
 */
public class BOJ_1931_회의실배정_김하연 {

    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static Meeting[] meetings;
    static class Meeting implements Comparable<Meeting>{
        int start;
        int end;

        Meeting(int start, int end){
            this.start=start;
            this.end=end;
        }

        public int compareTo(Meeting other){
            if (end==other.end){
                return start-other.start;
            }
            return end-other.end;
        }

        public String toString(){
            return "start: "+start+", end: "+end;
        }
    }
    public static void main(String[] args) throws IOException {

        br=new BufferedReader(new InputStreamReader(System.in));

        // 회의의 수를 입력받는다.
        N=Integer.parseInt(br.readLine().trim());
        meetings=new Meeting[N];

        for (int idx=0;idx<N;idx++){
            st=new StringTokenizer(br.readLine().trim());

            int start=Integer.parseInt(st.nextToken());
            int end=Integer.parseInt(st.nextToken());

            meetings[idx]=new Meeting(start,end);
        }

        // 회의가 끝나는 기준으로 정렬한다.
        Arrays.sort(meetings);

        // 회의의 개수를 구한다.
        int available=0;
        int lastTime=-1;
        for (int idx=0;idx<N;idx++){
            int startTime=meetings[idx].start;
            int endTime=meetings[idx].end;

            if (lastTime<=startTime){
                available+=1;
                lastTime=endTime;
            }
        }
        System.out.println(available);

    }
}
